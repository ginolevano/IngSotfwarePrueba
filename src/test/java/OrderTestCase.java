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
        lista.add(new Article("Teclado",1,50.00,10.00));
        lista.add(new Article("Monitor",1,100.0,10.00));
        order = new Order("1234",lista);
    }

    @Test
    @DisplayName(" simple getGrossTotal should to return suma of Article total")
    void TestGetGrossTotal(){
        double sumaTotal = 150.0;//Total cantidad de productos
        assertEquals(sumaTotal, order.getGrossTotal(),0.001);
    }

    @Test
    @DisplayName("DEscound")
    void TestGetDiscountedTotal(){
         ArrayList<Article> lista = new ArrayList<>();
        lista.add(new Article("Teclado", 1, 20.00, 10.0)); // 20 - 10% = 18
        lista.add(new Article("Monitor", 1, 10.00, 10.0)); // 10 - 10% = 9
        Order discountedOrder = new Order("5678", lista);

        double expected = 27.0; 
        assertEquals(expected, discountedOrder.getDiscountedTotal(), 0.001);
    }
    @Test
    @DisplayName("getGrossTotal and getDiscountedTotal should return 0 for empty list")
void testEmptyOrder() {
    Order emptyOrder = new Order("000", new ArrayList<>());
    assertEquals(0.0, emptyOrder.getGrossTotal(), 0.001);
    assertEquals(0.0, emptyOrder.getDiscountedTotal(), 0.001);
}

    @Test
    @DisplayName("GettersAndSetter should work!")
    void TestGettersAndSetter(){
        
        Order newOrder = new Order("1234",new ArrayList<>());
        
        newOrder.setIdPedido("124");
        assertEquals("124",newOrder.getIdPedido());

        ArrayList<Article> lista = new ArrayList<>();
        lista.add(new Article("webCam",1,20.0,0.0));

        newOrder.setListaArticulos(lista);
        assertEquals(lista, newOrder.getListaArticulos());
        assertEquals(20.0,newOrder.getGrossTotal(),0.001);

    }



@Test
@DisplayName("Default constructor should initialize object")
void testDefaultConstructor() {
    Order emptyOrder = new Order(); // usa el constructor vacío
    emptyOrder.setIdPedido("constructorVacio");
    emptyOrder.setListaArticulos(new ArrayList<>());

    assertEquals("constructorVacio", emptyOrder.getIdPedido());
    assertEquals(0.0, emptyOrder.getGrossTotal(), 0.001);
}


}
