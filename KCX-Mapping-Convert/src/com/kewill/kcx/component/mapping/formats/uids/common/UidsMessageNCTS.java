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
 * Module : UidsMessageNCTS<br>
 * Date Started : 23.08.2010<br>
 * Description : Fields and methods to write NCTS-UIDS Messages.
 * 
 * @author Frederick T
 * @version 1.0.00
 */
public class UidsMessageNCTS extends UidsMessage {
	private void writeTrader(String tag, TIN tin, PartyNCTS address,
			ContactPerson contactPerson) throws XMLStreamException {

		openElement(tag);
		if (tin != null) {
			writeElement("TIN", tin.getTIN());
		}
		writeAddress(address.getAddress());
		writeContact(contactPerson);
		closeElement();
	}

	private void writeTrader(String tag, PartyNCTS party) throws XMLStreamException {
		if (party == null) {
			return;
		}
		if (party.isEmpty()) {
			return;
		}

		openElement(tag);
		// AuthorisationID
		if (party.getPartyTIN() != null) {
			writeElement("CustomerID", party.getPartyTIN()
					.getCustomerIdentifier());
			writeElement("CustomsID", party.getPartyTIN()
					.getIsTINGermanApprovalNumber());
		}
		writeElement("ETNAddress", party.getETNAddress());
		// ElectronicSignature
		if (party.getPartyTIN() != null) {
			writeElement("TIN", party.getPartyTIN().getTIN());
		}
		writeElement("VATID", party.getVATNumber());
		// Status

		writeAddress(party.getAddress());
		writeContact(party.getContactPerson());
		closeElement();
	}

	public void writeAddress(AddressNCTS argument)
			throws XMLStreamException {
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
			writeElement("PostalCode", argument.getPostalCode());
			writeElement("City", argument.getCity());
			writeElement("District", argument.getDistrict());
			//EI20111026: writeElement("Country", argument.getCountry());
			writeElement("CountryCodeISO", argument.getCountry());
			writeElement("POBox", argument.getPOBox());
		closeElement();
	}	
	
	public void writeTIN(String tag, TIN argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement(tag);
		writeElement("TIN", argument.getTIN());
		closeElement();
	}

