package it.monaco.medical.service.controllers.dtos;

import java.io.Serializable;

public class ManualEntryNewVehicleDTO implements Serializable{

    private String infocarCode;
    private Long trimDate;
    private Long firstMatriculationDate;
    private String vehicleMakers;
    private String vehicleNewMakers;
    private String vehicleModels;
    private String vehicleNewModels;
    private String vehicleVersion;
    private String carType;
    private String tipoCarb;
    private String classeInq;
    private String maxSpeed;
    private String supplyCode;
    private String kw;
    private String taxableHorsePower;
    private String weight;
    private Float vehicleValue;
    private String indicatore;
    private String vehicleIndicator;
    private String indicInProd;
    private String antifurto;
    private Integer width;
    private Integer lenght;

    public String getInfocarCode() {

        return infocarCode;
    }

    public void setInfocarCode(String infocarCode) {

        this.infocarCode = infocarCode;
    }

    public Long getTrimDate() {

        return trimDate;
    }

    public void setTrimDate(Long trimDate) {

        this.trimDate = trimDate;
    }

    public Long getFirstMatriculationDate() {

        return firstMatriculationDate;
    }

    public void setFirstMatriculationDate(Long firstMatriculationDate) {

        this.firstMatriculationDate = firstMatriculationDate;
    }

    public String getVehicleVersion() {

        return vehicleVersion;
    }

    public void setVehicleVersion(String vehicleVersion) {

        this.vehicleVersion = vehicleVersion;
    }

    public String getCarType() {

        return carType;
    }

    public void setCarType(String carType) {

        this.carType = carType;
    }

    public String getTipoCarb() {

        return tipoCarb;
    }

    public void setTipoCarb(String tipoCarb) {

        this.tipoCarb = tipoCarb;
    }

    public String getClasseInq() {

        return classeInq;
    }

    public void setClasseInq(String classeInq) {

        this.classeInq = classeInq;
    }

    public String getMaxSpeed() {

        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {

        this.maxSpeed = maxSpeed;
    }

    public String getSupplyCode() {

        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {

        this.supplyCode = supplyCode;
    }

    public String getKw() {

        return kw;
    }

    public void setKw(String kw) {

        this.kw = kw;
    }

    public String getTaxableHorsePower() {

        return taxableHorsePower;
    }

    public void setTaxableHorsePower(String taxableHorsePower) {

        this.taxableHorsePower = taxableHorsePower;
    }

    public String getWeight() {

        return weight;
    }

    public void setWeight(String weight) {

        this.weight = weight;
    }

    public Float getVehicleValue() {

        return vehicleValue;
    }

    public void setVehicleValue(Float vehicleValue) {

        this.vehicleValue = vehicleValue;
    }

    public String getIndicatore() {

        return indicatore;
    }

    public void setIndicatore(String indicatore) {

        this.indicatore = indicatore;
    }

    public String getVehicleIndicator() {

        return vehicleIndicator;
    }

    public void setVehicleIndicator(String vehicleIndicator) {

        this.vehicleIndicator = vehicleIndicator;
    }

    public String getIndicInProd() {

        return indicInProd;
    }

    public void setIndicInProd(String indicInProd) {

        this.indicInProd = indicInProd;
    }

    public String getAntifurto() {

        return antifurto;
    }

    public void setAntifurto(String antifurto) {

        this.antifurto = antifurto;
    }

    public Integer getWidth() {

        return width;
    }

    public void setWidth(Integer width) {

        this.width = width;
    }

    public Integer getLenght() {

        return lenght;
    }

    public void setLenght(Integer lenght) {

        this.lenght = lenght;
    }

    public String getVehicleMakers() {
        return vehicleMakers;
    }

    public void setVehicleMakers(String vehicleMakers) {
        this.vehicleMakers = vehicleMakers;
    }

    public String getVehicleNewMakers() {
        return vehicleNewMakers;
    }

    public void setVehicleNewMakers(String vehicleNewMakers) {
        this.vehicleNewMakers = vehicleNewMakers;
    }

    public String getVehicleModels() {
        return vehicleModels;
    }

    public void setVehicleModels(String vehicleModels) {
        this.vehicleModels = vehicleModels;
    }

    public String getVehicleNewModels() {
        return vehicleNewModels;
    }

    public void setVehicleNewModels(String vehicleNewModels) {
        this.vehicleNewModels = vehicleNewModels;
    }
}
