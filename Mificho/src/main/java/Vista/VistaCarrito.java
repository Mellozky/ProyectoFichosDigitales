package Vista;

import Modelo.ItemCarrito;
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

public class VistaCarrito {
    private Stage stage;
    private TableView<ItemCarrito> tablaCarrito;
    private Button btnVolver;
    private Button btnVaciar;
    private Button btnConfirmarCompra;
    private Button btnEliminar;
    private Label lblTotal;
    private Spinner<Integer> spnCantidad;
    
    public VistaCarrito() {
        this.stage = new Stage();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("🛒 Mi Carrito");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        lblTotal = new Label("Total de productos: 0 | Total items: 0");
        lblTotal.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        lblTotal.setStyle("-fx-text-fill: #666;");
        
        tablaCarrito = new TableView<>();
        tablaCarrito.setPrefHeight(350);
        
        TableColumn<ItemCarrito, String> colNombre = new TableColumn<>("Producto");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colNombre.setPrefWidth(200);
        
        TableColumn<ItemCarrito, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        colTipo.setPrefWidth(120);
        
        TableColumn<ItemCarrito, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaProducto"));
        colFecha.setPrefWidth(120);
        
        TableColumn<ItemCarrito, Integer> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidad.setPrefWidth(100);
        
        tablaCarrito.getColumns().addAll(colNombre, colTipo, colFecha, colCantidad);
        
        Label lblCantidad = new Label("Cantidad:");
        spnCantidad = new Spinner<>(1, 50, 1);
        spnCantidad.setPrefWidth(100);
        spnCantidad.setEditable(true);
        
        btnEliminar = new Button("Eliminar Seleccionado");
        btnEliminar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 13px;");
        btnEliminar.setPrefWidth(160);
        btnEliminar.setDisable(true);
        
        HBox panelCantidad = new HBox(10);
        panelCantidad.setAlignment(Pos.CENTER);
        panelCantidad.setPadding(new Insets(10));
        panelCantidad.getChildren().addAll(lblCantidad, spnCantidad, btnEliminar);
        
        btnVolver = new Button("← Volver");
        btnVolver.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVolver.setPrefWidth(120);
        
        btnVaciar = new Button("🗑️ Vaciar Carrito");
        btnVaciar.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVaciar.setPrefWidth(150);
        
        btnConfirmarCompra = new Button("✓ Confirmar Compra");
        btnConfirmarCompra.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnConfirmarCompra.setPrefWidth(170);
        
        HBox botonesTop = new HBox(15);
        botonesTop.setAlignment(Pos.CENTER_LEFT);
        botonesTop.setPadding(new Insets(15));
        botonesTop.getChildren().addAll(btnVolver, lblTitulo, lblTotal);
        
        HBox botonesBottom = new HBox(15);
        botonesBottom.setAlignment(Pos.CENTER);
        botonesBottom.setPadding(new Insets(15));
        botonesBottom.getChildren().addAll(btnVaciar, btnConfirmarCompra);
        
        VBox contenedorCentral = new VBox(10);
        contenedorCentral.getChildren().addAll(tablaCarrito, panelCantidad);
        
        BorderPane root = new BorderPane();
        root.setTop(botonesTop);
        root.setCenter(contenedorCentral);
        root.setBottom(botonesBottom);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #F5F5F5;");
        
        Scene scene = new Scene(root, 650, 550);
        stage.setScene(scene);
        stage.setTitle("Mi Carrito de Compras");
    }
    
    public TableView<ItemCarrito> getTablaCarrito() {
        return tablaCarrito;
    }
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public Button getBtnVaciar() {
        return btnVaciar;
    }
    
    public Button getBtnConfirmarCompra() {
        return btnConfirmarCompra;
    }
    
    public Button getBtnEliminar() {
        return btnEliminar;
    }
    
    public Spinner<Integer> getSpnCantidad() {
        return spnCantidad;
    }
    
    public void actualizarTotal(int totalProductos, int totalItems) {
        lblTotal.setText(String.format("Total de productos: %d | Total items: %d", 
            totalProductos, totalItems));
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