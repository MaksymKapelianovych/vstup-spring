package ua.vstup.service.mapper.impl;

import ua.vstup.domain.Region;
import ua.vstup.domain.School;
import ua.vstup.entity.RegionEntity;
import ua.vstup.entity.SchoolEntity;
import ua.vstup.service.mapper.SchoolMapper;

public class SchoolMapperImpl implements SchoolMapper {
    @Override
    public School mapToDomain(SchoolEntity entity) {
        if(entity == null){
            return null;
        }
        School school = new School();
        school.setId(entity.getId());
        school.setName_en(entity.getName_en());
        school.setName_ua(entity.getName_ua());
        school.setCity_en(entity.getCity_en());
        school.setCity_ua(entity.getCity_ua());
        school.setRegion(Region.valueOf(entity.getRegionEntity().name()));
        return school;
    }

    @Override
    public SchoolEntity mapToEntity(School domain) {
        if(domain == null){
            return null;
        }
        SchoolEntity entity = new SchoolEntity();
        entity.setId(domain.getId());
        entity.setName_en(domain.getName_en());
        entity.setName_ua(domain.getName_ua());
        entity.setCity_en(domain.getCity_en());
        entity.setCity_ua(domain.getCity_ua());
        entity.setRegionEntity(RegionEntity.valueOf(domain.getRegion().name()));
        return entity;
    }
}
