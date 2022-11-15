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

    request.getRequestDispatcher("WEB-INF/pages/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null){
            switch(request.getParameter("action")){
                case "addPromo" -> {
                    PromoService PromoService = new PromoService();
                    Promos promo = new Promos();
                    promo.setName(request.getParameter("name"));
                    PromoService.add(promo);
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

                case "addApprenant" ->{
                    Apprenant newApprenant = new Apprenant();
                    newApprenant.setFirstname(request.getParameter("firstname"));
                    newApprenant.setLastname(request.getParameter("lastname"));
                    newApprenant.setEmail(request.getParameter("email"));
                    newApprenant.setPassword(request.getParameter("password"));
                    newApprenant.setPromosByPromoId(new ArrayList<>());
                    ApprenantService apprenantService = new ApprenantService();
                    apprenantService.add(newApprenant);
                    response.sendRedirect("AdminServlet");
                }
                case "deleteApprenant" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ApprenantService apprenantService = new ApprenantService();
                    apprenantService.deleteByID(id);
                    response.sendRedirect("AdminServlet");
                }
                case "updateApprenant" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Apprenant newApprenant = new Apprenant();
                    newApprenant.setId(id);
                    newApprenant.setFirstname(request.getParameter("firstname"));
                    newApprenant.setLastname(request.getParameter("lastname"));
                    newApprenant.setEmail(request.getParameter("email"));
                    newApprenant.setPassword(request.getParameter("password"));
                    newApprenant.setPromosByPromoId(new ArrayList<>());
                    ApprenantService apprenantService = new ApprenantService();
                    apprenantService.update(newApprenant);
                    response.sendRedirect("AdminServlet");
                }
                case "addFormateur" ->{
                    Formateur newFormateur = new Formateur();
                    newFormateur.setFirstname(request.getParameter("firstname"));
                    newFormateur.setLastname(request.getParameter("lastname"));
                    newFormateur.setEmail(request.getParameter("email"));
                    newFormateur.setPassword(request.getParameter("password"));
                    FormateurService formateurService = new FormateurService();
                    formateurService.add(newFormateur);
                    response.sendRedirect("AdminServlet");
                }
                case "deleteFormateur" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    FormateurService formateurService = new FormateurService();
                    formateurService.deleteByID(id);
                    response.sendRedirect("AdminServlet");
                }
                case "updateFormateur" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Formateur newFormateur = new Formateur();
                    newFormateur.setId(id);
                    newFormateur.setFirstname(request.getParameter("firstname"));
                    newFormateur.setLastname(request.getParameter("lastname"));
                    newFormateur.setEmail(request.getParameter("email"));
                    newFormateur.setPassword(request.getParameter("password"));
                    FormateurService formateurService = new FormateurService();
                    formateurService.update(newFormateur);
                    response.sendRedirect("AdminServlet");
                }

            }
        }

    }
}
