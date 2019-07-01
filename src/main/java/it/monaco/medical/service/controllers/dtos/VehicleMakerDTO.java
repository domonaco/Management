package it.monaco.medical.service.controllers.dtos;

import java.io.Serializable;

public class VehicleMakerDTO implements Serializable {

    private static final long serialVersionUID = 6108525113873785933L;

    private String code;

    private String description;

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
