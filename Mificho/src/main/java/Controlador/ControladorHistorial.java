package Controlador;

import Datos.ServicioFicho;
import Modelo.Ficho;
import Vista.VistaHistorial;
import javafx.collections.FXCollections;

public class ControladorHistorial {
    private VistaHistorial vista;
    private ServicioFicho servicioFicho;
    private String idEstudiante;
    
    public ControladorHistorial(VistaHistorial vista, String idEstudiante) {
        this.vista = vista;
        this.idEstudiante = idEstudiante;
        this.servicioFicho = ServicioFicho.getInstancia();
        inicializar();
    }
    
    private void inicializar() {
        vista.getBtnVolver().setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.getBtnActualizar().setOnAction(event -> {
            cargarHistorial();
        });
        
        cargarHistorial();
    }
    
    private void cargarHistorial() {
        Object[] fichos = servicioFicho.obtenerPorEstudiante(idEstudiante);
        
        Ficho[] fichosArray = new Ficho[fichos.length];
        for (int i = 0; i < fichos.length; i++) {
            fichosArray[i] = (Ficho) fichos[i];
        }
        
        vista.getTablaHistorial().setItems(FXCollections.observableArrayList(fichosArray));
        
        int total = fichos.length;
        int activos = servicioFicho.contarActivosPorEstudiante(idEstudiante);
        int usados = total - activos;
        
        vista.actualizarEstadisticas(total, activos, usados);
    }
}