package com.kewill.kcx.component.mapping.formats.kids.common;

import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.KcxNoDataFoundException;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : KidsMessageNCTS<br>
 * Created : 23.08.2010<br>
 * Description : Fields and methods to write NCTS-KidsMessages.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class KidsMessageNCTS20 extends KidsMessageNCTS {

	
	public void writeParty(String tag, PartyNCTS argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(tag);
			writeElement("VATNumber", argument.getVATNumber());
			writeElement("ETNAddress", argument.getETNAddress());
			writeAddress(argument.getAddress());
		closeElement();
	}	 
	
	public void writePartyTIN(String tag, TIN argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(tag);
			writeElement("TIN", argument.getTIN());
			writeElement("BO", argument.getBO());						
			writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
			//TODO-IWA ??? writeCodeElement("IsTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber(), "KCX0001");
			writeCodeElement("IdentificationType", argument.getIdentificationType(), "KCX0069");   
		closeElement();
	}
	
	public void writeNCTSParty(String tag, PartyNCTS argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		if (Utils.isStringEmpty(tag)) {
			return;
		}
		String tin = tag + "TIN";
		String contact = tag + "Contact";
		writePartyTIN(tin, argument.getPartyTIN());
		openElement(tag);
			writeElement("VATNumber", argument.getVATNumber());
			writeElement("ETNAddress", argument.getETNAddress());
			writeAddress(argument.getAddress());
		closeElement();		
		writeContactPerson(contact, argument.getContactPerson());
	}
	
	public void writeSpecialMention(SpecialMention argument) throws XMLStreamException {   
    	if (argument == null) {
    		return;
    	}			
    	openElement("SpecialMention");  
    		writeElement("TypeOfExport", argument.getCode());    		
    		writeCodeElement("ExportFromEU", argument.getExportFromEU(), "KCX0001");
    	    writeElement("ExportFromCountry", argument.getExportFromCountry());    	    
    	    writeElement("Export", argument.getExport());    
    	    writeElement("BansAndLimitations", argument.getBansAndLimitations()); //EI20130821 for LCAG
    	    writeElement("Text", argument.getText());
    	    writeElement("Language", argument.getLanguage()); //EI20130821
    	closeElement();						
    }	
}
