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
@Table(name = "unidade")
public class UnidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unid_id;

    private String unid_nome;
    private String unid_sigla;

    @ManyToMany
    @JoinTable
            (name = "unidade_endereco",
                    joinColumns = @JoinColumn(name = "unid_id"),
                    inverseJoinColumns = @JoinColumn(name = "end_id"))
    private Set<EnderecoEntity> enderecos = new HashSet<>();



}
