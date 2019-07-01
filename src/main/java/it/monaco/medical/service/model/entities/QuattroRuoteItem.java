package it.monaco.medical.service.model.entities;


import it.monaco.medical.service.model.enums.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

//  nuova QuattroRuote -> vecchia GEN_VEICOLO
@Entity
@Table(name = "GEN_VEICOLO")
@Immutable
public class QuattroRuoteItem extends  LegacyBaseEntity  implements Serializable {

        @EmbeddedId
        QuattroRuoteId id;

        @ElementCollection
        @JoinTable(name = "GEN_4R_OMOLOGAZIONI", joinColumns = {@JoinColumn(name = "MVVEHCODE", referencedColumnName = "MVVEHCODE"), @JoinColumn(name = "MVQRTDTE", referencedColumnName = "MVQRTDTE"), @JoinColumn(name = "DATA_D_INIZIO_VALIDITA", referencedColumnName = "DATA_D_INIZIO_VALIDITA"), @JoinColumn(name = "DATA_D_FINE_VALIDITA", referencedColumnName = "DATA_D_FINE_VALIDITA")})
        Set<CodiciOmologazione> codiciOmologazione = new HashSet<CodiciOmologazione>();

        @Column(name = "MVVEHCODE", updatable = false, insertable = false)
        private Long codiceInfocar;

        @Column(name = "MVQRTDTE", updatable = false, insertable = false)
        private Long dataInserimento;

        @Column(name = "MVMAKE")
        private String codiceMarca;

        @Column(name = "MVMAKED")
        private String descrizioneMarca;

        @Column(name = "MVMODEL")
        String codiceModello;

        @Column(name = "MVMODELD")
        private String descrizioneModello;

        @Column(name = "MVREG1")
        long dataInizioProduzione;

        @Column(name = "MVROAD", updatable = false, insertable = false)
        protected String tipoVeicoloString;

        @Column(name = "MVCOSTE")
        float prezzoListinoEuro;

        @Column(name = "MVCOSTL")
        float prezzoListinoLire;

        @Column(name = "MVFISCBHP")
        protected String cavalliFiscali;

        @Column(name = "MVFUEL")
        protected String alimentazioneString;

        @Column(name = "MVCAPCC")
        String cilindrata;

        @Column(name = "MVCAPKW")
        String potenzaKw;

        @Column(name = "MVBODTYPE")
        protected String tipoCarrozzeriaString;

        @Column(name = "MVMASS")
        protected String massa;

        @Column(name = "MVMAKEMOD")
        private String classeRischio;

        @Column(name = "MVVERSD")
        private String descrizioneAllestimento;

        @Column(name = "MVNOFSEAT")
        private String numeroPosti;

        @Column(name = "DATA_D_INIZIO_VALIDITA", updatable = false, insertable = false)
        @Temporal(TemporalType.DATE)
        private Date inizioValidita;

        @Column(name = "DATA_D_FINE_VALIDITA", updatable = false, insertable = false)
        @Temporal(TemporalType.DATE)
        private Date fineValidita;

        @Column(name = "MVVERSD_UPPER")
        private String descrizioneAllestimentoUppercase;

        @Column(name = "LUNGHE")
        private Integer vehicleLenght;

        @Column(name = "LARGHE")
        private Integer vehicleWidth;

        @Column(name = "ALTEZZ")
        private Integer vehicleHeigth;

        @Column(name = "MASSAR")
        private Integer kgTowableMass;

        @Column(name = "CODTRA")
        private String transactionTypeCode;

        @Column(name = "DESTRA")
        private String transactionTypeDescription;

        @Column(name = "TRAZIO")
        private String transactionTypeInit;

        @Column(name = "CODCAM")
        private String gearBox;

        @Column(name = "CAMBIO")
        private String gearBoxDescription;

        @Column(name = "VELMAX")
        private String maxSpeed;

        @Column(name = "ACC100")
        private String acceleration0100;

        @Column(name = "MVROAD")
        String vehicleTypeFlag;

        @Column(name = "CODTVE")
        String vehicleTypeCode;

        @Column(name = "CVEDES")
        String vehicleTypeDesc;

        @Column(name = "CODNOR")
        private String antipollutionCode;

        @Column(name = "DESNOR")
        private String antipollutionDesc;

        @Column(name = "MVPRODN")
        private String indicatoreInProduzione;

        @Column(name = "EFFECTDTE")
        private String effectDte;

        @Column(name = "ACTIVTO")
        private String activTo;

        @Column(name = "USERID")
        private String userID;


        @Transient
        List<OptionalData> optionals;

        @Transient
        EnumTipiAirbag tipoAirbag;

        @Transient
        EnumTipiAntifurto tipoAntifurtoDiSerie;

        public EnumTipiAirbag getTipoAirbag() {
                return tipoAirbag;
        }

        public void setTipoAirbag(EnumTipiAirbag tipoAirbag) {
                this.tipoAirbag = tipoAirbag;
        }

        public EnumTipiAntifurto getTipoAntifurtoDiSerie() {
                return tipoAntifurtoDiSerie;
        }

