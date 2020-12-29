package ua.vstup.service.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.vstup.domain.Request;
import ua.vstup.domain.State;
import ua.vstup.entity.RequestEntity;
import ua.vstup.entity.RequestStateEntity;
import ua.vstup.service.mapper.*;

@Component
public class RequestMapperImpl implements RequestMapper {
    @Autowired
    private EntrantMapper entrantMapper;
    @Autowired
    private FacultyMapper facultyMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private StatementMapper statementMapper;

    @Override
    public Request mapToDomain(RequestEntity entity) {
        if(entity == null){
            return null;
        }
        Request request = new Request();
        request.setId(entity.getId());
        request.setEntrant(entrantMapper.mapToDomain(entity.getEntrantEntity()));
        request.setFaculty(facultyMapper.mapToDomain(entity.getFacultyEntity()));
        request.setFirstSubject(subjectMapper.mapToDomain(entity.getFirstSubjectEntity()));
        request.setSecondSubject(subjectMapper.mapToDomain(entity.getSecondSubjectEntity()));
        request.setThirdSubject(subjectMapper.mapToDomain(entity.getThirdSubjectEntity()));
        request.setStatement(statementMapper.mapToDomain(entity.getStatementEntity()));
        request.setPriority(entity.getPriority());
        request.setState(State.valueOf(entity.getRequestStateEntity().name()));
        return request;
    }

    @Override
    public RequestEntity mapToEntity(Request domain) {
        if(domain == null){
            return null;
        }
        RequestEntity entity = new RequestEntity();
        entity.setId(domain.getId());
        entity.setEntrantEntity(entrantMapper.mapToEntity(domain.getEntrant()));
        entity.setFacultyEntity(facultyMapper.mapToEntity(domain.getFaculty()));
        entity.setFirstSubjectEntity(subjectMapper.mapToEntity(domain.getFirstSubject()));
        entity.setSecondSubjectEntity(subjectMapper.mapToEntity(domain.getSecondSubject()));
        entity.setThirdSubjectEntity(subjectMapper.mapToEntity(domain.getThirdSubject()));
        entity.setStatementEntity(statementMapper.mapToEntity(domain.getStatement()));
        entity.setPriority(domain.getPriority());
        entity.setRequestStateEntity(RequestStateEntity.valueOf(domain.getState().name()));
        return entity;
    }
}
