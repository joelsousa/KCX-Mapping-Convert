package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: DeclNumsDTO<br>
 * Erstellt		: 21.07.2010<br>
 * Beschreibung	: Data Transfer Object for the "declnums" database table. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class DeclNumsDTO {
	private String   kcxId = null;
	private String   refnr = null;
	private int   declnum = 0;
	private String   date = null;
	
	
// eventuell im Standard-Konstruktor das Datum mit today belegen?	
//	public DeclNumsDTO() {
//		date = ??? 
//    }
	
	public String getKcxId() {
		return kcxId;
	}
	public void setKcxId(String kcxId) {
		this.kcxId = kcxId;
	}
	public String getRefnr() {
		return refnr;
	}
	public void setRefnr(String refnr) {
		this.refnr = refnr;
	}
	public int getDeclnum() {
		return declnum;
	}
	public void setDeclnum(int declnum) {
		this.declnum = declnum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
