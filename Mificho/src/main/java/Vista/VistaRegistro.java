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

public class VistaRegistro {
    private Stage stage;
    private TextField txtId;
    private TextField txtNombre;
    private PasswordField txtContraseña;
    private PasswordField txtConfirmarContraseña;
    private ComboBox<String> cmbRol;
    private Button btnRegistrar;
    private Button btnCancelar;
    private Label lblMensaje;
    
    public VistaRegistro() {
        inicializar();
    }
    
    private void inicializar() {
        stage = new Stage();
        stage.setTitle("Registro de Usuario");
        
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");
        
        Label lblTitulo = new Label("REGISTRO DE USUARIO");
        lblTitulo.setFont(Font.font("System", FontWeight.BOLD, 24));
        lblTitulo.setTextFill(Color.web("#333333"));
        
        GridPane formulario = new GridPane();
        formulario.setHgap(15);
        formulario.setVgap(15);
        formulario.setAlignment(Pos.CENTER);
        
        Label lblId = new Label("ID Usuario:");
        lblId.setFont(Font.font("System", 14));
        txtId = new TextField();
        txtId.setPromptText("Ej: E006");
        txtId.setPrefWidth(250);
        txtId.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10; -fx-padding: 10;");
        
        Label lblNombre = new Label("Nombre:");
        lblNombre.setFont(Font.font("System", 14));
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtNombre.setPrefWidth(250);
        txtNombre.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10; -fx-padding: 10;");
        
        Label lblContraseña = new Label("Contraseña:");
        lblContraseña.setFont(Font.font("System", 14));
        txtContraseña = new PasswordField();
        txtContraseña.setPromptText("Contraseña");
        txtContraseña.setPrefWidth(250);
        txtContraseña.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10; -fx-padding: 10;");
        
        Label lblConfirmar = new Label("Confirmar:");
        lblConfirmar.setFont(Font.font("System", 14));
        txtConfirmarContraseña = new PasswordField();
        txtConfirmarContraseña.setPromptText("Confirmar contraseña");
        txtConfirmarContraseña.setPrefWidth(250);
        txtConfirmarContraseña.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10; -fx-padding: 10;");
        
        Label lblRol = new Label("Rol:");
        lblRol.setFont(Font.font("System", 14));
        cmbRol = new ComboBox<>();
        cmbRol.getItems().addAll("estudiante", "administrador");
        cmbRol.setValue("estudiante");
        cmbRol.setPrefWidth(250);
        cmbRol.setStyle("-fx-background-color: #F0F0F0; -fx-background-radius: 10;");
        
        formulario.add(lblId, 0, 0);
        formulario.add(txtId, 1, 0);
        formulario.add(lblNombre, 0, 1);
        formulario.add(txtNombre, 1, 1);
        formulario.add(lblContraseña, 0, 2);
        formulario.add(txtContraseña, 1, 2);
        formulario.add(lblConfirmar, 0, 3);
        formulario.add(txtConfirmarContraseña, 1, 3);
        formulario.add(lblRol, 0, 4);
        formulario.add(cmbRol, 1, 4);
        
        btnRegistrar = new Button("REGISTRAR");
        btnRegistrar.setPrefWidth(150);
        btnRegistrar.setPrefHeight(40);
        btnRegistrar.setStyle(
            "-fx-background-color: #4CAF50;" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        
        btnCancelar = new Button("CANCELAR");
        btnCancelar.setPrefWidth(150);
        btnCancelar.setPrefHeight(40);
        btnCancelar.setStyle(
            "-fx-background-color: #f44336;" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        
        HBox botones = new HBox(20);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(btnRegistrar, btnCancelar);
        
        lblMensaje = new Label();
        lblMensaje.setFont(Font.font("System", 12));
        lblMensaje.setWrapText(true);
        lblMensaje.setVisible(false);
        
        root.getChildren().addAll(lblTitulo, formulario, botones, lblMensaje);
        
        Scene scene = new Scene(root, 500, 550);
        stage.setScene(scene);
        stage.setResizable(false);
    }
    
    public TextField getTxtId() {
        return txtId;
    }
    
    public TextField getTxtNombre() {
        return txtNombre;
    }
    
    public PasswordField getTxtContraseña() {
        return txtContraseña;
    }
    
    public PasswordField getTxtConfirmarContraseña() {
        return txtConfirmarContraseña;
    }
    
    public ComboBox<String> getCmbRol() {
        return cmbRol;
    }
    
    public Button getBtnRegistrar() {
        return btnRegistrar;
    }
    
    public Button getBtnCancelar() {
        return btnCancelar;
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
    
    public void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtContraseña.clear();
        txtConfirmarContraseña.clear();
        cmbRol.setValue("estudiante");
        lblMensaje.setVisible(false);
    }
    
    public void mostrar() {
        stage.show();
    }
    
    public void cerrar() {
        stage.close();
    }
    
    public Stage getStage() {
        return stage;
    }
}