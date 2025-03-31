package com.seplag.processoseletivo.application.usecases.servidortemporario.impl;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.servidortemporario.DeletarServidorTemporarioUseCase;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletarServidorTemporarioUseCaseImpl implements DeletarServidorTemporarioUseCase {

    private final ServidorTemporarioRepository servidorTemporarioRepository;

    public DeletarServidorTemporarioUseCaseImpl(ServidorTemporarioRepository servidorTemporarioRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
    }

    @Override
    public MensagemRetorno execute(Long id) {
        servidorTemporarioRepository.deletar(id);
        return new MensagemRetorno("Servidor temporário deletado com sucesso");
    }
}
