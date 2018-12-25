/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photo.catalog;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.*;
import data.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.*;
//import util.*;

/**
 *
 * @author comp-one
 */
public class CheckUserServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckUserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String url="";
        //If the user object dosen't exist, chek for the email coolkie
        if(user == null){
            Cookie[] cookies = request.getCookies();
            String email = CookieUtil.getCookieValue(cookies, "cookieEmil");
            String name = CookieUtil.getCookieValue(cookies, "cookieName");
            String lastName = CookieUtil.getCookieValue(cookies, "cookieLastName");
            //if the email cookie does not exist, go to the registration page
            if(email == null || email.equals("")){
                url="/view/signup.jsp";
            }/* if email cookie does exist, create the User object from the
            email cookie and skip the registration page*/
            else{
                try {
                    user = UserDB.selectUser(email);
                } catch (SQLException ex) {
                    Logger.getLogger(CheckUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("user", user);
                url = "/view/userprofile/myprofil.jsp";
            }
        }
        else{
            url = "/view/userprofile/myprofil.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
            HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("admin");
        String url="";
        //If the user object dosen't exist, chek for the email coolkie
        if(admin == null){
            Cookie[] cookies = request.getCookies();
            String email = CookieUtil.getCookieValue(cookies, "adminCookie");
            String name = CookieUtil.getCookieValue(cookies, "admincookieName");
            String lastName = CookieUtil.getCookieValue(cookies, "admincookieLastName");
            //if the email cookie does not exist, go to the registration page
            if(email == null || email.equals("")){
                url="view/signup.jsp";
            }/* if email cookie does exist, create the User object from the
            email cookie and skip the registration page*/
            else{
                try {
                    admin = UserDB.selectUser(email);
                } catch (SQLException ex) {
                    Logger.getLogger(CheckUserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                session.setAttribute("user", admin);
                url = "/admin/deshboard.jsp";
            }
        }
        else{
            url = "/admin/deshboard.jsp";
        }
        response.sendRedirect(url);
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
