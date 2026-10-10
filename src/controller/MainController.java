package controller;

import dao.TransacaoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transacao;

import java.time.LocalDate;
import java.util.List;

public class MainController {

    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private ComboBox<String> cbTipo;
    @FXML private DatePicker dpData;
    @FXML private TableView<Transacao> tableTransacoes;
    @FXML private TableColumn<Transacao, String> colDescricao;
    @FXML private TableColumn<Transacao, Double> colValor;
    @FXML private TableColumn<Transacao, String> colTipo;
    @FXML private TableColumn<Transacao, Object> colData;

    // Labels para o resumo financeiro
    @FXML private Label lblReceitas;
    @FXML private Label lblDespesas;
    @FXML private Label lblSaldo;

    private final TransacaoDAO dao = new TransacaoDAO();
    private final ObservableList<Transacao> listaTransacoes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (cbTipo != null) {
            cbTipo.setItems(FXCollections.observableArrayList("RECEITA", "DESPESA"));
        }

        if (colDescricao != null) colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        if (colValor != null) colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        if (colTipo != null) colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        if (colData != null) colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        carregarDados();
    }

    private void carregarDados() {
        if (tableTransacoes != null) {
            List<Transacao> doBanco = dao.listarTodas();
            listaTransacoes.setAll(doBanco);
            tableTransacoes.setItems(listaTransacoes);
            tableTransacoes.refresh();

            atualizarTotais(doBanco);
        }
    }

    private void atualizarTotais(List<Transacao> transacoes) {
        double totalReceitas = 0.0;
        double totalDespesas = 0.0;

        for (Transacao t : transacoes) {
            if ("RECEITA".equalsIgnoreCase(t.getTipo())) {
                totalReceitas += t.getValor();
            } else if ("DESPESA".equalsIgnoreCase(t.getTipo())) {
                totalDespesas += t.getValor();
            }
        }

        double saldoTotal = totalReceitas - totalDespesas;

        if (lblReceitas != null) lblReceitas.setText(String.format("R$ %.2f", totalReceitas));
        if (lblDespesas != null) lblDespesas.setText(String.format("R$ %.2f", totalDespesas));
        if (lblSaldo != null) lblSaldo.setText(String.format("R$ %.2f", saldoTotal));
    }

    @FXML
    private void handleAdicionar() {
        try {
            String desc = txtDescricao.getText();
            String valorTexto = txtValor.getText();
            String tipo = cbTipo.getValue();
            LocalDate data = dpData.getValue();

            if (desc == null || desc.trim().isEmpty() ||
                    valorTexto == null || valorTexto.trim().isEmpty() ||
                    tipo == null || data == null) {

                exibirAlerta("Aviso", "Preencha todos os campos antes de adicionar!");
                return;
            }

            double val = Double.parseDouble(valorTexto.replace(",", "."));

            Transacao nova = new Transacao(desc, val, tipo, data);
            dao.salvar(nova);

            carregarDados();

            txtDescricao.clear();
            txtValor.clear();
            cbTipo.getSelectionModel().clearSelection();
            dpData.setValue(null);

        } catch (NumberFormatException e) {
            exibirAlerta("Erro de Formato", "Introduza um valor numérico válido!");
        } catch (Exception e) {
            exibirAlerta("Erro", "Erro ao guardar no banco de dados: " + e.getMessage());
        }
    }

    @FXML
    private void handleRemover() {
        if (tableTransacoes != null) {
            Transacao selecionada = tableTransacoes.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                dao.deletar(selecionada.getId());
                carregarDados();
            } else {
                exibirAlerta("Aviso", "Selecione uma transação na tabela para remover!");
            }
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}