package co.edu.poli.actividad9.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 * Carga la vista principal del sistema de monedas (actividad 3)
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Carga el archivo FXML de la vista del formulario
    	AnchorPane root = FXMLLoader.load(getClass().getResource("/co/edu/poli/actividad9/vista/formulario.fxml"));

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sistema de Monedas - CRUD y Serialización");
        stage.show();
    }

    // Método auxiliar para cambiar la vista si se requiere en el futuro
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
