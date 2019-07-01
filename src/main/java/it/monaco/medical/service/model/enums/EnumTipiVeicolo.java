package it.monaco.medical.service.model.enums;

public enum EnumTipiVeicolo {

    /**
     * Motociclo
     */
    MOTOCICLO("M", "M"),

    /**
     * Auto
     */
    AUTO("A", "A"),

    /**
     * Ciclomotore
     */
    CICLOMOTORE("C", "C"),

    /**
     * Fuoristrada
     */
    FUORISTRADA("F", "F"),


    NOT_VALID(".", ".");

    String dbValue;
    String teValue;

    private EnumTipiVeicolo(String dbValue, String teValue) {
        this.dbValue = dbValue;
        this.teValue = teValue;
    }

    public static EnumTipiVeicolo instanceForTeValue(String teValue) {
        for (EnumTipiVeicolo element : EnumTipiVeicolo.values()) {
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

    public static EnumTipiVeicolo instanceForDbValue(String dbValue) {
        for (EnumTipiVeicolo element : EnumTipiVeicolo.values()) {
            if (element.dbValue.equals(dbValue)) {
                return element;
            }
        }
        return NOT_VALID;
//		throw new IllegalArgumentException("Valore ignoto: '" + dbValue + "'");
    }
}
