<!DOCTYPE html>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fn" 
   uri = "http://java.sun.com/jsp/jstl/functions" %>


<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Photo Store</title>

</head>
<body id="bg-img">
	<div class="overlay"></div>
        <div class="container">
            <jsp:include page="inc/navmenu.jsp"></jsp:include>
        </div>
	

	<main id="home">
	<h1 class="lg-heading">
		Photo <span class="text-secondary">Store</span>
	</h1>
	<h2 class="sm-heading">We bring photos to you</h2>
        <div id="explore">
            <span class="text-secondary"><h1>Explore all photos on PhotoStore</h1></span>
            <c:forEach var="row" items="${result.rows}" >

                <c:set var="real_url" value="${row.url}" />
                <c:set var="url" value="${fn:substringAfter(real_url, '../../')}" />
                <div class="show-catalog">
                     <h3 class="artist-name">Artist ${row.artistEmail}</h3>

                    <a href="view/catalog/show.jsp?email=${row.artistEmail}&photoID=${row.id}">
                         <img src="<c:out value="${url}"/>"  id="catalog-img">
                    </a>

                 </div>
            </c:forEach>
        </div>
	<footer id="footer">
            <div class="icons">
		<a href=""> <i class="fab fa-facebook-square fa-3x"></i>
		</a> <a href=""> <i class="fab fa-instagram fa-3x"></i>
		</a> <a href=""> <i class="fab fa-twitter fa-3x"></i>
		</a> <a href=""> <i class="fab fa-linkedin fa-3x"></i>
		</a>
            </div>
        </footer>
	</main>
        
                
	<script src="js/main.js"></script>
</body>
</html>