import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.Article;

public class ArticleTestCase {
    
    private Article article;

    @BeforeEach
    void setUp(){
        article = new Article("camiseta",3,10.0,20.0 );
    }
    
    @Test
    @DisplayName("simple getGrossTotal should work")
    void getGrossAmount(){
        double expected = 30.0;
        assertEquals(expected,article.getGrossAmount(),0.0001);
    }

    @Test
    @DisplayName("simple getGrossTotal should return descounted value")
    void TestDiscountedAmount(){
        double expected = 24.0;
        assertEquals(expected,article.getDiscountedAmount(),0.0001);
    }
    //Testing GETTERS
    @Test
    @DisplayName("Testing getters setters should work")
    void TestGettersAndSetters(){

        article.setNombre("Pantalon");
        assertEquals("Pantalon",article.getNombre());
        
        article.setCantidad(2);
        assertEquals(2,article.getCantidad());
        
        article.setPrecio(20.0);
        assertEquals(20.0,article.getPrecio());
        
        article.setDescuento(10.0);
        assertEquals(10.0,article.getDescuento());
        
    }

@Test
@DisplayName("Testing toString and default constructor")
void TestToStringAndDefaultConstructor() {
    Article emptyArticle = new Article();
    emptyArticle.setNombre("0000");
    emptyArticle.setCantidad(1);
    emptyArticle.setPrecio(50.0);
    emptyArticle.setDescuento(5.0);

    String text = emptyArticle.toString();
    System.out.println("DEBUG toString => " + text);

    assertTrue(text.contains("0000"));
    assertTrue(text.contains("50") || text.contains("50,00"));
    assertTrue(text.contains("5") || text.contains("5,0"));
}

}



