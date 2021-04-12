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
public class DNMaterias {

    
    public Object add(Object data) {
        try {
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblMaterias newMateria = gson.fromJson((String) data, TblMaterias.class);
            newMateria.setStrnombre(newMateria.getStrnombre().toUpperCase());
            newMateria.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMateriasMapper mapper = session.getMapper(TblMateriasMapper.class);
                    mapper.insert(newMateria);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(newMateria);
            resultado.setResult("OK");
            resultado.setMessage("Materia creada exitosamente!!");
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
            TblMaterias updMateria = gson.fromJson((String) data, TblMaterias.class);

            updMateria.setStrnombre(updMateria.getStrnombre().toUpperCase());
            updMateria.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMateriasMapper mapper = session.getMapper(TblMateriasMapper.class);
                    mapper.updateByPrimaryKey(updMateria);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(updMateria);
            resultado.setResult("OK");
            resultado.setMessage("Materia actualizada exitosamente!!");
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
            List<TblMaterias> materiasList = new ArrayList<TblMaterias>();
            TblMaterias filtro = gson.fromJson((String) data, TblMaterias.class);
            
            int startPageIndex = Integer.parseInt(startIndex.toString());
            int recordsPerPage = Integer.parseInt(pageSize.toString());
            
            ResultadoJson resultado = new ResultadoJson();
            filtro.setStartPageIndex(startPageIndex);
            filtro.setRange(recordsPerPage);
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMateriasMapper mapper = session.getMapper(TblMateriasMapper.class);
                    materiasList = mapper.getMaterias(filtro);
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(materiasList.toArray());
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
    
    public Object getMateriasByCursos(Object data) {
        try {
            Gson gson = new Gson();
            TblMaterias obj = new TblMaterias();
            List<TblMaterias> materiasList = new ArrayList<TblMaterias>();
            int filtro = gson.fromJson((String) data, Integer.class);
            obj.setNumcurso(filtro);
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblMateriasMapper mapper = session.getMapper(TblMateriasMapper.class);
                    materiasList = mapper.getMateriasByCurso(obj);
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(materiasList.toArray());
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
