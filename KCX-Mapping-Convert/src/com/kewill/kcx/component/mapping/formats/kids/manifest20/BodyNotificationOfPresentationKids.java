package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 11.06.2013<br>
 * Description : BodyNotificationOfPresentationKids
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyNotificationOfPresentationKids extends KidsMessageManifest20 {

	private MsgNotificationOfPresentation message;	

    public BodyNotificationOfPresentationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgNotificationOfPresentation getMessage() {
		return message;
	}
	
	public void setMessage(MsgNotificationOfPresentation argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("NotificationOfPresentation");
            openElement("GoodsDeclaration"); 
            	
            	writePartyTIN("Agent", message.getAgentTIN());            	              
            	writeContact(message.getContact());
            	writeDateTimeToString("DateOfPresentation", message.getDateOfPresentation());  
                writeElement("TypeOfPresentation", message.getTypeOfPresentation());            	
                writeParty("Presenter", message.getPresenter());                      
                writeElement("MessageFunction", message.getMessageFunction());
                writeElement("Comments", message.getComments());
                writeElement("ReferenceNumber", message.getReferenceNumber());        
                writeElement("ReferenceIdentifier", message.getReferenceIdentifier());
                writeElement("RegistrationNumber", message.getRegistrationNumber()); 
                writeElement("RegistrationNumberAllocated", message.getRegistrationNumberAllocated());
                writeElement("ItemNumberAllocated", message.getItemNumberAllocated());
                writeElement("NCTSID", message.getNctsID());   
                writeCustomsOffices(message.getCustomsOffices());                   
            	//writeElement("FlightNumber", message.getFlightNumber());            	            	            	         
            	writeElement("PlaceOfLoading", message.getPlaceOfLoading());
            	writeElement("Port", message.getPort());
            	writeDateTimeToString("DateOfArrival", message.getDateOfArrival());  
                writeTransport("MeansOfTransport", message.getTransport());  
                writeTransport("MeansOfTransportAtBorder", message.getTransportAtBorder()); 
            	writeElement("ContainerQuantity", message.getContainerQuantity());    
            	writePreviousDocument(message.getPreviousDocument());        	             
            	writeReference(message.getReference());              	
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
        		writeParty("Disposal", item.getDisposal());     //Verfuegungsberechtigter               
        		writeParty("Custodian", item.getCustodian());   //Verwahrer
        		//writeElement("BriefCargoDescription", item.getBriefCargoDescription());
        		writeElement("ItemDescription", item.getItemDescription());
        		writeElement("ConfirmationCode", item.getConfirmationCode());
        		writeElement("CustomsStatusOfGoods", item.getCustomsStatusOfGoods());
        		writeElement("CountryOfDispatch", item.getCountryOfDispatch());
        		writeElement("CountryOfDestination", item.getCountryOfDestination());          		        		        
        		writeElement("DestinationPlace", item.getDestinationPlace());
        		writeElement("GrossMass", item.getGrossMass());        		
        		writePackageList(item.getPackagesList());
        		writeElement("PlaceOfCustodyCode", item.getPlaceOfCustodyCode());        		
        		writeElement("RangeOfGoodsCode", item.getRangeOfGoodsCode());        		
        		//writeElement("ReferenceNumber", item.getReferenceNumber()); <- ist in xsd
        		writeReferencedSpecification("", item.getReferencedSpecification());
        		writeElement("RoutingOfCustoms", item.getRoutingOfCustoms());                               		
        		writeItemExtension(item.getItemExtension());
        		writeCustomsNotification("EntrySumA", item.getCustomsNotification());
        		writeElement("FreezoneFlag", item.getFreezoneFlag());
        		
             closeElement();
    	} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
		
	}
	
}

