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
public class KidsMessageNCTS extends KidsMessage {

	
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
	
	 public void writeAddress(AddressNCTS argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}
	    	if (argument.isEmpty()) {
				return;
			}	    	
	    	openElement("Address"); 
	            writeElement("Name", argument.getName());
	            writeElement("Street", argument.getStreet());
	            writeElement("HouseNumber", argument.getHouseNumber());
	            writeElement("City", argument.getCity());
	            writeElement("PostalCode", argument.getPostalCode());            
	            writeElement("Country", argument.getCountry());
	            writeElement("CountrySubEntity",  argument.getCountrySubEntity()); 
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
			// CK20111103 writeElement("IsTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());
			writeCodeElement("IsTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber(), "KCX0001");
			writeElement("CustomerIdentifier", argument.getCustomerIdentifier());	
		closeElement();
	}

	public void writeContactPerson(String tag, ContactPerson argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(tag);
		writeElement("Position", argument.getPosition());
		writeElement("Clerk", argument.getClerk());
		writeElement("Identity", argument.getIdentity());
		writeElement("Email", argument.getEmail());
		writeElement("FaxNumber", argument.getFaxNumber());
		writeElement("PhoneNumber", argument.getPhoneNumber());
		writeElement("Initials", argument.getInitials());
		writeElement("Title", argument.getTitle());
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
	
	public void writeFormattedDateOrTime(String tag, String dateOrTimeString,
										EFormat currentFormat, EFormat resultFormat)
										throws XMLStreamException {		
		if (dateOrTimeString == null) {
    		return;
    	}
		if (Utils.isStringEmpty(dateOrTimeString)) {
    		return;
    	}
		if (currentFormat == null || resultFormat == null) {  //EI20110526 for FssTokids
			writeElement(tag, dateOrTimeString);			
			return;
		}
		
		if (currentFormat == EFormat.KIDS_DateTime) {                      //EI20110617
			if (dateOrTimeString.length() > 12) {
				dateOrTimeString = dateOrTimeString.substring(0, 12);  
			} else if (dateOrTimeString.length() < 12) {
				dateOrTimeString = dateOrTimeString + "000000000000";
				dateOrTimeString = dateOrTimeString.substring(0, 12); 
			}
		}
		if (currentFormat == EFormat.KIDS_Date) {                      //EI20110617
			if (dateOrTimeString.length() > 8) {
				dateOrTimeString = dateOrTimeString.substring(0, 8);  
			} else if (dateOrTimeString.length() < 8) {
				dateOrTimeString = dateOrTimeString + "00000000";
				dateOrTimeString = dateOrTimeString.substring(0, 8); 
			}
		}
		if (currentFormat == EFormat.KIDS_Time) {                      //EI20110617
			if (dateOrTimeString.length() > 6) {
				dateOrTimeString = dateOrTimeString.substring(0, 6);  
			} else if (dateOrTimeString.length() < 6) {
				dateOrTimeString = dateOrTimeString + "000000";
				dateOrTimeString = dateOrTimeString.substring(0, 6); 
			}
		}
		
		KcxDateTime kcx;
		try {
			kcx = new KcxDateTime(currentFormat, dateOrTimeString);
			writeElement(tag, kcx.format(resultFormat));
		} catch (ParseException e) {
			Utils.log(Utils.LOG_ERROR, 
					String.format("Date/Time string '%s' for element '%s' " +
					"could not be processed for output - %s", 
					dateOrTimeString, tag, e.getLocalizedMessage()));
		}
	}
	
	public void writeSealsId(SealNumber argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("SealsID");
		writeElement("SealsIdentity", argument.getNumber());
		writeElement("SealsIdentityLng", argument.getLanguage());
		closeElement();

	}

	public void writePackage(Packages argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Package");
			writeElement("Quantity", argument.getQuantity());
			//EI20110929 ???: if (argument.getSequentialNumber() != null) {
			writeElement("SequentialNumber", argument.getSequentialNumber());
			// CK20111103
			writeCodeElement("Type", argument.getType(), "KCX0066");
			writeElement("Marks", argument.getMarks());
		closeElement();
	}

	private void writeSpecialMention(SpecialMention argument, String msg)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("SpecialMentions");
		writeCodeElement("code", argument.getCode(), "C0039");
		closeElement();
	}

