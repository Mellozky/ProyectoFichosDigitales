package Controlador;

import Datos.ServicioDeseados;
import Datos.ServicioCarrito;
import Modelo.ItemDeseado;
import Vista.VistaDeseados;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControladorDeseados {
    private VistaDeseados vista;
    private ServicioDeseados servicioDeseados;
    private ServicioCarrito servicioCarrito;
    private String idEstudiante;
    
    public ControladorDeseados(VistaDeseados vista, String idEstudiante) {
        this.vista = vista;
        this.idEstudiante = idEstudiante;
        this.servicioDeseados = ServicioDeseados.getInstancia();
        this.servicioCarrito = ServicioCarrito.getInstancia();
        inicializar();
    }
    
    private void inicializar() {
        vista.getBtnVolver().setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.getBtnEliminar().setOnAction(event -> {
            eliminarSeleccionado();
        });
        
        vista.getBtnAgregarAlCarrito().setOnAction(event -> {
            agregarAlCarrito();
        });
        
        vista.getBtnVaciar().setOnAction(event -> {
            vaciarLista();
        });
        
        vista.getTablaDeseados().getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    vista.getBtnEliminar().setDisable(false);
                    vista.getBtnAgregarAlCarrito().setDisable(false);
                } else {
                    vista.getBtnEliminar().setDisable(true);
                    vista.getBtnAgregarAlCarrito().setDisable(true);
                }
            }
        );
        
        cargarDeseados();
    }
    
    private void cargarDeseados() {
        Object[] items = servicioDeseados.obtenerDeseadosPorEstudiante(idEstudiante);
        
        ItemDeseado[] itemsArray = new ItemDeseado[items.length];
        for (int i = 0; i < items.length; i++) {
            itemsArray[i] = (ItemDeseado) items[i];
        }
        
        vista.getTablaDeseados().setItems(FXCollections.observableArrayList(itemsArray));
        vista.actualizarTotal(items.length);
    }
    
    private void eliminarSeleccionado() {
        ItemDeseado seleccionado = vista.getTablaDeseados().getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("¿Eliminar de deseados?");
            alert.setContentText(seleccionado.getNombreProducto());
            
            if (alert.showAndWait().get() == ButtonType.OK) {
                if (servicioDeseados.eliminarDeseado(idEstudiante, seleccionado.getProducto().getId())) {
                    cargarDeseados();
                    mostrarMensaje("Producto eliminado de deseados", Alert.AlertType.INFORMATION);
                }
            }
        }
    }
    
    private void agregarAlCarrito() {
        ItemDeseado seleccionado = vista.getTablaDeseados().getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            servicioCarrito.agregarProducto(seleccionado.getProducto(), 1);
            mostrarMensaje("✓ Producto agregado al carrito", Alert.AlertType.INFORMATION);
        }
    }
    
    private void vaciarLista() {
        if (servicioDeseados.contarDeseadosPorEstudiante(idEstudiante) == 0) {
            mostrarMensaje("La lista está vacía", Alert.AlertType.WARNING);
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Vaciar lista de deseados?");
        alert.setContentText("Se eliminarán todos los productos");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            servicioDeseados.vaciarDeseados(idEstudiante);
            cargarDeseados();
            mostrarMensaje("Lista vaciada", Alert.AlertType.INFORMATION);
        }
    }
    
    private void mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}