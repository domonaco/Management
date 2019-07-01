package it.monaco.medical.service.model.enums;

import org.apache.commons.lang3.ArrayUtils;

public enum EnumTipiAlimentazione {

    DIESEL("D", "D", new String[]{"G", "F", "P"}), METANO("M", "M", new String[]{"ME"}), BENZINA("B", "B", new String[]{"B","B-O","B-W", "BET", "MSC"}),
    GAS("G", "G", new String[]{"GP"}), ELETTRICA("E", "E", new String[]{"ELE"}), IBRIDA("I", "I", new String[]{"GPL","M","B-E","G-E"});
    String dbValue;
    String teValue;
    String[] aniaValues;

    private EnumTipiAlimentazione(String dbValue, String teValue, String[] aniaValues) {
        this.dbValue = dbValue;
        this.teValue = teValue;
        this.aniaValues = aniaValues;
    }

    public static EnumTipiAlimentazione instanceForTeValue(String teValue) {
        for (EnumTipiAlimentazione element : EnumTipiAlimentazione.values()) {
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

    public String[] getAniaValues() {
        return aniaValues;
    }

    public static EnumTipiAlimentazione instanceForDbValue(String dbValue) {
        for (EnumTipiAlimentazione element : EnumTipiAlimentazione.values()) {
            if (element.dbValue.equals(dbValue)) {
                return element;
            }
        }
        return null;
//		throw new IllegalArgumentException("Valore ignoto: '" + dbValue + "'");
    }


    public static EnumTipiAlimentazione instanceForAniaValue(String aniaValue) {
        for (EnumTipiAlimentazione element : EnumTipiAlimentazione.values()) {
            if (ArrayUtils.contains(element.getAniaValues(), aniaValue)) {
                return element;
            }
        }
        throw new IllegalArgumentException("Valore ignoto: '" + aniaValue + "'");
    }
}
