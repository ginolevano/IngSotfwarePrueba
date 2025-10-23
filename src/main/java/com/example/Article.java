package com.example;
import com.example.Calculator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
    
    private Calculator calculator = new Calculator();

    @JsonProperty("name")
    private String nombre;
    @JsonProperty("quantity")
    private int cantidad;
    @JsonProperty("unitPrice")
    private double precio;
    @JsonProperty("discount")
    private double descuento;

    public Article(){}

    public Article(String nombre, int cantidad, double precio, double descuento){
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

    public int getCantidad(){
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

    //calcula el total bruto cantidad * precio
    public double getGrossAmount(){
        return calculator.multiply((int)precio,(int)cantidad);
    }

    public double getDiscountedAmount(){
        double bruto = getGrossAmount();
        return calculator.discount(bruto, descuento);
    }


}
