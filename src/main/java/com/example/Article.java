package com.example;
import com.example.Calculator;
public class Article {
    
    private Calculator calculator = new Calculator();

    private String nombre;
    private int cantidad;
    private double precio;
    private double descuento;

    public Article(String nombre,int cantidad,double precio, double descuento){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }
    //GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int cantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }

    public double getDescuento(){
        return descuento;
    }

    public void setDescuento(double descuento){
        this.descuento = descuento;
    }
    public double getGrossAmount(){
        return calculator.multiply(cantidad,(int)precio);
    }
    
    public double getDiscountedAmount(){
        return descuento;
    }
}
