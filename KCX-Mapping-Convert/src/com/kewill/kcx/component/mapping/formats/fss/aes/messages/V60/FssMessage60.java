package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

/*
 * Funktion    : FssMessage60.java
 * Titel       :
 * Date	       : 31.03.2009
 * Author      : Kewill CSF / Alfred Krzoska
 * Description : lists used in mappings Kids to FSS 
 *
 *
 */

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.util.Utils;

public class FssMessage60 {
	public String appendString ( String in, String append) throws FssException {
		return in + append + Utils.LF;
	}
	
	 // from Kids to Fss begin 
    public List <TsACO> createACOList( List <Packages> list, String referenz, String posnr ) {
		if (list == null) return null; 
		int size = list.size();		
		TsACO aco = null;
	    List <TsACO>acoList = new Vector<TsACO>();

	    	for (int i = 0; i < size; i++) {	   
				aco = new TsACO();
				aco.setAcoSubset(list.get(i), referenz, posnr); 							 		
				acoList.add(aco);
			}
			
			return acoList; 
	}   
    
    // List of containers
    public List <TsACN> createACNList( List <String> list, String referenz, String posnr ) {
		if  (list == null ) return null;
		int size = list.size();		
		TsACN acn = null;
	    List <TsACN>acnList = new Vector<TsACN>();

	    	for (int i = 0; i < size; i++) {	   
				acn = new TsACN();
				acn.setAcnSubset(list.get(i), referenz, posnr);
				acnList.add(acn);
			}
			
			return acnList; 
	}
   
	public List <TsAZV> createAZVList( List <PreviousDocument> list, String referenz, String posnr  ) {
		if  (list == null ) return null;
		int size = list.size();		
		TsAZV azv = null;
	    List <TsAZV>azvList = new Vector<TsAZV>();

	    	for (int i = 0; i < size; i++) {	   
				azv = new TsAZV();
				azv.setAzvSubset(list.get(i), referenz, posnr ); 							 		
				azvList.add(azv);
			}
			
			return azvList; 
	}


	public List <TsAED>createAEDList( List  <Document> list, String referenz, String posnr  ) {
		if (list == null) return null;
		int size = list.size();	
		TsAED aed = null;
		List <TsAED>aedList = new Vector<TsAED>();
		
    	for (int i = 0; i < size; i++) {	   
			aed = new TsAED();
			aed.setAedSubset(list.get(i), referenz, posnr ); 							 		
			aedList.add(aed);
		}
		
		return aedList; 	
	}
	// from Kids to Fss end  	
}
