module co.edu.poli.actividad9 {
    // Requerimientos de JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    
    // Abre los paquetes para que JavaFX pueda acceder vía reflexión
    opens co.edu.poli.actividad9.PrimaryController to javafx.fxml, javafx.graphics;
    opens co.edu.poli.actividad9.vista to javafx.fxml, javafx.graphics;
    opens co.edu.poli.actividad9.model to javafx.base;
    
    // Exporta los paquetes que necesitan ser accesibles
    exports co.edu.poli.actividad9.PrimaryController;
    exports co.edu.poli.actividad9.vista;
    exports co.edu.poli.actividad9.model;
    exports co.edu.poli.actividad9.servicios;
}