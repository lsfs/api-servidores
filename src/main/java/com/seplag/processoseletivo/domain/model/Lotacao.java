package com.seplag.processoseletivo.domain.model;

import java.time.LocalDate;

public class Lotacao {

    private Long lot_id;
    private Pessoa pessoa;
    private Unidade unidade;
    private LocalDate lot_data_lotacao;
    private LocalDate lot_data_remocao;
    private String lot_portaria;

    public Lotacao() {
    }

    public Lotacao(Long lot_id, Pessoa pessoa, Unidade unidade, LocalDate lot_data_lotacao, LocalDate lot_data_remocao, String lot_portaria) {
        this.lot_id = lot_id;
        this.pessoa = pessoa;
        this.unidade = unidade;
        this.lot_data_lotacao = lot_data_lotacao;
        this.lot_data_remocao = lot_data_remocao;
        this.lot_portaria = lot_portaria;
    }

    public Long getLot_id() {
        return lot_id;
    }

    public void setLot_id(Long lot_id) {
        this.lot_id = lot_id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public LocalDate getLot_data_lotacao() {
        return lot_data_lotacao;
    }

    public void setLot_data_lotacao(LocalDate lot_data_lotacao) {
        this.lot_data_lotacao = lot_data_lotacao;
    }

    public LocalDate getLot_data_remocao() {
        return lot_data_remocao;
    }

    public void setLot_data_remocao(LocalDate lot_data_remocao) {
        this.lot_data_remocao = lot_data_remocao;
    }

    public String getLot_portaria() {
        return lot_portaria;
    }

    public void setLot_portaria(String lot_portaria) {
        this.lot_portaria = lot_portaria;
    }
}
