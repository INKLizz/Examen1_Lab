/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1_p1;

/**
 *
 * @author Laura Sabillon
 */
public abstract class RentItem {
    private int codigo;
    private String nombre;
    private double precio;
    private int copias;
    
    public RentItem (int codigo, String nombre, double precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        copias = 0;
    }
    
    public String toString(){
        return "Codigo: " + codigo +"\nNombre: " + nombre +"\nPrecio: " + precio +" Lps.";
    }
    
    public abstract double pagoRenta(int dias); 

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
