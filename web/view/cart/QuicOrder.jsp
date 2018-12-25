<%-- 
    Document   : QuicxOrder
    Created on : Oct 8, 2018, 3:05:00 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quick Order</title>
        <style type="text/css" src="../../css/style.css"></style>
    </head>
    <body>
        <div class="overlay"></div>
        <jsp:include page="../../inc/navmenu.html"></jsp:include>
        <h1 class="lg-heading">Quick order an photo</h1>
        
        <table>
            <tr>
                <th>Description</th>
                <th>Price</th>
                <th></th>
            </tr>
            <c:forEach var="product" items="${products}">
                <td>
                    <a href="<c:url value='/Display'/>"${product.description}</a
                </td>
                <td>
                    <a href="<c:url value='/DisplayCartServlet'/>">Add To Cart</a>
                </td>
            </c:forEach>
        </table>
        
        <script src=".././js/main.js"></script>
    </body>
</html>
