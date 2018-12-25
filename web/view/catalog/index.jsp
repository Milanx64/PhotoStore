<%-- 
    Document   : index
    Created on : Oct 1, 2018, 4:53:57 PM
    Author     : comp-one
--%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

<title>Photo Catalog</title>
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
        <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
    </div>
    
    <main id="photo-catalog">
        <h1 class="danger-text">${msg}</h1>
        <div class="catalog">
            <c:forEach var="row" items="${photos}" varStatus="loopCounter">  
                
                <div class="show-catalog">
                    <h3><span class="text-secondary">Price </span>${row.price}</h3>
                    <a href="<c:url value='SelectPhotoServlet?artistEmail=${row.artistEmail}&photoID=${row.code}'/>">
                        <img src="${row.imageURL}" id="catalog-img">
                    </a>
                    <p>
                        <span class="text-secondary">About</span>
                        ${row.description}
                    </p>
                     
                </div>
            </c:forEach>    
      
        </div>     
    </main>
    
    <script src="../../js/main.js"></script>
</body>
</html>



