package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.application.dto.shared.MensagemRetorno;
import com.seplag.processoseletivo.application.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.domain.model.ServidorTemporario;
import com.seplag.processoseletivo.domain.repositories.ServidorTemporarioRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import com.seplag.processoseletivo.infra.mapper.EnderecoMapper;
import com.seplag.processoseletivo.infra.mapper.PessoaMapper;
import com.seplag.processoseletivo.infra.mapper.ServidorTemporarioMapper;
import com.seplag.processoseletivo.infra.persistence.entity.EnderecoEntity;
import com.seplag.processoseletivo.infra.persistence.entity.PessoaEntity;
import com.seplag.processoseletivo.infra.persistence.entity.ServidorTemporarioEntity;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.PessoaJpaRepository;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.ServidorTemporarioJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class ServidorTemporarioRepositoryImpl implements ServidorTemporarioRepository {

    private final ServidorTemporarioJpaRepository servidorTemporarioJpaRepository;
    private final PessoaJpaRepository pessoaJpaRepository;

    public ServidorTemporarioRepositoryImpl(ServidorTemporarioJpaRepository servidorTemporarioJpaRepository, PessoaJpaRepository pessoaJpaRepository) {
        this.servidorTemporarioJpaRepository = servidorTemporarioJpaRepository;
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

    @Override
    public ServidorTemporario criar(ServidorTemporario servidorTempModel) {

        ServidorTemporarioEntity servidorTempEntity = new ServidorTemporarioEntity();
        servidorTempEntity.setPessoa(PessoaMapper.toEntity(servidorTempModel.getPessoa()));

        if (servidorTempModel.getSt_data_demissao() != null) {
            servidorTempEntity.setSt_data_demissao(servidorTempModel.getSt_data_demissao());
        }

        servidorTempEntity.setSt_data_admissao(servidorTempModel.getSt_data_admissao());

        ServidorTemporarioEntity servidorTempEntitySalvo = servidorTemporarioJpaRepository.save(servidorTempEntity);
        return ServidorTemporarioMapper.toModel(servidorTempEntitySalvo);

    }

    @Override
    public ServidorTemporario atualizar(Long id, ServidorTemporario servidorTemporario) {

        PessoaEntity pessoaEntity = pessoaJpaRepository.findById(servidorTemporario.getPessoa().getPes_id())
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoaEntity.setPes_nome(servidorTemporario.getPessoa().getPes_nome());
        pessoaEntity.setPes_data_nascimento(servidorTemporario.getPessoa().getPes_data_nascimento());
        pessoaEntity.setPes_sexo(servidorTemporario.getPessoa().getPes_sexo());
        pessoaEntity.setPes_mae(servidorTemporario.getPessoa().getPes_mae());
        pessoaEntity.setPes_pai(servidorTemporario.getPessoa().getPes_pai());

        Set<EnderecoEntity> enderecos = servidorTemporario.getPessoa().getEnderecos()
                .stream()
                .map(EnderecoMapper::toEntity)
                .collect(Collectors.toSet());
        pessoaEntity.setPes_enderecos(enderecos);

        ServidorTemporarioEntity servidorTemporarioEntity = servidorTemporarioJpaRepository.findById(id)
                .orElse(new ServidorTemporarioEntity());
        servidorTemporarioEntity.setId(id);

        servidorTemporarioEntity.setPessoa(pessoaEntity);
        servidorTemporarioEntity.setSt_data_admissao(servidorTemporario.getSt_data_admissao());
        servidorTemporarioEntity.setSt_data_demissao(servidorTemporario.getSt_data_demissao());

        ServidorTemporarioEntity servidorAtualizado = servidorTemporarioJpaRepository.save(servidorTemporarioEntity);
        return ServidorTemporarioMapper.toModel(servidorAtualizado);
    }


    @Override
    public ServidorTemporario buscarPorId(Long id) {

        Optional<ServidorTemporarioEntity> servidorTempEntity = servidorTemporarioJpaRepository.buscarPorId(id);
        return servidorTempEntity.map(ServidorTemporarioMapper::toModel)
                .orElseThrow(() -> new EntityNotFoundException("Servidor temporário não encontrado"));
    }

    @Override
    public RespostaPaginada<ServidorTemporario> buscaServidoresTemporarios(int pagina, int tamanho) {

        var respostaPaginada = servidorTemporarioJpaRepository.findAll(PageRequest.of(pagina, tamanho));
        List<ServidorTemporario> servidoresTemporarios = respostaPaginada.getContent()
                .stream()
                .map(ServidorTemporarioMapper::toModel)
                .toList();

        return new RespostaPaginada<>(servidoresTemporarios, respostaPaginada.getNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());
    }

    @Override
    public void deletar(Long id) {
        var servidorTempEntity = servidorTemporarioJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servidor temporário não encontrado"));
        servidorTemporarioJpaRepository.delete(servidorTempEntity);
    }
}
