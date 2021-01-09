package ua.vstup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ua.vstup.domain.*;
import ua.vstup.entity.*;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.FacultyRepository;
import ua.vstup.repository.RequestRepository;
import ua.vstup.repository.StatementRepository;
import ua.vstup.repository.SubjectRepository;
import ua.vstup.service.RequestService;
import ua.vstup.service.mapper.EntrantMapper;
import ua.vstup.service.mapper.FacultyMapper;
import ua.vstup.service.mapper.RequestMapper;
import ua.vstup.service.mapper.SubjectMapper;

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
    private SubjectRepository subjectRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private EntrantMapper entrantMapper;
    @Autowired
    private FacultyMapper facultyMapper;
    @Autowired
    private SubjectMapper subjectMapper;

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
    public void add(Entrant entrant, Integer facultyId, Integer firstSubject, Integer secondSubject, Integer thirdSubject) {

        EntrantEntity entrantEntity = entrantMapper.mapToEntity(entrant);
        RequestEntity probe = new RequestEntity();
        probe.setEntrantEntity(entrantEntity);
        long count = requestRepository.count(Example.of(probe));

        if(count > 8){
            throw new IncorrectDataException("Max count of request reached");
        }

        FacultyEntity facultyEntity = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new IncorrectDataException("Faculty doesn't exist"));
        probe.setFacultyEntity(facultyEntity);

        if(requestRepository.count(Example.of(probe)) > 0){
            throw new IncorrectDataException("Request already exists");
        }

        Optional<RequestEntity> optionalRequestEntity = requestRepository.findByEntrantEntityAndFacultyEntity(
                entrantEntity, facultyEntity);

        if(optionalRequestEntity.isPresent()){
            throw new IncorrectDataException("Request to this faculty already exist");
        }


        SubjectEntity subjectEntity1 = subjectRepository.findById(firstSubject)
                .orElseThrow(() -> new IncorrectDataException("Subject doesn't exist"));
        SubjectEntity subjectEntity2 = subjectRepository.findById(secondSubject)
                .orElseThrow(() -> new IncorrectDataException("Subject doesn't exist"));
        SubjectEntity subjectEntity3 = subjectRepository.findById(thirdSubject)
                .orElseThrow(() -> new IncorrectDataException("Subject doesn't exist"));

        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setEntrantEntity(entrantEntity);
        requestEntity.setFacultyEntity(facultyEntity);
        requestEntity.setFirstSubjectEntity(subjectEntity1);
        requestEntity.setSecondSubjectEntity(subjectEntity2);
        requestEntity.setThirdSubjectEntity(subjectEntity3);
        requestEntity.setRequestStateEntity(RequestStateEntity.ACTIVE);
        requestEntity.setPriority((int) (count + 1));

        if(requestRepository.save(requestEntity).getId() == 0){
            throw new IncorrectDataException("");
        }
    }

    @Override
    public void checkIfRequestExists(Entrant entrant, Faculty faculty) {
        RequestEntity probe = new RequestEntity();
        probe.setEntrantEntity(entrantMapper.mapToEntity(entrant));
        probe.setFacultyEntity(facultyMapper.mapToEntity(faculty));
        if(requestRepository.count(Example.of(probe)) > 0){
            throw new IncorrectDataException("Request already exists");
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
