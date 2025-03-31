package com.seplag.processoseletivo.infra.config;


import com.seplag.processoseletivo.domain.repositories.*;
import com.seplag.processoseletivo.infra.persistence.repositories.*;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.*;
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

    @Bean
    public EnderecoRepository enderecoRepository(EnderecoJpaRepository enderecoJpaRepository,
                                                 CidadeRepository cidadeRepository) {
        return new EnderecoRepositoryImpl(enderecoJpaRepository, cidadeRepository);
    }

    @Bean
    public ServidorTemporarioRepository servidorTemporarioRepository(ServidorTemporarioJpaRepository servidorTemporarioJpaRepository, PessoaJpaRepository pessoaRepository) {
        return new ServidorTemporarioRepositoryImpl(servidorTemporarioJpaRepository, pessoaRepository);
    }

    @Bean
    public ServidorEfetivoRepository servidorEfetivoRepository(ServidorEfetivoJpaRepository servidorEfetivoJpaRepository, PessoaJpaRepository pessoaRepository) {
        return new ServidorEfetivoRepositoryImpl(servidorEfetivoJpaRepository, pessoaRepository);
    }

    @Bean
    public PessoaRepository pessoaRepository(PessoaJpaRepository pessoaJpaRepository) {
        return new PessoaRepositoryImpl(pessoaJpaRepository);
    }
}
