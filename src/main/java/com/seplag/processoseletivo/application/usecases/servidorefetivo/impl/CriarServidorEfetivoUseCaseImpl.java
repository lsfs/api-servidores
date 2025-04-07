package com.seplag.processoseletivo.application.usecases.servidorefetivo.impl;

import com.seplag.processoseletivo.application.dto.endereco.EnderecoRequestDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoRequestDto;
import com.seplag.processoseletivo.application.dto.servidorefetivo.ServidorEfetivoResponseDto;
import com.seplag.processoseletivo.application.usecases.fotopessoa.UploadFotoPessoaUseCase;
import com.seplag.processoseletivo.application.usecases.servidorefetivo.CriarServidorEfetivoUseCase;
import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.model.Pessoa;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CriarServidorEfetivoUseCaseImpl implements CriarServidorEfetivoUseCase {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final EnderecoRepository enderecoRepository;
    private final CidadeRepository cidadeRepository;

    public CriarServidorEfetivoUseCaseImpl(ServidorEfetivoRepository servidorEfetivoRepository, EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public ServidorEfetivoResponseDto execute(ServidorEfetivoRequestDto servidorEfetivoRequestDto) {
        ServidorEfetivo servidorEfetivo = toModel(servidorEfetivoRequestDto);
        ServidorEfetivo servidorEfetivoSalvo = servidorEfetivoRepository.criar(servidorEfetivo);

        return ServidorEfetivoResponseDto.of(servidorEfetivoSalvo);

    }

    private ServidorEfetivo toModel(ServidorEfetivoRequestDto requestDto) {

        Pessoa pessoa = new Pessoa();
        pessoa.setPes_nome(requestDto.servidorEfetivoNome());
        pessoa.setPes_data_nascimento(requestDto.servidorEfetivoDataNascimento());
        pessoa.setPes_sexo(requestDto.servidorEfetivoSexo());
        pessoa.setPes_mae(requestDto.servidorEfetivoMae());
        pessoa.setPes_pai(requestDto.servidorEfetivoPai());

        Set<Endereco> enderecos = salvarEnderecos(requestDto.servidorEfetivoEnderecos());

        pessoa.setEnderecos(enderecos);

        ServidorEfetivo servidorEfetivo = new ServidorEfetivo();
        servidorEfetivo.setPessoa(pessoa);
        servidorEfetivo.setSe_matricula(requestDto.servidorMatricula());

        return servidorEfetivo;
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