        public void setTipoAntifurtoDiSerie(EnumTipiAntifurto tipoAntifurtoDiSerie) {
                this.tipoAntifurtoDiSerie = tipoAntifurtoDiSerie;
        }

        public void setOptionals(List<OptionalData> optionals) {
                this.optionals = optionals;
        }

        @Transient
        List<ValoreUsato> valoriUsato;

        public void setValoriUsato(List<ValoreUsato> valoriUsato) {
                this.valoriUsato = valoriUsato;
        }

        public ValoreUsato getSingoloValoreUsato() {
                if (valoriUsato.size() == 1) {
                        return valoriUsato.iterator().next();
                } else {
                        if (valoriUsato.size() == 0) {
                                return null;
                        } else {
                                throw new IllegalStateException("Presente piu' di un valore usato per l'allestimento (manca filtro su data prima immatricolazione");
                        }
                }
        }

        public List<ValoreUsato> getValoriUsato() {
                return valoriUsato;
        }

        public List<OptionalData> getOptionals() {
                return optionals;
        }

        public long getCodiceInfocar() {
                return codiceInfocar;
        }

        public long getDataInserimento() {
                return dataInserimento;
        }

        public String getCodiceMarca() {
                return codiceMarca;
        }

        public String getDescrizioneMarca() {
                return descrizioneMarca;
        }

        public String getCodiceModello() {
                return codiceModello;
        }

        public String getDescrizioneModello() {
                return descrizioneModello;
        }

        public long getDataInizioProduzione() {
                return dataInizioProduzione;
        }

        public EnumTipiVeicolo getTipoVeicolo() {
                return EnumTipiVeicolo.instanceForDbValue(tipoVeicoloString);
        }

        public float getPrezzoListinoEuro() {
                return prezzoListinoEuro;
        }

        public float getPrezzoListinoLire() {
                return prezzoListinoLire;
        }

        public String getCavalliFiscali() {
                return cavalliFiscali;
        }

        public EnumTipiAlimentazione getAlimentazione() {
                return EnumTipiAlimentazione.instanceForDbValue(alimentazioneString);
        }

        public String getCilindrata() {
                return cilindrata;
        }

        public String getPotenzaKw() {
                return potenzaKw;
        }

        public EnumTipiCarrozzeria getTipoCarrozzeria() {
                return EnumTipiCarrozzeria.instanceForDbValue(tipoCarrozzeriaString);
        }

        public String getMassa() {
                return massa;
        }

        public String getClasseRischio() {
                return classeRischio;
        }

        public String getDescrizioneAllestimento() {
                return descrizioneAllestimento;
        }

        public Date getInizioValidita() {
                return inizioValidita;
        }

        public Date getFineValidita() {
                return fineValidita;
        }

        public String getDescrizioneAllestimentoUppercase() {
                return descrizioneAllestimentoUppercase;
        }

        public String getNumeroPosti() {
                return numeroPosti;
        }


        public Set<CodiciOmologazione> getCodiciOmologazione() {
                return codiciOmologazione;
        }

        public void setCodiciOmologazione(Set<CodiciOmologazione> codiciOmologazione) {
                this.codiciOmologazione = codiciOmologazione;
        }

        public void setId(QuattroRuoteId id) {
                this.id = id;
        }

        public void setCodiceMarca(String codiceMarca) {
                this.codiceMarca = codiceMarca;
        }

        public void setDescrizioneMarca(String descrizioneMarca) {
                this.descrizioneMarca = descrizioneMarca;
        }

        public void setCodiceModello(String codiceModello) {
                this.codiceModello = codiceModello;
        }

        public void setDescrizioneModello(String descrizioneModello) {
                this.descrizioneModello = descrizioneModello;
        }

        public void setTipoVeicoloString(String tipoVeicoloString) {
                this.tipoVeicoloString = tipoVeicoloString;
        }

        public void setCavalliFiscali(String cavalliFiscali) {
                this.cavalliFiscali = cavalliFiscali;
        }

        public void setAlimentazioneString(String alimentazioneString) {
                this.alimentazioneString = alimentazioneString;
        }

        public void setCilindrata(String cilindrata) {
                this.cilindrata = cilindrata;
        }

        public void setPotenzaKw(String potenzaKw) {
                this.potenzaKw = potenzaKw;
        }

        public void setTipoCarrozzeriaString(String tipoCarrozzeriaString) {
                this.tipoCarrozzeriaString = tipoCarrozzeriaString;
        }

        public void setMassa(String massa) {
                this.massa = massa;
        }

        public void setClasseRischio(String classeRischio) {
                this.classeRischio = classeRischio;
        }

        public void setDescrizioneAllestimento(String descrizioneAllestimento) {
                this.descrizioneAllestimento = descrizioneAllestimento;
        }

        public void setNumeroPosti(String numeroPosti) {
                this.numeroPosti = numeroPosti;
        }


        public String getAlimentazioneString() {
                return alimentazioneString;
        }

        public QuattroRuoteId getId() {
                return id;
        }

