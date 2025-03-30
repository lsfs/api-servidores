package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.unidade.CriaUnidadeUseCase;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CriarUnidadeUseCaseImpl implements CriaUnidadeUseCase {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoRepository enderecoRepository;

    public CriarUnidadeUseCaseImpl(UnidadeRepository unidadeRepository, EnderecoRepository enderecoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public UnidadeResponseDto execute(UnidadeRequestDto unidadeRequestDto) {

        Unidade unidade = new Unidade();
        unidade.setUnid_nome(unidadeRequestDto.unid_nome());
        unidade.setUni_sigla(unidadeRequestDto.unid_sigla());

        Set<Endereco> enderecosSet = unidadeRequestDto.enderecos()
                .stream()
                .map(this::buscarEndereco)
                .collect(Collectors.toSet());

        unidade.setEnderecos(enderecosSet);

        Unidade unidadeCriada = unidadeRepository.criar(unidade);
        return UnidadeResponseDto.of(unidadeCriada);
    }

    private Endereco buscarEndereco(Long id) {
        return enderecoRepository.buscarPorId(id);
    }


}
