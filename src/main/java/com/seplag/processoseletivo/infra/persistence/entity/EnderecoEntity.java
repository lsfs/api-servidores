package com.seplag.processoseletivo.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long end_id;

    private String end_tipo_logradouro;
    private String end_logradouro;
    private Long end_numero;
    private String end_bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid_id")
    private CidadeEntity cidade;

    @ManyToMany(mappedBy = "enderecos")
    private Set<UnidadeEntity> unidades = Set.of();

    @ManyToMany(mappedBy = "pes_enderecos")
    private Set<PessoaEntity> pessoas = Set.of();

}
