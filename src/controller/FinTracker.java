package controller;

import exceptions.EntradaInvalidaException;
import model.Transacao;
import utils.Formatador;
import java.util.ArrayList;
import java.util.List;

public class FinTracker {
    private List<Transacao> transacoes;

    public FinTracker() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) throws EntradaInvalidaException {
        if (transacao.getValor() <= 0) {
            throw new EntradaInvalidaException("O valor deve ser maior que zero. ");
        }
        transacoes.add(transacao);
        System.out.println("Sucesso: Transação Adicionada!");
    }
    public void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma Transação Cadastrada.");
            return;
        }
        for (Transacao t : transacoes){
            System.out.println(t.exibirDetalhes());
        }
    }
    public void removerTransacao(int id) throws EntradaInvalidaException {
        boolean removido = false;
        for (int i = 0; i < transacoes.size(); i++) {
            if (transacoes.get(i).getId() == id) {
                transacoes.remove(i);
                removido = true;
                System.out.println("Sucesso: Transação Removida!");
                break;
            }

        }
        if (!removido) {
            throw new EntradaInvalidaException("ID não encontrado.");
        }
    }

    public void calcularSaldoTotal() {
        double saldo = 0;
        for (Transacao t: transacoes){
            if (t.getTipo().equals("RECEITA")) saldo += t.getValor();
            else if (t.getTipo().equals("DESPESA"))  saldo -= t.getValor();
            }
        System.out.println("\n>>> Saldo atual: " + Formatador.formatarMoeda(saldo) +"<<<");
        }

    }

