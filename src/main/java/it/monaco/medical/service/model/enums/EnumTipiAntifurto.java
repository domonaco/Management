package it.monaco.medical.service.model.enums;

public enum EnumTipiAntifurto {

    /**
     * Immobilizzatore o Satellitare
     */
    IMMOBILIZER_SATELLITARE("D         ", "D"),

    /**
     * Nessuno
     */
    NESSUNO("N         ", "N"),

    /**
     * Solo allarme
     */
    SOLO_ALLARME("A         ", "A");

    String dbValue;
    String teValue;

    private EnumTipiAntifurto(String dbValue, String teValue) {
        this.dbValue = dbValue;
        this.teValue = teValue;
    }

    public static EnumTipiAntifurto instanceForTeValue(String teValue) {
        for (EnumTipiAntifurto element : EnumTipiAntifurto.values()) {
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

    public static EnumTipiAntifurto instanceForDbValue(String dbValue) {
        for (EnumTipiAntifurto element : EnumTipiAntifurto.values()) {
            if (element.dbValue.equals(dbValue)) {
                return element;
            }
        }
        return null;
//		throw new IllegalArgumentException("Valore ignoto: '" + dbValue + "'");
    }


}
