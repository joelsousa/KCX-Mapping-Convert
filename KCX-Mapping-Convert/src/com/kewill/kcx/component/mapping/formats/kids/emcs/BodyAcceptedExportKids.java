package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgAcceptedExport;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSAcceptedExport Kids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyAcceptedExportKids extends KidsMessageEMCS {

	private MsgAcceptedExport msgAcceptedExport;	
	private String version = "";         //EI20110819

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            	openElement("EMCS");
                //openElement(this.kidsMessageName);
                    openElement("EMCSAcceptedExport");
                    
                    	writeElement("ReferenceNumber", msgAcceptedExport.getReferenceNumber());
                    	writeElement("CustomerOrderNumber", msgAcceptedExport.getCustomerOrderNumber());
                    	writeElement("Clerk", msgAcceptedExport.getClerk());  
                    	writeExportAcceptance(msgAcceptedExport.getExportAcceptance());
                        if (version.equalsIgnoreCase("10")) {      //EI20110819
                        	writeElement("ExportPlaceCustomsOffice", msgAcceptedExport.getExportPlaceCustomsOffice());
                        } else {
                        	writeElement("DeliveryPlaceCustomsOffice", msgAcceptedExport.getExportPlaceCustomsOffice());
                        }
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
