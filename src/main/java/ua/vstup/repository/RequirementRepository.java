package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.RequirementEntity;

public interface RequirementRepository extends JpaRepository<RequirementEntity, Integer> {
}
