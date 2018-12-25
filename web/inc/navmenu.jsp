<%-- 
    Document   : navmenu
    Created on : Nov 28, 2018, 4:10:28 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title></title>
    </head>
    <body>
         <header>
            <div class="menu-btn">
                    <div class="btn-line"></div>
                    <div class="btn-line"></div>
                    <div class="btn-line"></div>
            </div>
            <nav class="menu">
                    <div class="nav">
                        <div class="list">
                            <ul class="menu-nav">
                                    <li class="nav-item">
                                        <a href="index.jsp" class="nav-link"><span class="text-secondary">PhotoStore</span></a>
                                    </li>
                                    <li class="nav-item" id="nav-form">
                                        <form class="form-inline search" action="PhotoCatalogServlet" method="post">
                                            <input class="" type="text" name="type" placeholder="Search">
                                            <button class="" type="submit">Search</button>
                                        </form>
                                    </li>
                                    <li class="dropdown nav-item">
                                        <a href="javascript:void(0)" class="dropbtn">Browse</a>
                                        <div class="dropdown-content">
                                          <a href="/PhotoStore/PhotoCatalogServlet">All photos</a>
                                          <a href="#">Popular</a>
                                          <a href="#">Recent</a>
                                        </div>
                                      </li>
                                    <li class="nav-item">
                                        <a href="view/login.jsp" class="nav-link">License</a>
                                    </li>
                                    <li class="nav-item"><a href="view/signup.jsp"
                                            class="nav-link">Upload your photos</a></li>
                                    <li class="dropdown nav-item">
                                        <a href="javascript:void(0)" class="dropbtn">***</a>
                                        <div class="dropdown-content">
                                          <%
                                           if (session.getAttribute("userauth") == null || session.getAttribute("adminauth") == null){
                                                %>
                                                    <a href="view/login.jsp">Login</a>
                                                    <a href="view/signup.jsp">Sign up</a>
                                                <%
                                                }
                                           %>
                                           <%
                                           if (session.getAttribute("userauth") != null  || session.getAttribute("adminauth") != null){
                                                %>  
                                                    <a href="${pageContext.request.contextPath}/ShowMeMyProfile?user=${userauth.email}">My Profile</a>
                                                    <a href="${pageContext.request.contextPath}/AdminLogOut">Logout</a>
                                                    
                                                    <%
                                                }
                                           %>
                                          <a href="#">About us</a>
                                          <a href="#">FAQ</a>
                                        </div>
                                      </li>


                            </ul>
                        </div>
                    </div>
            </nav>
    </header>
    </body>
</html>
