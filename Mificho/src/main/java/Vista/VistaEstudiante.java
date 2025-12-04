package Vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaEstudiante {
    private Stage stage;
    private Button btnCerrarSesion;
    private Button btnSeleccionarAlmuerzo1;
    private Button btnSeleccionarAlmuerzo2;
    private Button btnSeleccionarBebida1;
    private Button btnSeleccionarApetitivo1;
    private Button btnCarrito;
    private Button btnHistorial;
    private Button btnDeseados;
    
    public VistaEstudiante() {
        inicializar();
    }
    
    private void inicializar() {
        stage = new Stage();
        stage.setTitle("Dashboard Estudiante - Sistema de Fichos");
        
        BorderPane root = new BorderPane();
        root.setPrefSize(1000, 700);
        
        VBox menuLateral = crearMenuLateral();
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white; -fx-background-color: white;");
        
        VBox catalogo = crearCatalogo();
        scrollPane.setContent(catalogo);
        
        root.setLeft(menuLateral);
        root.setCenter(scrollPane);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
    }
    
    private VBox crearMenuLateral() {
        VBox menu = new VBox(15);
        menu.setPrefWidth(200);
        menu.setPadding(new Insets(20));
        menu.setAlignment(Pos.TOP_CENTER);
        menu.setStyle("-fx-background-color: #E1F5D0; -fx-border-color: #8BC34A; -fx-border-width: 0 2 0 0;");
        
        Label lblMenu = new Label("MENÚ");
        lblMenu.setFont(Font.font("System", FontWeight.BOLD, 18));
        lblMenu.setTextFill(Color.web("#333333"));
        
        ImageView logo = new ImageView();
        try {
            Image imagenLogo = new Image(getClass().getResourceAsStream("/Recursos/logo.png"));
            logo.setImage(imagenLogo);
            logo.setFitWidth(100);
            logo.setFitHeight(100);
            logo.setPreserveRatio(true);
            menu.getChildren().addAll(lblMenu, logo);
        } catch (Exception e) {
            logo.setFitWidth(100);
            logo.setFitHeight(100);
            menu.getChildren().addAll(lblMenu, logo);
        }
        
        Label seleccionHoy = new Label("Selección de hoy");
        seleccionHoy.setStyle("-fx-background-color: #7ED957; -fx-text-fill: white; -fx-padding: 8 10; -fx-background-radius: 15;");
        
        Label comprar = new Label("Comprar ficho");
        Label cancelar = new Label("Cancelar ficho");
        
        VBox opciones = new VBox(6, seleccionHoy, comprar, cancelar);
        
        btnDeseados = new Button("❤️ Lista de Deseados");
        btnDeseados.setStyle("-fx-background-color: #E91E63; -fx-text-fill: white; -fx-background-radius: 15;");
        btnDeseados.setPrefWidth(160);
        
        btnCarrito = new Button("🛒 Ver Carrito");
        btnCarrito.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-background-radius: 15;");
        btnCarrito.setPrefWidth(160);
        
        btnHistorial = new Button("📋 Ver Historial");
        btnHistorial.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 15;");
        btnHistorial.setPrefWidth(160);
        
        Region espaciador = new Region();
        VBox.setVgrow(espaciador, Priority.ALWAYS);
        
        btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setPrefWidth(160);
        btnCerrarSesion.setPrefHeight(35);
        btnCerrarSesion.setStyle(
            "-fx-background-color: #8BC34A;" +
            "-fx-background-radius: 15;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        
        btnCerrarSesion.setOnMouseEntered(e -> 
            btnCerrarSesion.setStyle(
                "-fx-background-color: #7CB342;" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            )
        );
        
        btnCerrarSesion.setOnMouseExited(e -> 
            btnCerrarSesion.setStyle(
                "-fx-background-color: #8BC34A;" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            )
        );
        
        menu.getChildren().addAll(opciones, btnDeseados, btnCarrito, btnHistorial, espaciador, btnCerrarSesion);
        
        return menu;
    }
    
    private VBox crearCatalogo() {
        VBox catalogo = new VBox(20);
        catalogo.setPadding(new Insets(30));
        catalogo.setAlignment(Pos.TOP_CENTER);
        catalogo.setStyle("-fx-background-color: white;");
        
        Label lblTitulo = new Label("MENÚ DEL DÍA");
        lblTitulo.setPrefWidth(500);
        lblTitulo.setAlignment(Pos.CENTER);
        lblTitulo.setPadding(new Insets(10));
        lblTitulo.setStyle(
            "-fx-background-color: #8BC34A;" +
            "-fx-background-radius: 20;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;"
        );
        
        GridPane gridProductos = new GridPane();
        gridProductos.setHgap(30);
        gridProductos.setVgap(30);
        gridProductos.setAlignment(Pos.CENTER);
        gridProductos.setPadding(new Insets(20));
        
        VBox producto1 = crearProducto("/Recursos/Almuerzo1.png", "Almuerzo 1", 1);
        VBox producto2 = crearProducto("/Recursos/Almuerzo2.png", "Almuerzo 2", 2);
        VBox producto3 = crearProducto("/Recursos/Bebida1.png", "Bebida 1", 3);
        VBox producto4 = crearProducto("/Recursos/Apetitivo1.png", "Apetitivo 1", 4);
        
        gridProductos.add(producto1, 0, 0);
        gridProductos.add(producto2, 1, 0);
        gridProductos.add(producto3, 0, 1);
        gridProductos.add(producto4, 1, 1);
        
        catalogo.getChildren().addAll(lblTitulo, gridProductos);
        
        return catalogo;
    }
    
    private VBox crearProducto(String rutaImagen, String nombre, int index) {
        VBox producto = new VBox(10);
        producto.setAlignment(Pos.CENTER);
        producto.setPrefSize(220, 200);
        producto.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #E0E0E0;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 15;"
        );
        
        ImageView imagen = new ImageView();
        try {
            imagen.setImage(new Image(getClass().getResourceAsStream(rutaImagen)));
            imagen.setFitWidth(180);
            imagen.setFitHeight(120);
            imagen.setPreserveRatio(true);
            producto.getChildren().add(imagen);
        } catch (Exception e) {
            Label placeholder = new Label("Imagen\n" + nombre);
            placeholder.setPrefSize(180, 120);
            placeholder.setAlignment(Pos.CENTER);
            placeholder.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #CCCCCC;");
            producto.getChildren().add(placeholder);
        }
        
        Button btnSeleccionar = new Button("Seleccionar");
        btnSeleccionar.setPrefWidth(150);
        btnSeleccionar.setPrefHeight(35);
        btnSeleccionar.setStyle(
            "-fx-background-color: #8BC34A;" +
            "-fx-background-radius: 15;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        
        btnSeleccionar.setOnMouseEntered(e -> 
            btnSeleccionar.setStyle(
                "-fx-background-color: #7CB342;" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            )
        );
        
        btnSeleccionar.setOnMouseExited(e -> 
            btnSeleccionar.setStyle(
                "-fx-background-color: #8BC34A;" +
                "-fx-background-radius: 15;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
            )
        );
        
        switch (index) {
            case 1: btnSeleccionarAlmuerzo1 = btnSeleccionar; break;
            case 2: btnSeleccionarAlmuerzo2 = btnSeleccionar; break;
            case 3: btnSeleccionarBebida1 = btnSeleccionar; break;
            case 4: btnSeleccionarApetitivo1 = btnSeleccionar; break;
        }
        
        producto.getChildren().add(btnSeleccionar);
        
        return producto;
    }
    
    public Button getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
    
    public Button getBtnSeleccionarAlmuerzo1() {
        return btnSeleccionarAlmuerzo1;
    }
    
    public Button getBtnSeleccionarAlmuerzo2() {
        return btnSeleccionarAlmuerzo2;
    }
    
    public Button getBtnSeleccionarBebida1() {
        return btnSeleccionarBebida1;
    }
    
    public Button getBtnSeleccionarApetitivo1() {
        return btnSeleccionarApetitivo1;
    }
    
    public Button getBtnCarrito() {
        return btnCarrito;
    }
    
    public Button getBtnHistorial() {
        return btnHistorial;
    }
    
    public Button getBtnDeseados() {
        return btnDeseados;
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