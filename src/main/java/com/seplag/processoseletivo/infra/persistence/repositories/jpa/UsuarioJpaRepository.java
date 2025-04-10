package com.seplag.processoseletivo.infra.persistence.repositories.jpa;


import com.seplag.processoseletivo.infra.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UsuarioEntity u WHERE u.email = :email")
    boolean existsByEmail(String email);
}
