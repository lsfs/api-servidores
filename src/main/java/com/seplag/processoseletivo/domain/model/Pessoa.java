package com.seplag.processoseletivo.domain.model;

import java.util.Date;
import java.util.Set;

public class Pessoa {

    private Long pes_id;
    private String pes_nome;
    private Date pes_data_nascimento;
    private String pes_sexo;
    private String pes_mae;
    private String pes_pai;
    private Set<Endereco> enderecos;


    public Pessoa() {
    }

    public Pessoa(Long pes_id, String pes_nome, Date pes_data_nascimento, String pes_sexo, String pes_mae, String pes_pai, Set<Endereco> enderecos) {
        this.pes_id = pes_id;
        this.pes_nome = pes_nome;
        this.pes_data_nascimento = pes_data_nascimento;
        this.pes_sexo = pes_sexo;
        this.pes_mae = pes_mae;
        this.pes_pai = pes_pai;
        this.enderecos = enderecos;
    }

    public Long getPes_id() {
        return pes_id;
    }

    public void setPes_id(Long pes_id) {
        this.pes_id = pes_id;
    }

    public String getPes_nome() {
        return pes_nome;
    }

    public void setPes_nome(String pes_nome) {
        this.pes_nome = pes_nome;
    }

    public Date getPes_data_nascimento() {
        return pes_data_nascimento;
    }

    public void setPes_data_nascimento(Date pes_data_nascimento) {
        this.pes_data_nascimento = pes_data_nascimento;
    }

    public String getPes_sexo() {
        return pes_sexo;
    }

    public void setPes_sexo(String pes_sexo) {
        this.pes_sexo = pes_sexo;
    }

    public String getPes_mae() {
        return pes_mae;
    }

    public void setPes_mae(String pes_mae) {
        this.pes_mae = pes_mae;
    }

    public String getPes_pai() {
        return pes_pai;
    }

    public void setPes_pai(String pes_pai) {
        this.pes_pai = pes_pai;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
