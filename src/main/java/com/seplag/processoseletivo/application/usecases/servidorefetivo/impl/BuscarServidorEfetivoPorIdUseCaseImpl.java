package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.BuscarServidorEfetivoPorIdUseCase;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarServidorEfetivoPorIdUseCaseImpl implements BuscarServidorEfetivoPorIdUseCase {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    public BuscarServidorEfetivoPorIdUseCaseImpl(ServidorEfetivoRepository servidorEfetivoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
    }

    @Override
    public ServidorEfetivoResponseDto execute(Long id) {
        ServidorEfetivo servidorEfetivo = servidorEfetivoRepository.buscarPorId(id);
        return ServidorEfetivoResponseDto.of(servidorEfetivo);
    }
}
