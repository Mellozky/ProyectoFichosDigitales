
package Vista;

import Modelo.Ficho;
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

public class VistaFichos {
    private Stage stage;
    private TableView<Ficho> tablaFichos;
    private TextField txtIdFicho;
    private TextField txtIdEstudiante;
    private DatePicker dpFecha;
    private ComboBox<String> cmbEstado;
    private ComboBox<String> cmbMenuSeleccionado;
    private Spinner<Integer> spnCantidad;
    private Button btnCrearFicho;
    private Button btnCrearMultiples;
    private Button btnEliminar;
    private Button btnLimpiar;
    private Button btnVolver;
    private Label lblMensaje;
    private Label lblEstadisticas;
    
    public VistaFichos(Stage stage) {
        this.stage = stage;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("Gestión de Fichos");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        lblEstadisticas = new Label("Total: 0 | Activos: 0 | Usados: 0");
        lblEstadisticas.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        lblEstadisticas.setStyle("-fx-text-fill: #666;");
        
        tablaFichos = new TableView<>();
        tablaFichos.setPrefHeight(300);
        
        TableColumn<Ficho, String> colId = new TableColumn<>("ID Ficho");
        colId.setCellValueFactory(new PropertyValueFactory<>("idFicho"));
        colId.setPrefWidth(80);
        
        TableColumn<Ficho, String> colEstudiante = new TableColumn<>("ID Estudiante");
        colEstudiante.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        colEstudiante.setPrefWidth(120);
        
        TableColumn<Ficho, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setPrefWidth(100);
        
        TableColumn<Ficho, String> colMenu = new TableColumn<>("Menú Seleccionado");
        colMenu.setCellValueFactory(new PropertyValueFactory<>("menuSeleccionado"));
        colMenu.setPrefWidth(150);
        
        TableColumn<Ficho, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setPrefWidth(100);
        
        tablaFichos.getColumns().addAll(colId, colEstudiante, colFecha, colMenu, colEstado);
        
        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.setPadding(new Insets(20));
        
        Label lblIdFicho = new Label("ID Ficho:");
        txtIdFicho = new TextField();
        txtIdFicho.setPromptText("Auto-generado");
        txtIdFicho.setDisable(true);
        txtIdFicho.setPrefWidth(150);
        
        Label lblIdEstudiante = new Label("ID Estudiante:");
        txtIdEstudiante = new TextField();
        txtIdEstudiante.setPromptText("Ej: E001");
        txtIdEstudiante.setPrefWidth(150);
        
        Label lblFecha = new Label("Fecha:");
        dpFecha = new DatePicker();
        dpFecha.setPromptText("Seleccione fecha");
        dpFecha.setPrefWidth(150);
        
        Label lblEstado = new Label("Estado:");
        cmbEstado = new ComboBox<>();
        cmbEstado.getItems().addAll("activo", "usado");
        cmbEstado.setValue("activo");
        cmbEstado.setDisable(true);
        cmbEstado.setPrefWidth(150);
        
        Label lblMenu = new Label("Menú:");
        cmbMenuSeleccionado = new ComboBox<>();
        cmbMenuSeleccionado.getItems().addAll("Sin seleccionar", "almuerzo1", "almuerzo2");
        cmbMenuSeleccionado.setValue("Sin seleccionar");
        cmbMenuSeleccionado.setDisable(true);
        cmbMenuSeleccionado.setPrefWidth(150);
        
        Label lblCantidad = new Label("Cantidad:");
        spnCantidad = new Spinner<>(1, 50, 1);
        spnCantidad.setPrefWidth(150);
        spnCantidad.setEditable(true);
        
        formulario.add(lblIdFicho, 0, 0);
        formulario.add(txtIdFicho, 1, 0);
        formulario.add(lblIdEstudiante, 0, 1);
        formulario.add(txtIdEstudiante, 1, 1);
        formulario.add(lblFecha, 0, 2);
        formulario.add(dpFecha, 1, 2);
        formulario.add(lblCantidad, 0, 3);
        formulario.add(spnCantidad, 1, 3);
        
        btnCrearFicho = new Button("Crear 1 Ficho");
        btnCrearFicho.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnCrearFicho.setPrefWidth(120);
        
        btnCrearMultiples = new Button("Crear Múltiples");
        btnCrearMultiples.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnCrearMultiples.setPrefWidth(120);
        
        btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        btnEliminar.setPrefWidth(120);
        btnEliminar.setDisable(true);
        
        btnLimpiar = new Button("Limpiar");
        btnLimpiar.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
        btnLimpiar.setPrefWidth(120);
        
        HBox botonesAccion = new HBox(10);
        botonesAccion.setAlignment(Pos.CENTER);
        botonesAccion.getChildren().addAll(btnCrearFicho, btnCrearMultiples, btnEliminar, btnLimpiar);
        
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
        header.getChildren().addAll(btnVolver, lblTitulo, lblEstadisticas);
        
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(tablaFichos);
        root.setBottom(panelFormulario);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 750, 650);
        stage.setScene(scene);
        stage.setTitle("Gestión de Fichos");
    }
    
    public TableView<Ficho> getTablaFichos() {
        return tablaFichos;
    }
    
    public TextField getTxtIdFicho() {
        return txtIdFicho;
    }
    
    public TextField getTxtIdEstudiante() {
        return txtIdEstudiante;
    }
    
    public DatePicker getDpFecha() {
        return dpFecha;
    }
    
    public Spinner<Integer> getSpnCantidad() {
        return spnCantidad;
    }
    
    public Button getBtnCrearFicho() {
        return btnCrearFicho;
    }
    
    public Button getBtnCrearMultiples() {
        return btnCrearMultiples;
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
    
    public void actualizarEstadisticas(int total, int activos, int usados) {
        lblEstadisticas.setText(String.format("Total: %d | Activos: %d | Usados: %d", total, activos, usados));
    }
    
    public void limpiarFormulario() {
        txtIdFicho.clear();
        txtIdEstudiante.clear();
        dpFecha.setValue(null);
        spnCantidad.getValueFactory().setValue(1);
        lblMensaje.setText("");
        btnEliminar.setDisable(true);
    }
    
    public void mostrar() {
        stage.show();
    }
    
    public void cerrar() {
        stage.close();
    }
}