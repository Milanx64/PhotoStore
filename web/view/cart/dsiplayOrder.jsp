<%-- 
    Document   : displayOrder
    Created on : Oct 6, 2018, 3:56:37 PM
    Author     : comp-one
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="overlay"></div>
        <div class="container">
            <jsp:include page="../inc/navmenu.jsp"></jsp:include>
        </div>
        <main id="displayOrder">
            <h1>Your </h1>
            <form action="<c:url value='/DisplayCartServlet' />" method="post">
            <table>
                <tr>
                    <th>QUT</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Amount</th>
                </tr>
                <tr>
                    
                </tr>
                <c:forEach var="item" items="${cart.items}">
                    <tr>
                    <form action="<c:url value='/DisplayCartServlet'/>" method="post">
                        <input type="hidden" name="productCode" value="$item.produc.code">
                        <input type="text" name="quantity" value="${item.quantity}">
                        <input type="submit" value="Update">
                    </form>
                        <td>${item.product.description}</td>
                        <td>${item.product.priceCurrencyFormat}</td>
                        <td>${item.totalCurrencyFormat}</td>
                        <td><imput type="submit" name="removeButton" value="Remove"></td>
                    </tr>
                </c:forEach>
            </table>
            </form>
           
        <%
            Cookie [] cookies = request.getCookies();
            for (Cookie c : cookies)
            {
        %>
        <tr>
            <td><%= c.getName()%></td>
            <td><%= c.getValue() %></td>
        <tr>
        
            <%
                }
%>
        </main>
    </body>
</html>
