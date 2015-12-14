<%-- 
    Document   : enlaces
    Created on : 06/12/2015, 20:49:50
    Author     : gochi
--%>

<%@page import="java.util.List"%>
<%@page import="principal.Documento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Documentos encontrados</title>
        <link rel="stylesheet" type="text/css" href="css/enlaces.css" />
    </head>
    <body>
        <a href="index.html" >Volver</a>
        <div id="divTitulo">Documentos encontrados</div>
        <ul>
            <%
                List<? extends Documento> documentos = (List<? extends Documento>) request.getSession().getAttribute("documentos");
                if (documentos.isEmpty()) {
                    %>
                    <li>No se encontraron documentos</li>
                    <%
                } else {
                    for (Documento documento : documentos) {
                        String enlace = documento.getEnlace();
                        String nombre = documento.getNombre();
                    %><li>
                        <a href="ConsultarContenido?contenido=<%= enlace%>" target="_blank"><%= nombre%></a>
                    </li><%
                    }
                }
            %>
        </ul>
    </body>
</html>
