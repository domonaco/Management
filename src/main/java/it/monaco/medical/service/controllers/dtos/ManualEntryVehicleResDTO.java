package it.monaco.medical.service.controllers.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManualEntryVehicleResDTO implements Serializable{

    private Long infocarCode;
    private Long firstMatriculationDate;
    private String vehicleMaker;
    private String vehicleModel;
    private String vehicleVersion;
    private String tipoCarb;
    private String taxableHorsePower;
    private String supplyCode;
    private String kw;
    private String carType;
    private Date dataDiInizioValidita;
    private Date dataDiFineValidita;

    public Long getInfocarCode() {

        return infocarCode;
    }

    public void setInfocarCode(Long infocarCode) {

        this.infocarCode = infocarCode;
    }

    public String getFirstMatriculationDate() {

        try{
            String dateString = Long.toString(firstMatriculationDate);
            if(dateString.length() == 6){
                return dateString.substring(4) + "/" + dateString.substring(0,4);
            }else{
                return dateString;
            }
        }catch(Exception e){
            return null;
        }


    }

    public void setFirstMatriculationDate(Long firstMatriculationDate) {

        this.firstMatriculationDate = firstMatriculationDate;
    }

    public String getVehicleMaker() {

        return vehicleMaker;
    }

    public void setVehicleMaker(String vehicleMaker) {

        this.vehicleMaker = vehicleMaker;
    }

    public String getVehicleModel() {

        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {

        this.vehicleModel = vehicleModel;
    }

    public String getVehicleVersion() {

        return vehicleVersion;
    }

    public void setVehicleVersion(String vehicleVersion) {

        this.vehicleVersion = vehicleVersion;
    }

    public String getTipoCarb() {

        return tipoCarb;
    }

    public void setTipoCarb(String tipoCarb) {

        this.tipoCarb = tipoCarb;
    }

    public String getTaxableHorsePower() {

        return taxableHorsePower;
    }

    public void setTaxableHorsePower(String taxableHorsePower) {

        this.taxableHorsePower = taxableHorsePower;
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

    public String getCarType() {

        return carType;
    }

    public void setCarType(String carType) {

        this.carType = carType;
    }

    public String getDataDiInizioValidita() {
        DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        return dateFormat.format(dataDiInizioValidita);
    }

    public void setDataDiInizioValidita(Date dataDiInizioValidita) {
        this.dataDiInizioValidita = dataDiInizioValidita;
    }

    public String  getDataDiFineValidita() {
        DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        return dateFormat.format(dataDiFineValidita);

    }

    public void setDataDiFineValidita(Date dataDiFineValidita) {
        this.dataDiFineValidita = dataDiFineValidita;
    }

}
