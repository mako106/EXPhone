/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chien.controller;

import com.chien.entity.Phone;
import com.chien.model.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chien
 */
public class addPhoneServlet extends HttpServlet {
        DB db = new DB();


   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    try {
            String name = request.getParameter("Name");
            String brand = request.getParameter("Brand");
            String price = request.getParameter("Price");
            String desc = request.getParameter("Description");
            int priceNumber = Integer.parseInt(price);
            Phone p = new Phone(name, brand, priceNumber, desc);
            if (db.addPhone(p)>0) {
                response.sendRedirect("listphone.jsp");
            } else{
                request.setAttribute("error", "Add fail");
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Price invalid");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
