/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Usuario;
import Vista.*;
import javafx.stage.Stage;

/**
 *
 * @author maria
 */
public class ControladorAdmin {
    private VistaAdmin vista;
    private Usuario admin;
    
    
    
    public ControladorAdmin (VistaAdmin vista, Usuario admin){
        this.vista = vista;
        this.admin = admin;
        inicializarEventos();
                
                
    }
            
      private void inicializarEventos() {
        vista.getBtnGestionProductos().setOnAction(e -> abrirGestionProductos());
        vista.getBtnGestionFichos().setOnAction(e -> abrirGestionFichos());
        vista.getBtnCerrarSesion().setOnAction(e -> cerrarSesion());
    }
    
   
    private void abrirGestionProductos() {
        Stage stageProductos = new Stage();
        VistaCatalogo catalogo = new VistaCatalogo(stageProductos);
        ControladorCatalogo catalogocontrolador = new ControladorCatalogo(VistaCatalogo);
        catalogo.mostrar();
    }
    
  
    private void abrirGestionFichos() {
        System.out.println("Abriendo gestión de fichos...");
        
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.INFORMATION
        );
        alert.setTitle("Información");
        alert.setHeaderText("Gestión de Fichos");
        alert.setContentText("Esta funcionalidad se implementará en el próximo commit.");
        alert.showAndWait();
    }
    
   
    private void cerrarSesion() {
        vista.cerrar();
        
        Stage loginStage = new Stage();
        VistaLogin loginvista = new VistaLogin(loginStage);
        ControladorLogin loginControlador = new ControladorLogin(loginvista);
        loginvista.mostrar();
    }
}
