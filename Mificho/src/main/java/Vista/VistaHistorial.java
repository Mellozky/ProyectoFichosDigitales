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

public class VistaHistorial {
    private Stage stage;
    private TableView<Ficho> tablaHistorial;
    private Button btnVolver;
    private Button btnActualizar;
    private Label lblEstadisticas;
    private String idEstudiante;
    
    public VistaHistorial(String idEstudiante) {
        this.idEstudiante = idEstudiante;
        this.stage = new Stage();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("Historial de Fichos");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        lblEstadisticas = new Label("Total fichos: 0 | Activos: 0 | Usados: 0");
        lblEstadisticas.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        lblEstadisticas.setStyle("-fx-text-fill: #666;");
        
        tablaHistorial = new TableView<>();
        tablaHistorial.setPrefHeight(400);
        
        TableColumn<Ficho, String> colId = new TableColumn<>("ID Ficho");
        colId.setCellValueFactory(new PropertyValueFactory<>("idFicho"));
        colId.setPrefWidth(100);
        
        TableColumn<Ficho, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setPrefWidth(120);
        
        TableColumn<Ficho, String> colMenu = new TableColumn<>("Menú Seleccionado");
        colMenu.setCellValueFactory(new PropertyValueFactory<>("menuSeleccionado"));
        colMenu.setPrefWidth(200);
        
        TableColumn<Ficho, String> colEstado = new TableColumn<>("Estado");
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colEstado.setPrefWidth(120);
        
        tablaHistorial.getColumns().addAll(colId, colFecha, colMenu, colEstado);
        
        btnVolver = new Button("← Volver");
        btnVolver.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white; -fx-font-size: 14px;");
        btnVolver.setPrefWidth(120);
        
        btnActualizar = new Button("🔄 Actualizar");
        btnActualizar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        btnActualizar.setPrefWidth(120);
        
        HBox botonesTop = new HBox(15);
        botonesTop.setAlignment(Pos.CENTER_LEFT);
        botonesTop.setPadding(new Insets(15));
        botonesTop.getChildren().addAll(btnVolver, lblTitulo, lblEstadisticas);
        
        HBox botonesBottom = new HBox(15);
        botonesBottom.setAlignment(Pos.CENTER);
        botonesBottom.setPadding(new Insets(15));
        botonesBottom.getChildren().addAll(btnActualizar);
        
        BorderPane root = new BorderPane();
        root.setTop(botonesTop);
        root.setCenter(tablaHistorial);
        root.setBottom(botonesBottom);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #F5F5F5;");
        
        Scene scene = new Scene(root, 650, 550);
        stage.setScene(scene);
        stage.setTitle("Historial de Fichos - " + idEstudiante);
    }
    
    public TableView<Ficho> getTablaHistorial() {
        return tablaHistorial;
    }
    
    public Button getBtnVolver() {
        return btnVolver;
    }
    
    public Button getBtnActualizar() {
        return btnActualizar;
    }
    
    public String getIdEstudiante() {
        return idEstudiante;
    }
    
    public void actualizarEstadisticas(int total, int activos, int usados) {
        lblEstadisticas.setText(String.format("Total fichos: %d | Activos: %d | Usados: %d", 
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