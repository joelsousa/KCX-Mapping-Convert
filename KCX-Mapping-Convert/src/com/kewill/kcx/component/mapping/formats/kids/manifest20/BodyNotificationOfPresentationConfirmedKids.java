package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentationConfirmed;
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
public class BodyNotificationOfPresentationConfirmedKids extends KidsMessageManifest20  {

	private MsgNotificationOfPresentationConfirmed message;	

    public BodyNotificationOfPresentationConfirmedKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgNotificationOfPresentationConfirmed getMessage() {
		return message;
	}
	
	public void setMessage(MsgNotificationOfPresentationConfirmed argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("NotificationOfPresentationConfirmed");
            openElement("GoodsDeclaration");     
            
            	writePartyTIN("Agent", message.getAgentTIN());            	
                writeContact(message.getContact());
                writeDateTimeToString("DateOfPresentation", message.getDateOfPresentation());
                writeParty("Presenter", message.getPresenter());                   
                //writeElement("FlightNumber", message.getFlightNumber()); jetzt nur TransportationNumber aus MeansOfTransport
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("ReferenceIdentifier", message.getReferenceIdentifier());                
                writeElement("NCTSID", message.getNctsID());   
                writeCustomsOffices(message.getCustomsOffices());
                writeElement("PlaceOfLoading", message.getPlaceOfLoading());
                writeDateTimeToString("DateOfArrival", message.getDateOfArrival());
                writeTransport("MeansOfTransport", message.getTransport());
                writeTransport("MeansOfTransportAtBorder", message.getTransportAtBorder()); 
            	writeReference(message.getReference()); 
                writeElement("BriefCargoDescription", message.getBriefCargoDescription());
            	writeElement("ContainerQuantity", message.getContainerQuantity());
            	writeElement("GrossMass", message.getGrossMass());            	
            	writePreviousDocument(message.getPreviousDocument());        	                         	             	               
                writeHeaderExtensions(message.getHeaderExtensions());
                writeElement("CustomsOfficeIsOfficeOfFirstEntry", message.getSumACustomsOfficeIsOfficeIsOfFirstEntry());               
                                
                if (message.getGoodsItemList() != null) {
                	for (GoodsItem item : message.getGoodsItemList()) {
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

    private void writeGoodsItem(GoodsItem item) {
    	if (item == null) {
    		return;
    	}
    	try {
        	openElement("GoodsItem");        	
        		writeElement("ItemNumber", item.getItemNumber());        		
        		writeParty("Disposal", item.getDisposal());                     
        		writeParty("Custodian", item.getCustodian());
        		//writeElement("BriefCargoDescription", goodsItem.getBriefCargoDescription()); 
        		writeElement("ItemDescription", item.getItemDescription());
        		writeElement("ConfirmationCode", item.getConfirmationCode());
        		writeElement("CustomsStatusOfGoods", item.getCustomsStatusOfGoods());
        		writeElement("CountryOfDestination", item.getCountryOfDestination());  
        		writeElement("CountryOfDispatch", item.getCountryOfDispatch());        		        		
    			writeElement("DestinationPlace", item.getDestinationPlace());
    			writeElement("GrossMass", item.getGrossMass());        		
    			writePackageList(item.getPackagesList());
    			writeAddress("PlaceOfCustody", item.getPlaceOfCustody());
    			writeElement("PlaceOfCustodyCode", item.getPlaceOfCustodyCode());        		
    			writeElement("RangeOfGoodsCode", item.getRangeOfGoodsCode());     
    			writeElement("RangeOfGoodsDescription", item.getRangeOfGoodsDescription());     
    			writeReferencedSpecification("", item.getReferencedSpecification());    		                           
    			writeItemExtension(item.getItemExtension());
    			writeCustomsNotification("EntrySumA", item.getCustomsNotification());
    			
             closeElement();
    	} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
		
	}
	
}

