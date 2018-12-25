/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author comp-one
 */
import java.sql.*;
import java.util.*;

import business.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ProductDB {
    public static Product selectProduct(String productCode){

        try {
            /*ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();*/
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
            
            String query = "SELECT * FROM photos " + "WHERE id= ?";
            
            try{
                ps = connection.prepareStatement(query);
                ps.setString(1, productCode);
                rs = ps.executeQuery();
                if(rs.next())
                {
                    Product p = new Product();
                    p.setCode(rs.getString("id"));
                    p.setDescription(rs.getString("about"));
                    p.setPrice(rs.getDouble("price"));
                    p.setType(rs.getString("type"));
                    p.setImageURL(rs.getString("url"));
                    p.setName(rs.getString("name"));
                    p.setTitle(rs.getString("title"));
                    return p;
                }
                else{
                    return null;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
            finally
            {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                //pool.freeConnection(connection);
            }
        }
         catch(SQLException ex) {
            
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    public static Product selectUserProduct(String email){
      try {
            /*ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();*/
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
            
            String query = "SELECT * FROM photos WHERE artistEmail=?";
            
            try{
                ps = connection.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if(rs.next())
                {
                    Product p = new Product();
                    p.setCode(rs.getString("id"));
                    p.setDescription(rs.getString("about"));
                    p.setPrice(rs.getDouble("price"));
                    p.setType(rs.getString("type"));
                    p.setImageURL(rs.getString("url"));
                    p.setName(rs.getString("name"));
                    p.setTitle(rs.getString("title"));
                    return p;
                }
                else{
                    return null;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }
            finally
            {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                //pool.freeConnection(connection);
            }
        }
         catch(SQLException ex) {
            
            Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    //This method will return 0 if productId is not found
    public static Product selectProductId(int productID) throws SQLException{
        //ConnectionPool pool = ConnectionPool.getInstance();
        //Connection connection = pool.getConnection();
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
        
        String query = "SELECT * FROM photos " + "WHERE id = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if(rs.next())
            {
                Product p = new Product();
                p.setCode(rs.getString("id"));
                p.setDescription(rs.getString("about"));
                p.setImageURL(rs.getString("url"));
                p.setPrice(rs.getDouble("price"));
                p.setType(rs.getString("type"));
                p.setTitle(rs.getString("title"));
                p.setName(rs.getString("name"));
                return p;
            }
            else{
                return null;
            }
        }
            catch(SQLException e){
                    e.printStackTrace();
                    return null;
            }
            finally
            {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                //pool.freeConnection(connection);
            }
    }
    public static ArrayList<Product> selectPhotos(String artistEmail) throws SQLException{
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();*/
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
        
        String query = "SELECT id,about,url,price,type,title,name FROM photos " + "WHERE artistEmail = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, artistEmail);
            ArrayList<Product> products = new ArrayList<Product>();
            rs = ps.executeQuery();
            while(rs.next())
            {
                Product p = new Product();
                p.setCode(rs.getString("id"));
                p.setDescription(rs.getString("about"));
                p.setImageURL(rs.getString("url"));
                p.setPrice(rs.getDouble("price"));
                p.setType(rs.getString("type"));
                p.setTitle(rs.getString("title"));
                p.setName(rs.getString("name"));
                products.add(p);
            }
            return products;
        }
            catch(SQLException e){
                    e.printStackTrace();
                    return null;
            }
            finally
            {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                //pool.freeConnection(connection);
            }
    }
    public static void addProductDetails(Product p, int id) throws SQLException{
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
        
        
        String query = "UPDATE photos SET type= ?, about=?, price= ?, title=? WHERE id = ?";
        
        ps = connection.prepareStatement(query);
        ps.setString(1, p.getType());
        ps.setString(2, p.getDescription());
        ps.setDouble(3, p.getPrice());
        ps.setString(4, p.getTitle());
        ps.setInt(5, id);
        ps.executeUpdate();
        
    }
    
    public static void deletePhotoByID(int id) throws SQLException{
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
        
        
        String query = "DELETE FROM photos WHERE id = ?";
        ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    
    }
    public static ArrayList<Product> selectAllPhotos() throws SQLException{
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();*/
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
        
        String query = "SELECT id,artistEmail,url,name,price,about FROM photos";
        
        try{
            ps = connection.prepareStatement(query);
            
            ArrayList<Product> products = new ArrayList<Product>();
            rs = ps.executeQuery();
            while(rs.next())
            {
                Product p = new Product();
                p.setCode(rs.getString("id"));
                p.setArtistEmail(rs.getString("artistEmail"));
                p.setName(rs.getString("name"));
                p.setImageURL(rs.getString("url"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("about"));
                
                products.add(p);
            }
            return products;
        }
            catch(SQLException e){
                    e.printStackTrace();
                    return null;
            }
            finally
            {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                //pool.freeConnection(connection);
            }
    }
    public static ArrayList<Product> selectPhotosByType(String type) throws SQLException{
        /*ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();*/
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
        
        String query = "SELECT id,about,url,price,type,title,name FROM photos " + "WHERE type = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, type);
            ArrayList<Product> products = new ArrayList<Product>();
            rs = ps.executeQuery();
            while(rs.next())
            {
                Product p = new Product();
                p.setCode(rs.getString("id"));
                p.setDescription(rs.getString("about"));
                p.setImageURL(rs.getString("url"));
                p.setPrice(rs.getDouble("price"));
                p.setType(rs.getString("type"));
                p.setTitle(rs.getString("title"));
                p.setName(rs.getString("name"));
                products.add(p);
            }
            return products;
        }
            catch(SQLException e){
                    e.printStackTrace();
                    return null;
            }
            finally
            {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                //pool.freeConnection(connection);
            }
    }
}
    

