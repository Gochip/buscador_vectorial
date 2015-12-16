package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Germán Parisi
 */
public class Principal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String frase = request.getParameter("buscar").toLowerCase();
        Controlador controlador = new Controlador();
        long tiempoInicial = System.currentTimeMillis();
        List<? extends Documento> documentos = controlador.buscar(frase, 15);
        long tiempoFinal = System.currentTimeMillis();
        long tiempoBusqueda = tiempoFinal - tiempoInicial;
        HttpSession sesion = request.getSession();
        sesion.setAttribute("documentos", documentos);
        sesion.setAttribute("frase", frase);
        sesion.setAttribute("tiempoBusqueda", tiempoBusqueda);
        response.sendRedirect("/buscador_vectorial/enlaces.jsp");
        /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Principal</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Enlaces</h1>");
//            String frase = request.getParameter("buscar");
//            Controlador controlador = new Controlador();
//            List<? extends Documento> documentos = controlador.buscar(frase, 10);
//            for (Documento documento : documentos) {
//                out.print("<li>");
//                out.print("<a href=\"ConsultarContenido?contenido=");
//                out.print(documento.getEnlace());
//                out.print("\">");
//                out.print(documento.getNombre());
//                out.print("</a>");
//                out.print("</li>");
//            }
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
