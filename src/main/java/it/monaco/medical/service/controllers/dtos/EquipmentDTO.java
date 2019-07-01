package it.monaco.medical.service.controllers.dtos;

import it.monaco.medical.service.model.enums.ResponseType;

import java.io.Serializable;

import java.io.Serializable;

public class EquipmentDTO  implements Serializable{
    public EquipmentDTO(){
    }

    private String code;

    private String descriptiopn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescriptiopn() {
        return descriptiopn;
    }

    public void setDescriptiopn(String descriptiopn) {
        this.descriptiopn = descriptiopn;
    }
}

