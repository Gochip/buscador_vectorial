package principal;

import java.util.ArrayList;

/**
 *
 * @author Parisi Germán
 */
public class Posteo {
    public ArrayList<Documento> obtenerCandidatos(Palabra palabra, int r){
        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.add(new Documento("Google", "http://www.google.com"));
        documentos.add(new Documento("PaBex", "http://pabex.com.ar"));
        documentos.add(new Documento("Facebook", "http://www.faceboook.com"));
        documentos.add(new Documento("Javahispano", "http://www.javahispano.org"));
        return documentos;
    }
}
