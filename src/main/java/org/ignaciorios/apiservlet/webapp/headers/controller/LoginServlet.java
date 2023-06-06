package org.ignaciorios.apiservlet.webapp.headers.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ignaciorios.apiservlet.webapp.headers.service.LoginService;
import org.ignaciorios.apiservlet.webapp.headers.service.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       LoginService auth = new LoginServiceImpl();
       Optional<String> cookieOptional = auth.getUsername(req);


       if(cookieOptional.isPresent()){
           resp.setContentType("text/html;charset=UTF-8");

           try (PrintWriter out = resp.getWriter()) {

               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("    <meta charset=\"UTF-8\">");
                       out.println("    <title>Bienvenida</title>");
               out.println("    <link rel=\"stylesheet\" href=\"styles.css\">");
               out.println("</head>");
               out.println("<body>");
               out.println("<h2>HOLAAAAA</h2>");
               out.println("<h2>" +"HOLAAAAA --->>  "+cookieOptional.get()+" ya has iniciado sesion "+"</h2>");
               out.println("<p><a href='"+req.getContextPath()+"/index.html'>VOLVER</a></p>");
               out.println("</body>");
               out.println("</html>");
           }

       }else{
           getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);

       }




    }

    final static String USERNAME = "ignacio";
    final static String PASSWORD = "123456";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              String username = req.getParameter("username");
              String password = req.getParameter("password");

              if(USERNAME.equals(username) && PASSWORD.equals(password)){
                  resp.setContentType("text/html;charset=UTF-8");

                  Cookie usernameCookie = new Cookie("username",username);
                  resp.addCookie(usernameCookie);

                  try (PrintWriter out = resp.getWriter()) {

                      out.println("<!DOCTYPE html>");
                      out.println("<html>");
                      out.println("<head>");
                      out.println("    <meta charset=\"UTF-8\">");
                              out.println("    <title>tituloo de la tag</title>");
                      out.println("    <link rel=\"stylesheet\" href=\"styles.css\">");
                      out.println("</head>");
                      out.println("<body>");
                      out.println("<h2>TODO OK </h2>");
                      out.println("<h3> Holaaa como estas "+ username  +"</h3>");
                      out.println("<p><a href='"+req.getContextPath()+"/index.html'>VOLVER</a></p>");
                      out.println("</body>");
                      out.println("</html>");
                  }

              }else{
                  resp.sendError(404,"No esta autorizado a ingresar ....:-(");
              }


    }
}
