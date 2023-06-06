package org.ignaciorios.apiservlet.webapp.headers.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ignaciorios.apiservlet.webapp.headers.models.Producto;
import org.ignaciorios.apiservlet.webapp.headers.service.LoginService;
import org.ignaciorios.apiservlet.webapp.headers.service.LoginServiceImpl;
import org.ignaciorios.apiservlet.webapp.headers.service.ProductoService;
import org.ignaciorios.apiservlet.webapp.headers.service.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html"})
public class ProductoxlsServervlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        // Crear instancia del servicio de productos
        ProductoService ps = new ProductoServiceImpl();
        // Obtener lista de productos
        List<Producto> productos = ps.listar();
        // Configurar el tipo de contenido de la respuesta HTTP
        resp.setContentType("text/html;charset=UTF-8");
        // Obtener la ruta del servlet
        String servletPath = req.getServletPath();
        // Verificar si la solicitud es para exportar a XLS

        try (PrintWriter out = resp.getWriter()) {
            // Respuesta para la solicitud HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Listado</title>");
            out.println("    <style>");
            out.println("        table {");
            out.println("            border-collapse: collapse;");
            out.println("            width: 100%;");
            out.println("        }");
            out.println("        th, td {");
            out.println("            padding: 8px;");
            out.println("            text-align: left;");
            out.println("            border-bottom: 1px solid #ddd;");
            out.println("        }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Listado</h2>");

            if (cookieOptional.isPresent()) {
                // Mostrar el nombre de usuario si existe una cookie válida
                out.println("<h1>HOLA <<" + cookieOptional.get() + ">> </h1>");
            } else {
                // Mostrar un mensaje de bienvenida para los invitados
                out.println("<h1>HOLA, Invitado</h1>");
            }

            // Generar la tabla de productos
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Tipo</th>");
            if (cookieOptional.isPresent()) {
                // Mostrar la columna de precio solo para usuarios autenticados
                out.println("<th>Precio</th>");
            }
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Iterar sobre la lista de productos y agregar cada uno como una fila en la tabla
            for (Producto p : productos) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if (cookieOptional.isPresent()) {
                    // Mostrar el precio solo para usuarios autenticados
                    out.println("<td>" + p.getPecio() + "</td>");
                }
                out.println("</tr>");
            }

            // Agregar un enlace para volver a la página principal
            out.println("<p><a href='" + req.getContextPath() + "/index.html'>VOLVER</a></p>");

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } //fintry
    }

}
