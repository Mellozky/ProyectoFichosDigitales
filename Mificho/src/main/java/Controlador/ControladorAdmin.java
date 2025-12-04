package Controlador;

import Vista.VistaAdmin;
import Vista.VistaHistorialAdmin;
import javafx.application.Platform;

public class ControladorAdmin {
    private VistaAdmin vista;
    
    public ControladorAdmin(VistaAdmin vista) {
        this.vista = vista;
        inicializar();
    }
    
    private void inicializar() {
        vista.btnCerrarSesion.setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.btnHistorialGlobal.setOnAction(event -> {
            abrirHistorialGlobal();
        });
        
        vista.btnSeleccionarAlmuerzo1.setOnAction(event -> {
            System.out.println("Admin - Seleccionado Almuerzo 1");
        });
        
        vista.btnSeleccionarAlmuerzo2.setOnAction(event -> {
            System.out.println("Admin - Seleccionado Almuerzo 2");
        });
        
        vista.btnSeleccionarBebida1.setOnAction(event -> {
            System.out.println("Admin - Seleccionada Bebida 1");
        });
        
        vista.btnSeleccionarApetitivo1.setOnAction(event -> {
            System.out.println("Admin - Seleccionado Apetitivo 1");
        });
        
        vista.btnEditarAlmuerzo1.setOnAction(event -> {
            System.out.println("Admin - Editar Almuerzo 1");
        });
        
        vista.btnEditarAlmuerzo2.setOnAction(event -> {
            System.out.println("Admin - Editar Almuerzo 2");
        });
        
        vista.btnEditarBebida1.setOnAction(event -> {
            System.out.println("Admin - Editar Bebida 1");
        });
        
        vista.btnEditarApetitivo1.setOnAction(event -> {
            System.out.println("Admin - Editar Apetitivo 1");
        });
    }
    
    private void abrirHistorialGlobal() {
        VistaHistorialAdmin vistaHistorial = new VistaHistorialAdmin();
        new ControladorHistorialAdmin(vistaHistorial);
        vistaHistorial.mostrar();
    }
}