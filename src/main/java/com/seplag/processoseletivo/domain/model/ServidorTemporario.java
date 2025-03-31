package com.seplag.processoseletivo.domain.model;

import java.time.LocalDate;

public class ServidorTemporario {

    private Pessoa pessoa;
    private LocalDate st_data_admissao;
    private LocalDate st_data_demissao;


    public ServidorTemporario() {
    }

    public ServidorTemporario(Pessoa pessoa, LocalDate st_data_admissao, LocalDate st_data_demissao) {
        this.pessoa = pessoa;
        this.st_data_admissao = st_data_admissao;
        this.st_data_demissao = st_data_demissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
