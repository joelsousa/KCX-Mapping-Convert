package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExportRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS.<br>
 * Created 		: 18.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSExportRejection,
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyExportRejectionKids extends KidsMessageEMCS {

	private MsgExportRejection message;	
	private String version = "";         //EI20110819

    public BodyExportRejectionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExportRejection getMessage() {
		return message;
	}
	public void setMessage(MsgExportRejection argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");
            //openElement(this.kidsMessageName);
            
            openElement("EMCSExportRejection");                      
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk()); 
                if (!version.equalsIgnoreCase("10")) {                
                	writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());  //EI20111111
                }
                writeDateTimeToString("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance()); 
                writeElement("DocumentReferenceNumber", message.getDocumentReferenceNumber());  
                writeElement("CancelExport", message.getCancelExport());                          
                writeDiagnosisList(message.getDiagnosisList()); 
                writeElement("RejectionReasonCode", message.getRejectionReasonCode());  
                writeDateTimeToString("RejectionDateAndTime", message.getRejectionDateAndTime()); //(EI20100630
                if (!version.equalsIgnoreCase("10")) {
                	writeTrader("Consignee", message.getConsignee());    //EI20110819
                }               
                writeFunctionalErrorAadList(message.getFunctionalErrorList(), version);
           closeElement();
                    
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}
