package it.monaco.medical.service.model.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "GEN_VALORI_USATO")
public class ValoreUsato implements Serializable {

    @EmbeddedId
    QuattroRuoteId id;

    @Column(name = "ACTIVFROM", updatable = false, insertable = false)
    long attivoDa;

    @Column(name = "ACTIVTO", updatable = false, insertable = false)
    long attivoA;

    @Column(name = "MVUSEDE", updatable = false, insertable = false)
    float valoreEuro;

    @Column(name = "MVUSEDL", updatable = false, insertable = false)
    float valoreLire;

    public long getAttivoDa() {

        return attivoDa;
    }

    public void setAttivoDa(long attivoDa) {

        this.attivoDa = attivoDa;
    }

    public long getAttivoA() {

        return attivoA;
    }

    public void setAttivoA(long attivoA) {

        this.attivoA = attivoA;
    }

    public float getValoreEuro() {

        return valoreEuro;
    }

    public void setValoreEuro(float valoreEuro) {

        this.valoreEuro = valoreEuro;
    }

    public float getValoreLire() {

        return valoreLire;
    }

    public void setValoreLire(float valoreLire) {

        this.valoreLire = valoreLire;
    }
}
