/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class Ficho {
        private int idFicho;
    private Estudiante estudiante;
    private Producto producto;
    private LocalDate fecha;
    private boolean utilizado;

    public Ficho() {
    }

    public Ficho(int idFicho, Estudiante estudiante, Producto producto, LocalDate fecha, boolean utilizado) {
        this.idFicho = idFicho;
        this.estudiante = estudiante;
        this.producto = producto;
        this.fecha = fecha;
        this.utilizado = utilizado;
    }

    public int getIdFicho() {
        return idFicho;
    }

    public void setIdFicho(int idFicho) {
        this.idFicho = idFicho;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }

    @Override
    public String toString() {
        return "Ficho #" + idFicho + " - " + estudiante.getNombre() + " - " + producto.getNombre() + 
               " - Fecha: " + fecha + (utilizado ? " (Usado)" : " (Disponible)");
    }   
}
