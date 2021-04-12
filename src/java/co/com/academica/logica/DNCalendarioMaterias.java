/*
 * Hernando Saa H
 *  */
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
public class DNCalendarioMaterias {

    
    public Object add(Object data) {
        try {
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblCalendarioMateria newMateria = gson.fromJson((String) data, TblCalendarioMateria.class);
            newMateria.setStrobservaciones(newMateria.getStrobservaciones().toUpperCase());
            newMateria.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCalendarioMateriaMapper mapper = session.getMapper(TblCalendarioMateriaMapper.class);
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
            resultado.setMessage("Horario creado exitosamente!!");
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
            TblCalendarioMateria updMateria = gson.fromJson((String) data, TblCalendarioMateria.class);
            updMateria.setStrobservaciones(updMateria.getStrobservaciones().toUpperCase());
            updMateria.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCalendarioMateriaMapper mapper = session.getMapper(TblCalendarioMateriaMapper.class);
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
            resultado.setMessage("Horario actualizado exitosamente!!");
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

    public Object listaHorario(Object data) {
        try {
            Gson gson = new Gson();
            TblCalendarioMateria obj = new TblCalendarioMateria();
            List<TblCalendarioMateria> calendarioMateriasList = new ArrayList<TblCalendarioMateria>();
            int filtro = gson.fromJson((String) data, Integer.class);
            obj.setNummateria(filtro);
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCalendarioMateriaMapper mapper = session.getMapper(TblCalendarioMateriaMapper.class);
                    calendarioMateriasList = mapper.getCalendarioMaterias(obj);
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(calendarioMateriasList.toArray());
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
