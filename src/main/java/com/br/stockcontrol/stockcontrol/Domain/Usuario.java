package com.br.stockcontrol.stockcontrol.Domain;



import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {

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
}

