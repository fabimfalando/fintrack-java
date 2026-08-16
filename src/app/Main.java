package app;

import controller.FinTracker;
import exceptions.EntradaInvalidaException;
import model.Transacao;
import model.TransacaoMensal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinTracker tracker = new FinTracker();
        boolean executando = true;

        while (executando) {
            System.out.println("\n===== FINTRACK CLT =====");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Saldo");
            System.out.println("4. Remover");
            System.out.println("5. Sair");
            System.out.println("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Descrição: ");
                        String desc = scanner.nextLine();

                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Tipo (Receita / Despesa): ");
                        String tipo = scanner.nextLine().toUpperCase();

                        System.out.print("Recorrente? (S/N): ");
                        String rec = scanner.nextLine().trim();

                        if (rec.equalsIgnoreCase("S")) {
                            System.out.print("Dia do vencimento: ");
                            int dia = scanner.nextInt();
                            scanner.nextLine();
                            tracker.adicionarTransacao(new TransacaoMensal(desc, valor, tipo, dia));
                        } else {
                            tracker.adicionarTransacao(new Transacao(desc, valor, tipo));
                        }
                        break;

                    case 2: tracker.listarTransacoes();break;
                    case 3: tracker.calcularSaldoTotal();break;
                    case 4:
                        System.out.print("ID para remover: ");
                        tracker.removerTransacao(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    case 5: executando = false; break;
                    default:
                        System.out.println("Opção invalida. ");
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro: Você digitou um texto onde deveria ser numeros. ");
                scanner.nextLine();
            } catch (EntradaInvalidaException e) {
                System.out.println("Aviso: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
