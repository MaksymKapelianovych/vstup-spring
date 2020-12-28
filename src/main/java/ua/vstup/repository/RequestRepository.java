package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.RequestEntity;
import ua.vstup.entity.StateEntity;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    List<RequestEntity> findAllByEntrantId(Integer entrantId);

    List<RequestEntity> findAll();

    List<RequestEntity> findAllByStatementId(Integer id);

    boolean updateStateById(Integer id, StateEntity state);

    Optional<RequestEntity> findByEntrantIdAndFacultyId(Integer entrantId, Integer facultyId);
}
