<%-- 
    Document   : add
    Created on : Oct 20, 2018, 4:18:36 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%
    String username = request.getParameter("username");
    String lastname = request.getParameter("lastname");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String role = request.getParameter("role");
%>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
<sql:query dataSource = "${snapshot}" var = "sql">
    <%--SELECT Username From userpass WHERE Username= '<%= username %>';--%>
                SELECT COUNT(Username) AS admin FROM userpass WHERE Username = '<%= username %>';
</sql:query>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
        <jsp:include page="../inc/navmenu.html"></jsp:include>
        <main class="add">
            <div class="">
                <c:forEach var="row" items="${sql.rows}">
    
                    <c:if test="${row.admin > 0}">
                        <p class="warning-text">Administrator with usrname <%= username %> already exists </p>
                    </c:if>
                    <c:if test="${row.admin == 0}">
                        <sql:update dataSource = "${snapshot}" var = "result">
                            INSERT INTO userpass (Username, Password, Email, Lastname) VALUES ('<%= username %>', '<%= password %>','<%= email %>', '<%= lastname%>');
                        </sql:update>
                        <sql:update dataSource = "${snapshot}" var = "result">
                            INSERT INTO userrole (Username, Rolename) VALUES ('<%= username%>','<%= role %>');
                        </sql:update>
                    </c:if>
                </c:forEach>
                <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
                <sql:query dataSource = "${snapshot}" var = "result">
                    SELECT * From userrole ;
                </sql:query>
                <table>
                    <p>All administrators</p>
                    <tr>
                        <td><span class="text-secondary">Username<span</td>
                        <td><span class="text-secondary">Rolename<span</td>
                    </tr>
                    <c:forEach var = "row" items = "${result.rows}">


                            <tr>
                                <td><c:out value = "${row.Username}"/></td>
                                <td><c:out value = "${row.Rolename}"/></td>

                            </tr>


                    </c:forEach>
                </table>
            </div>
        </main>
    </body>
</html>





