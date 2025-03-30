package com.seplag.processoseletivo.domain.model;

import java.util.Set;

public class Unidade {

    private Long unid_id;
    private String unid_nome;
    private String unid_sigla;
    private Set<Endereco> unid_enderecos;

    public Unidade() {
    }

    public Unidade(Long unid_id, String unid_nome, String unid_sigla) {
        this.unid_id = unid_id;
        this.unid_nome = unid_nome;
        this.unid_sigla = unid_sigla;
    }

    public Long getUnid_id() {
        return unid_id;
    }

    public void setUnid_id(Long unid_id) {
        this.unid_id = unid_id;
    }

    public String getUnid_nome() {
        return unid_nome;
    }

    public void setUnid_nome(String unid_nome) {
        this.unid_nome = unid_nome;
    }

    public String getUni_sigla() {
        return unid_sigla;
    }

    public void setUni_sigla(String unid_sigla) {
        this.unid_sigla = unid_sigla;
    }
}
