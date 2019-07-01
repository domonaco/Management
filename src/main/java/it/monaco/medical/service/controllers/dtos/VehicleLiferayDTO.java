package it.monaco.medical.service.controllers.dtos;

import it.monaco.medical.service.model.enums.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VehicleLiferayDTO implements Serializable {

    public VehicleLiferayDTO() {

    }

    private String infocarCode;

    private Date infocarDate;

    private EnumTipiAntifurto antitheftType;

    private String kw;

    private EnumTipiAlimentazione alimentation;

    private String maker;

    private String model;

    private Integer ccCapacity;

    private String version;

    private String numeroPosti;

    private TecnicalDataLiferayDTO technicalData;

    private List<VehicleOptionalDTO> optionals;

    public String getInfocarCode() {
        return infocarCode;
    }

    public void setInfocarCode(String infocarCode) {
        this.infocarCode = infocarCode;
    }

    public Date getInfocarDate() {
        return infocarDate;
    }

    public void setInfocarDate(Date infocarDate) {
        this.infocarDate = infocarDate;
    }

    public EnumTipiAntifurto getAntitheftType() {
        return antitheftType;
    }

    public void setAntitheftType(EnumTipiAntifurto antitheftType) {
        this.antitheftType = antitheftType;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public EnumTipiAlimentazione getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(EnumTipiAlimentazione alimentation) {
        this.alimentation = alimentation;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCcCapacity() {
        return ccCapacity;
    }

    public void setCcCapacity(Integer ccCapacity) {
        this.ccCapacity = ccCapacity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(String numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public TecnicalDataLiferayDTO getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(TecnicalDataLiferayDTO technicalData) {
        this.technicalData = technicalData;
    }

    public List<VehicleOptionalDTO> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<VehicleOptionalDTO> optionals) {
        this.optionals = optionals;
    }
}
