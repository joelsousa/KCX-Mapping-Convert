package com.kewill.kcx.component.mapping.formats.kids.ncts20;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalAppPosition;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: NCTS.<br>
 * Created		: 08.02.2013<br>
 * Description	: BodyNCTSArrivalNotificationKids
 *				: V41/V70: new Contact (TsVSP - Ansprechpartner)
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class BodyNCTSWriteOffNotificationKids extends KidsMessageNCTS20 {
	private MsgNCTSWriteOffNotification message;
	
	public BodyNCTSWriteOffNotificationKids(XMLStreamWriter writer) {
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
			openElement("NCTSWriteOffNotification");
			openElement("GoodsDeclaration");						
				writeElement("ReferenceNumber", message.getReferenceNumber());
				writeElement("TemporaryUCR", message.getTemporaryUCR());
				writeElement("UCRNumber", message.getUCRNumber());
				writeElement("StatusOfControl", message.getStatusOfControl());				
				writeFormattedDateOrTime("ReceiveTime", message.getReceiveTime(),
								message.getReceiveTimeFormat(), EFormat.KIDS_DateTime);						
				writeFormattedDateOrTime("AcceptanceTime", message.getAcceptanceTime(),	
								message.getAcceptanceTimeFormat(), EFormat.KIDS_DateTime);						
				writeFormattedDateOrTime("ReleaseTime", message.getReleaseTime(),	
								message.getReleaseTimeFormat(), EFormat.KIDS_DateTime);						
				writeFormattedDateOrTime("CancellationTime", message.getCancellationTime(),	
								message.getCancellationTimeFormat(), EFormat.KIDS_DateTime);						
				writeFormattedDateOrTime("BeginTimeOfProcessing", message.getBeginTimeOfProcessing(),
								message.getBeginProcessingTimeFormat(), EFormat.KIDS_DateTime);						
				writeElement("StatusInformation", message.getStatusInformation());	
				
				writeFormattedDateOrTime("CompletionTime", message.getCompletionTime(),	
								message.getCompletionTimeFormat(), EFormat.KIDS_DateTime);						
				writeElement("WriteOffType", message.getWriteOffType());
				writeElement("WriteOffText", message.getWriteOffText());
						
				writeFormattedDateOrTime("ReferencedAmountsCharging", message.getReferencedAmountsCharging(),
								message.getReferencedAmountsChargingFormat(), EFormat.KIDS_DateTime);										
				writePartyTin("PrincipalTIN", message.getPrincipal());
				writeTrader("Principal", message.getPrincipal());
				writePartyTin("GuarantorTIN", message.getGuarantor());
				writeTrader("Guarantor", message.getGuarantor());
				
				//writeContactPerson("Contact", message.getContact());   //EI20130207 new V41/V70
				
				writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
				writeElement("UseOfTydenSeals", message.getUseOfTydenSeals());
				writeElement("RepresentativeName", message.getRepresentativeName());
				writeGRNErrorList(message.getGrnErrorList());
				writeFunctionalErrorList(message.getFunctionalErrorList());
				
				writeLocalApplication(message.getLocalApplication());  //EI20130712 fuer CMP/LCAG
						
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
	            e.printStackTrace();
	    }     
	}
	
	private void writePartyTin(String tag, PartyNCTS argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	writePartyTIN(tag, argument.getPartyTIN());	    
    }

	private void writeTrader(String tag, PartyNCTS argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}  
    	//TODO-EI20110929:
    	//ist es richtig, dass hier Party ohne VATNumber und ETNAddress
    	if (argument.getAddress() != null && !argument.getAddress().isEmpty()) {
    		openElement(tag);             	                    	       	
    			writeAddress(argument.getAddress());
			closeElement();
    	}
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
	public void writeFunctionalErrorList(List<FunctionalErrorNcts> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (FunctionalErrorNcts error : list) {
			writeFunctionalErrorNcts("FunctionalError", error);
		}
	}
	
	private void writeLocalApplication(LocalApplication argument) throws XMLStreamException  {
		if (argument == null) {
		    return;
		}		
		openElement("LocalApplication");  
			writeElement("MessageType", argument.getMessageType());
			writeElement("MessageSubType", argument.getMessageSubType());
			writeElement("MessageFunction", argument.getMessageFunction());		   
		    writeElement("RegistrationNumber", argument.getRegistrationNumber());
		    writeElement("RegistrationDate", argument.getRegistrationDate());
		    writeElement("DeclarationStatus", argument.getDeclarationStatus());	
		    //writeElement("PositionStatus", argument.getPositionStatus());
		    writeElement("FlightNumber", argument.getFlightNumber());
		    writeElement("DepartureDate", argument.getDepartureDate());
		    writeElement("AirportOfDeparture", argument.getAirportOfDeparture());
		    writeElement("ArrivalDate", argument.getArrivalDate());
		    writeElement("AirportOfArrival", argument.getAirportOfArrival());
		    writeElement("AdditionalInformation", argument.getAdditionalData());
		    writePositionList(argument.getPositionList());                     //EI20140314
		closeElement();
	}
	private  void writePositionList(ArrayList<LocalAppPosition> list) throws XMLStreamException  { //EI20140314
		if (list == null) {
		    return;
		}
		if (list.isEmpty()) {
		    return;
		}
		for (LocalAppPosition appPos : list) {
			openElement("LocalApplicationPosition");  
				writeElement("PositionNumber", appPos.getPositionNumber());
				writeElement("PositionStatus", appPos.getPositionStatus());
				writeElement("PositionAWB", appPos.getPositionAWB());
			closeElement();
		}
	}
}
