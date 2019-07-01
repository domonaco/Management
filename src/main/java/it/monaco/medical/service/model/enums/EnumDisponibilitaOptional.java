package it.monaco.medical.service.model.enums;

public enum EnumDisponibilitaOptional {

    DI_SERIE("S"),
    OPTIONAL("O"),
    NON_DISPONIBILE("N");

    String dbValue;
    String teValue;

    private EnumDisponibilitaOptional(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static EnumDisponibilitaOptional instanceForDbValue(String dbValue) {
        for (EnumDisponibilitaOptional element : EnumDisponibilitaOptional.values()) {
            if (element.dbValue.equals(dbValue)) {
                return element;
            }
        }

        throw new IllegalArgumentException("Valore ignoto: '" + dbValue + "'");
    }
}
