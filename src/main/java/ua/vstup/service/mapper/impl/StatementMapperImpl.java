package ua.vstup.service.mapper.impl;

import ua.vstup.domain.Statement;
import ua.vstup.entity.StatementEntity;
import ua.vstup.service.mapper.StatementMapper;

public class StatementMapperImpl implements StatementMapper {
    @Override
    public Statement mapToDomain(StatementEntity entity) {
        if(entity == null){
            return null;
        }
        Statement statement = new Statement();
        statement.setId(entity.getId());
        statement.setFinalized(entity.getFinalized());
        return statement;
    }

    @Override
    public StatementEntity mapToEntity(Statement domain) {
        if(domain == null){
            return null;
        }
        StatementEntity entity = new StatementEntity();
        entity.setId(domain.getId());
        entity.setFinalized(domain.getFinalized());
        return entity;
    }
}
