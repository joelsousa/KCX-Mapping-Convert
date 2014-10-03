package com.kewill.kcx.component.mapping.common.start;
/*
 * Function    : EFssMessages.java
 * Titel       :
 * Date        : 04.03.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : valid Names of Customs Procedures with version numbers of Zabis
 * 			   :  
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

/**
 * Modul : EFssProcedureVersions<br>
 * Erstellt : 04.03.2009<br>
 * Beschreibung : valid Names of Customs Procedures with version numbers of Zabis
 * format.
 * 
 * @author kron
 * @version 1.0.00
 */
public enum EFssProcedureVersions {
    /**
     * Export Version 5.0.
     */
	EX0500, EXP0500,
	
	/**
	 * Export Version 6.0.
	 */
	EX0600,
    EXP0600, 
    /**
	 * Export ZABIS Version 7.0.   //EI20100802: entspricht Kids/Uids V21
	 */
    EXP0700, EX0700, EXP07000, EX07000,
    
	/**
	 * Schweiz: Verfahrenskürzel entsprechend der FSS-Verzeichnisse FSSINVCH und FSSOUVCH.
	 */
	VCH0600,
	VCH0620,
	VCH0640,
	
	/**
	 * NCTS Version 6.2 (Needs to confirm if this Procedure Prefix are correct).
	 */
	EX0620, 
	VE0620, VE0600, VE0500,
	VE0700, VE07000,
	
	/**
	 * Import     //EI20110912
	 */
	ZB0600,       //EI20121129: IMxxx umbenannt in ZBxxx, so wie andere Verfahren    
	ZB0620,
	ZB0640,
	ZB0700, ZB07000,
	
	/**
	 * PORT
	 */
	ZP0600, ZP0640, ZP0650, ZP0700, ZP07000,
	
	/**
	 * Manifest / SumA     // C.K. 2012-11
	 */
	SU0620, SU0600, SU0640, SU0650,
	SU0700, SU07000;
}
