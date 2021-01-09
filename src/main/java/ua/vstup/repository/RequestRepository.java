package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.vstup.entity.*;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    List<RequestEntity> findAllByEntrantEntity(EntrantEntity entrantEntity);

    List<RequestEntity> findAllByStatementEntity(StatementEntity statementEntity);

    Optional<RequestEntity> findByEntrantEntityAndFacultyEntity(EntrantEntity entrantEntity, FacultyEntity facultyEntity);
}
