package com.seplag.processoseletivo.domain.model;

public class ServidorEfetivo {

    private Pessoa pessoa;
    private String se_matricula;

    public ServidorEfetivo() {
    }

    public ServidorEfetivo(Pessoa pessoa, String se_matricula) {
        this.pessoa = pessoa;
        this.se_matricula = se_matricula;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getSe_matricula() {
        return se_matricula;
    }

    public void setSe_matricula(String se_matricula) {
        this.se_matricula = se_matricula;
    }
}
