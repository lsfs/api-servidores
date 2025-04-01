package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.LotacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LotacaoJpaRepository extends JpaRepository<LotacaoEntity, Long> {


    @Query("SELECT l FROM LotacaoEntity l " +
            "JOIN ServidorEfetivoEntity se ON l.pessoa.pes_id = se.pessoa.pes_id " +
            "WHERE l.unidade.unid_id = :unidadeId")
    Page<LotacaoEntity> buscaLotacaoesComServidoresEfetivosPorUnidadeId(@Param("unidadeId") Long unidadeId, Pageable pageable);
}
