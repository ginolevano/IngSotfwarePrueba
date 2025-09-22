import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("Mensaje de funcion getWordByIndex")

    

}