	public void writeFunctionalErrorNcts(String tag, FunctionalErrorNcts argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}		
		openElement(tag);
			writeElement("Code", argument.getCode());
			writeElement("Text", argument.getText());
			writeElement("Pointer", argument.getPointer());
		closeElement();
	}

	@Override
	public void writePDFInformation(PDFInformation argument)
			throws XMLStreamException {

		if (getCommonFieldsDTO() != null) {
			getCommonFieldsDTO().setPdfExists(false);
		}
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement("PDFInformation");
		writeElement("Name", argument.getName());
		writeElement("Directory", argument.getDirectory());
		writeElement("NewDirectory", argument.getNewDirectory());

		List<String> pdfList = argument.getPdflist();
		int listSize = pdfList.size();

		if (listSize > 0) {
			if (getCommonFieldsDTO() != null && getCommonFieldsDTO().isPdfTgz()) {
				writePdffile(pdfList);
				Utils.log("PDF nicht einbinden");
				getCommonFieldsDTO().setPdfExists(true);
			} else {
				for (int i = 0; i < pdfList.size(); i++) {
					writeElement("base64Text", pdfList.get(i));
				}
			}
		} else {
			Utils.log("(KidsMessage writePDFInformation) pdfList is empty");
		}

		closeElement();
	}

	public void writeTransport(String tag, TransportMeans argument)
			throws XMLStreamException {

		if (argument == null) {
			return;
		}
		openElement(tag);
			// CK20111103 writeElement("TransportMode", argument.getTransportMode());
			writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");
			// CK20111103 writeElement("TransportationType", argument.getTransportationType());
			writeCodeElement("TransportationType", argument.getTransportationType(), "KCX0004");
			//writeElement("TransportTime", argument.getTransportTime());
			writeElement("TransportationNumber", argument.getTransportationNumber());
			writeElement("TransportationCountry", argument.getTransportationCountry());
			//writeElement("PlaceOfLoading", argument.getPlaceOfLoading());
			//writeElement("PlaceOfLoadingCode", argument.getPlaceOfLoadingCode());
		closeElement();
	}
	
	public void writeSealNumber(SealNumber argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("SealNumbers");
			writeElement("Number", argument.getNumber());
			//writeElement("SealingParty", argument.getLanguage());
		closeElement();
	}

	public void writeSensitiveGoods(SensitiveGoods argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
		openElement("SensitiveGoods");
			writeCodeElement("Type", argument.getType(), "KCX0032");
			writeElement("Weight", argument.getWeight());
		closeElement();
	}
	
	public void writeCodeElement(String tag, String value, String kcxCodeID) throws XMLStreamException {
        if (value != null) {
            if (!value.trim().equals("")) {
                String targetValue = null;
                if (kidsHeader == null) {
                	writeElement(tag, value);
                	return;
                }
                if (kidsHeader.getMap().equals("1")) {
                    try {
                        targetValue = Utils.getKCXCodeFromValueCodeIdFromTo(value, kcxCodeID, 
                                                                            kidsHeader.getMapFrom(), 
                                                                            kidsHeader.getMapTo());
                    } catch (KcxNoDataFoundException e) {
                        Utils.log("(KidsMessage writeCodeElement) " + e.getMessage());
                        // Wird in der DB nichts gefunden wird ein Leerstring eingetragen
                        // code not found in database so output blank
                        targetValue = " ";
                    }
                } else {
                    targetValue = value;
                }
                Utils.log("(KidsMessage writeCodeElement) alter Wert " + value + " neuer Wert " + targetValue);
                writeElement(tag, targetValue);
            }
        }
    }
	
	public void writeEnRouteEvent(EnRouteEvent argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("EnRouteEvent");             	                    	   
    		writeElement("Place", argument.getPlace());
	    	writeElement("CountryCode", argument.getCountryCode());
    		writeTransShipment(argument.getTransShipment());
	    	writeElement("TotalNumberOfSeals", argument.getTotalNumberOfSeals());
	    	
	    	if (argument.getSealsIdentityList() != null) {
		    	for (String sealsIdentity : argument.getSealsIdentityList()) {
		    			writeElement("SealsIdentity", sealsIdentity);
	    		}
	    	}
	    	
	    	writeIncident(argument.getIncident());
	    	writeElement("AlreadyInNCTS", argument.getAlreadyInNCTS());
	    closeElement();
    }
	
	public void writeTransShipment(TransShipment argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("TransShipment");             	                    	   
    		writeElement("NewTransportId", argument.getNewTransportId());
    		writeElement("NewTransportCountry", argument.getNewTransportCountry());    		
			writeFormattedDateOrTime("EndorsementDate", argument.getEndorsementDate(), 						
						argument.getEndorsementDateFormat(), EFormat.KIDS_Date);       //EI20110815			
	    	writeElement("EndorsementAuthority", argument.getEndorsementAuthority());
	    	writeElement("EndorsementPlace", argument.getEndorsementPlace());
	    	writeElement("EndorsementCountry", argument.getEndorsementCountry());
	    	
	    	if (argument.getContainerNumberList() != null) {
		    	for (String containerNumber : argument.getContainerNumberList()) {
	    			writeElement("ContainerNumber", containerNumber);
		    	}
	    	}
	    	
	    closeElement();
    }
	
	public void writeIncident(Incident argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("Incident");             	                    	   
    		writeElement("IncidentFlag", argument.getIncidentFlag());
    		writeElement("IncidentInfo", argument.getIncidentInfo());    		
			writeFormattedDateOrTime("EndorsementDate", argument.getEndorsementDate(), 						
						argument.getEndorsementDateFormat(), EFormat.KIDS_Date);   //EI20110815			
    		writeElement("EndorsementAuthority", argument.getEndorsementAuthority());
    		writeElement("EndorsementPlace", argument.getEndorsementPlace());
    		writeElement("EndorsementCountry", argument.getEndorsementCountry());
	    closeElement();
    }
	
	public void writeSpecialMention(SpecialMention argument) throws XMLStreamException {   
    	if (argument == null) {
    		return;
    	}			
    	openElement("SpecialMention");  
    		writeElement("TypeOfExport", argument.getCode());   //EI20110929
    		// CK20111103 writeElement("ExportFromEU", argument.getExportFromEU());
    		writeCodeElement("ExportFromEU", argument.getExportFromEU(), "KCX0001");
    	    writeElement("ExportFromCountry", argument.getExportFromCountry());
    	    //EI20110929: writeElement("TypeOfExport", argument.getCode());
    	    writeElement("Export", argument.getExport());    //EI20130110
    	    writeElement("Text", argument.getText()); 
    	closeElement();						
    }	
	
	public void writeCommodityCode(CommodityCode argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("CommodityCode");
			writeElement("TarifCode", argument.getTarifCode());
		closeElement();
	}	
	
	public void writeContainers(Container argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Containers");
		for (String container : argument.getNumberList()) {
			writeElement("Number", container);
		}
		closeElement();
	}
	
	public void writeDocument(Document argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Document");
			writeElement("Qualifier", argument.getQualifier());
			writeElement("Type", argument.getType());
			writeElement("Reference", argument.getReference());
			writeElement("AdditionalInformation", argument.getAdditionalInformation());			
			writeFormattedDateOrTime("IssueDate", argument.getIssueDate(),					
						argument.getIssueDateFormat(), EFormat.KIDS_Date);
			
		closeElement();
	}
	
}
