package com.seplag.processoseletivo.application.usecases.servidortemporario.impl;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempRequestDto;
import com.seplag.processoseletivo.application.dto.servidortemporario.ServidorTempResponseDto;
import com.seplag.processoseletivo.application.usecases.servidortemporario.CriarServidorTempUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CriarServidorTempUseCaseImpl implements CriarServidorTempUseCase {

    private final ServidorTemporarioRepository servidorTempRepository;
    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;


    public CriarServidorTempUseCaseImpl(ServidorTemporarioRepository servidorTempRepository, EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.servidorTempRepository = servidorTempRepository;
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
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

        Set<Endereco> enderecos = salvarEnderecos(servidorTempRequestDto.servidorTempEnderecos());

        pessoa.setEnderecos(enderecos);
        ServidorTemporario servidorTemp = new ServidorTemporario();
        servidorTemp.setPessoa(pessoa);
        servidorTemp.setSt_data_admissao(servidorTempRequestDto.servidorTempDataAdmissao());
        servidorTemp.setSt_data_demissao(servidorTempRequestDto.servidorTempDataDemissao());

        return servidorTemp;

    }

    private Set<Endereco> salvarEnderecos(Set<EnderecoRequestDto> enderecos) {

        return enderecos.stream()
                .map(enderecoRequestDto -> {

                    Endereco endereco = new Endereco();
                    endereco.setEnd_tipo_logradouro(enderecoRequestDto.end_tipo_logradouro());
                    endereco.setEnd_logradouro(enderecoRequestDto.end_logradouro());
                    endereco.setEnd_numero(enderecoRequestDto.end_numero());
                    endereco.setEnd_bairro(enderecoRequestDto.end_bairro());


                    Cidade cidade = this.cidadeRepository.buscarOuCriar(enderecoRequestDto.cidade(), enderecoRequestDto.estado());
                    endereco.setCidade(cidade);
                    return enderecoRepository.criar(endereco);

                })
                .collect(Collectors.toSet());
    }

}
