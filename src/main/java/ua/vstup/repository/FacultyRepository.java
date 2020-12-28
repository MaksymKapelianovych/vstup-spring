package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.FacultyEntity;

import java.util.List;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Integer> {
    List<FacultyEntity> findAll();

    boolean updateActiveById(Integer id, boolean active);

    List<FacultyEntity> findAllByActive(boolean active);
}
