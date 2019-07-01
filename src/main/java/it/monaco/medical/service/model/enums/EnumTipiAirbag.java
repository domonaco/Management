package it.monaco.medical.service.model.enums;

public enum EnumTipiAirbag {

    /**
     * Lato Guida (conducente)
     */
    CONDUCENTE("1", "1", "Lato guida"),

    /**
     * Doppio Airbag (conducente e passeggero)
     */
    CONDUCENTE_PASSEGGERO("2", "2", "Conducente e passeggero"),

    /**
     * Doppio e Laterali (conducente,passeggero e laterali)
     */
    CONDUCENTE_PASSEGGERO_LATERALI("3", "3", "Conducente, passeggero e laterali"),

    /**
     * Nessun airbag
     */
    NESSUNO("4", "4", "Nessuno");

    String dbValue;
    String teValue;
    String description;

    private EnumTipiAirbag(String dbValue, String teValue) {
        this.dbValue = dbValue;
        this.teValue = teValue;
    }

    private EnumTipiAirbag(String dbValue, String teValue, String description) {
        this.dbValue = dbValue;
        this.teValue = teValue;
        this.description = description;
    }

    public static EnumTipiAirbag instanceForTeValue(String teValue) {
        for (EnumTipiAirbag element : EnumTipiAirbag.values()) {
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

    public String getDescription() {
        return description;
    }

    public static EnumTipiAirbag instanceForDbValue(String dbValue) {
        for (EnumTipiAirbag element : EnumTipiAirbag.values()) {
            if (element.dbValue.equals(dbValue)) {
                return element;
            }
        }
        return null;
//		throw new IllegalArgumentException("Valore ignoto: '" + dbValue + "'");
    }
}
