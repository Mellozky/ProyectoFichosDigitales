/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author PC
 */
public class Usuario {
    private String id;
    private String nombre;
    private String contraseña;
    private String rol;
    
    public Usuario (String id, String nombre, String contraseña, String rol){
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.rol = rol;
        
                
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    public boolean esAdmin(){
        return "administrador".equalsIgnoreCase(rol);
    }
    public boolean esEstudiante(){
        return "estudiante".equalsIgnoreCase(rol);
    }
    
    @Override
    public boolean equals (Object obj){
        if (this == obj) return true;
       if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return id.equals(usuario.id);
    }
    
    @Override
    public String toString(){
         return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
