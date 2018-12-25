/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package data;
package data;
import business.Product;
import java.sql.*;
import business.User;
import java.util.ArrayList;
/**
 *
 * @author comp-one
 */
public class UserDB {
    public static int insert (User user) throws SQLException  {
		
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
        String query;
        query = "INSERT INTO users (name, lastname, email, password) VALUES (?, ?, ?, ?)";
        try {
                ps = connection.prepareStatement(query);
                ps.setString(1, user.getName());
                ps.setString(2, user.getLastname());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                return ps.executeUpdate();

        }
        catch (SQLException e) {
                e.printStackTrace();
                return 0;
                }
        finally {
                        DBUtil.closePreparedStatement (ps);

        }

}
	
	public static int update (User user) throws SQLException {
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
	
	String query = "UPDATE users SET" +
	"name = ?," + "lastname = ?," + "WHERE email = ?";
	try {
		ps = connection.prepareStatement (query);
		ps.setString(1, user.getName());
		ps.setString (2, user.getLastname());
		//ps.setString (3, user.getEmail());
		ps.setString (3, user.getEmail());
		return ps.executeUpdate();
	}
	catch(SQLException e) {
		e.printStackTrace();
		return 0;
	}
	finally {
		DBUtil.closePreparedStatement (ps);
		//pool.freeConnection (connection);
	}
}
	public static int delete (User user) throws SQLException {
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
	
	String query = "DELETE FROM users WHERE email = ?"; 
	try {
		ps = connection.prepareStatement (query);
		ps.setString(1, user.getEmail());
		return ps.executeUpdate();
	}
	catch(SQLException e) {
	e.printStackTrace();
	return 0;
	}
	finally {
	DBUtil.closePreparedStatement (ps);
	//pool. freeConnection (connection);
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
	
	
	String query = "SELECT email FROM users WHERE email = ?";
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
	

	public static User selectUser (String email) throws SQLException {
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

            String query = "SELECT * FROM users " +
            "WHERE email = ?";
            try {
                ps = connection.prepareStatement (query);
                ps.setString(1, email);
                rs = ps.executeQuery();
                User user = null; 
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setLastname (rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAbout(rs.getString("about"));
            }
                return user;
            }
            catch (SQLException e) {
                e.printStackTrace(); return null;
            }
            finally {
                DBUtil.closeResultSet (rs); 
                DBUtil.closePreparedStatement (ps);
                //pool.freeConnection(connection);
            }


}
        public static void profilePhoto(String url, String email) throws SQLException{
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

                String query = "UPDATE users SET profilePhoto= ? WHERE email=?";
                try {
                    ps = connection.prepareStatement(query);
                    ps.setString(1,url);
                    ps.setString(2, email);
                    ps.executeUpdate();
                    
                }
                catch (SQLException e) {
                    e.printStackTrace(); 
                }
                finally {
                    DBUtil.closeResultSet (rs);
                    DBUtil.closePreparedStatement(ps);
                //pool.freeConnection (connection);
                }
        }
        public static void editBio(User user) throws SQLException{
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
                
                String query = "UPDATE users SET about = ? WHERE email = ?";
                try {
                    ps = connection.prepareStatement(query);
                    ps.setString(1,user.getAbout());
                    ps.setString(2, user.getEmail());
                    ps.executeUpdate();
                    
                }
                catch (SQLException e) {
                    e.printStackTrace(); 
                }
                finally {
                    DBUtil.closeResultSet (rs);
                    DBUtil.closePreparedStatement(ps);
                //pool.freeConnection (connection);
                }
                
        }
        //Select user for login
        public static User selectUser (String email, String password) throws SQLException {
          
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

            String query = "SELECT * FROM users " +
            "WHERE email = ? and password = ?";
            try {
                ps = connection.prepareStatement (query);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                User user = null; 
            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setLastname (rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setAbout(rs.getString("about"));
                user.setCoverPhoto(rs.getString("coverPhoto"));
                user.setProfilePhoto(rs.getString("profilePhoto"));
            }
                return user;
            }
            catch (SQLException e) {
                e.printStackTrace(); return null;
            }
            finally {
                DBUtil.closeResultSet (rs); 
                DBUtil.closePreparedStatement (ps);
                
            }
        }
        public static ArrayList<User> selectUsers() throws SQLException{
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
        
        String query = "SELECT * FROM users ";
        
        try{
            ps = connection.prepareStatement(query);
            
            ArrayList<User> users = new ArrayList<User>();
            rs = ps.executeQuery();
            while(rs.next())
            {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setName(rs.getString("name"));
                u.setLastname(rs.getString("lastname"));
                u.setEmail(rs.getString("email"));
                users.add(u);
            }
            return users;
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
