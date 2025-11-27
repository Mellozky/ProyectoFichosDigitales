package Controlador;

import Vista.VistaAdmin;
import javafx.application.Platform;

public class ControladorAdmin {
    private VistaAdmin vista;
    
    public ControladorAdmin(VistaAdmin vista) {
        this.vista = vista;
        inicializar();
    }
    
    private void inicializar() {
        // Cerrar sesión - vuelve al login
        vista.btnCerrarSesion.setOnAction(event -> {
            vista.cerrar();
            // Si quieres volver al login, deberías abrirlo aquí
            // new VistaLogin().mostrar();
        });
        
        // Botones de selección
        vista.btnSeleccionarAlmuerzo1.setOnAction(event -> {
            System.out.println("Admin - Seleccionado Almuerzo 1");
            // Aquí irá la lógica para seleccionar almuerzo 1
        });
        
        vista.btnSeleccionarAlmuerzo2.setOnAction(event -> {
            System.out.println("Admin - Seleccionado Almuerzo 2");
            // Aquí irá la lógica para seleccionar almuerzo 2
        });
        
        vista.btnSeleccionarBebida1.setOnAction(event -> {
            System.out.println("Admin - Seleccionada Bebida 1");
            // Aquí irá la lógica para seleccionar bebida
        });
        
        vista.btnSeleccionarApetitivo1.setOnAction(event -> {
            System.out.println("Admin - Seleccionado Apetitivo 1");
            // Aquí irá la lógica para seleccionar apetitivo
        });
        
        // Botones de edición (admin tiene acceso completo)
        vista.btnEditarAlmuerzo1.setOnAction(event -> {
            System.out.println("Admin - Editar Almuerzo 1");
            // Aquí irá la lógica para abrir ventana de edición
        });
        
        vista.btnEditarAlmuerzo2.setOnAction(event -> {
            System.out.println("Admin - Editar Almuerzo 2");
            // Aquí irá la lógica para abrir ventana de edición
        });
        
        vista.btnEditarBebida1.setOnAction(event -> {
            System.out.println("Admin - Editar Bebida 1");
            // Aquí irá la lógica para abrir ventana de edición
        });
        
        vista.btnEditarApetitivo1.setOnAction(event -> {
            System.out.println("Admin - Editar Apetitivo 1");
            // Aquí irá la lógica para abrir ventana de edición
        });
    }
}