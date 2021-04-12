/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.service;

import co.com.academica.utils.DESEncryption;
import co.com.academica.model.TblUsuarios;
import co.com.academica.logica.DNUsuarios;
import co.com.academica.utils.Mensajes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oskr
 */
public class LoginController extends HttpServlet {

    DNUsuarios servicio = new DNUsuarios();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if ("logout".equalsIgnoreCase(request.getParameter("param"))) {
                HttpSession session = request.getSession();
                session.removeAttribute("user");
                session.removeAttribute("userCompleto");
                session.removeAttribute("userRol");
                session.removeAttribute("ResultUploadZIP");
                session.removeAttribute("ResultUploadTif");
                session.removeAttribute("inconsistencias");
                session.invalidate();
                response.sendRedirect("login.jsp");
            } else {
                TblUsuarios voTnUsuario;
                TblUsuarios usuario = new TblUsuarios();

                String error;
                usuario.setStrlogin(request.getParameter("usuario"));
                usuario.setStrclave(request.getParameter("clave"));
                HttpSession session = request.getSession();

                DESEncryption enc = new DESEncryption();

                if (usuario.getStrclave() != null && usuario.getStrclave().trim().length() > 0) {
                    String strClave = enc.encrypt(usuario.getStrclave());
                    usuario.setStrclave(strClave);
                }
                //System.out.print(enc.encrypMD5(txtClave));
                voTnUsuario = servicio.validarUsuario(usuario); // llama al metodo validarUsuario que implementa la accion de ir a la base de datos y consultar y le manda el paràmetro de lo que digito el usuario
                if (voTnUsuario != null && voTnUsuario.getStrclave() != null) {
                    //System.out.print(voTnUsuario.getEmail());
                    //return "entrar"; //entrar es la cadena de texto que va hasta el archivo faces-config.xml y redirige al usuario a la pagina del home.jsp
                    session.setAttribute("user", voTnUsuario.getStrlogin());
                    session.setAttribute("userCompleto", voTnUsuario.getStrnombrecompleto());
                    session.setAttribute("userRol", voTnUsuario.getStrrol());
                    Mensajes.setMsg("Bienvenido " + voTnUsuario.getStrnombrecompleto(), Mensajes.TIPOS_MENSAJE.OK, request);
                    response.sendRedirect("home.jsp");
                } else {
                    throw new Exception("Usuario/Clave incorrectos");
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            Mensajes.setMsg("Error de conexiòn con la base de datos... verifique con el Administrador del sistema", Mensajes.TIPOS_MENSAJE.ERROR, request);
        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            Mensajes.setMsg(e.getMessage(), Mensajes.TIPOS_MENSAJE.ERROR, request);
            response.sendRedirect(request.getHeader("Referer"));
            //response.sendRedirect("login.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
