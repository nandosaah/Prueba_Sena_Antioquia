/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.logica;

import co.com.academica.model.TblCiudades;
import co.com.academica.model.TblCiudadesMapper;
import co.com.academica.model.TblDocentes;
import co.com.academica.model.TblDocentesMapper;
import co.com.academica.model.TblEstudiantes;
import co.com.academica.model.TblEstudiantesMapper;
import co.com.academica.mybatis.MyBatisUtil;
import co.com.academica.utils.OptionVO;
import co.com.academica.utils.ResultadoJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author oskr
 */
public class DNEstudiantes {

    private HttpServletRequest peticion;

    public Object add(Object data) {
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //peticion = (HttpServletRequest) data;
            TblEstudiantes newEstudiante = gson.fromJson((String) data, TblEstudiantes.class);
            newEstudiante.setStrnombres(newEstudiante.getStrnombres().toUpperCase());
            newEstudiante.setStrdireccion(newEstudiante.getStrdireccion().toUpperCase());
            newEstudiante.setStrcodigo(newEstudiante.getStrtipoidentificacion() + newEstudiante.getStridentificacion());
            newEstudiante.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblEstudiantesMapper mapper = session.getMapper(TblEstudiantesMapper.class);
                    mapper.insert(newEstudiante);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(newEstudiante);
            resultado.setResult("OK");
            resultado.setMessage("Estudiante creado exitosamente!!");
            return resultado;
            //return newUser;

        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }
        return null;
    }

    public Object update(Object data) {
        try {
            //Gson gson = new Gson();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //peticion = (HttpServletRequest) data;
            TblEstudiantes updEstudiante = gson.fromJson((String) data, TblEstudiantes.class);

            updEstudiante.setStrnombres(updEstudiante.getStrnombres().toUpperCase());
            updEstudiante.setStrdireccion(updEstudiante.getStrdireccion().toUpperCase());
            updEstudiante.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblEstudiantesMapper mapper = session.getMapper(TblEstudiantesMapper.class);
                    mapper.updateByPrimaryKey(updEstudiante);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(updEstudiante);
            resultado.setResult("OK");
            resultado.setMessage("Estudiante actualizado exitosamente!!");
            return resultado;
            //return newUser;

        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }
        return null;
    }

    public Object delete(Object data) {
        // TODO Auto-generated method stub
        return false;
    }

    public Object list(Object data, Object startIndex, Object pageSize) {
        try {
            List<TblEstudiantes> estudiantesList = new ArrayList<TblEstudiantes>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblEstudiantesMapper mapper = session.getMapper(TblEstudiantesMapper.class);
                    estudiantesList = mapper.getEstudiantes();
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(estudiantesList.toArray());
            return resultado;
            //return userList;

            //response.getWriter().print(jsonArray);
        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }

        return null;
    }

    public Object listEstudiantes(Object data) {
        try {
            List<TblEstudiantes> estudiantesList = new ArrayList<TblEstudiantes>();
            List<OptionVO> opciones = new ArrayList<OptionVO>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblEstudiantesMapper mapper = session.getMapper(TblEstudiantesMapper.class);
                    estudiantesList = mapper.getEstudiantes();
                    if (estudiantesList != null && estudiantesList.size() > 0) {
                        OptionVO selected = new OptionVO();
                        selected.setValue(null);
                        selected.setDisplayText("--Seleccione Estudiante--");
                        opciones.add(selected);
                        for (TblEstudiantes obj : estudiantesList) {
                            OptionVO controla = new OptionVO();
                            controla.setValue(String.valueOf(obj.getNumestudiante()));
                            controla.setDisplayText(obj.getStrnombres());
                            opciones.add(controla);
                        }
                    }
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setOptions(opciones.toArray());
            return resultado;
            //return userList;

            //response.getWriter().print(jsonArray);
        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }

        return null;
    }

    public Object get(Object data) {
        // TODO Auto-generated method stub
        return null;
    }
}
