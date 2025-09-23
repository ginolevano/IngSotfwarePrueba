package com.example;

import java.util.ArrayList;
import java.util.List;

public class Searcher {

    // Checks if the phrase exists in the list
    public boolean searchExactPhrase(String phrase, String[] names) {
        for (String item : names) {
            if (item.equals(phrase)) {
                return true;
            } // Quitamos el else que devolvía false
            // porque hacía que el método terminara al comprobar solo el primer elemento.
            // Eso significaba que si la primera palabra no coincidía, ya detiene la búsqueda.
            //Lo que queremos es recorrer todo el array:
            // y si encontramos la palabra → devolvemos true
            //si terminamos el recorrido sin encontrarla → devolvemos false
        }
        return false;
    }

    // Simple contains check
    public boolean searchWord(String word, List<String> list) {
        return list.contains(word);
    }

    // Get element by index safely
    public String getWordByIndex(List<String> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null; // Avoid IndexOutOfBounds
    }
    
    // New: find elements starting with a given prefix
    public List<String> searchByPrefix(String prefix, List<String> list) {
        List<String> results = new ArrayList<>();
        for (String element : list) {
            if (element.startsWith(prefix)) {
                results.add(element);
            }
        }
        return results;
    }

    // New: filter all elements that contain a given keyword
    public List<String> filterByKeyword(String keyword, List<String> list) {
        List<String> results = new ArrayList<>();
        for (String element : list) {
            if (element.contains(keyword)) {
                results.add(element);
            }
        }
        return results;
    }

    public String searchExactPhrase(String phrase, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchExactPhrase'");
    }

	public String getWordByIndex(String[] name, int index) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getWordByIndex'");
	}
}