package co.com.academica.service;

public interface Service {

    public Object add(Object data);

    public Object update(Object data);
        
    public Object delete(Object data);

    public Object list(Object data, Object startIndex, Object pageSize);

    public Object get(Object data);
}
