package co.com.academica.utils;

/*
 * Clase que define los datos que todo servicio devuelve como respuesta a una petición.
 */
public class ResultadoJson {

    //estado de la peticion, "OK" cuando la petición fue exitosa o "ERROR" cuando fue fallida.
    String Result = "";
    //registros generados en el servicio.
    Object[] Records = null;
//registros generados en el servicio.    
    Object[] Options = null;
    //registro generado en el servicio.
    Object Record = null;
    //total de registros generados.
    Integer TotalRecordCount = 0;
    //mensaje de error cuando la variable Result = "ERROR".
    String Message = "";
    //El stackTrace del Error
    String Error = "";

    public ResultadoJson() {
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        this.Result = result;
    }

    public Object[] getRecords() {
        return Records;
    }
    //public void setRecord (Object record) {

    public Object getRecord() {
        return Record;
    }

    public void setRecord(Object record) {
        this.setTotalRecordCount(1);
        this.Record = record;
    }

    //Object[] records = new Object[1];
    //records[0] = record;
    //setRecords(records);
    //}
    public void setRecords(Object[] records) {
        //this.setTotalRecordCount(records.length);
        this.Records = records;
    }

    public Object[] getOptions() {
        return Options;
    }

    public void setOptions(Object[] Options) {
        this.Options = Options;
    }

    public Integer getTotalRecordCount() {
        return TotalRecordCount;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        TotalRecordCount = totalRecordCount;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String error) {
        Message = error;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
