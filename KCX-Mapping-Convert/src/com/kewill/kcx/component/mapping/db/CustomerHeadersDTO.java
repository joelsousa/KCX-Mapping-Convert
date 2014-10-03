/*
 * Funktion    : CustomerHeadersDTO.java
 * Titel       :
 * Erstellt    : 18.06.2010
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.db;

/**
 * Modul        : CustomerProcedureDTO<br>
 * Erstellt     : 29.01.2009<br>
 * Beschreibung : Data Transfer Object for the "customer_procedures" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CustomerHeadersDTO {
    private String   kcxId       = null;
    private String   procedure   = null;
    private String   fssVersion  = null;
    private String   kidsVersion = null;
    private String   uidsVersion = null;
    
    
    public String getKcxId() {
        return kcxId;
    }
    public void setKcxId(String kcxId) {
        this.kcxId = kcxId;
    }
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public String getFssVersion() {
        return fssVersion;
    }
    public void setFssVersion(String fssVersion) {
        this.fssVersion = fssVersion;
    }
    public String getKidsVersion() {
        return kidsVersion;
    }
    public void setKidsVersion(String kidsVersion) {
        this.kidsVersion = kidsVersion;
    }
    public String getUidsVersion() {
        return uidsVersion;
    }
    public void setUidsVersion(String uidsVersion) {
        this.uidsVersion = uidsVersion;
    }
}
