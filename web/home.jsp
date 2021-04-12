<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sistema de Registro Académico - Cali</title>
        <jsp:include page="/template/styles.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="/template/header.jsp"></jsp:include>
        <%
            String user = (String) session.getAttribute("user");
            if (user != null) {
        %>
       <jsp:include page="/template/menu.jsp"></jsp:include>
        <%
        } else {
        %>
        <h3>Your don't have permission to access this page</h3>
        <%                            
        response.sendRedirect("login.jsp");
        }
        %>
        <jsp:include page="/template/footer.jsp"></jsp:include>
    </body>
</html>