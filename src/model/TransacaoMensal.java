package model;

public class TrasacaoMensal extends Transacao {
    private int diaVencimento;

    public TrasacaoMensal(String descricao, double valor, String tipo, int diaVencimento) {
        super(descricao, valor, tipo);
        this.diaVencimento = diaVencimento;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }
    @Override
    public String exibirDetales(){
        return super.exibirDetalhes() + "(Recorrente - Dia: "+ diaVencimento + ")";
    }
}
