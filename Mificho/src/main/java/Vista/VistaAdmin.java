package Vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaAdmin {

    private Stage stageAdmin;
    public TextField txtBuscar;
    public Button btnSeleccionarAlmuerzo1;
    public Button btnSeleccionarAlmuerzo2;
    public Button btnSeleccionarBebida1;
    public Button btnSeleccionarApetitivo1;
    public Button btnEditarAlmuerzo1;
    public Button btnEditarAlmuerzo2;
    public Button btnEditarBebida1;
    public Button btnEditarApetitivo1;
    public Button btnCerrarSesion;
    public Button btnHistorialGlobal;

    public VistaAdmin() {
        this.stageAdmin = new Stage();
        construirInterfaz();
    }

    private void construirInterfaz() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F5F5;");

        VBox menuLateral = new VBox(15);
        menuLateral.setPrefWidth(200);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setStyle("-fx-background-color: #E1F5D0;");

        Label lblMenu = new Label("ADMIN");
        lblMenu.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ImageView logo = new ImageView();
        try {
            Image imagenLogo = new Image(getClass().getResourceAsStream("/Recursos/logo.png"));
            logo.setImage(imagenLogo);
            logo.setFitHeight(90);
            logo.setPreserveRatio(true);
        } catch (Exception e) {
            logo.setFitHeight(90);
            logo.setFitWidth(90);
        }

        Label seleccionHoy = new Label("Selección de hoy");
        seleccionHoy.setStyle("-fx-background-color: #7ED957; -fx-text-fill: white; -fx-padding: 8 10; -fx-background-radius: 15;");

        VBox opciones = new VBox(6, seleccionHoy);

        btnHistorialGlobal = new Button(" Historial Global");
        btnHistorialGlobal.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 15;");
        btnHistorialGlobal.setPrefWidth(160);

        btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setStyle("-fx-background-color: #7ED957; -fx-text-fill: white; -fx-background-radius: 15;");
        btnCerrarSesion.setPrefWidth(160);

        Region espaciador = new Region();
        VBox.setVgrow(espaciador, Priority.ALWAYS);

        menuLateral.getChildren().addAll(lblMenu, logo, opciones, btnHistorialGlobal, espaciador, btnCerrarSesion);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #F5F5F5; -fx-background-color: #F5F5F5;");

        VBox centro = new VBox(20);
        centro.setPadding(new Insets(20));

        txtBuscar = new TextField();
        txtBuscar.setPromptText("BUSCAR");
        txtBuscar.setPrefWidth(400);
        txtBuscar.setStyle("-fx-background-radius: 20;");

        ImageView imgAlm1 = cargarImagen("/Recursos/Almuerzo1.png", 250);
        ImageView imgAlm2 = cargarImagen("/Recursos/Almuerzo2.png", 250);
        ImageView imgBebida = cargarImagen("/Recursos/Bebida1.png", 200);
        ImageView imgApetitivo = cargarImagen("/Recursos/Apetitivo1.png", 200);

        btnSeleccionarAlmuerzo1 = new Button("Seleccionar");
        btnSeleccionarAlmuerzo2 = new Button("Seleccionar");
        btnSeleccionarBebida1 = new Button("Seleccionar");
        btnSeleccionarApetitivo1 = new Button("Seleccionar");

        estilizarBoton(btnSeleccionarAlmuerzo1);
        estilizarBoton(btnSeleccionarAlmuerzo2);
        estilizarBoton(btnSeleccionarBebida1);
        estilizarBoton(btnSeleccionarApetitivo1);

        btnEditarAlmuerzo1 = new Button("Editar");
        btnEditarAlmuerzo2 = new Button("Editar");
        btnEditarBebida1 = new Button("Editar");
        btnEditarApetitivo1 = new Button("Editar");

        estilizarBoton(btnEditarAlmuerzo1);
        estilizarBoton(btnEditarAlmuerzo2);
        estilizarBoton(btnEditarBebida1);
        estilizarBoton(btnEditarApetitivo1);

        HBox fila1 = new HBox(40);
        fila1.setAlignment(Pos.CENTER);
        fila1.setPadding(new Insets(10));

        VBox alm1Box = new VBox(10);
        alm1Box.setAlignment(Pos.CENTER);
        alm1Box.setPrefWidth(280);
        alm1Box.setPrefHeight(380);
        alm1Box.getChildren().addAll(imgAlm1, btnSeleccionarAlmuerzo1, btnEditarAlmuerzo1);

        VBox alm2Box = new VBox(10);
        alm2Box.setAlignment(Pos.CENTER);
        alm2Box.setPrefWidth(280);
        alm2Box.setPrefHeight(380);
        alm2Box.getChildren().addAll(imgAlm2, btnSeleccionarAlmuerzo2, btnEditarAlmuerzo2);

        fila1.getChildren().addAll(alm1Box, alm2Box);

        HBox fila2 = new HBox(40);
        fila2.setAlignment(Pos.CENTER);
        fila2.setPadding(new Insets(10));

        VBox bebBox = new VBox(10);
        bebBox.setAlignment(Pos.CENTER);
        bebBox.setPrefWidth(280);
        bebBox.setPrefHeight(380);
        bebBox.getChildren().addAll(imgBebida, btnSeleccionarBebida1, btnEditarBebida1);

        VBox apeBox = new VBox(10);
        apeBox.setAlignment(Pos.CENTER);
        apeBox.setPrefWidth(280);
        apeBox.setPrefHeight(380);
        apeBox.getChildren().addAll(imgApetitivo, btnSeleccionarApetitivo1, btnEditarApetitivo1);

        fila2.getChildren().addAll(bebBox, apeBox);

        centro.getChildren().addAll(txtBuscar, fila1, fila2);
        scrollPane.setContent(centro);

        root.setLeft(menuLateral);
        root.setCenter(scrollPane);

        stageAdmin.setScene(new Scene(root, 1000, 900));
        stageAdmin.setTitle("Administrador");
    }

    private ImageView cargarImagen(String ruta, double ancho) {
        ImageView imageView = new ImageView();
        try {
            Image imagen = new Image(getClass().getResourceAsStream(ruta));
            imageView.setImage(imagen);
            imageView.setFitWidth(ancho);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            imageView.setFitWidth(ancho);
            imageView.setFitHeight(ancho * 0.7);
        }
        return imageView;
    }

    private void estilizarBoton(Button btn) {
        btn.setStyle("-fx-background-color: #7ED957; -fx-text-fill: white; -fx-background-radius: 20;");
        btn.setPrefWidth(120);
    }

    public void mostrar() {
        stageAdmin.show();
    }

    public void cerrar() {
        stageAdmin.close();
    }
}