package co.edu.poli.actividad9.PrimaryController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // TextFields
    @FXML private TextField txtId;
    @FXML private TextField txtAnio;
    @FXML private TextField txtValor;

    // ComboBox
    @FXML private ComboBox<String> cmbPais;
    @FXML private ComboBox<String> cmbColeccionista;
    @FXML private ComboBox<String> cmbConservacion;

    // RadioButtons
    @FXML private RadioButton rbAntigua;
    @FXML private RadioButton rbConmemorativa;
    @FXML private ToggleGroup tipoMoneda;

    // CheckBox
    @FXML private CheckBox chkCertificada;

    // Buttons
    @FXML private Button bttGuardar;
    @FXML private Button bttModificar;
    @FXML private Button bttEliminar;
    @FXML private Button bttSerializar;
    @FXML private Button bttDeserializar;

    // TableView y Columnas
    @FXML private TableView<Moneda> tblMonedas;
    @FXML private TableColumn<Moneda, String> colId;
    @FXML private TableColumn<Moneda, String> colPais;
    @FXML private TableColumn<Moneda, String> colColeccionista;
    @FXML private TableColumn<Moneda, Integer> colAnio;
    @FXML private TableColumn<Moneda, Double> colValor;
    @FXML private TableColumn<Moneda, String> colTipo;

    // Lista observable para la tabla
    private ObservableList<Moneda> listaMonedas = FXCollections.observableArrayList();
    private Moneda monedaSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar ComboBox con datos de ejemplo
        cmbPais.setItems(FXCollections.observableArrayList(
            "Colombia", "Estados Unidos", "España", "México", "Argentina", "Chile"
        ));
        
        cmbColeccionista.setItems(FXCollections.observableArrayList(
            "Juan Pérez", "María García", "Carlos López", "Ana Martínez"
        ));
        
        cmbConservacion.setItems(FXCollections.observableArrayList(
            "Excelente", "Muy Buena", "Buena", "Regular", "Pobre"
        ));

        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colColeccionista.setCellValueFactory(new PropertyValueFactory<>("coleccionista"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        // Asignar la lista observable a la tabla
        tblMonedas.setItems(listaMonedas);
    }

    @FXML
    private void guardarMoneda() {
        try {
            if (validarCampos()) {
                Moneda moneda = crearMonedaDesdeFormulario();
                listaMonedas.add(moneda);
                limpiarFormulario();
                mostrarAlerta("Éxito", "Moneda guardada correctamente", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al guardar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void modificarMoneda() {
        if (monedaSeleccionada != null) {
            try {
                if (validarCampos()) {
                    monedaSeleccionada.setId(txtId.getText());
                    monedaSeleccionada.setPais(cmbPais.getValue());
                    monedaSeleccionada.setColeccionista(cmbColeccionista.getValue());
                    monedaSeleccionada.setAnio(Integer.parseInt(txtAnio.getText()));
                    monedaSeleccionada.setValor(Double.parseDouble(txtValor.getText()));
                    monedaSeleccionada.setTipo(rbAntigua.isSelected() ? "Antigua" : "Conmemorativa");
                    monedaSeleccionada.setConservacion(cmbConservacion.getValue());
                    monedaSeleccionada.setCertificada(chkCertificada.isSelected());
                    
                    tblMonedas.refresh();
                    limpiarFormulario();
                    mostrarAlerta("Éxito", "Moneda modificada correctamente", Alert.AlertType.INFORMATION);
                }
            } catch (Exception e) {
                mostrarAlerta("Error", "Error al modificar: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione una moneda de la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void eliminarMoneda() {
        if (monedaSeleccionada != null) {
            listaMonedas.remove(monedaSeleccionada);
            limpiarFormulario();
            mostrarAlerta("Éxito", "Moneda eliminada correctamente", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Advertencia", "Seleccione una moneda de la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void seleccionarMoneda(MouseEvent event) {
        monedaSeleccionada = tblMonedas.getSelectionModel().getSelectedItem();
        if (monedaSeleccionada != null) {
            txtId.setText(monedaSeleccionada.getId());
            cmbPais.setValue(monedaSeleccionada.getPais());
            cmbColeccionista.setValue(monedaSeleccionada.getColeccionista());
            txtAnio.setText(String.valueOf(monedaSeleccionada.getAnio()));
            txtValor.setText(String.valueOf(monedaSeleccionada.getValor()));
            cmbConservacion.setValue(monedaSeleccionada.getConservacion());
            chkCertificada.setSelected(monedaSeleccionada.isCertificada());
            
            if ("Antigua".equals(monedaSeleccionada.getTipo())) {
                rbAntigua.setSelected(true);
            } else {
                rbConmemorativa.setSelected(true);
            }
        }
    }

    @FXML
    private void serializarMonedas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("monedas.dat"))) {
            List<Moneda> lista = new ArrayList<>(listaMonedas);
            oos.writeObject(lista);
            mostrarAlerta("Éxito", "Datos serializados correctamente", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            mostrarAlerta("Error", "Error al serializar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void deserializarMonedas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("monedas.dat"))) {
            List<Moneda> lista = (List<Moneda>) ois.readObject();
            listaMonedas.clear();
            listaMonedas.addAll(lista);
            mostrarAlerta("Éxito", "Datos deserializados correctamente", Alert.AlertType.INFORMATION);
        } catch (IOException | ClassNotFoundException e) {
            mostrarAlerta("Error", "Error al deserializar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private Moneda crearMonedaDesdeFormulario() {
        String id = txtId.getText();
        String pais = cmbPais.getValue();
        String coleccionista = cmbColeccionista.getValue();
        int anio = Integer.parseInt(txtAnio.getText());
        double valor = Double.parseDouble(txtValor.getText());
        String tipo = rbAntigua.isSelected() ? "Antigua" : "Conmemorativa";
        String conservacion = cmbConservacion.getValue();
        boolean certificada = chkCertificada.isSelected();
        
        return new Moneda(id, pais, coleccionista, anio, valor, tipo, conservacion, certificada);
    }

    private boolean validarCampos() {
        if (txtId.getText().isEmpty() || txtAnio.getText().isEmpty() || txtValor.getText().isEmpty() ||
            cmbPais.getValue() == null || cmbColeccionista.getValue() == null || cmbConservacion.getValue() == null) {
            mostrarAlerta("Advertencia", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return false;
        }
        
        try {
            Integer.parseInt(txtAnio.getText());
            Double.parseDouble(txtValor.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Año y Valor deben ser números válidos", Alert.AlertType.ERROR);
            return false;
        }
        
        if (!rbAntigua.isSelected() && !rbConmemorativa.isSelected()) {
            mostrarAlerta("Advertencia", "Seleccione un tipo de moneda", Alert.AlertType.WARNING);
            return false;
        }
        
        return true;
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtAnio.clear();
        txtValor.clear();
        cmbPais.setValue(null);
        cmbColeccionista.setValue(null);
        cmbConservacion.setValue(null);
        rbAntigua.setSelected(false);
        rbConmemorativa.setSelected(false);
        chkCertificada.setSelected(false);
        monedaSeleccionada = null;
        tblMonedas.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Clase interna Moneda (mejor crear en archivo separado)
    public static class Moneda implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String id;
        private String pais;
        private String coleccionista;
        private int anio;
        private double valor;
        private String tipo;
        private String conservacion;
        private boolean certificada;

        public Moneda(String id, String pais, String coleccionista, int anio, double valor, 
                     String tipo, String conservacion, boolean certificada) {
            this.id = id;
            this.pais = pais;
            this.coleccionista = coleccionista;
            this.anio = anio;
            this.valor = valor;
            this.tipo = tipo;
            this.conservacion = conservacion;
            this.certificada = certificada;
        }

        // Getters y Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getPais() { return pais; }
        public void setPais(String pais) { this.pais = pais; }
        
        public String getColeccionista() { return coleccionista; }
        public void setColeccionista(String coleccionista) { this.coleccionista = coleccionista; }
        
        public int getAnio() { return anio; }
        public void setAnio(int anio) { this.anio = anio; }
        
        public double getValor() { return valor; }
        public void setValor(double valor) { this.valor = valor; }
        
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        
        public String getConservacion() { return conservacion; }
        public void setConservacion(String conservacion) { this.conservacion = conservacion; }
        
        public boolean isCertificada() { return certificada; }
        public void setCertificada(boolean certificada) { this.certificada = certificada; }
    }
}