	public void writeContact(ContactPerson argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement("Contact");
			writeElement("Identity", argument.getIdentity());
			writeElement("Name", argument.getClerk());
			writeElement("Function", argument.getPosition());
			writeElement("Phone", argument.getPhoneNumber());
			writeElement("Fax", argument.getFaxNumber());
			writeElement("Email", argument.getEmail());
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
	    	writeElement("IsTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());
	    	writeElement("VATID", argument.getvATID());
	    	writeElement("TIRHolderID", argument.gettIRHolderID());
	    	writeElement("Status", argument.getStatus());
	    	writeAddress(argument.getAddress());
	    	writeContact(argument.getContact());
	    closeElement();
    }			
	//public void writePrincipal(TIN principalTIN, PartyNCTS principal) throws XMLStreamException {
	public void writePartyNCTS(String tag, TIN partyTIN, PartyNCTS party) throws XMLStreamException {
		if (partyTIN == null && party == null) {
			return;
		}		
		String customerID = "";
		String tin = "";
		String isTinGermanApprovalNumber = "";
		
		if (partyTIN != null) {
			customerID = partyTIN.getCustomerIdentifier();
			tin = partyTIN.getTIN();
			isTinGermanApprovalNumber =  partyTIN.getIsTINGermanApprovalNumber();
		}
		openElement(tag);
			writeElement("CustomerID", customerID);
			writeElement("ETNAddress", party.getETNAddress());
			writeElement("TIN", tin);
			writeElement("IsTINGermanApprovalNumber", isTinGermanApprovalNumber);
			writeElement("VATID", party.getVATNumber());
			writeAddress(party.getAddress());
		closeElement();
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
		if (currentFormat == null || resultFormat == null) {  
			//writeElement(tag, dateOrTimeString);
			writeStringToDateTime(tag, dateOrTimeString);    //EI20110526
			return;
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

	public void writeSpecialMentionsList(List<SpecialMention> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		for (SpecialMention specialMentions : list) {
			writeSpecialMentions(specialMentions);
		}
	}
	
	public void writeSpecialMentions(SpecialMention argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("SpecialMentions");		
			writeElement("ExportFromEC", argument.getExportFromEU());
			writeElement("ExportFromCountry", argument.getExportFromCountry());
			writeElement("Export", argument.getExport());                             //EI20130114
			writeElement("Code", argument.getTypeOfExport());
			writeElement("Text", argument.getText());
		closeElement();
	}
	
	public void writeFunctionalErrorList(List<FunctionalErrorNcts> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		for (FunctionalErrorNcts errList : list) {
			writeFunctionalErrorNcts(errList);
		}
	}

	public void writeFunctionalErrorNcts(FunctionalErrorNcts argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("FunctionalError");
		writeElement("Type", argument.getCode());
		writeElement("Pointer", argument.getPointer());
		writeElement("Reason", argument.getText());
		// writeElement("OriginalValue", argument.getNumber());
		// writeElement("Description", argument.getUniqueNumber());
		closeElement();
	}	

	public void writeEnRouteEvent(EnRouteEvent argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("EnRouteEvent");
		writeElement("Place", argument.getPlace());
		writeElement("CountryCode", argument.getCountryCode());
		writeTransShipment(argument.getTransShipment());
		writeElement("TotalNumberOfSeals", argument.getTotalNumberOfSeals());
		if (argument.getSealsIdentityList() != null) {
			for (String entry : argument.getSealsIdentityList()) {
				writeElement("SealsIdentity", entry);
			}
		}
		writeIncident(argument.getIncident());
		writeElement("AlreadyInNCTS", argument.getAlreadyInNCTS());
		closeElement();
	}
	
	public void writeSealNumberList(List<SealNumber> argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		for (SealNumber sealNumber : argument) {
			writeElement("SealsIdentity", sealNumber.getNumber());
		}
	}
		

	public void writeTransShipment(TransShipment argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("TransShipment");
		writeElement("NewTransportId", argument.getNewTransportId());
		writeElement("NewTransportCountry", argument.getNewTransportCountry());
		if (argument.getEndorsementDate() != null) {
			writeFormattedDateOrTime("EndorsementDate", argument.getEndorsementDate(), 
					//argument.getEndorsementDateFormat(), EFormat.ST_DateTimeTNoZ);
					argument.getEndorsementDateFormat(), EFormat.ST_Date);        //EI20110815
		}
		writeElement("EndorsementAuthority", argument.getEndorsementAuthority());
		writeElement("EndorsementPlace", argument.getEndorsementPlace());
		writeElement("EndorsementCountry", argument.getEndorsementCountry());
		if (argument.getContainerNumberList() != null) {
			for (String entry : argument.getContainerNumberList()) {
				writeElement("ContainerNumber", entry);
			}
		}
		closeElement();
	}

	public void writeIncident(Incident argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Incident");
		writeElement("IncidentFlag", argument.getIncidentFlag());
		writeElement("IncidentInfo", argument.getIncidentInfo());
		writeFormattedDateOrTime("EndorsementDate", argument.getEndorsementDate(),
				//argument.getEndorsementDateFormat(), EFormat.KIDS_DateTime);
				argument.getEndorsementDateFormat(), EFormat.ST_Date);    //EI20110815
		writeElement("EndorsementAuthority", argument.getEndorsementAuthority());
		writeElement("EndorsementPlace", argument.getEndorsementPlace());
		writeElement("EndorsementCountry", argument.getEndorsementCountry());
		closeElement();
	}

	@Override
	public void writePdfInformation(PDFInformation argument)
			throws XMLStreamException {

		if (getCommonFieldsDTO() != null) {
			getCommonFieldsDTO().setPdfExists(false);
		}

		if (argument == null) {
			return;
		}

		String name = argument.getName();
		String dir = argument.getDirectory();
		String newdir = argument.getNewDirectory();
		List<String> pdfList = argument.getPdflist();
		int listSize = 0;

		if (Utils.isStringEmpty(name) && Utils.isStringEmpty(dir) && 
			Utils.isStringEmpty(newdir) && pdfList == null) {
			return;
		}

		openElement("PDF");
		writeElement("Name", name);
		writeElement("Directory", dir);
		writeElement("Subdirectory", newdir);

		if (pdfList != null) {
			listSize = pdfList.size();
			if (listSize > 0) {
				// CK090617 wenn Kunde tgz-file will - lt. Eintrag in DB -
				// PDF-Information nicht einbetten
				if (getCommonFieldsDTO() != null && 
						getCommonFieldsDTO().isPdfTgz()) {
					writePdffile(pdfList);
					Utils.log("PDF nicht einbinden");
					getCommonFieldsDTO().setPdfExists(true);
				} else {
					for (int i = 0; i < pdfList.size(); i++) {
						writeElement("base64", pdfList.get(i));
					}
				}
			} else {
				Utils.log("(BodyExportReleaseUids writeBody) pdflist is empty");
			}
		}
		closeElement();
	}

	public void writeTransport(String tag, TransportMeans argument)
			throws XMLStreamException {

		if (argument == null) {
			return;
		}
		openElement(tag);
		// writeElement("Description", argument.getDescription());
		writeElement("Identity", argument.getTransportationNumber());
		writeElement("Mode", argument.getTransportMode());
		writeElement("Nationality", argument.getTransportationCountry());
		writeElement("Type", argument.getTransportationType());
		closeElement();
	}
	
	public void writeTransportAtDeparture(String tag, Transport argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
    		writeElement("Description", argument.getDescription());
	    	writeElement("Identity", argument.getIdentity());
	    	writeElement("Mode", argument.getMode());
	    	writeElement("Nationality", argument.getsetNationality());
	    	writeElement("ShippingLine", argument.getShippingLine());
	    	writeElement("Type", argument.getType());
	    closeElement();
    }
	
	public void writeSGICodesList(List<SensitiveGoods> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (SensitiveGoods sgi : list) {
			writeSGICodes(sgi);			
		}
	}
	public void writeSGICodes(SensitiveGoods argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("SGICodes");
			writeElement("Code", argument.getType());
			writeElement("Quantity", argument.getWeight());
		closeElement();
	}
	
	public void writeClerk(ContactPerson argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Clerk");
			writeElement("Identity", argument.getIdentity());
		closeElement();
	}
	
	public void writeContainerList(Container argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		if (argument.getNumberList() != null) {
			for (String container : argument.getNumberList()) {
				writeElement("ContainerNumber", container);
			}	
		}
	}
	public void writePackagesList(List<Packages> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Packages packages : list) {
			writePackage(packages);			
		}
	}
	public void writePackage(Packages argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Packages");
			writeElement("Number", argument.getSequentialNumber());
			writeElement("Quantity", argument.getQuantity());
			writeElement("MarksAndNumbers", argument.getMarks());
			writeElement("Type", argument.getType());
		closeElement();
	}
	
	public void writePreviousDocumentsList(List<PreviousDocument> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (PreviousDocument prevDoc : list) {
			writePreviousDocument(prevDoc);
		}
	}
	
	public void writePreviousDocument(PreviousDocument argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}

		openElement("PreviousDocuments");
			writeElement("Type", argument.getType());
			writeElement("Reference", argument.getMarks());
			writeElement("Remark", argument.getAdditionalInformation());						
			writeFormattedDateOrTime("DateOfCreation", argument.getDate(),
					argument.getDateFormat(), EFormat.ST_Date);			
		closeElement();
	}

	public void writeProducedDocumentsList(List<Document> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Document doc : list) {
			writeDocument(doc);
		}
	}
	
