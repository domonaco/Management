package it.monaco.medical.service.controllers.dtos;
import java.io.Serializable;


public class VehicleOptionalDTO implements Serializable {
    private String code;
    private String codeOld;
    private String description;
    private Double value;
    private Boolean standardOptional;
    private String availability;

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getStandardOptional() {
        return standardOptional;
    }

    public void setStandardOptional(Boolean standardOptional) {
        this.standardOptional = standardOptional;
    }

    public String getCodeOld() {
        return codeOld;
    }

    public void setCodeOld(String codeOld) {
        this.codeOld = codeOld;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}