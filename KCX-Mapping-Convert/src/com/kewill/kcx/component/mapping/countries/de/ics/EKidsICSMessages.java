package com.kewill.kcx.component.mapping.countries.de.ics;

/*
 * Function    : EKidsICSMessages.java
 * Titel       :
 * Date        : 09.06.2010
 * Author      : Pete T
 * Description : Valid Names of KIDS-message names in ICS
 * Parameters  :

 * Changes
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

/**
 * Modul        : EKidsICSMessages<br>
 * Erstellt     : 12.06.2010<br>
 * Beschreibung : Valid Names of KIDS-message names in ICS.
 *
 * @author  Pete T
 * @version 1.0.00
 */
public enum EKidsICSMessages {

    // names of KIDS Messages sorted in the same order like MessageNames in
	// FssUidsKidsMappingv6.xls
	// Christine Kron
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntrySummaryDeclarationAmendmentAcceptance("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntrySummaryDeclarationAmendmentRejection("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSDeclarationAmendment("CC313A", "CC313A"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntrySummaryDeclaration("CC315A", "CC315A"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntrySummaryDeclarationRejected("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSDiversionRequest("CC323A", "CC323A"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSDiversionRequestRejected("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSDiversionRequestAcknowledgment("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSUnloadingReportAcceptance("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntrySummaryDeclarationAcknowledgment("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntryDetailsData("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSEntryRelease("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSDeclarationAccepted("CY", "GR"),
	
	/**
     * Description: .
     */
	ICSEntryReleaseRejection("CY", "GR"),
	
	/**
     * Description:.
     */
	ICSImportControlDecisionNotification("CY", "GR"),
	
	/**
     * Description:.
     */
	ICSSubsequentArrivalNotification("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSSubsequentArrivalNotificationValidation("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSSubsequentArrivalItemRejection("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSArrivalNotification("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSArrivalNotificationValidation("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSArrivalItemRejection("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	ICSAdvancedInterventionNot("CY", "GR"),
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	localAppResult("CY", "GR"),	// CK 2020-11-05
	
	/**
     * Description (de): xx .
     * Direction:        xx
     */
	InternalStatusInformation("CY", "GR"), // CK 2020-11-05
 
	/**
     * Description (de): IE628 .
     * Direction:        xx
     */
	ICSExitDeclarationAcknowledgment("CY", "GR"), // AK 2010-10-22
 
	/**
     * Description (de): IE615 .
     * Direction:        xx
     */
	ICSExitSummaryDeclaration("CY", "GR"); // AK 2010-10-22
 
	
    private final String cyprus;
    private final String greece;

    EKidsICSMessages(String cy, String gr) {
        cyprus = cy;
        greece = gr;
    }

    public String getGreece() {
        return greece;
    }

    public String getCyprus() {
        return cyprus;
    }

    public static EKidsICSMessages fromGreece(String v) {
        for (EKidsICSMessages c : EKidsICSMessages.values()) {
            if (c.greece.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

	
	
	
	
}
