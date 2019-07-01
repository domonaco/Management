package it.monaco.medical.service.model.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "GEN_OPTIONAL_WEB_DESC")
@Immutable
public class OptionalDesc implements Serializable {

    @Id
    @Column(name = "OPTCOD", updatable = false, insertable = false)
    String codice;

    @Column(name = "OPTCODN", updatable = false, insertable = false)
    String codiceNumerico;

    @Column(name = "OPTDESC", updatable = false, insertable = false)
    String descrizione;

    public String getCodice() {
        return codice;
    }

    public String getCodiceNumerico() {
        return codiceNumerico;
    }

    public String getDescrizione() {
        return descrizione;
    }
}

