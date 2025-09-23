import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Searcher;

public class SearcherTest {

    private Searcher searcher;

    @BeforeEach
    void setUp(){
        searcher = new Searcher();
    }
    @Test
    @DisplayName("ESTE EL NOMBRE QUE PONEMOS A TEST UNITARIO")
    void TestsearchExactPhrase(){
        String[] names = {"gino","gino"};
//HEMOS ENCONTRADO UN ERROR EN ESTE FRACMENTO DE CODIGO.... LINEA 13
//EL ELSE DEBERIA ESTA FUERA DEL IF...
        boolean result = searcher.searchExactPhrase("gino",names);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("ESTE ES EL MENSAJITO DEL FUNCION searchWord")
    void TestsearchWord(){
    String[] name = {"word"};
        boolean result =searcher.searchExactPhrase("word", name);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("simple getWordByIndex should work!!")
    void TestgetWordByIndex(){
        List<String> name = Arrays.asList("Laura","Gino");
        String word = searcher.getWordByIndex(name, 1);
        assertEquals("Gino", word);
    }

    @Test
    @DisplayName("Simple searchByPrefix should work!!")
    void TestsearchByPrefix(){
        List<String> nameList = Arrays.asList("Gino","Sonqo");
        List<String> result = searcher.searchByPrefix("Gin",nameList);
        assertEquals(Arrays.asList("Gino"), result);
    }

    @Test
    @DisplayName("Simple filterByKeyword should work")
    void TestfilterByKeyword(){
        List<String> name = Arrays.asList("1234","123","12345");
        List<String> result =  searcher.filterByKeyword("123",name);
        //esperamos ambos resultado porque ambos contienen 123
        assertEquals(Arrays.asList("1234","123","12345"),result);
    }

    
    

}
