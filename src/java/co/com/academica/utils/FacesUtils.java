/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.utils;

/**
 *
 * @author OFlorez
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtils {

    private static final String PROP_FILE = "config.properties";

    public enum TIPOS_USUARIO {

        ADMINISTRADOR, USUARIO, CARGA;
    }

    public static String getTipoUsuario(TIPOS_USUARIO tipoUsu) {
        String resp = "";
        if (tipoUsu.equals(TIPOS_USUARIO.ADMINISTRADOR)) {
            resp = "ADMINISTRADOR";
        } else if (tipoUsu.equals(TIPOS_USUARIO.USUARIO)) {
            resp = "USUARIOS";
        } else if (tipoUsu.equals(TIPOS_USUARIO.CARGA)) {
            resp = "CARGA";
        }

        return resp;
    }

    public enum TIPOS_ESTADO {

        ACTIVADO, NO_CONSIDERADO, APLAZADO, APROBADO, NO_APROBADO, PUBLICADO;
    }

    public static Long getIdEstado(TIPOS_ESTADO tipoEst) {
        Long resp = 0L;
        if (tipoEst.equals(TIPOS_ESTADO.ACTIVADO)) {
            resp = 1L;
        } else if (tipoEst.equals(TIPOS_ESTADO.NO_CONSIDERADO)) {
            resp = 2L;
        } else if (tipoEst.equals(TIPOS_ESTADO.APLAZADO)) {
            resp = 3L;
        } else if (tipoEst.equals(TIPOS_ESTADO.APROBADO)) {
            resp = 4L;
        } else if (tipoEst.equals(TIPOS_ESTADO.NO_APROBADO)) {
            resp = 5L;
        } else if (tipoEst.equals(TIPOS_ESTADO.PUBLICADO)) {
            resp = 6L;
        }
        return resp;
    }

    public enum TIPOS_ESTADO_USER {

        ACTIVADO, ELIMINADO;
    }

    public static String getIdEstadoUser(TIPOS_ESTADO_USER tipoEst) {
        String resp = "";
        if (tipoEst.equals(TIPOS_ESTADO_USER.ACTIVADO)) {
            resp = "A";
        } else if (tipoEst.equals(TIPOS_ESTADO_USER.ELIMINADO)) {
            resp = "I";
        }
        return resp;
    }

    public enum TIPOS_RENTA {

        Rentas, Industria;
    }

    public static String getIdTiposRenta(TIPOS_RENTA tipoRenta) {
        String resp = "";
        if (tipoRenta.equals(TIPOS_RENTA.Rentas)) {
            resp = "R";
        } else if (tipoRenta.equals(TIPOS_RENTA.Industria)) {
            resp = "I";
        }
        return resp;
    }

    public static Properties getProperties() throws Exception {
        Properties conf = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(PROP_FILE);
        conf.load(is);
        is.close();
        return conf;
    }

/*    public static List<TblCargaArchivos> getRutaArchivo(List<TblCargaArchivos> tablaCargaArchivos, Integer startPageIndex) throws Exception {
        Integer id = startPageIndex; //variable que controla el número de registros que retorno la consulta de la base de datos
        List<TblCargaArchivos> listCargaArchivos = new ArrayList<TblCargaArchivos>();
        Properties conf = FacesUtils.getProperties();
        for (TblCargaArchivos recorreTabla : tablaCargaArchivos) {
            id++;
            recorreTabla.setNumidcarga(Long.parseLong(id.toString()));

            //obtiene la ruta del archivo
            File newFile = new File(recorreTabla.getRutaDisk());  //FacesUtils.getRutaAplicacion() obtiene la ruta donde queda desplegada la aplicación, cualquier ubicacion
            if (newFile.exists()) { // si el archivo existe
                recorreTabla.setRutaDisk(conf.getProperty("urlDescarga") + conf.getProperty("config.ruta.carga") + File.separator + recorreTabla.getNumformulario());
            } else {//si el archivo no existe
                recorreTabla.setRutaDisk(null);
            }
            listCargaArchivos.add(recorreTabla);//adiciona el dato modificado a la tabla temporal
        }
        return listCargaArchivos;
    }*/

    public static Date DateString(String date) throws ParseException {
        SimpleDateFormat sdf;
        if (date.contains("/")) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            sdf = new SimpleDateFormat("yy-MM-dd");
        }
        Date fecha = null;
        fecha = sdf.parse(date);
        //System.out.println(fecha);
        return fecha;
    }

    public static boolean validaObligatorioCSV(Object voObjeto) {
        if (voObjeto == null || voObjeto.toString().trim().length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    public static String obtenerNombreArchivo(String nombre) {
        int pos = nombre.lastIndexOf(".");//buscar el caracter punto (.) en la cadena de texto y devuelve el numero de la posiciòn en la cadena
        if (pos == -1) {
            nombre = "";
        } else {
            nombre = nombre.substring(0, pos);
        }
        return nombre;
    }

    public static boolean isNumeric(String cadena) {
        //System.out.println("********Comunes.isNumeric*********");
        //System.out.println("********" + cadena + "*********");
        Pattern p = Pattern.compile("(((^[1-9])[0-9]*)$)|(^0{1}$)");
        Matcher m = p.matcher(cadena);

        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static String TimestampString() {
        Date date = new Date();
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyyMMddHHmmss");
        String fecha = sdf.format(date);
        //System.out.println(fecha);
        return fecha;
    }
}
