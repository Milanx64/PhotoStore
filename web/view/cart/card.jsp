<%-- 
    Document   : card
    Created on : Oct 10, 2018, 2:12:03 PM
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

<title>About Photo Store</title>
</head>
<body>
    <div class="overlay"></div>
  <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
    <main id="card">
        <h3 class="text-secondary">Please add your credit card information</h3>
        <form action="<c:url value='/CompleteOrderServlet'/>" method="post">
            <div class="float form-content1">
                <p class="form-iteam">Credit card type</p>
                <select name="CardType" size="11">
                    <option value="visa">Visa</option>
                    <option value="mastercard">Mastercard</option>
                    <option value="ae">American Express</option>
                </select>

            </div>
            <div class="form-content2">

                <p class="form-iteam">Credit card number</p>
                <input type="text" name="CardNumber" placeholder="" required
                        class="input">

            </div>

            <div class="form-content3">

                <p class="form-iteam">Expiration date (mm/yyyy)</p>
                <select name="ExpirationDate" size="12">
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>

            </div>

            <div class="form-content4">

                <p class="form-iteam">EXpiration year</p>
                <selsect name="ExpirationYear">
                    <c:forEach var="year" items="${creditCardYears}">
                        <oprtion value="year">${year}                           
                    </c:forEach>
                </selsect>
            </div>
            <p>

                <button type="submint" value="SubmitOrder" id="submit-btn"
                        class="form-content5">SubmitOrder</button>
            </p>

        </form>
    <%
        
    %>
    
    </main>
</body>
</html>