/*
 * Function    : MapNCTSDeclarationRejectedToVFO.java
 * Date        : 31.08.2010
 * Author      : Kewill CSF / Elisabeth Iwaniuk
 * Description : Mapping of KIDS format of NCTSDeclarationRejected to FSS format VFO

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapNCTSDeclarationRejectedToVAN<br>
 * Erstellt		: 31.08.2010<br>
 * Beschreibung	: Mapping of KIDS format of NCTSDeclarationRejected to FSS format VFO.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapNCTSDeclarationRejectedToVFO extends KidsMessage {
	
	private MsgNCTSDeclarationRejected msgNCTSDeclarationRejected;
	private MsgVFO msgVFO;
	
	/// CONSTRUCTOR(s)
		public MapNCTSDeclarationRejectedToVFO(XMLEventReader parser, 
				TsVOR tsvor) throws XMLStreamException {		
			msgNCTSDeclarationRejected = new MsgNCTSDeclarationRejected(parser);
			msgVFO = new MsgVFO();
			msgVFO.setVorSubset(tsvor);
		}
	
	/// METHODS
		public String getMessage() {
	    	String res = "";
	    	
	        try {        
	        	msgNCTSDeclarationRejected.parse(HeaderType.KIDS);                      
	            getCommonFieldsDTO().setReferenceNumber(msgNCTSDeclarationRejected.getReferenceNumber());
	                                                          
	            // read MessageID from KidsHeader
	            	msgVFO.getVorSubset().setMsgid(getKidsHeader().getMessageID());
	            
	            // Setting all Ts in MsgVFO
		            msgVFO.setVfoSubset(setVfo());
		            
	                if (!msgNCTSDeclarationRejected.getErrorList().isEmpty()) {
		            	for (FunctionalErrorNcts err : msgNCTSDeclarationRejected.getErrorList()) {
		            		msgVFO.addVfpSubsetList(setVfp(err));
		            	}
		            }
		        
	            res = msgVFO.getFssString();
	           
	            Utils.log("(MapNCTSDeclarationRejectedToVFO getMessage) Msg = " + res);
			
		    } catch (FssException e) {	    	
		        e.printStackTrace();
		    }
			    
		    return res;
		}
		
		private TsVFO setVfo() {
			TsVFO vfoSubset	= new TsVFO();
			
			vfoSubset.setBeznr(msgNCTSDeclarationRejected.getReferenceNumber());
			vfoSubset.setMrn(msgNCTSDeclarationRejected.getUcrNumber());
			//vfoSubset.setFregnr("");		no assigned field
			
			return vfoSubset;
		}
		
		private TsVFP setVfp(FunctionalErrorNcts err) {
			TsVFP vfpSubset	= new TsVFP();
			
			vfpSubset.setBeznr(msgNCTSDeclarationRejected.getReferenceNumber());
			vfpSubset.setErrcde(err.getCode());
			vfpSubset.setErrtxt(err.getText());
			vfpSubset.setZeiger(err.getPointer());
			//vfpSubset.setLfdnr("");	no assigned field
			
			return vfpSubset;
		}
	
}

