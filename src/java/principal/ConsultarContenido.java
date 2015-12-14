package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            byte[] infoExtra = null;
            int tamInfoExtra = 0;
            if (fileName.endsWith(".pdf")) {
                //response = (HttpServletResponse) ctx.getExternalContext().getResponse();
                contentType = "application/pdf";
            } else if (fileName.endsWith(".html")) {
                contentType = "text/html";
                String urlOriginal = obtenerURLOriginal(archivoAEnviar);
                infoExtra = urlOriginal.getBytes();
                tamInfoExtra = infoExtra.length;
            }
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
            response.setContentLength((int) archivoAEnviar.length() + tamInfoExtra);

            FileInputStream fileInputStream = new FileInputStream(archivoAEnviar);
            try (OutputStream responseOutputStream = response.getOutputStream()) {
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
                if (tamInfoExtra > 0) {
                    responseOutputStream.write(infoExtra, 0, tamInfoExtra);
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

    private String obtenerURLOriginal(File html) {
        try {
            Scanner sc = new Scanner(html);
            String primerLinea = sc.nextLine();
            primerLinea = primerLinea.substring(10, primerLinea.length() - 3);

            String codigo = "<script>"
                    + "var parrafo = document.createElement('p');"
                    + "var enlace = document.createElement('a');"
                    + "var contenido = document.createTextNode('Ir al original');"
                    + "enlace.appendChild(contenido);"
                    + "enlace.href = '" + primerLinea + "';"
                    + "parrafo.appendChild(enlace);"
                    + "parrafo.style.position='absolute';"
                    + "parrafo.style.top='10px';"
                    + "parrafo.style.fontSize='14px';"
                    + "parrafo.style.zIndex='10000000';"
                    + "parrafo.style.backgroundColor='gray';"
                    + "parrafo.style.padding='10px';"
                    + "parrafo.style.borderRadius='5px';"
                    + "document.body.appendChild(parrafo);"
                    + "</script>";

            return codigo;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConsultarContenido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
