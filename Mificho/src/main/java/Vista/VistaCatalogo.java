package Vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaCatalogo {

    private Stage stageCatalogo;
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
    public Button btnHistorial;
    public Button btnCarrito;
    private boolean esAdmin;
    public Button btnAgregarDeseados1;
public Button btnAgregarDeseados2;
public Button btnAgregarDeseados3;
public Button btnAgregarDeseados4;
public Button btnDeseados;

    public VistaCatalogo(boolean admin) {
        this.esAdmin = admin;
        this.stageCatalogo = new Stage();
        construirInterfaz();
    }

    private void construirInterfaz() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F5F5;");

        VBox menuLateral = new VBox(15);
        menuLateral.setPrefWidth(200);
        menuLateral.setPadding(new Insets(20));
        menuLateral.setStyle("-fx-background-color: #E1F5D0;");

        Label lblMenu = new Label("MENU");
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

        Label comprar = new Label("Comprar ficho");
        Label cancelar = new Label("Cancelar ficho");
        Label deseados = new Label("Lista de deseados");

        VBox opciones = new VBox(6, seleccionHoy, comprar, cancelar, deseados);

        btnCarrito = new Button(" Ver Carrito");
        btnCarrito.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-background-radius: 15;");
        btnCarrito.setPrefWidth(160);

        btnHistorial = new Button(" Ver Historial");
        btnHistorial.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 15;");
        btnHistorial.setPrefWidth(160);

        btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setStyle("-fx-background-color: #7ED957; -fx-text-fill: white; -fx-background-radius: 15;");
        btnCerrarSesion.setPrefWidth(160);

        Region espaciador = new Region();
        VBox.setVgrow(espaciador, Priority.ALWAYS);

        menuLateral.getChildren().addAll(lblMenu, logo, opciones, btnCarrito, btnHistorial, espaciador, btnCerrarSesion);

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
        ImageView imgBebida = cargarImagen("/Recursos/Bebida1.png", 150);
        ImageView imgApetitivo = cargarImagen("/Recursos/Apetitivo1.png", 150);

        btnSeleccionarAlmuerzo1 = new Button("Seleccionar");
        btnSeleccionarAlmuerzo2 = new Button("Seleccionar");
        btnSeleccionarBebida1 = new Button("Seleccionar");
        btnSeleccionarApetitivo1 = new Button("Seleccionar");
        
        btnAgregarDeseados1 = new Button("agregar a deseados");
btnAgregarDeseados2 = new Button("agregar a deseados");
btnAgregarDeseados3 = new Button("agregar a deseados");
btnAgregarDeseados4 = new Button("agregar a deseados");

estilizarBotonCorazon(btnAgregarDeseados1);
estilizarBotonCorazon(btnAgregarDeseados2);
estilizarBotonCorazon(btnAgregarDeseados3);
estilizarBotonCorazon(btnAgregarDeseados4);

        estilizarBotonVerde(btnSeleccionarAlmuerzo1);
        estilizarBotonVerde(btnSeleccionarAlmuerzo2);
        estilizarBotonVerde(btnSeleccionarBebida1);
        estilizarBotonVerde(btnSeleccionarApetitivo1);

        btnEditarAlmuerzo1 = new Button("Editar");
        btnEditarAlmuerzo2 = new Button("Editar");
        btnEditarBebida1 = new Button("Editar");
        btnEditarApetitivo1 = new Button("Editar");

        estilizarBotonVerde(btnEditarAlmuerzo1);
        estilizarBotonVerde(btnEditarAlmuerzo2);
        estilizarBotonVerde(btnEditarBebida1);
        estilizarBotonVerde(btnEditarApetitivo1);

        if (!esAdmin) {
            btnEditarAlmuerzo1.setVisible(false);
            btnEditarAlmuerzo2.setVisible(false);
            btnEditarBebida1.setVisible(false);
            btnEditarApetitivo1.setVisible(false);
        }
        btnDeseados = new Button("️ Lista de Deseados");
btnDeseados.setStyle("-fx-background-color: #E91E63; -fx-text-fill: white; -fx-background-radius: 15;");
btnDeseados.setPrefWidth(160);

menuLateral.getChildren().addAll(lblMenu, logo, opciones, btnDeseados, btnCarrito, btnHistorial, espaciador, btnCerrarSesion);

        HBox fila1 = new HBox(40);
        fila1.setAlignment(Pos.CENTER);

        VBox alm1Box = new VBox(5, imgAlm1, btnSeleccionarAlmuerzo1, btnAgregarDeseados1, btnEditarAlmuerzo1);
        alm1Box.setAlignment(Pos.CENTER);

       VBox alm2Box = new VBox(5, imgAlm2, btnSeleccionarAlmuerzo2, btnAgregarDeseados2, btnEditarAlmuerzo2);

        alm2Box.setAlignment(Pos.CENTER);

        fila1.getChildren().addAll(alm1Box, alm2Box);

        HBox fila2 = new HBox(40);
        fila2.setAlignment(Pos.CENTER);

        VBox bebBox = new VBox(5, imgBebida, btnSeleccionarBebida1, btnAgregarDeseados3, btnEditarBebida1);

        bebBox.setAlignment(Pos.CENTER);

VBox apeBox = new VBox(5, imgApetitivo, btnSeleccionarApetitivo1, btnAgregarDeseados4, btnEditarApetitivo1);
        apeBox.setAlignment(Pos.CENTER);

        fila2.getChildren().addAll(bebBox, apeBox);

        centro.getChildren().addAll(txtBuscar, fila1, fila2);
        scrollPane.setContent(centro);

        root.setLeft(menuLateral);
        root.setCenter(scrollPane);

        stageCatalogo.setScene(new Scene(root, 1000, 700));
        stageCatalogo.setTitle("Catálogo");
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
    private void estilizarBotonCorazon(Button b) {
    b.setStyle("-fx-background-color: #E91E63; -fx-text-fill: white; -fx-background-radius: 20; -fx-font-size: 16px;");
    b.setPrefWidth(50);
}

    private void estilizarBotonVerde(Button b) {
        b.setStyle("-fx-background-color: #7ED957; -fx-text-fill: white; -fx-background-radius: 20;");
        b.setPrefWidth(120);
    }

    public void mostrar() {
        stageCatalogo.show();
    }

    public void cerrar() {
        stageCatalogo.close();
    }
}