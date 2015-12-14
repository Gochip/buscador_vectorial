package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultarContenido", urlPatterns = {"/ConsultarContenido"})
public class ConsultarContenido extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String contenido = request.getParameter("contenido");

        contenido = contenido.replace('\\', '/');
        contenido = ConfiguracionInicial.DIRECTORIO_ARCHIVOS + contenido;
        File archivoAEnviar = new File(contenido);

        if (archivoAEnviar.exists()) {
            String fileName = archivoAEnviar.getName();
            String contentType = "application/pdf";
            if (fileName.endsWith(".pdf")) {
                //response = (HttpServletResponse) ctx.getExternalContext().getResponse();
                contentType = "application/pdf";
            } else if (fileName.endsWith(".html")) {
                contentType = "text/html";
            }
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
            response.setContentLength((int) archivoAEnviar.length());

            FileInputStream fileInputStream = new FileInputStream(archivoAEnviar);
            try (OutputStream responseOutputStream = response.getOutputStream()) {
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
            }

            //String contentType = "application/vnd.ms-excel";
        }

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
