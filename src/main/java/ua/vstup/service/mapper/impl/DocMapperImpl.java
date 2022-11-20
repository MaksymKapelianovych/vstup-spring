package ua.vstup.service.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.vstup.domain.Doc;
import ua.vstup.domain.DocType;
import ua.vstup.entity.DocEntity;
import ua.vstup.entity.DocTypeEntity;
import ua.vstup.service.mapper.DocMapper;
import ua.vstup.service.mapper.EntrantMapper;
import ua.vstup.service.mapper.RequirementMapper;

@Component
public class DocMapperImpl implements DocMapper {
    @Override
    public Doc mapToDomain(DocEntity entity) {
        if(entity == null){
            return null;
        }

        Doc domain = new Doc();
        domain.setId(entity.getId());
        domain.setType(DocType.valueOf(entity.getType().name()));
        domain.setPath(entity.getPath());
        return domain;
    }

    @Override
    public DocEntity mapToEntity(Doc domain) {
        if(domain == null){
            return null;
        }

        DocEntity entity = new DocEntity();
        entity.setId(domain.getId());
        entity.setType(DocTypeEntity.valueOf(domain.getType().name()));
        entity.setPath(domain.getPath());

        return entity;
    }
}
