package com.kewill.kcx.component.mapping.formats.uids.ncts;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSWriteOffNotificationUids<br>
 * Created		: 12.05.2011<br>
 * Description	: Contains message structure for NCTSWriteOffNotificationUids UIDS. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class BodyNCTSWriteOffNotificationUids extends UidsMessageNCTS {
	private MsgNCTSWriteOffNotification message;
	
	public BodyNCTSWriteOffNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgNCTSWriteOffNotification getMessage() {
		return message;
	}
	
	public void setMessage(MsgNCTSWriteOffNotification argument) {
		this.message = argument;
	}
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");  
			openElement("NCTS");
			openElement("NCTSWriteOffNotification");	
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("TemporaryMRN", message.getTemporaryUCR());
				writeElement("MRN", message.getUCRNumber());
				writeElement("StatusOfControl", message.getStatusOfControl());								
				writeFormattedDateOrTime("DateOfReceipt", 
										message.getReceiveTime(), 
										message.getReceiveTimeFormat(), 
										EFormat.ST_DateTimeTNoZ);							
				writeFormattedDateOrTime("DateOfAcceptance", 
										message.getAcceptanceTime(), 
										message.getAcceptanceTimeFormat(), 
										EFormat.ST_DateTimeTNoZ);							
				writeFormattedDateOrTime("DateOfRelease", 
										message.getReleaseTime(), 
										message.getReleaseTimeFormat(), 
										EFormat.ST_DateTimeTNoZ);							
				writeFormattedDateOrTime("DateOfCancellation", 
										message.getCancellationTime(), 
										message.getCancellationTimeFormat(), 
										EFormat.ST_DateTimeTNoZ);							
				writeFormattedDateOrTime("DateOfBeginOfProcessing", 
										message.getBeginTimeOfProcessing(), 
										message.getBeginProcessingTimeFormat(), 
										EFormat.ST_DateTimeTNoZ);							
				writeElement("StatusInformation", message.getStatusInformation());							
							
				writeFormattedDateOrTime("WriteOffDate", 
										message.getCompletionTime(), 
										message.getCompletionTimeFormat(), 
										EFormat.ST_DateTimeTNoZ);
							
				writeElement("WriteOffType", message.getWriteOffType());
				writeElement("WriteOffText", message.getWriteOffText());
				
				writeFormattedDateOrTime("ReferencedAmountsCharging", 
										message.getReferencedAmountsCharging(),
										message.getReferencedAmountsChargingFormat(), 
										EFormat.ST_DateTimeTNoZ);
							
				writeParty("Principal", message.getPrincipal());
				//EI20111004: writeParty("Guarantor", message.getPrincipal());
				writeParty("Guarantor", message.getGuarantor());   //EI20111004
				writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
				writeElement("TydenSealFlag", message.getUseOfTydenSeals());
				writeElement("RepresentativeName", message.getRepresentativeName());
				writeGRNErrorList(message.getGrnErrorList());
				writeFunctionalErrorList(message.getFunctionalErrorList());
													
			closeElement();
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }     
	}	
	
	public void writeParty(String tag, PartyNCTS argument) throws XMLStreamException {
    	if (argument == null || argument.isEmpty()) {
    	    return;
    	}    
    	openElement(tag);  
    		if (argument.getPartyTIN() != null && !argument.getPartyTIN().isEmpty()) {
    			writeElement("TIN", argument.getPartyTIN().getTin());
    			writeElement("IsTINGermanApprovalNumber", argument.getPartyTIN().getIsTINGermanApprovalNumber());
    			writeElement("CustomerID", argument.getPartyTIN().getCustomerIdentifier());
    		}    	           	                    	       	
	    	writeAddress(argument.getAddress());	    	
	    closeElement();
    }
	
	public void writeGRNErrorList(List<FunctionalErrorNcts> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (FunctionalErrorNcts error : list) {
			if (!error.isEmpty()) {
				openElement("GRNError");
					writeElement("GRN", error.getGRN());
					writeElement("Code", error.getCode());
					writeElement("Text", error.getText());
				closeElement();
			}
		}
	}
	
}
