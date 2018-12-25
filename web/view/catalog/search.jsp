<%-- 
    Document   : search
    Created on : Oct 4, 2018, 1:06:55 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<% 
            String type =request.getParameter("type");
            
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
       <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
        <sql:query dataSource = "${snapshot}" var = "sql">
                SELECT url, artistEmail From photos WHERE type = '<%= type %>';
        </sql:query>
                
        <c:forEach var = "row" items = "${sql.rows}">
            
             <a href="show.jsp?email=${row.artistEmail}">
                 <img src="${row.url}" width="150px" height="150px"/>
            </a>
            
        </c:forEach>
        
        <script src="../../js/main.js"></script>
    </body>
</html>
