package com.kewill.kcx.component.mapping.util;

/*
 * Function    : KcxDateTime.java
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Heise
 * Description : formatting of date and time formats for KIDS, UIDS, ZABIS
 * Parameters  : 
 * 
 * SH090428 KcxDateTime succeeds even if the time zone is left out in ST_DateTime, ST_DateTimeT  
*/

import java.text.ParseException;


import java.text.SimpleDateFormat;

/**
 * Formatting of date and time formats for KIDS, UIDS, ZABIS.
 * Date: 10.09.2008
 * 
 * @author Sven Heise
 * @version 1.0.00
 * 
*/
public class KcxDateTime {

	// Date/Time is stored in Java-Date
	protected java.util.Date dateTime; 

	/**
	 * Initialization by format and string.
	 * 
	 * @param eFormatFrom : enum EFormat (ST_Date, ST_DateTime, KIDS_DateTime, ...)
	 * @param sDateTime : String containing the date/time-value formatted as defined in eFormatFrom
	 * @throws ParseException
	 */
	public KcxDateTime(EFormat eFormatFrom, String sDateTime)
	throws ParseException {
		try {
			SimpleDateFormat sDateFormat = new SimpleDateFormat(eFormatFrom.getFormat());
			dateTime = sDateFormat.parse(sDateTime);
		} catch (ParseException e) {
			// SH090428 try to parse again if the time zone is left out
			if (eFormatFrom.equals(EFormat.ST_DateTimeT)) {
				SimpleDateFormat sDateFormat = new SimpleDateFormat(EFormat.ST_DateTimeTNoZ.getFormat());
				dateTime = sDateFormat.parse(sDateTime);
			} else if (eFormatFrom.equals(EFormat.ST_DateTime)) {
				SimpleDateFormat sDateFormat = new SimpleDateFormat(EFormat.ST_DateTimeNoZ.getFormat());
				dateTime = sDateFormat.parse(sDateTime);
			} else {
				throw e;
			}
		}
			
	}

	/**
	 * initialization by single strings for year, month, day, time, timezone<br>
	 * every parameter string can be empty but if year is not empty, month and day should not be empty (otherwise no date value is stored)
	 * @param year : String year with four digits 
	 * @param month: String month with one or two digits (9, 09)
	 * @param day : String month with one or two digits (5, 05)
	 * @param time : String time formatted as HHmm, HH:mm, HHmmss or HH:mm:ss or empty
	 * @param timezone : String timezone formatted as +HH:mm or empty
	 * @throws ParseException if the Strings contain wrong values
	 */
	public KcxDateTime(String year, String month, String day, String time, String timezone) 
	throws ParseException {
		String sDateTime = "";
		String sFormat = "";
		// year with four digits; if year is empty, month and day are left out
		if ((year != null) && (year.length() == 4)) {
			sDateTime += year;
			sFormat += "yyyy";
			if ((month != null) && (month.length() > 0)) {
				sDateTime += month;
				if (month.length() == 1) {
					sFormat += "M";
				} else {
					sFormat += "MM";
				}
			}
			if ((day != null) && (day.length() > 0)) {
				sDateTime += day;
				if (day.length() == 1) {
					sFormat += "d";
				} else {
					sFormat += "dd";
				}
			}
		}
		// time 
		if ((time != null) && (time.length() > 0)) {
			sDateTime += time;
			if (time.length() == 4) {
				sFormat += "HHmm";
			} else if (time.length() == 5) {
				sFormat += "HH:mm";
			} else if (time.length() == 6) {
				sFormat += "HHmmss";
			} else if (time.length() == 8) {
				sFormat += "HH:mm:ss";
			}
		}
		if ((timezone != null) && (timezone.length() > 0)) {
			sDateTime += " " + timezone;
			sFormat += " Z";
		}
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat(sFormat);
		dateTime = sDateFormat.parse(sDateTime);
	}

	/**
	 * returns a formatted String for the stored date 
	 * @param eFormatTo enum EFormat (ST_Date, ST_DateTime, KIDS_DateTime, ...)
	 * @return String
	 */
	public String format(EFormat eFormatTo) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(eFormatTo.getFormat());
		return sDateFormat.format(dateTime);
	}

}
