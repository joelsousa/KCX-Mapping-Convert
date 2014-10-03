package com.kewill.kcx.component.mapping.formats.uids.common;

/*
 * Function    : FssToKids.java
 * Titel       :
 * Date        : 28.08.2008
 * Author      : Kewill CSF / schmidt, kron
 * Description : transformer called by Mule 
 * 				 to convert ZABIS-Fss messages in KIDS-Format
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Christine Kron
 * Date        : 24.09.2008
 * Label       : CK080924
 * Description : UIDS: dont write empty tags
 * 				 this is agreed with the ETN-Group
 * 
 * Author      : E.Iwaniuk
 * Date        : 10.03.2009
 * Label       : EI20090310
 * Description : new Tags for V60
 *  
 * Author      : E.Iwaniuk
 * Date        : 08.05.2009
 * Label       : EI20090508
 * Description : check if Utils.isStringEmpty(...) for writeDateToString... and writeStringToDate...
 * 
 * Author      : E.Iwaniuk
 * Date        : 14.05.2009
 * Label       : EI20090514
 * Description : Abfrage der Länge vor dem Aufruf writeStringToDate... rausgenommen
 *             : Bei DatumsFelder Blenks entfernt (xyz.trim())
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson extended: city, date  
 * 
 * Author      : E.Iwaniuk
 * Date        : 04.05.2009
 * Label       : EI20100504
 * Description : EMCS   
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;

public abstract class UidsMessage {
	
	public UidsHeader uidsHeader = null;
	public KidsHeader kidsHeader = null;

    protected XMLStreamWriter writer = null;

    protected String encoding = "UTF-8";
    
    private CommonFieldsDTO commonFieldsDTO = null;
    
    public void mapKidsToUidsHeader() {
    	String msgnam = "";
    	
//        CustomerDTO customerDTO = new Db().getUidsCustomerFromKidsId(kidsHeader.getReceiver());
//        Utils.log("(UidsMessage mapKidsToUidsHeader) customerDTO.getLocalId() = " + customerDTO.getLocalId());
//    	uidsHeader.setTo(kidsHeader.getReceiver());
        uidsHeader.setTo(commonFieldsDTO.getUidsId());
//        uidsHeader.setTo(customerDTO.getLocalId().trim());
        uidsHeader.setFrom(kidsHeader.getTransmitter());
        uidsHeader.setMsgid(kidsHeader.getMessageID());
        uidsHeader.setSentat(kidsHeader.getSentAt());
        uidsHeader.setInreplyto(kidsHeader.getInReplyTo());
        uidsHeader.setUidsNamespaceVersion(commonFieldsDTO.getUidsVersion());
        uidsHeader.setTimeZone(kidsHeader.getTimeZone());  //EI20110519

        // 20100609MS
        uidsHeader.setProcedure(kidsHeader.getProcedure());
        
        uidsHeader.setMethod(kidsHeader.getMethod());
        
        msgnam = kidsHeader.getMessageName();
       
        
        // CK090428 UIDS
        // Version in Abh. von KIDS-Release setzen
  		// Uids 1.0 entspricht Kids 1.0.00 entspricht Zabis 5.3
  	    // Uids 2.0 entspricht Kids 2.0.00 entspricht Zabis 6.0
     	// MessageType setzen bei V2.0
		if (kidsHeader.getRelease() == null) {
			Utils.log("(UidsMessage mapKidsToUidsHeader) KIDSRelease ist null");
			uidsHeader.setMessageVersion("1.0");
		} else if (kidsHeader.getRelease().equals("2.0.00")) {
			Utils.log("(UidsMessage mapKidsToUidsHeader) KIDSRelease ist 2.0.00");
			uidsHeader.setMessageVersion("2.0");
		} else if (kidsHeader.getRelease().equals("2.1.00")) {   //EI20120803
			Utils.log("(UidsMessage mapKidsToUidsHeader) KIDSRelease ist 2.1.00");
			uidsHeader.setMessageVersion("2.1");
		} else if (kidsHeader.getRelease().equals("4.1.00")) {    //EI20130211
			Utils.log("(UidsMessage mapKidsToUidsHeader) KIDSRelease ist 4.1.00");
			uidsHeader.setMessageVersion("4.1");
		} else if (kidsHeader.getRelease().equals("4.0.00")) {
			Utils.log("(UidsMessage mapKidsToUidsHeader) KIDSRelease ist 4.0.00");
			uidsHeader.setMessageVersion("4.0");
		} else  { uidsHeader.setMessageVersion("1.0");
		}
		uidsHeader.setMessageType(Utils.getUidsMsgFromKidsMsg(msgnam, uidsHeader.getProcedure()));
  		// Ende CK090428
        
        // act is depending on the direction
		// exceptions are localAppResult and InternalStatusInformation
		// Christine Kron 03.09.2010

		Utils.log("(UidsMessage mapKidsToUidsHeader) commonFieldsDTO.getDirection() = " + commonFieldsDTO.getDirection());
//		if (kidsHeader.getDirection().equalsIgnoreCase("TO_CUSTOMER")) {
	    if (commonFieldsDTO.getDirection() == EDirections.CountryToCustomer) {
			uidsHeader.setAct("inform");
		} else {
			uidsHeader.setAct("request");
		}
		
		// only this message has - independant from customs procedure - act "status"
		if (msgnam.equalsIgnoreCase("InternalStatusInformation")) {
			uidsHeader.setAct("status");
		}
		
		// with the message localAppResult the status is dependant from the content
		// and has to be set in MapFailureKU used with all customs procedures
		
        // 20100609MS
        uidsHeader.setDirection(kidsHeader.getDirection());
        
    }
    
    protected void writeElement(String tag, String value) throws XMLStreamException {
    	// folgende logs auskommentieren, da log-files zu groß werden
    	// C.K. 18.06.2009
        // Utils.log("(UidsMessage writeElement) tag = " + tag);
        // Utils.log("(UidsMessage writeElement) value = " + value);
/*        if (value == null || value.trim().equals("")) {
            writer.writeEmptyElement(tag);
        } else {
            writer.writeStartElement(tag);
            writer.writeCharacters(value);
            writer.writeEndElement();
        }*/
        // CK080924
        if (value != null) {
        	if (!value.trim().equals("")) {
        		writer.writeStartElement(tag);
        		writer.writeCharacters(value);
        		writer.writeEndElement();
        	}
        }
    }
  //iwa 20080924
    protected void writeElements(String tag, String value) throws XMLStreamException {
    	Utils.log("(UidsMessage writeElement) tag = " + tag);
        Utils.log("(UidsMessage writeElement) value = " + value);
        if (value == null || value.trim().equals("")) {
            writer.writeEmptyElement(tag);
        } else {
        	writeElement(tag, value);
        }
	} 
    
    //EI20100506: 
    protected void writeElementWithAttribute(String tag, String value, String attrName, 
                                                        String attrValue) throws XMLStreamException {
        if (value != null) {
        	if (!value.trim().equals("")) {
        		writer.writeStartElement(tag);
        		if (attrValue != null) {   //EI20110916
        			writer.writeAttribute(attrName, attrValue);        			
        		}         		
        		writer.writeCharacters(value);        		
        		writer.writeEndElement();
        	}
        }
    }    
    protected void setAttribute(String tag, String value) throws XMLStreamException {
    		writer.writeAttribute(tag, value);      	
    }
    
    protected void openElement(String tag) throws XMLStreamException {
//        Utils.log("(UidsMessage openElement) tag = " + tag);
        writer.writeStartElement(tag);
    }

    protected void closeElement() throws XMLStreamException {
//        Utils.log("(UidsMessage closeElement)");
        writer.writeEndElement();
    }

	public UidsHeader getUidsHeader() {
		return uidsHeader;
	}

	public void setUidsHeader(UidsHeader uidsHeader) {
		this.uidsHeader = uidsHeader;
	}

	public KidsHeader getKidsHeader() {
		return kidsHeader;
	}

	public void setKidsHeader(KidsHeader kidsHeader) {
		this.kidsHeader = kidsHeader;
	}
