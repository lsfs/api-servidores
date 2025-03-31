package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.infra.persistence.entity.ServidorEfetivoEntity;
import com.seplag.processoseletivo.infra.persistence.entity.ServidorTemporarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServidorEfetivoJpaRepository extends JpaRepository<ServidorEfetivoEntity, Long> {

    @Query("SELECT s FROM ServidorEfetivoEntity s JOIN FETCH s.pessoa WHERE s.id = :id")
    Optional<ServidorEfetivoEntity> buscarPorId(Long id);

}
