package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
}
