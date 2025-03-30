package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.unidade.BuscarUnidadesUseCase;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;

@Service
public class BuscarUnidadesUseCaseImpl implements BuscarUnidadesUseCase {

    private final UnidadeRepository unidadeRepository;

    public BuscarUnidadesUseCaseImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }


    @Override
    public RespostaPaginada<UnidadeResponseDto> execute(int pagina, int tamanho) {

        RespostaPaginada<Unidade> respostaPaginada = unidadeRepository.buscaUnidades(pagina, tamanho);
        var unidades = respostaPaginada.getContent()
                .stream()
                .map(UnidadeResponseDto::of)
                .toList();

        return new RespostaPaginada<>(unidades, respostaPaginada.getPageNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());
    }
}
