package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.vstup.domain.Faculty;
import ua.vstup.entity.EntrantEntity;
import ua.vstup.entity.FacultyEntity;
import ua.vstup.entity.RoleEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.FacultyRepository;
import ua.vstup.service.FacultyService;
import ua.vstup.service.mapper.FacultyMapper;
import ua.vstup.service.utility.ServiceUtility;
import ua.vstup.utility.ParameterParser;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacultyServiceImpl implements FacultyService {
    private static final int ITEMS_PER_PAGE = 3;

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    @Override
    public List<Faculty> getAll(String page, Sort sort) {
        PageRequest pageRequest = null;
        if(sort == null){
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE);
        }else{
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE, sort);

        }
        return facultyRepository.findAll(pageRequest).stream()
                .map(facultyMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Faculty> getAllActive(String page, Sort sort) {
        PageRequest pageRequest = null;
        if(sort == null){
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCountActive()), ITEMS_PER_PAGE);
        }else{
            pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCountActive()), ITEMS_PER_PAGE, sort);

        }

        return facultyRepository.findAllByActive(true, pageRequest).stream()
                .map(facultyMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Faculty get(Integer id) {
        FacultyEntity facultyEntity = facultyRepository.findById(id)
                .orElseThrow(() -> new IncorrectDataException("Faculty not found"));
        return facultyMapper.mapToDomain(facultyEntity);
    }

    @Override
    public int pageCount() {
        return ServiceUtility.getNumberOfPage(facultyRepository.count(), ITEMS_PER_PAGE);
    }

    @Override
    public int pageCountActive() {
        FacultyEntity probe = new FacultyEntity();
        probe.setActive(true);
        return ServiceUtility.getNumberOfPage(facultyRepository.count(Example.of(probe)), ITEMS_PER_PAGE);    }

    @Override
    public void add(Faculty faculty) {
        faculty.setActive(true);
        if(facultyRepository.save(facultyMapper.mapToEntity(faculty)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public void delete(Integer id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public void edit(Faculty faculty) {
        if(facultyRepository.save(facultyMapper.mapToEntity(faculty)).getId() == 0){
            throw new IncorrectDataException("");
        }
    }
}
