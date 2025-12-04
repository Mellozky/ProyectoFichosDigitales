package Vista;

import Modelo.Ficho;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaHistorialAdmin {
    private Stage stage;
    private TableView<Ficho> tablaFichos;
    private Button btnVolver;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnFiltrarActivos;
    private Button btnFiltrarUsados;
    private Button btnMostrarTodos;
    private TextField txtBuscarEstudiante;
    private Label lblEstadisticas;
    private ComboBox<String> cmbFiltroEstado;
    
    public VistaHistorialAdmin() {
        this.stage = new Stage();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("Historial Global de Fichos");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        lblEstadisticas = new Label("Total: 0 | Activos: 0 | Usados: 0");
        lblEstadisticas.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        lblEstadisticas.setStyle("-fx-text-fill: #666;");
        
        txtBuscarEstudiante = new TextField();
        txtBuscarEstudiante.setPromptText("🔍 Buscar por ID estudiante...");
        txtBuscarEstudiante.setPrefWidth(200);
        txtBuscarEstudiante.setStyle("-fx-background-radius: 15;");
        
        cmbFiltroEstado = new ComboBox<>();
        cmbFiltroEstado.getItems().addAll("Todos", "Activos", "Usados");
        cmbFiltroEstado.setValue("Todos");
        cmbFiltroEstado.setPrefWidth(120);
        
        tablaFichos = new TableView<>();
        tablaFichos.setPrefHeight(450);
        
        TableColumn<Ficho, String> colId = new TableColumn<>("ID Ficho");
        colId.setCellValueFactory(new PropertyValueFactory<>("idFicho"));
        colId.setPrefWidth(100);
        
        TableColumn<Ficho, String> colEstudiante = new TableColumn<>("ID Estudiante");
        colEstudiante.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        colEstudiante.setPrefWidth(120);
        
        TableColumn<Ficho, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setPrefWidth(100);
        
        TableColumn<Ficho, String> colMenu = new TableColumn<>("Menú Seleccionado");
        colMenu.setCellValueFactory(new PropertyValueFactory<>("menuSeleccionado"));
        colMenu.setPrefWidth(200);
        
        TableColumn<Ficho, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setPrefWidth(100);
        
        tablaFichos.getColumns().addAll(colId, colEstudiante, colFecha, colMenu, colEstado);
        
        btnVolver = new Button("← Volver");
        btnVolver.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVolver.setPrefWidth(120);
        
        btnActualizar = new Button("🔄 Actualizar");
        btnActualizar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        btnActualizar.setPrefWidth(120);
        
        btnEliminar = new Button("🗑️ Eliminar Seleccionado");
        btnEliminar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 13px;");
        btnEliminar.setPrefWidth(170);
        btnEliminar.setDisable(true);
        
        HBox filtros = new HBox(10);
        filtros.setAlignment(Pos.CENTER_LEFT);
        filtros.setPadding(new Insets(10));
        filtros.getChildren().addAll(
            new Label("Filtrar por estado:"), 
            cmbFiltroEstado,
            new Label("  |  "),
            txtBuscarEstudiante
        );
        
        HBox botonesTop = new HBox(15);
        botonesTop.setAlignment(Pos.CENTER_LEFT);
        botonesTop.setPadding(new Insets(15));
        botonesTop.getChildren().addAll(btnVolver, lblTitulo, lblEstadisticas);
        
        HBox botonesBottom = new HBox(15);
        botonesBottom.setAlignment(Pos.CENTER);
        botonesBottom.setPadding(new Insets(15));
        botonesBottom.getChildren().addAll(btnEliminar, btnActualizar);
        
        VBox contenedorCentral = new VBox(10);
        contenedorCentral.getChildren().addAll(filtros, tablaFichos);
        
        BorderPane root = new BorderPane();
        root.setTop(botonesTop);
        root.setCenter(contenedorCentral);
        root.setBottom(botonesBottom);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #F5F5F5;");
        
        Scene scene = new Scene(root, 750, 650);
        stage.setScene(scene);
        stage.setTitle("Historial Global de Fichos - Administrador");
    }
    
    public TableView<Ficho> getTablaFichos() {
        return tablaFichos;
    }
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public Button getBtnActualizar() {
        return btnActualizar;
    }
    
    public Button getBtnEliminar() {
        return btnEliminar;
    }
    
    public TextField getTxtBuscarEstudiante() {
        return txtBuscarEstudiante;
    }
    
    public ComboBox<String> getCmbFiltroEstado() {
        return cmbFiltroEstado;
    }
    
    public void actualizarEstadisticas(int total, int activos, int usados) {
        lblEstadisticas.setText(String.format("Total: %d | Activos: %d | Usados: %d", 
            total, activos, usados));
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