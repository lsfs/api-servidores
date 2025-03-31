package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeRequestDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.application.usecases.unidade.AtualizarUnidadeUseCase;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AtualizarUnidadeUseCaseImpl implements AtualizarUnidadeUseCase {

    private final UnidadeRepository unidadeRepository;
    private final EnderecoRepository enderecoRepository;

    public AtualizarUnidadeUseCaseImpl(UnidadeRepository unidadeRepository, EnderecoRepository enderecoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.enderecoRepository = enderecoRepository;
    }


    @Override
    public UnidadeResponseDto execute(Long unid_id, UnidadeRequestDto unidadeRequestDto) {
        Unidade unidade = buscaUnidade(unid_id);
        unidade.setUni_sigla(unidadeRequestDto.unid_sigla());
        unidade.setUnid_nome(unidadeRequestDto.unid_nome());

        Set<Endereco> enderecos = buscarEnderecos(unidadeRequestDto.enderecos());
        unidade.setEnderecos(enderecos);
        Unidade unidadeAtualizada = unidadeRepository.atualizar(unidade);

        return UnidadeResponseDto.of(unidadeAtualizada);

    }


    private Unidade buscaUnidade(Long id) {
        return unidadeRepository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada"));
    }

    private Set<Endereco> buscarEnderecos(Set<Long> enderecos) {

        return enderecos.stream()
                .map(enderecoRepository::buscarPorId)
                .collect(Collectors.toSet());
    }



}
