package com.kewill.kcx.component.mapping.db;

/**
 * Modul        : CountryConfigDTO<br>
 * Erstellt     : 06.08.2010<br>
 * Beschreibung : Data Transfer Object for the "country_config" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CountryConfigDTO {
    private String   country            = "";
    private String   xmlDir             = "";
    private String   xmlSaveDir         = "";
    private String   pdfDir             = "";
    private int      pdfWaitTime        = 0;
    private int      pdfWaitInterval    = 0;
    private String   pdfMethod          = "";
    
    
    
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getXmlDir() {
        return xmlDir;
    }
    public void setXmlDir(String xmlDir) {
        this.xmlDir = xmlDir;
    }
    public String getXmlSaveDir() {
        return xmlSaveDir;
    }
    public void setXmlSaveDir(String xmlSaveDir) {
        this.xmlSaveDir = xmlSaveDir;
    }
    public String getPdfDir() {
        return pdfDir;
    }
    public void setPdfDir(String pdfDir) {
        this.pdfDir = pdfDir;
    }
    public int getPdfWaitTime() {
        return pdfWaitTime;
    }
    public void setPdfWaitTime(int pdfWaitTime) {
        this.pdfWaitTime = pdfWaitTime;
    }
    public int getPdfWaitInterval() {
        return pdfWaitInterval;
    }
    public void setPdfWaitInterval(int pdfWaitInterval) {
        this.pdfWaitInterval = pdfWaitInterval;
    }
    public String getPdfMethod() {
        return pdfMethod;
    }
    public void setPdfMethod(String pdfMethod) {
        this.pdfMethod = pdfMethod;
    }
}

