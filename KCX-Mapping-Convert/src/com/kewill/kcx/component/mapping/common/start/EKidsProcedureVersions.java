package com.kewill.kcx.component.mapping.common.start;

/**
 * Module        : EKidsProcedureVersions<br>
 * Created	     : 31.03.2009<br>
 * Description	 : Valid Names of Customs Procedures with version numbers of Zabis. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public enum EKidsProcedureVersions {
    /**
     * KIDS Release 1.0.00 EXPORT.
     */
	K10EXPORT,
	
	/**
     * KIDS Release 1.1.00 EXPORT.
	 */
	K11EXPORT,
	
	/**
     * KIDS Release 1.2.00 EXPORT.
	 */
	K12EXPORT,
	K20EXPORT,

	/**
     * KIDS Release 2.1.00 EXPORT.
	 */	
	K21EXPORT, 					 //EI20120802
    /**
     * KIDS Release 1.0.00 EMCS.
     */
    K10EMCS,
    
    /**
     * KIDS Release 2.0.00 EMCS.
     */
    K20EMCS, 
    /**
     * KIDS Release 2.1.00 EMCS == ZABIS 7.2
     */
    K21EMCS,  
    
    /**
     * KIDS release 1.0.00 ICS.
     */
    K10ICS,

    /**
     * KIDS release 2.0.00 ICS.
     */
    K20ICS,
    
    /**
     * KIDS release 4.0.00 NCTS.
     */
    K40NCTS,
    K41NCTS,                         //EI20121121; 121220: von V42 auf V41 geaendert 
    
    /**
     * KIDS release 1.0.00 IMPORT.    //EI20110912
     */
    K10IMPORT,
	 /**
     * KIDS release 2.0.00 IMPORT.    //EI20110922
     */
    K20IMPORT,
    
	 /**
     * KIDS release 1.0.00 PORT.    //EI20111021
     */
    K10POR,
    K10PORT,
    K20PORT,
    
    
    /**
     * KIDS release 1.0.00 Manifest / Suma.    // CK20121115
     */
    K10MANIFEST,
    K20MANIFEST,
    K20REEXPORT                          //EI20130822
    ;
}
