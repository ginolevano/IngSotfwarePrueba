//IMPORTAMOS PACKAGE...
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.*;
import com.example.model.Calculator;

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
            assertEquals(20,result);
        }

        @Test
        @DisplayName("Multiply with zero should return zero")
        void TestMultiplyZero(){
            int result = calculator.multiply(0,5);
            assertEquals(0,result);
        }
        
        @Test
        @DisplayName("Simple Multiply__null should work")
        void TestMultiply__null(){

        }
        

        @Test
        @DisplayName("Strings shoudl work? ")
        void TestString(){            
            String texto = calculator.concat("a","b");
            assertEquals("ab", texto);
        }

        @Test
        @DisplayName("Concat should return EMPTY when input is null")
        void testConcatNull(){
            
            String resultado = calculator.concat(null,"b");
            assertEquals(Calculator.EMPTY,resultado);
            resultado = calculator.concat("a", null);
            assertEquals(Calculator.EMPTY,resultado);
            resultado = calculator.concat(null,null);
            assertEquals(Calculator.EMPTY,resultado);
        }

        @Test
        @DisplayName("double should work!!!")
        void TestDouble(){
            Double suma = calculator.sum(10.0,10.0);
            assertEquals(20.0 , suma,0.0001);
        }      
        
        
        @Test
        @DisplayName("Sum with negative numbers should work")
        void testNegativeSum(){
            double resultado = calculator.sum(-5.0,-5.0);
            assertEquals(-10.0,resultado,0.0001);
        }

        @Test
        @DisplayName("Discount should work!!")
        void TestDescount(){
            double descuento = calculator.discount(10.0, 20.0);
            assertEquals(8.0, descuento);
        }
//testeamos q lo valores esten fuera de rango
        @Test
        @DisplayName("simple Discount invalid not work") 
        void TestDescount_Invalido(){
            assertThrows(IllegalArgumentException.class,() -> calculator.discount(100.0,-5.0));
            assertThrows(IllegalArgumentException.class,() -> calculator.discount(100.0,150.0));
        }
//Anota correctamente q es un test
        @Test 
        //le estamos dando un nombre Descriptivo al Testing
        @DisplayName("CalculatorTotal should work!!") 
        void TestTotal(){
            List<Double> calculoTotal = Arrays.asList(0.1, 0.1);
            double result = calculator.calculateTotal(calculoTotal);
            assertEquals(0.2,result,0.0001);
        }

        @Test
        @DisplayName("Empty list should return 0.0")
        void testVacioTotal(){
            List<Double> listaVacia = new ArrayList<>();
            double resultado = calculator.calculateTotal(listaVacia);
            assertEquals(0.0,resultado,0.0001);
        }
        
    }
    

