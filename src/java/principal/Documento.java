package principal;

import java.util.Objects;

/**
 *
 * @author Parisi Germán
 */
public class Documento {
    private String nombre;
    private String enlace;

    public Documento(String nombre, String enlace) {
        this.nombre = nombre;
        this.enlace = enlace;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Documento{" + "nombre=" + nombre + ", enlace=" + enlace + '}';
    }
    
}
