package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o ficheiro visual FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));

        // Se criou a pasta 'resources' na raiz em vez do pacote 'view', use:
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("FinTrack - Gestão de Finanças Pessoais");
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}