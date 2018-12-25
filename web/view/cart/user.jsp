<%-- 
    Document   : user
    Created on : Oct 10, 2018, 1:26:30 PM
    Author     : comp-one
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" type="text/css" href="../../css/style.css">

<title>Photo Catalog</title>
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
            <jsp:include page="../inc/navmenu.jsp"></jsp:include>
        </div>
    
    <main id="user">
        <h3><span class="text-secondary">To prossed with your purches you will need to</span></h3>
        <a href="view/signup.jsp">Sign Up</a>
        <a href="<c:url value='view/signup.jsp'/>">Sign In</a>
        <p><span class="text-secondary">Or create acaunt for one download below</span></p>
        
        <form action="<c:url value='/ProcessUserServlet'/>" method="get">
            <div class="float form-content1">

                <p class="form-iteam">First Name</p>
                <input type="text" name="name" placeholder="" required class="input">

            </div>
            <div class="form-content2">

                <p class="form-iteam">last Name</p>
                <input type="text" name="lastname" placeholder="" required
                        class="input">

            </div>

            <div class="form-content3">

                <p class="form-iteam">Email</p>
                <input type="email" name="email" placeholder="" required
                        class="input">

            </div>

            <div class="form-content4">

                <p class="form-iteam">Password</p>
                <input type="password" name="password" placeholder="" required
                        class="input">

            </div>
            <p>

                <button type="submint" value="Sign Up" id="submit-btn"
                        class="form-content5">Sign Up</button>
            </p>

        </form>
        
        
    </main>
    
    
    <script src="../../js/main.js"></script>
</body>
</html>
