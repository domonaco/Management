package it.monaco.medical.service.model.entities;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "GEN_4R_OMOLOGAZIONI")
public class CodiciOmologazione {
    @Column(name = "OMOCOD", updatable = false, insertable = false)
    String codiceOmologazione;

    @Column(name = "OMOTEC", updatable = false, insertable = false)
    String codiceTecnico;

    public String getCodiceOmologazione() {
        return codiceOmologazione;
    }

    public void setCodiceOmologazione(String codiceOmologazione) {
        this.codiceOmologazione = codiceOmologazione;
    }

    public String getCodiceTecnico() {
        return codiceTecnico;
    }

    public void setCodiceTecnico(String codiceTecnico) {
        this.codiceTecnico = codiceTecnico;
    }

}

