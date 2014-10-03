package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNot;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNotPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
/*
 * Funktion    : BodyExitNotificationKU.java
 * Titel       :
 * Erstellt    : 26.09.2008
 * Author      : CSF GmbH / Krzoska
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Exit-Notification) kiff-export.xls
 * Anmerkungen : 
 *
 * Änderungen:
 * -----------
 * Author      : EI
 * Datum       : 23.04.2009 
 * Beschreibung: MsgKids replaced with MsgExtNot
 *
 */
public class BodyExitNotificationUids extends UidsMessage {	

    private MsgExtNot  msgExtNot = new MsgExtNot();
    
    public BodyExitNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExtNot getMsgKids() {
		return msgExtNot;
	}

	public void setMessage(MsgExtNot argument) {
		this.msgExtNot = argument;
	}

	public void writeBody() {
		
        try {
        	openElement("soap:Body");
               openElement("Submit");
               // C.K. Namespace Versionsabhängig setzen
               if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                   setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
               } else {
                   setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
               }
               // setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                 openElement("Export");
                    openElement("ExitNotification");                    
               		writeShipper(msgExtNot.getForwarder().getPartyTIN());                 		             		                
                	writeCustomsOffices("", "", msgExtNot.getRealOfficeOfExit(), msgExtNot.getIntendedOfficeOfExit());
                	writeStringToDateTime("DateOfExit", msgExtNot.getExitOrForwardingTime());
                	writeElement("DocumentReferenceNumber", msgExtNot.getUCRNumber());                	
                	writeElement("ReferenceNumber", msgExtNot.getReferenceNumber());                	
                	writeElement("Termination", msgExtNot.getFinalCode());
                	writeElement("ExternalRegistrationNumber", msgExtNot.getUCROtherSystem());                	
                	
                    if (msgExtNot.getGoodsItemList() != null) {
                        int	listSize = msgExtNot.getGoodsItemList().size();
                       	for (int i = 0; i < listSize; i++) {
                        	writeGoodsItemList((MsgExtNotPos) msgExtNot.getGoodsItemList().get(i));
                       	}  
                    }
                      closeElement(); // MsgName
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
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
    	
    	
