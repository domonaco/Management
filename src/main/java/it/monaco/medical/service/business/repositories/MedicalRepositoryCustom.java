package it.monaco.medical.service.business.repositories;

import it.monaco.medical.service.controllers.dtos.ManualEntryVehicleReqDTO;
import it.monaco.medical.service.controllers.dtos.OmologationCodeDTO;
import it.monaco.medical.service.controllers.dtos.SetupDTO;
import it.monaco.medical.service.model.entities.QuattroRuoteItem;
import it.monaco.medical.service.model.enums.EnumTipiAlimentazione;
import it.monaco.medical.service.model.exceptions.LegacyException;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MedicalRepositoryCustom{

    List<QuattroRuoteItem> findVehicleMakers(Long matriculationYearMonth, Date referenceDate, String aniaOmologationCode) throws LegacyException;

    List<QuattroRuoteItem> getFuelTypes(String makeCode, String modelName, Long annoMeseImmatricolazione, Date refrenceDate, String aniaOmologationCode) throws LegacyException;

    List<QuattroRuoteItem> findVehicleModels(Long matriculationYearMonth, String makeCode, Date referenceDate, String aniaOmologationCode) throws LegacyException;

    QuattroRuoteItem findVehicleDataOptional(Long infocar, Long qtrDate, Date refereceDate, Long matriculation) throws Exception;

    List<SetupDTO> findSetups(String modelName, String makeCode, EnumTipiAlimentazione supplyCode, Date referenceDate, Long matriculation, String aniaOmologationCode) throws LegacyException;

    List<SetupDTO> findRenewalSetups(String modelName, String makeCode, EnumTipiAlimentazione supplyCode, String kw, String cv, Date referenceDate, Long matriculation, String aniaOmologationCode) throws LegacyException;

    List<OmologationCodeDTO> findInfoByOmologationCode(String omologationCode) throws LegacyException;

    List<QuattroRuoteItem> findAllVehicleMakers() throws LegacyException;

    List<QuattroRuoteItem> getVehicleModelsByMakerCode (String makerCode, Boolean isInsertCall) throws LegacyException;

    List<QuattroRuoteItem> getVehicle(ManualEntryVehicleReqDTO manualEntryVehicleReqDTO) throws LegacyException;

    String getNewVehicleId();
}
