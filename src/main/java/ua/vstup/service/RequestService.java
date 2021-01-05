package ua.vstup.service;

import ua.vstup.domain.*;

import java.util.List;

public interface RequestService {
    List<Request> getAllByEntrant(Entrant entrant);

    List<Request> getAll();
    List<Request> getAllByStatementId(Integer id);

    void updateStateById(Integer id, RequestState requestState);

    void add(Request entrantRequest);

    List<Subject> jointSubjects(Requirement facultyInfo, Requirement entrantInfo);
}
