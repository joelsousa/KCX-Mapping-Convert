package com.kewill.kcx.component.mapping.formats.kids.manifest;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest;

/**
 * Module	   : Manifest.<br>
 * Created	   : 12.12.2012<br>
 * Description : BodyNotificationOfPresentationKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyNotificationOfPresentationKids extends KidsMessageManifest {

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
                writeElement("AgentTin", message.getAgentTIN());
                //EI20130524: writeContact("Contact", message.getContact());
                writeContact(message.getContact());
                writeTrader("Presenter", message.getPresenter());                                                      
                writeElement("TypeOfPresentation", message.getTypeOfPresentation());
            	//writeElement("DateOfPresentation", message.getDateOfPresentation()); 
                writeDateTimeToString("DateOfPresentation", message.getDateOfPresentation());  
                writeElement("MessageFunction", message.getMessageFunction());
                writeElement("Comments", message.getComments());
                writeElement("ReferenceNumber", message.getReferenceNumber());        
                writeElement("ReferenceIdentifier", message.getReferenceIdentifier());
                writeElement("RegistrationNumber", message.getRegistrationNumber()); 
                writeElement("RegistrationNumberAllocated", message.getRegistrationNumberAllocated());
                writeElement("NCTSID", message.getNctsID());   
                writeCustomsOffices(message.getCustomsOffices());                    
            	writeElement("FlightNumber", message.getFlightNumber());
            	writeElement("ItemNumberAllocated", message.getItemNumberAllocated());            	            	         	
            	writeElement("PlaceOfLoading", message.getPlaceOfLoading());
            	writeElement("Port", message.getPort());
            	writeElement("ContainerQuantity", message.getContainerQuantity());    
            	writePreviousDocument(message.getPreviousDocument());        	             
            	writeReference(message.getReference());  
                writeTransport(message.getTransport());                
                writeHeaderExtensions(message.getHeaderExtensions());
                                
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
        		writeElement("DisposalTIN", item.getDisposalTIN());                     //warum war es auskommentiert?
        		writeElement("CustodianTIN", item.getCustodianTIN());
        		writeElement("BriefCargoDescription", item.getBriefCargoDescription());
        		writeElement("ConfirmationCode", item.getConfirmationCode());
        		writeElement("CountryOfDestination", item.getCountryOfDestination());  //warum war es auskommentiert?
        		writeElement("CountryOfDispatch", item.getCountryOfDispatch());        		
        		writeElement("CustomsStatusOfGoods", item.getCustomsStatusOfGoods());
        		writeElement("DestinationCode", item.getDestinationCode());
        		writeElement("GrossMass", item.getGrossMass());
        		writePackaging(item.getPackaging());        		
        		writeElement("PlaceOfCustodyCode", item.getPlaceOfCustodyCode());        		
        		writeElement("RangeOfGoodsCode", item.getRangeOfGoodsCode());        		
        		//writeElement("ReferenceNumber", item.getReferenceNumber()); <- ist in xsd
        		writeReferencedSpecification("", item.getReferencedSpecification());
        		//writeElement("RoutingOfCustoms", );                         <- ist in xsd
        		writeTrader("Custodian", item.getCustodian());
        		writeTrader("Disposal", item.getDisposal());
        		writeItemExtension(item.getItemExtension());
             closeElement();
    	} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
		
	}
	
}

