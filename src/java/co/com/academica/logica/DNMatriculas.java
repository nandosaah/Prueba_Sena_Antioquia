/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.logica;

import co.com.academica.model.TblCalendarioMateria;
import co.com.academica.model.TblCalendarioMateriaMapper;
import co.com.academica.model.TblDocentes;
import co.com.academica.model.TblDocentesMapper;
import co.com.academica.model.TblMaterias;
import co.com.academica.model.TblMateriasMapper;
import co.com.academica.model.TblMatricula;
import co.com.academica.model.TblMatriculaMapper;
import co.com.academica.mybatis.MyBatisUtil;
import co.com.academica.service.Service;
import co.com.academica.utils.Mensajes;
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
public class DNMatriculas {

    
    public Object add(Object data) {
        try {
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblMatricula newMatricua = gson.fromJson((String) data, TblMatricula.class);
            newMatricua.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMatriculaMapper mapper = session.getMapper(TblMatriculaMapper.class);
                    mapper.insert(newMatricua);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(newMatricua);
            resultado.setResult("OK");
            resultado.setMessage("Matricula creada exitosamente!!");
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
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblMatricula updMatricula = gson.fromJson((String) data, TblMatricula.class);

            updMatricula.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMatriculaMapper mapper = session.getMapper(TblMatriculaMapper.class);
                    mapper.updateByPrimaryKey(updMatricula);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(updMatricula);
            resultado.setResult("OK");
            resultado.setMessage("Matricula actualizada exitosamente!!");
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
            Gson gson = new Gson();
            List<TblMatricula> matriculasList = new ArrayList<TblMatricula>();
            TblMatricula filtro = gson.fromJson((String) data, TblMatricula.class);
            
            int startPageIndex = Integer.parseInt(startIndex.toString());
            int recordsPerPage = Integer.parseInt(pageSize.toString());
            
            ResultadoJson resultado = new ResultadoJson();
            filtro.setStartPageIndex(startPageIndex);
            filtro.setRange(recordsPerPage);
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMatriculaMapper mapper = session.getMapper(TblMatriculaMapper.class);
                    matriculasList = mapper.getMatriculas(filtro);
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(matriculasList.toArray());
            return resultado;
            //return userList;

            //response.getWriter().print(jsonArray);
        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }

        return null;
    }
    
    public Object validaMatricula(Object data) {
        try {
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblMatricula objMatricula = gson.fromJson((String) data, TblMatricula.class);
            List<TblMatricula> retornoMatricula = new ArrayList<TblMatricula>();

            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMatriculaMapper mapper = session.getMapper(TblMatriculaMapper.class);
                    retornoMatricula = mapper.validaMatricula(objMatricula);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(retornoMatricula);
            resultado.setResult("OK");
            resultado.setMessage("Matricula!!");
            return resultado;
            //return newUser;

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
