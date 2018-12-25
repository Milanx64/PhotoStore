<%-- 
    Document   : delete
    Created on : Oct 21, 2018, 4:24:18 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%
    String email = request.getParameter("email");
    String id = request.getParameter("id");
%>



<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/style.css">

<title>Upload photo</title>
</head>

    <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>

        <sql:update dataSource = "${snapshot}" var = "count">
             DELETE FROM photos WHERE id = ? AND artistEmail = ?
             
             <sql:param value = "${param.photoID}" />
             <sql:param value = "${param.email}" />
        </sql:update>
   

    <div class="overlay"></div>
    <jsp:include page="../../inc/navmenu.html"></jsp:include>
    <%
            
            String redirectURL = "myprofil.jsp";
            response.sendRedirect(redirectURL);
        
    %>
    
</body>

