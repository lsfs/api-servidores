package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.LotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotacaoJpaRepository extends JpaRepository<LotacaoEntity, Long> {
}
