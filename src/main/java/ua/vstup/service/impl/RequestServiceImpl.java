package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
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
import ua.vstup.service.utility.ServiceUtility;
import ua.vstup.utility.ParameterParser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestServiceImpl implements RequestService {
    private static final int ITEMS_PER_PAGE = 9;

    private final RequestRepository requestRepository;
    private final StatementRepository statementRepository;
    private final SubjectRepository subjectRepository;
    private final FacultyRepository facultyRepository;

    private final RequestMapper requestMapper;
    private final EntrantMapper entrantMapper;
    private final FacultyMapper facultyMapper;
    private final SubjectMapper subjectMapper;

    @Override
    public List<Request> getAllByEntrant(Entrant entrant) {
        return requestRepository.findAllByEntrantEntity(entrantMapper.mapToEntity(entrant)).stream()
                .map(requestMapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Request> getAll(String page) {
        PageRequest pageRequest = PageRequest.of(ParameterParser.parsePageNumber(page, 0, pageCount()), ITEMS_PER_PAGE);
        return requestRepository.findAll(pageRequest).stream()
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
    public int pageCount() {
        return ServiceUtility.getNumberOfPage(facultyRepository.count(), ITEMS_PER_PAGE);
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
