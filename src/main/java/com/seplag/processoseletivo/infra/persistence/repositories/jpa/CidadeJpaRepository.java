package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeJpaRepository extends JpaRepository<CidadeEntity, Long> {
}
