package co.com.academica.model;

import co.com.academica.model.TblEstudiantes;
import co.com.academica.model.TblEstudiantes;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblEstudiantesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int countByExample();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int deleteByExample(TblEstudiantes example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer numestudiante);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int insert(TblEstudiantes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int insertSelective(TblEstudiantes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    List<TblEstudiantes> selectByExample(TblEstudiantes example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
     List<TblEstudiantes> getEstudiantes();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    TblEstudiantes selectByPrimaryKey(Integer numestudiante);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblEstudiantes record, @Param("example") TblEstudiantes example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblEstudiantes record, @Param("example") TblEstudiantes example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblEstudiantes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_estudiantes
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblEstudiantes record);
}