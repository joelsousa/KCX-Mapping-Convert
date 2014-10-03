/**
 * Function    : BodyArrivalNotificationKids.java
 * Titel       :
 * Date        : 16.06.2010
 * Author      : Pete T
 * Description : 
 * Version 	   : 1.0
 * Parameters  :
 */

package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDetails;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: BodyArrivalNotificationKids<br>
 * Erstellt		: 16.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used in ICSArrivalNotification.
 *                 
 * @author Pete T
 * @version 1.0.00
 * 
 * changes: EI20121025: removed unused import: ...de.ics.msg.common.GoodsItem
 */
public class BodyArrivalNotificationKids extends KidsMessageICS {

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
                //writeElement("ShipmentNumber", message.getShipmentNumber()); 
                //writeElement("MRN", message.getMrn()); 
                writeElement("TotalNumberPositions", message.getTotalNumberPositions()); 
                writeElement("TotalNumberPackages", message.getTotalNumberPackages()); 
                writeElement("TotalGrossMass", message.getTotalGrossMass()); 
                writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
                //writeElement("ConveyanceNumber", message.getConveyanceReference());	 
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
                    	
                if (message.getGoodsItemList() != null) {
	               for (GoodsItemArn goodsItem : message.getGoodsItemList()) {
	                    writeGoodsItem("GoodsItem", goodsItem); //FT2010.07.16
	               }
                }
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    public void writeGoodsItem(String tag, GoodsItemArn goodsItem) throws XMLStreamException {		//FT2010.07.16
    	if (goodsItem == null) {
    		return;
    	}
    	if (Utils.isStringEmpty(goodsItem.getItemNumber()) &&
    		goodsItem.getDocument() == null &&
    		goodsItem.getEsumaDataReferenceList() == null) {    		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
	    	writeElement("ItemNumber", goodsItem.getItemNumber());	    	
	    	if (goodsItem.getDocument() != null) {	    		
	    		openElement("Document");
	    			writeCodeElement("Type", goodsItem.getDocument().getType(), "KCX0035");   //
	    			writeElement("Reference", goodsItem.getDocument().getReference());
	    			writeElement("ReferenceLng", goodsItem.getDocument().getReferenceLng());
	    		closeElement();
	    	}
    	    
    	    if (goodsItem.getEsumaDataReferenceList() != null) {    	    		
    	    	for (EsumaDataReference esumaDataReference : goodsItem.getEsumaDataReferenceList()) {
	    	    	openElement("EsumaDataReference");
			    		writeElement("MRN", esumaDataReference.getMrn());
			    		writeElement("CountryOfficeFirstEntry", esumaDataReference.getCountryOfficeFirstEntry());
			    		if (esumaDataReference.getItemNumberEsumaLists() != null) {
				    		for (EsumaDetails itemNumberEsuma : esumaDataReference.getItemNumberEsumaLists()) {
				    			openElement("EsumaDetails");
					    			writeElement("ItemNumberEsuma", itemNumberEsuma.getItemNumberEsuma());
					    	    closeElement();
				    		}
			    		}
		    	    closeElement();
    	    	}
    	    }
    	    
	    closeElement();
	}
    
}
