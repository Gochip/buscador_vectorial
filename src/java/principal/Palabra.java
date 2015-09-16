package principal;

/**
 *
 * @author Parisi Germán
 */
public class Palabra implements Comparable<Palabra>{
    private String texto;
    /**
     * Cantidad de documentos que aparece esta palabra.
     */
    private int nr;
    /**
     * Frecuenta máxima de esta palabra en un documento.
     */
    private int maxtf;

    public Palabra(String texto, int nr, int maxtf) {
        this.texto = texto;
        this.nr = nr;
        this.maxtf = maxtf;
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getMaxtf() {
        return maxtf;
    }

    public void setMaxtf(int maxtf) {
        this.maxtf = maxtf;
    }
    
    @Override
    public int compareTo(Palabra o) {
        return texto.compareTo(o.texto);
    }

    @Override
    public String toString() {
        return "Palabra{" + "texto=" + texto + ", nr=" + nr + ", maxtf=" + maxtf + '}';
    }
}
