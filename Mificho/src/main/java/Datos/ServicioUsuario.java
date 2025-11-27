/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Utils.ListaEnlazada;
import Modelo.Usuario;
/**
 *
 * @author PC
 */
public class ServicioUsuario {
    private ListaEnlazada<Usuario> usuarios;
    private static ServicioUsuario instancia;
    
    public ServicioUsuario(){
        usuarios = new ListaEnlazada <>();
         inicializarUsuarios();
    }
    public static ServicioUsuario getInstancia(){
        if (instancia == null){
            instancia = new ServicioUsuario();
        }
        return instancia;
    }
    
    private void inicializarUsuarios(){
        usuarios.agregar (new Usuario("A001", "Admin", "admin123", "administrador"));
        
        usuarios.agregar(new Usuario("E001", "Juan Pérez", "juan123", "estudiante"));
        usuarios.agregar(new Usuario("E002", "María García", "maria123", "estudiante"));
        usuarios.agregar(new Usuario("E003", "Carlos López", "carlos123", "estudiante"));
        usuarios.agregar(new Usuario("E004", "Ana Martínez", "ana123", "estudiante"));
        usuarios.agregar(new Usuario("E005", "Pedro Rodríguez", "pedro123", "estudiante"));
    }
    
    public Usuario auntenticar (String id, String contraseña){
        Usuario usuario = usuarios.buscar(u -> u.getId().equals(id));
        if (usuario != null &&usuario.getContraseña().equals (contraseña)){
            return usuario;
            
        }
        return null;
        
    }
    
    public boolean crearUsuario(Usuario usuario) {
        Usuario existente = usuarios.buscar(u -> u.getId().equals(usuario.getId()));
        
        if (existente == null) {
            usuarios.agregar(usuario);
            return true;
        }
        
        return false;
}
     public boolean actualizarUsuario(Usuario usuario) {
        return usuarios.actualizar(
            u -> u.getId().equals(usuario.getId()),
            usuario
        );
    }
        public boolean eliminarUsuario(String id) {
        Usuario usuario = usuarios.buscar(u -> u.getId().equals(id));
        if (usuario != null) {
            return usuarios.eliminar(usuario);
        }
        return false;
    }
         public Usuario buscarPorId(String id) {
        return usuarios.buscar(u -> u.getId().equals(id));
    }
         public ListaEnlazada<Usuario> obtenerTodos() {
        return usuarios;
    }
          public Object[] obtenerEstudiantes() {
        ListaEnlazada<Usuario> estudiantes = new ListaEnlazada<>();
        
        for (int i = 0; i < usuarios.tamaño(); i++) {
            Usuario u = usuarios.obtener(i);
            if (u.esEstudiante()) {
                estudiantes.agregar(u);
            }
        }
        
        return estudiantes.arreglo();
    }
          public boolean idDisponible(String id) {
        return usuarios.buscar(u -> u.getId().equals(id)) == null;
    }
          public String generarIdEstudiante() {
        int maxNumero = 0;
        
        for (int i = 0; i < usuarios.tamaño(); i++) {
            Usuario u = usuarios.obtener(i);
            if (u.getId().startsWith("E")) {
                try {
                    int numero = Integer.parseInt(u.getId().substring(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                } catch (NumberFormatException e) {
                    
                }
            }
        }
        return String.format("E%03d", maxNumero + 1);
}
          
              
          }