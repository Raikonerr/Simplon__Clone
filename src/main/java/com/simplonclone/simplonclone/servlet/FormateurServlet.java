package com.simplonclone.simplonclone.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.simplonclone.simplonclone.services.ApprenantService;
import com.simplonclone.simplonclone.services.FormateurService;
import com.simplonclone.simplonclone.services.PromoService;

import com.simplonclone.simplonclone.entity.Apprenant;
import com.simplonclone.simplonclone.entity.Formateur;
import com.simplonclone.simplonclone.entity.Promos;
import java.util.List;
import java.io.IOException;

@WebServlet(name = "FormateurServlet", value = "/FormateurServlet")
public class FormateurServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FormateurService formateurService = new FormateurService();
        List<Formateur> formateurList = formateurService.getAll();
        request.setAttribute("formateurList", formateurList);
        request.getRequestDispatcher("formateurs.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action") != null){
            switch(request.getParameter("action")){
                case "add" -> {
                    Formateur newFormateur = new Formateur();
                    newFormateur.setFirstname(request.getParameter("firstname"));
                    newFormateur.setLastname(request.getParameter("lastname"));
                    newFormateur.setEmail(request.getParameter("email"));
                    newFormateur.setPassword(request.getParameter("password"));
                }
                case "delete" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    FormateurService formateurService = new FormateurService();
                    formateurService.deleteByID(id);
                }
                case "update" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Formateur newFormateur = new Formateur();
                    newFormateur.setId(id);
                    newFormateur.setFirstname(request.getParameter("firstname"));
                    newFormateur.setLastname(request.getParameter("lastname"));
                    newFormateur.setEmail(request.getParameter("email"));
                    newFormateur.setPassword(request.getParameter("password"));
                }
            }
        }
            request.getRequestDispatcher("adminServlet").forward(request, response);
    }
}
