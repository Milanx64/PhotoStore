<%-- 
    Document   : profilephoto.jsp
    Created on : Nov 7, 2018, 12:06:19 PM
    Author     : comp-one
--%>



<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>


<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import="business.User" %>
<%--<% User user = (User)request.getAttribute("user"); %>--%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>

<%! String email;
    String userSTR;
    String imgUrl;
    String name;
    
%>      
<%
    User user = new User();
    if (session.getAttribute("userauth") == null){
        response.sendRedirect(request.getContextPath()+"/view/login.jsp");
    }
    else if(session.getAttribute("userauth") != null){
        user = (User) session.getAttribute("userauth");
        email = user.getEmail();
        
    }
   if(email != null){
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
   String filePath = context.getInitParameter("file-upload");

   // Verify the content type
   String contentType = request.getContentType();
   
   if ((contentType.indexOf("multipart/form-data") >= 0)) {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      
      try { 
         // Parse the request to get file items.
         //List fileItems = uploadHandler.parseRequest(new ServletRequestContext(request));
         //List fileItems items = uploadHandler.parseRequest(new ServletRequestContext(request))
         List fileItems = upload.parseRequest(new ServletRequestContext(request));
         // Process the uploaded file items
         Iterator i = fileItems.iterator();
         filePath = "C:/Users/comp-one/Documents/NetBeansProjects/PhotoStore/web/photos/profilephotos/";
         out.println("<html>");
         out.println("<head>");
         out.println("<title>JSP File upload</title>");  
         out.println("</head>");
         out.println("<body>");
         
         while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();
               String fileName = fi.getName();
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
               // Write the file
               /*if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + 
                  fileName.substring( fileName.lastIndexOf("\\"))) ;
               } else {
                  file = new File( filePath + 
                  fileName.substring(fileName.lastIndexOf("\\")+1)) ;
               }*/
               file = new File(filePath+fileName);
               fi.write( file ) ;
               out.println("Uploaded Filename: " + filePath + 
               fileName + "<br>");
              imgUrl = "photos/profilephotos/" + fileName;
              name = fileName;
              String link = request.getContextPath()+"/InsertDB?profilePhotoURL="+ imgUrl + "&artistEmail="+ email;
              response.sendRedirect(link);
            }
         }
         out.println("</body>");
         out.println("</html>");
      } catch(Exception ex) {
         System.out.println(ex);
      }
   } else {
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
      out.println("</body>");
      out.println("</html>");
   }
   /*Dodaj koda za brisanje fotografije i dodavanje nove u bazu podataka
   takodje je potrebno dodati timestemp imenu fotografije kao i karkteristicni
   znak koji ce da se trimuje ako je potrebno
   */
   }
   

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet" type="text/css" href="../css/style.css">

<title>Upload File</title>
</head>
<body>
    <%= userSTR %>
</body>
</html>
