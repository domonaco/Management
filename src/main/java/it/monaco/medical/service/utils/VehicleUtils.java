package it.monaco.medical.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class VehicleUtils {
    /**
     * Data la data di immatricolazione ritorno <code>true</code> se il veicolo e' assicurato come
     * nuovo (fino a 6 mesi dall'immatricolazione e valore assicurato uguale al valore di listino
     * 4Ruote).
     *
     * @return
     */
    public static boolean isNewVehicle(Date matriculationDate) {
        GregorianCalendar calendarNow = new GregorianCalendar();
        calendarNow.setTime(Calendar.getInstance().getTime());
        GregorianCalendar calendarImmatricolazione = new GregorianCalendar();
        calendarImmatricolazione.setTime(matriculationDate);

		/*
		 * Il veicolo e' nuovo se ha fino a 6 mesi di eta' (potra' essere assicurato per il valore di
		 * listino a nuovo)
		 */
        calendarNow.add(Calendar.MONTH, -6);
        if (calendarImmatricolazione.after(calendarNow)) {
            return true;
        } else {
            return false;
        }
    }



    public static boolean isNewVehicle(Long matriculationDateYYYYMM) throws Exception {
        // TODO responsive - utilizzare it.dl.library.utils.DateUtils

        return isNewVehicle(convertMatriculationLongToDate(matriculationDateYYYYMM));
    }

    /**
     * Ritorna il coefficiente di deprezzamento del veicolo dati il valore usato
     * ed il valore a nuovo.
     *
     * @param usedVehicleValue
     * @param newVehicleValue
     * @return float
     */
    public static float getVehicleDepreciationCoefficient(float usedVehicleValue, float newVehicleValue) {
        return (usedVehicleValue / newVehicleValue);
    }

    /**
     * Ritorna il valore usato di un optional dato il valore a nuovo
     * dell'optional ed i valori nuovo ed usato del veicolo.
     *
     * @param usedVehicleValue
     * @param newVehicleValue
     * @param newOptionalValue
     * @return double
     */
    public static double calculateUsedOptionalValue(float usedVehicleValue, float newVehicleValue, float newOptionalValue) {
        return Math.floor(newOptionalValue * getVehicleDepreciationCoefficient(usedVehicleValue, newVehicleValue));
    }


    /**
     * Ritorna la data in formato {@link Date}
     *

     * @return {@link Date}
     */
    @Deprecated
    private static Date convertMatriculationLongToDate(Long dateYYYYMM) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try {
            return sdf.parse(String.valueOf(dateYYYYMM));

        } catch (ParseException e) {

            throw new Exception(e);
        }
    }

}
