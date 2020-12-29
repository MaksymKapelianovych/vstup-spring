package ua.vstup.service.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.vstup.domain.Faculty;
import ua.vstup.entity.FacultyEntity;
import ua.vstup.service.mapper.FacultyMapper;
import ua.vstup.service.mapper.RequirementMapper;

@Component
public class FacultyMapperImpl implements FacultyMapper {
    @Autowired
    private RequirementMapper requirementMapper;

    @Override
    public Faculty mapToDomain(FacultyEntity entity) {
        if(entity == null){
            return null;
        }
        Faculty faculty = new Faculty();
        faculty.setId(entity.getId());
        faculty.setName_en(entity.getName_en());
        faculty.setName_ua(entity.getName_ua());
        faculty.setMaxBudgetPlace(entity.getMaxBudgetPlace());
        faculty.setMaxPlace(entity.getMaxPlace());
        faculty.setRequirement(requirementMapper.mapToDomain(entity.getRequirementEntity()));
        faculty.setActive(entity.getActive());
        return faculty;
    }

    @Override
    public FacultyEntity mapToEntity(Faculty domain) {
        if(domain == null){
            return null;
        }
        FacultyEntity entity = new FacultyEntity();
        entity.setId(domain.getId());
        entity.setName_en(domain.getName_en());
        entity.setName_ua(domain.getName_ua());
        entity.setMaxBudgetPlace(domain.getMaxBudgetPlace());
        entity.setMaxPlace(domain.getMaxPlace());
        entity.setRequirementEntity(requirementMapper.mapToEntity(domain.getRequirement()));
        entity.setActive(domain.getActive());
        return entity;
    }
}
