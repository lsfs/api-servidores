package com.seplag.processoseletivo.application.usecases.endereco.impl;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.endereco.EnderecoResponseDto;
import com.seplag.processoseletivo.application.usecases.endereco.AtualizarEnderecoUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarEnderecoUseCaseImpl implements AtualizarEnderecoUseCase {

    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;

    public AtualizarEnderecoUseCaseImpl(EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public EnderecoResponseDto execute(Long id, EnderecoRequestDto enderecoRequestDto) {

        Endereco endereco = buscarEnderecoPorId(id);
        endereco.setEnd_tipo_logradouro(enderecoRequestDto.end_tipo_logradouro());
        endereco.setEnd_logradouro(enderecoRequestDto.end_logradouro());
        endereco.setEnd_numero(enderecoRequestDto.end_numero());
        endereco.setEnd_bairro(enderecoRequestDto.end_bairro());
        endereco.setCidade(buscaCidade(enderecoRequestDto.cidade_id()));

        Endereco enderecoAtualizado = enderecoRepository.atualizar(endereco);
        return EnderecoResponseDto.of(enderecoAtualizado);

    }

    private Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.buscarPorId(id);
    }

    private Cidade buscaCidade(Long id) {
        return cidadeRepository.buscarPorId(id);
    }

}
