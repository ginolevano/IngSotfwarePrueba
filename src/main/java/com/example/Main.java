package com.example;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        
        log.info("STARTING ORDER MANAGMENT SYSTEM.....");
        ObjectMapper mapper = new ObjectMapper();

        try(InputStream inputStream = Main.class.getResourceAsStream("/orders.json")){
            //System.out.println(Main.class.getResource("/orders.json"));

            if(inputStream == null){
                log.error("no se encontro el Order.json");
                return;
            }
            List<Order> orders = mapper.readValue(inputStream,new TypeReference<List<Order>>(){});

            for(int i = 0; i < orders.size();i++){
                    Order order = orders.get(i);
                    log.debug("Loaded order: {}" , order.getIdPedido());
                    System.out.println("\n PEDIDO : " + order.getIdPedido());

                    for(int j = 0; j < order.getListaArticulos().size();j++){
                        Article articulos = order.getListaArticulos().get(j);
                        System.out.println(" => " + articulos);
                    }

                    System.out.println("Total bruto => "+ order.getGrossTotal());
                    System.out.println("Total descuento => " + order.getDiscountedTotal());
                    System.out.println("=====");
            }
        }catch(Exception e){
                log.error("Error al leer .JSON " , e.getMessage());
        }

    }
    
}