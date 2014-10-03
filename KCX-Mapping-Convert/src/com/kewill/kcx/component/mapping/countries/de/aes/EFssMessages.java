package com.kewill.kcx.component.mapping.countries.de.aes;

/*
 * Function    : EFssMessages.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / kron
 * Description : valid Names of internal ZABIS-Messagenames in Export
 * 			   : relates to the doc aes-fss-60.doc 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Iwaniuk
 * Date        :
 * Label       : EI20100208
 * Description : HH - for testing of BellDavis-Message 
 *
 */
public enum EFssMessages {

    CAA, CAE,
    /**
     * Rückgabe (reverse) Ausfuhranmeldung = Überlassung (KIDS/UIDS: ExportRelease). 
     */
    HH,
    ADP, 
    ASP, AMR, AUP, AZP, STI, ERR, CON,
    AUG,     //new for V70
    ACL;     //new for V72
}
