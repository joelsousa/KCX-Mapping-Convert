package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntryReleaseRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;

/**
 * Module	    : ICS20<br> 
 * Created		: 24.10.2012<br>
 * Description  : Body of ICSEntryReleaseRejection in KIDS format.<br>
 *              : (IE322)
 *              : new V20: ConveyanceReference, EntryRejectionMotivation, ControlResultDate, ActualOfficeOfFirstEntry
 *               
 * @author krzoska
 * @version 2.0.00
 */
public class BodyEntryReleaseRejectionKids extends KidsMessageICS20 {

	private MsgEntryReleaseRejection message;	

    public BodyEntryReleaseRejectionKids(XMLStreamWriter writer) {
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
            openElement("ICSEntryReleaseRejection");
            openElement("GoodsDeclaration"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("MRN", message.getMrn());
                writeElement("ShipmentNumber", message.getShipmentNumber());                
                writeElement("ConveyanceReference", message.getConveyanceReference());  //new V20
                writeText("EntryRejectionMotivation", message.getMotivation());         //new V20
                writeElement("ControlResultDate", message.getControlDate());            //new V20
                writeElement("ActualOfficeOfFirstEntry", message.getCustomsOfficeOfActualEntry()); //new V20                
                if (message.getCarrier() != null) {
                    writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                    writePartyAddress("Carrier", message.getCarrier());
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
}
