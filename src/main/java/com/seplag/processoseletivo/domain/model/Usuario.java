package com.seplag.processoseletivo.domain.model;

import com.seplag.processoseletivo.domain.enums.Role;

import java.util.Set;

public class Usuario {

    private Long id;
    private String email;
    private String senha;

    private Set<Role> roles;

    public Usuario(Long id, String email, String senha, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
