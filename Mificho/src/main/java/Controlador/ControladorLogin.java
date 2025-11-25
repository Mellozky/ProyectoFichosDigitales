/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Datos.ServicioUsuario;
import Modelo.Usuario;
import Vista.VistaLogin;
import javafx.stage.Stage;
/**
 *
 * @author maria
 */
public class ControladorLogin {
    private VistaLogin vista;
    private ServicioUsuario usuarioservicio;
    
    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        this.usuarioservicio = ServicioUsuario.getInstancia();
        inicializarEventos();
    }
    private void inicializarEventos() {
        vista.getBtnIngresar().setOnAction(e -> iniciarSesion());
    }
    private void iniciarSesion() {
        String id = vista.getTxtId().getText().trim();
        String contraseña = vista.getTxtContraseña().getText().trim();
        if (id.isEmpty() || contraseña.isEmpty()) {
            vista.mostrarMensaje("Por favor complete todos los campos", true);
            return;
        }
        Usuario usuario = usuarioservicio.auntenticar(id, contraseña);
        
        if (usuario != null) {
            vista.mostrarMensaje("Bienvenido " + usuario.getNombre(), false);
            
        redirigirSegunRol(usuario);
        } else {
            vista.mostrarMensaje("ID o contraseña incorrectos", true);
            vista.getTxtContraseña().clear();
            vista.getTxtId().requestFocus();
        }
        
     }
        private void redirigirSegunRol(Usuario usuario) {
        try {
            Thread.sleep(500);
            
            if (usuario.esAdmin()) {
                abrirDashboardAdministrador(usuario);
            } else if (usuario.esEstudiante()) {
                abrirDashboardEstudiante(usuario);
            }
            
            vista.cerrar();
        } catch (InterruptedException e) {
            e.printStackTrace();
            
           
        }
    }
            private void abrirDashboardAdministrador(Usuario admin) {
        System.out.println("Abriendo dashboard de administrador para: " + admin.getNombre());
        
        Stage stage = new Stage();
        stage.setTitle("Dashboard Administrador - " + admin.getNombre());
        stage.show();
    }
            
            private void abrirDashboardEstudiante(Usuario estudiante) {
        System.out.println("Abriendo dashboard de estudiante para: " + estudiante.getNombre());
        
        Stage stage = new Stage();
        stage.setTitle("Dashboard Estudiante - " + estudiante.getNombre());
        stage.show();
    }
}
    
        

