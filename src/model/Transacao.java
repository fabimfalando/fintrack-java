package model;

import java.time.LocalDate;

public class Transacao {
    private static int contadorId = 1;

    private int Id;
    private String descricao;
    private double valor;
    private String tipo;
    private LocalDate data;

    // Construtor principal (4 parâmetros)
    public Transacao(String descricao, double valor, String tipo, LocalDate data) {
        this.Id = contadorId++;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = (tipo != null) ? tipo.toUpperCase() : "";
        this.data = (data != null) ? data : LocalDate.now();
    }

    // Construtor com ID (útil para quando o SQLite carrega do banco de dados)
    public Transacao(int id, String descricao, double valor, String tipo, LocalDate data) {
        this.Id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = (tipo != null) ? tipo.toUpperCase() : "";
        this.data = (data != null) ? data : LocalDate.now();
    }

    // Getters e Setters necessários para a TableView do JavaFX
    public int getId() {
        return Id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String exibirDetalhes() {
        return String.format("[%d] %s - %s: R$ %.2f (%s)", Id, tipo, descricao, valor, data);
    }
}