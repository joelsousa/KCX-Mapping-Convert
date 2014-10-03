/*
 * Created on 10.10.2004
 * Changes: EI20091019: trennerUK added 
 */
package com.kewill.kcx.component.mapping.formats.fss.common;

import java.text.ParseException;

import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;

/**
 * Modul		:	Teilsatz
 * Erstellt		:	10.10.2004
 * Beschreibung	:   Oberklasse aller FSS-Teilsatzklassen.
 * 	
 * @author  MS
 * @version 1.0.00
 */
public abstract class Teilsatz {
    /**
     * Der FSS-Feldtrenner (ox1D).
     */
	protected char trenner = 0x1D;
	//protected char trennerUK = ',';   //EI20091019
	protected char trennerUK = '|';     //EI201202 0x7C

	/**
	 * Die Satzkennung des Teilsatzes.
	 */
	protected String tsTyp; 

	// setFields will not be used if a FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method
	// Christine Kron 2.9.2010
	public abstract void setFields(String[] fields);

	public abstract String teilsatzBilden(); // Diese Methode hängt von der jeweiligen Teilsatzstruktur ab.

	public abstract boolean isEmpty(); // prüft ob relevante Felder gesetzt sind (außer beznr z.B.)

	public String stringToDateTime(String dateString) {
		// dateString should be as "YYYYMMDDHHMM" == KIDS_DateTime

		String datetime = ""; //dateString will be converted to formated date ==> ST_DateTime
		KcxDateTime kcx = null;

		if (dateString != null) {
			//length og KIDS_DateTime is 12: YYYYMMDDHHMM
			if (dateString.length() == 8) {
				dateString = dateString + "0000";
			} else if (dateString.length() == 14) {
				dateString = dateString.substring(0, 11);
			}

			try {
				kcx = new KcxDateTime(EFormat.KIDS_DateTime, dateString);
				datetime = kcx.format(EFormat.ST_DateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return datetime;
	}

	public String dateTimeToString(String datetime) {
		//datetime should be formated as ST_DateTime ==> "2008-10-20 22:02:55 +0200"
	    //formated datetime will be converted into String ==> YYYYMMDDHHMM (KIDS_DateTime)
		String dateString = ""; 
		KcxDateTime kcx = null;

		if (datetime != null) {
			try {
				kcx = new KcxDateTime(EFormat.ST_DateTime, datetime);
				dateString = kcx.format(EFormat.KIDS_DateTime);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateString;
	}

	public String stringToTime(String timeString) {
		// timeString should be as "HHmmss" == KIDS_Time

		String time = ""; //timeString will be converted to formated time ==> ST_Time_Sec
		KcxDateTime kcx = null;

		if (timeString != null) {
			//length og KIDS_Time is 6: HHmmss
			if (timeString.length() > 6) {
				timeString = timeString.substring(0, 5);
			}

			try {
				kcx = new KcxDateTime(EFormat.KIDS_Time, timeString);
				time = kcx.format(EFormat.ST_Time_Sec);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return time;
	}

	public String timeToString(String time) {
		//time should be formated as ST_Time_Sec ==> "22:02:55"

		String dateString = ""; //formated time will be converted into String ==> HHmmss (KIDS_Time)
		KcxDateTime kcx = null;

		if (time != null) {
			try {
				kcx = new KcxDateTime(EFormat.ST_Time_Sec, time);
				dateString = kcx.format(EFormat.KIDS_Time);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateString;
	}

	public String dateToString(String date) {
		//date should be formated as ST_Date ==> "2008-10-20"

		String dateString = ""; //formated date will be converted into String ==> YYYYMMDD (KIDS_Date)
		KcxDateTime kcx = null;

		if (date != null) {
			try {
				kcx = new KcxDateTime(EFormat.ST_Date, date);
				dateString = kcx.format(EFormat.KIDS_Date);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dateString;
	}

	public String stringToDate(String dateString) {
		// dateString should be as "YYYYMMDD" == KIDS_Date

		String date = ""; //dateString will be converted to formated date ==> ST_Date
		KcxDateTime kcx = null;

		if (dateString != null) {
			//length og KIDS_Date is 8: YYYYMMDD
			if (dateString.length() > 8) {
				dateString = dateString.substring(0, 7);
			}

			try {
				kcx = new KcxDateTime(EFormat.KIDS_Date, dateString);
				date = kcx.format(EFormat.ST_Date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

}
