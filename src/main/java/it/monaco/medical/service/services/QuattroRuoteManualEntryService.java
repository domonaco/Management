package it.monaco.medical.service.services;

import it.monaco.medical.service.business.repositories.QuattroRuoteRepository;
import it.monaco.medical.service.configuration.Constants;
import it.monaco.medical.service.controllers.dtos.*;
import it.monaco.medical.service.model.entities.QuattroRuoteId;
import it.monaco.medical.service.model.entities.QuattroRuoteItem;
import it.monaco.medical.service.model.entities.ValoreUsato;
import it.monaco.medical.service.model.enums.EnumTipiAntifurto;
import it.monaco.medical.service.model.enums.ErrorCode;
import it.monaco.medical.service.model.enums.ErrorType;
import it.monaco.medical.service.model.exceptions.LegacyException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuattroRuoteManualEntryService{

    public static Logger logger = LoggerFactory.getLogger(it.verti.legacy.local.integration.quattroruote.service.manager.services.QuattroRuoteService.class);

    @Autowired
    QuattroRuoteRepository quattroRuoteRepository;

    @Autowired
    QuattroRuoteService quattroRuoteService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.VehicleMakerDTO> getAllVehicleMakers() {

        List<VehicleMakerDTO> vehicleMakerDTOS = new ArrayList<>();
        List<QuattroRuoteItem> quattroRuoteItemList;

        try {
            quattroRuoteItemList = quattroRuoteRepository.findAllVehicleMakers();

        } catch (Exception ex) {
            logger.error("Error retrieving data from DB : ", ex);
            throw new LegacyException("Error retrieving data from DB ", ErrorType.DATABASE,
                                      ErrorCode.QTR_004_VEHICLE_MAKERS_DATABASE_ERROR);
        }

        if (CollectionUtils.isEmpty(quattroRuoteItemList))
            throw new LegacyException("No vehicle maker found.", ErrorType.DATABASE,
                                      ErrorCode.QTR_003_VEHICLE_MAKERS_NOT_FOUND);


        quattroRuoteItemList.forEach((i) -> {
            vehicleMakerDTOS.add(quattroRuoteService.entity2vehiclemaker(i));
        });

        return vehicleMakerDTOS;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.VehicleModelDTO> getVehicleModelsByMakerCode(String makeCode, Boolean isInsertCall)
            throws LegacyException {

        List<it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.VehicleModelDTO> vehicleModelsDTOList = new ArrayList<>();
        List<QuattroRuoteItem> quattroRuoteItemList;

        quattroRuoteItemList = quattroRuoteRepository.getVehicleModelsByMakerCode(makeCode, isInsertCall);

        if (CollectionUtils.isEmpty(quattroRuoteItemList)) {
            throw new LegacyException("No vehicle model found for inputs.", ErrorType.DATABASE,
                                      ErrorCode.QTR_005_VEHICLE_MODEL_NOT_FOUND);
        }

        quattroRuoteItemList.forEach((i) -> vehicleModelsDTOList.add(quattroRuoteService.entity2vehiclemodel(i,isInsertCall)));

        return vehicleModelsDTOList;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryVehicleResDTO> getManualEntryVehicles(it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryVehicleReqDTO manualEntryVehicleReqDTO) {

        List<it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryVehicleResDTO> manualEntryVehicleResDTOList = new ArrayList<>();
        List<QuattroRuoteItem> quattroRuoteItemList;

        try {
            quattroRuoteItemList = quattroRuoteRepository.getVehicle(manualEntryVehicleReqDTO);
        } catch (Exception ex) {
            logger.error("Error retrieving data from DB : ", ex);
            throw new LegacyException("Error retrieving data from DB ", ErrorType.DATABASE,
                                      ErrorCode.QTR_014_VEHICLE_DATABASE_ERROR);
        }

        if (CollectionUtils.isNotEmpty(quattroRuoteItemList)) {
            quattroRuoteItemList.forEach((i) -> {
                manualEntryVehicleResDTOList.add(entity2vehicle(i));
            });
        }

        return manualEntryVehicleResDTOList;
    }

    private it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryVehicleResDTO entity2vehicle(QuattroRuoteItem quattroRuoteItem) {

        it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryVehicleResDTO manualEntryVehicleResDTO = new it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryVehicleResDTO();

        manualEntryVehicleResDTO.setInfocarCode(quattroRuoteItem.getCodiceInfocar());
        manualEntryVehicleResDTO.setFirstMatriculationDate(quattroRuoteItem.getDataInizioProduzione());
        manualEntryVehicleResDTO.setVehicleMaker(quattroRuoteItem.getDescrizioneMarca());
        manualEntryVehicleResDTO.setVehicleModel(quattroRuoteItem.getDescrizioneModello());
        manualEntryVehicleResDTO.setVehicleVersion(quattroRuoteItem.getDescrizioneAllestimento());
        manualEntryVehicleResDTO.setTipoCarb(quattroRuoteItem.getAlimentazioneString());
        manualEntryVehicleResDTO.setTaxableHorsePower(quattroRuoteItem.getCavalliFiscali());
        manualEntryVehicleResDTO.setSupplyCode(quattroRuoteItem.getCilindrata());
        manualEntryVehicleResDTO.setKw(quattroRuoteItem.getPotenzaKw());
        manualEntryVehicleResDTO.setCarType(quattroRuoteItem.getTipoCarrozzeriaString());
        manualEntryVehicleResDTO.setDataDiInizioValidita(quattroRuoteItem.getInizioValidita());
        manualEntryVehicleResDTO.setDataDiFineValidita(quattroRuoteItem.getFineValidita());

        return manualEntryVehicleResDTO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public void insertNewVehicle(it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryNewVehicleDTO manualEntryNewVehicleDTO) {

        QuattroRuoteItem quattroRuoteItem = null;

        try {
            quattroRuoteItem = createQuattroRuoteObject(manualEntryNewVehicleDTO);

            logger.info("Saving new vehicle on DB...");

            quattroRuoteRepository.saveAndFlush(quattroRuoteItem);

        } catch (Exception ex) {
            logger.error("Error inserting new vehicle: " + ex.getMessage(), ex);
            throw new LegacyException("Error inserting new vehicle ", ErrorType.DATABASE,
                                      ErrorCode.QTR_014_VEHICLE_DATABASE_ERROR);
        }

    }

    private QuattroRuoteItem createQuattroRuoteObject(it.verti.legacy.local.integration.quattroruote.service.manager.controllers.dtos.ManualEntryNewVehicleDTO manualEntryNewVehicleDTO) {

        QuattroRuoteItem quattroRuoteItem = new QuattroRuoteItem();
        QuattroRuoteId quattroRuoteId = new QuattroRuoteId();
        quattroRuoteId.setCodiceInfocar(Long.valueOf(manualEntryNewVehicleDTO.getInfocarCode()));
        quattroRuoteId.setDataInserimento(manualEntryNewVehicleDTO.getTrimDate());
        quattroRuoteId.setFineValidita(new Date());
        quattroRuoteId.setInizioValidita(new Date());


        ValoreUsato valoreUsato = new ValoreUsato();
        List<ValoreUsato> valoreUsatoList = new ArrayList<>();

        quattroRuoteItem.setId(quattroRuoteId);
        quattroRuoteItem.setCodiceInfocar(Long.valueOf(manualEntryNewVehicleDTO.getInfocarCode()));
        quattroRuoteItem.setDataInserimento(manualEntryNewVehicleDTO.getTrimDate());
        quattroRuoteItem.setDataInizioProduzione(manualEntryNewVehicleDTO.getFirstMatriculationDate());
        quattroRuoteItem.setDescrizioneMarca(StringUtils.isNotBlank(manualEntryNewVehicleDTO.getVehicleNewMakers())
                                                                    ? manualEntryNewVehicleDTO.getVehicleNewMakers()
                                                                    : manualEntryNewVehicleDTO.getVehicleMakers());
        quattroRuoteItem.setDescrizioneModello(StringUtils.isNotBlank(manualEntryNewVehicleDTO.getVehicleNewModels())
                                                                    ? manualEntryNewVehicleDTO.getVehicleNewModels()
                                                                    : manualEntryNewVehicleDTO.getVehicleModels());
        quattroRuoteItem.setDescrizioneAllestimento(manualEntryNewVehicleDTO.getVehicleVersion());
        quattroRuoteItem.setTipoCarrozzeriaString(manualEntryNewVehicleDTO.getCarType());
        quattroRuoteItem.setAlimentazioneString(manualEntryNewVehicleDTO.getTipoCarb());
        quattroRuoteItem.setAntipollutionCode(manualEntryNewVehicleDTO.getClasseInq());
        quattroRuoteItem.setMaxSpeed(manualEntryNewVehicleDTO.getMaxSpeed());
        quattroRuoteItem.setCilindrata(manualEntryNewVehicleDTO.getSupplyCode());
        quattroRuoteItem.setPotenzaKw(manualEntryNewVehicleDTO.getKw());
        quattroRuoteItem.setCavalliFiscali(manualEntryNewVehicleDTO.getTaxableHorsePower());
        quattroRuoteItem.setMassa(manualEntryNewVehicleDTO.getWeight());
        valoreUsato.setValoreEuro(manualEntryNewVehicleDTO.getVehicleValue());
        valoreUsatoList.add(valoreUsato);
        quattroRuoteItem.setValoriUsato(valoreUsatoList);
        quattroRuoteItem.setVehicleTypeFlag(manualEntryNewVehicleDTO.getIndicatore());
        quattroRuoteItem.setClasseRischio(manualEntryNewVehicleDTO.getVehicleIndicator());
        quattroRuoteItem.setIndicatoreInProduzione(manualEntryNewVehicleDTO.getIndicInProd());
        quattroRuoteItem
                .setTipoAntifurtoDiSerie(EnumTipiAntifurto.instanceForDbValue(manualEntryNewVehicleDTO.getAntifurto()));
        quattroRuoteItem.setVehicleWidth(manualEntryNewVehicleDTO.getWidth());
        quattroRuoteItem.setVehicleLenght(manualEntryNewVehicleDTO.getLenght());
        quattroRuoteItem.setEffectDte("20180705");
        quattroRuoteItem.setActivTo("99999999");
        quattroRuoteItem.setUserID("INFOCAR");

        return quattroRuoteItem;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public String getNewVehicleId() {

        String newVehicleId = null;

        try {
            newVehicleId = quattroRuoteRepository.getNewVehicleId();
        } catch (Exception ex) {
            logger.error("Error retrieving new vehicle Id from DB: " + ex.getMessage(), ex);
        }

        return Constants.MANUAL_ENTRY_INSERT_PREFIX.concat(newVehicleId);

    }


}
