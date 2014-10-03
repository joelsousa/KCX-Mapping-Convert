package com.kewill.kcx.component.mapping.formats.uids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgChangeOfDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReminderMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AttributesType;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ChangedDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ComplementConsignee;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.DocumentCertificate;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsPackage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EvidenceOfEvent;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExportAcceptance;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.HeaderEaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.SubmittingPerson;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.TransportDetails;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.UpdateEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.WineProduct;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.UnsatisfactoryReason;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Diagnosis;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AadVal;

import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS.<br>
 * Created 		: 04.05.2010<br>
 * Description	: Fields and methods to write EMCS-UidsMessages .
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class UidsMessageEMCS extends UidsMessage {

    public void writeTransportDetails(TransportDetails argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	
    	String code = argument.getTransportUnitCode();
    	String units = argument.getIdentityOfTransportUnits();
    	String sealId = argument.getCommercialSealIdentification();
    	Text info = argument.getComplementaryInformation();
    	Text sealInfo = argument.getSealInformation();
    	
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	openElement("TransportDetails");
    		writeElement("TransportUnitCode", code);      	
    		writeElement("IdentityOfTransportUnits", units);      	
    		writeElement("CommercialSealIdentification", sealId);
    		
    		if (info != null) {
    			writeElementWithAttribute("ComplementaryInformation", info.getText(), "language", info.getLanguage());
    		}
    		if (sealInfo != null) {
    			writeElementWithAttribute("SealInformation", sealInfo.getText(), "language", sealInfo.getLanguage());
    		}
    	closeElement();
    }
    
    public void writeTransportDetailsList(List <TransportDetails> list) throws XMLStreamException {    	
		if (list == null) {
    		return;
		}    
		int size = list.size();
		for (int i = 0; i < size; i++) {					
			writeTransportDetails((TransportDetails) list.get(i));
		}
    }
 //---------
    
	public void writeTrader(String person, EmcsTrader trader, String version) throws XMLStreamException {
      	if (trader == null) {
    		return;
    	}
    	if (Utils.isStringEmpty(person)) {
    		return;
    	}
    	
    	String customer = trader.getCustomerID();
    	String tax = trader.getExciseTaxNumber();
    	String vat = trader.getVatId();
    	String name = trader.getName();
    	String street = trader.getStreet();
    	String number = trader.getStreetNumber();
    	String plz = trader.getPostalCode();
    	String city = trader.getCity(); 
    	String country = trader.getCountryCodeISO();
    	//EI20110930: String language = trader.getLanguage();       //EI20110928
    	
    	String streetAndNumber = street;
    	if (!Utils.isStringEmpty(number)) {
    		streetAndNumber = street + " " + number;
    	}
    	String dummy = "";  
    	
    	if (Utils.isStringEmpty(country) && Utils.isStringEmpty(customer) && Utils.isStringEmpty(tax) &&
    		Utils.isStringEmpty(vat) && Utils.isStringEmpty(name) && Utils.isStringEmpty(street) &&
    		Utils.isStringEmpty(number) && Utils.isStringEmpty(plz) && Utils.isStringEmpty(city)) {
        		return;
    	}
    	
       	openElement(person);      
       	    writeElement("AuthorisationID", dummy);   
       		writeElement("CustomerID", customer);   
       		writeElement("CustomsID", dummy);   
       		writeElement("ETNAddress", dummy);   
       		writeElement("ElectronicSignature", dummy);  
       		writeElement("TIN", dummy);   
       		writeElement("ExciseTaxNumber", tax);  //nicht in xsd!!!
       		writeElement("VATID", vat);  
       		writeElement("Status", dummy);   
       		
        	if (Utils.isStringEmpty(name) && Utils.isStringEmpty(street) && Utils.isStringEmpty(number) && 
        		Utils.isStringEmpty(plz) && Utils.isStringEmpty(city) && Utils.isStringEmpty(country)) {
        	} else {	
        		openElement("Address");
        			writeElement("Name", name);    
        			if (version.equals("10")) {    //EI20110711
        				writeElement("StreetAndNumber", streetAndNumber); 
        			} else {
        				writeElement("Street", street); 
        				//writeElement("StreetNumber", number); 
        				writeElement("HouseNumber", number);  //EI20110928
        			}
        			writeElement("PostalCode", plz); 
        			writeElement("City", city); 
        			//writeElement("CountryCodeISO", country);  
        			writeElement("Language", country);    //EI20100612
        		closeElement(); 
        	}
        	//TODO Contact
        	
		closeElement();  
    }
	
    public void writeComplementConsignee(ComplementConsignee argument) throws XMLStreamException {
    	
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	openElement("ComplementConsigneeTrader");
    		writeElement("MemberStateCode", argument.getMemberStateCode());    
    		writeElement("CertificateOfExemption", argument.getCertificateOfExemption());
    	closeElement();
    }     
  //-----------
   public void writeEaadDraft(MsgEMCSDeclaration argument) throws XMLStreamException {
	   
	   	if (argument == null) {
	   		return;
	   	}
 
	   	EaadDraft temp = new EaadDraft();
	   	
	   	temp.setDateOfDispatch(argument.getDateOfDispatch()); 
	   	temp.setTimeOfDispatch(argument.getTimeOfDispatch());
	   	temp.setOriginTypeCode(argument.getOriginTypeCode());
	   	temp.setInvoiceNumber(argument.getInvoiceNumber());
	   	temp.setInvoiceDate(argument.getInvoiceDate());
	   	temp.setImportSadList(argument.getImportSadList());
	   	
	   	writeEaadDraft(temp);
   }   
   public void writeEaadDraft(MsgValidDeclaration argument) throws XMLStreamException {
	   
	   	if (argument == null) {
	   		return;
	   	}

	   	EaadDraft temp = new EaadDraft();
	   	
	   	temp.setDateOfDispatch(argument.getDateOfDispatch()); 
	   	temp.setTimeOfDispatch(argument.getTimeOfDispatch());
	   	temp.setOriginTypeCode(argument.getOriginTypeCode());
	   	temp.setInvoiceNumber(argument.getInvoiceNumber());
	   	temp.setInvoiceDate(argument.getInvoiceDate());
	   	temp.setImportSadList(argument.getImportSadList());
	   	
	   	writeEaadDraft(temp);
  }      
    public void writeEaadDraft(EaadDraft argument) throws XMLStreamException {
    	
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	String dummy = "";
    	
    	openElement("EaadDraft");
    		writeElement("LocalReferenceNumber", dummy);   
			writeElement("InvoiceNumber", argument.getInvoiceNumber());    
			writeStringToDate("InvoiceDate", argument.getInvoiceDate());
			writeElement("OriginTypeCode", argument.getOriginTypeCode());    
			writeStringToDate("DateOfDispatch", argument.getDateOfDispatch());
			writeStringToTime("TimeOfDispatch", argument.getTimeOfDispatch());
		
			if (argument.getImportSadList() != null) {
				for (int i = 0; i < argument.getImportSadList().size(); i++) {					
					writeElement("ImportSad", (String) argument.getImportSadList().get(i));						
				}
			}
		closeElement();
    }	    
    //----------- 
    public void writeMovementGuarantee(String code, List<EmcsTrader> guarantorList, String version) 
    									throws XMLStreamException {
    	
    	if (guarantorList == null) {
    		return;    	
    	}
    	if (Utils.isStringEmpty(code)) {
    		return;
    	}
    	
    	openElement("MovementGuarantee");
			writeElement("GuarantorTypeCode", code);
			for (int i = 0; i < guarantorList.size(); i++) {					
				writeTrader("GuarantorTrader", guarantorList.get(i), version);						
			}
		closeElement();
    }	 
  //-----------
   public void writeAttributes(MsgEMCSDeclaration argument) throws XMLStreamException {    	
	   	if (argument == null) {
	   		return;
	   	}
	    
	   	AttributesType temp = new AttributesType();	   	
	   	temp.setSubmissionMessageType(argument.getSubmissionMessageType()); 
	   	temp.setDeferredSubmissionFlag(argument.getDeferredSubmissionFlag());
	   	
	   	writeAttributes(temp, "00");
    }
   public void writeAttributes(MsgReminderMessage argument, String version) throws XMLStreamException {    	
	   	if (argument == null) {
	   		return;
	   	}	    
	   	AttributesType temp = new AttributesType();	   	
	   	temp.setDateAndTimeOfIssuance(argument.getDateAndTimeOfIssuance()); 
	   	temp.setLimitDateAndTime(argument.getLimitDateAndTime());	   	
	   	temp.setReminderInformation(argument.getReminderInformation());
	   	temp.setReminderMessageType(argument.getReminderMessageType());
	   	
	   	writeAttributes(temp, version);
   }   
    
    public void writeAttributes(AttributesType argument, String version) throws XMLStreamException {
    	
    	if (argument == null) {
    		return;    	
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	openElement("Attributes");    		
    		if (version.equals("10")) {
    			writeElement("SubmissionMessageType", argument.getSubmissionMessageType());   
    			writeStringToDateTime("DateAndTimeOfIssuanceOfReminder", argument.getDateAndTimeOfIssuance());
    			if (argument.getReminderInformation() != null) {
       	   			writeElementWithAttribute("ReminderInformation", argument.getReminderInformation().getText(), 
       									      "language", argument.getReminderInformation().getLanguage());
        		}
    			writeStringToDateTime("LimitDateAndTime", argument.getLimitDateAndTime());  
    			writeElement("ReminderMessageType", argument.getReminderMessageType());
    			//EI20110819: writeElement("SubmissionMessageType", argument.getSubmissionMessageType());  
    			writeStringToDateTime("DateAndTimeOfValidationOfCancellation", 
        							   argument.getDateAndTimeOfValidationOfCancellation());
        		writeElement("DeferredSubmissionFlag", argument.getDeferredSubmissionFlag());      
    		} else {
    			writeElement("SubmissionMessageType", argument.getSubmissionMessageType());  
    			//V20 - DateTime name and format changed:
    			//writeStringToDateTime("DateAndTimeOfIssuance", argument.getDateAndTimeOfIssuance());
    			 writeFormattedDateTime("DateAndTimeOfIssuance", argument.getDateAndTimeOfIssuance(),
										EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    			if (argument.getReminderInformation() != null) {
       	   			writeElementWithAttribute("ReminderInformation", argument.getReminderInformation().getText(), 
       									      "language", argument.getReminderInformation().getLanguage());
        		}
    			//V20 - DateTime format changed:
    			//writeStringToDateTime("LimitDateAndTime", argument.getLimitDateAndTime());  
    			writeFormattedDateTime("LimitDateAndTime", argument.getLimitDateAndTime(),
										EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    			writeElement("ReminderMessageType", argument.getReminderMessageType());
    			//V20 - DateTime format changed:
    			//writeStringToDateTime("DateAndTimeOfValidationOfCancellation", 
				//		   argument.getDateAndTimeOfValidationOfCancellation());
    			writeFormattedDateTime("DateAndTimeOfValidationOfCancellation", 
    									argument.getDateAndTimeOfValidationOfCancellation(),
    									EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    			writeElement("DeferredSubmissionFlag", argument.getDeferredSubmissionFlag());   
    		}
    		   
    		
    		  		 	
    	closeElement();    	  
    }
  //-----------
   public void writeHeaderEaad(MsgEMCSDeclaration argument) throws XMLStreamException {
    	
	   	if (argument == null) {
	   		return;	   	
	   	}
	   	
	   	HeaderEaadDraft temp = new HeaderEaadDraft();
	   	
	   	temp.setJourneyTime(argument.getJourneyTime()); 
	   	temp.setDestinationTypeCode(argument.getDestinationTypeCode());
	   	temp.setTransportArrangement(argument.getTransportArrangement());
	   
	   	writeHeaderEaad(temp, "10");
    }

   public void writeHeaderEaad(MsgValidDeclaration argument, String version) throws XMLStreamException {
   	
	   	if (argument == null) {
	   		return;	   	
	   	}
	   	
	   	HeaderEaadDraft temp = new HeaderEaadDraft();
	   	
	   	temp.setJourneyTime(argument.getJourneyTime()); 	   	
	   	temp.setDestinationTypeCode(argument.getDestinationTypeCode());
	   	temp.setTransportArrangement(argument.getTransportArrangement());
	   	temp.setSequenceNumber(argument.getSequenceNumber());
	   	temp.setDateOfUpdateValidation(argument.getDateAndTimeOfUpdateValidation());  //EI20110927
	   
	   	writeHeaderEaad(temp, version);
   }
    public void writeHeaderEaad(HeaderEaadDraft argument, String version) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	
    	openElement("HeaderEaad");
    	    writeElement("SequenceNumber", argument.getSequenceNumber());
    	    if (!version.equalsIgnoreCase("10")) {           //EI20110927  
    	    	//V20 - new Tag DateOfUpdateValidation: 
    	    	//EI20110930: writeStringToDate("DateOfUpdateValidation", argument.getDateOfUpdateValidation());
    	    	//            for Date to DateTime, DateTime format changed:
    	    	//writeStringToDateTime("DateOfUpdateValidation", argument.getDateOfUpdateValidation()); //EI20110930
    	    	writeFormattedDateTime("DateOfUpdateValidation", argument.getDateOfUpdateValidation(),
							EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                             //EI20111017
    	    }
			writeElement("DestinationTypeCode", argument.getDestinationTypeCode());
			writeElement("JourneyTime", argument.getJourneyTime());
			writeElement("TransportArrangement", argument.getTransportArrangement());
		closeElement();
    }	   
    
  //-----------
    public void writeUpdateEaad(MsgChangeOfDestination argument) throws XMLStreamException {
    	
	   	if (argument == null) {
	   		return;	   	
	   	}
	   	
	   	UpdateEaad temp = new UpdateEaad();
	   	
	   	temp.setAadReferenceCode(argument.getAadReferenceCode()); 
		temp.setJourneyTime(argument.getJourneyTime());
		temp.setSequenceNumber(argument.getSequenceNumber());
	   	temp.setChangedTransportArrangement(argument.getChangedTransportArrangement());
	   	temp.setTransportModeCode(argument.getTransportModeCode());
		temp.setInvoiceNumber(argument.getInvoiceNumber());
		temp.setInvoiceDate(argument.getInvoiceDate());
	   
	   	writeUpdateEaad(temp);
    }    

	public void writeUpdateEaad(UpdateEaad argument) throws XMLStreamException {
	
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	
    	openElement("UpdateEaad");
    		//writeElement("AddReferenceCode", argument.getAadReferenceCode());  
    		writeElement("AadReferenceCode", argument.getAadReferenceCode());    //EI20110928 now active
    		writeElement("JourneyTime", argument.getJourneyTime());    		 
    		writeElement("ChangedTransportArrangement", argument.getChangedTransportArrangement());
    		writeElement("SequenceNumber", argument.getSequenceNumber()); 
    		writeStringToDate("InvoiceDate", argument.getInvoiceDate());    
    		writeElement("InvoiceNumber", argument.getInvoiceNumber());      		
    		writeElement("TransportModeCode", argument.getTransportModeCode());
    		if (argument.getComplementaryInformation() != null) {   		//V21 new
                writeElementWithAttribute("ComplementaryInformation", 
                		argument.getComplementaryInformation().getText(), "language",
                		argument.getComplementaryInformation().getLanguage()); 
            }
    	closeElement();
    }	
		
	public void writeChangedDestination(ChangedDestination argument, String version) throws XMLStreamException {
		
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	
    	openElement("ChangedDestination");
    		writeElement("DestinationTypeCode", argument.getDestinationTypeCode());      		  		
    		writeTrader("NewConsigneeTrader", argument.getNewConsignee(), version); 
    		writeTrader("DeliveryPlaceTrader", argument.getDeliveryPlace(), version);     
    		writeElement("DeliveryPlaceCustomsOffice", argument.getDeliveryPlaceCustomsOffice());  
    	closeElement();
    }
	//-----------	
	public void writeExciseMovementEaad(String number, String code, String date, String version) 
										throws XMLStreamException {
		
    	ExciseMovementEaad temp = new ExciseMovementEaad();
	   	
	   	temp.setSequenceNumber(number); 
		temp.setAadReferenceCode(code);	   
	   	temp.setDateAndTimeOfValidationOfEaad(date);	   	
		
	   	writeExciseMovementEaad(temp, version);	  
	}
	
	public void writeExciseMovementEaad(ExciseMovementEaad argument, String version) throws XMLStreamException {
		
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	    	
    	openElement("ExciseMovementEaad");
    	   	writeElement("SequenceNumber", argument.getSequenceNumber());  
    	   	writeElement("AadReferenceCode", argument.getAadReferenceCode()); 
    	   	if (version.equals("10")) {
    	   		writeStringToDateTime("DateAndTimeOfValidationOfEaad", argument.getDateAndTimeOfValidationOfEaad());
    	   	} else {
    	     	//V20 - DateTime name and format changed:
    	   		//writeStringToDateTime("DateAndTimeOfValidation", argument.getDateAndTimeOfValidationOfEaad());
    	   		writeFormattedDateTime("DateAndTimeOfValidation", argument.getDateAndTimeOfValidationOfEaad(),
    	   					EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    	   	}
    	closeElement();    	
    
    }
	   
    public void writeExciseMovementEaadList(List<ExciseMovementEaad> list, String version) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeExciseMovementEaad(list.get(i), version);   
		}	
    }	
	//-----------
	
	public void writeTransport(String mode, String argument) throws XMLStreamException {
		     
    	if (Utils.isStringEmpty(argument)) {
    		return;
    	}
    	
    	if (Utils.isStringEmpty(mode)) {
    		openElement("Transport");   		
   	   			writeElement("TransportModeCode", argument);    		     	   		
   	   		closeElement();
    	} else {    		
   	   		openElement("Transport" + mode);   		
   	   			writeElement("TransportModeCode", argument);    		     	   		
   	   		closeElement(); 
    	}
	}	
	//---------------	
	public void writeDocumentCertificateList(List <DocumentCertificate> list) throws XMLStreamException {		
        if (list == null) {
        	return;
        }
                                	
        int size = list.size();
        for (int i = 0; i < size; i++) {	                        		
        	writeDocumentCertificate(list.get(i));    		                          
        }		
	}
	public void writeDocumentCertificate(DocumentCertificate argument) throws XMLStreamException {
	     
	   	if (argument == null) {
	   		return;
	   	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
   	   	openElement("DocumentCertificate");   	
   	   	if (argument.getDocumentDescription() != null) {
			writeElementWithAttribute("DocumentDescription", argument.getDocumentDescription().getText(), 
					  				  "language", argument.getDocumentDescription().getLanguage());
   	   	}
   	   	if (argument.getDocumentReference() != null) {
   	   		writeElementWithAttribute("ReferenceOfDocument", argument.getDocumentReference().getText(),
   	   					              "language", argument.getDocumentReference().getLanguage());
   	   	}
   		closeElement();   	
	}
	//---------------
	public void writePackageList(List <EmcsPackage> list) throws XMLStreamException {		
        if (list == null) {
        	return;
        }
                                	
        int size = list.size();
        for (int i = 0; i < size; i++) {	                        		
    		writePackage(list.get(i));    		                          
        }		
	}
	public void writePackage(EmcsPackage argument) throws XMLStreamException {
	     
	   	if (argument == null) {
	   		return;
	   	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
   	   	openElement("Package");   		
   	   		writeElement("KindOfPackages", argument.getKindOfPackages());   
   	   		writeElement("NumberOfPackages", argument.getNumberOfPackages());
   	    	writeElement("CommercialSealIdentification", argument.getCommercialSealIdentification());  
   	    	
   	    	if (argument.getSealInformation() != null) {
				writeElementWithAttribute("SealInformation", argument.getSealInformation().getText(), 
										  "language", argument.getSealInformation().getLanguage());
   	    	}
   		closeElement();   	
	}	

	public void writeWineProduct(WineProduct argument) throws XMLStreamException {
	   	if (argument == null) {
	   		return;
	   	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
   	   	openElement("WineProduct");   		
   	   		writeElement("WineProductCategory", argument.getWineProductCategory());	
   	   		writeElement("WineGrowingZoneCode", argument.getWineGrowingZoneCode());   
   	    	writeElement("ThirdCountryOfOrigin", argument.getThirdCountryOfOrigin()); 
   	   		if (argument.getOtherInformation() != null) {
   	   			writeElementWithAttribute("OtherInformation", argument.getOtherInformation().getText(), 
   									      "language", argument.getOtherInformation().getLanguage());
   	   		}
   	   		List<String> list = argument.getWineOperationCodeList();
   	    	if (list != null) {
   	    		int size = list.size();
   	    		for (int i = 0; i < size; i++) {	                        		   	    			
   	    			writeElement("WineOperation", (String) list.get(i));   
   	    		}	
   	    	}
   		closeElement();  		
	}
	//---------
	  public void writeExportAcceptance(ExportAcceptance argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}
	    	
	    	openElement("ExportAcceptance");
	    		writeElement("ReferenceNumberOfSenderCustomsOffice", argument.getSenderCustomsOffice());	    		
	    		writeElement("IdentificationOfSenderCustomsOfficer", argument.getCustomsOfficerID());
	    		writeStringToDate("DateOfAcceptance", argument.getDateOfAcceptance());	    		
	    		writeElement("DocumentReferenceNumber", argument.getDocumentReferenceNumber());	    		
	    	closeElement();  
	    }	   
	    //------------
	    public void  writeUnsatisfactoryReasonList(List<UnsatisfactoryReason> list) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}
	    	
	    	int listSize = list.size();
	    	for (int i = 0; i < listSize; i++) {	                        		   	    			
	    		writeUnsatisfactoryReason(list.get(i));   
			}	
	    }
	    public void  writeUnsatisfactoryReason(UnsatisfactoryReason argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}
	    
	    	openElement("UnsatisfactoryReason");
    		writeElement("UnsatisfactoryReasonCode", argument.getUnsatisfactoryReasonCode());
    		if (argument.getComplementaryInformation() != null) {
    			writeElementWithAttribute("ComplementaryInformation", argument.getComplementaryInformation().getText(),
    						 "language", argument.getComplementaryInformation().getLanguage());	 
    		}
    		closeElement();  
	    }
	    //-------------
	    public void  writeErrorList(List<FunctionalError> list) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}
	    	
	    	int listSize = list.size();
	    	for (int i = 0; i < listSize; i++) {		    		
	    			writeError(list.get(i));   	    		 
			}		    	
	    }
	    public void  writeError(FunctionalError argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}
	    
	    	openElement("Error");
    			writeElement("Type", argument.getErrorType());
    			writeElement("Pointer", argument.getErrorLocation());
    			writeElement("Reason", argument.getErrorReason());    		   	
    			writeElement("OriginalValue", argument.getOriginalAttributeValue());    //EI20100625		
    		closeElement(); 	    	
	    }	   
	    //--------
	    public void  writeCAadValList(List<AadVal> list, String version) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}
	    	
	    	int listSize = list.size();
	    	for (int i = 0; i < listSize; i++) {		    		
	    		writeCAadVal(list.get(i), version);   
			}		    	
	    }
	    public void  writeCAadVal(AadVal argument, String version) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}
	    
	    	openElement("CAadVal");
	    		writeElement("AadReferenceCode", argument.getAadReferenceCode());
	    		writeElement("SequenceNumber", argument.getSequenceNumber());	
	    		if (version.equals("10")) {   	//EI20110919: 
	    			writeFunctionalError(argument.getFunctionalError());	
	    		}
	    	closeElement(); 
	    }	    
	    public void  writeFunctionalError(FunctionalError argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}
	    
	    	openElement("FunctionalError");
    			writeElement("ErrorType", argument.getErrorType());
    			writeElement("ErrorPointer", argument.getErrorLocation());
    			writeElement("ErrorReason", argument.getErrorReason());
    			writeElement("OriginalAttributeValue", argument.getOriginalAttributeValue());
    		closeElement(); 	    	
	    }	    
	    //--------	    
	    public void  writeExportCrossCheckingDiagnoses(String ref, String doc, String cancel, 
	    									List<Diagnosis> list, String version) throws XMLStreamException {
	    	if (Utils.isStringEmpty(ref) && Utils.isStringEmpty(doc) && Utils.isStringEmpty(cancel) &&	    		
	    		(list == null)) {	    		
	    		return;
	    	}	    			    	   
	    	openElement("ExportCrossCheckingDiagnoses");
    			writeElement("LocalReferenceNumber", ref);
    			writeElement("DocumentReferenceNumber", doc);
    			if (version.equals("10")) {
    				writeElement("CancelExportFlag", cancel);    
    			} else {
    				writeElement("CancelExport", cancel);    //EI20110927 
    			}
    			writeDiagnosisList(list);
    		closeElement(); 	    	
	    }	    
	    public void  writeDiagnosisList(List<Diagnosis> list) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}
	    	
	    	int listSize = list.size();	    	
	    	for (int i = 0; i < listSize; i++) {	                        		   	    			
	    		writeDiagnosis(list.get(i));   
			}	    	
	    }	 
	    public void  writeDiagnosis(Diagnosis argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	
	    	openElement("Diagnosis");
				writeElement("AadReferenceCode", argument.getAadReferenceCode());
				writeElement("BodyRecordUniqueReference", argument.getItemNumber());
				writeElement("DiagnosisCode", argument.getDiagnosisCode());    							
			closeElement(); 	    		    	 
	    }	
	      	   
	    public void  writeRejection(String dateTime, String code, String version) throws XMLStreamException {
	    	if (Utils.isStringEmpty(dateTime) && Utils.isStringEmpty(code)) {
	    		return;
	    	}
	    	openElement("Rejection");
	    	if (version.equals("10")) {
	    		writeStringToDateTime("RejectionDateAndTime", dateTime);
				writeElement("RejectionReasonCode", code);	
	    	} else {
	    		//V20 - DateTime format changed:
	    		writeFormattedDateTime("RejectionDateAndTime", dateTime,
	    				EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	        //EI20111017
				writeElement("RejectionReasonCode", code);	
	    	}
			closeElement(); 	    		    	 
	    }	
	    	
	    public void writeSubmittingPerson(SubmittingPerson argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}    
	    	openElement("SubmittingPerson");
				writeElement("SubmittingPersonCode", argument.getSubmittingPersonCode());
				writeElement("SubmittingPersonName", argument.getSubmittingPersonName());
				if (argument.getSubmittingPersonComplement() != null) {			
					writeElementWithAttribute("SubmittingPersonComplement",
						argument.getSubmittingPersonComplement().getText(),  
						"language", argument.getSubmittingPersonComplement().getLanguage());
				}
			closeElement(); 	    	
	    } 
	    
	    public void writeEvidenceOfEventList(List<EvidenceOfEvent> list) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}
	    	for (EvidenceOfEvent event : list) {	                        		   	    			
	    		writeEvidenceOfEvent(event);   
			}
	    }
	    public void writeEvidenceOfEvent(EvidenceOfEvent argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}    
	    	openElement("EvidenceOfEvent");
				writeElement("EvidenceTypeCode", argument.getEvidenceTypeCode());			
				if (argument.getIssuingAuthority() != null) {			
					writeElementWithAttribute("IssuingAuthority",
						argument.getIssuingAuthority().getText(),  
						"language", argument.getIssuingAuthority().getLanguage());
				}
				if (argument.getReferenceOfEvidence() != null) {			
					writeElementWithAttribute("ReferenceOfEvidence",
						argument.getReferenceOfEvidence().getText(),  
						"language", argument.getReferenceOfEvidence().getLanguage());
				}
				if (argument.getEvidenceTypeComplement() != null) {			
					writeElementWithAttribute("EvidenceTypeComplement",
						argument.getEvidenceTypeComplement().getText(), 
						"language", argument.getEvidenceTypeComplement().getLanguage());
				}
			closeElement(); 	    	
	    } 
}

