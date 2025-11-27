/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author PC
 */
public class VistaLogin {
private Stage stage;
    private TextField txtId;
    private PasswordField txtContraseña;
    private Button btnIngresar;
    private Label lblMensaje;
    private Label lblOlvideContraseña;
    
    public VistaLogin() {
        inicializar();
    }
    
    private void inicializar() {
        stage = new Stage();
        stage.setTitle("Login - Sistema de Fichos");
        
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 500);
        
        VBox panelIzquierdo = crearPanelIzquierdo();
        
        VBox panelDerecho = crearPanelDerecho();
        
        root.setLeft(panelIzquierdo);
        root.setCenter(panelDerecho);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
    }
    
    private VBox crearPanelIzquierdo() {
        VBox panel = new VBox();
        panel.setPrefWidth(400);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: #8BC34A;");
        
        try {
            ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/recursos/logo.png")));
            logo.setFitWidth(250);
            logo.setFitHeight(250);
            logo.setPreserveRatio(true);
            panel.getChildren().add(logo);
        } catch (Exception e) {
            Label placeholder = new Label("MI FICHO");
            placeholder.setFont(Font.font("System", FontWeight.BOLD, 48));
            placeholder.setTextFill(Color.WHITE);
            panel.getChildren().add(placeholder);
        }
        
        return panel;
    }
    
    private VBox crearPanelDerecho() {
        VBox panel = new VBox(20);
        panel.setPrefWidth(400);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(40, 60, 40, 60));
        panel.setStyle("-fx-background-color: white;");
        
        Label lblTitulo = new Label("INGRESAR");
        lblTitulo.setFont(Font.font("System", FontWeight.BOLD, 24));
        lblTitulo.setTextFill(Color.web("#333333"));
        
        Label lblSubtitulo = new Label("Iniciar Sesión");
        lblSubtitulo.setFont(Font.font("System", 14));
        lblSubtitulo.setTextFill(Color.web("#666666"));
        
        Region espaciador1 = new Region();
        espaciador1.setPrefHeight(20);
        
        txtId = new TextField();
        txtId.setPromptText("Usuario");
        txtId.setPrefHeight(45);
        txtId.setStyle(
            "-fx-background-color: #8BC34A;" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-prompt-text-fill: rgba(255, 255, 255, 0.7);" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 0 20 0 20;"
        );
        
        // Campo Contraseña
        txtContraseña = new PasswordField();
        txtContraseña.setPromptText("Contraseña");
        txtContraseña.setPrefHeight(45);
        txtContraseña.setStyle(
            "-fx-background-color: #8BC34A;" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-prompt-text-fill: rgba(255, 255, 255, 0.7);" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 0 20 0 20;"
        );
        
        lblOlvideContraseña = new Label("Olvidé mi contraseña");
        lblOlvideContraseña.setFont(Font.font("System", 11));
        lblOlvideContraseña.setTextFill(Color.web("#999999"));
        lblOlvideContraseña.setStyle("-fx-cursor: hand;");
        lblOlvideContraseña.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(lblOlvideContraseña, new Insets(0, 5, 0, 0));
        
        Region espaciador2 = new Region();
        espaciador2.setPrefHeight(10);
        
        btnIngresar = new Button("INGRESAR");
        btnIngresar.setPrefWidth(200);
        btnIngresar.setPrefHeight(40);
        btnIngresar.setStyle(
            "-fx-background-color: #8BC34A;" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        
        btnIngresar.setOnMouseEntered(e -> 
            btnIngresar.setStyle(
                "-fx-background-color: #7CB342;" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            )
        );
        
        btnIngresar.setOnMouseExited(e -> 
            btnIngresar.setStyle(
                "-fx-background-color: #8BC34A;" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            )
        );
        
        Region espaciador3 = new Region();
        espaciador3.setPrefHeight(30);
        
        VBox usuariosPrueba = new VBox(5);
        usuariosPrueba.setAlignment(Pos.CENTER);
        
        Label lblUsuariosPrueba = new Label("Usuarios de prueba:");
        lblUsuariosPrueba.setFont(Font.font("System", 12));
        lblUsuariosPrueba.setTextFill(Color.web("#666666"));
        
        Label lblAdmin = new Label("Admin: A001 / admin123");
        lblAdmin.setFont(Font.font("System", 11));
        lblAdmin.setTextFill(Color.web("#999999"));
        
        Label lblEstudiante = new Label("Estudiante: E001 / juan123");
        lblEstudiante.setFont(Font.font("System", 11));
        lblEstudiante.setTextFill(Color.web("#999999"));
        
        usuariosPrueba.getChildren().addAll(lblUsuariosPrueba, lblAdmin, lblEstudiante);
        
        lblMensaje = new Label();
        lblMensaje.setFont(Font.font("System", 12));
        lblMensaje.setWrapText(true);
        lblMensaje.setVisible(false);
        
        panel.getChildren().addAll(
            lblTitulo,
            lblSubtitulo,
            espaciador1,
            txtId,
            txtContraseña,
            lblOlvideContraseña,
            espaciador2,
            btnIngresar,
            espaciador3,
            usuariosPrueba,
            lblMensaje
        );
        
        return panel;
    }
    
    public TextField getTxtId() {
        return txtId;
    }
    
    public PasswordField getTxtContraseña() {
        return txtContraseña;
    }
    
    public Button getBtnIngresar() {
        return btnIngresar;
    }
    
    public Label getLblOlvideContraseña() {
        return lblOlvideContraseña;
    }
    
    public void mostrar() {
        stage.show();
    }
    
    public void cerrar() {
        stage.close();
    }
    
    public void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        lblMensaje.setVisible(true);
        
        if (esError) {
            lblMensaje.setTextFill(Color.web("#d32f2f"));
        } else {
            lblMensaje.setTextFill(Color.web("#4CAF50"));
        }
    }
    
    public Stage getStage() {
        return stage;
    }
     
}
