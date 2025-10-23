package com.example;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String idPedido;
    private ArrayList<Article> listaArticulos;
    private Calculator calculator = new Calculator();

    // Constructor
    public Order(String idPedido, ArrayList<Article> listaArticulos) {
        this.idPedido = idPedido;
        this.listaArticulos = listaArticulos;
    }

    // Getters y Setters
    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public ArrayList<Article> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(ArrayList<Article> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    //  calcula el total bruto usando Calculator
    public double getGrossTotal() {
        List<Double> totales = new ArrayList<>();
        for (int i = 0; i < listaArticulos.size(); i++) {
            totales.add(listaArticulos.get(i).getGrossAmount());
        }
        return calculator.calculateTotal(totales);
    }

    // ✅ Optimizado: calcula el total con descuento usando Calculator
    public double getDiscountedTotal() {
        List<Double> totalesConDescuento = new ArrayList<>();
        for (int i = 0; i < listaArticulos.size(); i++) {
            totalesConDescuento.add(listaArticulos.get(i).getDiscountedAmount());
        }
        return calculator.calculateTotal(totalesConDescuento);
    }

    @Override
    public String toString() {
        return "Order {" +
                "idPedido='" + idPedido + '\'' +
                ", total bruto=" + getGrossTotal() +
                ", total con descuento=" + getDiscountedTotal() +
                '}';
    }
}
