package Controlador;

import Datos.ServicioUsuario;
import Modelo.Usuario;
import Vista.VistaRegistro;

public class ControladorRegistro {
    private VistaRegistro vista;
    private ServicioUsuario servicioUsuario;
    
    public ControladorRegistro(VistaRegistro vista) {
        this.vista = vista;
        this.servicioUsuario = ServicioUsuario.getInstancia();
        inicializar();
    }
    
    private void inicializar() {
        vista.getBtnRegistrar().setOnAction(event -> registrarUsuario());
        
        vista.getBtnCancelar().setOnAction(event -> vista.cerrar());
        
        String idSugerido = servicioUsuario.generarIdEstudiante();
        vista.getTxtId().setText(idSugerido);
    }
    
    private void registrarUsuario() {
        String id = vista.getTxtId().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String contraseña = vista.getTxtContraseña().getText();
        String confirmar = vista.getTxtConfirmarContraseña().getText();
        String rol = vista.getCmbRol().getValue();
        
        if (id.isEmpty() || nombre.isEmpty() || contraseña.isEmpty()) {
            vista.mostrarMensaje("Por favor complete todos los campos", true);
            return;
        }
        
        if (!contraseña.equals(confirmar)) {
            vista.mostrarMensaje("Las contraseñas no coinciden", true);
            return;
        }
        
        if (contraseña.length() < 4) {
            vista.mostrarMensaje("La contraseña debe tener al menos 4 caracteres", true);
            return;
        }
        
        if (!servicioUsuario.idDisponible(id)) {
            vista.mostrarMensaje("El ID ya está en uso", true);
            return;
        }
        
        Usuario nuevoUsuario = new Usuario(id, nombre, contraseña, rol);
        
        if (servicioUsuario.crearUsuario(nuevoUsuario)) {
            vista.mostrarMensaje("✓ Usuario registrado exitosamente", false);
            vista.limpiarCampos();
            String nuevoId = servicioUsuario.generarIdEstudiante();
            vista.getTxtId().setText(nuevoId);
        } else {
            vista.mostrarMensaje("Error al registrar usuario", true);
        }
    }
}