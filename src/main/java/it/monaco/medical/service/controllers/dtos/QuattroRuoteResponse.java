package it.monaco.medical.service.controllers.dtos;



import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuattroRuoteResponse implements Serializable {

    private HttpStatus status;

    private List<VehicleMakerDTO> makers;

    private List<VehicleModelDTO> models;

    private List<FuelTypeDTO> supplies;

    private VehicleDTO vehicle;

    private List<SetupDTO> setups;

    private List<ManualEntryVehicleResDTO> manualEntryVehicleList;

    private ErrorDTO error;

    private VehicleLiferayDTO value;

    private List<VehicleDTO> vehicleList;

    private String newVehicleId;

    public QuattroRuoteResponse(HttpStatus status) {

        this.status = status;
    }

    public List<VehicleMakerDTO> getMakers() {
        return makers;
    }

    public void setMakers(List<VehicleMakerDTO> makers) {
        this.makers = makers;
    }

    public List<FuelTypeDTO> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<FuelTypeDTO> supplies) {
        this.supplies = supplies;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public List<VehicleModelDTO> getModels() {
        return models;
    }

    public List<SetupDTO> getSetups() {
        return setups;
    }

    public void setSetups(List<SetupDTO> setups) {
        this.setups = setups;
    }

    public void setModels(List<VehicleModelDTO> models) {
        this.models = models;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }

    public List<ManualEntryVehicleResDTO> getManualEntryVehicleList() {

        return manualEntryVehicleList;
    }

    public void setManualEntryVehicleList(List<ManualEntryVehicleResDTO> manualEntryVehicleList) {

        this.manualEntryVehicleList = manualEntryVehicleList;
    }

    public VehicleLiferayDTO getValue() {

        return value;
    }

    public void setValue(VehicleLiferayDTO value) {

        this.value = value;
    }

    public List<VehicleDTO> getVehicleList() {

        return vehicleList;
    }

    public void setVehicleList(List<VehicleDTO> vehicleList) {

        this.vehicleList = vehicleList;
    }

    public String getNewVehicleId() {

        return newVehicleId;
    }

    public void setNewVehicleId(String newVehicleId) {

        this.newVehicleId = newVehicleId;
    }
}

