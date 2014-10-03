package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 17.09.2012<br>
 * Description   : Uids Body of ManualTerminatio.
 * 				 : V21: only NameSpase is changed: written from CommonFieldsDTO

 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExpManualTerminationUids extends UidsMessage {	

    private MsgExpErl  message = new MsgExpErl();
 
    public BodyExpManualTerminationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpErl getMsgKids() {
		return message;
	}

	public void setMessage(MsgExpErl message) {
		this.message = message;
	}
    
	public void writeBody() {
				
        try {
        	openElement("soap:Body");
            openElement("Submit");
                //EI20120917:           	             
            	if (this.getCommonFieldsDTO() != null && 
            		!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) { 
            		setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
            	} else {
            		setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	}
            	
                openElement("Export");
                openElement("ManualTerminationExport");                                              
                    writeContact(message.getContact()); 
                    writeStringToDateTime("DateOfTermination", message.getTerminationTime());                   		                 	
                	writeHeaderExtensions(message.getSeal(), "ExpErl");   
                	writeElement("ReferenceNumber", message.getReferenceNumber());                  		
                	writeElement("Remark", message.getAnnotation());	                		
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }	
}    	
    	
    	
