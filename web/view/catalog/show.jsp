<%-- 
    Document   : show
    Created on : Oct 4, 2018, 12:15:06 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<% 
            String email =request.getParameter("email");
            String photoID = request.getParameter("photoID");
            
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
        <div class="container">
            <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
        </div>
        
        <main id="show">
          
            <div class="photo-holder">
               <img src="${photo.imageURL}" id="photo-show"/>
            </div>
            <div class="">
                <button class=""><a href="<c:url value='/DisplayCartServlet?productCode=${photo.code}'/>">Order a print</a></button>
                <button class=""><a href="<c:url value='/view/cart/download.jsp?productCode=${photo.code}'/>">Download this photo</a></button>
                
            </div>
            
        
            
            
        </main>
            
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>
