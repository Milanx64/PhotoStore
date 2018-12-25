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
        <title>LogIn</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
        <div class="container">
            <jsp:include page="../inc/navmenu.jsp"></jsp:include>
        </div>
        <main id="login">
            <h3 class="center">Log in to the Photo <span class="text-secondary">Store</span></h3>
            <form action="https://localhost:8443/${pageContext.request.contextPath}/LogInServlet" method="post">
                <div class="form-content1">

                    <p class="form-iteam">Email</p>
                    <input type="text" name="email" placeholder="" required class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">Password</p>
                    <input type="password" name="password" placeholder="" required
                            class="input">

                </div>
                <p>

                <button type="submit" value="Sign Up" id="submit-btn"
                        class="form-content5">Sign Up</button>
                </p>
        </form>
            ${pageContext.request.contextPath}
        </main>
        
        <script src="../js/main.js"></script>
    </body>
</html>