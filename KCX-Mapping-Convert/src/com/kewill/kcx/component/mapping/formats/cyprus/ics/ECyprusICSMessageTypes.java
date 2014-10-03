package com.kewill.kcx.component.mapping.formats.cyprus.ics;
/**
 * Module		: ECyprusICSMessageTypes<br>
 * Date Created	: 09.06.2011<br>
 * Description	: List of values permitted in field MesTypMES20 (Message type) identifying an ICS message.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public enum ECyprusICSMessageTypes {

    /**
     * IE304
     * ICSEntrySummaryDeclarationAmendmentAccepted.
	 */
    CC304A,
    
    /**
     * IE305
     * ICSEntrySummaryDeclarationAmendmentRejected.
     */
    CC305A,
    
    /**
     * IE313
     * ICSDeclarationAmendment.
     */
    CC313A,
    
    /**
     * IE315
     * ICSEntrySummaryDeclaration.
     */
    CC315A,
    
    /**
     * IE316
     * ICSEntrySummaryDeclarationRejected.
     */
    CC316A,
    
    /**
     * IE323
     * ICSDiversionRequest.
     */
    CC323A,
    
    /**
     * IE324
     * ICSDiversionRequestRejected.
     */
    CC324A,
    
    /**
     * IE325
     * ICSDiversionRequestAcknowledgement.
     */
    CC325A,
    
    /**
     * IE328
     * ICSEntrySummaryDeclarationAcknowledgement.
     */
    CC328A,
    
    /**
     * IE351
     * ICSAdvancedInterventionNot.
     */
    CC351A,
    
    /**
     * IE917
     * XML NACK.
     */
    CD917B
    
}
