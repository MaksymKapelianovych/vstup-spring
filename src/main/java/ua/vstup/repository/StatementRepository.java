package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.StatementEntity;

import java.util.Optional;

public interface StatementRepository extends JpaRepository<StatementEntity, Integer> {
    Optional<StatementEntity> findByFinalized(boolean finalized);
}
