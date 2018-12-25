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

<title>Upload photo</title>
</head>
<body>
  
    <div class="overlay"></div>
    <jsp:include page="../inc/navmenu.html"></jsp:include>
        <main id="delete">
            
            <button class="btn"><a href="dashboard.jsp">Back to the dashboard</a></button>
            <form class="form-inline search" action="${pageContext.request.contextPath}/AdminServlet"  method = "post">
                <input class="" type="text" name="artistEmail" placeholder="Enter user email">
                <button class="" type="submit">Search</button>               
            </form>
            <h3 class="danger-text">${message}</h3>
            <c:forEach var="row" items="${photosToDelete}" varStatus="loopCounter">
                <table border="1px" class="delete-photo-table">

                    <tr>
                        <td>Photo ID</td>
                        <td>Artist Email</td>
                        <td>URL</td>                 
                        <td>Delete photo</td>
                    </tr>
                   <tr>
                       <td><c:out value="${row.code}"/></td>
                       <td><c:out value="${row.artistEmail}"/></td>
                       <td><img src="<c:out value="${row.imageURL}"/>" width="200px" height="200"></td>
                       <td><a href="<c:url value='/AdminDeleteServlet?photoID=${row.code}&name=${row.name}'/>">
                               <button class="btn-danger">Delete</button>
                           </a>
                   </tr>
                </table>  
           </c:forEach>
                      
    
        </main>
    
</body>
</html>