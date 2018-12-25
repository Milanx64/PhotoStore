<%-- 
    Document   : deletephotos
    Created on : Oct 21, 2018, 4:39:34 PM
    Author     : comp-one
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib prefix = "fn" 
   uri = "http://java.sun.com/jsp/jstl/functions" %>
<% 
            String email =request.getParameter("email");
            String photoID = request.getParameter("photoID");
            
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<title>Upload photo</title>
</head>
<body>
    <div class="container">
            <jsp:include page="inc/navmenu.jsp"></jsp:include>
        </div>
    <div class="overlay"></div>
    <main id="delete">
        <form action="${pageContext.request.contextPath}/DeleteServlet?photoID=<%= photoID %>" method="post">
                 <div class="photo-holder-editor">
                     <img src="${photo.imageURL}" id="photo-show" class="border"/>
                 </div>
                 <div class="about-photo">
                     <div class="edit-photo-data" id="price">
                         <span class="text-secondary">Price: ${photo.price}</span><br>

                     </div>
                     <div class="edit-photo-data" id="about-photo">
                         <span class="text-secondary">About: ${photo.description}</span><br>

                     </div>
                     <div class="edit-photo-data" id="about-photo">
                         <span class="text-secondary">Type: ${photo.type}</span><br>

                     </div>
                         <input type="submit"  value="DELETE" class="btn-danger">
                 </div>
        </form>
        
        </main>
    </div>
</body>
</html>