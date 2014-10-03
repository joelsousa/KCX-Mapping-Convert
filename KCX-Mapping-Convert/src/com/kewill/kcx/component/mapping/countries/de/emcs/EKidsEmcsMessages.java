package com.kewill.kcx.component.mapping.countries.de.emcs;

/**
 * Modul        : EKidsEmcsMessages<br>
 * Erstellt     : 06.05.2010<br>
 * Beschreibung : Valid Names of KIDS-Messagenames in EMCS.
 * 
 * @author  schmidt
 * @version 1.0.00
 */
public enum EKidsEmcsMessages {
	
	// from customer to customs
    /**
     * Subprocedure(s):  EME
     * Functional name:  N_AAD_SUB
     * Technical Name:   ED815
     * Description (en): Submitted draft of e-AAD
     * Description (de): Entwurf e-VD.
     * Direction:        Customer to customs
     */
    EMCSDeclaration,
    
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_DEL_EXP
     * Technical Name:   ED837
     * Description (en): Explanation on delay of delivery.
     * Description (de): Erklärung für Verspätung.
     * Direction:        Customer to customs
     */
    EMCSExplanationOnDelay,
	
	
	// from customs to customer
    /**
     * Subprocedure(s):  EME
     * Functional name:  C_EXP_NOT
     * Technical Name:   ED829
     * Description (en): Notification of accepted export.
     * Description (de): Meldung über zugelassene Ausfuhr.
     * Direction:        Customs to customer
     */
    EMCSAcceptedExport,
    
    /**
     * Subprocedure(s):  EMB
     * Functional name:  C_AAD_NOT
     * Technical Name:   ED803
     * Description (en): Notification of diverted e-AAD.
     * Description (de): Meldung über umgeleitetes e-VD.
     * Direction:        Customs to customer
     */
    EMCSDiversionNotification,
    
    /**
     * Subprocedure(s):  EME
     * Functional name:  C_EXP_REJ
     * Technical Name:   ED839
     * Description (en): Rejection of e-AAD for export.
     * Description (de): Ablehnung Ausfuhr e-VD.
     * Direction:        Customs to customer
     */
    EMCSExportRejection,
    
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_EXC_REM
     * Technical Name:   ED802
     * Description (en): Reminder message for excise movement.
     * Description (de): Erinnerungsmeldung.
     * Direction:        Customs to customer
     */
    EMCSReminderMessage,
    
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_AAD_VAL
     * Technical Name:   ED801
     * Description (en): e-AAD (Administrative accompanying document).
     * Description (de): Elektronisches Verwaltungsdokument (e-VD).
     * Direction:        Customs to customer
     */
    EMCSValidDeclaration,
    
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  N_REJ_DAT
     * Technical Name:   ED704
     * Description (en): Generic refusal Message.
     * Description (de): Fachliche Fehlermeldung.
     * Direction:        Customs to customer
     */
    EMCSGenericRefusalMessage,
    
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  E_EDI_NCK
     * Technical Name:   ED907
     * Description (en): Technical error message of an EDIFACT message.
     * Description (de): Technische Fehlermeldung einer EDIFACT Nachricht.
     * Direction:        Customs to customer
     */
    InternalStatusInformation,
    
    
    
    // from customer to customs and
    // from customs to customer
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_UPD_DAT
     * Technical Name:   ED813
     * Description (en): Change of Destination.
     * Description (de): Änderung des Bestimmungsortes.
     * Direction:        Customer to customs (EME)
     * Direction:        Customs to customer (EME, EMB)
     */
    EMCSChangeOfDestination, 
    
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_CAN_DAT
     * Technical Name:   ED810
     * Description (en): Cancellation of an e-AAD.
     * Description (de): Annulierung eines e-VD.
     * Direction:        Customer to customs (EME)
     * Direction:        Customs to customer (EME, EMB)
     */
    EMCSCancellation, 

    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_DEL_DAT
     * Technical Name:   ED818
     * Description (en): Accepted or rejected report of receipt.
     * Description (de): Eingangsmeldung.
     * Direction:        Customer to customs (EMB)
     * Direction:        Customs to customer (EME, EMB)
     */
    EMCSReportOfReceipt,
    
    /** new V20
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_REJ_DAT
     * Technical Name:   ED819
     * Description (en): Alert Or Rejection.
     * Description (de): Warnung/Ablehnung vor Empfang.
     * Direction:        Customer to customs (EMB)
     * Direction:        Customs to customer (EME, EMB)
     */
    EMCSAlertOrRejection,

    /** new V20
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_STP_NOT
     * Technical Name:   ED807
     * Description (en): Interruption Of Movement.
     * Description (de): Abbruch der Beförderung.
     * Direction:        Customer to customs (EMB)
     * Direction:        Customs to customer (EME, EMB)
     */
    EMCSInterruptionOfMovement,

     /** new V20
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_SHR_EXP
     * Technical Name:   ED871
     * Description (en): Explanation of Reason for Shortage
     * Description (de): Erläuterung zu Fehl-/Mehrmengen.
     * Direction:   
     */
    EMCSExplanationOnReasonForShortage,    //EI20110917
    //EMCSExplanationOfReasonForShortage,
    
    /** new V20
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_STP_RSP
     * Technical Name:   ED905
     * Description (en): Status Response
     * Description (de): Statusmitteilung.
     * Direction:   
     */
    EMCSStatusResponse,
    
    /** new V20
     * Subprocedure(s):  EME, EMB
     * Functional name:  C_EVT_DAT
     * Technical Name:   ED840
     * Description (en): Event Report
     * Description (de): Ereignisbericht.
     * Direction:   
     */
    EMCSEventReport,
    
    /** new V20
     * Subprocedure(s):  EME (EMA)
     * Functional name:  E_SPL_SUB
     * Technical Name:   ED825
     * Description (en): SubmittedDraftOfSplittingOperation
     * Description (de): Aufteilungsmitteilung.
     * Direction:   
     */
    EMCSSubmittedDraftOfSplittingOperation,
    
    // from application to customer
    /**
     * Subprocedure(s):  EME, EMB
     * Functional name:  -
     * Technical Name:   -
     * Description (en): Local application failure message.
     * Description (de): Einarbeitungsprotokoll.
     * Direction:        Application to customer
     */
	localAppResult;

}
