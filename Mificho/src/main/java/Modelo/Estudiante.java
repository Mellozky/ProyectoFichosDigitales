/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Estudiante {
 private String nombre;
    private String documento;
    private String correo;
    private String contrasena;
    private double saldo; // saldo disponible en la cuenta del estudiante
    private ArrayList<Ficho> historialFichos; // lista de fichos o pedidos realizados

    
    public Estudiante(String nombre, String documento, String correo, String contrasena) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.contrasena = contrasena;
        this.saldo = 0.0;
        this.historialFichos = new ArrayList<>();
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Ficho> getHistorialFichos() {
        return historialFichos;
    }

    public void agregarSaldo(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean descontarSaldo(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            return true;
        } else {
            return false;
        }
    }

    public void agregarFicho(Ficho ficho) {
        historialFichos.add(ficho);
    }

    public void verHistorialFichos() {
        if (historialFichos.isEmpty()) {
            System.out.println("No hay fichos registrados.");
        } else {
            for (Ficho f : historialFichos) {
                System.out.println(f);
            }
        }
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", correo='" + correo + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
