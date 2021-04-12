/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.logica;

import co.com.academica.model.TblCiudades;
import co.com.academica.model.TblCiudadesMapper;
import co.com.academica.model.TblDocentes;
import co.com.academica.model.TblDocentesMapper;
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
public class DNDocentes {

    public Object add(Object data) {
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //peticion = (HttpServletRequest) data;
            TblDocentes newDocente = gson.fromJson((String) data, TblDocentes.class);
            newDocente.setStrnombres(newDocente.getStrnombres().toUpperCase());
            newDocente.setStrdireccion(newDocente.getStrdireccion().toUpperCase());
            newDocente.setStrcodigo(newDocente.getStrtipoidentificacion() + newDocente.getStridentificacion());
            newDocente.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblDocentesMapper mapper = session.getMapper(TblDocentesMapper.class);
                    mapper.insert(newDocente);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(newDocente);
            resultado.setResult("OK");
            resultado.setMessage("Docente creado exitosamente!!");
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
            TblDocentes updDocente = gson.fromJson((String) data, TblDocentes.class);

            updDocente.setStrnombres(updDocente.getStrnombres().toUpperCase());
            updDocente.setStrdireccion(updDocente.getStrdireccion().toUpperCase());
            updDocente.setDtmfecharegistro(new Timestamp(new Date().getTime()));
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblDocentesMapper mapper = session.getMapper(TblDocentesMapper.class);
                    mapper.updateByPrimaryKey(updDocente);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(updDocente);
            resultado.setResult("OK");
            resultado.setMessage("Docente actualizado exitosamente!!");
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
            List<TblDocentes> docentesList = new ArrayList<TblDocentes>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblDocentesMapper mapper = session.getMapper(TblDocentesMapper.class);
                    docentesList = mapper.getDocentes();
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(docentesList.toArray());
            return resultado;
            //return userList;

            //response.getWriter().print(jsonArray);
        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }

        return null;
    }

    public Object consultaCiudad(Object data) {
        try {
            List<TblCiudades> listCiudad = new ArrayList<TblCiudades>();
            List<OptionVO> opciones = new ArrayList<OptionVO>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;
            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblCiudadesMapper mapper = session.getMapper(TblCiudadesMapper.class);
                    listCiudad = mapper.getCiudades();
                    if (listCiudad != null && listCiudad.size() > 0) {
                            OptionVO selected = new OptionVO();
                            selected.setValue(null);
                            selected.setDisplayText("--Seleccione Ciudad--");
                            opciones.add(selected);
                            for (TblCiudades obj : listCiudad) {
                                OptionVO controla = new OptionVO();
                                controla.setValue(String.valueOf(obj.getNumciudad()));
                                controla.setDisplayText(obj.getStrciudad());
                                opciones.add(controla);
                            }
                        }
                        totalRegistros = mapper.countByExample();
                        //listTipoIngreso = session.selectList("TblTipoFactComproMapper.getTipoIngreso");
                    }  finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setOptions(opciones.toArray());
            return resultado;
        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
        }
        return null;
    }

    public Object get(Object data) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object listDocentes(Object data) {
        try {
            List<TblDocentes> docentesList = new ArrayList<TblDocentes>();
            List<OptionVO> opciones = new ArrayList<OptionVO>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblDocentesMapper mapper = session.getMapper(TblDocentesMapper.class);
                    docentesList = mapper.getDocentes();
                    if (docentesList != null && docentesList.size() > 0) {
                        OptionVO selected = new OptionVO();
                        selected.setValue(null);
                        selected.setDisplayText("--Seleccione Docente--");
                        opciones.add(selected);
                        for (TblDocentes obj : docentesList) {
                            OptionVO controla = new OptionVO();
                            controla.setValue(String.valueOf(obj.getNumdocente()));
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
}
