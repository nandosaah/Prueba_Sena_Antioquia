package co.com.academica.service;
import co.com.academica.utils.Mensajes;
import org.apache.commons.lang3.StringEscapeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import co.com.academica.utils.ResultadoJson;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private HttpServletRequest peticion;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);

    }

    private void doProcess(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            //peticion = request;

            String serviceName = (String) request.getParameter("service"); //toma el tipo de DNmateria o DNdocente
            String actionName = (String) request.getParameter("action"); //si va adicionar actualizar..
            String data = (String) request.getParameter("data");
            String startIndex = (String) request.getParameter("jtStartIndex");
            String pageSize = (String) request.getParameter("jtPageSize");

            ArrayList<Object> parameters = new ArrayList<Object>();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            response.setContentType("application/json"); //mete todo en la variable response del gson de arriba

            ResultadoJson outputService = new ResultadoJson(); //trae todo los resultados del gson
            Object result = outputService;

            if (serviceName != null && actionName != null && data != null) {

                //se adiciona el data al listado de parametros que se le van a enviar al metodo a invocar
                parameters.add(data);
                //se adicionan otros parametros
                if (actionName.equals("list")) {
                    if (startIndex != null && pageSize != null) {
                        parameters.add(Integer.parseInt(startIndex));
                        parameters.add(Integer.parseInt(pageSize));
                    }
                }
            } else {
                // TODO: ERROR: parametros service o action no suministrados
                response.getWriter().write("Fallo: " + result);
            }
            result = loadService(serviceName, actionName, parameters);// el utimo parametro arreylist inserta un arrayList 0,10
            //outputService = (ResultadoJson) result;
            //response.getWriter().write(StringEscapeUtils.escapeEcmaScript(gson.toJson(result)).replaceAll("\\\\\"", "\""));
            String jsonArray = gson.toJson(result); // manda el json a un array
            //String jsonData = "{\"Result\":\"OK\",\"Records\":" + jsonArray + "}";
            response.getWriter().print(jsonArray);
            //result = "{\"Result\":\"OK\",\"Records\":" + result + "}";
            //response.getWriter().print(gsonEncoder.toJson(result));
            //Mensajes.setMsg(outputService.getMessage(), Mensajes.TIPOS_MENSAJE.OK, request);
        } catch (Exception e) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage() + "}";
            response.getWriter().print(error);
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param serviceName
     * @param actionName
     * @return {@link}
     * http://viralpatel.net/blogs/java-dynamic-class-loading-java-reflection-api/
     * {@link}
     * http://www.javaworld.com/jw-12-2000/jw-1221-reflection.html?page=1
     * {@link}
     * http://stackoverflow.com/questions/13259329/reflection-java-lang-classnotfoundexception
     */
    @SuppressWarnings("unchecked")
    private Object loadService(String serviceName, String actionName, ArrayList<Object> parameters) {
        ResultadoJson outputService = new ResultadoJson();

        try {
            //ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();
            ClassLoader myClassLoader = getClass().getClassLoader();

            // Step 2: Define a class to be loaded.
            String classNameToBeLoaded = "co.com.academica.logica.";
            classNameToBeLoaded += serviceName;
            //outputService.setMessage("Paso2");

            // Step 3: Load the class
            Class myClass = myClassLoader.loadClass(classNameToBeLoaded);
            //outputService.setMessage("Paso3");

            // Step 4: create a new instance of that class
            Object whatInstance = myClass.newInstance();
            //outputService.setMessage("Paso4");

            //String methodParameter = "Hola Mundo...";

            // Step 4.1: get the method processInput
			/*Method processInputMethod = myClass.getMethod("processInput", new Class[] { String.class });
             Object returnValueInputMethod = (Object) processInputMethod.invoke(whatInstance,
             new Object[] { data });
             parameters.add(returnValueInputMethod);*/

            // Step 5: get the method, with proper parameter signature.
            // The second parameter is the parameter type.
            // There can be multiple parameters for the method we are trying to
            // call,
            // hence the use of array.
            Method myMethod = null;
                if (actionName.equals("list")) {
                myMethod = myClass.getMethod(actionName, Object.class, Object.class, Object.class);
            } else {
                myMethod = myClass.getMethod(actionName, new Class[]{Object.class});
            }
            //outputService.setMessage("Paso5");

            // Step 6:
            // Calling the real method. Passing methodParameter as
            // parameter. You can pass multiple parameters based on
            // the signature of the method you are calling. Hence
            // there is an array.
            Object returnValue = null;
            if (actionName.equals("list")) {
                returnValue = (Object) myMethod.invoke(whatInstance,
                        parameters.get(0), parameters.get(1), parameters.get(2));
            } else {
                returnValue = (Object) myMethod.invoke(whatInstance,
                        parameters.toArray());
                //new Object[] { returnValueInputMethod });
            }

            return returnValue;

        } catch (Exception e) {
            outputService.setError("ERROR: " + e.getStackTrace());
            String message = "";
            if (e.getMessage() != null) {
                message = e.getMessage() + " ";
            }
            if (e.getCause() != null) {
                message += e.getCause().getMessage() + " ";
            }
            outputService.setMessage("ERROR: " + message);
            e.printStackTrace();
        }

        return outputService;
    }
}
