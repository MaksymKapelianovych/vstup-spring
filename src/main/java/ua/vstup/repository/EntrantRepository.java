package ua.vstup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.vstup.entity.EntrantEntity;
import ua.vstup.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface EntrantRepository extends JpaRepository<EntrantEntity, Integer> {
    Optional<EntrantEntity> findByEmail(String email);

    List<EntrantEntity> findAllByRole(RoleEntity roleEntity);
}