// from Kids to Uids beginn:
	public void writeTypeOfDeclaration(String areaCode, String typeOfPermit) throws XMLStreamException {
		if (Utils.isStringEmpty(areaCode) && Utils.isStringEmpty(typeOfPermit)) {
		    return;
		}
		
		openElement("TypeOfDeclaration"); //alle, reihenfolge egal
			////DeclarationCode
			writeElement("RegionCode", areaCode);
			writeElement("ProcedureCode", typeOfPermit);
			////TransitCode
		closeElement(); 
	}

	public void writeMeansOfTransportInland(TransportMeans argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
		
		String mode = argument.getTransportMode();
		
		if (Utils.isStringEmpty(mode)) {
		    return;
		}
		
		openElement("MeansOfTransport");
			setAttribute("TransportType", "Inland");
			writeElement("Type", mode);
		closeElement();
	}

	public void writeMeansOfTransport(TransportMeans argument, String tag) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		String mode = argument.getTransportMode();
		String nr = argument.getTransportationNumber();
		String country = argument.getTransportationCountry();
		String type = argument.getTransportationType();
		
		if (Utils.isStringEmpty(mode) && Utils.isStringEmpty(nr) && 
		        Utils.isStringEmpty(country) && Utils.isStringEmpty(type)) {
            return;
		}

		openElement("MeansOfTransport");
			setAttribute("TransportType", tag);
            writeElement("Identity", nr);
            writeElement("Mode", type);
			writeElement("Nationality", country);
            writeElement("Type", mode);
		closeElement();
	}
	
	public void writeIncoTerms(IncoTerms argument) throws XMLStreamException {		  
		if (argument == null) {
		    return; 
		}
		if (argument.isEmpty()) {
		    return; 
		}	 	
		openElement("Incoterms");
			writeElement("Code", argument.getIncoTerm());
			writeElement("Description", argument.getText());
			writeElement("City", argument.getPlace());	            			
			writeElement("LocationCode", argument.getLocationCode());
		closeElement();
	}

	public void writeAddress(Address argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}

	   	String name = argument.getName();
    	String street = argument.getStreet();
    	String city = argument.getCity();
    	String poco = argument.getPostalCode();
    	String country = argument.getCountry();
    	
    	if (Utils.isStringEmpty(name) && Utils.isStringEmpty(street) && Utils.isStringEmpty(city) && 
    	        Utils.isStringEmpty(poco) && Utils.isStringEmpty(country)) {
    	    return;	
    	}
			 	       	
       	openElement("Address");	  //alle, reihenfolge egal
       		writeElement("Name", name);
       		writeElement("StreetAndNumber", street);  
       		writeElement("PostalCode", poco); 
       		writeElement("City", city); 
       		////District       			
       		writeElement("CountryCodeISO", country);
       		////POBox
       	closeElement();	  	
	}
	public void writeContact(ContactPerson argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
		String identity = argument.getIdentity(); 
	   	String position = argument.getPosition();  
    	String clerk = argument.getClerk();
    	String email = argument.getEmail();
    	String fax = argument.getFaxNumber();
    	String phone = argument.getPhoneNumber();    
    	String city = argument.getCity(); //EI20090608
    	String date = argument.getDate(); //EI20090608
    		
    	if (Utils.isStringEmpty(position) && Utils.isStringEmpty(clerk) && 
    		Utils.isStringEmpty(email) && Utils.isStringEmpty(fax) && Utils.isStringEmpty(phone) &&
    		Utils.isStringEmpty(city) && Utils.isStringEmpty(date) && Utils.isStringEmpty(identity)) {
    	    return;	
    	}
    	
        openElement("Contact");     //alle, reihenfolge egal        	
            writeElement("Identity", identity);   //EI20090609
        	writeElement("Name", clerk);        		
        	writeElement("Function", position);
        	writeElement("Phone", phone);
        	writeElement("Fax", fax);
        	writeElement("Email", email);
        	writeElement("City", city);     //EI20090608
        	writeStringToDate("Date", date);	 //EI20090608        	
        closeElement();
       	  	
	}
	public void writeContact(String sb) throws XMLStreamException {
		
		if (Utils.isStringEmpty(sb)) {
		    return;
		}
		
		openElement("Contact");		
			writeElement("Identity", sb);							
		closeElement(); 
	}
	// EI20090409:
	public void writeParty(String person, Party party) throws XMLStreamException  {
		if (party == null) {
			return;
		}
		
		if (Utils.isStringEmpty(person)) {
			return;
		}
		
		TIN partyTIN = party.getPartyTIN();
		Address adr = party.getAddress();
		ContactPerson contact = party.getContactPerson();
		
		openElement(person);	
			if (party.getPartyTIN() != null) {
				writeElement("CustomerID", partyTIN.getCustomerIdentifier());
				if (!person.equals("Consignee")) {
				    writeElement("CustomsID", partyTIN.getIsTINGermanApprovalNumber());
				}
				writeElement("TIN", partyTIN.getTIN());
			}
			if (adr != null) {
				writeAddress(adr);
			}
			
			if (contact != null) {
				if (!(person.equals("Consignee"))) {
					writeContact(contact);   
				}
			}
		closeElement();	
	}
	
	public void writeParty(String tag, Party party, TIN tin, ContactPerson contact) throws XMLStreamException {
		String tnr = "";
		String is = "";
		String customer = "";
		String etn = "";
		Address adr = null;
		
		if (party != null) {
			adr = party.getAddress();	
			if (tag.equals("Subcontractor")) {
                etn = party.getETNAddress();
			}
		}
		
		if (tin != null)  {
			tnr = tin.getTIN();
			if (!tag.equals("Consignee")) {
                is = tin.getIsTINGermanApprovalNumber();
			}
			customer = tin.getCustomerIdentifier();
		}
		if (Utils.isStringEmpty(etn) && Utils.isStringEmpty(tnr) && Utils.isStringEmpty(is) && 
		        Utils.isStringEmpty(customer) && adr == null && contact == null) {
		    return;
		}
			 
		openElement(tag);
			////AuthorisationID
			writeElement("CustomerID", customer);					
			writeElement("CustomsID", is); 				
			writeElement("ETNAdress", etn);
				
            //ElectronicSignature           
            writeElement("TIN", tnr);
			////VATID
			////Status
			writeAddress(adr);
				
			if (!tag.equals("Consignee")) {
				writeContact(contact);   
			}
		closeElement();
		
	}
	public void writeShipper(TIN argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
		
		String customer = argument.getCustomerIdentifier();
		String tnr = argument.getTIN();
				
		if (Utils.isStringEmpty(customer) && Utils.isStringEmpty(tnr)) {
		    return;
		}
							
		openElement("Shipper");			
			writeElement("CustomerID", customer);
			writeElement("TIN", tnr);
		closeElement();
	}
	
	public void writeShipper(Party party, TIN tin) throws XMLStreamException {
		if (party == null && tin == null) {
		    return;
		}
		
		String customer = tin.getCustomerIdentifier();
		String etn = party.getETNAddress();	
		writeShipper(etn, customer);
	}
	public void writeShipper(String etn, String customer) throws XMLStreamException {
		//Forwarder

		if (Utils.isStringEmpty(etn) && Utils.isStringEmpty(customer)) {
		    return;
		}
							
		openElement("Shipper");				    
			writeElement("CustomerID", etn);
			writeElement("ETNAddress", customer);
		closeElement();
	}

	public void writeShipper(Party party, String message) throws XMLStreamException {
		if (party == null) {
		    return;
		}
    	
    	String vatid = party.getVATNumber();    	
    	if (Utils.isStringEmpty(vatid)) {
    	    return;    	
    	}
    	
    	if (!Utils.isStringEmpty(vatid) && message.equals("ExpDat-CH")) {
    		openElement("Issuer");
    			writeElement("VATID", vatid);
    		closeElement();
    	}   
	}
	public void writeShipmentPeriod(LoadingTime argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		String begin = argument.getBeginTime();
		String end = argument.getEndTime();
		
		if (Utils.isStringEmpty(begin) && Utils.isStringEmpty(end)) {
		    return;
		}
		
		openElement("ShipmentPeriod");
			writeStringToDateTime("Begin", begin);
			writeStringToDateTime("End", end);
		closeElement();
	}

	public void writeHeaderExtensions(Seal argument, String message) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		String tydenseals = argument.getUseOfTydenseals();
		String stock = "";
		if (message.equals("ExpDat")) {
            stock = argument.getUseOfTydensealStock();
		}
		
		if (Utils.isStringEmpty(tydenseals) && Utils.isStringEmpty(stock)) {
		    return;
		}
				
		openElement("HeaderExtensions");
			writeElement("TydenSealsFlag", tydenseals);
			writeElement("TydenSealsStockFlag", stock);
			////EDECRevisionFlag
			////EDECReleaseFlag
		closeElement(); // HeaderExtensions
	}
	
	//EI20090310
	public void writeItinerary(Route argument) throws XMLStreamException {
		if (argument == null) { 
            return;
		}
				
		List countryList = argument.getCountryList();
		if (countryList == null) {
			return;
		}
		
		int size = countryList.size();
		if (size > 0) {
		openElement("Itinerary");
			for (int i = 0; i < size; i++) {
				String country = (String) countryList.get(i);
				writeElement("CountryOfRouting", country);  
			}
		closeElement(); 
		}
	}
	//EI20090310
	public void writeExportRestitutionHeader(ExportRefundHeader argument)	throws XMLStreamException {
		if (argument == null) {
            return;
		}
		
		String remark = argument.getText();
		String application = argument.getPaymentKids();
		String payment = argument.getBankNumber();	
		String AIDAcode = argument.getAssignee();
		String AIDAaccount = argument.getGuarantee();
		String pending = argument.getReservationCode();
		String country = argument.getDestinationCountry();
		
		if (Utils.isStringEmpty(remark) && Utils.isStringEmpty(application) &&
			Utils.isStringEmpty(payment) && Utils.isStringEmpty(AIDAcode) &&	
			Utils.isStringEmpty(AIDAaccount) && Utils.isStringEmpty(pending) &&
			Utils.isStringEmpty(country)) {
			return;
		}
		
		openElement("ExportRestitutionHeader");
			writeElement("Remark", remark); 
			writeElement("ApplicationType", application); 
			writeElement("PaymentType", payment);
			writeElement("AIDACode", AIDAcode);  			  
			writeElement("AIDAAccount", AIDAaccount);  
			writeElement("RestitutionPending", pending);  
			writeElement("CountryOfRouting", country);    //gibt es nicht in UIDS-Definition
		closeElement(); 		                					
	}
	
	//EI20090310
	public void writeExportRestitutionItem(ExportRefundItem erItem, ApprovedTreatment appTreatment) 
	                                                                        throws XMLStreamException {
		if (erItem == null && appTreatment == null) { 
            return;
		}
		
		String description1 = "";
		String description2 = "";
		String procedure = "";
		String estimated = "";
		String amount = "";
		String measurement = "";
		String country = "";
		String budget = "";
		String coeficient = "";
		String equityA = "";
		String equityB = "";
		String equityC = "";
		String equityD = "";
		Ingredients ingredients = null;
		List ingredientsList = null;
		int listSize = 0;
		
		if (erItem != null) {
			description1 = erItem.getAddition1();
			description2 = erItem.getAddition2();
			estimated = erItem.getAmountCode();
			amount = erItem.getAmount();
			measurement = erItem.getUnitOfMeasurement();
			country = erItem.getOriginCountry();
			budget = erItem.getTypeOfRefund();
			coeficient = erItem.getAmountCoefficient();
			equityA = erItem.getPartA();
			equityB = erItem.getPartB();
			equityC = erItem.getPartC();
			equityD = erItem.getPartD();			
			ingredientsList = erItem.getIngredientsList();
			if (ingredientsList != null) {
				listSize = ingredientsList.size();
			}
		}
		if (appTreatment != null) {
			procedure = appTreatment.getCodeForRefund();			
		}
		
		if (Utils.isStringEmpty(description1) && Utils.isStringEmpty(description2) &&
			Utils.isStringEmpty(estimated) && Utils.isStringEmpty(amount) &&	
			Utils.isStringEmpty(measurement) && Utils.isStringEmpty(country) &&
			Utils.isStringEmpty(budget) && Utils.isStringEmpty(coeficient) &&
			Utils.isStringEmpty(equityA) && Utils.isStringEmpty(equityB) &&
			Utils.isStringEmpty(equityC) && Utils.isStringEmpty(equityD) &&			
			Utils.isStringEmpty(procedure)  && listSize == 0) {
            return;
		}
		
		openElement("ExportRestitutionItem");
			writeElement("GoodsDescriptionExt", description1); 
			writeElement("GoodsDescriptionExt", description2); 
			writeElement("RestitutionProcedure", procedure);
			writeElement("Estimated", estimated); 
			writeElement("Amount", amount);
			writeElement("Measurement", measurement);  			  
			writeElement("OriginCountry", country);  
			writeElement("BudgetControl", budget);  
			writeElement("Coeficient", coeficient);  			  
			writeElement("Equity", equityA);  
			writeElement("Equity", equityB);  
			writeElement("Equity", equityC);  
			writeElement("Equity", equityD);  
			if (listSize > 0) {
				for (int i = 0; i < listSize; i++) {
					ingredients = (Ingredients) ingredientsList.get(i);
					writeIngredients(ingredients);  
				}
			}
		closeElement(); 		                					
	}
	//EI20090310:
	public void writeIngredients(Ingredients argument)throws XMLStreamException {
		if (argument == null) {
            return;
		}
				
		String factor1 = argument.getAmount1();
		String indicator = argument.getKindOfCoefficient();
		String factor2 = argument.getAmount2();
		String factor3 = argument.getRate();		
		String percent = argument.getWeightPercent();
		String amount = argument.getWeight();		
		String recipe = argument.getUniqueFactoryNumber();
		String key = argument.getTarifNumber();
		String license = argument.getLicenceNumber();
		String description = argument.getText();
		
		if (Utils.isStringEmpty(factor1) && Utils.isStringEmpty(factor2) &&
			Utils.isStringEmpty(factor3) && Utils.isStringEmpty(indicator) &&	
			Utils.isStringEmpty(percent) && Utils.isStringEmpty(amount) &&
			Utils.isStringEmpty(recipe) && Utils.isStringEmpty(key) &&
			Utils.isStringEmpty(license) && Utils.isStringEmpty(description)) {
			return;
		}
		
		openElement("ExportRestitutionHeader");
			writeElement("CalculationFactor1", factor1); 
			writeElement("DivisionIndicator1", indicator); 
			writeElement("CalculationFactor2", factor2);
			writeElement("CalculationFactor3", factor3); 
			writeElement("WeightPercentage", percent);  			  
			writeElement("AmountPart", amount);  
			writeElement("RecipeID", recipe);  
			writeElement("Key", key); 
			writeElement("IngredientLicense", license); 			
			writeElement("IngredientDescription", description);   
		closeElement(); 		
	}
	//EI20090310
	public void writeWriteOffATLAS(Completion bwCompletion, Completion ipCompletion) throws XMLStreamException {
		if (bwCompletion == null && ipCompletion == null) {
            return;	
		}

		String allowance = ""; //in beiden bwc/ipc muss das gleiche sein
		String reference = ""; //in beiden bwc/ipc muss das gleiche sein
		List bwcList = null;
		int bwcSize = 0;
		List ipcList = null;
		int ipcSize = 0;
						
		if (bwCompletion != null) {
			reference = bwCompletion.getReferenceNumber();
			allowance = bwCompletion.getAuthorizationNumber();
			bwcList = bwCompletion.getCompletionItemList();
			if (bwcList != null) {
				bwcSize = bwcList.size();
			}
		}
		if (ipCompletion != null) {
			allowance = ipCompletion.getAuthorizationNumber(); 
			ipcList = ipCompletion.getCompletionItemList();   //EI20090817 stand bwCompletion...
			if (ipcList != null) {
				ipcSize = ipcList.size();
			}
		}
		
		if (Utils.isStringEmpty(allowance) && Utils.isStringEmpty(reference) &&
				bwcSize == 0 && ipcSize == 0) {
			return;
		}
		
		openElement("WriteOffATLAS");
			writeWriteOff("WriteOffZL", bwCompletion);
			writeWriteOff("WriteOffAVUV", ipCompletion);
		closeElement();
	}
	
	//EI20090310
	public void writeWriteOff(String tag, Completion argument)	throws XMLStreamException {
		if (argument == null) {
            return;	
		}

		String allowance = argument.getAuthorizationNumber();
		String reference = argument.getReferenceNumber();
		List completionItemList = argument.getCompletionItemList();
		int listSize = 0;
		
		if (completionItemList != null) {
			listSize = completionItemList.size();
		}
		
		if (Utils.isStringEmpty(allowance) && Utils.isStringEmpty(reference) && listSize == 0) {
			return;	
		}
		
		openElement(tag);
			writeElement("Allowance", allowance);
			if (tag.equals("WriteOffZL")) {
				writeElement("Reference", reference);
			}
			for (int i = 0; i < listSize; i++) {
				writeCompletionItem(tag, (CompletionItem) completionItemList.get(i));
			}
		closeElement();		
	}
	
	//EI20090310
	public void writeCompletionItem(String tag, CompletionItem argument)	throws XMLStreamException {
		if (argument == null) {
            return;	
		}

		String itemNumber = argument.getPositionNumber();
		String regNumber = argument.getUCR();
		String tarif = argument.getTarifNumber();
		String handlingCode = argument.getUsualFormOfHandling();
		String atlasCode = argument.getEntryInAtlas();
		String text = argument.getText();
		WriteDownAmount wdAmount = argument.getWriteDownAmount();
		WriteDownAmount tAmount = argument.getTradeAmount();
		
		String unitQualifier = "";
		String unit = "";
		String value = "";
		String handlingQualifier = "";
		String handlingUnit = "";
		String handlingValue = "";
		
		if (wdAmount != null) {
			//EI20090312: unitQualifier = wdAmount.getQualifier();
			unit = wdAmount.getUnitOfMeasurement();
			//EI20090818: value = wdAmount.getValueKids();
			value = wdAmount.getWriteoffValue();  //EI20090818
		}
		if (tAmount != null) {			
			//EI20090312: handlingQualifier = tAmount.getQualifier();
			handlingUnit = tAmount.getUnitOfMeasurement();
			//handlingValue = tAmount.getValueKids();
			handlingValue = tAmount.getWriteoffValue();
		}
		
		if (Utils.isStringEmpty(itemNumber) && Utils.isStringEmpty(regNumber) &&
			Utils.isStringEmpty(tarif) && Utils.isStringEmpty(handlingCode) &&
			Utils.isStringEmpty(text) && Utils.isStringEmpty(atlasCode) &&
			Utils.isStringEmpty(unitQualifier) && Utils.isStringEmpty(unit) &&
			Utils.isStringEmpty(value) && Utils.isStringEmpty(handlingQualifier) &&
			Utils.isStringEmpty(handlingUnit) && Utils.isStringEmpty(handlingValue)) {
			return;
		}
		
		openElement("WriteOffData");
			writeElement("WriteOffItemNumber", itemNumber);
			writeElement("WriteOffRegNumber", regNumber);
			if (tag.equals("WriteOffZL")) {
				writeElement("WriteOffTariffCode", tarif);
				writeElement("HandlingCode", handlingCode);
			}
			writeElement("ATLASCode", atlasCode);
			writeElement("AdditionalText", text);
			if (tag.equals("WriteOffZL")) {
				//EI20090312: writeElement("WriteOffUnitQualifier", unitQualifier);
				writeElement("WriteOffUnit", unit);
				writeElement("WriteOffValue", value);
				writeElement("HandlingUnitQualifier", handlingQualifier);
				writeElement("HandlingUnit", handlingUnit);
				writeElement("HandlingValue", handlingValue);
			}
		closeElement();
		
	}
	
	public void writeProcedurePrevious(String argument) throws XMLStreamException {
		if (Utils.isStringEmpty(argument)) {
            return;
		}
		
		openElement("Procedure");
			////Declared
			writeElement("Previous", argument);
			////Supplemental
			////Additional
		closeElement();
	}

	public void writeProcedure(ApprovedTreatment argument) throws XMLStreamException {		
		if (argument == null) {
		    return;
		}
		
		String decl = argument.getDeclared();
		String prev = argument.getPrevious();
		String nat = argument.getNational();
		String nat2 = argument.getNational2();
		String nat3 = argument.getNational3();
		String com = argument.getCommunity();
		String com2 = argument.getCommunity2();
		String com3 = argument.getCommunity3();
		
		if (Utils.isStringEmpty(decl) && Utils.isStringEmpty(prev) && Utils.isStringEmpty(nat)) {
            return;
		}
		
		openElement("Procedure");
			writeElement("Declared", decl);
			writeElement("Previous", prev);
			writeElement("Additional", nat);
			writeElement("Additional2", nat2);
			writeElement("Additional3", nat3);
			writeElement("Community", com);
			writeElement("Community2", com2);
			writeElement("Community3", com3);
			
		closeElement();
	}

	public void writeSeals(Seal argument) throws XMLStreamException {		 
		if (argument == null) {
		    return;
		}
		if (argument.isEmpty()) {
		    return;
		}		
		//List <SealNumber>tmpSealList =  s.getSealNumbersList();
		List <String>tmpSealList =  argument.getSealNumberList();
		
		openElement("Seals");
			writeElement("Type", argument.getKind());
			writeElement("Quantity", argument.getNumber());
			
			if (tmpSealList != null) {					
				for (String snr : tmpSealList) {												
					if (!Utils.isStringEmpty(snr)) {
						openElement("SealsID");
							writeElement("Identity", snr);
							////Language
						closeElement();
					}
				}					
			}			
		closeElement(); // Seals
	}	

	public void writeTransaction(Business argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		String code = argument.getBusinessTypeCode();
		String price = argument.getInvoicePrice();
		String curr = argument.getCurrency();

		if (Utils.isStringEmpty(code) && Utils.isStringEmpty(price)  && Utils.isStringEmpty(curr)) {
		    return;
		}
		
		openElement("Transaction");
			writeElement("Type", code);
			////AdditionalType
			writeElement("Amount", price);
			writeElement("Currency", curr);            				
		closeElement(); // Transaction
	}
	public void writeCustomsOffices(String oExport, String oCompletion, 
	                                String oExit, String actExit) throws XMLStreamException {	
		if (Utils.isStringEmpty(oExport) && Utils.isStringEmpty(oCompletion) &&
			Utils.isStringEmpty(oExit) && Utils.isStringEmpty(actExit)) {
            return;		
		}
		
		openElement("CustomsOffices");		
		    writeElement("OfficeOfActualExit", actExit);
			writeElement("OfficeOfAdditionalDeclarationExport", oCompletion);
            writeElement("OfficeOfExit", oExit);     //for "ExpEAM" is rExit==null, the Tag won't be visible            
            writeElement("OfficeOfExport", oExport);            
		closeElement(); 
	}	

	public void writePlaceofLoading(PlaceOfLoading argument) throws XMLStreamException {		 		
		if (argument == null) {
		    return;
		}
		
		String code = argument.getCode();
		String street = argument.getStreet();
		String poco = argument.getPostalCode();
		String city = argument.getCity();
		String location = argument.getAgreedLocationOfGoods();
		
		if (Utils.isStringEmpty(code) && Utils.isStringEmpty(street) && Utils.isStringEmpty(poco) && 
		        Utils.isStringEmpty(city) && Utils.isStringEmpty(location)) {
            return;
		}
		
		openElement("PlaceOfLoading");
			writeElement("Code", code);
			if (!Utils.isStringEmpty(street) && !Utils.isStringEmpty(poco) && !Utils.isStringEmpty(city)) { 
				openElement("Address");
					writeElement("StreetAndNumber", street);
					writeElement("PostalCode", poco);
					writeElement("City", city);
		        closeElement(); // Address 
			} 
			writeElement("Addition", location);
		closeElement(); //PlaceOfLoading
	}

	public void writePackaging(Packages argument, String message) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		String quantity = argument.getQuantity();
		String nr = argument.getSequentialNumber();
		String type = argument.getType();
		String marks = argument.getMarks();
		
		if (Utils.isStringEmpty(quantity) && Utils.isStringEmpty(nr) && 
		        Utils.isStringEmpty(type) && Utils.isStringEmpty(marks)) {
            return;
		}
		
		openElement("Packaging");  //sequence
		    writeElement("Quantity", quantity);
            ////Identifier
            ////Item....
			if (!message.equals("ExitNotification")) {
			    writeElement("MarksAndNumbers", marks);
			}
            writeElement("Number", nr);             
            if (!message.equals("ExitNotification")) {
                writeElement("Type", type); 
            }
		closeElement();
	}	
	public void writeCommodityCode(CommodityCode argument) throws XMLStreamException {
		 if (argument == null) {
		     return;
		 }
		 
		 String tarif = argument.getTarifCode();
		 String teu = argument.getEUTarifCode();
		 String ta1 = argument.getTarifAddition1();
		 String ta2 = argument.getTarifAddition2();
		 String add = argument.getAddition();
		 
		 if (Utils.isStringEmpty(tarif) && Utils.isStringEmpty(teu) && Utils.isStringEmpty(ta1) && 
		         Utils.isStringEmpty(ta2) && Utils.isStringEmpty(add)) {
	            return;
		 }
		 
	     writeElement("CommodityCodeKN8", tarif);
	     writeElement("CommodityCodeTARIC", teu);
	     writeElement("CommodityCodeFirstAddition", ta1);
	     writeElement("CommodityCodeSecondAddition", ta2);
	     writeElement("CommodityCodeNationalAddition", add);
	     ////DestinationCountry	
	}
	  
	public void writePreviousDocument(PreviousDocument argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		
		String reference = argument.getMarks();
		String information = argument.getAdditionalInformation();
		String type = argument.getType();
		String date = argument.getDate();
		
		if (Utils.isStringEmpty(reference) && Utils.isStringEmpty(information) && Utils.isStringEmpty(type)) {
			return;
		}
		
		//openElement("PreviousDocument");
			// CK081003
				openElement("PreviousDocument");
				writeElement("Type", type);
				if (!(Utils.isStringEmpty(reference) && Utils.isStringEmpty(information))) {
					openElement("Document");
						writeElement("DateOfCreation", date);
						writeElement("Reference", reference);
						writeElement("Remark", information);
					closeElement();
				}
				closeElement();  //PreviousDocument		
		//closeElement();  // Document
	}
		
	// writeDocument
	public void writeProducedDocument(Document argument)throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		String category = argument.getTypeKids();   //EI20090409
		String idate = argument.getIssueDate();
		String edate = argument.getExpirationDate();
		String referenz = argument.getReference();
		String information = argument.getAdditionalInformation();
		String type = argument.getQualifier();
		String detail = argument.getDetail();                  //EI20090310
		String value = argument.getValue();                    //EI20090310
		WriteDownAmount wda = argument.getWriteDownAmount();   //EI20090310
		String present = argument.getPresent();
		String presentL = argument.getPresentLocation();

		if (Utils.isStringEmpty(type) && Utils.isStringEmpty(idate) && Utils.isStringEmpty(edate) && 
		        Utils.isStringEmpty(referenz) && Utils.isStringEmpty(information) && 
		        Utils.isStringEmpty(category) && Utils.isStringEmpty(detail) && 
		        Utils.isStringEmpty(value) && wda == null) {
		    return;				
		}
		
		openElement("ProducedDocument");
			openElement("Document");
				////sehr viele Tags
				writeElement("Category", category);				
                writeStringToDate("DateOfCreation", idate);
				writeStringToDate("DateOfValidity", edate);					
				writeElement("Reference", referenz);
				writeElement("Remark", information);
				writeElement("Detail", detail);
				writeElement("Type", type);
				if (!(Utils.isStringEmpty(value) && wda == null)) {
					openElement("Writeoff");
					    if (wda != null)  { //EI20090818
					    	//writeElement("Measurement", wda.getUnitOfMeasurement());
					        //EI: oder soll es  "Unit" heisen? Unit ist in UIDS-Beschreibung 
					    	writeElement("Unit", wda.getUnitOfMeasurement());
					    }
						writeElement("Value", value);	
						//EI20090818: writeElement("WriteoffValue", wda.getValueKids());	
						
						if (wda != null)  { //EI20090818
						    writeElement("WriteoffValue", wda.getWriteoffValue());  //EI20090818
						}
					closeElement();
				}
				writeElement("Present", present);
				writeElement("PresentLocation", presentL);
			closeElement();  //Document			
		closeElement();  //ProducedDocument
	}
		

	public void writeContainer(Container argument) throws XMLStreamException {		
		if (argument == null) {
		    return;
		}
		List list = argument.getNumberList();
		if (list == null) {
		    return;
		}
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			writeElement("ContainerRegistration", (String) list.get(i));
		}		
	}
	public void writePrevious(String argument) throws XMLStreamException {
		if (Utils.isStringEmpty(argument)) {
            return;
		}		
		openElement("Procedure");
			writeElement("Previous", argument);
		closeElement();
	}
		
	 public void writePDFInformationList(List<PDFInformation> list) throws XMLStreamException {   //EI20110811        
	    	if (list == null) {
	    	    return;
	    	}
	    	if (list.isEmpty()) {
	    	    return;
	    	}
	    	for (PDFInformation pfd : list) {
	    		writeOnePDFInformation(pfd);
	    	}
	 }
	  public void writeOnePDFInformation(PDFInformation pdf) throws XMLStreamException {    //EI20110811        
	    	if (pdf == null) {
	    	    return;
	    	}
	    	if (pdf.isEmpty()) {
	    	    return;
	    	}
	    	openElement("PDF");    	
	    		writeElement("Name", pdf.getName());      	
	    		writeElement("Directory", pdf.getDirectory());  
	    		writeElement("Remark", pdf.getRemark());   	
	    		writeElement("SubDirectory", pdf.getNewDirectory());	    		
				
				if (pdf.getPdflist() != null) {
					List <String> pdfList = pdf.getPdflist(); 
					if (!pdfList.isEmpty()) {
						// Wenn der Kunde lt. Eintrag in DB ein tgz-file will
						// dann PDF-Information nicht einbetten
						if (commonFieldsDTO != null && commonFieldsDTO.isPdfTgz()) {
							writePdffile(pdfList);
							Utils.log("PDF nicht einbinden");
							commonFieldsDTO.setPdfExists(true);
						} else {
							for (String base64 : pdfList)  {
								writeElement("base64", base64); 
							}
						}
					} else {
	                Utils.log("(KidsMessage one PDFInformation from PDFInformationList) pdfList is empty");
					}
				}   			
			closeElement();  
	    }
	 
	public void writePdfInformation(PDFInformation argument) throws XMLStreamException {
		//EI20100520: commonFieldsDTO.setPdfExists(false);
		if (commonFieldsDTO != null) {   //EI20100520
			commonFieldsDTO.setPdfExists(false);
		}
		
	    if (argument == null) {
	        return;
	    }
	   
	    String name = argument.getName();
	    String dir = argument.getDirectory();
	    String newdir = argument.getNewDirectory();
	    List <String> pdfList = argument.getPdflist();
	    int listSize =  0;
	    
	    if (Utils.isStringEmpty(name) && Utils.isStringEmpty(dir)  && 
	            Utils.isStringEmpty(newdir) && pdfList == null) {
	    	return;
	    }
	   		
	    openElement("PDF");    	
	    	writeElement("Name", name);      	
	    	writeElement("Directory", dir); 
	    	writeElement("Remark", argument.getRemark());   	
	    	writeElement("SubDirectory", newdir);
	    	
	    	if (pdfList != null) {	    		
	    		listSize = pdfList.size();
	    		if (listSize > 0) {
	    		    // CK090617 wenn Kunde tgz-file will - lt. Eintrag in DB - PDF-Information nicht einbetten
	    			if (commonFieldsDTO != null && commonFieldsDTO.isPdfTgz()) {
	    				writePdffile(pdfList);
	    				Utils.log("PDF nicht einbinden");
	    				commonFieldsDTO.setPdfExists(true);
	    			} else {
		    			for (int i = 0; i < pdfList.size(); i++)  {
		    				writeElement("base64", pdfList.get(i)); 
		    			}
	    			}
	    		} else {
	    			Utils.log("(BodyExportReleaseUids writeBody) pdflist is empty");
	    		}
	    	}
	    closeElement();    	
	}	
	
   //--------------------------------
	public void writeStringToDateTime(String tag, String dateString)  throws XMLStreamException {
		// dateString should be as "YYYYMMDDHHMM" == KIDS_DateTime	
		// dateString will be konverted to formated date ==> ST_DateTime
		
		if (dateString == null || Utils.isStringEmpty(dateString)) {
			return;
		}
		
		//EI20100531: if dateString is formated:
		if (dateString.contains(":") || dateString.contains("-")) {    //EI20100531
			 writeElement(tag, dateString);
			 return;
		}
		
		String datetime = ""; 
		KcxDateTime kcx;
				
		dateString.trim();   			
		if (dateString.length() == 8) {
			dateString = dateString + "0000";
		} else if (dateString.length() == 14) {
			//dateString = dateString.substring(0, 11);
			dateString = dateString.substring(0, 12);   //EI20111017
		}
					
		try {
			kcx = new KcxDateTime(EFormat.KIDS_DateTime, dateString);
			datetime = kcx.format(EFormat.ST_DateTimeT);
			//uidsDate = kcx.format(EFormat.ST_Date);
			writeElement(tag, datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	 public void writeStringToDate(String tag, String dateString)  throws XMLStreamException {
			// dateString should be as "YYYYMMDD" == KIDS_Date					   
		 
		if (dateString == null || Utils.isStringEmpty(dateString)) {
			return;
		}
		
		//EI20100531: if dateString is formated:
		if (dateString.contains("-") || dateString.contains(":")) {    //EI20100531
			writeElement(tag, dateString);
			return;
		}
			
		String date = ""; //dateString will be converted to formated date ==> ST_Date
		KcxDateTime kcx;
										
		dateString.trim();   							
		if (dateString.length() > 8) {        //such a: YYYYMMDDHHmmss	
			dateString = dateString.substring(0, 8);  //EI20110928 it was 7 instead of 8
		}
						
		try {
			kcx = new KcxDateTime(EFormat.KIDS_Date, dateString);
			date = kcx.format(EFormat.ST_Date);
			writeElement(tag, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	 }
 
	public void writeStringToTime(String tag, String timeString) throws XMLStreamException {
			// timeString should be as "HHmmss" == KIDS_Time
					
		if (timeString == null || Utils.isStringEmpty(timeString)) { 
			return;
		}
		
		//EI20100531: if timeString is formated:
		if (timeString.contains("-") || timeString.contains(":")) {    //EI20100531
			writeElement(tag, timeString);
			return;
		}
					 
		String time = ""; //timeString will be converted to formated time ==> ST_Time_Sec
		KcxDateTime kcx;				
				
		timeString.trim();                
		if (timeString.length() > 6) {		//such a: HHmmss.123		
			timeString = timeString.substring(0, 6);
		}		
		if (timeString.length() == 4) {		//EI20110928	
			timeString = timeString  + "00";
		}  
		if (timeString.length() < 4) {		//EI20110928		
			return;
		} 
		try {
			kcx = new KcxDateTime(EFormat.KIDS_Time, timeString);
			time = kcx.format(EFormat.ST_Time_Sec);
			writeElement(tag, time);
		} catch (ParseException e) {
			e.printStackTrace();
		}						 				
	}
	
    public void writeFormattedDateOrTime(String tag, String dateOrTimeString, EFormat currentFormat,
    		EFormat resultFormat) throws XMLStreamException {
    	KcxDateTime kcx;
		try {
			kcx = new KcxDateTime(currentFormat, dateOrTimeString);
	    	writeElement(tag, kcx.format(resultFormat)); 
		} catch (ParseException e) {
            Utils.log(Utils.LOG_ERROR, String.format(
            		"Date/Time string '%s' for element '%s' could not be processed for output - %s",
            		dateOrTimeString,
            		tag,
            		e.getLocalizedMessage()));
		}
    }
    public void writeFormattedDateTime(String tag, String dateTimeString, EFormat currentFormat,
			EFormat resultFormat) throws XMLStreamException {
    	if (dateTimeString == null || Utils.isStringEmpty(dateTimeString)) {
    		return;
    	}
    	if (currentFormat == null || resultFormat == null) {
    		if (dateTimeString.length() <= 6) {
    			writeStringToTime(tag, dateTimeString);    
    		} else if (dateTimeString.length() == 8) {
    			writeStringToDate(tag, dateTimeString);    
    		} else if (dateTimeString.length() >= 12) {
    			writeStringToDateTime(tag, dateTimeString);    
    		} 
    	} else {
    		writeFormattedDateOrTime(tag, dateTimeString, currentFormat, resultFormat);
    	}
    }
    
    
 ///from Kids to Uids end   

    public CommonFieldsDTO getCommonFieldsDTO() {
        return commonFieldsDTO;
    }

    public void setCommonFieldsDTO(CommonFieldsDTO commonFieldsDTO) {
        this.commonFieldsDTO = commonFieldsDTO;
    }
    /**
     * Schreibt das version und encoding tag einer xml
     * mit doppelten hochkomma anstelle von einfachen hochkomma.
     * @param encoding
     * @param version
     * @throws XMLStreamException
     */
    protected void writeStartDocument(String encoding, String version) throws XMLStreamException {
		writer.writeDTD("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"?>");
	}
    
    public void writePdffile(List <String> pdfList) {

    	StringBuffer buff = new StringBuffer();
    	String str = "";
    	String name = "";
    	
    	for (int i = 0; i < pdfList.size(); i++)  {
    		str = pdfList.get(i);
    		// str = str.replaceFirst("\\n", "");
    		buff.append(str);
		}
    	String filename = commonFieldsDTO.getFilename();

    	if (filename.lastIndexOf(".xml") > 0) {
			name = filename.substring(0, filename.lastIndexOf(".xml"));	
        } else {
        	name = filename;
        }
    	
    	byte[] out = Base64.decodeBase64(buff.toString().getBytes());
//        str = new String(out);
        
        try {
			FileOutputStream fos = new FileOutputStream(Config.getPdfpath() + "/" + name + ".pdf");
			fos.write(out);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//		File file;
//			file = new File(Config.getPdfpath(), name + ".pdf");
//
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			PrintWriter writer = new PrintWriter(file);
//			writer.write(Base64.decodeBase64(buff.toString().getBytes()).toString());
//			writer.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }
    public void writePdffileTmp(List <String> pdfList) {

    	StringBuffer buff = new StringBuffer();
    	String str = "";
    	String name = "";
    	
    	for (int i = 0; i < pdfList.size(); i++)  {
    		str = pdfList.get(i);
    		buff.append(Base64.decodeBase64(str.getBytes()).toString() + Utils.LF);  
		}
    	String filename = commonFieldsDTO.getFilename();

    	if (filename.lastIndexOf(".xml") > 0) {
			name = filename.substring(0, filename.lastIndexOf(".xml"));	
        } else {
        	name = filename;
        }
    	byte[] out = Base64.decodeBase64(buff.toString().getBytes());
        str = new String(out);
        
        try {
			FileOutputStream fos = new FileOutputStream(Config.getPdfpath() + "/" + name + ".TmpPdf");
			fos.write(out);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			File file;
//			file = new File(Config.getPdfpath(), name + "Tmp.pdf");
//
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//			PrintWriter writer = new PrintWriter(file);
//			writer.write(buff.toString());
//			writer.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }
    public void writeWareHouse(WareHouse wareHouse) throws XMLStreamException {
    	if (wareHouse == null) {
    	    return;
    	}
    	
    	String type = wareHouse.getWarehouseType();
    	String identification = wareHouse.getWarehouseIdentification();
    	String country = wareHouse.getWarehouseCountry();
    	
    	if (Utils.isStringEmpty(type) && Utils.isStringEmpty(identification) && Utils.isStringEmpty(country)) {
    		return;
    	}
    	
    	openElement("Warehouse");
    		writeElement("CountryCodeISO", country);      	
    		writeElement("Identity", identification);      	
    		writeElement("Type", type);      	
    	closeElement();
    }
    

}

