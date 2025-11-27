/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import Modelo.Producto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author maria
 */
public class VistaSeleccion {
    private Stage stage;
    private Button btnMenu1;
    private Button btnMenu2;
    private Button btnCancelar;
    private Producto menu1;
    private Producto menu2;
    
    public VistaSeleccion(Stage parentStage, Producto menu1, Producto menu2) {
        this.stage = new Stage();
        this.stage.initModality(Modality.WINDOW_MODAL);
        this.stage.initOwner(parentStage);
        this.menu1 = menu1;
        this.menu2 = menu2;
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Label lblTitulo = new Label("🍽️ Selecciona tu Menú del Día");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitulo.setTextAlignment(TextAlignment.CENTER);
        
        Label lblSubtitulo = new Label("Elige una de las dos opciones disponibles");
        lblSubtitulo.setFont(Font.font("Arial", 14));
        lblSubtitulo.setStyle("-fx-text-fill: #666;");
        
        VBox header = new VBox(5);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        header.getChildren().addAll(lblTitulo, lblSubtitulo);
        
        VBox tarjetaMenu1 = crearTarjetaMenu(menu1, "#FF6B6B");
        btnMenu1 = new Button("Seleccionar " + menu1.getNombre());
        btnMenu1.setStyle(
            "-fx-background-color: #FF6B6B; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-cursor: hand;"
        );
        btnMenu1.setPrefWidth(280);
        btnMenu1.setPrefHeight(40);
        
        VBox contenedorMenu1 = new VBox(15);
        contenedorMenu1.setAlignment(Pos.CENTER);
        contenedorMenu1.getChildren().addAll(tarjetaMenu1, btnMenu1);
        
        VBox tarjetaMenu2 = crearTarjetaMenu(menu2, "#4ECDC4");
        btnMenu2 = new Button("Seleccionar " + menu2.getNombre());
        btnMenu2.setStyle(
            "-fx-background-color: #4ECDC4; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-cursor: hand;"
        );
        btnMenu2.setPrefWidth(280);
        btnMenu2.setPrefHeight(40);
        
        VBox contenedorMenu2 = new VBox(15);
        contenedorMenu2.setAlignment(Pos.CENTER);
        contenedorMenu2.getChildren().addAll(tarjetaMenu2, btnMenu2);
        
        // Contenedor de ambos menús
        HBox contenedorMenus = new HBox(30);
        contenedorMenus.setAlignment(Pos.CENTER);
        contenedorMenus.setPadding(new Insets(20));
        contenedorMenus.getChildren().addAll(contenedorMenu1, contenedorMenu2);
        
        // Botón cancelar
        btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white;");
        btnCancelar.setPrefWidth(150);
        
        HBox footer = new HBox(btnCancelar);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        
        // Layout principal
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(contenedorMenus);
        root.setBottom(footer);
        root.setStyle("-fx-background-color: #f5f5f5;");
        
        // Escena
        Scene scene = new Scene(root, 700, 650);
        stage.setScene(scene);
        stage.setTitle("Selección de Menú");
        stage.setResizable(false);
    }
    
   
    private VBox crearTarjetaMenu(Producto producto, String colorTema) {
        VBox tarjeta = new VBox(10);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPadding(new Insets(15));
        tarjeta.setPrefWidth(280);
        tarjeta.setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 10; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
        );
        
        VBox imagenPlaceholder = crearImagenPlaceholder(producto, colorTema);
        
        Label lblNombre = new Label(producto.getNombre());
        lblNombre.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        lblNombre.setWrapText(true);
        lblNombre.setTextAlignment(TextAlignment.CENTER);
        lblNombre.setMaxWidth(250);
        
        Region separador = new Region();
        separador.setPrefHeight(2);
        separador.setMaxWidth(250);
        separador.setStyle("-fx-background-color: " + colorTema + ";");
        
        Label lblDescripcion = new Label(producto.getDescripcion());
        lblDescripcion.setFont(Font.font("Arial", 12));
        lblDescripcion.setWrapText(true);
        lblDescripcion.setTextAlignment(TextAlignment.CENTER);
        lblDescripcion.setMaxWidth(250);
        lblDescripcion.setStyle("-fx-text-fill: #666;");
        
        Label lblTipo = new Label(producto.getTipo().toUpperCase());
        lblTipo.setFont(Font.font("Arial", FontWeight.BOLD, 10));
        lblTipo.setPadding(new Insets(3, 10, 3, 10));
        lblTipo.setStyle(
            "-fx-background-color: " + colorTema + "; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 15;"
        );
        
        tarjeta.getChildren().addAll(
            imagenPlaceholder,
            lblTipo,
            lblNombre,
            separador,
            lblDescripcion
        );
        
        return tarjeta;
    }
    
   
    private VBox crearImagenPlaceholder(Producto producto, String colorBase) {
        VBox contenedor = new VBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPrefSize(250, 180);
        contenedor.setMaxSize(250, 180);
        contenedor.setStyle(
            "-fx-background-color: linear-gradient(to bottom, " + 
            colorBase + ", derive(" + colorBase + ", -20%)); " +
            "-fx-background-radius: 10;"
        );
        
        String emoji = obtenerEmojiPorProducto(producto.getNombre());
        Label lblEmoji = new Label(emoji);
        lblEmoji.setFont(Font.font(80));
        
        Label lblTexto = new Label("📸 Imagen del Plato");
        lblTexto.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        lblTexto.setTextFill(Color.WHITE);
        lblTexto.setStyle("-fx-opacity: 0.8;");
        
        contenedor.getChildren().addAll(lblEmoji, lblTexto);
        
        return contenedor;
    }
    
    
    private String obtenerEmojiPorProducto(String nombre) {
        nombre = nombre.toLowerCase();
        
        if (nombre.contains("pollo")) return "🍗";
        if (nombre.contains("pasta")) return "🍝";
        if (nombre.contains("bandeja")) return "🍛";
        if (nombre.contains("pescado")) return "🐟";
        if (nombre.contains("sancocho")) return "🍲";
        if (nombre.contains("cerdo") || nombre.contains("lomo")) return "🥩";
        if (nombre.contains("arroz")) return "🍚";
        if (nombre.contains("sopa")) return "🍜";
        if (nombre.contains("hamburguesa")) return "🍔";
        if (nombre.contains("pizza")) return "🍕";
        if (nombre.contains("ensalada")) return "🥗";
        
        return "🍽️"; 
    }
    
    public Button getBtnMenu1() {
        return btnMenu1;
    }
    
    public Button getBtnMenu2() {
        return btnMenu2;
    }
    
    public Button getBtnCancelar() {
        return btnCancelar;
    }
    
    public Producto getMenu1() {
        return menu1;
    }
    
    public Producto getMenu2() {
        return menu2;
    }
    
    public void mostrar() {
        stage.showAndWait();
    }
    
    public void cerrar() {
        stage.close();
    }
}
