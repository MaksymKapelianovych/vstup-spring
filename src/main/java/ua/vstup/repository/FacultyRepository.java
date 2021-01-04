package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.vstup.entity.FacultyEntity;

import java.util.List;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Integer> {
    List<FacultyEntity> findAll();

    List<FacultyEntity> findAllByActive(boolean active);

    @Modifying
    @Query("UPDATE FacultyEntity SET active = :active WHERE id = :id")
    boolean updateActiveById(@Param("id") Integer id, @Param("active") boolean active);
}
