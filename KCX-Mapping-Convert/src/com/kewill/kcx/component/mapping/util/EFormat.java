package com.kewill.kcx.component.mapping.util;

/*
 * Function    : EFormat.java
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Heise
 * Description : enumeration for KIDS-, UIDS- and other date-/time-formats with format definitions
 * 
 * SH090428 ST_DateTimeNoZ, ST_DateTimeTNoZ like ST_DateTime but without Timezone 
*/

/**
 * enumeration for KIDS-, UIDS- and other date-/time-formats with format definitions<P>
 * UIDS: ST_Date, ST_Time_Min, ST_Time_Sec, ST_Time_Ms, ST_DateTime<BR>
 * KIDS: KIDS_DateTime
 */
public enum EFormat {
	// UIDS
	ST_Date("yyyy-MM-dd"),
	ST_Time_Min("HH:mm"),
	ST_Time_Sec("HH:mm:ss"),
	ST_Time_Ms("HH:mm:ss.SSS"),
	ST_DateTimeT("yyyy-MM-dd'T'HH:mm:ssZ"),
	ST_DateTimeZ("yyyy-MM-dd' 'HH:mm:ss' 'Z"), // verwendet in alter Export Version zu ersetzen durch ST_DateTimeTZ
	// Hinweis: ist das Z im Format macht Java daraus +0100
	// darum wird das Z nun als String ausgegeben
	// auf Wunsch/Hinweis der ETN-Partner
	ST_DateTimeTZ("yyyy-MM-dd'T'HH:mm:ss'Z'"),  //EI20110119
	
	ST_DateTime("yyyy-MM-dd' 'HH:mm:ss"),
	
	//SH090428
	ST_DateTimeTNoZ("yyyy-MM-dd'T'HH:mm:ss"),
	ST_DateTimeNoZ("yyyy-MM-dd' 'HH:mm:ss"),
	// KIDS
	KIDS_DateTime("yyyyMMddHHmm"),
	KIDS_Time("HHmmss"),
	KIDS_Date("yyyyMMdd"),
	// General Use
	G_Day2("dd"),
	G_Month2("MM"),
	G_Year4("yyyy"),
	G_Time_Min("HH:mm"),
	G_Time_Sec("HH:mm:ss"),
	G_Timezone("Z");
	// add new format definitions in the enumeration
	// use format definitions for SimpleDateFormat

	private String fmtDateTime;

	EFormat(String fmtDateTime) {
		this.fmtDateTime = fmtDateTime;
	}
	
	String getFormat() {
		return fmtDateTime;
	}

	//example ST_DateTime 2008-09-10T13:41:57 +0200
}
