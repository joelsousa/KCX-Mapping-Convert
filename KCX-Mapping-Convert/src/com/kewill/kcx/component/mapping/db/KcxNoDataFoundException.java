/*
 * Funktion    : KcxNoDataFoundException.java
 * Titel       :
 * Erstellt    : 18.09.2008
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
 * Modul		: KcxNoDataFoundException<br>
 * Erstellt		: 10.12.2008<br>
 * Beschreibung	: Exception thrown if no data could be found in the database. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxNoDataFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KcxNoDataFoundException() {
    }

    public KcxNoDataFoundException(String message) {
        super(message);
    }

    public KcxNoDataFoundException(Throwable cause) {
        super(cause);
    }

    public KcxNoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
