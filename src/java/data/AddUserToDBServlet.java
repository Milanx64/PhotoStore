/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.User;
import data.UserDB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author comp-one
 */
public class AddUserToDBServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            // get parameters from the request
            String firstName = request.getParameter ("name");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter ("email");
            String password= request.getParameter ("password");
            // create the User object
            User user;
            user = new User();
            user.setEmail(email);
            user.setName(firstName);
            user.setLastname(lastName);
            user.setPassword(password);
            
            String url = "";
            String message = "";
            
            if (UserDB.emailExists(user.getEmail())) {
                message = "This email address already exists<br>" +
                        "Please enter another email address.";
                url = "view/signup.jsp";
            }
            else {
                UserDB.insert (user);
                url = "view/userprofile/myprofil.jsp";
              
            }
            
            // store the user and message in the session
            session.setAttribute("user", user);
            request.setAttribute("message", message);
            Cookie cookieEmail = new Cookie("cookieEmail", email);
            cookieEmail.setMaxAge(30*60*24*365);
            cookieEmail.setPath("/");
            response.addCookie(cookieEmail);
            // forward the request and response to the view
            request.getRequestDispatcher(url).forward(request, response);
                   
        } catch (SQLException ex) {
            Logger.getLogger(AddUserToDBServlet.class.getName()).log(Level.SEVERE, null, ex);
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