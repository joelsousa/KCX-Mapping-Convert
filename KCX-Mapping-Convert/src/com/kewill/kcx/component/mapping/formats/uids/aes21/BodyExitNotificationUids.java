package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNot;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNotPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 17.09.2012<br>
 * Description   : Uids Body of ExitNotificationUids.
 * 				 : V21: only NameSpase is changed: written from CommonFieldsDTO

 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExitNotificationUids extends UidsMessage {	

    private MsgExtNot  message = new MsgExtNot();
    
    public BodyExitNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExtNot getMsgKids() {
		return message;
	}

	public void setMessage(MsgExtNot argument) {
		this.message = argument;
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
                openElement("ExitNotification");                    
               		writeShipper(message.getForwarder().getPartyTIN());                 		             		                
                	writeCustomsOffices("", "", message.getRealOfficeOfExit(), message.getIntendedOfficeOfExit());
                	writeStringToDateTime("DateOfExit", message.getExitOrForwardingTime());
                	writeElement("DocumentReferenceNumber", message.getUCRNumber());                	
                	writeElement("ReferenceNumber", message.getReferenceNumber());                	
                	writeElement("Termination", message.getFinalCode());
                	writeElement("ExternalRegistrationNumber", message.getUCROtherSystem());                	
                	
                    if (message.getGoodsItemList() != null) {                        
                       	for (MsgExtNotPos item : message.getGoodsItemList()) {
                        	writeGoodsItemList(item);
                       	}  
                    }
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }

	private void writeGoodsItemList(MsgExtNotPos argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		openElement("GoodsItem");
			writeElement("ItemNumber", argument.getItemNumber());	
			
			if (argument.getPackagesList() != null) {				
				for (int i = 0, packListSize=argument.getPackagesList().size(); i < packListSize; i++) {
				    writePackaging((Packages) argument.getPackagesList().get(i), "ExitNotification");
				}                		
			}
    	closeElement();
	}
	
}    	
    	
    	
