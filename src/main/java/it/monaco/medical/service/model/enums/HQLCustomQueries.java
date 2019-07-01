package it.monaco.medical.service.model.enums;

public class HQLCustomQueries {


    // vehicle makers
    public static final String ELENCA_MARCHE_SELECT_FROM = "select distinct d4r.codiceMarca, d4r.descrizioneMarca " +
                                                           "from QuattroRuoteItem d4r order by d4r.descrizioneMarca";
    public static final StringBuilder ELENCA_MARCHE_WHERE = new StringBuilder()
            .append(" where to_number(d4r.codiceMarca) < 900000")
            .append(" and (trunc(:dataRiferimento) between d4r.inizioValidita and d4r.fineValidita)")
            .append(" and (d4r.dataInizioProduzione between :annoMeseImmatricolazione - 1000l and :annoMeseImmatricolazione)");

    public static final String ELENCA_MARCHE_ORDER = " order by d4r.descrizioneMarca";


    //vehicle supplies
    public static final String ELENCA_ALIMENTAZIONI_SELECT_FROM = "select distinct d4r.alimentazioneString from QuattroRuoteItem d4r";
    public static final StringBuilder ELENCA_ALIMENTAZIONI_WHERE = new StringBuilder()
            .append(" where d4r.codiceMarca = :codiceMarca")
            .append(" and d4r.descrizioneModello like :descrizioneModello and (trunc(:dataRiferimento) between d4r.inizioValidita and d4r.fineValidita)")
            .append(" and (d4r.dataInizioProduzione between :annoMeseImmatricolazione - 1000l and :annoMeseImmatricolazione)");
    public static final String ELENCA_ALIMENTAZIONI_ORDER = " order by d4r.alimentazioneString";




    //vehicle-technical-data
    public static final StringBuilder CARICA_ALLESTIMENTO_SELECT_FROM = new StringBuilder()
            .append("SELECT gv.*, ROW_NUMBER () OVER (PARTITION BY gv.mvvehcode ORDER BY gv.mvreg1 DESC)")
            .append(" FROM gen_veicolo gv");

    public static final StringBuilder GET_OPTIONAL_BY_OMOLOGATION_CODE = new StringBuilder()
            .append("SELECT gv.* ")
            .append(" FROM gen_veicolo gv ")
            .append(" WHERE ( gv.mvvehcode, gv.mvqrtdte ) IN ")
            .append("(SELECT g4o.mvvehcode, g4o.mvqrtdte ")
            .append("FROM gen_4r_omologazioni g4o ")
            .append("WHERE omocod = :omologationCode) ")
            .append("AND SYSDATE BETWEEN gv.data_d_inizio_validita ")
            .append("AND gv.data_d_fine_validita ");

    public static final StringBuilder CARICA_ALLESTIMENTO_WHERE = new StringBuilder()
            .append(" where rownum = 1 and gv.MVVEHCODE = :codiceInfocar")
            .append(" and (trunc(:dataRiferimento) between gv.data_d_inizio_validita and gv.data_d_fine_validita)")
            .append(" and gv.mvqrtdte = :dataInserimento");

    public static final StringBuilder CARICA_OPTIONALS = new StringBuilder()
            .append("SELECT *")
            .append(" FROM gen_optional_web v, gen_optional_web_desc t")
            .append(" WHERE v.optcod = t.optcod AND v.infocar = :codiceInfocar")
            .append(" AND v.regmonth = remainder(:dataInserimento, 100)")
            .append(" AND v.regyear = (:dataInserimento - remainder(:dataInserimento, 100))/100")
            .append(" AND TRUNC (:dataRiferimento) BETWEEN v.data_d_inizio_validita AND v.data_d_fine_validita");

    public static final StringBuilder CARICA_AIRBAG_ANTIFURTO = new StringBuilder()
            .append("SELECT DECODE (abagside,'D','3','G','3','S', '3',DECODE (abagp,'D', '2','G', '2','S', '2',DECODE (abagd, 'D', '1', 'G', '1', 'S', '1', '4'))) codice_airbag,DECODE (antifurto,'S', 'D         ','', 'D         ','D', 'D         ',DECODE (immob, 'S', 'A         ', '', 'A         ', 'D', 'A         ', 'N         ')) codice_antifurto")
            .append(" FROM gen_optional")
            .append(" WHERE infocar = :codiceInfocar AND regyear = (:dataInserimento - remainder(:dataInserimento, 100))/100")
            .append(" AND regmonth = remainder(:dataInserimento, 100) AND trunc(:dataRiferimento) BETWEEN data_d_inizio_validita AND data_d_fine_validita");

    public static final StringBuilder CARICA_VALORI_USATO = new StringBuilder()
            .append("SELECT valore.*")
            .append(" FROM gen_valori_usato valore")
            .append(" WHERE mvvehcode = :codiceInfocar AND (:annoMeseImmatricolazione BETWEEN activfrom AND activto)")
            .append(" AND mvqrtdte = :dataInserimento AND (trunc(:dataRiferimento) BETWEEN data_d_inizio_validita AND data_d_fine_validita)");


