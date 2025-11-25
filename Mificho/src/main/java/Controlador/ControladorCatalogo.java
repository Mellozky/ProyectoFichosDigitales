/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Datos.ServicioProducto;
import Modelo.Producto;
import Vista.VistaCatalogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
/**
 *
 * @author maria
 */
public class ControladorCatalogo {
    private VistaCatalogo vista;
    private ServicioProducto productoservicio;
    private ObservableList<Producto> listaProductos;
    
    public ControladorCatalogo(VistaCatalogo vista) {
        this.vista = vista;
        this.productoservicio = productoservicio.getInstancia();
        this.listaProductos = FXCollections.observableArrayList();
        inicializarEventos();
        cargarProductos();
    }
    
    private void inicializarEventos() {
        vista.getBtnAgregar().setOnAction(e -> agregarProducto());
        vista.getBtnEditar().setOnAction(e -> editarProducto());
        vista.getBtnEliminar().setOnAction(e -> eliminarProducto());
        vista.getBtnLimpiar().setOnAction(e -> vista.limpiarFormulario());
        vista.getBtnVolver().setOnAction(e -> vista.cerrar());
        
        vista.getTablaProductos().getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    cargarProductoEnFormulario(newValue);
                }
            }
        );
    }
    
    
    private void cargarProductos() {
        listaProductos.clear();
        
        Object[] productos = productoservicio.obtenerTodos().arreglo();
        for (Object obj : productos) {
            if (obj instanceof Producto) {
                listaProductos.add((Producto) obj);
            }
        }
        
        vista.getTablaProductos().setItems(listaProductos);
    }
    
    
    private void cargarProductoEnFormulario(Producto producto) {
        vista.getTxtId().setText(producto.getId());
        vista.getTxtNombre().setText(producto.getNombre());
        vista.getCmbTipo().setValue(producto.getTipo());
        vista.getTxtDescripcion().setText(producto.getDescripcion());
        
        try {
            LocalDate fecha = LocalDate.parse(producto.getFecha());
            vista.getDpFecha().setValue(fecha);
        } catch (Exception e) {
            vista.getDpFecha().setValue(null);
        }
        
        vista.getBtnEditar().setDisable(false);
        vista.getBtnEliminar().setDisable(false);
        vista.getBtnAgregar().setDisable(true);
    }
    
    
    private void agregarProducto() {
        if (!validarCampos()) {
            return;
        }
        
        String id = productoservicio.generarIdProducto();
        String nombre = vista.getTxtNombre().getText().trim();
        String tipo = vista.getCmbTipo().getValue();
        String fecha = vista.getDpFecha().getValue().toString();
        String descripcion = vista.getTxtDescripcion().getText().trim();
        
        Producto nuevoProducto = new Producto(id, nombre, tipo, fecha, descripcion);
        
        if (productoservicio.crearProducto(nuevoProducto)) {
            vista.mostrarMensaje("Producto creado exitosamente", false);
            cargarProductos();
            vista.limpiarFormulario();
        } else {
            vista.mostrarMensaje("Error al crear el producto", true);
        }
    }
    
   
    private void editarProducto() {
        if (!validarCampos()) {
            return;
        }
        
        String id = vista.getTxtId().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String tipo = vista.getCmbTipo().getValue();
        String fecha = vista.getDpFecha().getValue().toString();
        String descripcion = vista.getTxtDescripcion().getText().trim();
        
        Producto productoActualizado = new Producto(id, nombre, tipo, fecha, descripcion);
        
        if (productoservicio.actualizarProducto(productoActualizado)) {
            vista.mostrarMensaje("Producto actualizado exitosamente", false);
            cargarProductos();
            vista.limpiarFormulario();
        } else {
            vista.mostrarMensaje("Error al actualizar el producto", true);
        }
    }
    
    
    private void eliminarProducto() {
        String id = vista.getTxtId().getText().trim();
        
        if (id.isEmpty()) {
            vista.mostrarMensaje("Seleccione un producto para eliminar", true);
            return;
        }
        
        javafx.scene.control.Alert confirmacion = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.CONFIRMATION
        );
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Eliminar producto?");
        confirmacion.setContentText("¿Está seguro de eliminar el producto " + id + "?");
        
        confirmacion.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                if (productoservicio.eliminarProducto(id)) {
                    vista.mostrarMensaje("Producto eliminado exitosamente", false);
                    cargarProductos();
                    vista.limpiarFormulario();
                } else {
                    vista.mostrarMensaje("Error al eliminar el producto", true);
                }
            }
        });
    }
    
        
    private boolean validarCampos() {
        if (vista.getTxtNombre().getText().trim().isEmpty()) {
            vista.mostrarMensaje("El nombre es obligatorio", true);
            return false;
        }
        
        if (vista.getCmbTipo().getValue() == null) {
            vista.mostrarMensaje("Debe seleccionar un tipo", true);
            return false;
        }
        
        if (vista.getDpFecha().getValue() == null) {
            vista.mostrarMensaje("Debe seleccionar una fecha", true);
            return false;
        }
        
        if (vista.getTxtDescripcion().getText().trim().isEmpty()) {
            vista.mostrarMensaje("La descripción es obligatoria", true);
            return false;
        }
        
        return true;
    }
}
