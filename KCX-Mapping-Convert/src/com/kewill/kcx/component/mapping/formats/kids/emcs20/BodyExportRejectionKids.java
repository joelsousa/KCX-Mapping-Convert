package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExportRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS20;

/**
 * Module		: EMCS.<br>
 * Created 		: 18.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSExportRejection,
 *              : V20 new Tags: DeliveryPlaceCustomsOffice, Consignee
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyExportRejectionKids extends KidsMessageEMCS20 {

	private MsgExportRejection message;	
	
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
            openElement("soap:Body");
            openElement("EMCS");           
            openElement("EMCSExportRejection"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());                             
                writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());  //V20 new               
                writeDateTimeToString("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance()); 
                writeElement("DocumentReferenceNumber", message.getDocumentReferenceNumber());  
                writeElement("CancelExport", message.getCancelExport());                          
                writeDiagnosisList(message.getDiagnosisList()); 
                writeElement("RejectionReasonCode", message.getRejectionReasonCode());  
                writeDateTimeToString("RejectionDateAndTime", message.getRejectionDateAndTime()); //EI20100630                
                writeTrader("Consignee", message.getConsignee());    							  //V20 new     
                writeFunctionalErrorAadList(message.getFunctionalErrorList());
       
           closeElement();                    
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}
