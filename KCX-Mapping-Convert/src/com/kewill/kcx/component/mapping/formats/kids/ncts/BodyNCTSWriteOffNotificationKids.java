package com.kewill.kcx.component.mapping.formats.kids.ncts;

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSArrivalNotificationKids.<br>
 * Created		: 11.05.2011<br>
 * Description	: 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyNCTSWriteOffNotificationKids extends KidsMessageNCTS {
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
				writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
				writeElement("UseOfTydenSeals", message.getUseOfTydenSeals());
				writeElement("RepresentativeName", message.getRepresentativeName());
				writeGRNErrorList(message.getGrnErrorList());
				writeFunctionalErrorList(message.getFunctionalErrorList());
						
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
}
