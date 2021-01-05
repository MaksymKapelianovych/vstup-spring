package ua.vstup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vstup.domain.School;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.SchoolRepository;
import ua.vstup.service.SchoolService;
import ua.vstup.service.mapper.SchoolMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public void add(School school) {
        if(schoolRepository.save(schoolMapper.mapToEntity(school)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public List<School> getAll() {
        return schoolRepository.findAll().stream()
                .map(schoolMapper::mapToDomain)
                .collect(Collectors.toList());
    }
}
