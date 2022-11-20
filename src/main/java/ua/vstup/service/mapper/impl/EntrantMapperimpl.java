package ua.vstup.service.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.vstup.domain.Entrant;
import ua.vstup.domain.Role;
import ua.vstup.entity.EntrantEntity;
import ua.vstup.entity.RoleEntity;
import ua.vstup.service.mapper.DocMapper;
import ua.vstup.service.mapper.EntrantMapper;
import ua.vstup.service.mapper.RequirementMapper;
import ua.vstup.service.mapper.SchoolMapper;

@Component
public class EntrantMapperimpl implements EntrantMapper {
    @Autowired
    private RequirementMapper requirementMapper;
    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private DocMapper docMapper;

    @Override
    public Entrant mapToDomain(EntrantEntity entity) {
        if(entity == null){
            return null;
        }
        Entrant entrant = new Entrant();
        entrant.setId(entity.getId());
        entrant.setName(entity.getName());
        entrant.setEmail(entity.getEmail());
        entrant.setPassword(entity.getPassword());
        entrant.setRole(Role.valueOf(entity.getRole().name()));
        entrant.setRequirement(requirementMapper.mapToDomain(entity.getRequirementEntity()));
        entrant.setSchool(schoolMapper.mapToDomain(entity.getSchoolEntity()));
        entrant.setActive(entity.getActive());
        entrant.setCertificate(docMapper.mapToDomain(entity.getCertificationDocEntity()));
        entrant.setPassport(docMapper.mapToDomain(entity.getPassportDocEntity()));
        entrant.setAssessment(docMapper.mapToDomain(entity.getAssessmentDocEntity()));
        entrant.setPhoto(docMapper.mapToDomain(entity.getPhotoDocEntity()));
        return entrant;
    }

    @Override
    public EntrantEntity mapToEntity(Entrant domain) {
        if(domain == null){
            return null;
        }
        EntrantEntity entity = new EntrantEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setRole(RoleEntity.valueOf(domain.getRole().name()));
        entity.setRequirementEntity(requirementMapper.mapToEntity(domain.getRequirement()));
        entity.setSchoolEntity(schoolMapper.mapToEntity(domain.getSchool()));
        entity.setActive(domain.isActive());
        entity.setCertificationDocEntity(docMapper.mapToEntity(domain.getCertificate()));
        entity.setPassportDocEntity(docMapper.mapToEntity(domain.getPassport()));
        entity.setAssessmentDocEntity(docMapper.mapToEntity(domain.getAssessment()));
        entity.setPhotoDocEntity(docMapper.mapToEntity(domain.getPhoto()));
        return entity;
    }
}
