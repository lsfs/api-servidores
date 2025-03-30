package com.seplag.processoseletivo.infra.config;


import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import com.seplag.processoseletivo.infra.persistence.repositories.CidadeRepositoryImpl;
import com.seplag.processoseletivo.infra.persistence.repositories.UnidadeRepositoryImpl;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.CidadeJpaRepository;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.UnidadeJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UnidadeRepository unidadeRepository(UnidadeJpaRepository unidadeJpaRepository) {
        return new UnidadeRepositoryImpl(unidadeJpaRepository);
    }

    @Bean
    public CidadeRepository cidadeRepository(CidadeJpaRepository cidadeJpaRepository) {
        return new CidadeRepositoryImpl(cidadeJpaRepository);
    }

}
