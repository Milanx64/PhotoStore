<%-- 
    Document   : dashboard
    Created on : Oct 1, 2018, 11:33:46 AM
    Author     : comp-one
--%>

<%@page import="data.EditServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page import="Admin.AdminServlet"%>
<%@page import="Admin.AdminUploadServlet"%>

<% 
    if (session.getAttribute("adminauth") == null){
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
%>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <title>Admin dashboard</title>
</head>
<body>
    <div class="overlay"></div>
	<jsp:include page="../inc/navmenu.html"></jsp:include>
        <main id="admin">
            <div class="center">
                <h1>Welcome, ${adminauth.name} to <span class="text-secondary">Admins dashboard</span></h1>
            </div>
            <div class="console">
                <p>Here you can</p>
                <a href="admin/addadmin.jsp" class="btn">Add new admin</a>
                <a href="${pageContext.request.contextPath}/AdminServlet?deleteUser=1" class="btn-danger">Delete User</a>
                <a href="${pageContext.request.contextPath}/AdminServlet?deletePhoto=1" class="btn-danger">Delete Photos</a>
               <a href="admin/upload.jsp" class="btn-success">Upload Photos</a>
               
            </div>
            <div class="data">
                <h3 class="text-secondary">Number of photos</h3>
                
                <h3 class="text-secondary">Ordered prints</h3>
                <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
                <sql:query dataSource = "${snapshot}" var = "result">
                    SELECT * From invoice;
                </sql:query>
               
                <c:forEach var = "row" items = "${result.rows}">
                <table border="1px"> 
                    <tr>
                        <td>User ID</td>
                        <td>Invoice Date</td>
                        <td>Total Amount</td>
                        <td>Is Processed</td>
                    </tr>
                       
                    <tr>
                        <td><c:out value = "${row.UserID}"/></td>
                        <td><c:out value = "${row.invoiceDate}"/></td>
                        <td><c:out value = "${row.TotalAmount}"/></td>
                        <td><c:out value = "${row.isprocessed}"/></td>
                     </tr>
                </c:forEach>
                </table>
                
            </div>
            <div class="data">
                <h3 class="text-secondary">Downloads</h3>
                <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
                <sql:query dataSource = "${snapshot}" var = "sql">
                    SELECT * From download;
                </sql:query>
                <table>
                <c:forEach var = "row" items = "${sql.rows}">
                    
                    <tr>
                        <td>User ID</td>
                        <td>Download Date</td>
                        <td>Product Code</td>
                    </tr>
                       
                <tr>
                    <td><c:out value = "${row.UserID}"/></td>
                    <td><c:out value = "${row.DownloadDate}"/></td>
                    <td><c:out value = "${row.ProductCode}"/></td>

                </tr>
                </c:forEach>
                </table>
            </div>
            <div class="data">
                <c:forEach var="row" items="${photos}" varStatus="loopCounter">  
            
                <div class="show-catalog">
                    
                    <a href="<c:url value='${request.getContextPath()}/InsertDB?photoID=${row.id}'/>">
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
