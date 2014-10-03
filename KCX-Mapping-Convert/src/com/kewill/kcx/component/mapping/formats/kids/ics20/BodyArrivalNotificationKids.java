package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 25.10.2012<br>
 * Description  : Contains Message Structure with fields used in ICSArrivalNotification (IE347).
 * 				: new for V20: OfficeOfNotification, GoodsItem.EsumaReference(changed structure)
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyArrivalNotificationKids extends KidsMessageICS20 {

	private MsgArrivalNotification message;	

    public BodyArrivalNotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgArrivalNotification getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalNotification argument) {
		this.message = argument;
	}
	
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSArrivalNotification");
            openElement("GoodsDeclaration");  
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());                 
                writeElement("TotalNumberPositions", message.getTotalNumberPositions()); 
                writeElement("TotalNumberPackages", message.getTotalNumberPackages()); 
                writeElement("TotalGrossMass", message.getTotalGrossMass()); 
                writeTransportMeansBorder(message.getMeansOfTransportBorder());            	
                writeElement("ConveyanceReference", message.getConveyanceReference());	  
	            writeFormattedDateTime("DateOfPresentation", message.getDateOfPresentation(),
	                    	message.getDateOfPresentationFormat(), EFormat.KIDS_DateTime);
	            writeFormattedDateTime("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
	                    	message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_DateTime);                      
                if (message.getCarrier() != null) {
                	writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                	writePartyAddress("Carrier", message.getCarrier());
                	writeContactPerson("Carrier", message.getCarrier().getContactPerson());
                }
                writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                writeElement("CountryOfficeFirstEntry", message.getCountryOfficeFirstEntry()); 
                writeElement("OfficeOfNotification", message.getOfficeOfNotification());    //new for V20
                
                if (message.getGoodsItemList() != null) {
	               for (GoodsItemArn goodsItem : message.getGoodsItemList()) {
	                    writeGoodsItem(goodsItem); 
	               }
                }
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    public void writeGoodsItem(GoodsItemArn goodsItem) throws XMLStreamException {		
    	if (goodsItem == null) {
    		return;
    	}
    	if (Utils.isStringEmpty(goodsItem.getItemNumber()) &&
    		goodsItem.getDocument() == null &&
    		goodsItem.getEsumaDataReferenceList() == null) {    		
    	    return;
    	}
    	
    	openElement("GoodsItem");             	                    	   
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	writeDocument(goodsItem.getDocument());
	    	
    	    if (goodsItem.getEsumaDataReferenceList() != null) {              //new V20: changed structure  	    		
    	    	for (EsumaDataReference data : goodsItem.getEsumaDataReferenceList()) {
    	    		writeEsumaDataReference(data);    	    	
    	    	}
    	    }    	   
	    closeElement();
	}
    
}
