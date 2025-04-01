package com.seplag.processoseletivo.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foto_pessoa")
public class FotoPessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fp_id;

    @ManyToOne
    @JoinColumn(name = "pes_id", referencedColumnName = "pes_id")
    private PessoaEntity pessoa;

    private LocalDate fp_data;
    private String fp_bucket;
    private String fp_hash;




}
