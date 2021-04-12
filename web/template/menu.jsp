<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="co.com.academica.utils.*"%>
<%= Mensajes.dspMsg(request)%>
<%String userCompleto = (String) session.getAttribute("userCompleto");
    String userRol = (String) session.getAttribute("userRol");
%>

<form id="formMenu">
    <div align="center" class="form-group">
        <table width="100%">
            <tr>
                <td width="20%">
                </td>
                <td width="60%">
                    <div class="menuClass">
                        <div class="btn-group">
                            <a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/registroCurso.jsp">Cursos</a>
                            <a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/registroDocente.jsp">Docentes</a>
                            <a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/registroEstudiante.jsp">Estudiantes</a>
                            <a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/registroMateria.jsp">Materias</a>
                            <a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/registroMatricula.jsp">Matrícula</a>
                            <%
                                if (userRol != null && userRol.equalsIgnoreCase("ADMINISTRADOR")) {%>
                            <a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/usuarios.jsp">Usuarios</a>
                            <%
                                }
                            %>
                            <a class="btn btn-success btn-sm" href="LoginController?param=logout">Cerrar</a>
                        </div>
                    </div>    
                </td>
                <td width="20%">
                    <div align="right">
                        <span>Usuario: </span>
                        <% out.println("<label class='label label-default'>" + userCompleto + "</label>");%>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</form>