package it.monaco.medical.service.business.repositories.hibernate;

import it.monaco.medical.service.business.repositories.QuattroRuoteRepositoryCustom;
import it.monaco.medical.service.controllers.dtos.*;
import it.monaco.medical.service.model.entities.OptionalData;
import it.monaco.medical.service.model.entities.QuattroRuoteItem;
import it.monaco.medical.service.model.entities.ValoreUsato;
import it.monaco.medical.service.model.enums.*;
import it.monaco.medical.service.model.exceptions.LegacyException;
import it.monaco.medical.service.utils.LegacyDateUtils;
import it.monaco.medical.service.utils.VehicleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuattroRuoteRepositoryImpl implements QuattroRuoteRepositoryCustom{


    public static Logger logger = LoggerFactory.getLogger(QuattroRuoteRepositoryImpl.class);

    @PersistenceContext
    EntityManager entityManager;


    private static final String CODICE_MARCA = "codiceMarca";
    private static final String ALIMENTAZIONE = "alimentazione";
    private static final String DESCRIZIONE_MODELLO = "descrizioneModello";
    private static final String CODICE_MODELLO = "codiceModello";
    private static final String DATA_INSERIMENTO = "dataInserimento";
    private static final String ANNO_MESE_IMMATRICOLAZIONE = "annoMeseImmatricolazione";
    private static final String DATA_RIFERIMENTO = "dataRiferimento";
    private static final String CODICE_INFOCAR = "codiceInfocar";
    private static final String CODICE_OMOLOGAZIONE = "codiceOmologazione";
    private static final String CILINDRATA = "cc";
    private static final String CAVALLI_FISCALI = "cv";
    private static final String POTENZA_KW = "kW";
    private static final String SYSDATEPRJCONFIG = "sysdatePrjConfig";
    private static final String DASH = "-";


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<QuattroRuoteItem> findVehicleMakers(Long annoMeseImmatricolazione, Date referenceDate,
                                                    String aniaOmologationCode) throws LegacyException {

        List<QuattroRuoteItem> quattroRuoteItemList = new ArrayList<QuattroRuoteItem>();

        try {
            StringBuilder sb = new StringBuilder(500).append(HQLCustomQueries.ELENCA_MARCHE_SELECT_FROM);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                sb.append(HQLCustomQueries.ANIA_JPQL_FROM);
            }

            sb.append(HQLCustomQueries.ELENCA_MARCHE_WHERE);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                sb.append(HQLCustomQueries.ANIA_JPQL_FILTER);
            }

            sb.append(HQLCustomQueries.ELENCA_MARCHE_ORDER);


            Query q = entityManager.createQuery(sb.toString());
            q.setParameter(DATA_RIFERIMENTO, referenceDate);
            q.setParameter(ANNO_MESE_IMMATRICOLAZIONE, annoMeseImmatricolazione);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                q.setParameter(CODICE_OMOLOGAZIONE, aniaOmologationCode);
            }


            List<Object[]> data = q.getResultList();

            for (Object[] row : data) {
                QuattroRuoteItem quattroRuoteItem = new QuattroRuoteItem();
                quattroRuoteItem.setCodiceMarca((String) row[0]);
                quattroRuoteItem.setDescrizioneMarca((String) row[1]);
                quattroRuoteItemList.add(quattroRuoteItem);
            }


        } catch (Exception ex) {

            throw new LegacyException(ex.getMessage(), ErrorType.DATABASE);
        }


        return quattroRuoteItemList;

    }

    @Override
    public List<QuattroRuoteItem> findAllVehicleMakers() throws LegacyException {

        List<QuattroRuoteItem> quattroRuoteItemList = new ArrayList<>();

        Query q = entityManager.createQuery(HQLCustomQueries.ELENCA_MARCHE_SELECT_FROM);


        List<Object[]> data = q.getResultList();

        for (Object[] row : data) {
            QuattroRuoteItem quattroRuoteItem = new QuattroRuoteItem();
            quattroRuoteItem.setCodiceMarca((String) row[0]);
            quattroRuoteItem.setDescrizioneMarca((String) row[1]);
            quattroRuoteItemList.add(quattroRuoteItem);
        }

        return quattroRuoteItemList;
    }

    @Override
    public List<QuattroRuoteItem> getVehicleModelsByMakerCode(String makerCode, Boolean isInsertCall)
            throws LegacyException {

        String makerCodeSplitted = "";

        if (isInsertCall) {
            makerCodeSplitted = makerCode.split("-")[0];
        }

        List<QuattroRuoteItem> quattroRuoteItemList = new ArrayList<>();

        String makerCodeToUse = isInsertCall ? makerCodeSplitted : makerCode;

        String queryString;

        try {

            if (isInsertCall) {
                queryString = HQLCustomQueries.GET_MODEL_DESCRIPTION_BY_MAKER_CODE;
            } else {
                queryString = HQLCustomQueries.GET_INFOCAR_CODE_AND_DESCRIPTION_BY_MAKER_CODE;
            }

            queryString += HQLCustomQueries.ELENCA_MODELLI_ORDER;

            Query q = entityManager.createQuery(queryString);
            q.setParameter(CODICE_MARCA, makerCodeToUse);

            if (isInsertCall) {
                List<Object> data = q.getResultList();
                for (Object row : data) {
                    QuattroRuoteItem quattroRuoteItem = new QuattroRuoteItem();
                    quattroRuoteItem.setCodiceModello((String) row);
                    quattroRuoteItem.setDescrizioneModello((String) row);
                    quattroRuoteItemList.add(quattroRuoteItem);
                }
            } else {
                List<Object[]> data = q.getResultList();
                for (Object[] row : data) {
                    QuattroRuoteItem quattroRuoteItem = new QuattroRuoteItem();
                    quattroRuoteItem.setCodiceInfocar((Long) row[0]);
                    quattroRuoteItem.setDescrizioneModello((String) row[1]);
                    quattroRuoteItemList.add(quattroRuoteItem);
                }
            }

        } catch (Exception ex) {

            logger.error("Unexpected error retrieving data from db : ", ex);
            throw new LegacyException(ex.getMessage(), ErrorType.DATABASE,
                                      ErrorCode.QTR_006_VEHICLE_MODEL_DATABASE_ERROR);
        }

        return quattroRuoteItemList;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<QuattroRuoteItem> getFuelTypes(String makeCode, String modelName, Long annoMeseImmatricolazione,
                                               Date referenceDate, String aniaOmologationCode) throws LegacyException {

        List<QuattroRuoteItem> quattroRuoteItemList = new ArrayList<QuattroRuoteItem>();

        try {
            StringBuilder sb = new StringBuilder(500).append(HQLCustomQueries.ELENCA_ALIMENTAZIONI_SELECT_FROM);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                sb.append(HQLCustomQueries.ANIA_JPQL_FROM);
            }

            sb.append(HQLCustomQueries.ELENCA_ALIMENTAZIONI_WHERE);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                sb.append(HQLCustomQueries.ANIA_JPQL_FILTER);
            }

            sb.append(HQLCustomQueries.ELENCA_ALIMENTAZIONI_ORDER);

            Query q = entityManager.createQuery(sb.toString());
            q.setParameter(DESCRIZIONE_MODELLO, StringUtils.trim(modelName) + '%');
            q.setParameter(CODICE_MARCA, makeCode);
            q.setParameter(DATA_RIFERIMENTO, referenceDate);
            q.setParameter(ANNO_MESE_IMMATRICOLAZIONE, annoMeseImmatricolazione);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                q.setParameter(CODICE_OMOLOGAZIONE, aniaOmologationCode);
            }


            List<Object> data = q.getResultList();
            for (Object row : data) {
                QuattroRuoteItem item = new QuattroRuoteItem();
                item.setAlimentazioneString((String) row);
                quattroRuoteItemList.add(item);
            }


        } catch (Exception ex) {
            logger.error("Error retrieving data from db: ", ex);
            throw new LegacyException(ex.getMessage(), ErrorType.DATABASE);
        }


        return quattroRuoteItemList;

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<QuattroRuoteItem> findVehicleModels(Long annoMeseImmatricolazione, String makeCode, Date referenceDate,
                                                    String aniaOmologationCode) throws LegacyException {

        List<QuattroRuoteItem> quattroRuoteItemList = new ArrayList<QuattroRuoteItem>();

        try {

            StringBuilder sb = new StringBuilder(500).append(HQLCustomQueries.ELENCA_MODELLI_SELECT_FROM);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                sb.append(HQLCustomQueries.ANIA_JPQL_FROM);
            }

            sb.append(HQLCustomQueries.ELENCA_MODELLI_WHERE);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                sb.append(HQLCustomQueries.ANIA_JPQL_FILTER);
            }

            sb.append(HQLCustomQueries.ELENCA_MODELLI_ORDER);


            Query q = entityManager.createQuery(sb.toString());
            q.setParameter(CODICE_MARCA, makeCode);
            q.setParameter(DATA_RIFERIMENTO, referenceDate);
            q.setParameter(ANNO_MESE_IMMATRICOLAZIONE, annoMeseImmatricolazione);

            if (StringUtils.isNotBlank(aniaOmologationCode)) {
                q.setParameter(CODICE_OMOLOGAZIONE, aniaOmologationCode);
            }

            List<Object[]> data = q.getResultList();

            for (Object[] row : data) {
                QuattroRuoteItem quattroRuoteItem = new QuattroRuoteItem();
                quattroRuoteItem.setCodiceModello((String) row[0]);
                quattroRuoteItem.setDescrizioneModello((String) row[1]);
                quattroRuoteItemList.add(quattroRuoteItem);
            }

        } catch (Exception ex) {

            logger.error("Unsexpected error retriving data from db : ", ex);
            throw new LegacyException(ex.getMessage(), ErrorType.DATABASE,
                                      ErrorCode.QTR_006_VEHICLE_MODEL_DATABASE_ERROR);
        }

        return quattroRuoteItemList;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)

    public QuattroRuoteItem findVehicleDataOptional(Long infocar, Long qtrDate, Date refereceDate, Long matriculation)
            throws Exception {

        StringBuilder sb = (new StringBuilder(500)).append(HQLCustomQueries.CARICA_ALLESTIMENTO_SELECT_FROM);
        sb.append(HQLCustomQueries.CARICA_ALLESTIMENTO_WHERE);
        Query q = this.entityManager.createNativeQuery(sb.toString(), QuattroRuoteItem.class);
        q.setParameter("codiceInfocar", infocar);
        q.setParameter("dataInserimento", qtrDate);
        q.setParameter("dataRiferimento", refereceDate);
        QuattroRuoteItem quattroRuoteItem = (QuattroRuoteItem) q.getSingleResult();

        Query qv;
        List valoriUsato;
        try {
            qv = this.entityManager.createNativeQuery(HQLCustomQueries.CARICA_OPTIONALS.toString(), OptionalData.class);
            qv.setParameter("codiceInfocar", quattroRuoteItem.getCodiceInfocar());
            qv.setParameter("dataInserimento", quattroRuoteItem.getDataInserimento());
            qv.setParameter("dataRiferimento", refereceDate);
            valoriUsato = qv.getResultList();
            quattroRuoteItem.setOptionals(valoriUsato);
        } catch (Exception var13) {
            logger.warn("caricaAllestimento(long, Date, long) - CARICA OPTIONALS - infocar=" + infocar +
                        ", referenceDate=" + refereceDate + ", matriculation=" + matriculation + ", e=" + var13);
        }

        try {
            qv = this.entityManager.createNativeQuery(HQLCustomQueries.CARICA_AIRBAG_ANTIFURTO.toString());
            qv.setParameter("codiceInfocar", quattroRuoteItem.getCodiceInfocar());
            qv.setParameter("dataInserimento", quattroRuoteItem.getDataInserimento());
            qv.setParameter("dataRiferimento", refereceDate);
            Object airbagAndAfData = qv.getSingleResult();
            quattroRuoteItem
                    .setTipoAirbag(EnumTipiAirbag.instanceForDbValue(String.valueOf(((Object[]) airbagAndAfData)[0])));
            quattroRuoteItem.setTipoAntifurtoDiSerie(
                    EnumTipiAntifurto.instanceForDbValue(String.valueOf(((Object[]) airbagAndAfData)[1])));
        } catch (NoResultException var11) {
            logger.info(
                    "caricaAllestimento - tipo AIRBAG/ANTIFURTO non trovato (infocar=" + infocar + ", referencedate=" +
                    refereceDate + ", matriculation=" + matriculation + ")");
            quattroRuoteItem.setTipoAirbag(EnumTipiAirbag.NESSUNO);
            quattroRuoteItem.setTipoAntifurtoDiSerie(EnumTipiAntifurto.NESSUNO);
        } catch (Exception var12) {
            logger.warn("caricaAllestimento(long, Date, long) - CARICA AIRBAG/ANTIFURTO - infocar=" + infocar +
                        ", referenceDate=" + refereceDate + ", matriculation=" + matriculation + ", e=" + var12);
            quattroRuoteItem.setTipoAirbag(EnumTipiAirbag.NESSUNO);
            quattroRuoteItem.setTipoAntifurtoDiSerie(EnumTipiAntifurto.NESSUNO);
        }

        try {
            qv = this.entityManager
                    .createNativeQuery(HQLCustomQueries.CARICA_VALORI_USATO.toString(), ValoreUsato.class);
            qv.setParameter("codiceInfocar", quattroRuoteItem.getCodiceInfocar());
            qv.setParameter("annoMeseImmatricolazione", matriculation);
            qv.setParameter("dataInserimento", quattroRuoteItem.getDataInserimento());
            qv.setParameter("dataRiferimento", refereceDate);
            valoriUsato = qv.getResultList();
            quattroRuoteItem.setValoriUsato(valoriUsato);
        } catch (Exception var10) {
            logger.warn("caricaAllestimento(long, Date, long) - CARICA VALORI USATO - codiceInfocar=" + infocar +
                        ", dataRiferimento=" + refereceDate + ", annoMeseImmatricolazione=" + matriculation + ", e=" +
                        var10);
        }

        return quattroRuoteItem;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<SetupDTO> findSetups(String modelName, String makeCode, EnumTipiAlimentazione supplyCode,
                                     Date referenceDate, Long matriculation, String aniaOmologationCode)
            throws LegacyException {

        StringBuilder sb = new StringBuilder(500).append(HQLCustomQueries.ELENCA_ALLESTIMENTI_SELECT_FROM);

        if (StringUtils.isNotBlank(aniaOmologationCode)) {
            sb.append(HQLCustomQueries.ANIA_SQL_FROM);
        }

        sb.append(HQLCustomQueries.ELENCA_ALLESTIMENTI_WHERE);

        if (StringUtils.isNotBlank(aniaOmologationCode)) {
            sb.append(HQLCustomQueries.ANIA_SQL_FILTER);
        }

        sb.append(HQLCustomQueries.ELENCA_ALLESTIMENTI_ORDER);
        Query q = entityManager.createNativeQuery(sb.toString(), QuattroRuoteItem.class);

        q.setParameter(CODICE_MARCA, makeCode);
        q.setParameter(DESCRIZIONE_MODELLO, modelName.trim() + '%');
        q.setParameter(ALIMENTAZIONE, supplyCode.getDbValue());
        q.setParameter(DATA_RIFERIMENTO, referenceDate);
        q.setParameter(ANNO_MESE_IMMATRICOLAZIONE, matriculation);

        if (StringUtils.isNotBlank(aniaOmologationCode)) {
            q.setParameter(CODICE_OMOLOGAZIONE, aniaOmologationCode);
        }

        List<SetupDTO> setupDTOS = new ArrayList<SetupDTO>();
        List<QuattroRuoteItem> data = q.getResultList();

        for (QuattroRuoteItem row : data) {
            SetupDTO setupDTO = new SetupDTO();
            setupDTO.setCode(row.getCodiceInfocar() + DASH + row.getDataInserimento());
            setupDTO.setDescription(row.getDescrizioneAllestimento() + "(" + row.getDataInizioProduzione() / 100 + ")");
            setupDTOS.add(setupDTO);

        }

        return setupDTOS;


    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<SetupDTO> findRenewalSetups(String modelName, String makeCode, EnumTipiAlimentazione supplyCode,
                                            String kw, String cv, Date referenceDate, Long matriculation,
                                            String aniaOmologationCode) throws LegacyException {

        StringBuilder sb = new StringBuilder(500).append(HQLCustomQueries.ELENCA_ALLESTIMENTI_SELECT_FROM);

        sb.append(HQLCustomQueries.ELENCA_ALLESTIMENTI_4RINNOVI_WHERE);
        sb.append(HQLCustomQueries.ELENCA_ALLESTIMENTI_ORDER);

        Query q = entityManager.createNativeQuery(sb.toString(), QuattroRuoteItem.class);

        q.setParameter(CODICE_MARCA, makeCode);
        q.setParameter(CODICE_MODELLO, modelName);
        q.setParameter(ALIMENTAZIONE, supplyCode.getDbValue());
        q.setParameter(DATA_RIFERIMENTO, referenceDate);
        q.setParameter(ANNO_MESE_IMMATRICOLAZIONE, matriculation);
        q.setParameter(CAVALLI_FISCALI, cv);
        q.setParameter(POTENZA_KW, kw);

        List<SetupDTO> setupDTOS = new ArrayList<SetupDTO>();
        List<QuattroRuoteItem> data = q.getResultList();

        for (QuattroRuoteItem row : data) {


            SetupDTO setupDTO = new SetupDTO();
            setupDTO.setCode(row.getCodiceInfocar() + DASH + row.getDataInserimento());
            setupDTO.setDescription(row.getDescrizioneAllestimento() + "(" + row.getDataInizioProduzione() / 100 + ")");
            setupDTOS.add(setupDTO);
            setupDTOS.add(setupDTO);
        }

        return setupDTOS;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<OmologationCodeDTO> findInfoByOmologationCode(String aniaOmologationCode) throws LegacyException {

        List<OmologationCodeDTO> omologationCodeDTOSList = new ArrayList<>();
        String queryString = HQLCustomQueries.GET_FROM_OMOLOGATION_CODE;

        Query q = entityManager.createNativeQuery(queryString);
        q.setParameter("codiceOmologazione", aniaOmologationCode);

        List<Object[]> data = q.getResultList();

        for (Object[] row : data) {
            OmologationCodeDTO omologationCodeDTO = new OmologationCodeDTO();

            omologationCodeDTO.setInfocar(((BigDecimal) row[0]).longValue());
            omologationCodeDTO.setQuattroRuoteDate(((BigDecimal) row[1]).longValue());
            omologationCodeDTOSList.add(omologationCodeDTO);
        }

        return omologationCodeDTOSList;

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
                    vehicleOptional.setCodeOld(optional4R.getCodice());
                    vehicleOptional.setDescription(optional4R.getDesc().getDescrizione());
                    vehicleOptional.setStandardOptional(isStandardOptional(optional4R));
                    vehicleOptional.setAvailability(optional4R.getDisponibilitaString());


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
            throw new LegacyException("Unrecognized fuel type for db value : " + quattroRuoteItem.getAlimentazione(),
                                      ErrorType.UNEXPECTED, ErrorCode.LEG_999_UNEXPECTED_ERROR);

        return fuelTypeDTO;

    }

    @Override
    public List<QuattroRuoteItem> getVehicle(ManualEntryVehicleReqDTO manualEntryVehicleReqDTO) throws LegacyException {

        StringBuilder sb = (new StringBuilder(500)).append(HQLCustomQueries.LOAD_VEHICLE);

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getVehicleModels())) {
            sb.append(HQLCustomQueries.AND_MODEL);
        }

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getSupplyCode())) {
            sb.append(HQLCustomQueries.AND_SUPPLY_CODE);
        }

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getKw())) {
            sb.append(HQLCustomQueries.AND_KW);
        }

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getTaxableHorsePower())) {
            sb.append(HQLCustomQueries.AND_TAXABLE_HORSE_POWER);
        }

        if (manualEntryVehicleReqDTO.getFirstMatriculationDate() != null &&
            !manualEntryVehicleReqDTO.getFirstMatriculationDate().equals(" ") &&
            !manualEntryVehicleReqDTO.getFirstMatriculationDate().equals("")) {
            sb.append(HQLCustomQueries.AND_FIRST_MATRICULATION_DATE);
        }

        Query q = entityManager.createQuery(sb.toString(), QuattroRuoteItem.class)
                               .setParameter("vehicleMakers", manualEntryVehicleReqDTO.getVehicleMakers());

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getVehicleModels())) {
            q.setParameter("vehicleModels", Long.valueOf(manualEntryVehicleReqDTO.getVehicleModels()));
        }

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getSupplyCode())) {
            q.setParameter("supplyCode", manualEntryVehicleReqDTO.getSupplyCode());
        }

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getKw())) {
            q.setParameter("kw", manualEntryVehicleReqDTO.getKw());
        }

        if (StringUtils.isNotBlank(manualEntryVehicleReqDTO.getTaxableHorsePower())) {
            q.setParameter("taxableHorsePower", manualEntryVehicleReqDTO.getTaxableHorsePower());
        }

        if (manualEntryVehicleReqDTO.getFirstMatriculationDate() != null &&
            !manualEntryVehicleReqDTO.getFirstMatriculationDate().equals(" ") &&
            !manualEntryVehicleReqDTO.getFirstMatriculationDate().equals("")) {
            q.setParameter("firstMatriculationDate", manualEntryVehicleReqDTO.getFirstMatriculationDate());
        }

        List<QuattroRuoteItem> vehicleList = q.getResultList();

        return vehicleList;

    }

    @Override
    public String getNewVehicleId() {

        StringBuilder sb = (new StringBuilder(500)).append(HQLCustomQueries.GET_NEW_VEHICLE_ID);

        Query query = entityManager.createNativeQuery(sb.toString());

        return String.valueOf(query.getSingleResult());

    }

}
