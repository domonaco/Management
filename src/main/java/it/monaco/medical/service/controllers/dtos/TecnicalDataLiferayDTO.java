package it.monaco.medical.service.controllers.dtos;

import it.monaco.medical.service.model.enums.EnumTipiAirbag;
import it.monaco.medical.service.model.enums.EnumTipiCarrozzeria;

import java.io.Serializable;

public class TecnicalDataLiferayDTO implements Serializable {

    private String makerCode;

    private String modelCode;

    private Double catalogPrice;

    private Double currentCatalogPrice;

    private Integer taxableHorsePower;

    private Integer weight;

    private String riskClass;

    private EnumTipiCarrozzeria bodyType;

    private EnumTipiAirbag airbagType;

    public String getMakerCode() {
        return makerCode;
    }

    public void setMakerCode(String makerCode) {
        this.makerCode = makerCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Double getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(Double catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public Integer getTaxableHorsePower() {
        return taxableHorsePower;
    }

    public void setTaxableHorsePower(Integer taxableHorsePower) {
        this.taxableHorsePower = taxableHorsePower;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(String riskClass) {
        this.riskClass = riskClass;
    }

    public EnumTipiCarrozzeria getBodyType() {
        return bodyType;
    }

    public void setBodyType(EnumTipiCarrozzeria bodyType) {
        this.bodyType = bodyType;
    }

    public EnumTipiAirbag getAirbagType() {
        return airbagType;
    }

    public void setAirbagType(EnumTipiAirbag airbagType) {
        this.airbagType = airbagType;
    }

    public Double getCurrentCatalogPrice() {
        return currentCatalogPrice;
    }

    public void setCurrentCatalogPrice(Double currentCatalogPrice) {
        this.currentCatalogPrice = currentCatalogPrice;
    }
}
