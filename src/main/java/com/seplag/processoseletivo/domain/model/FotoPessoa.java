package com.seplag.processoseletivo.domain.model;

import java.time.LocalDate;

public class FotoPessoa {

    private Long fp_id;
    private Pessoa pes_id;
    private LocalDate fp_data;
    private String fp_bucket;
    private String fp_hash;

    public FotoPessoa() {
    }

    public FotoPessoa(Long fp_id, Pessoa pes_id, LocalDate fp_data, String fp_bucket, String fp_hash) {
        this.fp_id = fp_id;
        this.pes_id = pes_id;
        this.fp_data = fp_data;
        this.fp_bucket = fp_bucket;
        this.fp_hash = fp_hash;
    }

    public Long getFp_id() {
        return fp_id;
    }

    public void setFp_id(Long fp_id) {
        this.fp_id = fp_id;
    }

    public Pessoa getPes_id() {
        return pes_id;
    }

    public void setPes_id(Pessoa pes_id) {
        this.pes_id = pes_id;
    }

    public LocalDate getFp_data() {
        return fp_data;
    }

    public void setFp_data(LocalDate fp_data) {
        this.fp_data = fp_data;
    }

    public String getFp_bucket() {
        return fp_bucket;
    }

    public void setFp_bucket(String fp_bucket) {
        this.fp_bucket = fp_bucket;
    }

    public String getFp_hash() {
        return fp_hash;
    }

    public void setFp_hash(String fp_hash) {
        this.fp_hash = fp_hash;
    }
}
