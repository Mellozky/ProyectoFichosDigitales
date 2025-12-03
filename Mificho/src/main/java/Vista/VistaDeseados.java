package Vista;

import Modelo.ItemDeseado;
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

public class VistaDeseados {
    private Stage stage;
    private TableView<ItemDeseado> tablaDeseados;
    private Button btnVolver;
    private Button btnEliminar;
    private Button btnAgregarAlCarrito;
    private Button btnVaciar;
    private Label lblTotal;
    private String idEstudiante;
    
    public VistaDeseados(String idEstudiante) {
        this.idEstudiante = idEstudiante;
        this.stage = new Stage();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("❤️ Lista de Deseados");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        lblTotal = new Label("Total de productos: 0");
        lblTotal.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        lblTotal.setStyle("-fx-text-fill: #666;");
        
        tablaDeseados = new TableView<>();
        tablaDeseados.setPrefHeight(350);
        
        TableColumn<ItemDeseado, String> colNombre = new TableColumn<>("Producto");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colNombre.setPrefWidth(180);
        
        TableColumn<ItemDeseado, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        colTipo.setPrefWidth(100);
        
        TableColumn<ItemDeseado, String> colFecha = new TableColumn<>("Fecha Disponible");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaProducto"));
        colFecha.setPrefWidth(120);
        
        TableColumn<ItemDeseado, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        colDescripcion.setPrefWidth(200);
        
        TableColumn<ItemDeseado, String> colAgregado = new TableColumn<>("Agregado");
        colAgregado.setCellValueFactory(new PropertyValueFactory<>("fechaAgregado"));
        colAgregado.setPrefWidth(100);
        
        tablaDeseados.getColumns().addAll(colNombre, colTipo, colFecha, colDescripcion, colAgregado);
        
        btnEliminar = new Button("Eliminar de Deseados");
        btnEliminar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 13px;");
        btnEliminar.setPrefWidth(170);
        btnEliminar.setDisable(true);
        
        btnAgregarAlCarrito = new Button("🛒 Agregar al Carrito");
        btnAgregarAlCarrito.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 13px;");
        btnAgregarAlCarrito.setPrefWidth(170);
        btnAgregarAlCarrito.setDisable(true);
        
        HBox botonesAccion = new HBox(10);
        botonesAccion.setAlignment(Pos.CENTER);
        botonesAccion.setPadding(new Insets(10));
        botonesAccion.getChildren().addAll(btnAgregarAlCarrito, btnEliminar);
        
        btnVolver = new Button("← Volver");
        btnVolver.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVolver.setPrefWidth(120);
        
        btnVaciar = new Button("🗑️ Vaciar Lista");
        btnVaciar.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVaciar.setPrefWidth(150);
        
        HBox botonesTop = new HBox(15);
        botonesTop.setAlignment(Pos.CENTER_LEFT);
        botonesTop.setPadding(new Insets(15));
        botonesTop.getChildren().addAll(btnVolver, lblTitulo, lblTotal);
        
        HBox botonesBottom = new HBox(15);
        botonesBottom.setAlignment(Pos.CENTER);
        botonesBottom.setPadding(new Insets(15));
        botonesBottom.getChildren().addAll(btnVaciar);
        
        VBox contenedorCentral = new VBox(10);
        contenedorCentral.getChildren().addAll(tablaDeseados, botonesAccion);
        
        BorderPane root = new BorderPane();
        root.setTop(botonesTop);
        root.setCenter(contenedorCentral);
        root.setBottom(botonesBottom);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #F5F5F5;");
        
        Scene scene = new Scene(root, 750, 550);
        stage.setScene(scene);
        stage.setTitle("Lista de Deseados - " + idEstudiante);
    }
    
    public TableView<ItemDeseado> getTablaDeseados() {
        return tablaDeseados;
    }
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public Button getBtnEliminar() {
        return btnEliminar;
    }
    
    public Button getBtnAgregarAlCarrito() {
        return btnAgregarAlCarrito;
    }
    
    public Button getBtnVaciar() {
        return btnVaciar;
    }
    
    public String getIdEstudiante() {
        return idEstudiante;
    }
    
    public void actualizarTotal(int total) {
        lblTotal.setText("Total de productos: " + total);
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