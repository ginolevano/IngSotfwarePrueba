import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Article;
import com.example.*;

public class testArticle {
    
    @Test
    @DisplayName("Constructor")
    void TestConstructor(){
        Article Article = new Article("CPU",100.0);
        assertEquals("CPU",Article.getNombre());
        assertEquals(100.0,Article.getPrecio(),0.0001);
    }

    @Test
    @DisplayName("Getters & Setters")
    void testGettersAndSetters(){

        Article Article = new Article("Test",0.0);

        Article.setNombre("pantalla");
        assertEquals("pantalla",Article.getNombre());

        Article.setPrecio(200.0);
        assertEquals(200.0,Article.getPrecio(),0.0001);

    }


}
