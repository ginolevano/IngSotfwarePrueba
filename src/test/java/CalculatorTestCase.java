//IMPORTAMOS PACKAGE...
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.*;

/*import scala.collection.immutable.ArraySeq;*/

public class CalculatorTestCase {

    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
    }
// SIEMPRE TIENE Q SER SOLO
        @Test 
        @DisplayName("Simple Multiply should work")
        void TestMultiply(){
            int result = calculator.multiply(4, 5);
            // assertEquals(20, calculator.multiply(4, 5 ),
            // "Regular multiplicacion shouldm work");
            assertEquals(result, 20);
        }
        @Test
        @DisplayName("Strings shoudl work? ")
        void TestString(){            
            String texto = calculator.concat("a","b");
            assertEquals("ab", texto);
        }


        @Test
        @DisplayName("double should work!!!")
        void TestDouble(){
            Double suma = calculator.sum(10.0,10.0);
            assertEquals(suma, 20.0);
        }        

        @Test
        @DisplayName("Discount should work!!")
        void TestDescount(){
            double descuento = calculator.discount(10.0, 20.0);
            assertEquals(8.0, descuento);
        }
//Anota correctamente q es un test
        @Test 
        //le estamos dando un nombre Descriptivo al Testing
        @DisplayName("CalculatorTotal should work!!") 
        void TestTotal(){
            List<Double> calculoTotal = Arrays.asList(0.1, 0.1);
            double result = calculator.calculateTotal(calculoTotal);
            assertEquals(0.2,result);
        }
        
    }
    

