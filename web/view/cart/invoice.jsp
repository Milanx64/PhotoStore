<%-- 
    Document   : invoice
    Created on : Oct 10, 2018, 1:57:32 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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

<title>Invoice</title>
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
    
    <main id="invoice">
        <h1 class="lg-heading"><span class="text-secondary">Your Invoice</span></h1>
        <table border="1px">
            <tr>
                <td>Date</td>
                <td>${invoice.invoiceDateDefaultFormat}</td>
                <td></td>
            </tr>
            <tr>
                <td>Ship print to</td>
                <td>${user.name}</td>
                <td></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td>Description</td>
                <td>price</td>
                <td>Total</td>
            </tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                   <td>${item.quantity}</td>
                    <td>${item.product.description}</td>
                    <td>${item.product.priceCurrencyFormat}</td>
                    <td>${item.totalCurrencyFormat}</td>
               </tr>  
            </c:forEach>
               <tr>
                   <td colspan="2" class="con">
                       <button class="btn">
                           <a  href="<c:url value='card.jsp'/>">Continue</a>
                       </button>
                       
                   </td>
                   <td colspan="2" class="con">
                       <button class="btn" >
                           <a  href="<c:url value='cart.jsp'/>">Back</a>
                       </button>
                   </td>
            </tr>
        </table>
    </main>
    
    <script src="../../js/main.js"></script>
</body>
</html>