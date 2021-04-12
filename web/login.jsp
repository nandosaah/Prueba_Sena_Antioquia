<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="co.com.academica.utils.*"%>
<%
    if (session.getAttribute("user") != null) {
        response.sendRedirect("home.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sistema de Registro Académico - Cali</title>
        <jsp:include page="/template/styles.jsp"></jsp:include>
            <script type="text/javascript">
                $().ready(function() {
                    $("#loginForm").validate({
                        rules: {
                            usuario: { required: true, minlength: 2, maxlength: 10},
                            clave: { required: true, minlength: 2, maxlength: 16}                        
                        },
                        messages: {
                            usuario: {
                                required: "El campo usuario es obligatorio",
                                minlength: "El usuario debe contener al menos 2 caracteres",
                                maxlength: "El usuario debe contener máximo 10 caracteres"
                            },
                            clave: {
                                required: "El campo clave es obligatorio",
                                minlength: "La clave debe contener al menos 2 caracteres",
                                maxlength: "La clave debe contener máximo 16 caracteres"
                            }                        
                        }
                    });
                });
            </script>
        </head>
        <body>
        <jsp:include page="/template/header.jsp"></jsp:include>
        <%= Mensajes.dspMsg(request)%>
        <form id="loginForm" method="post" action="LoginController" class="form-horizontal">
            <div align="center" class="form-group">
                <table>
                    <tr>
                        <td>
                            <label class="label label-default" for="usuario">Usuario:</label>
                        </td>
                        <td>
                            <input maxlength="10" class="form-control input-sm" type="text" name="usuario" id="usuario"/>
                        </td>
                    </tr>
                    <tr>    
                        <td>
                            <label class="label label-default" for="password">Contraseña:</label>
                        </td>
                        <td>
                            <input maxlength="16" class="form-control input-sm" type="password" name="clave" id="clave"  />
                        </td>
                    </tr>
                </table>
                </br>
                <input class="btn btn-success" type="submit" value="Entrar">
            </div>  
        </form>
        <jsp:include page="/template/footer.jsp"></jsp:include>
    </body>
</html>