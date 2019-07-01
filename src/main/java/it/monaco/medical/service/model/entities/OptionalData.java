package it.monaco.medical.service.model.entities;

import it.monaco.medical.service.model.enums.EnumDisponibilitaOptional;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "GEN_OPTIONAL_WEB")
public class OptionalData implements Serializable {

    @Id
    @Column(name = "OPTCOD")
    String codice;

    @Column(name = "OPTDEF", updatable = false, insertable = false)
    String disponibilitaString;

    @Column(name = "OPTVAL", updatable = false, insertable = false)
    float valore;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "OPTCOD", referencedColumnName = "OPTCOD", nullable = false, insertable = false, updatable = false)
    OptionalDesc desc;

    public EnumDisponibilitaOptional getDisponibilita() {
        try {
            return EnumDisponibilitaOptional.instanceForDbValue(disponibilitaString);

        } catch(IllegalArgumentException e) {
            return EnumDisponibilitaOptional.NON_DISPONIBILE;
        }
    }

    public float getValore() {
        return valore;
    }

    public String getCodice() {
        return codice;
    }

    public String getDisponibilitaString() {
        return disponibilitaString;
    }

    public OptionalDesc getDesc() {
        return desc;
    }
}
