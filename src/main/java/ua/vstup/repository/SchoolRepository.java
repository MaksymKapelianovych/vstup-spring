package ua.vstup.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.SchoolEntity;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {
    List<SchoolEntity> findAll();
    List<SchoolEntity> findAllByUniqueSchoolId(Integer uniqueSchoolId, Pageable page);
    Optional<SchoolEntity> findByUniqueSchoolId(Integer uniqueSchoolId);

    long countByUniqueSchoolId(Integer uniqueSchoolId);
}
