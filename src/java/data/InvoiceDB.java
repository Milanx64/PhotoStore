/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.*;


import java.sql.*;
import java.util.*;

/**
 *
 * @author comp-one
 */
public class InvoiceDB {
    public static void insert(Invoice invoice) throws SQLException{
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
        
        //this method adds a record to the Invoice table.
        //To inset the exact invoice date, the SQL NOW() function is used.
        
        String query = "INSERT INTO invoice (UserID, invoiceDate, TotalAmount, Isprocessed)"
                + "VALUES (?, ?, ?, 'n')";
        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getUser().getId());
            ps.setDouble(2, invoice.getInvoiceTotal());
            ps.executeUpdate();
            
            //Get the InvoiceID from the last Insert statement
            String indentityQuery = "SELECT @@IDENTITY AS INDENTITY";
            Statement indentityStatement = connection.createStatement();
            ResultSet indentityResultSet = indentityStatement.executeQuery(indentityQuery);
            indentityResultSet.next();
            long invoiceID = indentityResultSet.getLong("INDENTITY");
            indentityResultSet.close();
            indentityStatement.close();
            
            //Write line iteams to the LineItem table
            
            List<LineItem> lineItems = invoice.getLineItems();
            for (LineItem item : lineItems){
                LineItemDB.insert(invoiceID, item);
            }
        }
        catch(SQLException e){
            System.err.println();
        }
        finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            //pool.freeConnection(connection);
        }
    }
    //This method sets the Invoice.ISProcessed column to 'y' yes
    public static void update(Invoice invoice){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "UPDATE Invoice SET"
                + "IsProcessed = 'y' "
                + "WHERE InvoiceID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getInvoiceNumber());
            ps.executeUpdate();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static ArrayList<Invoice> selectUnprocessedInvoices() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //This method reads in all invoices that have not been
        //processed yet. To do this, it creates a ArrayList<Invoice> of
        //Invoice objects, which each contain a User object.
        //This method returns null if no unprocessed invoices are found.
        String query = "SELECT * "
                + "FROM User "
                + "INNER JOIN Invoice "
                + "ON User.UserID = Invoice.UserID "
                + "WHERE Invoice.IsProcessed = 'n' "
                + "ORDER BY InvoiceDate";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Invoice> unprocessedInvoices = new ArrayList<>();
            while (rs.next()) {
                //Create a User object
                User user = UserDB.selectUser(rs.getString("Email"));

                //Get line items
                long invoiceID = rs.getLong("Invoice.InvoiceID");
                List<LineItem> lineItems = LineItemDB.selectLineItems(invoiceID);

                //Create the Invoice object
                Invoice invoice = new Invoice();
                invoice.setUser(user);
                invoice.setInvoiceDate(rs.getDate("InvoiceDate"));
                invoice.setInvoiceNumber(invoiceID);
                invoice.setLineItems(lineItems);

                unprocessedInvoices.add(invoice);
            }
            return unprocessedInvoices;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
