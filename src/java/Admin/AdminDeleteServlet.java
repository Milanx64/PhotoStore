/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import business.Admin;
import business.Product;
import data.ProductDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author comp-one
 */
public class AdminDeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminDeleteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDeleteServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        Admin admin = new Admin();
        admin = (Admin) session.getAttribute("adminauth");
        session.removeAttribute("photos");
        
        //Check photo id and name
        String photoID = request.getParameter("photoID");
        String photoName = request.getParameter("name");
        if(photoID != null || photoName != null){
            //perform delete operation in DB and File System
            //Convert String to int
            int id = Integer.parseInt(photoID);
            try {
                ProductDB.deletePhotoByID(id);
                //Delete file in File System
                String path = "C:/Users/comp-one/Documents/NetBeansProjects/PhotoStore/web/photos/" + photoName;
                File deleteFile = new File(path);
                if(deleteFile.exists())
                    deleteFile.delete();
                String msg = "Photo of " + photoName + " succesfuly deleted";
                //Make new Sesion with new list of photo to be send to JSP
                ArrayList<Product> products = new ArrayList<Product>();
                products = ProductDB.selectAllPhotos();
                session.setAttribute("photos", products);
                request.setAttribute("message", msg);
                request.getRequestDispatcher("admin/deletephotos.jsp").forward(request, response);
                    
            } catch (SQLException ex) {
                Logger.getLogger(AdminDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        Admin admin = new Admin();
        admin = (Admin) session.getAttribute("adminauth");
        //Find photos of specific user
        String artist = request.getParameter("artistEmail");
        if(artist != null){
            //find all photos of artist
            ArrayList<Product> products = new ArrayList<Product>();
            try {
                products = ProductDB.selectPhotos(artist);
                session.setMaxInactiveInterval(1000);
                session.setAttribute("photos", products);
                request.getRequestDispatcher("admin/deletephotos.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AdminDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
