package Datos;

import Modelo.Producto;
import Modelo.ItemCarrito;
import Utils.ListaEnlazada;

public class ServicioCarrito {
    private ListaEnlazada<ItemCarrito> items;
    private static ServicioCarrito instancia;
    private String idEstudianteActual;
    
    private ServicioCarrito() {
        items = new ListaEnlazada<>();
    }
    
    public static ServicioCarrito getInstancia() {
        if (instancia == null) {
            instancia = new ServicioCarrito();
        }
        return instancia;
    }
    
    public void setIdEstudiante(String id) {
        this.idEstudianteActual = id;
    }
    
    public String getIdEstudiante() {
        return idEstudianteActual;
    }
    
    public boolean agregarProducto(Producto producto, int cantidad) {
        ItemCarrito existente = items.buscar(item -> 
            item.getProducto().getId().equals(producto.getId())
        );
        
        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
            return items.actualizar(
                item -> item.getProducto().getId().equals(producto.getId()),
                existente
            );
        } else {
            ItemCarrito nuevoItem = new ItemCarrito(producto, cantidad);
            items.agregar(nuevoItem);
            return true;
        }
    }
    
    public boolean eliminarProducto(String idProducto) {
        ItemCarrito item = items.buscar(i -> i.getProducto().getId().equals(idProducto));
        if (item != null) {
            return items.eliminar(item);
        }
        return false;
    }
    
    public boolean actualizarCantidad(String idProducto, int nuevaCantidad) {
        ItemCarrito item = items.buscar(i -> i.getProducto().getId().equals(idProducto));
        if (item != null) {
            if (nuevaCantidad <= 0) {
                return items.eliminar(item);
            }
            item.setCantidad(nuevaCantidad);
            return items.actualizar(
                i -> i.getProducto().getId().equals(idProducto),
                item
            );
        }
        return false;
    }
    
    public ListaEnlazada<ItemCarrito> obtenerTodos() {
        return items;
    }
    
    public Object[] obtenerArray() {
        return items.arreglo();
    }
    
    public int getTotalItems() {
        int total = 0;
        for (int i = 0; i < items.tamaño(); i++) {
            total += items.obtener(i).getCantidad();
        }
        return total;
    }
    
    public int getCantidadProductos() {
        return items.tamaño();
    }
    
    public void vaciarCarrito() {
        items.limpiar();
    }
    
    public boolean estaVacio() {
        return items.estaVacia();
    }
}