package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: AirportLandDTO<br>
 * Erstellt		: 28.06.2013<br>
 * Beschreibung	: Data Transfer Object for the "Airport_Land" database table. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class AirportLandDTO {
	private String   airportCode  = null;
	private String   airportBez   = null;
	private String   airportLand  = null;
    private String   airportDate  = null;
    private String   unlocode  = null;
    
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String code) {
		airportCode = code;
	}
	public String getAirportBez() {
		return airportBez;
	}
	public void setAirportBez(String bez) {
		airportBez = bez;
	}
	public String getAirportLand() {
		return airportLand;
	}
	public void setAirportLand(String land) {
		airportLand = land;
	}
	public String getAirportDate() {
		return airportDate;
	}
	public void setAirportDate(String date) {
		airportDate = date;
	} 
	public String getUnlocode() {
		return unlocode;
	}
	public void setUnlocode(String code) {
		unlocode = code;
	}  
}
