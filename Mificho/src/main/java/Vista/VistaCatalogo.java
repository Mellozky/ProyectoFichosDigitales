/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Modelo.Producto;
/**
 *
 * @author maria
 */
public class VistaCatalogo {
      private Stage stage;
    private TableView<Producto> tablaProductos;
    private TextField txtId;
    private TextField txtNombre;
    private ComboBox<String> cmbTipo;
    private DatePicker dpFecha;
    private TextArea txtDescripcion;
    private Button btnAgregar;
    private Button btnEditar;
    private Button btnEliminar;
    private Button btnLimpiar;
    private Button btnVolver;
    private Label lblMensaje;
    
     public VistaCatalogo (Stage stage) {
        this.stage = stage;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("Gestión de Productos / Menús");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        tablaProductos = new TableView<>();
        tablaProductos.setPrefHeight(300);
        
        TableColumn<Producto, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(80);
        
        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(180);
        
        TableColumn<Producto, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTipo.setPrefWidth(100);
        
        TableColumn<Producto, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setPrefWidth(100);
        
        TableColumn<Producto, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colDescripcion.setPrefWidth(250);
        
        tablaProductos.getColumns().addAll(colId, colNombre, colTipo, colFecha, colDescripcion);
        
        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.setPadding(new Insets(20));
        
        Label lblIdForm = new Label("ID:");
        txtId = new TextField();
        txtId.setPromptText("Ej: P001");
        txtId.setDisable(true);
        
        Label lblNombreForm = new Label("Nombre:");
        txtNombre = new TextField();
        txtNombre.setPromptText("Ej: Arroz con Pollo");
        txtNombre.setPrefWidth(200);
        
        Label lblTipoForm = new Label("Tipo:");
        cmbTipo = new ComboBox<>();
        cmbTipo.getItems().addAll("almuerzo1", "almuerzo2");
        cmbTipo.setPromptText("Seleccione tipo");
        cmbTipo.setPrefWidth(200);
        
        Label lblFechaForm = new Label("Fecha:");
        dpFecha = new DatePicker();
        dpFecha.setPromptText("Seleccione fecha");
        dpFecha.setPrefWidth(200);
        
        Label lblDescripcionForm = new Label("Descripción:");
        txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Descripción del menú");
        txtDescripcion.setPrefRowCount(3);
        txtDescripcion.setPrefWidth(200);
        txtDescripcion.setWrapText(true);
        
        formulario.add(lblIdForm, 0, 0);
        formulario.add(txtId, 1, 0);
        formulario.add(lblNombreForm, 0, 1);
        formulario.add(txtNombre, 1, 1);
        formulario.add(lblTipoForm, 0, 2);
        formulario.add(cmbTipo, 1, 2);
        formulario.add(lblFechaForm, 0, 3);
        formulario.add(dpFecha, 1, 3);
        formulario.add(lblDescripcionForm, 0, 4);
        formulario.add(txtDescripcion, 1, 4);
        
        btnAgregar = new Button("Agregar");
        btnAgregar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnAgregar.setPrefWidth(100);
        
        btnEditar = new Button("Editar");
        btnEditar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnEditar.setPrefWidth(100);
        btnEditar.setDisable(true);
        
        btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        btnEliminar.setPrefWidth(100);
        btnEliminar.setDisable(true);
        
        btnLimpiar = new Button("Limpiar");
        btnLimpiar.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
        btnLimpiar.setPrefWidth(100);
        
        HBox botonesAccion = new HBox(10);
        botonesAccion.setAlignment(Pos.CENTER);
        botonesAccion.getChildren().addAll(btnAgregar, btnEditar, btnEliminar, btnLimpiar);
        
        lblMensaje = new Label();
        lblMensaje.setStyle("-fx-font-size: 12px;");
        
        VBox panelFormulario = new VBox(10);
        panelFormulario.setPadding(new Insets(10));
        panelFormulario.getChildren().addAll(formulario, botonesAccion, lblMensaje);
        panelFormulario.setStyle("-fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-background-color: #F5F5F5;");
        
        btnVolver = new Button("← Volver al Panel");
        btnVolver.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white;");
        
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10));
        header.getChildren().addAll(btnVolver, lblTitulo);
        
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(tablaProductos);
        root.setBottom(panelFormulario);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.setTitle("Catálogo de Productos");
    }
    
    public TableView<Producto> getTablaProductos() {
        return tablaProductos;
    }
    
    public TextField getTxtId() {
        return txtId;
    }
    
    public TextField getTxtNombre() {
        return txtNombre;
    }
    
    public ComboBox<String> getCmbTipo() {
        return cmbTipo;
    }
    
    public DatePicker getDpFecha() {
        return dpFecha;
    }
    
    public TextArea getTxtDescripcion() {
        return txtDescripcion;
    }
    
    public Button getBtnAgregar() {
        return btnAgregar;
    }
    
    public Button getBtnEditar() {
        return btnEditar;
    }
    
    public Button getBtnEliminar() {
        return btnEliminar;
    }
    
    public Button getBtnLimpiar() {
        return btnLimpiar;
    }
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        if (esError) {
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
        }
    }
    
    public void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        cmbTipo.setValue(null);
        dpFecha.setValue(null);
        txtDescripcion.clear();
        lblMensaje.setText("");
        btnEditar.setDisable(true);
        btnEliminar.setDisable(true);
        btnAgregar.setDisable(false);
    }
    
    public void mostrar() {
        stage.show();
    }
    
    public void cerrar() {
        stage.close();
    }
}
