package Controlador;

import Datos.ServicioCarrito;
import Datos.ServicioProducto;
import Modelo.Producto;
import Vista.VistaCatalogo;
import Vista.VistaHistorial;
import Vista.VistaCarrito;
import javafx.scene.control.Alert;
import Datos.ServicioDeseados;
import Vista.VistaDeseados;

public class ControladorCatalogo {
    private VistaCatalogo vista;
    private String idEstudianteActual;
    private ServicioCarrito servicioCarrito;
    private ServicioProducto servicioProducto;
    private ServicioDeseados servicioDeseados;

    public ControladorCatalogo(VistaCatalogo vista, String idEstudiante) {
        this.vista = vista;
        this.idEstudianteActual = idEstudiante;
        this.servicioCarrito = ServicioCarrito.getInstancia();
        this.servicioProducto = ServicioProducto.getInstancia();
        this.servicioDeseados = ServicioDeseados.getInstancia();

        servicioCarrito.setIdEstudiante(idEstudiante);
        inicializar();
    }
    
    
    private void inicializar() {
        vista.btnCerrarSesion.setOnAction(event -> {
            vista.cerrar();
        });
        
        vista.btnCarrito.setOnAction(event -> {
            abrirCarrito();
        });
        
        vista.btnHistorial.setOnAction(event -> {
            abrirHistorial();
        });
        
        vista.btnSeleccionarAlmuerzo1.setOnAction(event -> {
            agregarAlCarrito("almuerzo1");
        });
        
        vista.btnSeleccionarAlmuerzo2.setOnAction(event -> {
            agregarAlCarrito("almuerzo2");
        });
        
        vista.btnSeleccionarBebida1.setOnAction(event -> {
            System.out.println("Seleccionada Bebida 1");
        });
        
        vista.btnSeleccionarApetitivo1.setOnAction(event -> {
            System.out.println("Seleccionado Apetitivo 1");
        });
        
        vista.btnEditarAlmuerzo1.setOnAction(event -> {
            System.out.println("Editar Almuerzo 1");
        });
        
        vista.btnEditarAlmuerzo2.setOnAction(event -> {
            System.out.println("Editar Almuerzo 2");
        });
        
        vista.btnEditarBebida1.setOnAction(event -> {
            System.out.println("Editar Bebida 1");
        });
        
        vista.btnEditarApetitivo1.setOnAction(event -> {
            System.out.println("Editar Apetitivo 1");
        });
        
        vista.btnDeseados.setOnAction(event -> {
    abrirDeseados();
});

vista.btnAgregarDeseados1.setOnAction(event -> {
    agregarADeseados("almuerzo1");
});

vista.btnAgregarDeseados2.setOnAction(event -> {
    agregarADeseados("almuerzo2");
});

vista.btnAgregarDeseados3.setOnAction(event -> {
    System.out.println("Agregar bebida a deseados");
});

vista.btnAgregarDeseados4.setOnAction(event -> {
    System.out.println("Agregar apetitivo a deseados");
});
vista.btnSeleccionarBebida1.setOnAction(event -> {
    System.out.println("Seleccionada Bebida 1 - Funcionalidad pendiente");
});

vista.btnSeleccionarApetitivo1.setOnAction(event -> {
    System.out.println("Seleccionado Apetitivo 1 - Funcionalidad pendiente");
});

vista.btnAgregarDeseados3.setOnAction(event -> {
    System.out.println("Agregar bebida a deseados - Funcionalidad pendiente");
});

vista.btnAgregarDeseados4.setOnAction(event -> {
    System.out.println("Agregar apetitivo a deseados - Funcionalidad pendiente");
});
    }
    
    
    private void agregarAlCarrito(String tipoMenu) {
        String fechaHoy = servicioProducto.obtenerFechaHoy();
        Producto producto = servicioProducto.obtenerMenuDelDia(fechaHoy, tipoMenu);
        
        if (producto != null) {
            servicioCarrito.agregarProducto(producto, 1);
            mostrarMensaje(" Producto agregado al carrito", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje(" No hay menú disponible para hoy", Alert.AlertType.WARNING);
        }
    }
    
    
    public void abrirHistorial() {
        VistaHistorial vistaHistorial = new VistaHistorial(idEstudianteActual);
        new ControladorHistorial(vistaHistorial, idEstudianteActual);
        vistaHistorial.mostrar();
    }
    
    public void abrirCarrito() {
        VistaCarrito vistaCarrito = new VistaCarrito();
        new ControladorCarrito(vistaCarrito);
        vistaCarrito.mostrar();
    }
    
    private void mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void agregarADeseados(String tipoMenu) {
    String fechaHoy = servicioProducto.obtenerFechaHoy();
    Producto producto = servicioProducto.obtenerMenuDelDia(fechaHoy, tipoMenu);
    
    if (producto != null) {
        if (servicioDeseados.agregarDeseado(idEstudianteActual, producto)) {
            mostrarMensaje("Producto agregado a deseados", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("El producto ya está en deseados", Alert.AlertType.WARNING);
        }
    } else {
        mostrarMensaje("No hay menú disponible", Alert.AlertType.WARNING);
    }
}

public void abrirDeseados() {
    VistaDeseados vistaDeseados = new VistaDeseados(idEstudianteActual);
    new ControladorDeseados(vistaDeseados, idEstudianteActual);
    vistaDeseados.mostrar();
}
    
}