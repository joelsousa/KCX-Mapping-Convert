package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgReExport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemReexport;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 04.06.2013<br>
 * Description : BodyNotificationOfPresentationConfirmedKids
 * 
 * @author krzoska
 * @version 2.0.00
 *
 */
public class BodyReExportKids extends KidsMessageManifest20  {

	private MsgReExport message;	

    public BodyReExportKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgReExport getMessage() {
		return message;
	}
	
	public void setMessage(MsgReExport argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ReExport");
            openElement("GoodsDeclaration"); 
            	
            	writeParty("Agent", message.getAgent());            	 
                writeContact(message.getContact());               
                writeElement("ProcedureType", message.getProcedureType());
                //writeElement("FlightNumber", message.getFlightNumber());
                writeElement("PlaceOfUnloading", message.getPlaceOfUnloading());
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("ReferenceIdentifier", message.getReferenceIdentifier());   
                writeElement("TypeOfTransaction", message.getTypeOfTransaction()); 
                writeTransport("MeansOfTransport", message.getTransport());  
                writeFlightDetails(message.getFlightDetails());       //EI20131017
                writeHeaderExtensions(message.getHeaderExtensions());
                                
                if (message.getGoodsItemList() != null) {
                	for (GoodsItemReexport item : message.getGoodsItemList()) {
                		writeGoodsItem(item);
                	}
                }
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

    private void writeGoodsItem(GoodsItemReexport item) {
    	if (item == null) {
    		return;
    	}
    	try {
        	openElement("GoodsItem");        	
        		writeElement("ItemNumber", item.getItemNumber());   
        		writePartyTIN("Custodian", item.getCustodianTIN());
        		writeElement("DestinationPlace", item.getDestinationPlace());        	       		      
    			writePackageList(item.getPackagesList());    			
    			writeReference(item.getReference());    			     		    
    			writeReferencedSpecification("", item.getReferencedSpecification());    	
    			writeElement("ChangeFlag", item.getChangeFlag()); 
    			writeCustomsNotification("CustomsNotification", item.getCustomsNotification());
    			
             closeElement();
    	} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
		
	}
	
}

