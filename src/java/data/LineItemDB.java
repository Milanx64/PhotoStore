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

public class LineItemDB {
    //This method adds one lineItem to the LineItems table.
    public static long insert(long invoiceID, LineItem lineItem){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO LineItem(InvoiceID, ProductID, Quantity)"
                + "VALUES(?, ?, ?)";
        try{
            ps = connection.prepareCall(query);
            ps.setLong(1, invoiceID);
            ps.setLong(2, lineItem.getProduct().getId());
            ps.setInt(3, lineItem.getQuantity());
            return ps.executeUpdate();
        }
        catch(SQLException e){
            System.err.println(e);
            return 0;
        }
        finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    //This method returns null if a record isn't found.
    public static List<LineItem> selectLineItems(long invoiceID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM LineItem "
                + "WHERE InvoiceID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoiceID);
            rs = ps.executeQuery();
            List<LineItem> lineItems = new ArrayList<>();
            while (rs.next()) {
                LineItem lineItem = new LineItem();
                int productID = rs.getInt("ProductID");
                Product product = ProductDB.selectProductId(productID);
                lineItem.setProduct(product);
                lineItem.setQuantity(rs.getInt("Quantity"));
                lineItems.add(lineItem);
            }
            return lineItems;
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
