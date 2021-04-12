package co.com.academica.utils;

import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Mensajes {
    

    public enum TIPOS_MENSAJE {

        OK, INFO, AYUDA, ALERTA, ERROR;
    }

    public Mensajes() {
    }

    /**
     * FIJA UN MENSAJE PARA EL USUARIO EN LA SESION SE DESPLEGARA LA PROXIMA VEZ
     * QUE SE CARGUE EL LOADER
     *
     * @param vsMensaje String con la cadena del mensaje
     * @param voTipoMensaje tipo de mensaje
     */
    public static void setMsg(String vsMensaje, TIPOS_MENSAJE voTipoMensaje, HttpServletRequest request) {
        if (vsMensaje == null) {
            return;
        }

        try {
            
            voTipoMensaje = voTipoMensaje == null ? TIPOS_MENSAJE.INFO : voTipoMensaje;

            HttpSession voSession = request.getSession();
            Vector vMensaje = new Vector();

            vMensaje.add(voTipoMensaje);
            vMensaje.add(vsMensaje);

            voSession.setAttribute("MENSAJE", vMensaje);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    /**
     * muestra en pantalla el mensaje que haya en sesion y posteriormente lo
     * elimina de la sesion
     *
     * @throws IOException
     */
    public static String dspMsg(HttpServletRequest request) {
        try {

            double idMsg = Math.random();

            Vector vMensaje = (Vector) request.getSession().getAttribute("MENSAJE");
            if (vMensaje == null) {
                //lo sacamos del request

                return "" + (request.getSession().getAttribute("MENSAJE") == null ? "" : request.getSession().getAttribute("MENSAJE"));
            }
            TIPOS_MENSAJE voTipo = (TIPOS_MENSAJE) vMensaje.get(0);
            String vsMensaje = (String) vMensaje.get(1);

            String vsTipoMsg = getTipoMensaje(voTipo);
            String vsTipoIcono = getTipoIcono (voTipo);

            String vsMsg = "<div id='mensajes' class='alert alert-" + vsTipoMsg + "' role='alert'>"
                    + "<span class='glyphicon glyphicon-"+vsTipoIcono+"' aria-hidden='true'></span>"+ " " +vsMensaje +"</div> ";

            //Se elimina el mensaje de la sesion:
            request.getSession().removeAttribute("MENSAJE");
            return vsMsg == null || vsMsg.equals("null") ? "" : vsMsg;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static String getTipoMensaje(TIPOS_MENSAJE voTipoM) {
        String vsResp = "";
        if (voTipoM.equals(TIPOS_MENSAJE.OK)) {
            vsResp = "success";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.INFO)) {
            vsResp = "info";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.AYUDA)) {
            vsResp = "info";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.ALERTA)) {
            vsResp = "warning";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.ERROR)) {
            vsResp = "danger";
        }
        return vsResp;
    }
    
    private static String getTipoIcono(TIPOS_MENSAJE voTipoM) {
        String vsResp = "";
        if (voTipoM.equals(TIPOS_MENSAJE.OK)) {
            vsResp = "ok-sign";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.INFO)) {
            vsResp = "exclamation-sign";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.AYUDA)) {
            vsResp = "warning-sign";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.ALERTA)) {
            vsResp = "warning-sign";
        }
        if (voTipoM.equals(TIPOS_MENSAJE.ERROR)) {
            vsResp = "remove-sign";
        }
        return vsResp;
    }
}
