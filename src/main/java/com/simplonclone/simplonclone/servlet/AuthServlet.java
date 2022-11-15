package com.simplonclone.simplonclone.servlet;

import com.simplonclone.simplonclone.entity.Admin;
import com.simplonclone.simplonclone.entity.Apprenant;
import com.simplonclone.simplonclone.entity.Formateur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.simplonclone.simplonclone.services.ApprenantService;
import com.simplonclone.simplonclone.services.FormateurService;
import com.simplonclone.simplonclone.services.AdminService;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("action") != null){
            switch (request.getParameter("action")){
                case "admin" -> {
                    AdminService adminService = new AdminService();
                    Admin admin = adminService.login(request.getParameter("email"), request.getParameter("password"));
                    if (admin != null) {
                        request.getSession().setAttribute("admin", admin);
                        response.sendRedirect("AdminServlet");
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                }
                    case "apprenant" -> {
                        ApprenantService apprenantService = new ApprenantService();
                        Apprenant apprenant = apprenantService.login(request.getParameter("email"), request.getParameter("password"));
                        if(apprenant != null){
                            request.getSession().setAttribute("apprenant", apprenant);
                            response.sendRedirect("ApprenantServlet");
                        }else{
                            response.sendRedirect("login.jsp");
                        }
                    }
                    case "formateur" -> {
                        FormateurService formateurService = new FormateurService();
                        Formateur formateur = formateurService.login(request.getParameter("email"), request.getParameter("password"));
                        if(formateur != null){
                            request.getSession().setAttribute("formateur", formateur);
                            response.sendRedirect("FormateurServlet");
                        }else{
                            response.sendRedirect("login.jsp");
                        }
                    }
                }
            }
        }

    }

