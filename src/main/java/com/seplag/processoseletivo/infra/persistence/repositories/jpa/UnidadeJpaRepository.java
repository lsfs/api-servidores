package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.UnidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeJpaRepository extends JpaRepository<UnidadeEntity, Long> {

    @Query("SELECT DISTINCT u FROM UnidadeEntity u " +
            "JOIN LotacaoEntity l ON l.unidade.unid_id = u.unid_id " +
            "WHERE l.pessoa.pes_id = :pesId")
    List<UnidadeEntity> buscaPorPessoaId(Long pesId);

    boolean existsById(Long id);

}
