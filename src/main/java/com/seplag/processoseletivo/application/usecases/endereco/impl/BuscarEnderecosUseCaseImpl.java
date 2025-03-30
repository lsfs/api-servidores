package com.seplag.processoseletivo.application.usecases.endereco.impl;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.usecases.endereco.BuscarEnderecosUseCase;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;

@Service
public class BuscarEnderecosUseCaseImpl implements BuscarEnderecosUseCase {

    private final EnderecoRepository enderecoRepository;

    public BuscarEnderecosUseCaseImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }


    @Override
    public RespostaPaginada<EnderecoResponseDto> execute(int pagina, int tamanho) {

        RespostaPaginada<Endereco> respostaPaginada = enderecoRepository.buscar(pagina, tamanho);

        var enderecos = respostaPaginada.getContent()
                .stream()
                .map(EnderecoResponseDto::of)
                .toList();

        return new RespostaPaginada<>(enderecos, respostaPaginada.getPageNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());
    }
}
