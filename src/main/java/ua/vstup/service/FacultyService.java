package ua.vstup.service;

import org.springframework.data.domain.Sort;
import ua.vstup.domain.Faculty;
import ua.vstup.domain.Subject;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll(String page, Sort sort);
    List<Faculty> getAllActive(String page, Sort sort);
    Faculty get(Integer id);

    void add(Faculty faculty);

    void delete(Integer id);

    int pageCount();
    int pageCountActive();

    void edit(Faculty faculty);
}
