package Datos;

import Utils.ListaEnlazada;
import Modelo.Usuario;
import java.io.*;

public class ServicioUsuario {
    private ListaEnlazada<Usuario> usuarios;
    private static ServicioUsuario instancia;
    private static final String ARCHIVO = "usuarios.txt";
    
    private ServicioUsuario(){
        usuarios = new ListaEnlazada<>();
        cargarDesdeArchivo();
        if (usuarios.estaVacia()) {
            inicializarUsuarios();
        }
    }
    
    public static ServicioUsuario getInstancia(){
        if (instancia == null){
            instancia = new ServicioUsuario();
        }
        return instancia;
    }
    
    private void inicializarUsuarios(){
        usuarios.agregar(new Usuario("A001", "Admin", "admin123", "administrador"));
        usuarios.agregar(new Usuario("E001", "Juan Pérez", "juan123", "estudiante"));
        usuarios.agregar(new Usuario("E002", "María García", "maria123", "estudiante"));
        usuarios.agregar(new Usuario("E003", "Carlos López", "carlos123", "estudiante"));
        usuarios.agregar(new Usuario("E004", "Ana Martínez", "ana123", "estudiante"));
        usuarios.agregar(new Usuario("E005", "Pedro Rodríguez", "pedro123", "estudiante"));
        guardarEnArchivo();
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
                if (partes.length == 4) {
                    Usuario usuario = new Usuario(
                        partes[0].trim(),
                        partes[1].trim(),
                        partes[2].trim(),
                        partes[3].trim()
                    );
                    usuarios.agregar(usuario);
                }
            }
            System.out.println("Usuarios cargados desde archivo: " + usuarios.tamaño());
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }
    
    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < usuarios.tamaño(); i++) {
                Usuario u = usuarios.obtener(i);
                bw.write(u.getId() + "|" + u.getNombre() + "|" + 
                         u.getContraseña() + "|" + u.getRol());
                bw.newLine();
            }
            System.out.println("Usuarios guardados en archivo");
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
    
    public Usuario auntenticar(String id, String contraseña){
        Usuario usuario = usuarios.buscar(u -> u.getId().equals(id));
        if (usuario != null && usuario.getContraseña().equals(contraseña)){
            return usuario;
        }
        return null;
    }
    
    public boolean crearUsuario(Usuario usuario) {
        Usuario existente = usuarios.buscar(u -> u.getId().equals(usuario.getId()));
        
        if (existente == null) {
            usuarios.agregar(usuario);
            guardarEnArchivo();
            return true;
        }
        
        return false;
    }
    
    public boolean actualizarUsuario(Usuario usuario) {
        boolean resultado = usuarios.actualizar(
            u -> u.getId().equals(usuario.getId()),
            usuario
        );
        if (resultado) {
            guardarEnArchivo();
        }
        return resultado;
    }
    
    public boolean eliminarUsuario(String id) {
        Usuario usuario = usuarios.buscar(u -> u.getId().equals(id));
        if (usuario != null) {
            boolean resultado = usuarios.eliminar(usuario);
            if (resultado) {
                guardarEnArchivo();
            }
            return resultado;
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