        public void setCodiceInfocar(Long codiceInfocar) {
                this.codiceInfocar = codiceInfocar;
        }

        public void setDataInserimento(Long dataInserimento) {
                this.dataInserimento = dataInserimento;
        }

        public void setDataInizioProduzione(long dataInizioProduzione) {
                this.dataInizioProduzione = dataInizioProduzione;
        }

        public String getTipoVeicoloString() {
                return tipoVeicoloString;
        }

        public void setPrezzoListinoEuro(float prezzoListinoEuro) {
                this.prezzoListinoEuro = prezzoListinoEuro;
        }

        public void setPrezzoListinoLire(float prezzoListinoLire) {
                this.prezzoListinoLire = prezzoListinoLire;
        }

        public String getTipoCarrozzeriaString() {
                return tipoCarrozzeriaString;
        }

        public void setInizioValidita(Date inizioValidita) {
                this.inizioValidita = inizioValidita;
        }

        public void setFineValidita(Date fineValidita) {
                this.fineValidita = fineValidita;
        }

        public void setDescrizioneAllestimentoUppercase(String descrizioneAllestimentoUppercase) {
                this.descrizioneAllestimentoUppercase = descrizioneAllestimentoUppercase;
        }

        public Integer getVehicleLenght() {
                return vehicleLenght;
        }

        public void setVehicleLenght(Integer vehicleLenght) {
                this.vehicleLenght = vehicleLenght;
        }

        public Integer getVehicleWidth() {
                return vehicleWidth;
        }

        public void setVehicleWidth(Integer vehicleWidth) {
                this.vehicleWidth = vehicleWidth;
        }

        public Integer getVehicleHeigth() {
                return vehicleHeigth;
        }

        public void setVehicleHeigth(Integer vehicleHeigth) {
                this.vehicleHeigth = vehicleHeigth;
        }

        public Integer getKgTowableMass() {
                return kgTowableMass;
        }

        public void setKgTowableMass(Integer kgTowableMass) {
                this.kgTowableMass = kgTowableMass;
        }

        public String getTransactionTypeCode() {
                return transactionTypeCode;
        }

        public void setTransactionTypeCode(String transactionTypeCode) {
                this.transactionTypeCode = transactionTypeCode;
        }

        public String getTransactionTypeDescription() {
                return transactionTypeDescription;
        }

        public void setTransactionTypeDescription(String transactionTypeDescription) {
                this.transactionTypeDescription = transactionTypeDescription;
        }

        public String getTransactionTypeInit() {
                return transactionTypeInit;
        }

        public void setTransactionTypeInit(String transactionTypeInit) {
                this.transactionTypeInit = transactionTypeInit;
        }

        public String getGearBox() {
                return gearBox;
        }

        public void setGearBox(String gearBox) {
                this.gearBox = gearBox;
        }

        public String getGearBoxDescription() {
                return gearBoxDescription;
        }

        public void setGearBoxDescription(String gearBoxDescription) {
                this.gearBoxDescription = gearBoxDescription;
        }

        public String getMaxSpeed() {
                return maxSpeed;
        }

        public void setMaxSpeed(String maxSpeed) {
                this.maxSpeed = maxSpeed;
        }

        public String getAcceleration0100() {
                return acceleration0100;
        }

        public void setAcceleration0100(String acceleration0100) {
                this.acceleration0100 = acceleration0100;
        }

        public String getAntipollutionCode() {
                return antipollutionCode;
        }

        public void setAntipollutionCode(String antipollutionCode) {
                this.antipollutionCode = antipollutionCode;
        }

        public String getAntipollutionDesc() {
                return antipollutionDesc;
        }

        public void setAntipollutionDesc(String antipollutionDesc) {
                this.antipollutionDesc = antipollutionDesc;
        }

        public String getVehicleTypeFlag() {
                return vehicleTypeFlag;
        }

        public void setVehicleTypeFlag(String vehicleTypeFlag) {
                this.vehicleTypeFlag = vehicleTypeFlag;
        }

        public String getVehicleTypeCode() {
                return vehicleTypeCode;
        }

        public void setVehicleTypeCode(String vehicleTypeCode) {
                this.vehicleTypeCode = vehicleTypeCode;
        }

        public String getVehicleTypeDesc() {
                return vehicleTypeDesc;
        }

        public void setVehicleTypeDesc(String vehicleTypeDesc) {
                this.vehicleTypeDesc = vehicleTypeDesc;
        }

        public String getIndicatoreInProduzione() {

                return indicatoreInProduzione;
        }

        public void setIndicatoreInProduzione(String indicatoreInProduzione) {

                this.indicatoreInProduzione = indicatoreInProduzione;
        }

        public String getEffectDte() {

                return effectDte;
        }

        public void setEffectDte(String effectDte) {

                this.effectDte = effectDte;
        }

        public String getActivTo() {

                return activTo;
        }

        public void setActivTo(String activTo) {

                this.activTo = activTo;
        }

        public String getUserID() {

                return userID;
        }

        public void setUserID(String userID) {

                this.userID = userID;
        }
}
