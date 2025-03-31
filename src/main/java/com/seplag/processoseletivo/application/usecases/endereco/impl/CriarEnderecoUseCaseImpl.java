package com.seplag.processoseletivo.application.usecases.endereco.impl;

import com.seplag.processoseletivo.application.dto.cidade.CidadeRequestDto;
import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesPorIdUseCase;
import com.seplag.processoseletivo.application.usecases.cidade.CriarCidadeUseCase;
import com.seplag.processoseletivo.application.usecases.endereco.CriarEnderecoUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarEnderecoUseCaseImpl implements CriarEnderecoUseCase {

    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;

    public CriarEnderecoUseCaseImpl(EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public EnderecoResponseDto execute(EnderecoRequestDto dto) {

        Endereco endereco = new Endereco(
                null,
                dto.end_tipo_logradouro(),
                dto.end_logradouro(),
                dto.end_numero(),
                dto.end_bairro(),
                buscaCidade(dto.cidade_id())
        );

        Endereco enderecoCriado = enderecoRepository.criar(endereco);
        return EnderecoResponseDto.of(enderecoCriado);

    }


    private Cidade buscaCidade(Long id) {
       return cidadeRepository.buscarPorId(id);
    }


}
