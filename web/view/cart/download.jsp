<%-- 
    Document   : download.jsp
    Created on : Oct 11, 2018, 2:09:38 PM
    Author     : comp-one
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
 
<%
    String productID = request.getParameter("productCode");
    
%>
    

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../../css/style.css">
    </head>
<body>
    <div class="overlay"></div>
    <div class="container">
        <div class="container">
            <jsp:include page="../inc/navmenu.jsp"></jsp:include>
        </div>
    </div>
    
    ${user}
    ${user.name}
    ${user.lastname}
    ${user.email}
    ${invoice}
    <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                url = "jdbc:mysql://localhost/phototest"
                user = "root"  password = ""/>
    <sql:query dataSource = "${snapshot}" var = "result">
        SELECT * From photos WHERE id = '<%= productID %>';
    </sql:query>
    <sql:query dataSource = "${snapshot}" var = "sql">
                SELECT * From users WHERE email = '${user.email}';
    </sql:query>
    <table border="1px">
       <c:if test="${user.email !=null}">
            <c:forEach var = "row" items = "${result.rows}">
            <tr>
                <th>Price</th>
                <th>Type</th>
                <th>About</th>
            </tr>
            <tr>

               <td><c:out value = "${row.price}"/></td>
               <td><c:out value = "${row.type}"/></td>
               <td><c:out value = "${row.about}"/></td>
               <td><a href="<c:url value='/DownloadServlet?name=${row.name}&${row.id}'/>">Download from here</a></td>
            </tr>
            </c:forEach>
            <c:forEach var = "row" items = "${sql.rows}">
                <td><c:out value = "${row.name}"/></td>
            </c:forEach>
        </c:if>
                ${user.email}
        <c:if test="${user.email == null}">
            
            <c:redirect url="/view/signup.jsp"></c:redirect>
        </c:if>
        
    
        
    </table>
    
            
                <a href="/DownloadServlet?"></a>
    <script src="../../js/main.js"></script>
</body>
</html>
