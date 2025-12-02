package Controlador;

import Modelo.Usuario;
import Datos.ServicioUsuario;
import Vista.VistaLogin;
import Vista.VistaCatalogo;
import Vista.VistaAdmin;
import javafx.stage.Stage;

public class ControladorLogin {
    private VistaLogin vista;
    private ServicioUsuario servicioUsuario;
    private String usuarioActualId;
    
    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        this.servicioUsuario = ServicioUsuario.getInstancia();
        inicializarEventos();
    }
    
    private void inicializarEventos() {
        vista.getBtnIngresar().setOnAction(event -> iniciarSesion());
        vista.getTxtContraseña().setOnAction(event -> iniciarSesion());
    }
    
    private void iniciarSesion() {
        String usuario = vista.getTxtId().getText().trim();
        String contrasena = vista.getTxtContraseña().getText();
        
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            vista.mostrarMensaje("Por favor ingrese usuario y contraseña", true);
            return;
        }
        
        Usuario user = servicioUsuario.auntenticar(usuario, contrasena);
        
        if (user != null) {
            usuarioActualId = user.getId();
            System.out.println("Login exitoso: " + user.getNombre() + " - " + user.getRol());
            vista.cerrar();
            
            if (user.getRol().equalsIgnoreCase("administrador")) {
                abrirAdmin();
            } else {
                abrirCatalogo();
            }
        } else {
            vista.mostrarMensaje("Usuario o contraseña incorrectos", true);
        }
    }
    
    private void abrirCatalogo() {
        try {
            VistaCatalogo vistaCatalogo = new VistaCatalogo(false);
            new ControladorCatalogo(vistaCatalogo, usuarioActualId);
            vistaCatalogo.mostrar();
            System.out.println("Catálogo abierto correctamente");
        } catch (Exception e) {
            System.err.println("Error al abrir catálogo:");
            e.printStackTrace();
        }
    }
    
    private void abrirAdmin() {
        try {
            VistaAdmin vistaAdmin = new VistaAdmin();
            new ControladorAdmin(vistaAdmin);
            vistaAdmin.mostrar();
            System.out.println("Vista Admin abierta correctamente");
        } catch (Exception e) {
            System.err.println("Error al abrir admin:");
            e.printStackTrace();
        }
    }
}