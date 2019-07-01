package it.monaco.medical.service.controllers.dtos;



import com.fasterxml.jackson.annotation.JsonInclude;
import it.monaco.medical.service.model.enums.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TechnicalDataDTO extends  RestResponseDTO implements Serializable {

    public TechnicalDataDTO(ResponseType status) {
        super(status);
    }

    private String infoVehicleCode;

    private Date infoVehicleDate;

    private String kw;

    private EnumTipiAlimentazione alimantion;

    private EnumTipiVeicolo type;

    private String maker;

    private String model;

    private String actualValue;

    private String ccCapacity;

    private String version;

    private Date validUntil;

    private Date validFrom;

    private String numeroPosti;

    private String makerCode;

    private String modelCode;

    private EnumUsiPra praUse;

    private Double catalogPrice;

    private Double currentCatalogPrioe;

    private Integer taxableHorsePower;

    private Integer weight;

    private String riskClass;

    private EnumTipiCarrozzeria bodyDescription;

    private EnumTipiAirbag airbagType;

    private List<TechnicalDataOptionalDTO> optionals;


    public String getInfoVehicleCode() {
        return infoVehicleCode;
    }

    public void setInfoVehicleCode(String infoVehicleCode) {
        this.infoVehicleCode = infoVehicleCode;
    }

    public Date getInfoVehicleDate() {
        return infoVehicleDate;
    }

    public void setInfoVehicleDate(Date infoVehicleDate) {
        this.infoVehicleDate = infoVehicleDate;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public EnumTipiAlimentazione getAlimantion() {
        return alimantion;
    }

    public void setAlimantion(EnumTipiAlimentazione alimantion) {
        this.alimantion = alimantion;
    }

    public EnumTipiVeicolo getType() {
        return type;
    }

    public void setType(EnumTipiVeicolo type) {
        this.type = type;
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

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getCcCapacity() {
        return ccCapacity;
    }

    public void setCcCapacity(String ccCapacity) {
        this.ccCapacity = ccCapacity;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public String getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(String numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

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

    public EnumUsiPra getPraUse() {
        return praUse;
    }

    public void setPraUse(EnumUsiPra praUse) {
        this.praUse = praUse;
    }

    public Double getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(Double catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public Double getCurrentCatalogPrioe() {
        return currentCatalogPrioe;
    }

    public void setCurrentCatalogPrioe(Double currentCatalogPrioe) {
        this.currentCatalogPrioe = currentCatalogPrioe;
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

    public EnumTipiCarrozzeria getBodyDescription() {
        return bodyDescription;
    }

    public void setBodyDescription(EnumTipiCarrozzeria bodyDescription) {
        this.bodyDescription = bodyDescription;
    }

    public EnumTipiAirbag getAirbagType() {
        return airbagType;
    }

    public void setAirbagType(EnumTipiAirbag airbagType) {
        this.airbagType = airbagType;
    }

    public List<TechnicalDataOptionalDTO> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<TechnicalDataOptionalDTO> optionals) {
        this.optionals = optionals;
    }
}
