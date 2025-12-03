package Modelo;

public class ItemDeseado {
    private String idEstudiante;
    private Producto producto;
    private String fechaAgregado;
    
    public ItemDeseado(String idEstudiante, Producto producto, String fechaAgregado) {
        this.idEstudiante = idEstudiante;
        this.producto = producto;
        this.fechaAgregado = fechaAgregado;
    }
    
    public String getIdEstudiante() {
        return idEstudiante;
    }
    
    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public String getFechaAgregado() {
        return fechaAgregado;
    }
    
    public void setFechaAgregado(String fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
    
    public String getNombreProducto() {
        return producto.getNombre();
    }
    
    public String getTipoProducto() {
        return producto.getTipo();
    }
    
    public String getFechaProducto() {
        return producto.getFecha();
    }
    
    public String getDescripcionProducto() {
        return producto.getDescripcion();
    }
}