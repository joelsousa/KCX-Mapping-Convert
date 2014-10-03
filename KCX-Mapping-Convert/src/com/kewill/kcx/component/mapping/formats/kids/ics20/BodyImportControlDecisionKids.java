package com.kewill.kcx.component.mapping.formats.kids.ics20;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgImportControlDecision;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;

/**
 * Module	 	: ICS20<br> 
 * Create		: 24.10.2012<br>
 * Description  : Body of MsgImportControlDecision (IE361).
 * 
 * @author 	iwaniuk
 * @version 2.0.00
 */
public class BodyImportControlDecisionKids extends KidsMessageICS20 {

	private MsgImportControlDecision message;	

    public BodyImportControlDecisionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportControlDecision getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportControlDecision argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSImportControlDecisionNotification");
            openElement("GoodsDeclaration"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber()); 
            	writeElement("MRN", message.getMrn());
            	writeElement("TotalNumberPosition", message.getTotalNumberPosition());
                writeElement("ShipmentNumber", message.getShipmentNumber());
                writeElement("ConveyanceReference", message.getConveyanceReference());
                	
                 if (message.getCarrier() != null) {
                    writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                    writePartyAddress("Carrier", message.getCarrier());
                 }
                 writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                 
                 if (message.getCustomsInterventionList() != null) {
	 		           for (CustomsIntervention item : message.getCustomsInterventionList()) {
	 		                writeCustomsInterventions(item);
	 		           }
	 	          }
                 if (message.getGoodsItemList() != null) {
	 		           for (GoodsItemShort item : message.getGoodsItemList()) {
	 		                writeGoodsItemShort(item);
	 		           }
	 	          }
	              	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    public void  writeSealsId(List<SealNumber> argument) throws XMLStreamException {
       	if (argument == null) {
    	    return;
    	} 
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	openElement("SealsID");
			for (SealNumber seals : argument) {
				writeElement("SealsIdentity", seals.getNumber());
				writeElement("SealsIdentityLng", seals.getLanguage());
			}
		closeElement();
    	
    }  
   
}