	public void writeDocument(Document argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("ProducedDocuments");
			writeElement("Type", argument.getType());
			writeElement("Reference", argument.getReference());
			writeElement("Remark", argument.getAdditionalInformation());
			writeFormattedDateOrTime("DateOfCreation", argument.getIssueDate(),
					argument.getIssueDateFormat(), EFormat.ST_Date);
		closeElement();		
	}	
	
	public void writeGuaranteeList(List<Guarantee> list) throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (Guarantee guarantee : list) {
			writeGuarantee(guarantee);
		}
	}
	
	public void writeGuarantee(Guarantee argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Guarantee");
			writeElement("TypeOfGuarantee", argument.getTypeOfGuarantee());
			if (argument.getReferenceList() != null) {
				for (Reference reference : argument.getReferenceList()) {
					writeReference(reference);
				}
			}
		closeElement();
	}
	
	public void writeReference(Reference argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Reference");
			writeElement("GRN", argument.getGrn());
			writeElement("AccessCode", argument.getAccessCode());
			writeElement("OtherNumber", argument.getOtherNumber());
			writeElement("IssuingOffice", argument.getIssuingOffice());
			writeElement("OwnerTIN", argument.getOwnerTin());
			writeElement("PriceGroup", argument.getPriceGroup());
			writeElement("NotValidForEC", argument.getNotValidForEC());
			for (String countryList : argument.getNotValidForCountryList()) {
				writeElement("NotValidForCountry", countryList);
			}
			writeAmount(argument.getAmount());
		closeElement();
	}

	public void writeAmount(Amount argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Amounts");
			writeElement("Amount", argument.getAmount());
			writeElement("Currency", argument.getCurrency());
			writeElement("LocalAmount", argument.getLocalAmount());
			writeElement("LocalCurrency", argument.getLocalCurrency());
		closeElement();
	}

	public void writeResultsOfControl(ResultsOfControl argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("ResultsOfControl");             	                    	   
    		writeElement("Description", argument.getDescription());
    		writeElement("ControlIndicator", argument.getControlIndicator());
    		writeElement("AttributePointer", argument.getAttributePointer());
    		writeElement("CorrectedValue", argument.getCorrectedValue());
	    closeElement();
    }
}