    // vehicle models
    public static final String ELENCA_MODELLI_SELECT_FROM = "select distinct d4r.codiceModello,d4r.descrizioneModello from QuattroRuoteItem d4r";

    public static final StringBuilder ELENCA_MODELLI_WHERE = new StringBuilder()
            .append(" where d4r.codiceMarca = :codiceMarca")
//								.append(" where d4r.alimentazioneString != 'E' and d4r.codiceMarca = :codiceMarca")
            .append(" and (trunc(:dataRiferimento) between d4r.inizioValidita and d4r.fineValidita)")
            .append(" and (d4r.dataInizioProduzione between :annoMeseImmatricolazione - 1000l and :annoMeseImmatricolazione)");

    public static final String ELENCA_MODELLI_ORDER = " order by d4r.descrizioneModello";

    // vehicle setups
    public static final String ELENCA_ALLESTIMENTI_SELECT_FROM = "select gv.* FROM gen_veicolo gv";

    public static final StringBuilder ELENCA_ALLESTIMENTI_WHERE = new StringBuilder()
            .append(" where gv.mvmodeld like :descrizioneModello and gv.mvfuel = :alimentazione")
            .append(" and gv.mvmake = :codiceMarca and (trunc(:dataRiferimento) between gv.data_d_inizio_validita and gv.data_d_fine_validita)")
            .append(" and (gv.mvreg1 between :annoMeseImmatricolazione - 1000 and :annoMeseImmatricolazione)");

    public static final StringBuilder ELENCA_ALLESTIMENTI_4RINNOVI_WHERE = new StringBuilder()
            .append(" where gv.mvmodel = :codiceModello and gv.mvfuel = :alimentazione")
            .append(" and gv.mvmake = :codiceMarca and (trunc(:dataRiferimento) between gv.data_d_inizio_validita and gv.data_d_fine_validita)")
            .append(" and (gv.mvreg1 between :annoMeseImmatricolazione - 1000 and :annoMeseImmatricolazione)")
            .append(" and to_number(gv.mvfiscbhp) = to_number(:cv) and to_number(gv.mvcapkw) = to_number(:kW)");

    public static final String ELENCA_ALLESTIMENTI_ORDER = " order by MVREG1 desc, MVVERSD asc";


    public static final String ANIA_SQL_FROM = ", gen_4r_omologazioni g4o";

    public static final StringBuilder ANIA_SQL_FILTER = new StringBuilder()
            .append(" AND g4o.omocod = :codiceOmologazione AND gv.mvvehcode = g4o.mvvehcode")
            .append(" AND gv.mvqrtdte = g4o.mvqrtdte AND gv.data_d_inizio_validita = g4o.data_d_inizio_validita")
            .append(" AND gv.data_d_fine_validita = g4o.data_d_fine_validita");



    public static final String FILTRA_ALLESTIMENTI_CV = " and to_number(gv.mvfiscbhp) = to_number(:cv)";
    public static final String FILTRA_ALLESTIMENTI_KW = " and to_number(gv.mvcapkw) = to_number(:kW)";


    public static final String ANIA_JPQL_FROM = " join d4r.codiciOmologazione codici";

    public static final String ANIA_JPQL_FILTER = " and :codiceOmologazione = codici.codiceOmologazione";

    public static final String GET_FROM_OMOLOGATION_CODE = "select distinct qrom.mvvehcode,qrom.mvqrtdte from gen_4r_omologazioni qrom WHERE " +
                                                      " qrom.omocod = :codiceOmologazione ";

    public static final String GET_MODEL_DESCRIPTION_BY_MAKER_CODE = "select distinct d4r.descrizioneModello from " +
                                                                     "QuattroRuoteItem d4r"
                                                                     +  " where d4r.codiceMarca = :codiceMarca ";

    public static final String GET_INFOCAR_CODE_AND_DESCRIPTION_BY_MAKER_CODE = "select distinct d4r.codiceInfocar, " +
                                                                                "d4r.descrizioneModello" +
                                                                              " from QuattroRuoteItem d4r"
                                                                              +  " where d4r.codiceMarca = :codiceMarca ";
    // MANUAL ENTRY QUERIES

    public static final String LOAD_VEHICLE = "FROM QuattroRuoteItem qr WHERE qr.codiceMarca = :vehicleMakers ";
    public static final String AND_MODEL = " AND trim(qr.codiceInfocar) = :vehicleModels";
    public static final String AND_SUPPLY_CODE = " AND qr.cilindrata = :supplyCode";
    public static final String AND_KW = " AND (ltrim(qr.potenzaKw,'0') = :kw OR qr.potenzaKw = :kw) ";
    public static final String AND_TAXABLE_HORSE_POWER = " AND qr.cavalliFiscali = :taxableHorsePower";
    public static final String AND_FIRST_MATRICULATION_DATE = " AND qr.dataInizioProduzione = :firstMatriculationDate";

    public static final String GET_NEW_VEHICLE_ID = "select NEW_VEHICLE_ID.nextval from dual";

}
