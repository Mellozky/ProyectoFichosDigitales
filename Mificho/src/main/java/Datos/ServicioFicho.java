/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Modelo.Ficho;
import Utils.ListaEnlazada;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author maria
 */
public class ServicioFicho {
    private ListaEnlazada<Ficho> fichos;
    private static ServicioFicho instancia;
    private DateTimeFormatter formatter;
    private int contadorFichos;
    
    private ServicioFicho() {
        fichos = new ListaEnlazada<>();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        contadorFichos = 1;
        inicializarFichos();
    }
    
    public static ServicioFicho getInstancia() {
        if (instancia == null) {
            instancia = new ServicioFicho();
        }
        return instancia;
    }
    
    
    private void inicializarFichos() {
        String hoy = LocalDate.now().format(formatter);
        String manana = LocalDate.now().plusDays(1).format(formatter);
        
        fichos.agregar(new Ficho("F001", "E001", hoy));
        fichos.agregar(new Ficho("F002", "E002", hoy));
        fichos.agregar(new Ficho("F003", "E003", hoy));
        
        fichos.agregar(new Ficho("F004", "E001", manana));
        fichos.agregar(new Ficho("F005", "E004", manana));
        
        Ficho fichoUsado = new Ficho("F006", "E005", hoy);
        fichoUsado.usar("almuerzo1");
        fichos.agregar(fichoUsado);
        
        contadorFichos = 7;
    }
    
   
    public boolean crearFicho(String idEstudiante, String fecha) {
        String idFicho = generarIdFicho();
        Ficho nuevoFicho = new Ficho(idFicho, idEstudiante, fecha);
        fichos.agregar(nuevoFicho);
        return true;
    }
    
    
    public boolean crearFichos(String idEstudiante, String fecha, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            crearFicho(idEstudiante, fecha);
        }
        return true;
    }
    
    
    public ListaEnlazada<Ficho> obtenerTodos() {
        return fichos;
    }
    
    
    public Object[] obtenerPorEstudiante(String idEstudiante) {
        ListaEnlazada<Ficho> fichosEstudiante = new ListaEnlazada<>();
        
        for (int i = 0; i < fichos.tamaño(); i++) {
            Ficho f = fichos.obtener(i);
            if (f.getIdEstudiante().equals(idEstudiante)) {
                fichosEstudiante.agregar(f);
            }
        }
        
        return fichosEstudiante.arreglo();
    }
    
    /**
     * Obtiene fichos activos de un estudiante
     */
    public Object[] obtenerActivosPorEstudiante(String idEstudiante) {
        ListaEnlazada<Ficho> fichosActivos = new ListaEnlazada<>();
        
        for (int i = 0; i < fichos.tamaño(); i++) {
            Ficho f = fichos.obtener(i);
            if (f.getIdEstudiante().equals(idEstudiante) && f.estaActivo()) {
                fichosActivos.agregar(f);
            }
        }
        
        return fichosActivos.arreglo();
    }
    
    
    public Object[] obtenerPorFecha(String fecha) {
        ListaEnlazada<Ficho> fichosFecha = new ListaEnlazada<>();
        
        for (int i = 0; i < fichos.tamaño(); i++) {
            Ficho f = fichos.obtener(i);
            if (f.getFecha().equals(fecha)) {
                fichosFecha.agregar(f);
            }
        }
        
        return fichosFecha.arreglo();
    }
    
   
    public Ficho buscarPorId(String idFicho) {
        return fichos.buscar(f -> f.getIdFicho().equals(idFicho));
    }
    
   
    public boolean usarFicho(String idFicho, String tipoMenu) {
        Ficho ficho = buscarPorId(idFicho);
        
        if (ficho != null && ficho.estaActivo()) {
            ficho.usar(tipoMenu);
            return fichos.actualizar(f -> f.getIdFicho().equals(idFicho), ficho);
        }
        
        return false;
    }
    
    
    public boolean eliminarFicho(String idFicho) {
        Ficho ficho = buscarPorId(idFicho);
        if (ficho != null) {
            return fichos.eliminar(ficho);
        }
        return false;
    }
    
    
    private String generarIdFicho() {
        return String.format("F%03d", contadorFichos++);
    }
    
    
    public int contarActivosPorEstudiante(String idEstudiante) {
        int contador = 0;
        for (int i = 0; i < fichos.tamaño(); i++) {
            Ficho f = fichos.obtener(i);
            if (f.getIdEstudiante().equals(idEstudiante) && f.estaActivo()) {
                contador++;
            }
        }
        return contador;
    }
    
    
    public Object[] obtenerHistorialPorEstudiante(String idEstudiante) {
        ListaEnlazada<Ficho> historial = new ListaEnlazada<>();
        
        for (int i = 0; i < fichos.tamaño(); i++) {
            Ficho f = fichos.obtener(i);
            if (f.getIdEstudiante().equals(idEstudiante) && f.estaUsado()) {
                historial.agregar(f);
            }
        }
        
        return historial.arreglo();
    }
    
    
    public int obtenerTotalFichos() {
        return fichos.tamaño();
    }
    
   
    public int obtenerTotalUsados() {
        int contador = 0;
        for (int i = 0; i < fichos.tamaño(); i++) {
            if (fichos.obtener(i).estaUsado()) {
                contador++;
            }
        }
        return contador;
    }
    
   
    public int obtenerTotalActivos() {
        int contador = 0;
        for (int i = 0; i < fichos.tamaño(); i++) {
            if (fichos.obtener(i).estaActivo()) {
                contador++;
            }
        }
        return contador;
    }
}
