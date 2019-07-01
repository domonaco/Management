package it.monaco.medical.service.controllers.dtos;

import java.io.Serializable;

public class ManualEntryVehicleReqDTO implements Serializable {

    private String vehicleMakers;
    private String vehicleModels;
    private String supplyCode;
    private String kw;
    private String taxableHorsePower;
    private Long firstMatriculationDate;

    public String getVehicleMakers() {

        return vehicleMakers;
    }

    public void setVehicleMakers(String vehicleMakers) {

        this.vehicleMakers = vehicleMakers;
    }

    public String getVehicleModels() {

        return vehicleModels;
    }

    public void setVehicleModels(String vehicleModels) {

        this.vehicleModels = vehicleModels;
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

    public Long getFirstMatriculationDate() {

        return firstMatriculationDate;
    }

    public void setFirstMatriculationDate(String firstMatriculationDate) {

        try{
            if(firstMatriculationDate != null){
                firstMatriculationDate = firstMatriculationDate.replace("/", "");
                String toSet = firstMatriculationDate.substring(2) + firstMatriculationDate.substring(0,2);
                this.firstMatriculationDate = new Long(toSet);
            }
        }catch (Exception e){
            this.firstMatriculationDate = null;
        }

    }
}
