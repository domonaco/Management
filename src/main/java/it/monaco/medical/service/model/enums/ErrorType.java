package it.monaco.medical.service.model.enums;

public enum ErrorType {

    VALIDATION("VALIDATION"),
    DATABASE("DATABASE"),
    UNEXPECTED("UNEXPECTED");

    private String label;

    ErrorType(String label)
    {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
