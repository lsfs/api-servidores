package com.seplag.processoseletivo.application.usecases.cidade.impl;

import com.seplag.processoseletivo.application.dto.cidade.CidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.cidade.BuscarCidadesUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import org.springframework.stereotype.Service;

@Service
public class BuscarCidadesUseCaseImpl implements BuscarCidadesUseCase {

    private final CidadeRepository cidadeRepository;

    public BuscarCidadesUseCaseImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public RespostaPaginada<CidadeResponseDto> execute(int pagina, int tamanho) {

        RespostaPaginada<Cidade> respostaPaginada = cidadeRepository.buscarCidades(pagina, tamanho);

        var cidades = respostaPaginada.getContent()
                .stream()
                .map(CidadeResponseDto::of)
                .toList();

        return new RespostaPaginada<>(cidades, respostaPaginada.getPageNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }
}
