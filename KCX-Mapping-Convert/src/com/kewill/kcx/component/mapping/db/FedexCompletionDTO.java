package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: FedexCompletionDTO<br>
 * Erstellt		: 30.03.2011<br>
 * Beschreibung	: Data Transfer Object for the "fedex_completion" database table. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class FedexCompletionDTO {
	private String   kcxId        = "";
	private String   key = "";
	private String   value = "";
	
	
    public String getKcxId() {
        return kcxId;
    }
    public void setKcxId(String kcxId) {
        this.kcxId = kcxId;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
