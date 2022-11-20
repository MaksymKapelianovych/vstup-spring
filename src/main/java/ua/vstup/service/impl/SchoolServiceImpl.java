package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.vstup.domain.School;
import ua.vstup.entity.SchoolEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.SchoolRepository;
import ua.vstup.service.SchoolService;
import ua.vstup.service.mapper.SchoolMapper;
import ua.vstup.service.utility.ServiceUtility;
import ua.vstup.utility.ParameterParser;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SchoolServiceImpl implements SchoolService {
    private static final int ITEMS_PER_PAGE = 3;

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public void add(School school) {
        school.setIs_active(true);
        school.setUnique_school_id(1);
        schoolRepository.findAll(Sort.by(Sort.Direction.ASC, "uniqueSchoolId")).stream()
                .findFirst()
                .ifPresent(schoolEntity -> school.setUnique_school_id(schoolEntity.getUniqueSchoolId() + 1));

        if(schoolRepository.save(schoolMapper.mapToEntity(school)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    /**
     * This school should be present in database as new row with same unique_school_id and is_active == true
     * Previous active school should become inactive
     */
    @Override
    public void edit(School school){
        // Set id == 0, so repository.save will treat it as completely new entity
        school.setId(0);

        SchoolEntity schoolEntity = schoolRepository.findByUniqueSchoolId(school.getUnique_school_id())
                .orElseThrow(() -> new IncorrectDataException("School does not exist"));

        schoolEntity.setIs_active(false);
        if(schoolRepository.save(schoolEntity).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }

        if(schoolRepository.save(schoolMapper.mapToEntity(school)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public int pageCount() {
        return ServiceUtility.getNumberOfPage(schoolRepository.count(), ITEMS_PER_PAGE);
    }

    @Override
    public int pageCountForHistory(Integer uniqueSchoolId) {
        return ServiceUtility.getNumberOfPage(schoolRepository.countByUniqueSchoolId(uniqueSchoolId), ITEMS_PER_PAGE);
    }

    @Override
    public List<School> getAll() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<School> getAll(String page, Sort sort){
        PageRequest pageRequest = null;
        if(sort == null){
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE);
        }else{
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE, sort);

        }
        return schoolRepository.findAll(pageRequest).stream()
                .map(schoolMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<School> getAllHistory(Integer uniqueSchoolId, String page, Sort sort) {
        PageRequest pageRequest = null;
        if(sort == null){
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE);
        }else{
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE, sort);

        }
        return schoolRepository.findAllByUniqueSchoolId(uniqueSchoolId, pageRequest).stream()
                .map(schoolMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public School get(Integer id){
        return schoolRepository.findById(id).map(schoolMapper::mapToDomain).orElseThrow(() -> new IncorrectDataException("School does not exist"));
    }
}
