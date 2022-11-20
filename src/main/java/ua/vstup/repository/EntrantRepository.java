package ua.vstup.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ua.vstup.entity.EntrantEntity;
import ua.vstup.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface EntrantRepository extends JpaRepository<EntrantEntity, Integer> {
    Optional<EntrantEntity> findByEmail(String email);


    List<EntrantEntity> findAllByRole(RoleEntity roleEntity, Pageable pageRequest);
    List<EntrantEntity> findAllByRole(RoleEntity roleEntity);
}
