<%-- 
    Document   : myprofil
    Created on : Oct 1, 2018, 3:23:22 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page import="data.EditServlet"%>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/phototest"
            user = "root"  password = ""/>
<%
    if (session.getAttribute("userauth") == null){
        response.sendRedirect(request.getContextPath()+"/view/login.jsp");
    }
    String email = "";
    
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

<title>Artist Catalog</title>
</head>
<body>
    
    <div class="overlay"></div>
    <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
    </div>
    
    <main id="user-profile">
        <div class="profile-bio">
             <img src="${userauth.profilePhoto}" width="200" height="200" id="bio-photo" class="bio-image"/>
        
            
            <div class="image" style="background-image:url(${userauth.coverPhoto})">
                   

                    <h2 class=""><span class="white">${userauth.name}</span> <span class="text-secondary">${userauth.lastname}</span></h2>

                    <p class="about-user">${userauth.about}</p>

                    <a href="#">${userauth.name}'s <i class="fab fa-instagram"> </i></a>
                    <a href="#">${userauth.name}'s <i class="fab fa-facebook"></i></a>
                    <a href="#">${userauth.name}'s <i class="fab fa-twitter"></i></a>
                    <div class="desh">
                        <a href="${pageContext.request.contextPath}/EditServlet"><button class="btn">Edit bio</button></a>
                        
                        <p><form action="view/userprofile/profilephoto.jsp" method="post"  enctype = "multipart/form-data" id="upload-profile-form">
                            Change profile photo
                            <input type = "file" name = "file" size = "50" class="btn" required id="upload-profile-button"/>
                            </form>
                        </p>
                        <form action="view/userprofile/cover.jsp" method="post"  enctype = "multipart/form-data" id="upload-form">
                            Change cover photo
                            <input type = "file" name = "file" size = "50" class="btn" required id="upload-button"/>
                        </form>
                        <a href="view/userprofile/upload.jsp"><button class="btn">Upload photo</button></a>

                    </div>
                    <div class="clear"></div>
            </div>
           
        </div>
        <div class="gallry">
            <c:forEach var="row" items="${photos}" varStatus="loopCounter">  
            
                <div class="show-catalog">
                    
                    <a href="<c:url value='${request.getContextPath()}/EditPhoto?photoId=${row.code}'/>">
                        <img src="${row.imageURL}" id="catalog-img">
                    </a>
                    <div id="editor">
                        
                        <a href="${pageContext.request.contextPath}/DeleteServlet?photoID=${row.code}"><button class="btn-danger">Delete</button></a>
                        <a href="${pageContext.request.contextPath}/EditPhoto?photoId=${row.code}"><button class="btn">Edit info</button></a>
                        <a href="<c:url value='statistics.jsp?photoID=${row.id}'/>"><button class="btn">Statistics</button> </a>
                    </div>
                    
                </div>
            </c:forEach>        
                     
        </div>
    </main>
    
    
    
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>