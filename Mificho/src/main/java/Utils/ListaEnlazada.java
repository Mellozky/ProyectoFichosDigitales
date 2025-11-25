/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.util.function.Predicate;
/**
 *
 * @author PC
 * @param <T>
 */

public class ListaEnlazada <T> {
    private Nodo<T> cabeza;
    private int tamaño;
    
    public ListaEnlazada (){
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    
    public void agregar (T dato){
        Nodo<T> nuevoNodo = new Nodo <>(dato);
        if (cabeza ==null){
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente()!=null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente (nuevoNodo);
        
        }
        tamaño ++;
    }
    
    public boolean eliminar (T dato){
        if (cabeza == null){
             return false;
        }
        if (cabeza.getDato().equals(dato)){
    cabeza = cabeza.getSiguiente();
    tamaño --;
    return true;
}
    
    Nodo<T> actual = cabeza;
    while (actual.getSiguiente()!=null){
    if (actual.getSiguiente().getDato().equals(dato)){
    actual.setSiguiente(actual.getSiguiente().getSiguiente());
    tamaño --;
    return true;
}
    actual = actual.getSiguiente();
 
}
        return false;
    }
    
   
  
    public T buscar (Predicate <T> condicion){
        Nodo<T> actual = cabeza;
        while (actual !=null){
            if (condicion.test(actual.getDato ())){
                return actual.getDato();
            }
            actual = actual.getSiguiente ();
        }
        return null;
    }
    public boolean actualizar (Predicate<T> condicion, T nuevosDatos){
        Nodo <T> actual = cabeza;
        
        while (actual !=null){
            if (condicion.test(actual.getDato())){
                actual.setDato(nuevosDatos);
                return true;
               
            }
             actual = actual.getSiguiente();
        }
        return false;
}
    public int tamaño (){
        return tamaño;
    }
    
    public boolean estaVacia (){
        return cabeza == null;
    }
    
   public T obtener (int indice){
    if (indice < 0 || indice >= tamaño){
        throw new IndexOutOfBoundsException("fuera de rango");
    }
    Nodo<T> actual = cabeza;
    for (int i = 0; i < indice; i++) { 
        actual = actual.getSiguiente();
    }
    return actual.getDato();
}
    public Object [] arreglo(){
        Object [] arreglo = new Object [tamaño];
        Nodo<T> actual = cabeza;
        int indice = 0;
        while (actual !=null){
            arreglo [indice ++] = actual.getDato();
            actual = actual.getSiguiente();
        }
        return arreglo;
    }
    
    public void limpiar (){
        cabeza = null;
        tamaño = 0;
    }
    
    public boolean contiene (T dato){
        Nodo <T> actual = cabeza;
        while (actual != null){
            if ( actual.getDato().equals(dato)){
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
}
