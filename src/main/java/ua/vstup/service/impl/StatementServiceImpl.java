package ua.vstup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.vstup.domain.Faculty;
import ua.vstup.domain.Request;
import ua.vstup.domain.RequestState;
import ua.vstup.domain.Statement;
import ua.vstup.entity.RequestStateEntity;
import ua.vstup.entity.StatementEntity;
import ua.vstup.exception.IncorrectDataException;
import ua.vstup.repository.FacultyRepository;
import ua.vstup.repository.RequestRepository;
import ua.vstup.repository.StatementRepository;
import ua.vstup.service.StatementService;
import ua.vstup.service.mapper.FacultyMapper;
import ua.vstup.service.mapper.RequestMapper;
import ua.vstup.service.mapper.StatementMapper;

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
                .collect(Collectors.toList());

        //TODO add priority
        for(int i = 1; i < 10; i++){
            int finalI = i;
            facultyList.forEach(faculty -> processByPriority(requestList, finalI, faculty));
        }

        requestList.forEach(request -> requestRepository.save(requestMapper.mapToEntity(request)));

    }

    private void processByPriority(List<Request> requestList, Integer priority, Faculty faculty){
        requestList.stream()
                .filter(request -> request.getPriority().equals(priority) && request.getFaculty().equals(faculty))
                .sorted(Comparator.comparing(Request::getRate))
                .forEach(request -> {
                    if(!request.getEntrant().isPassed()) {
                        if (faculty.getMaxBudgetPlace() > 0) {
                            request.setRequestState(RequestState.BUDGET);
                            request.getEntrant().setPassed(true);
                            faculty.decreaseBudgetPlace();
                        } else if (faculty.getMaxPlace() > 0) {
                            request.setRequestState(RequestState.CONTRACT);
                            request.getEntrant().setPassed(true);
                            faculty.decreasePlace();
                        } else {
                            request.setRequestState(RequestState.NOT_PASS);
                        }
                    }else{
                        request.setRequestState(RequestState.NOT_PASS);
                    }
                });
    }
}
