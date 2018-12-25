/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
//import java.mail.*;
import business.*;
import data.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.*;
import business.Invoice.*;

/**
 *
 * @author comp-one
 */
public class CompleteOrderServlet extends HttpServlet {

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
            out.println("<title>Servlet CompleteOrderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompleteOrderServlet at " + request.getContextPath() + "</h1>");
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
            User user = (User) session.getAttribute("user");
            Invoice invoice = (Invoice)session.getAttribute("invoice");
            
            //String cardType = request.getParameter("CardType");
            String crdeitCard="trr";
            String [] cardType = request.getParameterValues("CardType");
            for(int i=0; i< cardType.length; i++){
                crdeitCard = cardType[i];
            }
            String cardNumber = request.getParameter("CardNumber");
            String expMonth = request.getParameter("ExpirationDate");
            String expYear = request.getParameter("ExpirationYear");
            crdeitCard="trr";
            
            user.setCardType(crdeitCard);
            user.setCardNumber(cardNumber);
            user.setExpDate(expMonth + "/" + expYear);
            
            //If a record for a User object exists, update it
            if(UserDB.emailExists(user.getEmail())){
                //UserDB.update(user);
            }
            // Otherwise write a new record for the User object
            else
            {
                UserDB.insert(user);
            }
            
            //Write a new invoice record
            InvoiceDB.insert(invoice);
            
            //Set the emailCookie in the user's browser
            Cookie cookieEmail = new Cookie("cookieEmail", user.getEmail());
            cookieEmail.setMaxAge(60*24*365);
            cookieEmail.setPath("/");
            response.addCookie(cookieEmail);
            
            //Remove all items from the user's cart
            Cart cart = (Cart) session.getAttribute("cart");
            cart.setItems(new ArrayList<LineItem>());
            
            //Send an email to the user to confirm the order
            String to = user.getEmail();
            String from = "";
            String subject = "NO-replay Order Confirmation";
            String body = "Dear" + user.getName() + "\n\n" +
                    "Thanks for ordering from us. "+
                    "You should recive your order in. " + 
                    "Have a grait day.";
            boolean isBodyHTML = false;
            /*try{
                MailUtil.sendMail(to, from, subject, body, isBodyHTML);
            }
            catch(MessagingException e){
                this.log(
                        "Unable to send email. \n"
                );
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(CompleteOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String url = "/car/complete.jsp";
        RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        //response.sendRedirect("view/cart/complete.jsp");
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
