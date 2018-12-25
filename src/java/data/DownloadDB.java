/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;
import business.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author comp-one
 */
public class DownloadDB {
    public static long insert(Download download){

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
            String query = null;
            
            
            query = "INSERT INTO `download`(`UserID`, `DownloadDate`, `ProducCode`, `id`)"
                    + " VALUES" + "([value-1],[value-2],[value-3],[value-4])";
            try{
                ps = connection.prepareCall(query);
                //ps.setLong(1, download.getUser().getId());
                ps.setString(2, download.getProductCode());
                return ps.executeUpdate();
            }
            catch(SQLException e){
                System.err.println(e);
                return 0;
            }
            finally{
                //DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
               // pool.freeConnection(connection);
            }
        }
         catch(SQLException ex) {
            
            Logger.getLogger(DownloadDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        return 0;
    }
}
