/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.*;
import java.io.File;
import data.*;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
/**
 *
 * @author comp-one
 */

public class InsertDB extends HttpServlet {

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
            out.println("<title>Servlet InsertDB</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertDB at " + request.getContextPath() + "</h1>");
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
        User user = new User();
        user = (User) session.getAttribute("userauth");
        
        String artistEmail = request.getParameter("artistEmail");
        String url = request.getParameter("photoURL");
        String coverURL = request.getParameter("coverPhotoURL");
        String photoID = request.getParameter("photoID");
        String profilePhoto = request.getParameter("profilePhotoURL");
        /*String type = request.getParameter("type");
        String about = request.getParameter("about");
        String priceSTR = request.getParameter("price");
        String name = request.getParameter("photo-name");
        String title = request.getParameter("photo-title");*/
        
        //Insert photo to DB
        if(url != null){
            Product product = new Product();
            product.setImageURL(url);
            product.setArtistEmail(user.getEmail());
            

            try {
                //insertPhoto(product);
                insertPhoto(product);
                user.setProfilePhoto(profilePhoto);
                session.setAttribute("userauth", user);
                request.getRequestDispatcher("view/userprofile/myprofil.jsp").forward(request, response);
                //response.sendRedirect("view/userprofile/myprofil.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(InsertDB.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        else if(coverURL != null){
            try {
                deleteCoverPhoto(artistEmail);
                updateCoverPhoto(coverURL, artistEmail);
            } catch (SQLException ex) {
                Logger.getLogger(InsertDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("view/userprofile/myprofil.jsp");
        }
        else if(photoID != null){
            //int id = Integer.parseInt(photoID);
            int id = 49;
            Product product = new Product();
            try {
                product = ProductDB.selectProductId(id);
                //send product to jsp
                request.setAttribute("product", product);
                request.getRequestDispatcher("view/userprofile/editor.jsp").forward(request, response);
                
                //response.sendRedirect("view/userprofile/test.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(InsertDB.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        else if(profilePhoto != null){
            try {
                UserDB.profilePhoto(profilePhoto, artistEmail);
                user.setProfilePhoto(profilePhoto);
                session.setAttribute("userauth", user);
                request.getRequestDispatcher("view/userprofile/myprofil.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(InsertDB.class.getName()).log(Level.SEVERE, null, ex);
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

    private int insertPhoto(Product product) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
         }
         catch(ClassNotFoundException ex) {
            
            System.exit(1);
         }
        String URL = "jdbc:mysql://localhost/phototest";
        String USER = "root";
        String PASS = "";
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        /*String query = "INSERT INTO photos(artistEmail,url, type, about, price, name, title) VALUES (hikari@google.com,?, ?, ?, ?, ?, ?)";*/
        String query = "INSERT INTO photos (artistEmail, url, type, about, price ,name, title) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
                        ps.setString(1, product.getArtistEmail());
			ps.setString(2, product.getImageURL());
			ps.setString(3, product.getType());
			ps.setString(4, product.getDescription());
			ps.setDouble(5, product.getPrice());
                        ps.setString(6, product.getName());
                        ps.setString(7, product.getTitle());
                      //  ps.setString(4, product.get);
			return ps.executeUpdate();
                        
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
			}
		finally {
				DBUtil.closePreparedStatement (ps);
				//pool.freeConnection (connection);
		}
    }
    private int updateCoverPhoto(String photoURL, String email) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
         }
         catch(ClassNotFoundException ex) {
            
            System.exit(1);
         }
        String URL = "jdbc:mysql://localhost/phototest";
        String USER = "root";
        String PASS = "";
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query, coverPhoto;
        
        
        
        query = "UPDATE users SET coverPhoto= ? WHERE email=?";
		try {
			ps = connection.prepareStatement(query);
                        ps.setString(1, photoURL);
			ps.setString(2, email);			
                        return ps.executeUpdate();
                        
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
			}
		finally {
				DBUtil.closePreparedStatement (ps);
				//pool.freeConnection (connection);
		}
    }
    private int deleteCoverPhoto(String email) throws SQLException{
         try {
            Class.forName("com.mysql.jdbc.Driver");
         }
         catch(ClassNotFoundException ex) {
            
            System.exit(1);
         }
        String URL = "jdbc:mysql://localhost/phototest";
        String USER = "root";
        String PASS = "";
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String coverPhoto;
        String query = "SELECT coverPhoto FROM users WHERE email= '" +email+"'";
        
        try{
            ps = connection.prepareStatement(query);
            //ps.setString(1, "hikari@google.com");
            //ps.setString(1, email);
            rs = ps.executeQuery(query);
           
            while(rs.next()){
                coverPhoto = rs.getString("coverPhoto");
                String name = coverPhoto.replace("../../photos/coverphotos/", " ");
                name = coverPhoto.trim();
                if(coverPhoto != null){
                    String path = "C:/Users/comp-one/Documents/NetBeansProjects/PhotoStore/web/photos/coverphotos/" + name;
                    File deleteFile = new File(path);
                    if(deleteFile.exists())
                        deleteFile.delete();
                    return 1;
                    
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
        finally{
            DBUtil.closePreparedStatement (ps);
            //pool.freeConnection (connection);
        }
        return 0;
    }
}
