/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.utils;

/**
 *
 * @author oflorez
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jpazmin
 */
public class showFileServlet extends HttpServlet {

    private String strLogin;

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(true);
            strLogin = (String) session.getAttribute("user");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (strLogin != null) {
            String nombreArchivo = request.getParameter("url");
//        String dirName = this.getServletContext().getRealPath("");

            File file = new File(nombreArchivo);

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buffer = new byte[bis.available()];

            bis.read(buffer);
            bos.write(buffer);

//        response.setContentType("application/octet-stream");
            if (nombreArchivo.endsWith(".txt")) {
                response.setContentType("application/txt");
            } else {
                response.setContentType("application/tif");
            }
            response.setContentLength(bos.size());
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            if (nombreArchivo.endsWith(".txt")) {
                response.addHeader("Content-Disposition", "attachment; filename=reporte.txt");
            } else {
                response.addHeader("Content-Disposition", "attachment; filename=reporte.TIF");
            }
            response.setHeader("content-transfer-encoding", "binary");

            ServletOutputStream out = response.getOutputStream();
            bos.writeTo(out);

            out.flush();
            out.close();

            response.setStatus(response.SC_OK);
            //FacesContext.getCurrentInstance().responseComplete();
        } else {
            try {
                response.sendRedirect("login.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to
     * post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
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
