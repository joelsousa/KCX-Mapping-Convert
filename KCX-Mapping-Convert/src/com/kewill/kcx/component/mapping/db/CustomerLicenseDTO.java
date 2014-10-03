package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: CustomerLicenseDTO<br>
 * Erstellt		: 29.01.2009<br>
 * Beschreibung	: Data Transfer Object for the "customer_licenses" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CustomerLicenseDTO {
	private String   kcxId     = null;
    private String   country   = null;
	private String   procedure = null;
	
	
	public String getKcxId() {
		return kcxId;
	}
	public String getProcedure() {
		return procedure;
	}
    public String getCountry() {
        return country;
    }
    public void setKcxId(String kcxId) {
        this.kcxId = kcxId;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
