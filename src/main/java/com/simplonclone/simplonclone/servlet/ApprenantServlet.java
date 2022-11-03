package com.simplonclone.simplonclone.servlet;

import com.simplonclone.simplonclone.entity.Apprenant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.simplonclone.simplonclone.services.ApprenantService;
import java.io.IOException;
import java.util.List;

import java.io.IOException;

@WebServlet(name = "ApprenantServlet", value = "/ApprenantServlet")
public class ApprenantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ApprenantService apprenantService = new ApprenantService();
      List<Apprenant> apprenants = apprenantService.getAll();
        request.setAttribute("apprenants", apprenants);
        request.getRequestDispatcher("apprenant.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action") != null){
            switch (request.getParameter("action")){
                case "add" -> {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");

                    Apprenant newApprenant = new Apprenant();
                    newApprenant.setFirstname(firstName);
                    newApprenant.setLastname(lastName);
                    newApprenant.setEmail(email);
                    newApprenant.setPassword(password);

                    ApprenantService apprenantService = new ApprenantService();
                    apprenantService.add(newApprenant);
                }
                case "delete" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ApprenantService apprenantService = new ApprenantService();
                    apprenantService.deleteByID(id);
                }
                case "update" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");

                    Apprenant newApprenant = new Apprenant();
                    newApprenant.setId(id);
                    newApprenant.setFirstname(firstName);
                    newApprenant.setLastname(lastName);
                    newApprenant.setEmail(email);
                    newApprenant.setPassword(password);

                    ApprenantService apprenantService = new ApprenantService();
                    apprenantService.update(newApprenant);
                }
                case "edit" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ApprenantService apprenantService = new ApprenantService();
                    Apprenant apprenant = apprenantService.findById(id);
                    request.setAttribute("apprenant", apprenant);
                    request.getRequestDispatcher("editApprenant.jsp").forward(request, response);
                }
            }
        }
        response.sendRedirect("AdminServlet");
    }
}
