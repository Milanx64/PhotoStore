<%-- 
    Document   : editor
    Created on : Oct 29, 2018, 3:12:19 PM
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
            if (session.getAttribute("userauth") == null){
                response.sendRedirect(request.getContextPath()+"/view/login.jsp");
            }
            if(session.getAttribute("product") == null){
                response.sendRedirect(request.getContextPath()+"/view/userprofile/myprofil.jsp");
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
        <div class="container">
            <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
        </div>
        
        <main id="show">
            <div class="photo-holder-editor" >
                <h3 class="text-secondary" id="title">${product.title}</h3>
                <img src="${product.imageURL}" id="photo-show" class="border"/>
                <div class="show-catalog">
                    <p class="text-secondary">Price is ${product.price}</p>
                    <p class="text-secondary">${product.description}</p>
                    <p class="text-secondary">Type is ${product.type}</p>
                </div>
            </div>
            <div class="about-photo">
                <form action="UpdateServlet?photoID=${product.code}&email=${userauth.email}" method="post">
                   <div class="edit-photo-data" id="price">

                       <p class="text-secondary">New price:</p>
                       <input type="number" name="price" class="input"  >
                   </div>
                   <div class="edit-photo-data" id="about-photo">

                       <p class="text-secondary">About photo:</p>
                       <input type="text" name="about" class="input" >
                   </div>
                   <div class="edit-photo-data" id="about-photo">

                       <p class="text-secondary">Type:</p>
                       <input type="text" name="type" class="input" >
                   </div>
                   <div class="edit-photo-data" id="about-photo">

                       <p class="text-secondary">Change title:</p>
                       <input type="text" name="title" class="input" >
                   </div>
                   <input type="submit" value="Update" class="btn">
                       <button class="btn"><a href="view/userprofile/myprofil.jsp?${param.email}">GO BACK</a></button>
                </form>
            </div>
             
       
        
        </main>
            
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>

