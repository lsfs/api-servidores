package com.seplag.processoseletivo.application.usecases.servidortemporario.impl;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTemporarioRequestUpdateDto;
import com.seplag.processoseletivo.application.usecases.servidortemporario.AtualizarServidorTemporarioUseCase;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AtualizarServidorTemporarioUseCaseImpl implements AtualizarServidorTemporarioUseCase {

    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final EnderecoRepository enderecoRepository;

    public AtualizarServidorTemporarioUseCaseImpl(ServidorTemporarioRepository servidorTemporarioRepository, EnderecoRepository enderecoRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public ServidorTempResponseDto execute(Long id, ServidorTemporarioRequestUpdateDto servidorTempRequestDto) {

        ServidorTemporario servidorTemporario = buscarServidorTemporarioPorId(id);

        Pessoa pessoa = new Pessoa();
        pessoa.setPes_id(id);
        pessoa.setPes_nome(servidorTempRequestDto.servidorTempNome());
        pessoa.setPes_data_nascimento(servidorTempRequestDto.servidorTempDataNascimento());
        pessoa.setPes_pai(servidorTempRequestDto.servidorTempPai());
        pessoa.setPes_mae(servidorTempRequestDto.servidorTempMae());
        pessoa.setPes_sexo(servidorTempRequestDto.servidorTempSexo());

        Set<Endereco> enderecos = buscarEnderecos(servidorTempRequestDto.servidorTempEnderecos());
        pessoa.setEnderecos(enderecos);
        servidorTemporario.setPessoa(pessoa);

        servidorTemporario.setSt_data_admissao(servidorTempRequestDto.servidorTempDataAdmissao());
        servidorTemporario.setSt_data_demissao(servidorTempRequestDto.servidorTempDataDemissao());
        ServidorTemporario servidorTemporarioAtualizado = servidorTemporarioRepository.atualizar(id, servidorTemporario);

        return ServidorTempResponseDto.of(servidorTemporarioAtualizado);


    }


    private ServidorTemporario buscarServidorTemporarioPorId(Long id) {
        return servidorTemporarioRepository.buscarPorId(id);
    }

    private Set<Endereco> buscarEnderecos(Set<Long> enderecos) {

        return enderecos.stream()
                .map(enderecoRepository::buscarPorId)
                .collect(Collectors.toSet());
    }

}
