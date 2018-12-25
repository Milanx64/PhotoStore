<%-- 
    Document   : show
    Created on : Nov 14, 2018, 1:25:11 PM
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
    if (session.getAttribute("adminauth") == null){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
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

<title>Upload photo - Show</title>
</head>
<body>
  
    <div class="overlay"></div>
    <jsp:include page="../inc/navmenu.html"></jsp:include>
        <main id="show">
            <h3 class="">One more step <span class="text-secondary">add more data to your photo</span></h3>
            <div class="photo-holder-editor" >
                <h3 class="text-secondary" id="title">code je ${photo.id}</h3>
                <img src="../${photo.imageURL}" id="photo-show" class="border"/>
                <div class="show-catalog">
                    <p class="text-secondary">Price is ${photo.price}</p>
                    <p class="text-secondary">${photo.description}</p>
                    <p class="text-secondary">Type is ${photo.type}</p>
                </div>
            </div>
            <div class="about-photo">
                <form action="${pageContext.request.contextPath}/AdminUploadServlet" method="post">
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
                   <input type="submit" value="Countinue" class="btn">
                       <button class="btn"><a href="view/userprofile/myprofil.jsp?${param.email}">GO BACK</a></button>
                </form>
            </div>
            
            
        </main>
</body>
</html>
