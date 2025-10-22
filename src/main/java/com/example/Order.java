package com.example;
import java.util.ArrayList;

public class Order {
    private String idPedido;
    private ArrayList<Article> ListaArticulos ;

//CONSTRUCTOR 
    public Order(String idPedido,ArrayList<Article> ListaArticulos) {
        this.idPedido = idPedido;
        this.ListaArticulos = ListaArticulos;
}

//GETTERS AND SETTERS
    public ArrayList<Article> getListaArticulos() {
        return ListaArticulos;
    }
    public void setListaArticulos(ArrayList<Article> listaArticulos) {
        ListaArticulos = listaArticulos;
    }
    public String getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

//meotodo que calcula total precio de Article!!
    public double getGrossTotal(){
        double total = 0.0;
        for(int i = 0; i < ListaArticulos.size(); i++){
            Article Article = ListaArticulos.get(i);
            total += Article.getGrossAmount();
        }
        return total;
    }

    //meotodo que calcula total de todos los  Article!!
    public double getDiscountedTotal(){
        double total = 0.0;
        for(int i = 0; i <  ListaArticulos.size();i++){
            Article article = ListaArticulos.get(i);
            total += article.getDiscountedAmount();
        }
        return total;
        
    }

    @Override
    public String toString() {
        return "Order [idPedido=" + idPedido + ", getGrossTotal()=" + getGrossTotal() + "Total descuento = "+ getDiscountedTotal() +"]";
    }
    
   

    

}