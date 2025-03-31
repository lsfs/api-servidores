package com.seplag.processoseletivo.application.usecases.servidortemporario.impl;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.usecases.servidortemporario.BuscarServidorTempPorIdUseCase;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarServidorTempPorIdUseCaseImpl implements BuscarServidorTempPorIdUseCase {

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    public BuscarServidorTempPorIdUseCaseImpl(ServidorTemporarioRepository servidorTemporarioRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
    }

    @Override
    public ServidorTempResponseDto execute(Long id) {

        ServidorTemporario servidor = servidorTemporarioRepository.buscarPorId(id);
        return ServidorTempResponseDto.of(servidor);
    }
}
