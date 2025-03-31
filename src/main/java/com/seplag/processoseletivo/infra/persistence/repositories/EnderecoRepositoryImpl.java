package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.shared.exceptions.EntityNotFoundException;
import com.seplag.processoseletivo.domain.model.Endereco;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.repositories.EnderecoRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import com.seplag.processoseletivo.infra.persistence.mapper.CidadeMapper;
import com.seplag.processoseletivo.infra.persistence.mapper.EnderecoMapper;
import com.seplag.processoseletivo.infra.persistence.entity.CidadeEntity;
import com.seplag.processoseletivo.infra.persistence.entity.EnderecoEntity;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.EnderecoJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public class EnderecoRepositoryImpl implements EnderecoRepository {

    EnderecoJpaRepository enderecoJpaRepository;
    CidadeRepository cidadeRepository;

    public EnderecoRepositoryImpl(EnderecoJpaRepository enderecoJpaRepository, CidadeRepository cidadeRepository) {
        this.enderecoJpaRepository = enderecoJpaRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public Endereco criar(Endereco endereco) {

        EnderecoEntity enderecoEntity = EnderecoMapper.toEntity(endereco);
        CidadeEntity cidadeEntity = getCidadeById(endereco.getCidade().getCid_id());

        enderecoEntity.setCidade(cidadeEntity);

        EnderecoEntity enderecoSalvo = enderecoJpaRepository.save(enderecoEntity);

        return EnderecoMapper.toModel(enderecoSalvo);
    }

    private CidadeEntity getCidadeById(Long id) {
        return CidadeMapper.toEntity(
                cidadeRepository.buscarPorId(id)
        );
    }

    @Override
    public Endereco atualizar(Endereco endereco) {

        EnderecoEntity enderecoEntity = EnderecoMapper.toEntity(endereco);
        CidadeEntity cidadeEntity = getCidadeById(endereco.getCidade().getCid_id());
        enderecoEntity.setCidade(cidadeEntity);

        EnderecoEntity enderecoAtualizado = enderecoJpaRepository.save(enderecoEntity);
        return EnderecoMapper.toModel(enderecoAtualizado);

    }

    @Override
    public RespostaPaginada<Endereco> buscar(int pagina, int tamanho) {

        var respostaPaginada = enderecoJpaRepository.findAll(PageRequest.of(pagina, tamanho));
        List<Endereco> enderecos = respostaPaginada.getContent()
                .stream()
                .map(EnderecoMapper::toModel)
                .toList();

        return new RespostaPaginada<>(enderecos, respostaPaginada.getNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());
    }

    @Override
    public Endereco buscarPorId(Long id) {
        Optional<EnderecoEntity> enderecoEntity = enderecoJpaRepository.findById(id);

        return enderecoEntity.map(EnderecoMapper::toModel).orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

    }
}
