package com.example;

public class Items {

    private String nombre;
    private double precio;

    public Items(String n, double precio){
        this.nombre = n;
        this.precio = precio;
    }

    public  String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre ){
        this.nombre = nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public String toString(){
        return nombre + " : " + precio;
    }

  
    

}
