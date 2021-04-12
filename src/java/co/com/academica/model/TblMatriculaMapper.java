package co.com.academica.model;

import co.com.academica.model.TblMatricula;
import co.com.academica.model.TblMatricula;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblMatriculaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int countByExample();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int deleteByExample(TblMatricula example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer nummatricula);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int insert(TblMatricula record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int insertSelective(TblMatricula record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    List<TblMatricula> selectByExample(TblMatricula example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    TblMatricula selectByPrimaryKey(Integer nummatricula);

    
    List<TblMatricula> getMatriculas(TblMatricula example);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    
    List<TblMatricula> validaMatricula(TblMatricula example);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblMatricula record, @Param("example") TblMatricula example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblMatricula record, @Param("example") TblMatricula example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblMatricula record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_matricula
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblMatricula record);
}