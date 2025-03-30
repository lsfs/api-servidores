package com.seplag.processoseletivo.domain.model;

import java.time.LocalDate;

public class Lotacao {

    private Long lot_id;
    private Pessoa pes_id;
    private Unidade uni_id;
    private LocalDate lot_data_lotacao;
    private LocalDate lot_data_remocao;
    private String lot_portaria;

    public Lotacao() {
    }

    public Lotacao(Long lot_id, Pessoa pes_id, Unidade uni_id, LocalDate lot_data_lotacao, LocalDate lot_data_remocao, String lot_portaria) {
        this.lot_id = lot_id;
        this.pes_id = pes_id;
        this.uni_id = uni_id;
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

    public Pessoa getPes_id() {
        return pes_id;
    }

    public void setPes_id(Pessoa pes_id) {
        this.pes_id = pes_id;
    }

    public Unidade getUni_id() {
        return uni_id;
    }

    public void setUni_id(Unidade uni_id) {
        this.uni_id = uni_id;
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
