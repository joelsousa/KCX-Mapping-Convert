package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: AirportLandDTO<br>
 * Erstellt		: 19.03.2014<br>
 * Beschreibung	: Data Transfer Object for the "Customer_Packagecodes" database table. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CustomerPackagecodesDTO {
	private String   unRec21  = null;
	private String   description   = null;
	private String   zabis  = null;
    private String   zabisGpo  = null;
    private String   zabisHds  = null;
    private String   zabisBht  = null;
    private String   bdh  = null;
    private String   kff  = null;
    
	public String getUnRec21() {
		return unRec21;
	}
	public void setUnRec21(String unRec21) {
		this.unRec21 = unRec21;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getZabis() {
		return zabis;
	}
	public void setZabis(String zabis) {
		this.zabis = zabis;
	}
	
	public String getZabisGpo() {
		return zabisGpo;
	}
	public void setZabisGpo(String gpo) {
		this.zabisGpo = gpo;
	}
	
	public String getZabisHds() {
		return zabisHds;
	}
	public void setZabisHds(String hds) {
		this.zabisHds = hds;
	}
	
	public String getZabisBht() {
		return zabisBht;
	}
	public void setZabisBht(String bht) {
		this.zabisBht = bht;
	}
	
	public String getBdh() {
		return bdh;
	}
	public void setBdh(String bdh) {
		this.bdh = bdh;
	}
	
	public String getKff() {
		return kff;
	}
	public void setKff(String kff) {
		this.kff = kff;
	}
    	
}
