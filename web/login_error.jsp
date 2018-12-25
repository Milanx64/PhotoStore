<%-- 
    Document   : login_error
    Created on : Oct 15, 2018, 5:17:01 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ERROR</h1>
        <h3>Login as admin</h3>
            <form action="j_security_check" method="post">
                <div class="float form-content1">

                    <p class="form-iteam">Username</p>
                    <input type="text" name="j_username" placeholder="" required class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">password</p>
                    <input type="password" name="j_password" placeholder="" required
                            class="input">

                </div>
                <p>

                <button type="submint" value="Sign Up" id="submit-btn"
                        class="form-content5">Sign Up</button>
                </p>
        </form>
        <% String username = request.getParameter("j_username");
            String password = request.getParameter("j_password");
        %>
        <%= username %>
        <%= password %>
        
        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
            <sql:query dataSource = "${snapshot}" var = "result">
                SELECT * From users WHERE name = '<%= username %>';
            </sql:query>
                
            <c:forEach var = "row" items = "${result.rows}">
            <tr>
                <td>Rezultat</td>
               <td><c:out value = "${row.login}"/></td>
               <td><c:out value = "${row.password}"/></td>
               
            </tr>
            </c:forEach>
    </body>
</html>
