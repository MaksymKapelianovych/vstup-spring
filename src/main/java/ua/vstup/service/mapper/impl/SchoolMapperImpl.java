package ua.vstup.service.mapper.impl;

import org.springframework.stereotype.Component;
import ua.vstup.domain.Region;
import ua.vstup.domain.School;
import ua.vstup.entity.RegionEntity;
import ua.vstup.entity.SchoolEntity;
import ua.vstup.service.mapper.SchoolMapper;

@Component
public class SchoolMapperImpl implements SchoolMapper {
    @Override
    public School mapToDomain(SchoolEntity entity) {
        if(entity == null){
            return null;
        }
        School school = new School();
        school.setId(entity.getId());
        school.setNameEn(entity.getName_en());
        school.setNameUa(entity.getName_ua());
        school.setCityEn(entity.getCity_en());
        school.setCityUa(entity.getCity_ua());
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
        entity.setName_en(domain.getNameEn());
        entity.setName_ua(domain.getNameUa());
        entity.setCity_en(domain.getCityEn());
        entity.setCity_ua(domain.getCityUa());
        entity.setRegionEntity(RegionEntity.valueOf(domain.getRegion().name()));
        return entity;
    }
}
