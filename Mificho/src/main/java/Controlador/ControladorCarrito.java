package Controlador;

import Datos.ServicioCarrito;
import Datos.ServicioFicho;
import Modelo.ItemCarrito;
import Vista.VistaCarrito;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControladorCarrito {
    private VistaCarrito vista;
    private ServicioCarrito servicioCarrito;
    private ServicioFicho servicioFicho;
    
    public ControladorCarrito(VistaCarrito vista) {
        this.vista = vista;
        this.servicioCarrito = ServicioCarrito.getInstancia();
        this.servicioFicho = ServicioFicho.getInstancia();
        inicializar();
    }
    
    private void inicializar() {
        vista.getBtnVolver().setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.getBtnVaciar().setOnAction(event -> {
            vaciarCarrito();
        });
        
        vista.getBtnConfirmarCompra().setOnAction(event -> {
            confirmarCompra();
        });
        
        vista.getBtnEliminar().setOnAction(event -> {
            eliminarSeleccionado();
        });
        
        vista.getTablaCarrito().getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    vista.getBtnEliminar().setDisable(false);
                    vista.getSpnCantidad().getValueFactory().setValue(newSelection.getCantidad());
                } else {
                    vista.getBtnEliminar().setDisable(true);
                }
            }
        );
        
        cargarCarrito();
    }
    
    private void cargarCarrito() {
        Object[] items = servicioCarrito.obtenerArray();
        
        ItemCarrito[] itemsArray = new ItemCarrito[items.length];
        for (int i = 0; i < items.length; i++) {
            itemsArray[i] = (ItemCarrito) items[i];
        }
        
        vista.getTablaCarrito().setItems(FXCollections.observableArrayList(itemsArray));
        vista.actualizarTotal(servicioCarrito.getCantidadProductos(), servicioCarrito.getTotalItems());
    }
    
    private void vaciarCarrito() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Vaciar carrito?");
        alert.setContentText("Se eliminarán todos los productos del carrito");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            servicioCarrito.vaciarCarrito();
            cargarCarrito();
            mostrarMensaje("Carrito vaciado", Alert.AlertType.INFORMATION);
        }
    }
    
    private void confirmarCompra() {
        if (servicioCarrito.estaVacio()) {
            mostrarMensaje("El carrito está vacío", Alert.AlertType.WARNING);
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Compra");
        alert.setHeaderText("¿Confirmar compra de fichos?");
        alert.setContentText("Se crearán " + servicioCarrito.getTotalItems() + " fichos");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            Object[] items = servicioCarrito.obtenerArray();
            String idEstudiante = servicioCarrito.getIdEstudiante();
            
            for (int i = 0; i < items.length; i++) {
                ItemCarrito item = (ItemCarrito) items[i];
                servicioFicho.crearFichos(idEstudiante, item.getFechaProducto(), item.getCantidad());
            }
            
            servicioCarrito.vaciarCarrito();
            cargarCarrito();
            mostrarMensaje("¡Compra realizada con éxito!", Alert.AlertType.INFORMATION);
            vista.cerrar();
        }
    }
    
    private void eliminarSeleccionado() {
        ItemCarrito seleccionado = vista.getTablaCarrito().getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            servicioCarrito.eliminarProducto(seleccionado.getProducto().getId());
            cargarCarrito();
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