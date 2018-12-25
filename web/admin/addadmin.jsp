<%-- 
    Document   : addadmin
    Created on : Oct 20, 2018, 4:05:42 PM
    Author     : comp-one
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%      
    if (session.getAttribute("adminauth") == null){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
        <jsp:include page="../inc/navmenu.html"></jsp:include>
        <main id="add-admin">
            <h3 class="text-secondary">Add new administrator</h3>
            
            <form action="${pageContext.request.contextPath}/AdminServlet?add=1" method="post">
                <div class="form-content1">

                    <p class="form-iteam">Name</p>
                    <input type="text" name="name" placeholder="" required class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">Lastname</p>
                    <input type="text" name="lastname" placeholder="" required
                            class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">Password</p>
                    <input type="password" name="password" placeholder="" required
                            class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">Email</p>
                    <input type="email" name="email" placeholder="" required
                            class="input">

                </div>
                <div class="form-content2">

                    <p class="form-iteam">Address</p>
                    <input type="text" name="address" placeholder="" required
                            class="input">

                </div>
                
                <p>

                <button type="submit" value="Sign Up" id="submit-btn"
                        class="form-content5">Sign Up</button>
                </p>
            </form>
            <div id="active-admin">
                <h3 class="danger-text">${message}</h3>
                
            </div>
            
        </main>
</html>
