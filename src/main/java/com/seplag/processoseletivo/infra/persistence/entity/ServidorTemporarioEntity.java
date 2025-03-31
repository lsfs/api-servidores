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
@Table(name = "servidor_temporario")
public class ServidorTemporarioEntity{

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private PessoaEntity pessoa;

    private LocalDate st_data_admissao;

    private LocalDate st_data_demissao;


}
