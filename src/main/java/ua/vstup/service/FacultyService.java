package ua.vstup.service;

import ua.vstup.domain.Faculty;
import ua.vstup.domain.Subject;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll();

    void add(Faculty faculty);

    void delete(Integer id);

    List<Faculty> getAllActive();

    Faculty get(Integer id);

    void edit(Faculty faculty);
}
