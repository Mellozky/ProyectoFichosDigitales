package Controlador;

import Datos.ServicioCarrito;
import Datos.ServicioProducto;
import Datos.ServicioDeseados;
import Modelo.Producto;
import Vista.VistaEstudiante;
import Vista.VistaHistorial;
import Vista.VistaCarrito;
import Vista.VistaDeseados;
import javafx.scene.control.Alert;
import Vista.VistaCatalogo;

public class ControladorEstudiante {
    private VistaEstudiante vista;
    private String idEstudianteActual;
    private ServicioCarrito servicioCarrito;
    private ServicioProducto servicioProducto;
    private ServicioDeseados servicioDeseados;
    
    public ControladorEstudiante(VistaEstudiante vista, String idEstudiante) {
        this.vista = vista;
        this.idEstudianteActual = idEstudiante;
        this.servicioCarrito = ServicioCarrito.getInstancia();
        this.servicioProducto = ServicioProducto.getInstancia();
        this.servicioDeseados = ServicioDeseados.getInstancia();
        servicioCarrito.setIdEstudiante(idEstudiante);
        inicializar();
    }
    
    private void inicializar() {
        vista.getBtnCerrarSesion().setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.getBtnCarrito().setOnAction(event -> {
            abrirCarrito();
        });
        
        vista.getBtnHistorial().setOnAction(event -> {
            abrirHistorial();
        });
        
        vista.getBtnDeseados().setOnAction(event -> {
            abrirDeseados();
        });
        
        vista.getBtnSeleccionarAlmuerzo1().setOnAction(event -> {
            agregarAlCarrito("almuerzo1");
        });
        
        vista.getBtnSeleccionarAlmuerzo2().setOnAction(event -> {
            agregarAlCarrito("almuerzo2");
        });
        
        vista.getBtnSeleccionarBebida1().setOnAction(event -> {
            System.out.println("Seleccionada Bebida 1");
        });
        
        vista.getBtnSeleccionarApetitivo1().setOnAction(event -> {
            System.out.println("Seleccionado Apetitivo 1");
        });
    }
    
    private void agregarAlCarrito(String tipoMenu) {
        String fechaHoy = servicioProducto.obtenerFechaHoy();
        Producto producto = servicioProducto.obtenerMenuDelDia(fechaHoy, tipoMenu);
        
        if (producto != null) {
            servicioCarrito.agregarProducto(producto, 1);
            mostrarMensaje("✓ Producto agregado al carrito", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("⚠ No hay menú disponible para hoy", Alert.AlertType.WARNING);
        }
    }
    
    public void abrirHistorial() {
        VistaHistorial vistaHistorial = new VistaHistorial(idEstudianteActual);
        new ControladorHistorial(vistaHistorial, idEstudianteActual);
        vistaHistorial.mostrar();
    }
    
    public void abrirCarrito() {
        VistaCarrito vistaCarrito = new VistaCarrito();
        new ControladorCarrito(vistaCarrito);
        vistaCarrito.mostrar();
    }
    
    public void abrirDeseados() {
        VistaDeseados vistaDeseados = new VistaDeseados(idEstudianteActual);
        new ControladorDeseados(vistaDeseados, idEstudianteActual);
        vistaDeseados.mostrar();
    }
    
    private void mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}