package com.seplag.processoseletivo.application.usecases.unidade.impl;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoUnidadeEnderecoResponseDto;
import com.seplag.processoseletivo.application.dto.unidade.UnidadeResponseDto;
import com.seplag.processoseletivo.application.usecases.unidade.BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.model.Unidade;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import com.seplag.processoseletivo.domain.repositories.UnidadeRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
public class BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCaseImpl implements BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCase {
    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final UnidadeRepository unidadeRepository;

    public BuscarEnderecoDeUnidadePorNomeServidorEfetivoUseCaseImpl(ServidorEfetivoRepository servidorEfetivoRepository, UnidadeRepository unidadeRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.unidadeRepository = unidadeRepository;
    }


    @Override
    public RespostaPaginada<ServidorEfetivoUnidadeEnderecoResponseDto> execute(String parteNome, int pagina, int tamanho) {
        RespostaPaginada<ServidorEfetivo> respostaPaginada =
                servidorEfetivoRepository.buscarPorParteDoNome(parteNome, pagina, tamanho);

        List<ServidorEfetivoUnidadeEnderecoResponseDto> servidoresEnderecos = respostaPaginada.getContent()
                .stream()
                .map(servidorEfetivo -> {
                    var pessoa = servidorEfetivo.getPessoa();
                    if (pessoa == null) {
                        return null;
                    }

                    List<UnidadeResponseDto> unidades = unidadeRepository.buscaPorPessoaId(pessoa.getPes_id())
                            .stream()
                            .map(UnidadeResponseDto::enderecoDetails)
                            .collect(Collectors.toList());

                    if (unidades.isEmpty()) {
                        return null;
                    }

                    return new ServidorEfetivoUnidadeEnderecoResponseDto(
                            pessoa.getPes_nome(),
                            unidades
                    );
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new RespostaPaginada<>(
                servidoresEnderecos,
                respostaPaginada.getPageNumber(),
                respostaPaginada.getTotalPages(),
                respostaPaginada.getTotalElements()
        );
    }
}
