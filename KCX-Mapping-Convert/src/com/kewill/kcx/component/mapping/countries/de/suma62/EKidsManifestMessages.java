package com.kewill.kcx.component.mapping.countries.de.suma62;

public enum EKidsManifestMessages {

	
	/**
	    * Functional name:  CUSTST (FSS-EVK)  
	    * Technical Name:   IE25
	    * Description (en): GoodsReleasedExternal
	    * Description (de): Rückgabe Verwahrmitteilung aus NCTS-IN 
	    * Direction:        Customs to customer.
	*/
	GoodsReleasedExternal,		
	
	/**
	    * Functional name:  CUSTST (FSS-SWV)
	    * Technical Name:   SCTSTE
	    * Description (en): GoodsReleasedInternal
	    * Description (de): Verwahrmitteilung
	    * Direction:        Customs to customer.
	*/
	GoodsReleasedInternal,	
	
	/**                                                       //EI20130211
	    * Functional name:  CUSREC (FSS-SCK)
	    * Technical Name:   CUSREC
	    * Description (en): ProcessingResults
	    * Description (de): Verwahrmitteilung
	    * Direction:        Customs to customer.
	*/
	ProcessingResults,
		
	////////////////
	/**
	    * Functional name: 
	    * Technical Name:   
	    * Description (en): NotificationOfPresentation
	    * Description (de): 
	    * Direction:        Customer to customs.
	*/
	
	NotificationOfPresentation,
	ErrorMessage,
	localAppResult;       
}
