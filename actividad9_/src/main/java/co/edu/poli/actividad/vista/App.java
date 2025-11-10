package co.edu.poli.actividad.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aplicación principal de JavaFX
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML desde la ruta correcta
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/poli/actividad/vista/Formulario.fxml"));
        
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Gestión de Monedas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

