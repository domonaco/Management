package it.monaco.medical.service.model.entities;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class QuattroRuoteId implements Serializable {

    public long getCodiceInfocar() {
        return codiceInfocar;
    }

    public void setCodiceInfocar(long codiceInfocar) {
        this.codiceInfocar = codiceInfocar;
    }

    public long getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(long dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Date getInizioValidita() {
        return inizioValidita;
    }

    public void setInizioValidita(Date inizioValidita) {
        this.inizioValidita = inizioValidita;
    }

    public Date getFineValidita() {
        return fineValidita;
    }

    public void setFineValidita(Date fineValidita) {
        this.fineValidita = fineValidita;
    }

    private static final long serialVersionUID = 1L;
    @Column(name = "MVVEHCODE")
    long codiceInfocar;
    @Column(name = "MVQRTDTE")
    long dataInserimento;
    @Column(name = "DATA_D_INIZIO_VALIDITA")
    @Temporal(TemporalType.DATE)
    Date inizioValidita;
    @Column(name = "DATA_D_FINE_VALIDITA")
    @Temporal(TemporalType.DATE)
    Date fineValidita;
}
