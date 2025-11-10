package co.edu.poli.actividad.controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.poli.actividad.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador para gestionar el CRUD y la serialización/deserialización
 * de monedas en la interfaz JavaFX.
 */
public class FormularioControlador implements Initializable {

    // ========= CAMPOS DEL FORMULARIO =========
    @FXML private TextField txtId;
    @FXML private TextField txtValor;
    @FXML private TextField txtAnio;
    @FXML private TextField txtNombrePais;
    @FXML private TextField txtPeso;
    @FXML private TextField txtDiametro;
    @FXML private TextArea txtDescripcion;

    @FXML private ComboBox<String> cmbTipoMoneda;
    @FXML private ComboBox<String> cmbMaterial;
    @FXML private ComboBox<String> cmbEstadoConservacion;
    @FXML private ComboBox<String> cmbEpoca;

    @FXML private RadioButton rbAntigua;
    @FXML private RadioButton rbConmemorativa;
    @FXML private ToggleGroup grupoTipoMoneda;

    @FXML private CheckBox chkCertificada;
    @FXML private CheckBox chkEnColeccion;
    @FXML private CheckBox chkLimpiada;

    // Campos específicos para moneda conmemorativa
    @FXML private TextField txtMotivoConmemoracion;
    @FXML private TextField txtAnioEvento;
    @FXML private Label lblMotivoConmemoracion;
    @FXML private Label lblAnioEvento;

    // Campos específicos para moneda antigua
    @FXML private TextField txtValorHistorico;
    @FXML private Label lblEpoca;
    @FXML private Label lblValorHistorico;

    // ========= TABLA =========
    @FXML private TableView<Moneda> tblMonedas;
    @FXML private TableColumn<Moneda, String> colId;
    @FXML private TableColumn<Moneda, Double> colValor;
    @FXML private TableColumn<Moneda, Integer> colAnio;
    @FXML private TableColumn<Moneda, String> colPais;
    @FXML private TableColumn<Moneda, String> colMaterial;
    @FXML private TableColumn<Moneda, String> colTipo;

    // Lista observable para la tabla
    private final ObservableList<Moneda> listaMonedas = FXCollections.observableArrayList();

    // Archivo para serialización
    private final File archivo = new File("monedas.dat");

    // ========= MÉTODOS PRINCIPALES =========
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Opciones del combo tipo de moneda
        if (cmbTipoMoneda != null) {
            cmbTipoMoneda.getItems().addAll("Circulación", "Colección", "Antigua", "Moderna");
        }

        // Opciones del combo material
        if (cmbMaterial != null) {
            cmbMaterial.getItems().addAll("Oro", "Plata", "Bronce", "Cobre", "Níquel", "Acero", "Aleación");
        }

        // Opciones del combo estado de conservación
        if (cmbEstadoConservacion != null) {
            cmbEstadoConservacion.getItems().addAll("Excelente", "Muy Bueno", "Bueno", "Regular", "Malo");
        }

        // Opciones del combo época (para monedas antiguas)
        if (cmbEpoca != null) {
            cmbEpoca.getItems().addAll("Romana", "Griega", "Medieval", "Renacentista", "Colonial", 
                                       "Barroca", "Moderna Temprana", "Siglo XIX", "Otra");
        }

        // Asegurar que los radio buttons pertenezcan al ToggleGroup
        if (rbAntigua != null) rbAntigua.setToggleGroup(grupoTipoMoneda);
        if (rbConmemorativa != null) rbConmemorativa.setToggleGroup(grupoTipoMoneda);

        // Seleccionar por defecto Antigua
        if (rbAntigua != null) rbAntigua.setSelected(true);
        toggleCamposEspecificos("Antigua");

