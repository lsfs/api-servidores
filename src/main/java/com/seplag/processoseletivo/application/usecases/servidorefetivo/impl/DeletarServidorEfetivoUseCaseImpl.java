package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.DeletarServidorEfetivoUseCase;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarServidorEfetivoUseCaseImpl implements DeletarServidorEfetivoUseCase {

    private final ServidorEfetivoRepository servidorEfetivoRepository;

    public DeletarServidorEfetivoUseCaseImpl(ServidorEfetivoRepository servidorEfetivoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
    }

    @Override
    public MensagemRetorno execute(Long id) {
        servidorEfetivoRepository.deletar(id);
        return new MensagemRetorno("Servidor efetivo deletado com sucesso");
    }
}
