package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryReleaseRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyEntryReleaseRejectionUids.<br>
 * Created		: 2011.02.03<br>
 * Description	: UIDS Body format of ICSEntryReleaseRejection
 * 				: (KIDS: ICSEntryReleaseRejection, IE322) 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyEntryReleaseRejectionUids extends UidsMessageICS {

	private MsgEntryReleaseRejection message;	

    public BodyEntryReleaseRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEntryReleaseRejection getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntryReleaseRejection argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit"); 
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911");
        	openElement("ICS");
        	//EI20121025: openElement("ICSEntryRelease");
    		openElement("ICSEntryReleaseRejection");
    		
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());            	
            	writeElement("CommercialReferenceNumber", message.getShipmentNumber());            
            	writeElement("MRN", message.getMrn()); 
            	writeElement("ConveyanceNumber", message.getConveyanceReference());
            	writeTrader("EntryCarrier", message.getCarrier());            	           
            	writeElement("OfficeOfActualEntry", message.getCustomsOfficeOfActualEntry()); 
            	if (message.getMotivation() != null && !message.getMotivation().isEmpty()) {
            		openElement("Motivation");
            			writeElement("Code", message.getMotivation().getCode()); 
            			writeElement("Text", message.getMotivation().getText()); 
            		closeElement();            	
            	}
            	writeFormattedDateTime("ControlDate", message.getControlDate(),
            			message.getControlDateFormat(), EFormat.ST_DateTimeTZ);	
            	
            	if (message.getGoodsItemList() != null) {
 		           for (GoodsItemShort item : message.getGoodsItemList()) {
 		                writeGoodsItemShort(item);
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
  
}
