package com.example;
import java.util.ArrayList;

public class Order {
    private String idPedido;
    private ArrayList<String> ListaArticulos = new ArrayList<>();


    public Order(String idPedido,ArrayList<String> ListaArticulos) {
        this.idPedido = idPedido;
        this.ListaArticulos = ListaArticulos;
    }
//GETTERS AND SETTERS
    public ArrayList<String> getListaArticulos() {
        return ListaArticulos;
    }
    public void setListaArticulos(ArrayList<String> listaArticulos) {
        ListaArticulos = listaArticulos;
    }
    public String getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public double getGrossTotal(){
        double total = 0.0;
        for(int i = 0; i < ListaArticulos.size(); i++){

        }
        return total;
    }
    

}
