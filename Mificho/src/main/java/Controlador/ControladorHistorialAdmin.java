package Controlador;

import Datos.ServicioFicho;
import Modelo.Ficho;
import Vista.VistaHistorialAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControladorHistorialAdmin {
    private VistaHistorialAdmin vista;
    private ServicioFicho servicioFicho;
    
    public ControladorHistorialAdmin(VistaHistorialAdmin vista) {
        this.vista = vista;
        this.servicioFicho = ServicioFicho.getInstancia();
        inicializar();
    }
    
    private void inicializar() {
        vista.getBtnVolver().setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.getBtnActualizar().setOnAction(event -> {
            cargarFichos();
        });
        
        vista.getBtnEliminar().setOnAction(event -> {
            eliminarFicho();
        });
        
        vista.getTablaFichos().getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                vista.getBtnEliminar().setDisable(newSelection == null);
            }
        );
        
        vista.getCmbFiltroEstado().setOnAction(event -> {
            aplicarFiltros();
        });
        
        vista.getTxtBuscarEstudiante().textProperty().addListener((obs, oldText, newText) -> {
            aplicarFiltros();
        });
        
        cargarFichos();
    }
    
    private void cargarFichos() {
        Object[] fichos = servicioFicho.obtenerTodos().arreglo();
        
        Ficho[] fichosArray = new Ficho[fichos.length];
        for (int i = 0; i < fichos.length; i++) {
            fichosArray[i] = (Ficho) fichos[i];
        }
        
        vista.getTablaFichos().setItems(FXCollections.observableArrayList(fichosArray));
        
        int total = servicioFicho.obtenerTotalFichos();
        int activos = servicioFicho.obtenerTotalActivos();
        int usados = servicioFicho.obtenerTotalUsados();
        
        vista.actualizarEstadisticas(total, activos, usados);
    }
    
    private void aplicarFiltros() {
        Object[] todosFichos = servicioFicho.obtenerTodos().arreglo();
        ObservableList<Ficho> fichosFiltrados = FXCollections.observableArrayList();
        
        String filtroEstado = vista.getCmbFiltroEstado().getValue();
        String buscarEstudiante = vista.getTxtBuscarEstudiante().getText().trim().toLowerCase();
        
        for (int i = 0; i < todosFichos.length; i++) {
            Ficho ficho = (Ficho) todosFichos[i];
            boolean cumpleFiltro = true;
            
            if (filtroEstado.equals("Activos") && !ficho.estaActivo()) {
                cumpleFiltro = false;
            } else if (filtroEstado.equals("Usados") && !ficho.estaUsado()) {
                cumpleFiltro = false;
            }
            
            if (!buscarEstudiante.isEmpty() && 
                !ficho.getIdEstudiante().toLowerCase().contains(buscarEstudiante)) {
                cumpleFiltro = false;
            }
            
            if (cumpleFiltro) {
                fichosFiltrados.add(ficho);
            }
        }
        
        vista.getTablaFichos().setItems(fichosFiltrados);
        vista.actualizarEstadisticas(
            fichosFiltrados.size(),
            contarActivos(fichosFiltrados),
            contarUsados(fichosFiltrados)
        );
    }
    
    private int contarActivos(ObservableList<Ficho> fichos) {
        int contador = 0;
        for (Ficho f : fichos) {
            if (f.estaActivo()) contador++;
        }
        return contador;
    }
    
    private int contarUsados(ObservableList<Ficho> fichos) {
        int contador = 0;
        for (Ficho f : fichos) {
            if (f.estaUsado()) contador++;
        }
        return contador;
    }
    
    private void eliminarFicho() {
        Ficho seleccionado = vista.getTablaFichos().getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            return;
        }
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Eliminar este ficho?");
        confirmacion.setContentText(
            "ID Ficho: " + seleccionado.getIdFicho() + "\n" +
            "Estudiante: " + seleccionado.getIdEstudiante() + "\n" +
            "Estado: " + seleccionado.getEstado()
        );
        
        if (confirmacion.showAndWait().get() == ButtonType.OK) {
            if (servicioFicho.eliminarFicho(seleccionado.getIdFicho())) {
                mostrarMensaje("✓ Ficho eliminado correctamente", Alert.AlertType.INFORMATION);
                cargarFichos();
            } else {
                mostrarMensaje("Error al eliminar el ficho", Alert.AlertType.ERROR);
            }
        }
    }
    
    private void mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}