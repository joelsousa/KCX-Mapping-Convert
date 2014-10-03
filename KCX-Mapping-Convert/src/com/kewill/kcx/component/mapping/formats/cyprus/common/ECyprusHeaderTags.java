package com.kewill.kcx.component.mapping.formats.cyprus.common;
/**
 * Module		: ECyprusHeaderTags<br>
 * Date Created	: 09.06.2011<br>
 * Description	: List of Cyprus Header Tags.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public enum ECyprusHeaderTags {

    /**
     * Message types.
     * KIDS: MessageName
     */
    CC304A, CC305A, CC313A, CC315A, CC316A, CC323A, CC324A, CC325A, CC328A, CC351A, CD906B, CD917B, ALL,
    
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
    CorIdeMES25
}
