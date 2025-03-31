package com.seplag.processoseletivo.application.usecases.servidortemporario.impl;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.usecases.servidortemporario.BuscarServidoresTemporariosUseCase;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;

@Service
public class BuscarServidoresTemporariosUseCaseImpl implements BuscarServidoresTemporariosUseCase {

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    public BuscarServidoresTemporariosUseCaseImpl(ServidorTemporarioRepository servidorTemporarioRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
    }

    @Override
    public RespostaPaginada<ServidorTempResponseDto> execute(int pagina, int tamanho) {

        RespostaPaginada<ServidorTemporario> respostaPaginada = servidorTemporarioRepository.buscaServidoresTemporarios(pagina, tamanho);
        var servidoresTemporarios = respostaPaginada.getContent()
                .stream()
                .map(ServidorTempResponseDto::of)
                .toList();

        return new RespostaPaginada<>(servidoresTemporarios, respostaPaginada.getPageNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }
}
