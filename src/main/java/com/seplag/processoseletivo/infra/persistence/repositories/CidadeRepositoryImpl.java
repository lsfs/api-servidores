package com.seplag.processoseletivo.infra.persistence.repositories;

import com.seplag.processoseletivo.domain.model.Cidade;
import com.seplag.processoseletivo.domain.repositories.CidadeRepository;
import com.seplag.processoseletivo.domain.utils.RespostaPaginada;
import com.seplag.processoseletivo.infra.mapper.CidadeMapper;
import com.seplag.processoseletivo.infra.persistence.entity.CidadeEntity;
import com.seplag.processoseletivo.infra.persistence.repositories.jpa.CidadeJpaRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public class CidadeRepositoryImpl implements CidadeRepository {

    CidadeJpaRepository cidadeJpaRepository;

    public CidadeRepositoryImpl(CidadeJpaRepository cidadeJpaRepository) {
        this.cidadeJpaRepository = cidadeJpaRepository;
    }

    @Override
    public Cidade criar(Cidade cidade) {

        CidadeEntity cidadeEntity = new CidadeEntity();
        cidadeEntity.setCid_nome(cidade.getCid_nome());
        cidadeEntity.setCid_uf(cidade.getCid_uf());

        CidadeEntity cidadeSalva = cidadeJpaRepository.save(cidadeEntity);
        return CidadeMapper.toModel(cidadeSalva);

    }

    @Override
    public Optional<Cidade> buscarPorId(Long id) {

        Optional<CidadeEntity> cidadeEntity = cidadeJpaRepository.findById(id);
        return cidadeEntity.map(CidadeMapper::toModel);

    }

    @Override
    public RespostaPaginada<Cidade> buscarCidades(int pagina, int tamanho) {

        var respostaPaginada = cidadeJpaRepository.findAll(PageRequest.of(pagina, tamanho));
        List<Cidade> cidades = respostaPaginada.getContent().stream()
                .map(CidadeMapper::toModel)
                .toList();

        return new RespostaPaginada<>(cidades, respostaPaginada.getNumber(), respostaPaginada.getTotalPages(), respostaPaginada.getTotalElements());

    }

    @Override
    public Cidade atualizar(Long id, Cidade cidade) {

      CidadeEntity cidadeEntity = new CidadeEntity(
                id,
                cidade.getCid_nome(),
                cidade.getCid_uf()
      );

        CidadeEntity cidadeSalva = cidadeJpaRepository.save(cidadeEntity);
        return CidadeMapper.toModel(cidadeSalva);
    }


}
