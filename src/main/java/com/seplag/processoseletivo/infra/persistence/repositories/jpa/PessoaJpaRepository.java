package com.seplag.processoseletivo.infra.persistence.repositories.jpa;

import com.seplag.processoseletivo.infra.persistence.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long> {
}
