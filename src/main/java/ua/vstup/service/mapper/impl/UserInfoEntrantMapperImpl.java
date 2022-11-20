package ua.vstup.service.mapper.impl;

import org.springframework.stereotype.Component;
import ua.vstup.domain.Entrant;
import ua.vstup.domain.UserInfo;
import ua.vstup.service.mapper.UserInfoEntrantMapper;

@Component
public class UserInfoEntrantMapperImpl implements UserInfoEntrantMapper {

    @Override
    public Entrant mapToDomain(UserInfo entity) {
        if(entity == null){
            return null;
        }
        Entrant entrant = new Entrant();
        entrant.setId(entrant.getId());
        entrant.setName(entity.getName());
        entrant.setEmail(entity.getEmail());
        entrant.setPassword(entity.getPassword());
        entrant.setRequirement(entity.getRequirement());
        return entrant;
    }

    @Override
    public UserInfo mapToEntity(Entrant domain) {
        return null;
    }
}
