package it.monaco.medical.service.controllers.dtos;



import it.monaco.medical.service.model.enums.*;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class VehicleDTO implements Serializable {

    public VehicleDTO() {

    }

    private String infoVehicleCode;

    private Date infoVehicleDate;

    private EnumTipiAntifurto antiTheftType;

    private String kw;

    private EnumTipiAlimentazione alimentation;

    private String maker;

    private String model;

    private Integer ccCapacity;

    private String version;

    private String numeroPosti;

    private EnumTipiVeicolo type;

    private String actualValue;

    private Date validUntil;

    private Date validFrom;

    private String makerCode;

    private String modelCode;

    private EnumUsiPra praUse;

    private Double catalogPrice;

    private Double currentCatalogPrice;

    private Integer taxableHorsePower;

    private Integer weight;

    private String riskClass;

    private EnumTipiCarrozzeria bodyType;

    private EnumTipiAirbag airbagType;

    private Integer vehicleLenght;

    private Integer vehicleWidth;

    private Integer vehicleHeigth;

    private Integer kgTowableMass;

    private String transactionTypeCode;

    private String transactionTypeDescription;

    private String transactionTypeInit;

    private String gearBox;

    private String gearBoxDescription;

    private String maxSpeed;

    private String acceleration0100;

    private String antipollutionCode;

    private String antipollutionDesc;

    private String vehicleTypeCode;

    private String vehicleTypeDesc;

    private String vehicleTypeFlag;

    private List<VehicleOptionalDTO> optionals;

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

    public EnumTipiAlimentazione getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(EnumTipiAlimentazione alimentation) {
        this.alimentation = alimentation;
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

    public EnumTipiAntifurto getAntiTheftType() {
        return antiTheftType;
    }

    public void setAntiTheftType(EnumTipiAntifurto antiTheftType) {
        this.antiTheftType = antiTheftType;
    }

    public List<VehicleOptionalDTO> getOptionals() {
        return optionals;
    }

    public void setOptionals(List<VehicleOptionalDTO> optionals) {
        this.optionals = optionals;
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

    public Double getCurrentCatalogPrice() {
        return currentCatalogPrice;
    }

    public void setCurrentCatalogPrice(Double currentCatalogPrice) {
        this.currentCatalogPrice = currentCatalogPrice;
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

    public Integer getVehicleLenght() {
        return vehicleLenght;
    }

    public void setVehicleLenght(Integer vehicleLenght) {
        this.vehicleLenght = vehicleLenght;
    }

    public Integer getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(Integer vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public Integer getVehicleHeigth() {
        return vehicleHeigth;
    }

    public void setVehicleHeigth(Integer vehicleHeigth) {
        this.vehicleHeigth = vehicleHeigth;
    }

    public Integer getKgTowableMass() {
        return kgTowableMass;
    }

    public void setKgTowableMass(Integer kgTowableMass) {
        this.kgTowableMass = kgTowableMass;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public String getTransactionTypeDescription() {
        return transactionTypeDescription;
    }

    public void setTransactionTypeDescription(String transactionTypeDescription) {
        this.transactionTypeDescription = transactionTypeDescription;
    }

    public String getTransactionTypeInit() {
        return transactionTypeInit;
    }

    public void setTransactionTypeInit(String transactionTypeInit) {
        this.transactionTypeInit = transactionTypeInit;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getGearBoxDescription() {
        return gearBoxDescription;
    }

    public void setGearBoxDescription(String gearBoxDescription) {
        this.gearBoxDescription = gearBoxDescription;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getAcceleration0100() {
        return acceleration0100;
    }

    public void setAcceleration0100(String acceleration0100) {
        this.acceleration0100 = acceleration0100;
    }

    public String getAntipollutionCode() {
        return antipollutionCode;
    }

    public void setAntipollutionCode(String antipollutionCode) {
        this.antipollutionCode = antipollutionCode;
    }

    public String getAntipollutionDesc() {
        return antipollutionDesc;
    }

    public void setAntipollutionDesc(String antipollutionDesc) {
        this.antipollutionDesc = antipollutionDesc;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeDesc() {
        return vehicleTypeDesc;
    }

    public void setVehicleTypeDesc(String vehicleTypeDesc) {
        this.vehicleTypeDesc = vehicleTypeDesc;
    }

    public String getVehicleTypeFlag() {
        return vehicleTypeFlag;
    }

    public void setVehicleTypeFlag(String vehicleTypeFlag) {
        this.vehicleTypeFlag = vehicleTypeFlag;
    }
}
