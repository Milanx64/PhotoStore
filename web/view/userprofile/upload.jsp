<%-- 
    Document   : upload
    Created on : Oct 3, 2018, 11:23:42 AM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import="business.User" %>
<%
    String email = null;
    User user = new User();
    if (session.getAttribute("userauth") == null){
        response.sendRedirect(request.getContextPath()+"/view/login.jsp");
    }
    else if(session.getAttribute("userauth") != null){
        user = (User) session.getAttribute("userauth");
        email = user.getEmail();  
    }
    if(email == null){
        response.sendRedirect(request.getContextPath()+"/view/userprofile/myprofile.jsp");
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
<link rel="stylesheet" type="text/css" href="../../css/style.css">

<title>Upload photo</title>
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
        <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
    </div>
    
    <main id="upload">
        <h2 class="">Upload your <span class="text-secondary">captures</span></h2>
        <span class="text-secondary">Select a file to upload</span> <br />
        <form action = "uploadfile.jsp?email=<%= email %>" method = "post"
         enctype = "multipart/form-data">
           <input type = "file" name = "file" size = "50" id="file-btn" required class="btn"/>
           
           <input type = "submit" value = "Upload File" class="btn" />
           <input type="reset" value="Restart" class="btn" />
        </form>
 
    </main>
    
    
    <script src="../../js/main.js"></script>
</body>
</html>
