package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgAcceptedExport;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSAcceptedExport Kids message.
 *              : V20 Tag ExportPlaceCustomsOffice renamed in DeliveryPlaceCustomsOffice
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyAcceptedExportKids extends KidsMessageEMCS {

	private MsgAcceptedExport msgAcceptedExport;	
	
    public BodyAcceptedExportKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgAcceptedExport getMessage() {
		return msgAcceptedExport;
	}
	public void setMessage(MsgAcceptedExport argument) {
		this.msgAcceptedExport = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");           
            openElement("EMCSAcceptedExport");
                    
            	writeElement("ReferenceNumber", msgAcceptedExport.getReferenceNumber());
                writeElement("CustomerOrderNumber", msgAcceptedExport.getCustomerOrderNumber());
                writeElement("Clerk", msgAcceptedExport.getClerk());  
                writeExportAcceptance(msgAcceptedExport.getExportAcceptance());                 
                //writeElement("ExportPlaceCustomsOffice", msgAcceptedExport.getExportPlaceCustomsOffice());                      
                writeElement("DeliveryPlaceCustomsOffice", msgAcceptedExport.getExportPlaceCustomsOffice()); //V20 renamed                       
                writeDateTimeToString("DateAndTimeOfIssuance", msgAcceptedExport.getDateAndTimeOfIssuance());
                writeTrader("Consignee", msgAcceptedExport.getConsignee());  
                writeExciseMovementEaadList(msgAcceptedExport.getExciseMovementEaadList());
                                             
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}
