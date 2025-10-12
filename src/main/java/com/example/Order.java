package com.example;
import java.util.ArrayList;

public class Order {
    private String idPedido;
    private ArrayList<Items> ListaArticulos ;

//GETTERS AND SETTERS
    public ArrayList<Items> getListaArticulos() {
        return ListaArticulos;
    }
    public void setListaArticulos(ArrayList<Items> listaArticulos) {
        ListaArticulos = listaArticulos;
    }
    public String getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    //CONSTRUCTOR 
    public Order(String idPedido,ArrayList<Items> ListaArticulos) {
        this.idPedido = idPedido;
        this.ListaArticulos = ListaArticulos;
    }
//meotodo que calcula total precio de ITEMS!!
    public double getGrossTotal(){
        double total = 0.0;
        for(int i = 0; i < ListaArticulos.size(); i++){
            Items items = ListaArticulos.get(i);
            total += items.getPrecio();
        }
        return total;
    }

//meotodo que calcula total de todos los  ITEMS!!
    public double getDiscountedTotal(double descuento){
        double bruto = getGrossTotal();
        double descuentoTotal = bruto * (descuento / 100.0);
        return bruto - descuentoTotal;
        
    }

    

}
