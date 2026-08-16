package model;

import utils.Formatador;

public class Transacao {
    private static int contadorId = 1;

    private int Id;
    private String descricao;
    private double valor;
    private String tipo;

    public Transacao(String descricao, double valor, String tipo) {
        this.Id = contadorId++;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo.toUpperCase();
    }

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

    public String exibirDetalhes() {
        return String.format("[%d] %s-%s: %s", Id, tipo, descricao, Formatador.formatarMoeda(valor));
    }
}
