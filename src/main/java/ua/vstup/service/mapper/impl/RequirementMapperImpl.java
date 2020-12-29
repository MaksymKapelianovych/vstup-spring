package ua.vstup.service.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.vstup.domain.Requirement;
import ua.vstup.entity.RequirementEntity;
import ua.vstup.service.mapper.RequirementMapper;
import ua.vstup.service.mapper.SubjectMapper;

public class RequirementMapperImpl implements RequirementMapper {
    @Autowired
    private SubjectMapper subjectMapper;
    @Override
    public Requirement mapToDomain(RequirementEntity entity) {
        if(entity == null){
            return null;
        }
        Requirement requirement = new Requirement();
        requirement.setId(entity.getId());
        requirement.setFirstSubject(subjectMapper.mapToDomain(entity.getFirstSubjectEntity()));
        requirement.setSecondSubject(subjectMapper.mapToDomain(entity.getSecondSubjectEntity()));
        requirement.setThirdSubject(subjectMapper.mapToDomain(entity.getThirdSubjectEntity()));
        requirement.setFourthSubject(subjectMapper.mapToDomain(entity.getFourthSubjectEntity()));
        requirement.setFifthSubject(subjectMapper.mapToDomain(entity.getFifthSubjectEntity()));
        return requirement;
    }

    @Override
    public RequirementEntity mapToEntity(Requirement domain) {
        if(domain == null){
            return null;
        }
        RequirementEntity entity = new RequirementEntity();
        entity.setId(domain.getId());
        entity.setFirstSubjectEntity(subjectMapper.mapToEntity(domain.getFirstSubject()));
        entity.setSecondSubjectEntity(subjectMapper.mapToEntity(domain.getSecondSubject()));
        entity.setThirdSubjectEntity(subjectMapper.mapToEntity(domain.getThirdSubject()));
        entity.setFourthSubjectEntity(subjectMapper.mapToEntity(domain.getFourthSubject()));
        entity.setFifthSubjectEntity(subjectMapper.mapToEntity(domain.getFifthSubject()));
        return entity;
    }
}
