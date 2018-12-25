<%-- 
    Document   : cart
    Created on : Oct 6, 2018, 3:56:25 PM
    Author     : comp-one
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
        <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
        <main id="cart">
        <h1 class="lg-heading"><span class="text-secondary">Your</span>Cart</h1>
            <form action="<c:url value='/DisplayCartServlet'/>" method="post">

                <table border="1px">
                    <tr>
                        <th>Quy</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Amount</th>
                        
                    </tr>
                    <c:forEach var="item" items="${cart.items}">
                        <tr>
                        <form action="<c:url value='/DisplayCartServlet'/>" method="post">
                            <input type="hidden" name="productCode" value="${item.product.code}">
                            <td><input type="number" name="quantity" value="${item.quantity}">
                                <input type="submit" value="Update" class="btn"></td>
                        </form>
                            
                            <td>${item.product.description}</td>
                            <td>${item.product.priceCurrencyFormat}</td>
                            <td>${item.totalCurrencyFormat}</td>
                            <imput type="submit" name="removeButton" value="Remove">
                        </tr>
                    </c:forEach>
                        
                </table>
            </form>
            <div class="btns">
                <form action=<c:url value='/CheckCartUserServlet'/> method="get">
                    <input type="submit" value="Checkout" class="btn">
                </form>
                <a class="btn" href=<c:url value="/PhotoCatalogServlet"/>>Continue Shopping</a>
            </div>
             
        
        </main>
    </body>
</html>
