package ua.vstup.service;

import ua.vstup.domain.*;

import java.util.List;

public interface RequestService {
    List<Request> getAllByEntrant(Entrant entrant);

    List<Request> getAll(String page);
    List<Request> getAllByStatementId(Integer id);

    int pageCount();

    void updateStateById(Integer id, RequestState requestState);

    List<Subject> jointSubjects(Requirement facultyInfo, Requirement entrantInfo);

    void add(Entrant entrant, Integer facultyId, Integer firstSubject, Integer secondSubject, Integer thirdSubject);

    void checkIfRequestExists(Entrant entrant, Faculty faculty);
}
