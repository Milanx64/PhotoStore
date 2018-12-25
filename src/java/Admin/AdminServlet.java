/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import business.Admin;
import business.Product;
import business.User;
import data.AdminDB;
import data.ProductDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author comp-one
 */
public class AdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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
        //For geting data about all users
        String deleteUser = request.getParameter("deleteUser");
        if(deleteUser != null){
            //Find all users in data base and sand data to deleteuser.jsp
            ArrayList<User> users = new ArrayList<User>();
            try {
                users = UserDB.selectUsers();
                session.setAttribute("users", users);
                request.getRequestDispatcher("admin/deleteusere.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        //To delete a specific user
        String email = request.getParameter("email");
        if(email != null){
            User user = new User();
            user.setEmail(email);
            try {
                UserDB.delete(user);
                ArrayList<User> users = new ArrayList<User>();
                users = UserDB.selectUsers();
                session.setAttribute("users", users);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String msg = "User with email " + email + " deleted";
            request.setAttribute("message", msg);
            request.getRequestDispatcher("admin/deleteusere.jsp").forward(request, response);
        }
        //TO delete a photo
        String photo = request.getParameter("deletePhoto");
        String artistEmail = request.getParameter("artistEmail");
        if(photo != null){
            //Get all photos from data base or search DB by user email
            if(artistEmail != null){
                
                ArrayList<Product> products  = new ArrayList<Product>();
                try {
                    products = ProductDB.selectPhotos(artistEmail);
                    session.setAttribute("photos", products);
                    request.getRequestDispatcher("admin/deletephotos.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //If artistEmail is null show all photos from DB
            else{
                
                ArrayList<Product> products  = new ArrayList<Product>();
                try {
                    products = ProductDB.selectAllPhotos();
                    session.setAttribute("photosToDelete", products);
                    request.getRequestDispatcher("admin/deletephotos.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);
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
        Admin admin = new Admin();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String add = request.getParameter("add");
        if(name == null){
            try {
                //Check if admin exists in DB
                admin = AdminDB.selectAdmin(email, password);
                session.setAttribute("adminauth", admin);
                //Get all photos of admin
                ArrayList<Product> products  = new ArrayList<Product>();
                products = ProductDB.selectPhotos(admin.getEmail());
                session.setAttribute("photos", products);
                //Make cookie
                Cookie adminCookie = new Cookie("adminCookie", email);
                adminCookie.setMaxAge(30*60*24*365);
                adminCookie.setPath("/");
                response.addCookie(adminCookie);
                request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(name != null){
            admin.setName(name);
            admin.setLastname(lastname);
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setAddress(address);
            try {
                if(AdminDB.emailExists(admin.getEmail())){
                    String message = "This email already exist";
                    String url = "admin/addadmin.jsp";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher(url).forward(request, response);
                }
                else{
                    AdminDB.insertAdmin(admin);
                    String url = "admin/addadmin.jsp";
                    String message = "New account crated";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher(url).forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
