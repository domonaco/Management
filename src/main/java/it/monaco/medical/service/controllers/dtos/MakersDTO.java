package it.monaco.medical.service.controllers.dtos;

import java.io.Serializable;

public class MakersDTO implements Serializable{

    public MakersDTO() {

    }

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