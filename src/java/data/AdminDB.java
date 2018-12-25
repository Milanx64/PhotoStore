/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import business.Admin;
import business.Product;
import java.sql.*;

/**
 *
 * @author comp-one
 */
public class AdminDB {
    public static int insertAdmin(Admin admin) throws SQLException{
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
        
        String query = "INSERT INTO admins (name, lastname, password, email, address)" +
                "VALUES(?, ?, ?, ?, ?)";
        try {
			ps = connection.prepareStatement(query);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getLastname());
			ps.setString(3, admin.getPassword());
			ps.setString(4, admin.getEmail());
                        ps.setString(5, admin.getAddress());
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
    public static Admin selectAdmin(String email, String password) throws SQLException{
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
        
        String guery = "SELECT * FROM admins WHERE email = ? AND password = ?";
        try{
            ps = connection.prepareStatement(guery);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            Admin admin = null;
            if(rs.next()){
                admin = new Admin();
                admin.setName(rs.getString("name"));
                admin.setLastname(rs.getString("lastname"));
                admin.setEmail(rs.getString("email"));
            }
            return admin;
           
        }
        catch (SQLException e) {
	e.printStackTrace(); return null;
	}
	finally {
            DBUtil.closeResultSet (rs);
            DBUtil.closePreparedStatement(ps);
        }
    
    
    }
    public static boolean emailExists(String email) throws SQLException {
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
	
	
	String query = "SELECT email FROM admins WHERE email = ?";
	try {
	ps = connection.prepareStatement(query);
        ps.setString(1, email);
	rs = ps.executeQuery(); 
	return rs.next();
	}
	catch (SQLException e) {
	e.printStackTrace(); return false;
	}
	finally {
	DBUtil.closeResultSet (rs);
	DBUtil.closePreparedStatement(ps);
	//pool.freeConnection (connection);
	}
	}
    public static int insertPhoto(Product product) throws SQLException {
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
    
    
    
}
