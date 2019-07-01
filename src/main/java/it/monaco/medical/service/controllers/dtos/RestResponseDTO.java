package it.monaco.medical.service.controllers.dtos;



import it.monaco.medical.service.model.enums.ResponseType;

import java.io.Serializable;
import java.util.Collection;

public class RestResponseDTO implements Serializable {

    private final ResponseType status;
    private AlertDTO alert;
    private Long totalCount;
    private Object data;



    public RestResponseDTO(ResponseType status)
    {
        this.status = status;
    }


    public RestResponseDTO withAlert(String message) {

        if (this.alert != null) {
            throw new UnsupportedOperationException("Alert already defined !");
        }
        else
        {
            this.alert = new AlertDTO(this.status, message);
            return this;
        }
    }


    public RestResponseDTO withSingleton(Object data){


        if(data == null || !(data instanceof Iterable) && !data.getClass().isArray()){
            this.data = data;
            return this;
        }
        else
        {
            throw  new UnsupportedOperationException("Please assign operation with different constructor");
        }

    }


    public <T> RestResponseDTO withCollection(T[] collection) {
        this.data = collection;
        if (collection == null) {
            this.totalCount = null;
        } else {
            this.totalCount = (long) collection.length;

        }
        return this;
    }

    public RestResponseDTO withCollection(Collection<?> collection)
    {
        this.data = collection;
        if (collection == null) {
            this.totalCount = null;
        } else {
            this.totalCount = (long) collection.size();

        }
        return this;
    }


}
