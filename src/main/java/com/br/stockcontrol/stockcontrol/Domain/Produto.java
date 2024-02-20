package com.br.stockcontrol.stockcontrol.Domain;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "data_adicao")
    private LocalDate dataDeAdicao;

    @Column(name = "data_validade")
    private LocalDate dataDeValidade;

    @Column(name = "valor")
    private Double valor;

    public Produto(String id, String nome, Long quantidade, LocalDate dataDeAdicao, LocalDate dataDeValidade, Double valor) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataDeAdicao = dataDeAdicao;
        this.dataDeValidade = dataDeValidade;
        this.valor = valor;
    }

    public Produto(String nome, LocalDate dataDeAdicao) {
        this.nome = nome;
        this.dataDeAdicao = dataDeAdicao;
    }

    public Produto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataDeAdicao() {
        return dataDeAdicao;
    }

    public void setDataDeAdicao(LocalDate dataDeAdicao) {
        this.dataDeAdicao = dataDeAdicao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
