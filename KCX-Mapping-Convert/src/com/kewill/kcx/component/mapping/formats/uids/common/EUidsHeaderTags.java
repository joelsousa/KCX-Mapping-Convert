/*
 * Funktion    : EUidsHeaderTags.java
 * Titel       : Liste von UIDS-XML-Elementnamen
 * Erstellt    : 11.08.2008
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * R�ckgabe    : keine
 * Aufruf      : 
 *
 * �nderungen:
 * -----------
 * Author      : Sven Heise
 * Datum       : 08.09.2008
 * Beschreibung: AdditionalInformation erg�nzt
 *
 * Author      : iwaniuk
 * Datum       :
 * Kennzeichen : EI20100629
 * Beschreibung: new Tag TestIndicator (optional)
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.formats.uids.common;

/**
 * Modul		: EUidsHeaderTags<br>
 * Erstellt		: 29.06.2010<br>
 * Beschreibung	: Zugelassene Tags in einem UIDS-Header. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public enum EUidsHeaderTags {
    /**
     * SOAP-Envelope.
     */
	Envelope, 
        /**
         * SOAP Header und UIDS Header.
         */
	    Header, 
            /**
             * UIDS-ID des Empf�ngers.
             */
	        To, 
            /**
             * UIDS-Dom�ne des Empf�ngers.
             */
            ToDomain, 
            /**
             * UIDS-ID des Absenders.
             */
	        From, 
            /**
             * UIDS-Dom�ne des Absenders.
             */
            FromDomain, 
            /**
             * Nachrichtenname / Nachrichtentyp.
             */
	        MessageID, 
            /**
             * Eindeutige ID der Nachricht auf die sich dieses Nachricht bezieht.
             */
	        InReplyTo, 
            /**
             * Sendetimestamp.
             */
	        SentAt,
	        /**
	         * Typ der Nachricht.
	         * request = Anfrage an den Zoll
	         * inform = R�ckantwort von Zoll oder Applikation
	         * confirm = Best�tigung der Einarbeitung durch die lokale Applikation
	         * failure = Fehlermeldung der Einarbeitung durch die lokale Applikation
	         */
	        Act,
	        /**
	         * Testflag.
	         * "true", "false"
	         * Wird nicht ausgewertet.
	         */
	        TestIndicator,    //EI20100629
	        /**
	         * Bedeutung nicht bekannt.
	         */
	        AdditionalInformation, 
            /**
             * Zollverfahren
             * Export, EMCS, ...
             */
	        Procedure,
            /**
             * Zollverfahren.
             * N�here Unterteilung
             */
	        Method,
            /**
             * Nachrichtenname / Nachrichtentyp.
             */
	        MessageType, 
            /**
             * Nachrichtenrichtung. AK20131007 Kommentar angepasst
             * FROM_CUSTOMER = Nachricht vom Kunden an den Zoll oder die Anwendung.
             * TO_CUSTOMER   = Nachricht vom Zoll oder der Anwendung an den Kunden.
             */
	        Direction, 
            /**
             * Nachrichtenversion.
             */
	        MessageVersion,
	        /**
	         * Charmap.  //EI201301014 wird FORD es tatsaechlich senden???
	         */
	        CharMap,              
	        /**
	         * Unbekanntes Headertag.
	         */
	        Unknown;
}
