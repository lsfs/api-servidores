package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoRequestDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.AtualizarServidorEfetivoUseCase;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AtualizaServidorEfetivoUseCaseImpl implements AtualizarServidorEfetivoUseCase {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final EnderecoRepository enderecoRepository;

    public AtualizaServidorEfetivoUseCaseImpl(ServidorEfetivoRepository servidorEfetivoRepository, EnderecoRepository enderecoRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public ServidorEfetivoResponseDto execute(Long id, ServidorEfetivoRequestDto servidorEfetivoRequestDto) {

        ServidorEfetivo servidorEfetivo = buscarServidorEfetivoPorId(id);

        Pessoa pessoa = new Pessoa();
        pessoa.setPes_id(id);
        pessoa.setPes_nome(servidorEfetivoRequestDto.servidorEfetivoNome());
        pessoa.setPes_data_nascimento(servidorEfetivoRequestDto.servidorEfetivoDataNascimento());
        pessoa.setPes_pai(servidorEfetivoRequestDto.servidorEfetivoPai());
        pessoa.setPes_mae(servidorEfetivoRequestDto.servidorEfetivoMae());
        pessoa.setPes_sexo(servidorEfetivoRequestDto.servidorEfetivoSexo());

        Set<Endereco> enderecos = buscarEnderecos(servidorEfetivoRequestDto.servidorEfetivoEnderecos());
        pessoa.setEnderecos(enderecos);
        servidorEfetivo.setPessoa(pessoa);

        servidorEfetivo.setSe_matricula(servidorEfetivoRequestDto.servidorMatricula());

        ServidorEfetivo servidorEfetivoAtualizado = servidorEfetivoRepository.atualizar(id, servidorEfetivo);
        return ServidorEfetivoResponseDto.of(servidorEfetivoAtualizado);


    }

    private ServidorEfetivo buscarServidorEfetivoPorId(Long id) {
        return servidorEfetivoRepository.buscarPorId(id);
    }

    private Set<Endereco> buscarEnderecos(Set<Long> enderecos) {

        return enderecos.stream()
                .map(enderecoRepository::buscarPorId)
                .collect(Collectors.toSet());
    }
}
