import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.Article;
import com.example.Order;

public class OrderTestCase {
    
    private Order order;
    
    @BeforeEach
    void setUp(){
        ArrayList<Article> lista = new ArrayList<>();
        lista.add(new Article("Teclado",20.00));
        lista.add(new Article("Monitor",10.00));
        order = new Order("1234",lista);
    }

    @Test
    @DisplayName(" simple getGrossTotal should to return suma of Article total")
    void TestGetGrossTotal(){
        double sumaTotal = 30.0;//Total cantidad de productos
        assertEquals(sumaTotal, order.getGrossTotal(),0.001);
    }

    @Test
    @DisplayName("DEscound")
    void TestGetDiscountedTotal(){
        double esperado = 27.00;//30*0.9=27
        assertEquals(esperado,order.getDiscountedTotal(10.0),0.001);
    }

    @Test
    @DisplayName("GettersAndSetter should work!")
    void TestGettersAndSetter(){
        
        Order newOrder = new Order("1234",new ArrayList<>());
        
        newOrder.setIdPedido("124");
        assertEquals("1234",order.getIdPedido());

        ArrayList<Article> lista = new ArrayList<>();
        lista.add(new Article("webCam",20.0));

        newOrder.setListaArticulos(lista);
        assertEquals(lista, newOrder.getListaArticulos());
        assertEquals(20.0,newOrder.getGrossTotal(),0.001);

    }

}
