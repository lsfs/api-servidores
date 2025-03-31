package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.ServidorTemporarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServidorTemporarioJpaRepository extends JpaRepository<ServidorTemporarioEntity, Long> {

    @Query("SELECT s FROM ServidorTemporarioEntity s JOIN FETCH s.pessoa WHERE s.id = :id")
    Optional<ServidorTemporarioEntity> buscarPorId(Long id);

}
