package com.kewill.kcx.component.mapping.formats.malta.common.ics;

/**
 * Module		: EMaltaICSMessageTypes<br>
 * Date Created	: 19.08.2013<br>
 * Description	: List of values permitted in field MesTypMES20 (Message type) identifying an ICS message.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public enum EMaltaICSMessageTypes {

    /**
     * IE304
     * ICSEntrySummaryDeclarationAmendmentAccepted.
	 */
    CC304AType, CC304A,
    
    /**
     * IE305
     * ICSEntrySummaryDeclarationAmendmentRejected.
     */
    CC305AType, CC305A,
    
    /**
     * IE313
     * ICSDeclarationAmendment.
     */
    CC313AType, CC313A,
    
    /**
     * IE315
     * ICSEntrySummaryDeclaration.
     */
    CC315AType, CC315A,
    
    /**
     * IE316
     * ICSEntrySummaryDeclarationRejected.
     */
    CC316AType, CC316A,
    
    /**
     * IE323
     * ICSDiversionRequest.
     */
    CC323AType, CC323A,
    
    /**
     * IE324
     * ICSDiversionRequestRejected.
     */
    CC324AType, CC324A,
    
    /**
     * IE325
     * ICSDiversionRequestAcknowledgement.
     */
    CC325AType, CC325A,
    
    /**
     * IE328
     * ICSEntrySummaryDeclarationAcknowledgement.
     */
    CC328AType, CC328A,

    /**
     * IE348
     * ICSEntrySummaryDeclarationAcknowledgement.
     */
    CC348AType, CC348A,

    /**
     * IE351
     * ICSAdvancedInterventionNot.
     */
    CC351AType, CC351A,
    
    /**
     * IE917
     * XML NACK.
     */
    CD917BType, CD917B,
    
    /**
     * OPRNAK
     * Operational Error
     */
    OPRNAK,
    /**
     * OPRACK
     * Operational Technical Answer
     */
    OPRACK;
    
    ;
  
    
}