        // Listener para mostrar/ocultar campos según tipo de moneda
        if (grupoTipoMoneda != null) {
            grupoTipoMoneda.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    RadioButton selected = (RadioButton) newVal;
                    toggleCamposEspecificos(selected.getText());
                }
            });
        }

        // Configurar columnas de tabla
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id_moneda"));
        if (colValor != null) colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        if (colAnio != null) colAnio.setCellValueFactory(new PropertyValueFactory<>("annyo_creacion"));
        if (colPais != null) colPais.setCellValueFactory(cellData -> {
            Pais pais = cellData.getValue().getPais();
            return new javafx.beans.property.SimpleStringProperty(
                pais != null ? pais.getNombre() : "N/A"
            );
        });
        if (colMaterial != null) colMaterial.setCellValueFactory(cellData -> {
            Especificacion esp = cellData.getValue().getEspecificacion();
            return new javafx.beans.property.SimpleStringProperty(
                esp != null ? esp.getMaterial() : "N/A"
            );
        });
        if (colTipo != null) colTipo.setCellValueFactory(cellData -> {
            String tipo = cellData.getValue() instanceof Conmemorativa ? "Conmemorativa" : "Antigua";
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });

        if (tblMonedas != null) tblMonedas.setItems(listaMonedas);
    }

    @FXML
    private void guardarMoneda(ActionEvent event) {
        try {
            String id = txtId.getText() == null ? "" : txtId.getText().trim();
            String valorTxt = txtValor.getText() == null ? "" : txtValor.getText().trim();
            String anioTxt = txtAnio.getText() == null ? "" : txtAnio.getText().trim();
            String nombrePais = txtNombrePais.getText() == null ? "" : txtNombrePais.getText().trim();

            if (id.isEmpty() || valorTxt.isEmpty() || anioTxt.isEmpty() || nombrePais.isEmpty()) {
                mostrarAlerta("Error", "ID, Valor, Año y País son obligatorios.");
                return;
            }

            double valor = Double.parseDouble(valorTxt);
            int anio = Integer.parseInt(anioTxt);

            // Verificar si el ID ya existe
            for (Moneda m : listaMonedas) {
                if (m.getId_moneda().equals(id)) {
                    mostrarAlerta("Error", "Ya existe una moneda con ese ID.");
                    return;
                }
            }

            // Crear objetos relacionados
            Pais pais = new Pais("P-" + id, "N/A", "N/A", "N/A", nombrePais);
            
            String material = cmbMaterial.getValue() != null ? cmbMaterial.getValue() : "N/A";
            double peso = 0.0;
            double diametro = 0.0;
            try {
                if (txtPeso.getText() != null && !txtPeso.getText().trim().isEmpty()) {
                    peso = Double.parseDouble(txtPeso.getText().trim());
                }
                if (txtDiametro.getText() != null && !txtDiametro.getText().trim().isEmpty()) {
                    diametro = Double.parseDouble(txtDiametro.getText().trim());
                }
            } catch (NumberFormatException e) {
                // Mantener valores por defecto
            }
            
            Especificacion especificacion = new Especificacion("ESP-" + id, peso, material, diametro, "N/A");
            
            String nombreColeccion = chkEnColeccion.isSelected() ? "Mi Colección" : "Sin colección";
            Coleccion coleccion = new Coleccion("COL-" + id, nombreColeccion, "N/A", "N/A");
            
            String estadoConservacion = cmbEstadoConservacion.getValue() != null ? 
                cmbEstadoConservacion.getValue() : "N/A";
            Conservacion conservacion = new Conservacion("CONS-" + id, chkLimpiada.isSelected(), 
                false, estadoConservacion, "N/A", "N/A");
            
            String entidadCertificadora = chkCertificada.isSelected() ? "Certificadora Nacional" : "N/A";
            Certificacion certificacion = new Certificacion("CERT-" + id, 0, entidadCertificadora, "N/A", "N/A");

            Moneda nuevaMoneda;
            
            // Verificar si es conmemorativa o antigua
            if (rbConmemorativa != null && rbConmemorativa.isSelected()) {
                String motivo = txtMotivoConmemoracion.getText() == null ? "" : 
                    txtMotivoConmemoracion.getText().trim();
                String anioEventoTxt = txtAnioEvento.getText() == null ? "" : 
                    txtAnioEvento.getText().trim();
                
                if (motivo.isEmpty() || anioEventoTxt.isEmpty()) {
                    mostrarAlerta("Error", "Para moneda conmemorativa, Motivo y Año del Evento son obligatorios.");
                    return;
                }
                
                int anioEvento = Integer.parseInt(anioEventoTxt);
                nuevaMoneda = new Conmemorativa(id, 30.3, pais, especificacion, coleccion, 2023,
                    conservacion, certificacion, 8000, motivo, anioEvento);
                    
            } else {
                // Moneda Antigua
                String epoca = cmbEpoca.getValue();
                String valorHistoricoTxt = txtValorHistorico.getText() == null ? "" : 
                    txtValorHistorico.getText().trim();
                
                if (epoca == null || epoca.isEmpty() || valorHistoricoTxt.isEmpty()) {
                    mostrarAlerta("Error", "Para moneda antigua, Época y Valor Histórico son obligatorios.");
                    return;
                }
                
                int valorHistorico = Integer.parseInt(valorHistoricoTxt);
                nuevaMoneda = new Antigua(id, valor, pais, especificacion, coleccion, anio,
                    conservacion, certificacion, 10000, epoca, valorHistorico);
            }

            listaMonedas.add(nuevaMoneda);
            limpiarCampos();
            mostrarAlerta("Éxito", "Moneda guardada correctamente.");
            
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Valor, Año, Peso, Diámetro y Valor Histórico deben ser números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar la moneda: " + e.getMessage());
        }
    }

    @FXML
    private void modificarMoneda(ActionEvent event) {
        Moneda seleccionada = tblMonedas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Atención", "Seleccione una moneda para modificar.");
            return;
        }

        try {
            String valorTxt = txtValor.getText() == null ? "" : txtValor.getText().trim();
            String anioTxt = txtAnio.getText() == null ? "" : txtAnio.getText().trim();
            String nombrePais = txtNombrePais.getText() == null ? "" : txtNombrePais.getText().trim();

            if (valorTxt.isEmpty() || anioTxt.isEmpty() || nombrePais.isEmpty()) {
                mostrarAlerta("Error", "Valor, Año y País son obligatorios.");
                return;
            }

            seleccionada.setValor(Double.parseDouble(valorTxt));
            seleccionada.setAnnyo_creacion(Integer.parseInt(anioTxt));
            
            if (seleccionada.getPais() != null) {
                seleccionada.getPais().setNombre(nombrePais);
            }
            
            if (cmbMaterial.getValue() != null && seleccionada.getEspecificacion() != null) {
                seleccionada.getEspecificacion().setMaterial(cmbMaterial.getValue());
            }
            
            // CORRECCIÓN: usar setEstado_conservacion() en lugar de setId_conservacion()
            if (cmbEstadoConservacion.getValue() != null && seleccionada.getConservacion() != null) {
                seleccionada.getConservacion().setEstado_conservacion(cmbEstadoConservacion.getValue());
            }

            // Si es conmemorativa, actualizar campos específicos
            if (seleccionada instanceof Conmemorativa && rbConmemorativa.isSelected()) {
                String motivo = txtMotivoConmemoracion.getText();
                String anioEventoTxt = txtAnioEvento.getText();
                
                // CORRECCIÓN: usar setMotivo_conmemoracion() y setAnnio_evento()
                if (motivo != null && !motivo.trim().isEmpty()) {
                    ((Conmemorativa) seleccionada).setMotivo_conmemoracion(motivo.trim());
                }
                if (anioEventoTxt != null && !anioEventoTxt.trim().isEmpty()) {
                    ((Conmemorativa) seleccionada).setanioEvento(Integer.parseInt(anioEventoTxt.trim()));
                }
            }
            
            // Si es antigua, actualizar campos específicos
            if (seleccionada instanceof Antigua && rbAntigua.isSelected()) {
                String epoca = cmbEpoca.getValue();
                String valorHistoricoTxt = txtValorHistorico.getText();
                
                if (epoca != null && !epoca.isEmpty()) {
                    ((Antigua) seleccionada).setEpoca(epoca);
                }
                if (valorHistoricoTxt != null && !valorHistoricoTxt.trim().isEmpty()) {
                    ((Antigua) seleccionada).setValor_historico(Integer.parseInt(valorHistoricoTxt.trim()));
                }
            }

            tblMonedas.refresh();
            limpiarCampos();
            mostrarAlerta("Éxito", "Moneda modificada correctamente.");
            
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Los valores numéricos deben ser válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al modificar: " + e.getMessage());
        }
    }

    @FXML
    private void eliminarMoneda(ActionEvent event) {
        Moneda seleccionada = tblMonedas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("¿Está seguro de eliminar la moneda: " + seleccionada.getId_moneda() + "?");
            
            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    listaMonedas.remove(seleccionada);
                    limpiarCampos();
                    mostrarAlerta("Éxito", "Moneda eliminada correctamente.");
                }
            });
        } else {
            mostrarAlerta("Atención", "Seleccione una moneda para eliminar.");
        }
    }

    @FXML
    private void serializarMonedas(ActionEvent event) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(new ArrayList<>(listaMonedas));
            mostrarAlerta("Éxito", "Monedas guardadas en archivo correctamente.");
        } catch (IOException e) {
            mostrarAlerta("Error", "Error al guardar archivo: " + e.getMessage());
        }
    }

    @FXML
    private void deserializarMonedas(ActionEvent event) {
        if (!archivo.exists()) {
            mostrarAlerta("Aviso", "No existe archivo de monedas serializado.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            @SuppressWarnings("unchecked")
            ArrayList<Moneda> leidas = (ArrayList<Moneda>) ois.readObject();
            listaMonedas.setAll(leidas);
            tblMonedas.refresh();
            mostrarAlerta("Éxito", "Monedas cargadas desde archivo correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            mostrarAlerta("Error", "Error al leer archivo: " + e.getMessage());
        }
    }

    @FXML
    private void mostrarSeleccion(MouseEvent event) {
        Moneda seleccionada = tblMonedas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            txtId.setText((String) seleccionada.getId_moneda());
            txtValor.setText(String.valueOf(seleccionada.getValor()));
            txtAnio.setText(String.valueOf(seleccionada.getAnnyo_creacion()));
            
            if (seleccionada.getPais() != null) {
                txtNombrePais.setText(seleccionada.getPais().getNombre());
            }
            
            if (seleccionada.getEspecificacion() != null) {
                cmbMaterial.setValue(seleccionada.getEspecificacion().getMaterial());
                txtPeso.setText(String.valueOf(seleccionada.getEspecificacion().getPeso()));
                txtDiametro.setText(String.valueOf(seleccionada.getEspecificacion().getDiametro()));
            }
            
            if (seleccionada.getConservacion() != null) {
                cmbEstadoConservacion.setValue(seleccionada.getConservacion().getEstado_conservacion());
                chkLimpiada.setSelected(seleccionada.getConservacion().isLimpiada());
            }
            
            if (seleccionada.getCertificacion() != null) {
                chkCertificada.setSelected(!seleccionada.getCertificacion().getEntidad_certificadora().equals("N/A"));
            }
            
            if (seleccionada.getColeccion() != null) {
                chkEnColeccion.setSelected(!seleccionada.getColeccion().getNombre_coleccion().equals("Sin colección"));
            }
            
            // Seleccionar tipo de moneda y cargar campos específicos
            if (seleccionada instanceof Conmemorativa) {
                rbConmemorativa.setSelected(true);
                Conmemorativa conm = (Conmemorativa) seleccionada;
                // CORRECCIÓN: usar getMotivo_conmemoracion() y getAnnio_evento()
                txtMotivoConmemoracion.setText(conm.getMotivo_conmemoracion());
                txtAnioEvento.setText(String.valueOf(conm.getAnnio_evento()));
                
            } else if (seleccionada instanceof Antigua) {
                rbAntigua.setSelected(true);
                Antigua antigua = (Antigua) seleccionada;
                cmbEpoca.setValue(antigua.getEpoca());
                txtValorHistorico.setText(String.valueOf(antigua.getValor_historico()));
            }
        }
    }

    @FXML
    private void limpiarFormulario(ActionEvent event) {
        limpiarCampos();
    }

    // ========= MÉTODOS AUXILIARES =========
    private void limpiarCampos() {
        if (txtId != null) txtId.clear();
        if (txtValor != null) txtValor.clear();
        if (txtAnio != null) txtAnio.clear();
        if (txtNombrePais != null) txtNombrePais.clear();
        if (txtPeso != null) txtPeso.clear();
        if (txtDiametro != null) txtDiametro.clear();
        if (txtDescripcion != null) txtDescripcion.clear();
        if (txtMotivoConmemoracion != null) txtMotivoConmemoracion.clear();
        if (txtAnioEvento != null) txtAnioEvento.clear();
        if (txtValorHistorico != null) txtValorHistorico.clear();
        
        if (cmbTipoMoneda != null) cmbTipoMoneda.setValue(null);
        if (cmbMaterial != null) cmbMaterial.setValue(null);
        if (cmbEstadoConservacion != null) cmbEstadoConservacion.setValue(null);
        if (cmbEpoca != null) cmbEpoca.setValue(null);
        
        if (grupoTipoMoneda != null && rbAntigua != null) {
            rbAntigua.setSelected(true);
        }
        
        if (chkCertificada != null) chkCertificada.setSelected(false);
        if (chkEnColeccion != null) chkEnColeccion.setSelected(false);
        if (chkLimpiada != null) chkLimpiada.setSelected(false);
        
        if (tblMonedas != null) tblMonedas.getSelectionModel().clearSelection();
    }

    private void toggleCamposEspecificos(String tipoMoneda) {
        boolean esConmemorativa = tipoMoneda.equals("Conmemorativa");
        boolean esAntigua = tipoMoneda.equals("Antigua");
        
        // Campos conmemorativos
        if (txtMotivoConmemoracion != null) txtMotivoConmemoracion.setVisible(esConmemorativa);
        if (txtAnioEvento != null) txtAnioEvento.setVisible(esConmemorativa);
        if (lblMotivoConmemoracion != null) lblMotivoConmemoracion.setVisible(esConmemorativa);
        if (lblAnioEvento != null) lblAnioEvento.setVisible(esConmemorativa);
        
        // Campos antiguos
        if (cmbEpoca != null) cmbEpoca.setVisible(esAntigua);
        if (txtValorHistorico != null) txtValorHistorico.setVisible(esAntigua);
        if (lblEpoca != null) lblEpoca.setVisible(esAntigua);
        if (lblValorHistorico != null) lblValorHistorico.setVisible(esAntigua);
        
        // Limpiar campos ocultos
        if (!esConmemorativa) {
            if (txtMotivoConmemoracion != null) txtMotivoConmemoracion.clear();
            if (txtAnioEvento != null) txtAnioEvento.clear();
        }
        if (!esAntigua) {
            if (cmbEpoca != null) cmbEpoca.setValue(null);
            if (txtValorHistorico != null) txtValorHistorico.clear();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}