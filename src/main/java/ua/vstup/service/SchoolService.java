package ua.vstup.service;

import org.springframework.data.domain.Sort;
import ua.vstup.domain.School;

import java.util.List;

public interface SchoolService {
    void add(School school);
    void edit(School school);
    int pageCount();
    int pageCountForHistory(Integer uniqueSchoolId);
    List<School> getAll();
    List<School> getAll(String page, Sort sort);
    List<School> getAllHistory(Integer id, String page, Sort sort);
    School get(Integer id);

}
