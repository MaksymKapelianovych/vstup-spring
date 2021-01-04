package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.vstup.entity.RequestEntity;
import ua.vstup.entity.RequestStateEntity;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    List<RequestEntity> findAllByEntrantId(Integer entrantId);

    List<RequestEntity> findAll();

    List<RequestEntity> findAllByStatementId(Integer id);

    Optional<RequestEntity> findByEntrantIdAndFacultyId(Integer entrantId, Integer facultyId);

    @Modifying
    @Query("UPDATE RequestEntity SET requestStateEntity = :state WHERE id = :id")
    boolean updateStateById(@Param("id") Integer id, @Param("state") RequestStateEntity state);
}
