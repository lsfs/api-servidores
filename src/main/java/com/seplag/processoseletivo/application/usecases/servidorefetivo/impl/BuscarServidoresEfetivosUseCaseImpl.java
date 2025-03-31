package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.BuscarServidoresEfetivosUseCase;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;

@Service
public class BuscarServidoresEfetivosUseCaseImpl implements BuscarServidoresEfetivosUseCase {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    public BuscarServidoresEfetivosUseCaseImpl(ServidorEfetivoRepository servidorEfetivoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
    }

    @Override
    public RespostaPaginada<ServidorEfetivoResponseDto> execute(int pagina, int tamanho) {

        RespostaPaginada<ServidorEfetivo> respostaPaginada = servidorEfetivoRepository.buscaServidores(pagina, tamanho);
        var servidoresEfetivos = respostaPaginada.getContent()
                .stream()
                .map(ServidorEfetivoResponseDto::of)
                .toList();

        return new RespostaPaginada<>(servidoresEfetivos, respostaPaginada.getPageNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }
}
