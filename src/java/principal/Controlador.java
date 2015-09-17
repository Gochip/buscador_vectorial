package principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import javafx.scene.control.TreeSortMode;

/**
 *
 * @author Parisi Germán
 */
public class Controlador {

    private Vocabulario vocabulario;

    public Controlador() {
        this.vocabulario = cargarVocabulario();
    }

    /**
     * Algoritmo principal del buscador vectorial. Recibe una frase a buscar y
     * retorna los R documentos más relevantes.
     *
     * @param frase
     * @return
     */
    public List<? extends Documento> buscar(String frase, int r) {
        ArrayList<NodoDocumento> candidatos = new ArrayList<>();
        Posteo posteo = new Posteo();
        LinkedList<Palabra> palabras = getPalabras(frase);

        for (Palabra palabra : palabras) {
            List<Documento> cantidatosDePalabra = posteo.obtenerCandidatos(palabra, r);
            mejorarEnElRanking(candidatos, cantidatosDePalabra);
        }

        return candidatos.subList(0, Math.min(r, candidatos.size()));
    }

    /**
     * Mejora en el ranking si el nuevo candidato ya era un candidato.
     *
     * @param candidatos
     * @param nuevosCandidatos
     */
    private void mejorarEnElRanking(List<NodoDocumento> candidatos, List<Documento> nuevosCandidatos) {
        for (Documento nuevo : nuevosCandidatos) {
            int indiceNuevoCandidato = candidatos.indexOf(nuevo);
            System.out.println("INDICE" + indiceNuevoCandidato);
            if (indiceNuevoCandidato < 0) {
                // Nuevo...
                NodoDocumento n = new NodoDocumento(nuevo, 1);
                candidatos.add(n);
            } else {
                // Ya era un candidato...
                NodoDocumento nodo = candidatos.get(indiceNuevoCandidato);
                nodo.addApariciones();
            }
        }
        System.out.println(candidatos);
        candidatos.sort(new ComparadorInverso());
    }

    /**
     * Retorna las palabras de la frase en orden de importancia. La palabra más
     * importante será aquella que aparece en menos documentos.
     *
     * @return la palabra más importante.
     */
    private LinkedList<Palabra> getPalabras(String frase) {
        String partes[] = frase.split(" ");
        TreeSet<Palabra> arbol = new TreeSet<>();
        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i];
            Palabra palabra = vocabulario.getPalabra(parte);
            if (palabra != null) {
                arbol.add(palabra);
            }
        }
        return new LinkedList<>(arbol);
    }

    /**
     *
     * @return
     */
    private Vocabulario cargarVocabulario() {
        Vocabulario v = new Vocabulario();
        Palabra pal1 = new Palabra("computadora", 3, 8);
        Palabra pal2 = new Palabra("notebook", 2, 6);
        Palabra pal3 = new Palabra("problema", 2, 5);
        Palabra pal4 = new Palabra("mesa", 4, 5);
        Palabra pal5 = new Palabra("votar", 3, 15);
        v.putPalabra(pal1.getTexto(), pal1);
        v.putPalabra(pal2.getTexto(), pal2);
        v.putPalabra(pal3.getTexto(), pal3);
        v.putPalabra(pal4.getTexto(), pal4);
        v.putPalabra(pal5.getTexto(), pal5);
        return v;
    }

}

class NodoDocumento extends Documento {

    private int apariciones;

    public NodoDocumento(Documento documento, int apariciones) {
        this(documento.getNombre(), documento.getEnlace(), apariciones);
    }

    public NodoDocumento(String nombre, String enlace, int apariciones) {
        super(nombre, enlace);
        this.apariciones = apariciones;
    }

    public void addApariciones() {
        this.apariciones++;
    }

    public int getApariciones() {
        return this.apariciones;
    }

}

class ComparadorInverso implements Comparator<NodoDocumento> {

    @Override
    public int compare(NodoDocumento o1, NodoDocumento o2) {
        return - o1.getApariciones() + o2.getApariciones();
    }

}
