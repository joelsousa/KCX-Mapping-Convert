package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntryReleaseRejection;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20.<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS Body format of ICSEntryReleaseRejection
 * 				: (KIDS: ICSEntryReleaseRejection, IE322) 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyEntryReleaseRejectionUids extends UidsMessageICS20 {

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
    		openElement("ICSEntryReleaseRejection");
    		
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());            	
            	writeElement("CommercialReferenceNumber", message.getShipmentNumber());            
            	writeElement("MRN", message.getMrn()); 
            	writeElement("ConveyanceNumber", message.getConveyanceReference());
            	writeTrader("EntryCarrier", message.getCarrier());            	           
            	writeElement("OfficeOfActualEntry", message.getCustomsOfficeOfActualEntry()); 
            	writeText("Motivation", message.getMotivation());            		
            	writeFormattedDateTime("ControlDate", message.getControlDate(),
            			message.getControlDateFormat(), EFormat.ST_DateTimeTZ);	
            	
            	if (message.getGoodsItemList() != null) {
 		           for (GoodsItemShort item : message.getGoodsItemList()) {
 		                writeGoodsItemShort(item, "IE322");
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
