package com.kewill.kcx.component.mapping.formats.uids.common;

import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Amount;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Reference;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.ResultsOfControl;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module 		: NCTS<br>
 * Created 		: 08.02.2013<br>
 * Description  : Fields and methods to write NCTS-UIDS Messages V41/V70.
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class UidsMessageNCTS20 extends UidsMessageNCTS {

	public void writeTIN(String tag, TIN argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement(tag);
			writeElement("TIN", argument.getTIN());
			//EI ???: writeElement("BO", argument.getBO());
			//EI ???: writeElement("IdentificationType", argument.getIdentificationType());
		closeElement();
	}

	
	public void writeNCTSTrader(String tag, NCTSTrader argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	
    	openElement(tag);             	                    	   
    		writeElement("AuthorisationID", argument.getAuthorisationID());
	    	writeElement("CustomerID", argument.getCustomerID());
	    	writeElement("CustomsID", argument.getCustomsID());
	    	writeElement("ETNAddress", argument.geteTNAddress());
	    	writeElement("ElectronicSignature", argument.getElectronicSignature());
	    	writeElement("TIN", argument.gettIN());
	    	writeElement("TINType", argument.gettINType());
	    	//EI ??? writeElement("BO", argument.getBO());
	    	writeElement("IsTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());  //EI ???
	    	//EI ??? writeElement("IdentificationType", argument.getIdentificationType());
	    	writeElement("VATID", argument.getvATID());
	    	writeElement("TIRHolderID", argument.gettIRHolderID());
	    	writeElement("Status", argument.getStatus());
	    	writeAddress(argument.getAddress());
	    	writeContact(argument.getContact());
	    closeElement();
    }			
	//public void writePrincipal(TIN principalTIN, PartyNCTS principal) throws XMLStreamException {
	public void writePartyNCTS(String tag, PartyNCTS party) throws XMLStreamException {
		if (party == null) {
			return;
		}		
		String customerID = "";
		String tin = "";
		String isTinGermanApprovalNumber = "";
		
		if (party.getPartyTIN() != null) {
			customerID = party.getPartyTIN().getCustomerIdentifier();
			tin = party.getPartyTIN().getTIN();
			isTinGermanApprovalNumber =  party.getPartyTIN().getIsTINGermanApprovalNumber();
		}
		
		openElement(tag);
			writeElement("CustomerID", customerID);
			writeElement("ETNAddress", party.getETNAddress());
			writeElement("TIN", tin);
			//EI ??? writeElement("BO", argument.getBO());
			writeElement("IsTINGermanApprovalNumber", isTinGermanApprovalNumber);
			//EI ??? writeElement("IdentificationType", argument.getIdentificationType());
			writeElement("VATID", party.getVATNumber());
			writeAddress(party.getAddress());
		closeElement();
	}
	
}
