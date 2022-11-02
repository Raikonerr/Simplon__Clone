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
        ApprenantService a = new ApprenantService();
        List<Apprenant> apprenants = a.getAll();
        request.setAttribute("apprenants", apprenants);
        request.getRequestDispatcher("apprenants.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post method
        if(request.getParameter("action") != null){
            if(request.getParameter("action").equals("add")){
                Apprenant apprenant = new Apprenant();
                apprenant.setFirstname(request.getParameter("firstname"));
                apprenant.setLastname(request.getParameter("lastname"));
                apprenant.setEmail(request.getParameter("email"));
                apprenant.setPassword(request.getParameter("password"));
                ApprenantService apprenantService = new ApprenantService();
                apprenantService.add(apprenant);
            }if (request.getParameter("action").equals("delete")){
                ApprenantService apprenantService = new ApprenantService();
                apprenantService.deleteByID(Integer.parseInt(request.getParameter("id")));
            }
        }

    }
}
