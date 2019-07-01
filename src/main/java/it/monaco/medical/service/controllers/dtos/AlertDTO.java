package it.monaco.medical.service.controllers.dtos;

import it.monaco.medical.service.model.enums.ResponseType;

import java.io.Serializable;

public class AlertDTO implements Serializable {

    private final String message;
    private final ResponseType type;

    public AlertDTO(ResponseType type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public ResponseType getType()
    {
        return this.type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AlertDTO{");
        sb.append("message='").append(message).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
