package model;

import java.time.LocalDate;

public class TransacaoMensal extends Transacao {

    private final int diaVencimento;

    // O construtor recebe os 5 parâmetros necessários e envia a 'data' para a classe pai (Transacao)
    public TransacaoMensal(String descricao, double valor, String tipo, LocalDate data, int diaVencimento) {
        super(descricao, valor, tipo, data);
        this.diaVencimento = diaVencimento;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + " (Recorrente - Dia: " + diaVencimento + ")";
    }
}