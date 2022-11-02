package com.simplonclone.simplonclone.servlet;

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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
