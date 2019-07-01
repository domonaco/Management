package it.monaco.medical.service.model.enums;

public enum EnumTipiCarrozzeria {
    /**
     * Station Wagon
     */
    STATIONWAGON("SW ", "SW"),

    /**
     * Coupe'
     */
    COUPE("C  ", "C"),

    /**
     * Furgone
     */
    FURGONE("M  ", "M"),

    /**
     * Fuoristrada???
     */
    FHT("FHT", "FHT"),

    /**
     * Torpedo con hard top
     */
    TORPEDO_HARD_TOP("SH ", "SH"),

    /**
     * Berlina 2 volumi
     */
    BERLINA_2_VOLUMI("B2V", "B2V"),

    /**
     * Multi spazio
     */
    MULTISPAZIO("MPW", "MPW"),

    /**
     * Berlina 3 volumi
     */
    BERLINA_3_VOLUMI("B3V", "B3V"),

    /**
     * Pickup
     */
    PICKUP("FA ", "FA"),

    /**
     * Fuoristrada???
     */
    FST("FST", "FST"),

    /**
     * Spider
     */
    SPIDER("A  ", "A"),

    /**
     * Pickuop con due file di sedili
     */
    SEMI_PICKUP("FSW", "FSW"),

    /**
     * Torpedo
     */
    TORPEDO("AT ", "AT"),

    ////// Sezione Moto

    HYPERSPORT("HY0", "HY01"),
    ENDURO("EP0", "EP02"),
    SPORT_TOURER("ST0", "ST03"),
    TRIAL("TR0", "TR04"),
    MOTARD("SM1", "SM10"),
    CUSTOM_CRUISER("CC2", "CC20"),
    SCOOTER("SC1", "SC15"),
    NAKED("NA1", "NA10"),
    ON_OFF("ES0", "ES02"),
    PIT_BIKE("PB0", "PB01"),
    OTHER_MOTO("XX0", "XX00"),
    OLD_OTHER_MOTO("A  ", "A   "),
    FLAT_TRACK("FT0", "FT01"),
    MOTORCROSS("MC1", "MC10"),
    MINICROSS("MC2", "MC20"),
    QUADRICICLO("QC0", "QC01");

    String dbValue;
    String teValue;

    private EnumTipiCarrozzeria(String dbValue, String teValue) {
        this.dbValue = dbValue;
        this.teValue = teValue;
    }

    public static EnumTipiCarrozzeria instanceForTeValue(String teValue) {
        for (EnumTipiCarrozzeria element : EnumTipiCarrozzeria.values()) {
            if (element.teValue.equals(teValue)) {
                return element;
            }
        }

        throw new IllegalArgumentException("Valore ignoto: '" + teValue + "'");
    }

    public String getTeValue() {
        return teValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static EnumTipiCarrozzeria instanceForDbValue(String dbValue) {
        for (EnumTipiCarrozzeria element : EnumTipiCarrozzeria.values()) {
            if (element.dbValue.equals(dbValue)) {
                return element;
            }
        }
        return null;
//		throw new IllegalArgumentException("Valore ignoto: '" + dbValue + "'");
    }
}
