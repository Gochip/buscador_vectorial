package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Parisi Germán
 */
public class Vocabulario implements Iterable<Palabra>{
    private HashMap<String, Palabra> palabras;
    
    public Vocabulario(){
        palabras = new HashMap<>();
    }
    
    public void putPalabra(String texto, Palabra palabra){
        palabras.put(texto, palabra);
    }
    
    public Palabra getPalabra(String texto){
        return palabras.get(texto);
    }

    @Override
    public Iterator<Palabra> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return palabras.toString();
    }

}
