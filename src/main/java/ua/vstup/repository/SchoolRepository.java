package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.SchoolEntity;

import java.util.List;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {
    List<SchoolEntity> findAll();
}
