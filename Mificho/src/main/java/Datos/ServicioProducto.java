/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Modelo.Producto;
import Utils.ListaEnlazada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author maria
 */
public class ServicioProducto {
     private ListaEnlazada<Producto> productos;
    private static ServicioProducto instancia;
    private DateTimeFormatter formatter;
    
    private ServicioProducto() {
        productos = new ListaEnlazada<>();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        inicializarProductos();
    }
    
    public static ServicioProducto getInstancia() {
        if (instancia == null) {
            instancia = new ServicioProducto();
        }
        return instancia;
    }
    
    
    private void inicializarProductos() {
        String hoy = LocalDate.now().format(formatter);
        String manana = LocalDate.now().plusDays(1).format(formatter);
        
        productos.agregar(new Producto(
            "P001",
            "Arroz con Pollo",
            "almuerzo1",
            hoy,
            "Arroz, pollo guisado, ensalada, jugo natural"
        ));
        
        productos.agregar(new Producto(
            "P002",
            "Pasta Boloñesa",
            "almuerzo2",
            hoy,
            "Pasta con salsa boloñesa, pan, ensalada, refresco"
        ));
        
        productos.agregar(new Producto(
            "P003",
            "Bandeja Paisa",
            "almuerzo1",
            manana,
            "Frijoles, arroz, carne, chicharrón, huevo, aguacate"
        ));
        
        productos.agregar(new Producto(
            "P004",
            "Pescado Frito",
            "almuerzo2",
            manana,
            "Pescado frito, arroz de coco, patacones, ensalada"
        ));
        
        productos.agregar(new Producto(
            "P005",
            "Sancocho de Gallina",
            "almuerzo1",
            LocalDate.now().plusDays(2).format(formatter),
            "Sancocho con yuca, plátano, arroz, aguacate"
        ));
        
        productos.agregar(new Producto(
            "P006",
            "Lomo de Cerdo",
            "almuerzo2",
            LocalDate.now().plusDays(2).format(formatter),
            "Lomo de cerdo, papas fritas, ensalada césar"
        ));
    }
    
   
    public boolean crearProducto(Producto producto) {
        Producto existente = productos.buscar(p -> p.getId().equals(producto.getId()));
        
        if (existente == null) {
            productos.agregar(producto);
            return true;
        }
        
        return false;
    }
    
    
    public boolean actualizarProducto(Producto producto) {
        return productos.actualizar(
            p -> p.getId().equals(producto.getId()),
            producto
        );
    }
    
  
    public boolean eliminarProducto(String id) {
        Producto producto = productos.buscar(p -> p.getId().equals(id));
        if (producto != null) {
            return productos.eliminar(producto);
        }
        return false;
    }
    
   
    public Producto buscarPorId(String id) {
        return productos.buscar(p -> p.getId().equals(id));
    }
    
    
    public ListaEnlazada<Producto> obtenerTodos() {
        return productos;
    }
    
    
    public Object[] obtenerPorFecha(String fecha) {
        ListaEnlazada<Producto> productosFecha = new ListaEnlazada<>();
        
        for (int i = 0; i < productos.tamaño(); i++) {
            Producto p = productos.obtener(i);
            if (p.getFecha().equals(fecha)) {
                productosFecha.agregar(p);
            }
        }
        
        return productosFecha.arreglo();
    }
    
    
    public Object[] obtenerMenusDeHoy() {
        String hoy = LocalDate.now().format(formatter);
        return obtenerPorFecha(hoy);
    }
    
   
    public Producto obtenerMenuDelDia(String fecha, String tipo) {
        return productos.buscar(p -> 
            p.getFecha().equals(fecha) && p.getTipo().equalsIgnoreCase(tipo)
        );
    }
    
    
    public boolean idDisponible(String id) {
        return productos.buscar(p -> p.getId().equals(id)) == null;
    }
    
    
    public String generarIdProducto() {
        int maxNumero = 0;
        
        for (int i = 0; i < productos.tamaño(); i++) {
            Producto p = productos.obtener(i);
            if (p.getId().startsWith("P")) {
                try {
                    int numero = Integer.parseInt(p.getId().substring(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        
        return String.format("P%03d", maxNumero + 1);
    }
    
   
    public Object[] obtenerPorTipo(String tipo) {
        ListaEnlazada<Producto> productosTipo = new ListaEnlazada<>();
        
        for (int i = 0; i < productos.tamaño(); i++) {
            Producto p = productos.obtener(i);
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                productosTipo.agregar(p);
            }
        }
        
        return productosTipo.arreglo();
    }
    
    
    public String obtenerFechaHoy() {
        return LocalDate.now().format(formatter);
    }
}
