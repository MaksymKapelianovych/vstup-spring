package ua.vstup.service.mapper.impl;

import ua.vstup.domain.Subject;
import ua.vstup.domain.SubjectName;
import ua.vstup.entity.SubjectEntity;
import ua.vstup.entity.SubjectNameEntity;
import ua.vstup.service.mapper.SubjectMapper;

public class SubjectMapperImpl implements SubjectMapper {
    @Override
    public Subject mapToDomain(SubjectEntity entity) {
        if(entity == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(entity.getId());
        subject.setName(SubjectName.valueOf(entity.getSubjectNameEntity().name()));
        subject.setRate(entity.getRate());
        return subject;
    }

    @Override
    public SubjectEntity mapToEntity(Subject domain) {
        if(domain == null){
            return null;
        }
        SubjectEntity entity = new SubjectEntity();
        entity.setId(domain.getId());
        entity.setSubjectNameEntity(SubjectNameEntity.valueOf(domain.getName().name()));
        entity.setRate(domain.getRate());
        return entity;
    }
}
