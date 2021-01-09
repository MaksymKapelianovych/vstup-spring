package ua.vstup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vstup.domain.*;
import ua.vstup.entity.RequestEntity;
import ua.vstup.entity.RequestStateEntity;
import ua.vstup.entity.StatementEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.RequestRepository;
import ua.vstup.repository.StatementRepository;
import ua.vstup.service.RequestService;
import ua.vstup.service.mapper.EntrantMapper;
import ua.vstup.service.mapper.FacultyMapper;
import ua.vstup.service.mapper.RequestMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private StatementRepository statementRepository;
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private EntrantMapper entrantMapper;
    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public List<Request> getAllByEntrant(Entrant entrant) {
        return requestRepository.findAllByEntrantEntity(entrantMapper.mapToEntity(entrant)).stream()
                .map(requestMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll().stream()
                .map(requestMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Request> getAllByStatementId(Integer id) {
        StatementEntity statementEntity = statementRepository.findById(id).orElse(null);
        return requestRepository.findAllByStatementEntity(statementEntity).stream()
                .map(requestMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStateById(Integer id, RequestState requestState) {
        RequestEntity requestEntity = requestRepository.findById(id)
                .orElseThrow(() -> new IncorrectDataException("Request doesn't exist"));
        requestEntity.setRequestStateEntity(RequestStateEntity.valueOf(requestState.name()));
        if(requestRepository.save(requestEntity).getId() == 0){
            throw new IncorrectDataException("");
        }
    }

    @Override
    public void add(Request entrantRequest) {
        Optional<RequestEntity> requestEntity = requestRepository.findByEntrantEntityAndFacultyEntity(
                entrantMapper.mapToEntity(entrantRequest.getEntrant()),
                facultyMapper.mapToEntity(entrantRequest.getFaculty()));

        if(requestEntity.isPresent()){
            throw new IncorrectDataException("Request to this faculty already exist");
        }

        if(requestRepository.save(requestMapper.mapToEntity(entrantRequest)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public List<Subject> jointSubjects(Requirement facultyInfo, Requirement entrantInfo) {
        List<Subject> intersectedEntrantSubjects = entrantInfo.getSubjectList().stream()
                .filter(entrantSubject -> {
                            Subject subject = facultyInfo.getSubjectBySubjectName(entrantSubject.getName());
                            return subject != null && entrantSubject.getRate() >= subject.getRate();
                        }
                )
                .collect(Collectors.toList());
        if(intersectedEntrantSubjects.size() < 3){
            throw new IncorrectDataException("Subjects not equal");
        }
        return intersectedEntrantSubjects;
    }
}
