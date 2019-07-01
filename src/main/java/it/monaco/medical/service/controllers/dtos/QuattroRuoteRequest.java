package it.monaco.medical.service.controllers.dtos;

import java.io.Serializable;
import java.util.Date;



import java.io.Serializable;
import java.util.Date;

public class QuattroRuoteRequest implements Serializable {

    private Date matriculationDate;

    private Date referenceDate;

    private String aniaOmologationCode;

    private String channel;

    private String makeCode;

    private String infocarCode;

    private String fuelType;

    private String host;

    public Date getMatriculationDate() {
        return matriculationDate;
    }

    public void setMatriculationDate(Date matriculationDate) {
        this.matriculationDate = matriculationDate;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public String getAniaOmologationCode() {
        return aniaOmologationCode;
    }

    public void setAniaOmologationCode(String aniaOmologationCode) {
        this.aniaOmologationCode = aniaOmologationCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMakeCode() {
        return makeCode;
    }

    public void setMakeCode(String makeCode) {
        this.makeCode = makeCode;
    }

    public String getInfocarCode() {
        return infocarCode;
    }

    public void setInfocarCode(String infocarCode) {
        this.infocarCode = infocarCode;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QuattroRuoteRequest{");
        sb.append("matriculationDate=").append(matriculationDate);
        sb.append(", referenceDate=").append(referenceDate);
        sb.append(", aniaOmologationCode='").append(aniaOmologationCode).append('\'');
        sb.append(", channel='").append(channel).append('\'');
        sb.append(", makeCode='").append(makeCode).append('\'');
        sb.append(", infocarCode='").append(infocarCode).append('\'');
        sb.append(", fuelType='").append(fuelType).append('\'');
        sb.append(", host='").append(host).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
