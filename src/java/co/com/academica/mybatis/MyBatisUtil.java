/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.mybatis;

import co.com.academica.utils.Mensajes;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author oskr
 */
 public class MyBatisUtil {
    private String resource = "co/com/academica/mybatis/mybatis-config.xml";
    private SqlSession session = null;
    
    public SqlSession getSession (){
        try{
            Reader reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            session = sqlMapper.openSession();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return session;        
    }    
}
