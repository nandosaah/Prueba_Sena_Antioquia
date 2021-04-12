package co.com.academica.model;

import co.com.academica.model.TblCalendarioMateria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblCalendarioMateriaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int countByExample();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int deleteByExample(TblCalendarioMateria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer numcalendario);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int insert(TblCalendarioMateria record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int insertSelective(TblCalendarioMateria record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    List<TblCalendarioMateria> selectByExample(TblCalendarioMateria example);
    
    List<TblCalendarioMateria> getCalendarioMaterias(TblCalendarioMateria a);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    TblCalendarioMateria selectByPrimaryKey(Integer numcalendario);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblCalendarioMateria record, @Param("example") TblCalendarioMateria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblCalendarioMateria record, @Param("example") TblCalendarioMateria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblCalendarioMateria record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_calendario_materia
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblCalendarioMateria record);
}