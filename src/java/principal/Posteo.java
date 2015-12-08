package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Parisi Germán
 */
public class Posteo {

    public ArrayList<Documento> obtenerCandidatos(Palabra palabra, int r) {
        ImplementacionPosteo posteo = new PosteoBaseDatos();
        return posteo.obtenerCandidatos(palabra, r);
    }
}

interface ImplementacionPosteo {

    public ArrayList<Documento> obtenerCandidatos(Palabra palabra, int r);
}

class PosteoBaseDatos implements ImplementacionPosteo {

    @Override
    public ArrayList<Documento> obtenerCandidatos(Palabra palabra, int r) {
        ArrayList<Documento> documentos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + ConfiguracionInicial.BD + "?user=" + ConfiguracionInicial.USER + "&password=" + ConfiguracionInicial.PASS);
            Statement st = con.createStatement();
            String consulta = "SELECT d.nombre AS nombre, d.enlace AS enlace FROM posteo AS p INNER JOIN documentos AS d ON(p.id_documento=d.id) "
                                         + "INNER JOIN vocabulario AS v ON(p.id_vocabulario=v.id) "
                                         + "WHERE v.texto='" + palabra.getTexto() + "' "
                                         + "ORDER BY tf DESC LIMIT " + r;
            System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String enlace = rs.getString("enlace");
                Documento documento = new Documento(nombre, enlace);
                documentos.add(documento);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PosteoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PosteoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentos;
    }

}
