package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: CustomerProcedureDTO<br>
 * Erstellt		: 29.01.2009<br>
 * Beschreibung	: Data Transfer Object for the "customer_procedures" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CustomerProcedureDTO {
	private String   kcxId       = null;
	private String   procedure   = null;
	private String   msgFormat   = null;
    private String   mappingCode = null;
    private String   fssVersion  = null;
    private String   uidsVersion = null;
    private String   kidsRelease = null;
	
	
	public String getKcxId() {
		return kcxId;
	}
	public String getProcedure() {
		return procedure;
	}
    public String getMsgFormat() {
        return msgFormat;
    }
    public String getMappingCode() {
        return mappingCode;
    }
    public void setKcxId(String kcxId) {
        this.kcxId = kcxId;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public void setMsgFormat(String msgFormat) {
        this.msgFormat = msgFormat;
    }
    public void setMappingCode(String mappingCode) {
        this.mappingCode = mappingCode;
    }
	public String getFssVersion() {
		return fssVersion;
	}
	public void setFssVersion(String fssVersion) {
		this.fssVersion = fssVersion;
	}
	public String getUidsVersion() {
		return uidsVersion;
	}
	public void setUidsVersion(String uidsVersion) {
		this.uidsVersion = uidsVersion;
	}
	public String getKidsRelease() {
		return kidsRelease;
	}
	public void setKidsRelease(String kidsRelease) {
		this.kidsRelease = kidsRelease;
	}
}
