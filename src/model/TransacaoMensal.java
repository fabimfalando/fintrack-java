package model;

public class TransacaoMensal extends Transacao {
    private int diaVencimento;

    public TransacaoMensal(String descricao, double valor, String tipo, int diaVencimento) {
        super(descricao, valor, tipo);
        this.diaVencimento = diaVencimento;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }
    @Override
    public String exibirDetalhes(){
        return super.exibirDetalhes() + "(Recorrente - Dia: "+ diaVencimento + ")";
    }
}
