package principal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Parisi Germán
 */
public class Controlador {

    public Controlador() {

    }

    public List<Documento> buscar(String frase) {
        ArrayList<Documento> candidatos = new ArrayList<>();
        Posteo posteo = new Posteo();
        int r = 10;
        LinkedList<Palabra> palabras = getPalabras(frase);

        for (Palabra palabra : palabras) {
            List<Documento> cantidatosDePalabra = posteo.obtenerCandidatos(palabra, r);
            mejorarEnElRanking(candidatos, cantidatosDePalabra);
        }

        return candidatos.subList(0, Math.min(r, candidatos.size()));
    }

    private void mejorarEnElRanking(List<Documento> candidatos, List<Documento> nuevosCandidatos) {
        candidatos.addAll(nuevosCandidatos);
    }

    /**
     * Retorna las palabras de la frase en orden de importancia. La palabra más
     * importante será aquella que aparece en menos documentos.
     *
     * @return la palabra más importante.
     */
    private LinkedList<Palabra> getPalabras(String frase) {
        String partes[] = frase.split(" ");
        LinkedList<Palabra> palabras = new LinkedList<>();
        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i];
            palabras.add(new Palabra(parte, 5, 3));
        }
        return palabras;
    }

    /**
     * 
     * @return 
     * @deprecated 
     */
    private Vocabulario cargarVocabulario() {
        Vocabulario vocabulario = new Vocabulario();
        Palabra pal1 = new Palabra("computadora", 10, 5);
        Palabra pal2 = new Palabra("notebook", 15, 4);
        Palabra pal3 = new Palabra("problema", 20, 8);
        Palabra pal4 = new Palabra("mesa", 2, 2);
        Palabra pal5 = new Palabra("votar", 5, 20);
        vocabulario.putPalabra(pal1.getTexto(), pal1);
        vocabulario.putPalabra(pal2.getTexto(), pal2);
        vocabulario.putPalabra(pal3.getTexto(), pal3);
        vocabulario.putPalabra(pal4.getTexto(), pal4);
        vocabulario.putPalabra(pal5.getTexto(), pal5);
        return vocabulario;
    }

}
