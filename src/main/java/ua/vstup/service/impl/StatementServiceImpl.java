package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vstup.domain.*;
import ua.vstup.entity.StatementEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.FacultyRepository;
import ua.vstup.repository.RequestRepository;
import ua.vstup.repository.StatementRepository;
import ua.vstup.service.StatementService;
import ua.vstup.service.mapper.FacultyMapper;
import ua.vstup.service.mapper.RequestMapper;
import ua.vstup.service.mapper.StatementMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StatementServiceImpl implements StatementService {
    private final StatementRepository statementRepository;
    private final FacultyRepository facultyRepository;
    private final RequestRepository requestRepository;

    private final StatementMapper statementMapper;
    private final FacultyMapper facultyMapper;
    private final RequestMapper requestMapper;

    @Override
    public void createStatement() {
        if(statementRepository.findByFinalized(false).isPresent()){
            throw new IncorrectDataException("Statement already exists");
        }

        Statement statement = new Statement();
        statement.setId(null);
        statement.setFinalized(false);

        if(statementRepository.save(statementMapper.mapToEntity(statement)).getId() == 0){
            throw new IncorrectDataException("Incorrect data");
        }
    }

    @Override
    public Statement getUnfinalizedStatement() {
        StatementEntity statementEntity = statementRepository.findByFinalized(false)
                .orElse(null);
        return statementMapper.mapToDomain(statementEntity);
    }

    @Override
    public void finalizeStatement() {

        /*
        *
        * 1. Для кожного факультету
        * 2. Взяти заявки по к-ть бюджетних місць
        * 3. Якщо абітурієнт відмічений таким, що пройшов, то його заявки не розглядаємо
        * 4. Якщо заявка з вищим пріоритетом активна, то поточну не розглядаємо
        * 5. Повторюємо поки є бюджетні місця
        *
        * */

        StatementEntity statementEntity = statementRepository.findByFinalized(false)
                .orElse(null);
        if(statementEntity == null){
            throw new IncorrectDataException("Statement don`t exists");
        }
        List<Faculty> facultyList = facultyRepository.findAllByActive(true).stream()
                .map(facultyMapper::mapToDomain)
                .collect(Collectors.toList());

        List<Request> requestList = requestRepository.findAllByStatementEntity(statementEntity).stream()
                .map(requestMapper::mapToDomain)
                .peek(request -> request.setFaculty(facultyList.stream()
                        .filter(faculty -> faculty.equals(request.getFaculty()))
                        .findAny().orElseThrow(() -> new IncorrectDataException(""))))
                .collect(Collectors.toList());

        List<Entrant> passedEntrants = new ArrayList<>();

        while(facultyList.stream().anyMatch(faculty -> faculty.getMaxBudgetPlace() > 0 &&
                requestList.stream().anyMatch(request ->
                        request.getRequestState() == RequestState.ACCEPTED &&
                                request.getFaculty().equals(faculty) &&
                                !passedEntrants.contains(request.getEntrant())))) {
            facultyList.forEach(faculty -> processRequestToBudget(requestList, faculty, passedEntrants));
        }

        while(facultyList.stream().anyMatch(faculty -> faculty.getMaxPlace() > 0 &&
                requestList.stream().anyMatch(request ->
                        request.getRequestState() == RequestState.ACCEPTED &&
                                request.getFaculty().equals(faculty) &&
                                !passedEntrants.contains(request.getEntrant())))) {
            facultyList.forEach(faculty -> processRequestToContract(requestList, faculty, passedEntrants));
        }

        processRequestToNotPass(requestList);

        requestList.forEach(request -> requestRepository.save(requestMapper.mapToEntity(request)));
        statementEntity.setFinalized(true);
        statementRepository.save(statementEntity);
    }

    private void processRequestToNotPass(List<Request> requestList) {
        requestList.stream()
                .filter(request -> request.getRequestState() == RequestState.ACCEPTED)
                .forEach(request -> request.setRequestState(RequestState.NOT_PASS));
    }

    private void processRequestToContract(List<Request> requestList, Faculty faculty, List<Entrant> passedEntrants) {
        requestList.stream()
                .filter(request -> request.getFaculty().equals(faculty) &&
                        !passedEntrants.contains(request.getEntrant()) &&
                        !requestWithHigherPriorityCanPassContract(requestList, request)
                )
                .sorted(Comparator.comparing(Request::getRate))
                .limit(faculty.getMaxPlace())
                .peek(System.out::println)
                .forEach(request -> {
                    request.setRequestState(RequestState.CONTRACT);
                    passedEntrants.add(request.getEntrant());
                    faculty.decreasePlace();
                });
    }

    private void processRequestToBudget(List<Request> requestList, Faculty faculty, List<Entrant> passedEntrants){
        requestList.stream()
                .filter(request -> request.getFaculty().equals(faculty) &&
                            !passedEntrants.contains(request.getEntrant()) &&
                            !requestWithHigherPriorityCanPassBudget(requestList, request)
                )
                .sorted(Comparator.comparing(Request::getRate))
                .limit(faculty.getMaxBudgetPlace())
                .peek(System.out::println)
                .forEach(request -> {
                    request.setRequestState(RequestState.BUDGET);
                    passedEntrants.add(request.getEntrant());
                    faculty.decreaseBudgetPlace();
                });
    }

    private boolean requestWithHigherPriorityCanPassBudget(List<Request> requestList, Request goalRequest){
        return requestList.stream().anyMatch(request -> request != goalRequest &&
                request.getFaculty().getMaxBudgetPlace() > 0 &&
                request.getPriority() < goalRequest.getPriority() &&
                request.getEntrant().equals(goalRequest.getEntrant()) &&
                request.getRequestState() != RequestState.ACTIVE);
    }

    private boolean requestWithHigherPriorityCanPassContract(List<Request> requestList, Request goalRequest){
        return requestList.stream().anyMatch(request -> request != goalRequest &&
                request.getFaculty().getMaxPlace() > 0 &&
                request.getPriority() < goalRequest.getPriority() &&
                request.getEntrant().equals(goalRequest.getEntrant()) &&
                request.getRequestState() != RequestState.ACTIVE);
    }
}
