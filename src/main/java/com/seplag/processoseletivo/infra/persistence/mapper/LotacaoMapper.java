package com.seplag.processoseletivo.infra.persistence.mapper;

import com.seplag.processoseletivo.domain.model.Lotacao;
import com.seplag.processoseletivo.infra.persistence.entity.LotacaoEntity;

public class LotacaoMapper {

    public static Lotacao toModel(LotacaoEntity lotacaoEntity) {
        if (lotacaoEntity == null) {
            return null;
        }

        Lotacao lotacao = new Lotacao();
        lotacao.setLot_id(lotacaoEntity.getLot_id());
        lotacao.setLot_data_lotacao(lotacaoEntity.getLot_data_lotacao());
        lotacao.setLot_data_remocao(lotacaoEntity.getLot_data_remocao());
        lotacao.setLot_portaria(lotacaoEntity.getLot_portaria());
        lotacao.setPessoa(PessoaMapper.toModel(lotacaoEntity.getPessoa()));
        lotacao.setUnidade(UnidadeMapper.toModel(lotacaoEntity.getUnidade()));
        return lotacao;

    }

}
