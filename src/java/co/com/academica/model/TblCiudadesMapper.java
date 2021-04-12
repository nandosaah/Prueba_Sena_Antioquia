package co.com.academica.model;

import co.com.academica.model.TblCiudades;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblCiudadesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int countByExample();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int deleteByExample(TblCiudades example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer numciudad);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int insert(TblCiudades record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int insertSelective(TblCiudades record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    List<TblCiudades> selectByExample(TblCiudades example);

    
     /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    List<TblCiudades> getCiudades();
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    TblCiudades selectByPrimaryKey(Integer numciudad);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblCiudades record, @Param("example") TblCiudades example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblCiudades record, @Param("example") TblCiudades example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblCiudades record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_ciudades
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblCiudades record);
}