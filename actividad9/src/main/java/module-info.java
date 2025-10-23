module co.edu.poli.actividad9 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.actividad9 to javafx.fxml;
    exports co.edu.poli.actividad9;
}
