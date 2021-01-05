package ua.vstup.service;

import ua.vstup.domain.School;

import java.util.List;

public interface SchoolService {
    void add(School school);
    List<School> getAll();
}
