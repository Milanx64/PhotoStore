<%-- 
    Document   : login
    Created on : Oct 15, 2018, 4:45:13 PM
    Author     : comp-one
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
        <div class="container">
            <jsp:include page="inc/navmenu.jsp"></jsp:include>
        </div>
        
        <main id="admin-logins">
            <h3 class="lg-heading">Log in to the Photo <span class="text-secondary">Store</span></h3>
            <form action="${pageContext.request.contextPath}/AdminServlet" method="post">
                <div class="float form-content1">

                    <p class="form-iteam">Email</p>
                    <input type="text" name="email" placeholder="" required class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">password</p>
                    <input type="password" name="password" placeholder="" required
                            class="input">

                </div>
                <p>

                    <input type="submit" value="Login" id="submit-btn">
                </p>
        </form>
            ${message}
        </main>
        
        <script src="../js/main.js"></script>
    </body>
</html>