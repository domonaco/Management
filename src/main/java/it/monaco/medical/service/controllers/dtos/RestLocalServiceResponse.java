package it.monaco.medical.service.controllers.dtos;


import it.monaco.medical.service.model.enums.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Collection;

public class RestLocalServiceResponse  extends ResponseEntity<RestResponseDTO> {

    protected RestLocalServiceResponse(RestResponseDTO body) {

        super(body, HttpStatus.OK);
    }

    protected RestLocalServiceResponse (RestResponseDTO body, HttpStatus status) {

        super(body, status);
    }

    protected RestLocalServiceResponse(RestResponseDTO body, MultiValueMap<String, String> headers, HttpStatus staus) {

        super(body, headers, staus);

    }


    public static final RestLocalServiceResponse success()
    {
        return new RestLocalServiceResponse(new RestResponseDTO(ResponseType.SUCCESS), HttpStatus.OK);
    }

    public static final RestLocalServiceResponse info()
    {
        return new RestLocalServiceResponse(new RestResponseDTO(ResponseType.INFO), HttpStatus.OK);
    }


    public static final RestLocalServiceResponse warning()
    {
        return new RestLocalServiceResponse(new RestResponseDTO(ResponseType.WARNING), HttpStatus.OK);

    }


    public static final RestLocalServiceResponse error()
    {
        return new RestLocalServiceResponse(new RestResponseDTO(ResponseType.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public  RestLocalServiceResponse withAlert(String message)
    {
        ((RestResponseDTO)this.getBody()).withAlert(message);
        return this;
    }

    public  RestLocalServiceResponse withSingleton(Object data)
    {
        ((RestResponseDTO)this.getBody()).withSingleton(data);
        return this;
    }



    public  RestLocalServiceResponse withCollection(Collection<?> collection)
    {
        ((RestResponseDTO)this.getBody()).withCollection(collection);
        return this;
    }


}
