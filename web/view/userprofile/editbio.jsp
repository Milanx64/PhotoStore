<%-- 
    Document   : editbio
    Created on : Nov 7, 2018, 12:54:05 PM
    Author     : comp-one
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="overlay"></div>
        <div class="container">
            <jsp:include page="../../inc/navmenu.jsp"></jsp:include>
        </div>
        <main id="editbio">
            <div class="editbio">
                <h1 class="text-secondary">${userauth.name}</h1>
                <p>Make your bio stude up from other on Photo Store by writing something about
                you. You currently have this written in your bio</p>
                <form action="EditServlet?email=${userauth.email}" method="post">
                    <textarea name="about" cols="10" class="biotext">${userauth.about}</textarea>
                    <input type="submit" value="submit" class="btn">
                </form>
            </div>
        </main>
    </body>
</html>
