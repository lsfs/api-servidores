package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.FotoPessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoPessoaJpaRepository extends JpaRepository<FotoPessoaEntity, Long> {

    @Query(value = "SELECT * FROM foto_pessoa f WHERE f.pes_id = :id ORDER BY f.fp_data DESC LIMIT 1", nativeQuery = true)
    Optional<FotoPessoaEntity> findByPessoaId(Long id);
}
