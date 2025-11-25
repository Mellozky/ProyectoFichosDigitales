/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 *
 * @author maria
 */
public class VistaAdmin {
    private Stage stage;
    private Usuario administrador;
    private Button btnGestionProductos;
    private Button btnGestionFichos;
    private Button btnCerrarSesion;
    
    public VistaAdmin(Stage stage, Usuario administrador) {
        this.stage = stage;
        this.administrador = administrador;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        
        Label lblTitulo = new Label("Panel de Administración");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        
        Label lblBienvenida = new Label("Bienvenido, " + administrador.getNombre());
        lblBienvenida.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        lblBienvenida.setStyle("-fx-text-fill: #666;");
        
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.getChildren().addAll(lblTitulo, lblBienvenida);
        
        btnGestionProductos = crearBotonMenu(
            "Gestión de Productos",
            "Administrar menús del día",
            "#2196F3"
        );
        
        btnGestionFichos = crearBotonMenu(
            "Gestión de Fichos",
            "Crear y administrar fichos de estudiantes",
            "#4CAF50"
        );
        
        Button btnEstadisticas = crearBotonMenu(
            "Estadísticas",
            "Ver reportes y estadísticas del sistema",
            "#FF9800"
        );
        
        VBox menuPrincipal = new VBox(15);
        menuPrincipal.setAlignment(Pos.CENTER);
        menuPrincipal.setPadding(new Insets(20));
        menuPrincipal.getChildren().addAll(
            btnGestionProductos,
            btnGestionFichos,
            btnEstadisticas
        );
        
        btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 12px;");
        btnCerrarSesion.setPrefWidth(150);
        
        HBox footer = new HBox(btnCerrarSesion);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20));
        
        
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(menuPrincipal);
        root.setBottom(footer);
        
        
        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Administrador - Sistema de Fichos");
    }
    
    
    private Button crearBotonMenu(String titulo, String descripcion, String color) {
        VBox contenedor = new VBox(5);
        contenedor.setAlignment(Pos.CENTER);
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lblTitulo.setStyle("-fx-text-fill: white;");
        
        Label lblDescripcion = new Label(descripcion);
        lblDescripcion.setFont(Font.font("Arial", 12));
        lblDescripcion.setStyle("-fx-text-fill: #E0E0E0;");
        
        contenedor.getChildren().addAll(lblTitulo, lblDescripcion);
        
        Button btn = new Button();
        btn.setGraphic(contenedor);
        btn.setPrefSize(400, 80);
        btn.setStyle(
            "-fx-background-color: " + color + "; " +
            "-fx-cursor: hand; " +
            "-fx-background-radius: 10;"
        );
        
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: derive(" + color + ", -10%); " +
            "-fx-cursor: hand; " +
            "-fx-background-radius: 10;"
        ));
        
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: " + color + "; " +
            "-fx-cursor: hand; " +
            "-fx-background-radius: 10;"
        ));
        
        return btn;
    }
    
    public Button getBtnGestionProductos() {
        return btnGestionProductos;
    }
    
    public Button getBtnGestionFichos() {
        return btnGestionFichos;
    }
    
    public Button getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
    
    public void mostrar() {
        stage.show();
    }
    
    public void cerrar() {
        stage.close();
    }
}
 
