<%-- 
    Document   : deleteusere
    Created on : Oct 21, 2018, 3:55:46 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
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

<title>Delete User</title>
</head>
<body>
    
    <div class="overlay"></div>
    <jsp:include page="../inc/navmenu.html"></jsp:include>
    <main id="user">
        <button class="btn"><a href="${pageContext.request.contextPath}/AdminServlet">Back to the dashboard</a></button>
        <h3 class="danger-text">${message}</h3>
        <div class="delete">
            <table border="1px">
                <tr>
                    <td>User ID</td>
                    <td>User name</td>
                    <td>Lastname</td>
                    <td>User email</td>
                    <td>Delete user</td>
                </tr>
                
                <c:forEach var="row" items="${users}" varStatus="loopCounter">
                    <tr>
                        <td><c:out value="${row.id}"/></td>
                        <td><c:out value="${row.name}"/></td>
                        <td><c:out value="${row.lastname}"/></td>
                        <td><c:out value="${row.email}"/></td>
                        <td><a href="<c:url value='/AdminServlet?email=${row.email}'/>">
                                <button class="btn-danger">Delete</button>
                            </a></td>
                    </tr>
                    
                </c:forEach>
                    
               
                
            </table>
        </div>
    </main>