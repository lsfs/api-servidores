package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.usecases.unidade.DeletarUnidadeUseCase;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeletarUnidadeUseCaseImpl implements DeletarUnidadeUseCase {

    private final UnidadeRepository unidadeRepository;

    public DeletarUnidadeUseCaseImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public MensagemRetorno execute(Long idUnidade) {

        unidadeRepository.deletar(idUnidade);
        return new MensagemRetorno("Unidade deletada com sucesso");

    }
}
