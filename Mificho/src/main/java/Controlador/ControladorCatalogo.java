package Controlador;

import Datos.ServicioUsuario;
import Vista.VistaCatalogo;
import Vista.VistaHistorial;
import javafx.application.Platform;

public class ControladorCatalogo {
    private VistaCatalogo vista;
    private String idEstudianteActual;
    
    public ControladorCatalogo(VistaCatalogo vista, String idEstudiante) {
        this.vista = vista;
        this.idEstudianteActual = idEstudiante;
        inicializar();
    }
    
    private void inicializar() {
        vista.btnCerrarSesion.setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.btnSeleccionarAlmuerzo1.setOnAction(event -> {
            System.out.println("Seleccionado Almuerzo 1");
        });
        
        vista.btnSeleccionarAlmuerzo2.setOnAction(event -> {
            System.out.println("Seleccionado Almuerzo 2");
        });
        
        vista.btnSeleccionarBebida1.setOnAction(event -> {
            System.out.println("Seleccionada Bebida 1");
        });
        
        vista.btnSeleccionarApetitivo1.setOnAction(event -> {
            System.out.println("Seleccionado Apetitivo 1");
        });
        
        vista.btnEditarAlmuerzo1.setOnAction(event -> {
            System.out.println("Editar Almuerzo 1");
        });
        
        vista.btnEditarAlmuerzo2.setOnAction(event -> {
            System.out.println("Editar Almuerzo 2");
        });
        
        vista.btnEditarBebida1.setOnAction(event -> {
            System.out.println("Editar Bebida 1");
        });
        
        vista.btnEditarApetitivo1.setOnAction(event -> {
            System.out.println("Editar Apetitivo 1");
        });
    }
    
    public void abrirHistorial() {
        VistaHistorial vistaHistorial = new VistaHistorial(idEstudianteActual);
        new ControladorHistorial(vistaHistorial, idEstudianteActual);
        vistaHistorial.mostrar();
    }
}