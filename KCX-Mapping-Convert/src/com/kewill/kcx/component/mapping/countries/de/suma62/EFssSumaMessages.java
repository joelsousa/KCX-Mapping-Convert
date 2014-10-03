package com.kewill.kcx.component.mapping.countries.de.suma62;


/**
 * Module		: EFssSumaMessages
 * Created   	: 06.11.2012
 * Description	: Valid Names of FSS-message names in Zabis SumA (Manifest).
 * 
 * @author Christine Kron
 * @version 06.20
 */
public enum EFssSumaMessages {
		
	/**
	    * Functional name:  CUSTST
	    * Technical Name:   IE25
	    * Description (en): GoodsReleasedExternal
	    * Description (de): R�ckgabe Verwahrmitteilung aus NCTS 
	    * Direction:        Customs to customer.
	*/
	EVK,
		
	/**
	    * Functional name:  CUSTST
	    * Technical Name:   SCTSTE
	    * Description (en): GoodsReleasedInternal
	    * Description (de): Verwahrmitteilung
	    * Direction:        Customs to customer.
	*/
	SWV, //GoodsReleasedInternal, Verwahrmitteilung
	
	/**
	    * Functional name:  CUSREC
	    * Technical Name:   SCTREC
	    * Description (en): ProcessingResults
	    * Description (de): Verwahrmitteilung
	    * Direction:        Customs to customer.
	*/
	SCK,   //ProcessingResults
	
	ERR,
	CON,
	STI;	

}
