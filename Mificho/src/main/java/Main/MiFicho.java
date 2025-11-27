/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Controlador.ControladorLogin;
import Vista.VistaLogin;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
/**
 *
 * @author maria
 */
public class MiFicho extends Application{
    
     public static void main(String[] args) {
        launch(args);
    }
      
    @Override
    public void start(Stage primaryStage) {
        VistaLogin loginvista = new VistaLogin();
        new ControladorLogin(loginvista);
        loginvista.mostrar();
    }

   
}
