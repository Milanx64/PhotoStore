<%-- 
    Document   : update
    Created on : Oct 29, 2018, 3:46:30 PM
    Author     : comp-one
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import ="java.sql.*" %>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/phototest"
         user = "root"  password = ""/>
<sql:query dataSource = "${snapshot}" var = "result">
    SELECT * FROM photos WHERE artistEmail = ?
    <sql:param value="${param.email}"/>
</sql:query>
<c:forEach var="row" items="${results.rows}">
    <c:set var="price" value="${row.price}"/>
    <c:set var="type" value="${row.type}"/>
    <c:set var="about" value="${row.about}"/>
    
</c:forEach>
<c:if test="${param.price != null}">
    <c:set var="price" value="${param.price}"/>
</c:if>
<c:if test="${param.about != null}">
    <c:set var="about" value="${param.about}"/>
</c:if>
<c:if test="${param.type != null}">
    <c:set var="type" value="${param.type}"/>
</c:if>
<sql:update dataSource = "${snapshot}" var = "result">
        
        UPDATE photos SET price= ?, about= ?, type= ? WHERE artistEmail=? and id =?;
        <sql:param value="${price}" />
        <sql:param value="${about}" />
        <sql:param value="${type}" />
        <sql:param value="${param.email}"/>
        <sql:param value="${param.photoID}"/>
</sql:update>
        <c:redirect url="myprofil.jsp?email=${param.email}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        

    </body>
</html>

