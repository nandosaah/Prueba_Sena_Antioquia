/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.academica.utils;

import java.util.List;

/**
 *
 * @author oskr
 */
public class ResultadoInsertDatosVO {

    private List<OptionVO> objVo;
    private int resultadoInsert;

    public List<OptionVO> getObjVo() {
        return objVo;
    }

    public void setObjVo(List<OptionVO> objVo) {
        this.objVo = objVo;
    }

    public int getResultadoInsert() {
        return resultadoInsert;
    }

    public void setResultadoInsert(int resultadoInsert) {
        this.resultadoInsert = resultadoInsert;
    }
}
