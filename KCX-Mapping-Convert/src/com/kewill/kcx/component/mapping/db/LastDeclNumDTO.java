package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: LastDeclNumDTO<br>
 * Erstellt		: 21.07.2010<br>
 * Beschreibung	: Data Transfer Object for the "declnums" database table. 
 * 
 * @author kron
 * @version 1.0.00
 */
public class LastDeclNumDTO {
	
	private int   lastnum = 0;

	public int getLastnum() {
		return lastnum;
	}

	public void setLastnum(int lastnum) {
		this.lastnum = lastnum;
	}
}
