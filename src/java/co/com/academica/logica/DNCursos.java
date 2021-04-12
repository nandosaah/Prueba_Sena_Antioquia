/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.logica;

import co.com.academica.model.TblCiudades;
import co.com.academica.model.TblCiudadesMapper;
import co.com.academica.model.TblCurso;
import co.com.academica.model.TblCursoMapper;
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
public class DNCursos {

    public Object add(Object data) {
        try {
            Gson gson = new Gson();
            TblCurso newCurso = gson.fromJson((String) data, TblCurso.class);
            newCurso.setStrnombre(newCurso.getStrnombre().toUpperCase());
            newCurso.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCursoMapper mapper = session.getMapper(TblCursoMapper.class);
                    mapper.insert(newCurso); /* OJO VALIDE ESTA INFORMACION*/
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(newCurso);
            resultado.setResult("OK");
            resultado.setMessage("Curso creado exitosamente!!");
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
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblCurso updCurso = gson.fromJson((String) data, TblCurso.class);

            updCurso.setStrnombre(updCurso.getStrnombre().toUpperCase());
            updCurso.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCursoMapper mapper = session.getMapper(TblCursoMapper.class);
                    mapper.updateByPrimaryKey(updCurso);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(updCurso);
            resultado.setResult("OK");
            resultado.setMessage("Curso actualizado exitosamente!!");
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
            List<TblCurso> cursosList = new ArrayList<TblCurso>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCursoMapper mapper = session.getMapper(TblCursoMapper.class);
                    cursosList = mapper.getCursos();
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(cursosList.toArray());
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

    public Object listCursos(Object data) {
        try {
            List<TblCurso> cursosList = new ArrayList<TblCurso>();
            List<OptionVO> opciones = new ArrayList<OptionVO>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCursoMapper mapper = session.getMapper(TblCursoMapper.class);
                    cursosList = mapper.getCursos();
                    if (cursosList != null && cursosList.size() > 0) {
                        OptionVO selected = new OptionVO();
                        selected.setValue(null);
                        selected.setDisplayText("--Seleccione Curso--");
                        opciones.add(selected);
                        for (TblCurso obj : cursosList) {
                            OptionVO controla = new OptionVO();
                            controla.setValue(String.valueOf(obj.getNumcurso()));
                            controla.setDisplayText(obj.getStrnombre());
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
}
