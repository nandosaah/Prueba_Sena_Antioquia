package co.com.academica.logica;

//import co.com.hacienda.model.TblCargaArchivos;
import co.com.academica.model.TblUsuarios;
import co.com.academica.model.TblUsuariosMapper;
import co.com.academica.mybatis.MyBatisUtil;
import co.com.academica.service.Service;
import co.com.academica.utils.DESEncryption;
import co.com.academica.utils.ResultadoJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;

public class DNUsuarios {

    private HttpServletRequest peticion;

    public Object add(Object data) {
        try {
            Gson gson = new Gson();
            //peticion = (HttpServletRequest) data;
            TblUsuarios newUser = gson.fromJson((String) data, TblUsuarios.class);
            newUser.setStrlogin(newUser.getStrlogin().toUpperCase());
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblUsuariosMapper mapper = session.getMapper(TblUsuariosMapper.class);
                    mapper.insertUsuario(newUser);
                    session.commit();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(newUser);
            resultado.setResult("OK");
            resultado.setMessage("Usuario creado exitosamente!!");
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
            TblUsuarios updUser = gson.fromJson((String) data, TblUsuarios.class);

            String claveClaro = updUser.getStrclave();
            updUser.setStrclave(generaClaveMD5(claveClaro));
            updUser.setStrlogin(updUser.getStrlogin().toUpperCase());
            ResultadoJson resultado = new ResultadoJson();

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblUsuariosMapper mapper = session.getMapper(TblUsuariosMapper.class);
                    mapper.updateUsuario(updUser);
                    session.commit();
                    updUser.setStrclave(claveClaro);
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }
            resultado.setRecord(updUser);
            resultado.setResult("OK");
            resultado.setMessage("Usuario actualizado exitosamente!!");
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

    public Object get(Object data) {
        // TODO Auto-generated method stub
        return null;
    }

    public TblUsuarios validarUsuario(TblUsuarios user) {
        TblUsuarios voResp = new TblUsuarios();
        SqlSession session = new MyBatisUtil().getSession();
        if (session != null) {
            try {
                //DESPUES
                TblUsuariosMapper mapper = session.getMapper(TblUsuariosMapper.class);
                voResp = mapper.getByLogin(user);

                //ANTES
                //voResp = session.selectOne("TblUsuariosMapper.getByLogin", usuario); // metodo TblUsuarios.getByLogin que esta mapeado en el TblUsuariosMapper.xml
            } finally {
                session.close();
            }
        } else {
            System.out.println("Error de session");
        }
        return voResp;
    }

    public Object list(Object data, Object startIndex, Object pageSize) {
        try {
            List<TblUsuarios> userList = new ArrayList<TblUsuarios>();
            ResultadoJson resultado = new ResultadoJson();
            Integer totalRegistros = 0;

            SqlSession session = new MyBatisUtil().getSession();
            if (session != null) {
                try {
                    TblUsuariosMapper mapper = session.getMapper(TblUsuariosMapper.class);
                    userList = mapper.getUsuarios();
                    userList = mostrarClaveClaro(userList);
                    totalRegistros = mapper.countByExample();
                } finally {
                    session.close();
                }
            } else {
                System.out.println("Error de session");
            }

            resultado.setResult("OK");
            resultado.setTotalRecordCount(totalRegistros);
            resultado.setRecords(userList.toArray());
            return resultado;
            //return userList;

            //response.getWriter().print(jsonArray);
        } catch (Exception ex) {
            String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
            //response.getWriter().print(error);
        }

        return null;
    }

    public String generaClaveMD5(String claveClaro) {
        String claveMd5;
        DESEncryption enc = new DESEncryption();
        claveMd5 = enc.encrypt(claveClaro);
        return claveMd5;
    }

    public String descifraClaveMD5(String claveCifrada) {
        String claveClaro;
        DESEncryption enc = new DESEncryption();
        claveClaro = enc.decrypt(claveCifrada);
        return claveClaro;
    }

    public List<TblUsuarios> mostrarClaveClaro(List<TblUsuarios> userList) {
        //mostrar la clave en claro
        List<TblUsuarios> tmpTabla = new ArrayList<TblUsuarios>();
        for (TblUsuarios user : userList) {
            String clave = user.getStrclave();
            user.setStrclave(descifraClaveMD5(clave));
            tmpTabla.add(user);
        }
        return tmpTabla;
    }
}
