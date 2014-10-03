package com.kewill.kcx.component.mapping.formats.kids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AadVal;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.FunctionalError;

/**
 * Modul		: EMCS<br>
 * Created		: 04.05.2010<br>
 * Description	: Fields and methods to write EMCS-KidsMessages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsMessageEMCS20  extends KidsMessageEMCS { 	
 	
    public void  writeFunctionalErrorAadList(List<AadVal> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	    	
    	for (AadVal aad : list) {	                        		   	    			
    		writeFunctionalErrorAad(aad);   
		}		    	
    }  
    
    public void  writeFunctionalErrorAad(AadVal argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}    
    	openElement("FunctionalError");
    	    writeElement("AadReferenceCode", argument.getAadReferenceCode());
    	    writeElement("SequenceNumber", argument.getSequenceNumber());    	   
		closeElement(); 	    	
    }
    
    public void  writeFunctionalErrorList(List<FunctionalError> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	    	
    	for (FunctionalError err : list) {	                        		   	    			
    		writeFunctionalErrorCT(err);   
		}		    	
    }
    
    public void  writeFunctionalErrorCT(FunctionalError argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}        	
    	openElement("FunctionalError");    		    		
    		//writeCodeElement("ErrorType", argument.getErrorType(), "A0049");     	//V20 new KCX-Code	
    		writeElement("ErrorType", argument.getErrorType());
			writeElement("ErrorReason", argument.getErrorReason());
			writeElement("ErrorLocation", argument.getErrorLocation());
			writeElement("OriginalAttributeValue", argument.getOriginalAttributeValue());
		closeElement(); 	    	
    }   
    
}

