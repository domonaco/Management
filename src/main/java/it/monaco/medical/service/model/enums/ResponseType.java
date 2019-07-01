package it.monaco.medical.service.model.enums;

public enum ResponseType {

    INFO("info"),
    SUCCESS("sucess"),
    WARNING("warning"),
    ERROR("error");

    private final String value;

    private ResponseType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
