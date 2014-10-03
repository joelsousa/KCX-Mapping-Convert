package com.kewill.kcx.component.mapping.formats.malta.common;
/**
 * Module		: EMaltaHeaderTags<br>
 * Date Created	: 14.08.2013<br>
 * Description	: List of Malta Header Tags.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public enum EMaltaHeaderTags {

    /**
     * Message types.
     * Malta: MessageName
     */
    CC304A, CC305A, CC313A, CC315A, CC316A, CC323A, CC324A, CC325A, CC328A, CC348A, CC351A, CD906B, CD917B,
    
    /**
	 * Message sender.
	 * KIDS: Transmitter
	 */
    MesSenMES3,
    
    /**
     * Message recipient.       
     * KIDS: Receiver
     */
    MesRecMES6,
    
    /**
     * Date of preparation.
     * KIDS: SentAt/Date/(Year, Month, Say)
     */
    DatOfPreMES9,
    
    /**
     * Date of preparation.
     * KIDS: SentAt/Time
     */
    TimOfPreMES10,
    
    /**
     * Priority.
     * KIDS: none
     */
    PriMES15,
    
    /**
     * Test indicator.
     * KIDS: none
     */
    TesIndMES18,
    
    /**
     * Message identification.
     * KIDS: MessageID
     */
    MesIdeMES19,
    /**
     * Message type.
     * KIDS: MessageTP/MessageName
     */
    MesTypMES20,
    
    /**
     * Correlation identifier.
     * KIDS: none
     */
    CorIdeMES25,
    
    /**
     * Operation Error
     * KIDS: none
     */
    Envelope, Body, Fault, faultcode, faultstring, detail, OPRNAK,
    
    /**
     * OPRACK
     * Operational Technical Answer
     */
    OPRACK;
    
    ;
    
}
