package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vstup.domain.Faculty;
import ua.vstup.entity.FacultyEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.FacultyRepository;
import ua.vstup.service.FacultyService;
import ua.vstup.service.mapper.FacultyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll().stream()
                .map(facultyMapper::mapToDomain)
                .collect(Collectors.toList());
    }

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
    public List<Faculty> getAllActive() {
        return facultyRepository.findAllByActive(true).stream()
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
    public void edit(Faculty faculty) {
        if(facultyRepository.save(facultyMapper.mapToEntity(faculty)).getId() == 0){
            throw new IncorrectDataException("");
        }
    }
}
