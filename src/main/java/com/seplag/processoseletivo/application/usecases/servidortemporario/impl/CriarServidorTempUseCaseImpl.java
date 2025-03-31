package com.seplag.processoseletivo.application.usecases.servidortemporario.impl;

import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.usecases.servidortemporario.CriarServidorTempUseCase;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CriarServidorTempUseCaseImpl implements CriarServidorTempUseCase {

    private final ServidorTemporarioRepository servidorTempRepository;
    private final EnderecoRepository enderecoRepository;


    public CriarServidorTempUseCaseImpl(ServidorTemporarioRepository servidorTempRepository, EnderecoRepository enderecoRepository) {
        this.servidorTempRepository = servidorTempRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public ServidorTempResponseDto execute(ServidorTempRequestDto servidorTempRequestDto) {

        ServidorTemporario servidorTempModel = toModel(servidorTempRequestDto);
        ServidorTemporario servidorTempSalvo = servidorTempRepository.criar(servidorTempModel);

        return ServidorTempResponseDto.of(servidorTempSalvo);

    }

    private ServidorTemporario toModel(ServidorTempRequestDto servidorTempRequestDto) {

        Pessoa pessoa = new Pessoa();
        pessoa.setPes_nome(servidorTempRequestDto.servidorTempNome());
        pessoa.setPes_data_nascimento(servidorTempRequestDto.servidorTempDataNascimento());
        pessoa.setPes_sexo(servidorTempRequestDto.servidorTempSexo());
        pessoa.setPes_mae(servidorTempRequestDto.servidorTempMae());
        pessoa.setPes_pai(servidorTempRequestDto.servidorTempPai());

        Set<Endereco> enderecos = buscarEnderecos(servidorTempRequestDto.servidorTempEnderecos());

        pessoa.setEnderecos(enderecos);
        ServidorTemporario servidorTemp = new ServidorTemporario();
        servidorTemp.setPessoa(pessoa);
        servidorTemp.setSt_data_admissao(servidorTempRequestDto.servidorTempDataAdmissao());
        servidorTemp.setSt_data_demissao(servidorTempRequestDto.servidorTempDataDemissao());

        return servidorTemp;

    }

    private Set<Endereco> buscarEnderecos(Set<Long> enderecos) {

        return enderecos.stream()
                .map(enderecoRepository::buscarPorId)
                .collect(Collectors.toSet());
    }

}
