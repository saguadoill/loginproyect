package com.saguado.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ServicioLogin extends HttpServlet {
    ManagerConetion managerConetion;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        managerConetion = new ManagerConetion();
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        PrintWriter printWriter = resp.getWriter();

        if(req.getParameter("Registrar") != null){
            String sql = "INSERT INTO prueba VALUES (?,?,?)";
            String[] campos ={null,user,pass};
            managerConetion.insertUpdate(sql,campos);
            resp.sendRedirect("./registro.jsp");

        }else if(req.getParameter("Ingresar") != null){
            String sql = "SELECT user,pass FROM prueba WHERE user=? AND pass=?";
            String[] campos = {user,pass};
            if(managerConetion.consultar(sql,campos)){
                resp.sendRedirect("./user_home.jsp");
            }else {
                printWriter.print("Campo usuario y/o contraseña incorrectos!!!");
            }
        }


    }
}
