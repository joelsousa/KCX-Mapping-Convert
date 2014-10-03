package com.kewill.kcx.component.mapping.formats.kids.common;

/**
 * Modul        : EKidsHeaderTags<br>
 * Erstellt     : 20.05.2010<br>
 * Beschreibung : Liste der Tags die in einem KIDS-Header (einschl. SOAP-Header) vorkommen k�nnen.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public enum EKidsHeaderTags {
    /**
     * SOAP-Envelope.
     */
    Envelope, 
        /**
         * SOAP Header und KIDS Header.
         */
        Header,  KidsHeader, KIDSHeader, //EI20130510: BDP kann kein "Header" sondern "KidsHeader"
            /**
             * Sendetimestamp.
             */
            SentAt,
                /**
                 * Sendedatum.
                 */
                Date,
                    /**
                     * Sendedatum Jahr, Monat, Tag.
                     */
                    Year, Month, Day, 
                /**
                 * Sendezeit.
                 */
                Time, 
                /**
                 * Zeitzone des Sendezeitstempels.
                 */
                TimeZone, Timezone,
            /**
             * KIDS-ID des Absenders.
             */
            Transmitter, 
            /**
             * KIDS-ID des Empf�ngers.
             */
            Receiver, 
            /**
             * Method?
             */
            Method,
            /**
             * Nachrichtenparameter.
             */
            MessageTP,
                /**
                 * L�dercode des Zielandes.
                 */
                CountryCode, 
                /**
                 * Zollverfahren.
                 * Export, EMCS, ...
                 */
                Procedure, 
                /**
                 * Zollverfahren.
                 * N�here Unterteilung (?)
                 */
                ProcedureSpecification, 
                /**
                 * Nachrichtenname / Nachrichtentyp.
                 */
                MessageName, 
                /**
                 * Version.
                 */
                Release,
                /**
                 * JustCode?.
                 */
                JustCode,
                /**
                 * Nachrichtenrichtung. AK20131007 Kommentar angepasst
                 * FROM_CUSTOMER = Nachricht vom Kunden an den Zoll oder die Anwendung.
                 * TO_CUSTOMER   = Nachricht vom Zoll oder der Anwendung an den Kunden.
                 */
                Direction, 
            /**
             * Parameter f�r CustomsXChange.
             */
            CustomsExchange,
                /**
                 * Absender Firma.
                 */
                Sender,
                /**
                 * Absender Niederlassung.
                 */
                Senderbranch,
                /**
                 * Empf�nger Firma.
                 * Bereits oben definiert
                 */
                // Receiver, 
                /**
                 * Empf�nger Niederlassung.
                 */
                Receiverbranch,
                /**
                 * Codemappingparameter.
                 */
                Codemapping,
                CodeMapping,
                    /**
                     * Soll gemapt werden? 1=Ja, 0=Nein
                     */
                    Map,
                    /**
                     * L�ndercode des Landes dessen Codes gemappt werden soll.
                     */
                    MapFrom,
                    /**
                     * L�ndercode des Landes in dessen Codes gemappt werden soll.
                     */
                    MapTo,
                    /**
                     * ApplicationControl.
                     */
                    ApplicationControl,
                    /**
                     * AutoTransmission.
                     */
                    AutoTransmission,
                    /**
                     * ValidationCheck 
                     */
                    ValidationCheck,
                    /**
                     * TestIndicator 
                     */
                    TestIndicator,
                    
            /**
             * Default Selection?.
             */
            DefaultSelection,
                /**
                 * User?.
                 */
                User,
                /**
                 * TagNAme?.
                 */
                TagName,
            /**
             * Eindeutige ID dieser Nachricht.
             */
            MessageID,
            /**
             * Eindeutige ID der Nachricht auf die sich dieses Nachricht bezieht.
             */
            InReplyTo,
            /**
             * Identifies the physical sending party (i.e. from which machine does this message come?).
             */
            CustomerNumber;
}
