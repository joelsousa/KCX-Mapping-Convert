package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: KcxLicenseException<br>
 * Erstellt		: 29.01.2009<br>
 * Beschreibung	: Exception thrown if no license data could be found in the database. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxLicenseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KcxLicenseException() {
    }

    public KcxLicenseException(String message) {
        super(message);
    }

    public KcxLicenseException(Throwable cause) {
        super(cause);
    }

    public KcxLicenseException(String message, Throwable cause) {
        super(message, cause);
    }

}
