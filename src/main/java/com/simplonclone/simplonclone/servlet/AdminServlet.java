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
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ApprenantService ApprenantService = new ApprenantService();
    FormateurService FormateurService = new FormateurService();
    PromoService PromoService = new PromoService();

    List<Apprenant> apprenantList = ApprenantService.getAll();
    List<Formateur> formateurList = FormateurService.getAll();
    List<Promos> promoList = PromoService.getAll();

    request.setAttribute("apprenantList", apprenantList);
    request.setAttribute("formateurList", formateurList);
    request.setAttribute("promoList", promoList);

    request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null){
            switch(request.getParameter("action")){
                case "addPromo" -> {
                    Promos newPromo = new Promos();
                    newPromo.setName(request.getParameter("name"));
                    int formateurId = Integer.parseInt(request.getParameter("formateurId"));
                    if(formateurId != 0){
                        newPromo.setFormateurId(formateurId);
                    }
                    PromoService promoService = new PromoService();
                    promoService.add(newPromo);
                    response.sendRedirect("AdminServlet");

                }
                case "deletePromo" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    PromoService promoService = new PromoService();
                    promoService.deleteByID(id);
                    response.sendRedirect("AdminServlet");
                }
                case "updatePromo" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Promos newPromo = new Promos();
                    newPromo.setId(id);
                    newPromo.setName(request.getParameter("name"));
                    int formateurId = Integer.parseInt(request.getParameter("formateurId"));
                    if(formateurId != 0){
                        newPromo.setFormateurId(formateurId);
                    }
                    PromoService promoService = new PromoService();
                    promoService.update(newPromo);
                    response.sendRedirect("AdminServlet");
                }

            }
        }

    }
}
