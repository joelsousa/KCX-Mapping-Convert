package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cuscan;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusfin;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusrec;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusstp;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Custst;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Tufsta;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 12.02.2013<br>
 * Description : BodyCustomsResponseKids - Responses to CMP/LCAG
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyCustomsResponseKids extends KidsMessageManifest20 {

	private MsgCustomsResponse message;	

    public BodyCustomsResponseKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgCustomsResponse getMessage() {
		return message;
	}
	
	public void setMessage(MsgCustomsResponse argument) {
		this.message = argument;
	}
		
    public void writeBody(String messageName) {
        try {
        	//EI20131001: openElement("soap:Body");
            openElement("CustomsResponse");
            if (message.getMessageHeader() != null) {
            	openElement("MessageHeader");             	
            		writeElement("MessageType", message.getMessageHeader().getMessageType());  
            		writeElement("MessageSubType", message.getMessageHeader().getMessageSubType());
            		writeElement("MessageName", message.getMessageHeader().getMessageName());     
            		writeElement("MessageFunction", message.getMessageHeader().getMessageFunction()); 
            		//EI20131021: writeElement("MessageDateTime", message.getMessageHeader().getMessageDateTime());
            		writeDateTimeToString("MessageDateTime", message.getMessageHeader().getMessageDateTime());
            		writeHeaderParty("MessageSender", message.getMessageHeader().getMessageSender());
            		writeHeaderParty("MessageRecipient", message.getMessageHeader().getMessageRecipient());
            		writeElement("MessageVersion", message.getMessageHeader().getMessageVersion());
            		writeElement("MessageReferenceID", message.getMessageHeader().getMessageReferenceID());     
            		writeElement("MessageConversationID", message.getMessageHeader().getMessageConversationID());                   
                closeElement();
            }
            if (message.getMessageBody() != null) {
                openElement("MessageBody"); 
                	writeElement("DeclarationStatus", message.getMessageBody().getDeclarationStatus());
                	if (messageName.equals("TUFSTA")) {
                		writeTufsta(message.getMessageBody().getTufsta()); 
                		writeCusrec(message.getMessageBody().getCusrec());   //EI20140117: + CUSREC
                	} else if (messageName.equals("CUSCAN")) {
                		writeCuscan(message.getMessageBody().getCuscan());
                	} else if (messageName.equals("CUSFIN")) {
                		writeCusfin(message.getMessageBody().getCusfin());
                	} else if (messageName.equals("CUSTST")) {
                		writeCustst(message.getMessageBody().getCustst());
                	} else if (messageName.equals("CUSSTP")) {
                		writeCusstp(message.getMessageBody().getCusstp());
                	} else if (messageName.equals("CUSREC")) {
                		writeCusrec(message.getMessageBody().getCusrec());
                	}
                closeElement();
            }
            closeElement();
          //EI20131001: closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

    private void writeTufsta(Tufsta argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    		return;
    	}
    	openElement("TUFSTA");     		
    		writeHeaderDetailTUFSTA(argument.getHeaderDetail());      		
    	closeElement();    	
	}
    
    private void writeCuscan(Cuscan argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    		return;
    	}
    	openElement("CUSCAN");     		
    		writeHeaderDetails(argument.getHeaderDetail());    		
    		if (argument.getItemDetailsList() != null) {
        		for (ItemDetails item : argument.getItemDetailsList()) {
        			writeItemDetailsCUSCAN(item);
        		}
        	}    	
    	closeElement();
	}
    
    private void writeCusfin(Cusfin argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    		return;
    	}
    	openElement("CUSFIN");     	
    		writeHeaderDetails(argument.getHeaderDetail());
    		writeElement("TypeOfCompletion", argument.getTypeOfCompletion()); //EI20140217
    		writeElement("CompletionAtlasReference", argument.getCompletionAtlasReference());		
    		writeElement("CompletionOtherReference", argument.getCompletionOtherReference());		
    		writePartyDetails("CustodyDetails", argument.getCustodyDetails());		
    		if (argument.getItemDetailsList() != null) {
        		for (ItemDetails item : argument.getItemDetailsList()) {
        			writeItemDetailsCUSFIN(item);
        		}
        	}   
    	closeElement();
	  }
    private void writeCustst(Custst argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    		return;
    	}
    	openElement("CUSTST");     		
    		writeHeaderDetails(argument.getHeaderDetail());
    		if (argument.getItemDetailsList() != null) {
        		for (ItemDetails item : argument.getItemDetailsList()) {
        			writeItemDetailsCUSTST(item);
        		}
        	}  
    	closeElement();
	  }
    private void writeCusstp(Cusstp argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    		return;
    	}
    	openElement("CUSSTP");     		
    		writeHeaderDetails(argument.getHeaderDetail());
    		if (argument.getItemDetailsList() != null) {
        		for (ItemDetails item : argument.getItemDetailsList()) {
        			writeItemDetailsCUSSTP(item);
        		}
        	}  
    	closeElement();
	  }
	  
    private void writeCusrec(Cusrec argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    		return;
    	}
    	openElement("CUSREC");
    	writeHeaderDetails(argument.getHeaderDetail());
    	writeHeaderNotification(argument.getHeaderNotification());
    	if (argument.getItemDetailsList() != null) {
    		for (ItemDetails item : argument.getItemDetailsList()) {
    			writeItemDetailsCUSREC(item);
    		}
    	}
    	closeElement();
	}
    private void writeHeaderDetails(HeaderDetail detail) throws XMLStreamException {
    	if (detail == null) {
    	    return;
    	}    	    	    
    	openElement("HeaderDetail");
    		writeElement("FlightNumber", detail.getFlightNumber());
    		writeElement("AirportOfDeparture", detail.getAirportOfDeparture());
    		writeDateToString("DepartureDate", detail.getDepartureDate());
    		writeElement("AirportOfArrival", detail.getAirportOfArrival());
    		writeDateToString("ArrivalDate", detail.getArrivalDate());
		   	writeElement("CustomsRegistration", detail.getCustomsRegistration());
		   	writeDateToString("RegistrationDate", detail.getRegistrationDate());		  
		closeElement();
    }   
    //TUFSTA
    private void writeHeaderDetailTUFSTA(HeaderDetail detail) throws XMLStreamException {
    	if (detail == null) {
    	    return;
    	}    	    	    
    	openElement("HeaderDetail");
    		writeElement("FlightNumber", detail.getFlightNumber());
    		writeElement("AirportOfDeparture", detail.getAirportOfDeparture());    		
    		writeDateTimeToDate("DepartureDate", detail.getDepartureDate());
    		writeElement("AirportOfArrival", detail.getAirportOfArrival());
    		writeDateTimeToDate("ArrivalDate", detail.getArrivalDate());
		   	writeElement("CustomsStatus", detail.getCustomsStatus());
		   	writeElement("TemporaryMRN", detail.getTemporaryMRN());
		   	writeElement("MRN", detail.getMrn());			   	
		   	writeDateTimeToDate("ReleaseDate", detail.getReleaseDate());
		   	writeDateTimeToDate("CompletionDate", detail.getCompletionDate());
		   	writeElement("Reason", detail.getReason());
		   	writeElement("CustomsOfficeOfDeparture", detail.getCustomsOfficeOfDeparture());
		   	writePartyDetails("PrincipalDetails", detail.getPrincipalDetails());
		   	writeGuarantyDetails(detail.getGuarantyDetailsList());
		   	writeErrorDetails(detail.getErrorDetailsList());
		closeElement();
    }
    //CUSCAN not in scope!
    private void writeItemDetailsCUSCAN(ItemDetails item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	    	  
     	openElement("ItemDetails");
     		writeElement("LineItemNumber", item.getLineItemNumber());
    		writeElement("LineStatus", item.getLineStatus());
    		writeElement("WaybillNumber", item.getWaybillNumber());
    		writeElement("NumberOfPieces", item.getNumberOfPieces());
    		writeElement("StatusCode", item.getStatusCode());
    		writeElement("CancellationReason", item.getCancellationReason());
    		//writeElement("", item);
    		writePartyDetails("CustodyDetails", item.getCustodyDetails());
        closeElement();
    } 
    //CUSFIN
    private void writeItemDetailsCUSFIN(ItemDetails item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	    	  
     	openElement("ItemDetails");
     		writeElement("LineItemNumber", item.getLineItemNumber());
    		writeElement("LineStatus", item.getLineStatus());
    		writeElement("WaybillNumber", item.getWaybillNumber());
    		writeElement("CustomsRegistration", item.getCustomsRegistration());
    		writeDateToString("RegistrationDate", item.getRegistrationDate());
    		writeElement("NumberOfPieces", item.getNumberOfPieces());
    		writeElement("CancellationCode", item.getCancellationCode());    		
        closeElement();
    }  
    //CUSTST
    private void writeItemDetailsCUSTST(ItemDetails item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	    	  
     	openElement("ItemDetails");
     		writeElement("LineItemNumber", item.getLineItemNumber());
    		writeElement("LineStatus", item.getLineStatus());
    		writeElement("WaybillNumber", item.getWaybillNumber());
    		writeElement("ItemDescription", item.getItemDescription());
    		writeElement("NumberOfPieces", item.getNumberOfPieces());
    		writeElement("ItemUnit", item.getItemUnit());
    		writeElement("Weight", item.getWeight());
    		writeElement("CustomsStatus", item.getCustomsStatus());
    		writeElement("VuB", item.getVub());
    		writeElement("CustodyDeadline", item.getCustodyDeadline());
    		writePartyDetails("CustodyDetails", item.getCustodyDetails());
    		writeDisposalDetails(item.getDisposalDetails());
    		writeCustodyWarehouse(item.getCustodyWarehouse());
        closeElement();
    }  
    //CUSSTP
    private void writeItemDetailsCUSSTP(ItemDetails item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	    	  
     	openElement("ItemDetails");
     		writeElement("LineItemNumber", item.getLineItemNumber());
    		writeElement("LineStatus", item.getLineStatus());
    		writeElement("WaybillNumber", item.getWaybillNumber());
    		writeElement("ItemDescription", item.getItemDescription());
    		writeElement("NumberOfPieces", item.getNumberOfPieces());
    		writeElement("ActionCode", item.getActionCode());
    		writeElement("ActionInformation", item.getActionInformation());    		
    		writePartyDetails("CustodyDetails", item.getCustodyDetails());    		
        closeElement();
    }  
//CUSREC
    private void writeItemDetailsCUSREC(ItemDetails item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}    	
    	   
     	openElement("ItemDetails");
     		writeElement("LineItemNumber", item.getLineItemNumber());
    		writeElement("LineStatus", item.getLineStatus());
    		writeElement("WaybillNumber", item.getWaybillNumber());
    		writeItemNotification(item.getItemNotification());    		
        closeElement();
    }  
}

