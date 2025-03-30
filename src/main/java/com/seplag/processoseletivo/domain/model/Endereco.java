package com.seplag.processoseletivo.domain.model;

public class Endereco {

    private Long end_id;
    private String end_tipo_logradouro;
    private String end_logradouro;
    private Long end_numero;
    private String end_bairro;
    private Cidade cid_id;

    public Endereco() {
    }

    public Endereco(Long end_id, String end_tipo_logradouro, String end_logradouro, Long end_numero, String end_bairro) {
        this.end_id = end_id;
        this.end_tipo_logradouro = end_tipo_logradouro;
        this.end_logradouro = end_logradouro;
        this.end_numero = end_numero;
        this.end_bairro = end_bairro;
    }


    public Long getEnd_id() {
        return end_id;
    }

    public void setEnd_id(Long end_id) {
        this.end_id = end_id;
    }

    public String getEnd_tipo_logradouro() {
        return end_tipo_logradouro;
    }

    public void setEnd_tipo_logradouro(String end_tipo_logradouro) {
        this.end_tipo_logradouro = end_tipo_logradouro;
    }

    public String getEnd_logradouro() {
        return end_logradouro;
    }

    public void setEnd_logradouro(String end_logradouro) {
        this.end_logradouro = end_logradouro;
    }

    public Long getEnd_numero() {
        return end_numero;
    }

    public void setEnd_numero(Long end_numero) {
        this.end_numero = end_numero;
    }

    public String getEnd_bairro() {
        return end_bairro;
    }

    public void setEnd_bairro(String end_bairro) {
        this.end_bairro = end_bairro;
    }
}
