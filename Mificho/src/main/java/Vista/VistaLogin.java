/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
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
    
    public VistaLogin(Stage stage) {
        this.stage = stage;
        inicializarComponentes();
    }
     private void inicializarComponentes() {
         Label lblTitulo = new Label("Sistema de Gestión de Fichos");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        Label lblSubtitulo = new Label("Iniciar Sesión");
        lblSubtitulo.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
       Label lblId = new Label("ID Usuario:");
        txtId = new TextField();
        txtId.setPromptText("Ej: E001 o A001");
        txtId.setPrefWidth(200);
        
        Label lblContraseña = new Label("Contraseña:");
        txtContraseña = new PasswordField();
        txtContraseña.setPromptText("Ingrese su contraseña");
        txtContraseña.setPrefWidth(200);
        
         btnIngresar = new Button("Ingresar");
        btnIngresar.setPrefWidth(200);
        btnIngresar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        
        lblMensaje = new Label();
        lblMensaje.setStyle("-fx-text-fill: red;");
        
        grid.add(lblId, 0, 0);
        grid.add(txtId, 1, 0);
        grid.add(lblContraseña, 0, 1);
        grid.add(txtContraseña, 1, 1);
        grid.add(btnIngresar, 1, 2);
        grid.add(lblMensaje, 1, 3);
        
         Label lblAyuda = new Label("Usuarios de prueba:\nAdmin: A001 / admin123\nEstudiante: E001 / juan123");
        lblAyuda.setStyle("-fx-font-size: 11px; -fx-text-fill: gray;");
        lblAyuda.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(lblTitulo, lblSubtitulo, grid, lblAyuda);
        
        Scene scene = new Scene(vbox, 500, 450);
        stage.setScene(scene);
        stage.setTitle("Login - Sistema de Fichos");
        stage.setResizable(false);
        
        txtContraseña.setOnAction(e -> btnIngresar.fire());
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
    
    public Label getLblMensaje() {
        return lblMensaje;
    }
    
    public void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        if (esError) {
            lblMensaje.setStyle("-fx-text-fill: red;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: green;");
        }
    }
    
    public void limpiarCampos() {
        txtId.clear();
        txtContraseña.clear();
        lblMensaje.setText("");
    }
    
    public void mostrar() {
        stage.show();
    }
    
    public void cerrar() {
        stage.close();
    }
     
}
