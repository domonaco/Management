package it.monaco.medical.service.controllers.dtos;


import it.monaco.medical.service.model.enums.ResponseType;

import java.io.Serializable;

public class FuelTypeDTO implements Serializable {

    public FuelTypeDTO() {

    }

    public  String code;

    public String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
