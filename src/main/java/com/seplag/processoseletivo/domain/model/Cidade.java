package com.seplag.processoseletivo.domain.model;

public class Cidade {

    private Long cid_id;
    private String cid_nome;
    private String cid_uf;

    public Cidade() {
    }

    public Cidade(Long cid_id, String cid_nome, String cid_uf) {
        this.cid_id = cid_id;
        this.cid_nome = cid_nome;
        this.cid_uf = cid_uf;
    }

    public Long getCid_id() {
        return cid_id;
    }

    public void setCid_id(Long cid_id) {
        this.cid_id = cid_id;
    }

    public String getCid_nome() {
        return cid_nome;
    }

    public void setCid_nome(String cid_nome) {
        this.cid_nome = cid_nome;
    }

    public String getCid_uf() {
        return cid_uf;
    }

    public void setCid_uf(String cid_uf) {
        this.cid_uf = cid_uf;
    }

}
