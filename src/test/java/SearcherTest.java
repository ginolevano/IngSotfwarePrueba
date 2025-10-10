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
    void TestSearchExactPhrase(){
        String[] names = {"gino","gino"};
//HEMOS ENCONTRADO UN ERROR EN ESTE FRAGMENTO DE CÓDIGO.... LINEA 13
        boolean result = searcher.searchExactPhrase("gino",names);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("simple searchExactPhrase should work false")
    void Test2SearchExactPhrase(){
        String[] nameDiferent = {"pepe","gino"};
        boolean resultado = searcher.searchExactPhrase("Juan",nameDiferent);
        assertEquals(false,resultado);
    }
//we found 

    @Test
    @DisplayName("simple searchWord should work!!")
    void TestSearchWord(){
        List<String> name = Arrays.asList("peep");
        boolean resultado = searcher.searchWord("peep" ,name);
        assertTrue(resultado);
    }

//we dont found 
    @Test
    @DisplayName("simple searchWord should work!!")
    void TestSearchWord_NotFound(){
        List<String> name = Arrays.asList("gino","pepe");
        boolean resultado = searcher.searchWord("giino",name);
        assertEquals(false,resultado);
    }

    @Test 
    @DisplayName("simple searchWordNotFound should return false when word no found")
    void TestSearchWordNotFound(){
        List<String> name = Arrays.asList("Pablo","Gino");
        boolean resultado = searcher.searchWord("Pabloo",name);
        assertEquals(false,resultado);
    }


    @Test
    @DisplayName("simple getWordByIndex should work!!")
    void TestGetWordByIndex(){
        List<String> name = Arrays.asList("Laura","Gino");
        String word = searcher.getWordByIndex(name, 1);
        assertEquals("Gino", word);
    }

    @Test
    @DisplayName("simple getWordByIndex should work!!")
    void Test2GetWordByIndex(){
        List<String> names = Arrays.asList("juan","luis");
        String result = searcher.getWordByIndex(names,5);
        assertEquals(null,result);
    }

// we found
    @Test
    @DisplayName("Simple searchByPrefix should work!!")
    void TestSearchByPrefix(){
        List<String> nameList = Arrays.asList("Gino","Sonqo");
        List<String> result = searcher.searchByPrefix("Gin",nameList);
        assertEquals(Arrays.asList("Gino"), result);
    }
//we dont found
    @Test
    @DisplayName("Simple searchByPrefix should work!!")
    void TestSearchByPrefix_NotFound(){
        List<String> name = Arrays.asList("Gino","Sonqo");
        List<String> resultado = searcher.searchByPrefix("ginoo",name);
        assertTrue(resultado.isEmpty());
    }
//isEmpty

@Test
@DisplayName("simple searchByPrefix_isEmpty should handle empty ")
void  TestSearchByPrefix_isVacio(){
    List<String> name = new ArrayList<>();
    List<String> resultado = searcher.searchByPrefix("gino",name);
    assertTrue(resultado.isEmpty());

}


//we found
    @Test
    @DisplayName("Simple filterByKeyword should work")
    void TestFilterByKeyword(){
        List<String> name = Arrays.asList("1234","123","12345");
        List<String> result =  searcher.filterByKeyword("123",name);
        //esperamos ambos resultado porque ambos contienen 123
        assertEquals(Arrays.asList("1234","123","12345"),result);
    }
    //we dont found
    @Test
    @DisplayName("Simple filterByKeyword should work")
    void TestFilterByKeyword_NotFound(){
        List<String> name = Arrays.asList("123","567");
        List<String> resultado = searcher.filterByKeyword("gino",name);
        assertTrue(resultado.isEmpty());
    }


    @Test
    @DisplayName("Searcher default constructor should be work")
    void TestSearcherConstructor(){
        Searcher searcher = new Searcher();
        assertNotNull(searcher);
    }

}
