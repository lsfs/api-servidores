package com.seplag.processoseletivo.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pes_id;

    private String pes_nome;

    private LocalDate pes_data_nascimento;

    private String pes_sexo;

    private String pes_mae;
    private String pes_pai;

    @ManyToMany
    @JoinTable(name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),

            inverseJoinColumns = @JoinColumn(name = "end_id"))
    private Set<EnderecoEntity> pes_enderecos = Set.of();
    

}
