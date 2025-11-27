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
       private String idFicho;
    private String idEstudiante;
    private String fecha; 
    private String menuSeleccionado; 
    private String estado; 
    
    public Ficho(String idFicho, String idEstudiante, String fecha) {
        this.idFicho = idFicho;
        this.idEstudiante = idEstudiante;
        this.fecha = fecha;
        this.menuSeleccionado = null;
        this.estado = "activo";
    }
    
    public String getIdFicho() {
        return idFicho;
    }
    
    public void setIdFicho(String idFicho) {
        this.idFicho = idFicho;
    }
    
    public String getIdEstudiante() {
        return idEstudiante;
    }
    
    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getMenuSeleccionado() {
        return menuSeleccionado;
    }
    
    public void setMenuSeleccionado(String menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean estaActivo() {
        return "activo".equalsIgnoreCase(estado);
    }
    
    public boolean estaUsado() {
        return "usado".equalsIgnoreCase(estado);
    }
    
   
    public void usar(String tipoMenu) {
        this.menuSeleccionado = tipoMenu;
        this.estado = "usado";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ficho ficho = (Ficho) obj;
        return idFicho.equals(ficho.idFicho);
    }
    
    @Override
    public String toString() {
        return "Ficho{" +
                "idFicho='" + idFicho + '\'' +
                ", idEstudiante='" + idEstudiante + '\'' +
                ", fecha='" + fecha + '\'' +
                ", menuSeleccionado='" + menuSeleccionado + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    
}
