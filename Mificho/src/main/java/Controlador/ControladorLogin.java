/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Usuario;
import Datos.ServicioUsuario;
import Vista.VistaLogin;
import Vista.VistaCatalogo;
import Vista.VistaAdmin;
import javafx.stage.Stage;
/**
 *
 * @author maria
 */
public class ControladorLogin {
   private VistaLogin vista;
    private ServicioUsuario servicioUsuario;

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;
        this.servicioUsuario = ServicioUsuario.getInstancia();
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnIngresar().setOnAction(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        String usuario = vista.getTxtId().getText();
        String contrasena = vista.getTxtContraseña().getText();

        Usuario user = servicioUsuario.auntenticar(usuario, contrasena);

        if (user != null) {

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
        Stage stage = new Stage();
        VistaCatalogo vistaCatalogo = new VistaCatalogo(false);
        new ControladorCatalogo(vistaCatalogo);
        vistaCatalogo.mostrar();
    }

    private void abrirAdmin() {
        Stage stage = new Stage();
        VistaAdmin vistaAdmin = new VistaAdmin();
        new ControladorAdmin(vistaAdmin);
        vistaAdmin.mostrar();
    }
}
    
        

