package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM cidade WHERE cid_nome = :nome AND cid_uf = :uf")
    Optional<CidadeEntity> findByCidNomeAndCidUf(@Param("nome") String nome, @Param("uf") String uf);
}
