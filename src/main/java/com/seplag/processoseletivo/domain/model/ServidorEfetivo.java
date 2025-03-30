package com.seplag.processoseletivo.domain.model;

public class ServidorEfetivo {

    private Pessoa pes_id;
    private String se_matricula;

    public ServidorEfetivo() {
    }

    public ServidorEfetivo(Pessoa pes_id, String se_matricula) {
        this.pes_id = pes_id;
        this.se_matricula = se_matricula;
    }

    public Pessoa getPes_id() {
        return pes_id;
    }

    public void setPes_id(Pessoa pes_id) {
        this.pes_id = pes_id;
    }

    public String getSe_matricula() {
        return se_matricula;
    }

    public void setSe_matricula(String se_matricula) {
        this.se_matricula = se_matricula;
    }
}
