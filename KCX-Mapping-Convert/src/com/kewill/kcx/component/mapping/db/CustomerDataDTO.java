package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: CustomerDataDTO<br>
 * Erstellt		: 17.06.2009<br>
 * Beschreibung	: Data Transfer Object for the "customer_data" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CustomerDataDTO {
	private String   kcxId        = "";
	private String   pdfOutFormat = "";
	private String   bobName      = "";   //EI20130215
	
    public String getKcxId() {
        return kcxId;
    }
    public void setKcxId(String kcxId) {
        this.kcxId = kcxId;
    }
    public String getPdfOutFormat() {
        return pdfOutFormat;
    }
    public void setPdfOutFormat(String pdfOutFormat) {
        this.pdfOutFormat = pdfOutFormat;
    }
    public String getBobName() {
    	if (bobName != null) {    //EI20130902
    		bobName = bobName.trim();
    	}
        return bobName;
    }
    public void setBobName(String bob) {
        this.bobName = bob;
    }
}
