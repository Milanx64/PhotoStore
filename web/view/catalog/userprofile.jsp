<%-- 
    Document   : userprofile
    Created on : Oct 2, 2018, 5:40:51 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/phototest"
            user = "root"  password = ""/>
<%
    String email = request.getParameter("email");
    if(email == null){
        email = "hikari@google.com";
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

<title>Artist Catalog</title>
</head>
<body>
    <div class="container">
            <jsp:include page="../inc/navmenu.jsp"></jsp:include>
        </div>
    <div class="overlay"></div>
    <sql:query dataSource = "${snapshot}" var = "result">
        SELECT name, lastname,email, about, contactEmail FROM users WHERE email = '<%= email %>';
    </sql:query>
    <sql:query dataSource = "${snapshot}" var = "sql">
        SELECT * From photos WHERE artistEmail = '<%= email %>';
    </sql:query>
    <main id="user-profile">
        <div class="profile-bio">
            <c:forEach var = "row" items = "${result.rows}">
                <img src="../../photos/Koala.jpg" width="200" height="200" id="bio-photo" class="bio-image"/>
             
                <h2 class=""><span class="white">${row.name}</span> <span class="text-secondary">${row.lastname}</span></h2>
                <button class="btn">Follow</button>
                <p class="about-user">${row.about}</p>
                
                <a href="#">${row.name}'s <i class="fab fa-instagram"> </i></a>
                <a href="#">${row.name}'s <i class="fab fa-facebook"></i></a>
                <a href="#">${row.name}'s <i class="fab fa-twitter"></i></a>
                <div class="clear"></div>
            </c:forEach>
        </div>
        <div class="gallry">
            <c:forEach var = "row" items = "${sql.rows}">
                <div class="show-catalog">
                    <a href="<c:url value='show.jsp?photoID=${row.id}&email=${row.artistEmail}'/>">
                        <img src="${row.url}" id="catalog-img">
                    </a>
                </div>
                
             
            </c:forEach>
        </div>
    </main>
    
    
    
    <script src="../../js/main.js"></script>
</body>
</html>