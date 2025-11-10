module co.edu.poli.actividad9_ {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.actividad9_ to javafx.fxml;
    exports co.edu.poli.actividad9_;
}
