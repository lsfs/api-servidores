package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.domain.model.ServidorEfetivo;
import com.seplag.processoseletivo.domain.repositories.ServidorEfetivoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import com.seplag.processoseletivo.infra.persistence.mapper.EnderecoMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.PessoaMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.ServidorEfetivoMapper;
import com.seplag.processoseletivo.infra.persistence.entity.EnderecoEntity;
import com.seplag.processoseletivo.infra.persistence.entity.PessoaEntity;
import com.seplag.processoseletivo.infra.persistence.entity.ServidorEfetivoEntity;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.PessoaJpaRepository;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.ServidorEfetivoJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class ServidorEfetivoRepositoryImpl implements ServidorEfetivoRepository {

    private final ServidorEfetivoJpaRepository servidorEfetivoJpaRepository;
    private final PessoaJpaRepository pessoaJpaRepository;


    public ServidorEfetivoRepositoryImpl(ServidorEfetivoJpaRepository servidorEfetivoJpaRepository, PessoaJpaRepository pessoaJpaRepository) {
        this.servidorEfetivoJpaRepository = servidorEfetivoJpaRepository;
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override
    public ServidorEfetivo criar(ServidorEfetivo servidor) {

        ServidorEfetivoEntity servidorEfetivoEntity = new ServidorEfetivoEntity();
        servidorEfetivoEntity.setPessoa(PessoaMapper.toEntity(servidor.getPessoa()));

        servidorEfetivoEntity.setSe_matricula(servidor.getSe_matricula());

        ServidorEfetivoEntity servidorEfetivoEntitySalvo = servidorEfetivoJpaRepository.save(servidorEfetivoEntity);
        return ServidorEfetivoMapper.toModel(servidorEfetivoEntitySalvo);

    }

    @Override
    public ServidorEfetivo buscarPorId(Long id) {

        Optional<ServidorEfetivoEntity> servidorEfetivoEntity = servidorEfetivoJpaRepository.findById(id);
        return servidorEfetivoEntity.map(ServidorEfetivoMapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("Servidor efetivo não encontrado"));

    }

    @Override
    public ServidorEfetivo atualizar(Long id, ServidorEfetivo servidor) {

        PessoaEntity pessoaEntity = pessoaJpaRepository.findById(servidor.getPessoa().getPes_id())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoaEntity.setPes_nome(servidor.getPessoa().getPes_nome());
        pessoaEntity.setPes_data_nascimento(servidor.getPessoa().getPes_data_nascimento());
        pessoaEntity.setPes_sexo(servidor.getPessoa().getPes_sexo());
        pessoaEntity.setPes_mae(servidor.getPessoa().getPes_mae());
        pessoaEntity.setPes_pai(servidor.getPessoa().getPes_pai());

        Set<EnderecoEntity> enderecos = servidor.getPessoa().getEnderecos()
                .stream()
                .map(EnderecoMapper::toEntity)
                .collect(Collectors.toSet());

        pessoaEntity.setPes_enderecos(enderecos);

        ServidorEfetivoEntity servidorEfetivoEntity = servidorEfetivoJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor efetivo não encontrado"));

        servidorEfetivoEntity.setPessoa(pessoaEntity);
        servidorEfetivoEntity.setSe_matricula(servidor.getSe_matricula());

        ServidorEfetivoEntity servidorAtualizado = servidorEfetivoJpaRepository.save(servidorEfetivoEntity);
        return ServidorEfetivoMapper.toModel(servidorAtualizado);

    }

    @Override
    public RespostaPaginada<ServidorEfetivo> buscaServidores(int pagina, int tamanho) {

        var respostaPaginada = servidorEfetivoJpaRepository.findAll(PageRequest.of(pagina, tamanho));
        List<ServidorEfetivo> servidorEfetivos = respostaPaginada.getContent()
                .stream()
                .map(ServidorEfetivoMapper::toModel)
                .toList();
        return new RespostaPaginada<>(servidorEfetivos,respostaPaginada.getNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }

    @Override
    public void deletar(Long id) {
        var servidorEfetivoEntity = servidorEfetivoJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor efetivo não encontrado"));
        servidorEfetivoJpaRepository.delete(servidorEfetivoEntity);

    }

    @Override
    public RespostaPaginada<ServidorEfetivo> listarPorUnidade(Long unid_id, int pagina, int tamanho) {
        return null;
    }
}
