package Datos;

import Modelo.Producto;
import Modelo.ItemDeseado;
import Utils.ListaEnlazada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import Vista.VistaDeseados;
import Controlador.ControladorCatalogo;


public class ServicioDeseados {
    private ListaEnlazada<ItemDeseado> deseados;
    private static ServicioDeseados instancia;
    private DateTimeFormatter formatter;
    private static final String ARCHIVO = "deseados.txt";
    private ServicioProducto servicioProducto;
    
    private ServicioDeseados() {
        deseados = new ListaEnlazada<>();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        servicioProducto = ServicioProducto.getInstancia();
        cargarDesdeArchivo();
    }
    
    public static ServicioDeseados getInstancia() {
        if (instancia == null) {
            instancia = new ServicioDeseados();
        }
        return instancia;
    }
    
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    String idEstudiante = partes[0].trim();
                    String idProducto = partes[1].trim();
                    String fechaAgregado = partes[2].trim();
                    
                    Producto producto = servicioProducto.buscarPorId(idProducto);
                    if (producto != null) {
                        deseados.agregar(new ItemDeseado(idEstudiante, producto, fechaAgregado));
                    }
                }
            }
            System.out.println("Deseados cargados desde archivo: " + deseados.tamaño());
        } catch (IOException e) {
            System.err.println("Error al cargar deseados: " + e.getMessage());
        }
    }
    
    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < deseados.tamaño(); i++) {
                ItemDeseado item = deseados.obtener(i);
                bw.write(item.getIdEstudiante() + "|" + 
                         item.getProducto().getId() + "|" + 
                         item.getFechaAgregado());
                bw.newLine();
            }
            System.out.println("Deseados guardados en archivo");
        } catch (IOException e) {
            System.err.println("Error al guardar deseados: " + e.getMessage());
        }
    }
    
    public boolean agregarDeseado(String idEstudiante, Producto producto) {
        ItemDeseado existente = deseados.buscar(item -> 
            item.getIdEstudiante().equals(idEstudiante) && 
            item.getProducto().getId().equals(producto.getId())
        );
        
        if (existente == null) {
            String fechaActual = LocalDate.now().format(formatter);
            ItemDeseado nuevoItem = new ItemDeseado(idEstudiante, producto, fechaActual);
            deseados.agregar(nuevoItem);
            guardarEnArchivo();
            return true;
        }
        
        return false;
    }
    
    public boolean eliminarDeseado(String idEstudiante, String idProducto) {
        ItemDeseado item = deseados.buscar(d -> 
            d.getIdEstudiante().equals(idEstudiante) && 
            d.getProducto().getId().equals(idProducto)
        );
        
        if (item != null) {
            boolean resultado = deseados.eliminar(item);
            if (resultado) {
                guardarEnArchivo();
            }
            return resultado;
        }
        return false;
    }
    
    public Object[] obtenerDeseadosPorEstudiante(String idEstudiante) {
        ListaEnlazada<ItemDeseado> deseadosEstudiante = new ListaEnlazada<>();
        
        for (int i = 0; i < deseados.tamaño(); i++) {
            ItemDeseado item = deseados.obtener(i);
            if (item.getIdEstudiante().equals(idEstudiante)) {
                deseadosEstudiante.agregar(item);
            }
        }
        
        return deseadosEstudiante.arreglo();
    }
    
    public int contarDeseadosPorEstudiante(String idEstudiante) {
        int contador = 0;
        for (int i = 0; i < deseados.tamaño(); i++) {
            if (deseados.obtener(i).getIdEstudiante().equals(idEstudiante)) {
                contador++;
            }
        }
        return contador;
    }
    
    public boolean estaEnDeseados(String idEstudiante, String idProducto) {
        return deseados.buscar(item -> 
            item.getIdEstudiante().equals(idEstudiante) && 
            item.getProducto().getId().equals(idProducto)
        ) != null;
    }
    
    public void vaciarDeseados(String idEstudiante) {
        ListaEnlazada<ItemDeseado> aEliminar = new ListaEnlazada<>();
        
        for (int i = 0; i < deseados.tamaño(); i++) {
            ItemDeseado item = deseados.obtener(i);
            if (item.getIdEstudiante().equals(idEstudiante)) {
                aEliminar.agregar(item);
            }
        }
        
        for (int i = 0; i < aEliminar.tamaño(); i++) {
            deseados.eliminar(aEliminar.obtener(i));
        }
        
        if (aEliminar.tamaño() > 0) {
            guardarEnArchivo();
        }
    }
}