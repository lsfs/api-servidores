package com.seplag.processoseletivo.domain.model;

import java.time.LocalDate;

public class ServidorTemporario {

    private Pessoa pes_id;
    private LocalDate st_data_admissao;
    private LocalDate st_data_demissao;


    public ServidorTemporario() {
    }

    public ServidorTemporario(Pessoa pes_id, LocalDate st_data_admissao, LocalDate st_data_demissao) {
        this.pes_id = pes_id;
        this.st_data_admissao = st_data_admissao;
        this.st_data_demissao = st_data_demissao;
    }

    public Pessoa getPes_id() {
        return pes_id;
    }

    public void setPes_id(Pessoa pes_id) {
        this.pes_id = pes_id;
    }

    public LocalDate getSt_data_admissao() {
        return st_data_admissao;
    }

    public void setSt_data_admissao(LocalDate st_data_admissao) {
        this.st_data_admissao = st_data_admissao;
    }

    public LocalDate getSt_data_demissao() {
        return st_data_demissao;
    }

    public void setSt_data_demissao(LocalDate st_data_demissao) {
        this.st_data_demissao = st_data_demissao;
    }
}
