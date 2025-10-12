import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Items;
import com.example.*;

public class testItems {
    
    @Test
    @DisplayName("Constructor")
    void TestConstructor(){
        Items items = new Items("CPU",100.0);
        assertEquals("CPU",items.getNombre());
        assertEquals(100.0,items.getPrecio(),0.0001);
    }

    @Test
    @DisplayName("Getters & Setters")
    void testGettersAndSetters(){

        Items items = new Items("Test",0.0);

        items.setNombre("pantalla");
        assertEquals("pantalla",items.getNombre());

        items.setPrecio(200.0);
        assertEquals(200.0,items.getPrecio(),0.0001);

    }


}
