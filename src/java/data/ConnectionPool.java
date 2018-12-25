/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 *
 * @author comp-one
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
	private static DataSource dataSource = null;
	
	private ConnectionPool() {
		
	try {
		/*InitialContext ic = new InitialContext();
		dataSource = (DataSource)
		ic.lookup ("jdbc:mysql:/localhost/phototest");*/
                //Get a connection
                String dbURL = "jdbc:mysql://localhost:3306/phototest";
                String username="root";
                String password="";
                dataSource = (DataSource)DriverManager.getConnection(dbURL, username, password);
                
	}
	catch(Exception e){
		e.printStackTrace();
	}
	}
       	
	public static ConnectionPool getInstance() {
		if (pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}
	public Connection getConnection() {
		try {
		return dataSource.getConnection();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
		public void freeConnection (Connection c) {
		try {
		c.close();
		}
		catch (SQLException sqle) {
		sqle.printStackTrace();
		}
		}
}
