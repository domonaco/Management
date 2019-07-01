package it.monaco.medical.service.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LegacyDateUtils {
    private static final Logger logger = LoggerFactory.getLogger(LegacyDateUtils.class);
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_UI_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DEFAULT_UI_HOUR_MINUTE_FORMAT = "HH.mm";
    public static final String DEFAULT_UI_DATE_FORMAT_EXT_MONTH = "dd MMMM yyyy";
    public static final String YYYYMMDD_SHORT = "yyyy-MM-dd";
    public static final String YYMMDD_SHORT = "yy-MM-dd";
    public static final String YYYYMM_SHORT = "yyyy-MM";
    public static final String YYYYMM_LONG = "yyyy MMM";
    public static final String ORACLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Locale LOCALE_IT;
    public static final String PATTERN_FROM = "yyyyMMdd";
    public static final String PATTERN_INVERTED = "ddMMyyyy";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYY = "yyyy";
    private static GregorianCalendar calendar;

    public LegacyDateUtils() {
    }

    public static GregorianCalendar newGregorianCalendar() {
        return new GregorianCalendar();
    }

    public static Calendar newCalendar() {
        return Calendar.getInstance();
    }

    public static Date newDate() {
        return new Date();
    }

    public static Date getStartDate(Date date) {
        calendar.setTime(date);
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return (Date)calendar.getTime().clone();
    }

    public static Date getEndDate(Date date) {
        calendar.setTime(date);
        calendar.set(11, calendar.getActualMaximum(11));
        calendar.set(12, calendar.getActualMaximum(12));
        calendar.set(13, calendar.getActualMaximum(13));
        calendar.set(14, calendar.getActualMaximum(14));
        return (Date)calendar.getTime().clone();
    }

    public static Date getNextDay(Date date) {
        calendar.setTime(date);
        calendar.add(5, 1);
        return (Date)calendar.getTime().clone();
    }

    public static Date getNextNDay(Date date, int n) {
        calendar.setTime(date);
        calendar.add(5, n);
        return (Date)calendar.getTime().clone();
    }

    public static Date getBeginOfMonth(Date date, int months) {
        calendar.setTime(date);
        if (months != 0) {
            calendar.add(2, months);
        }

        calendar.set(5, calendar.getActualMinimum(5));
        return (Date)calendar.getTime().clone();
    }

    public static Date getEndOfMonth(Date date, int months) {
        calendar.setTime(date);
        if (months != 0) {
            calendar.add(2, months);
        }

        calendar.set(5, calendar.getActualMaximum(5));
        return (Date)calendar.getTime().clone();
    }

    public static Date getEndOfMonth(Date date) {
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        return (Date)calendar.getTime().clone();
    }

    public static Date getCurrentEndOfMonth() {
        return getEndOfMonth(new Date());
    }

    public static int daysBetween(Date startDate, Date endDate) {
        GregorianCalendar c1 = newGregorianCalendar();
        GregorianCalendar c2 = newGregorianCalendar();
        c1.setTime(startDate);
        c2.setTime(endDate);
        return daysBetween((Calendar)c1, (Calendar)c2);
    }

    public static int daysBetween(Calendar early, Calendar late) {
        return (int)(toJulian(late) - toJulian(early));
    }

    public static final float toJulian(Calendar c) {
        int Y = c.get(1);
        int M = c.get(2);
        int D = c.get(5);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (float)((int)(365.25F * (float)(Y + 4716)));
        float F = (float)((int)(30.6001F * (float)(M + 1)));
        float JD = (float)(C + D) + E + F - 1524.5F;
        return JD;
    }

    public static Date getBeginOfYear(Date date) {
        calendar.setTime(date);
        calendar.set(2, calendar.getActualMinimum(2));
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, calendar.getActualMinimum(11));
        calendar.set(12, calendar.getActualMinimum(12));
        calendar.set(13, calendar.getActualMinimum(13));
        calendar.set(14, calendar.getActualMinimum(14));
        return (Date)calendar.getTime().clone();
    }

    public static Date getEndOfYear(Date date) {
        calendar.setTime(date);
        calendar.set(2, calendar.getActualMaximum(2));
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, calendar.getActualMaximum(11));
        calendar.set(12, calendar.getActualMaximum(12));
        calendar.set(13, calendar.getActualMaximum(13));
        calendar.set(14, calendar.getActualMaximum(14));
        return (Date)calendar.getTime().clone();
    }

    public static int getYearOfDate(Date date) {
        return calendar.get(1);
    }

    public static Date getEndOfYear(int year) {
        calendar.set(1, year);
        calendar.set(2, calendar.getActualMaximum(2));
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, calendar.getActualMaximum(11));
        calendar.set(12, calendar.getActualMaximum(12));
        calendar.set(13, calendar.getActualMaximum(13));
        calendar.set(14, calendar.getActualMaximum(14));
        return (Date)calendar.getTime().clone();
    }

    public static final Date roundToLastMinute(Date date) {
        if (date == null) {
            return null;
        } else {
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            return (Date)calendar.getTime().clone();
        }
    }

    public static final Date roundToFirstMinute(Date date) {
        if (date == null) {
            return null;
        } else {
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return (Date)calendar.getTime().clone();
        }
    }

    public static final Calendar toCalendar(String strDate, String pattern) throws Exception {
        Calendar calendar = newGregorianCalendar();
        calendar.setTime(toDate(strDate, pattern));
        return calendar;
    }

    public static final Date toDate(String strDate) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat();
        Date date = null;

        try {
            date = df.parse(strDate);
            return date;
        } catch (ParseException var4) {
            logger.debug("DateUtils.stringToDate(" + strDate + ")");
            throw new ParseException(var4.getMessage(), var4.getErrorOffset());
        }
    }

    public static final boolean isValidFormat(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);

        try {
            df.parse(strDate);
            return true;
        } catch (ParseException var4) {
            return false;
        }
    }

    public static final Date toDate(String strDate, String pattern) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(pattern );
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = null;

        try {
            date = df.parse(strDate);
            return date;
        } catch (ParseException var5) {
            logger.debug("DateUtils.stringToDate(" + strDate + "," + pattern + ")");
            throw new ParseException(var5.getMessage(), var5.getErrorOffset());
        }
    }

    public static String toString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static String toString(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static String oracleFormat(Date date) {
        return toString(date, "yyyy-MM-dd");
    }

    public static String getEndOfMonth(Date date, String pattern) {
        date = getEndOfMonth(date);
        return toString(date, pattern);
    }

    public static String getEndOfMonth(String strDate, String pattern) throws Exception {
        Date data = toDate(strDate, pattern);
        data = getEndOfMonth(data);
        return oracleFormat(data);
    }

    public static Date increaseOneYear(Date date) {
        Calendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(1, 1);
        return gc.getTime();
    }

    public static Date increase365Days(Date date) {
        return increase365DaysReturnCaledar(date).getTime();
    }

    public static Calendar increase365DaysReturnCaledar(Date date) {
        Calendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(6, 365);
        return gc;
    }

    public static Date decrease30Days(Date date) {
        int days = 30;
        return decreaseDays(date, days);
    }

    public static Date decrease45Days(Date date) {
        int days = 45;
        return decreaseDays(date, days);
    }

    public static Date decrease60Days(Date date) {
        int days = 60;
        return decreaseDays(date, days);
    }

    private static Date decreaseDays(Date date, int days) {
        Calendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(6, -days);
        return gc.getTime();
    }

    public static Date convertToDate(String dateStr, String format) throws Exception {
        return StringUtils.isNotBlank(dateStr) ? toDate(dateStr, format) : null;
    }

    public static String convertToString(Date date, String format) throws Exception {
        return date != null ? dateToString(date, format) : null;
    }

    private static String dateToString(Date date, String format) throws Exception {
        String s = null;
        SimpleDateFormat df = null;

        try {
            df = new SimpleDateFormat(format, LOCALE_IT);
            s = df.format(date);
        } catch (Exception var5) {
            logger.error("error converting", var5);
        }

        return s;
    }

    public static GregorianCalendar convertToGregorianCalendar(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar.toGregorianCalendar();
    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(GregorianCalendar calendar) throws Exception {
        try {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar xmlGregCal = datatypeFactory.newXMLGregorianCalendar(calendar);
            return xmlGregCal;
        } catch (DatatypeConfigurationException var3) {
            throw new Exception(var3);
        }
    }

    static {
        LOCALE_IT = Locale.ITALY;
        calendar = new GregorianCalendar();
    }
}
