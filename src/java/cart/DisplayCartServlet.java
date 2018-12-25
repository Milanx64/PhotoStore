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

import business.*;
import data.*;

/**
 *
 * @author comp-one
 */
public class DisplayCartServlet extends HttpServlet {

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
            out.println("<title>Servlet DisplayCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayCartServlet at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
        String quantityStr = request.getParameter("quantity");
        String productCode = request.getParameter("productCode");
        String removeButtonvalue = request.getParameter("removeButon");
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        /*If user enters a negative or invalid number in the update text box
        the quantity is automatically reset to 1
        */
        //int quantity = 1;
        int quantity;
        try{
            quantity = Integer.parseInt(quantityStr);
            if(quantity < 0)
                quantity = 1;
        }
        catch(NumberFormatException nfe){
            quantity = 1;
        }
        //Get product form product code
        
        Product product = ProductDB.selectProduct(productCode);
        session.setAttribute("product", product);
        
        
        //If product exists, add or remove from cart
        if(product != null){
                LineItem lineItem = new LineItem();
                
                lineItem.setProduct(product);
                lineItem.setQuantity(quantity);
                if(quantity > 0)
                    cart.addItem(lineItem);
                else
                    cart.removeItem(lineItem);
        }
        session.setAttribute("cart", cart);
        /*If there is no iteams in cart, forward to the quick order page
        Otherwise, forward to the cart page
        */
        String url="";
        if(cart.getItems().size() <= 0){
            url = "/view/cart/dsiplayOrder.jsp";
        }
        else{
            url = "/view/cart/cart.jsp";
        }
        /*RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);*/
         response.sendRedirect("view/cart/cart.jsp");

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
