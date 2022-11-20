package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vstup.entity.DocEntity;

public interface DocRepository extends JpaRepository<DocEntity, Integer> {
}
