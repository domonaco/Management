package it.monaco.medical.service.services;

import it.monaco.medical.service.business.repositories.QuattroRuoteRepository;
import it.monaco.medical.service.controllers.dtos.*;
import it.monaco.medical.service.model.entities.OptionalData;
import it.monaco.medical.service.model.entities.QuattroRuoteItem;
import it.monaco.medical.service.model.enums.EnumDisponibilitaOptional;
import it.monaco.medical.service.model.enums.EnumTipiAlimentazione;
import it.monaco.medical.service.model.enums.ErrorCode;
import it.monaco.medical.service.model.enums.ErrorType;
import it.monaco.medical.service.model.exceptions.LegacyException;
import it.monaco.medical.service.utils.LegacyDateUtils;
import it.monaco.medical.service.utils.VehicleUtils;
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
public class QuattroRuoteService{

    public static Logger logger = LoggerFactory.getLogger(QuattroRuoteService.class);

    @Autowired
    QuattroRuoteRepository quattroRuoteRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<VehicleMakerDTO> getVehicleMakers(Long matriculation, String referenceDate, String aniaOmologationCode)
            throws LegacyException, Exception {

        List<VehicleMakerDTO> vehicleMakerDTOS = new ArrayList<>();
        List<QuattroRuoteItem> quattroRuoteItemList;

        try {
            quattroRuoteItemList = quattroRuoteRepository.findVehicleMakers(matriculation, LegacyDateUtils
                    .toDate(referenceDate, LegacyDateUtils.PATTERN_FROM), aniaOmologationCode);

        } catch (Exception ex) {
            logger.error("Error retrieving data from DB : ", ex);
            throw new LegacyException("Error retrieving data from DB ", ErrorType.DATABASE,
                                      ErrorCode.QTR_004_VEHICLE_MAKERS_DATABASE_ERROR);
        }

        if (CollectionUtils.isEmpty(quattroRuoteItemList))
            throw new LegacyException("No vehicle maker found.", ErrorType.DATABASE,
                                      ErrorCode.QTR_003_VEHICLE_MAKERS_NOT_FOUND);


        quattroRuoteItemList.forEach((i) -> {
            vehicleMakerDTOS.add(entity2vehiclemaker(i));
        });

        return vehicleMakerDTOS;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<FuelTypeDTO> getFuelTypes(String makeCode, String modelName, Long annoMeseImmatricolazione,
                                          Date refrenceDate, String aniaOmologationCode) throws LegacyException {

        List<FuelTypeDTO> fuelTypeDTOS = new ArrayList<FuelTypeDTO>();
        List<QuattroRuoteItem> quattroRuoteItemList = null;

        try {
            quattroRuoteItemList = quattroRuoteRepository
                    .getFuelTypes(makeCode, modelName, annoMeseImmatricolazione, refrenceDate, aniaOmologationCode);
        } catch (Exception ex) {
            logger.error("Error retrieving fuel types from database: ", ex);
            throw new LegacyException("", ErrorType.DATABASE, ErrorCode.QTR_008_VEHICLE_SUPPLIES_DATABASE_ERROR);
        }

        if (CollectionUtils.isEmpty(quattroRuoteItemList))
            throw new LegacyException("No fuel type found for inputs.", ErrorType.DATABASE,
                                      ErrorCode.QTR_007_VEHICLE_SUPPLIES_NOT_FOUND);


        quattroRuoteItemList.forEach((i) -> {
            fuelTypeDTOS.add(entity2fueltype(i));
        });
        return fuelTypeDTOS;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<VehicleModelDTO> getVehicleModels(Long matriculationYearMonth, String makeCode, Date referenceDate,
                                                  String aniaOmologationCode) throws LegacyException {

        List<VehicleModelDTO> vehicleModelsDTOList = new ArrayList<VehicleModelDTO>();
        List<QuattroRuoteItem> quattroRuoteItemList = null;

        quattroRuoteItemList = quattroRuoteRepository
                .findVehicleModels(matriculationYearMonth, makeCode, referenceDate, aniaOmologationCode);

        if (CollectionUtils.isEmpty(quattroRuoteItemList))
            throw new LegacyException("No vehicle model found for inputs.", ErrorType.DATABASE,
                                      ErrorCode.QTR_005_VEHICLE_MODEL_NOT_FOUND);


        quattroRuoteItemList.forEach((i) -> {
            vehicleModelsDTOList.add(entity2vehiclemodel(i,Boolean.TRUE));
        });

        return vehicleModelsDTOList;

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public VehicleDTO getVehicleDataOptional(Long infocar, Long qtrDate, Date refereceDate, Long matriculation)
            throws Exception {

        VehicleDTO vehicleDTO = null;
        QuattroRuoteItem quattroRuoteItem = null;

        try {
            quattroRuoteItem =
                    quattroRuoteRepository.findVehicleDataOptional(infocar, qtrDate, refereceDate, matriculation);
        } catch (Exception ex) {
            logger.error("Error retrieving vehicle optional data from database: ", ex);
            throw new LegacyException("Error retrieving vehcile optional data from database.", ErrorType.DATABASE,
                                      ErrorCode.QTR_010_VEHICLE_DATA_OPTIONAL_DATABASE_ERROR);
        }

        if (quattroRuoteItem == null)
            throw new LegacyException("No vehicle data optional for inputs.", ErrorType.DATABASE,
                                      ErrorCode.QTR_009_VEHICLE_DATA_OPTIONAL_NOT_FOUND);


        vehicleDTO = entity2vehicle(quattroRuoteItem, matriculation);


        return vehicleDTO;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<SetupDTO> getSetups(String modelName, String makeCode, EnumTipiAlimentazione supplyCode,
                                    Date referenceDate, Long matriculation, String aniaOmologationCode)
            throws LegacyException {

        List<SetupDTO> setups = null;

        try {
            setups = quattroRuoteRepository
                    .findSetups(modelName, makeCode, supplyCode, referenceDate, matriculation, aniaOmologationCode);
        } catch (Exception ex) {
            logger.error("Error retrieving vehicle setups from database: ", ex);
            throw new LegacyException("", ErrorType.DATABASE, ErrorCode.QTR_012_VEHICLE_SETUP_DATABASE_ERROR);
        }

        if (CollectionUtils.isEmpty(setups))
            throw new LegacyException("No vehicle setup found for inputs.", ErrorType.DATABASE,
                                      ErrorCode.QTR_011_VEHICLE_SETUP_NOT_FOUND);


        return setups;

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<SetupDTO> getRenewalSetups(String modelName, String makeCode, EnumTipiAlimentazione supplyCode,
                                           String kw, String cv, Date referenceDate, Long matriculation,
                                           String aniaOmologationCode) throws LegacyException {

        List<SetupDTO> setups = null;

        try {
            setups = quattroRuoteRepository
                    .findRenewalSetups(modelName, makeCode, supplyCode, kw, cv, referenceDate, matriculation,
                                       aniaOmologationCode);
        } catch (Exception ex) {
            logger.error("Error retrieving vehicle setups from database: ", ex);
            throw new LegacyException("", ErrorType.DATABASE, ErrorCode.QTR_012_VEHICLE_SETUP_DATABASE_ERROR);
        }

        if (CollectionUtils.isEmpty(setups))
            throw new LegacyException("No vehicle setup found for inputs.", ErrorType.DATABASE,
                                      ErrorCode.QTR_011_VEHICLE_SETUP_NOT_FOUND);


        return setups;

    }

    public VehicleMakerDTO entity2vehiclemaker(QuattroRuoteItem quattroRuoteItem) {

        VehicleMakerDTO vehicleMakerDTO = new VehicleMakerDTO();
        if (quattroRuoteItem != null) {
            vehicleMakerDTO.setCode(StringUtils.trim(quattroRuoteItem.getCodiceMarca()));
            vehicleMakerDTO.setDescription(StringUtils.trim(quattroRuoteItem.getDescrizioneMarca()));
        }

        return vehicleMakerDTO;

    }


    public VehicleModelDTO entity2vehiclemodel(QuattroRuoteItem quattroRuoteItem, Boolean isInsertCall) {

        VehicleModelDTO vehicleModelDTO = new VehicleModelDTO();

        if (quattroRuoteItem != null) {

            String codeToSave = isInsertCall ? quattroRuoteItem.getCodiceModello()
                                             : String.valueOf(quattroRuoteItem.getCodiceInfocar());

            vehicleModelDTO.setCode(codeToSave);
            vehicleModelDTO.setDescription(StringUtils.trim(quattroRuoteItem.getDescrizioneModello()));

        }

        return vehicleModelDTO;

    }

    private VehicleDTO entity2vehicle(QuattroRuoteItem quattroRuoteItem, Long matriculation) throws Exception {

        VehicleDTO vehicle = new VehicleDTO();

        vehicle.setType(quattroRuoteItem.getTipoVeicolo());
        vehicle.setMaker(StringUtils.trim(quattroRuoteItem.getDescrizioneMarca()));
        vehicle.setModel(StringUtils.trim(quattroRuoteItem.getDescrizioneModello()));
        vehicle.setInfoVehicleCode(String.valueOf(quattroRuoteItem.getCodiceInfocar()));
        try {
            vehicle.setInfoVehicleDate(LegacyDateUtils.toDate(String.valueOf(quattroRuoteItem.getDataInserimento()),
                                                              LegacyDateUtils.YYYYMM));
        } catch (Exception e) {
            logger.error("Impossible to parse date in pattern yyyyMM : {}. ", quattroRuoteItem.getDataInserimento());
        }
        vehicle.setAntiTheftType(quattroRuoteItem.getTipoAntifurtoDiSerie());
        vehicle.setVersion(StringUtils.trim(quattroRuoteItem.getDescrizioneAllestimento()));
        vehicle.setKw(quattroRuoteItem.getPotenzaKw());
        vehicle.setAlimentation(quattroRuoteItem.getAlimentazione());
        vehicle.setNumeroPosti(quattroRuoteItem.getNumeroPosti());
        vehicle.setCcCapacity(quattroRuoteItem.getCilindrata() != null ?
                              Integer.valueOf(StringUtils.trim(quattroRuoteItem.getCilindrata())) : null);
        vehicle.setMakerCode(quattroRuoteItem.getCodiceMarca());
        vehicle.setModelCode(quattroRuoteItem.getCodiceModello());
        vehicle.setRiskClass(quattroRuoteItem.getClasseRischio());
        vehicle.setWeight(Integer.valueOf(StringUtils.trim(quattroRuoteItem.getMassa())));
        vehicle.setTaxableHorsePower(Integer.valueOf(StringUtils.trim(quattroRuoteItem.getCavalliFiscali())));
        vehicle.setAirbagType(quattroRuoteItem.getTipoAirbag());
        vehicle.setBodyType(quattroRuoteItem.getTipoCarrozzeria());
        vehicle.setCatalogPrice(new Double(quattroRuoteItem.getPrezzoListinoEuro()));
        vehicle.setVehicleLenght(quattroRuoteItem.getVehicleLenght());
        vehicle.setVehicleWidth(quattroRuoteItem.getVehicleWidth());
        vehicle.setVehicleHeigth(quattroRuoteItem.getVehicleHeigth());
        vehicle.setKgTowableMass(quattroRuoteItem.getKgTowableMass());
        vehicle.setTransactionTypeCode(quattroRuoteItem.getTransactionTypeCode());
        vehicle.setTransactionTypeDescription(quattroRuoteItem.getTransactionTypeDescription());
        vehicle.setTransactionTypeInit(quattroRuoteItem.getTransactionTypeInit());
        vehicle.setGearBox(quattroRuoteItem.getGearBox());
        vehicle.setGearBoxDescription(quattroRuoteItem.getGearBoxDescription());
        vehicle.setMaxSpeed(quattroRuoteItem.getMaxSpeed());
        vehicle.setAcceleration0100(quattroRuoteItem.getAcceleration0100());
        vehicle.setVehicleTypeCode(quattroRuoteItem.getVehicleTypeCode());
        vehicle.setVehicleTypeFlag(quattroRuoteItem.getVehicleTypeFlag());
        vehicle.setVehicleTypeDesc(quattroRuoteItem.getVehicleTypeDesc());
        vehicle.setAntipollutionCode(quattroRuoteItem.getAntipollutionCode());
        vehicle.setAntipollutionDesc(quattroRuoteItem.getAntipollutionDesc());


        final boolean isNewVehicle = VehicleUtils.isNewVehicle(matriculation);

        if (quattroRuoteItem.getSingoloValoreUsato() != null) {
            vehicle.setCurrentCatalogPrice(new Double(quattroRuoteItem.getSingoloValoreUsato().getValoreEuro()));
            /*
             * Set valori optional nuovi/usati solo se il valore attuale del veicolo è maggiore di 0
             */
            vehicle.setOptionals(wrapOptionals(quattroRuoteItem, isNewVehicle));
        }
        if (isNewVehicle) {
            vehicle.setOptionals(wrapOptionals(quattroRuoteItem, isNewVehicle));
        }

        return vehicle;
    }


    private List<VehicleOptionalDTO> wrapOptionals(QuattroRuoteItem quattroRuoteItem, Boolean isNewVehicle) {

        ArrayList<VehicleOptionalDTO> optionals = new ArrayList<VehicleOptionalDTO>();

        List<OptionalData> optionals4R = quattroRuoteItem.getOptionals();

        if (optionals4R != null) {

            for (OptionalData optional4R : optionals4R) {
                /*
                 * Aggiungo alla lista solo gli optional selezionabili (quindi escludiamo quelli di serie e quelli non disponibili)
                 */
                if (EnumDisponibilitaOptional.OPTIONAL.equals(optional4R.getDisponibilita())) {
                    VehicleOptionalDTO vehicleOptional = new VehicleOptionalDTO();
                    vehicleOptional.setCode(optional4R.getDesc().getCodiceNumerico());
                    vehicleOptional.setDescription(optional4R.getDesc().getDescrizione());
                    vehicleOptional.setStandardOptional(isStandardOptional(optional4R));


                    if (isNewVehicle) {
                        /*
                         * Valore optional veicolo nuovo
                         */
                        vehicleOptional.setValue(Double.valueOf(optional4R.getValore()));

                    } else if (quattroRuoteItem.getSingoloValoreUsato() != null) {
                        /*
                         * Se valore usato dell'optional se � presente un valore
                         * usato e nuovo dell'auto
                         */
                        vehicleOptional.setValue(VehicleUtils.calculateUsedOptionalValue(
                                quattroRuoteItem.getSingoloValoreUsato().getValoreEuro(),
                                quattroRuoteItem.getPrezzoListinoEuro(), optional4R.getValore()));

                    } else {
                        /*
                         * Il veicolo non � nuovo e non ha un valore usato per cui
                         * il valore assicurabile e' 0. Impostiamo anche il valore
                         * optional a 0.
                         */
                        vehicleOptional.setValue(Double.valueOf(optional4R.getValore()));

                    }

                    optionals.add(vehicleOptional);
                }
            }

        }

        return optionals;

    }

    private Boolean isStandardOptional(OptionalData optionals4R) {

        if (optionals4R != null && StringUtils.isNotBlank(optionals4R.getDisponibilitaString()) &&
            "S".equals((StringUtils.trim(optionals4R.getDisponibilitaString()))))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }


    private FuelTypeDTO entity2fueltype(QuattroRuoteItem quattroRuoteItem) {

        FuelTypeDTO fuelTypeDTO = null;
        if (quattroRuoteItem != null && quattroRuoteItem.getAlimentazione() != null) {
            for (EnumTipiAlimentazione tipoAlimentazione : EnumTipiAlimentazione.values()) {
                if (tipoAlimentazione.getDbValue().equals(quattroRuoteItem.getAlimentazioneString())) {
                    fuelTypeDTO = new FuelTypeDTO();
                    fuelTypeDTO.setCode(tipoAlimentazione.getTeValue());
                    fuelTypeDTO.setDescription(quattroRuoteItem.getAlimentazione().name());
                }
            }
        }

        if (fuelTypeDTO == null)
            throw new LegacyException("Unrecognied fuel type for db value : " + quattroRuoteItem.getAlimentazione(),
                                      ErrorType.UNEXPECTED, ErrorCode.LEG_999_UNEXPECTED_ERROR);

        return fuelTypeDTO;

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<VehicleDTO> getOptionalByOmologationCode(String omologationCode, Long matriculation) {

        List<VehicleDTO> vehicleList = new ArrayList<>();

        try {
            List<OmologationCodeDTO> omologationCodeList =
                    quattroRuoteRepository.findInfoByOmologationCode(omologationCode);
            if (omologationCodeList == null) {
                throw new LegacyException("No vehicle data optional for inputs.", ErrorType.DATABASE,
                                          ErrorCode.QTR_009_VEHICLE_DATA_OPTIONAL_NOT_FOUND);
            }
            for (OmologationCodeDTO codiceOmologazione : omologationCodeList) {
                vehicleList.add(getVehicleDataOptional(codiceOmologazione.getInfocar(),
                                                       codiceOmologazione.getQuattroRuoteDate(), new Date(),
                                                       matriculation));
            }

        } catch (Exception ex) {
            logger.error("Error retrieving vehicle optional data from database: " + ex.getMessage(), ex);
            throw new LegacyException("Error retrieving vehicle optional data from database.", ErrorType.DATABASE,
                                      ErrorCode.QTR_010_VEHICLE_DATA_OPTIONAL_DATABASE_ERROR);
        }

        return vehicleList;
    }

}
