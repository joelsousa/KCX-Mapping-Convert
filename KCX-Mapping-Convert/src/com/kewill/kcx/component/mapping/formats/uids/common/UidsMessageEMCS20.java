package com.kewill.kcx.component.mapping.formats.uids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.emcs21.msg.MsgChangeOfDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgReminderMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AadVal;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AttributesType;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ChangedDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Diagnosis;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.HeaderEaadDraft;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.UpdateEaad;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS.<br>
 * Created 		: 12.10.2012<br>
 * Description	: Fields and methods to write EMCS20-UidsMessages .
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class UidsMessageEMCS20 extends UidsMessageEMCS {

     
	public void writeTrader(String person, EmcsTrader trader) throws XMLStreamException {
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
    	    	
    	if (Utils.isStringEmpty(country) && Utils.isStringEmpty(customer) && Utils.isStringEmpty(tax) &&
    		Utils.isStringEmpty(vat) && Utils.isStringEmpty(name) && Utils.isStringEmpty(street) &&
    		Utils.isStringEmpty(number) && Utils.isStringEmpty(plz) && Utils.isStringEmpty(city)) {
        		return;
    	}
    	String dummy = "";  
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
        			//writeElement("StreetNumber", number) //V10, 
        			writeElement("Street", street);        //V20 getrennte Tags: Street and  HouseNumber 				
        			writeElement("HouseNumber", number);  //EI20110928        			
        			writeElement("PostalCode", plz); 
        			writeElement("City", city); 
        			//writeElement("CountryCodeISO", country);  
        			writeElement("Language", country);    //EI20100612
        		closeElement(); 
        	}
        	//TODO Contact
        	
		closeElement();  
    }
    
    public void writeMovementGuarantee(String code, List<EmcsTrader> guarantorList) 
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
				writeTrader("GuarantorTrader", guarantorList.get(i));						
			}
		closeElement();
    }	 
  
   public void writeAttributes(MsgReminderMessage argument) throws XMLStreamException {    	
	   	if (argument == null) {
	   		return;
	   	}	    
	   	AttributesType temp = new AttributesType();	   	
	   	temp.setDateAndTimeOfIssuance(argument.getDateAndTimeOfIssuance()); 
	   	temp.setLimitDateAndTime(argument.getLimitDateAndTime());	   	
	   	temp.setReminderInformation(argument.getReminderInformation());
	   	temp.setReminderMessageType(argument.getReminderMessageType());
	   	
	   	writeAttributes(temp);
   }   
    
    public void writeAttributes(AttributesType argument) throws XMLStreamException {
    	
    	if (argument == null) {
    		return;    	
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	openElement("Attributes");    		    		
    		writeElement("SubmissionMessageType", argument.getSubmissionMessageType());      			
    		//writeStringToDateTime("DateAndTimeOfIssuance", argument.getDateAndTimeOfIssuance());
    		writeFormattedDateTime("DateAndTimeOfIssuance", argument.getDateAndTimeOfIssuance(),
										EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    		if (argument.getReminderInformation() != null) {
       	   		writeElementWithAttribute("ReminderInformation", argument.getReminderInformation().getText(), 
       									      "language", argument.getReminderInformation().getLanguage());
        	}    			
    		//writeStringToDateTime("LimitDateAndTime", argument.getLimitDateAndTime());  
    		writeFormattedDateTime("LimitDateAndTime", argument.getLimitDateAndTime(),
										EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    		writeElement("ReminderMessageType", argument.getReminderMessageType());    			
    		//writeStringToDateTime("DateAndTimeOfValidationOfCancellation", 
			//		   argument.getDateAndTimeOfValidationOfCancellation());
    		writeFormattedDateTime("DateAndTimeOfValidationOfCancellation", 
    									argument.getDateAndTimeOfValidationOfCancellation(),
    									EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
    		writeElement("DeferredSubmissionFlag", argument.getDeferredSubmissionFlag());       	  		 
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

   public void writeHeaderEaad(MsgValidDeclaration argument) throws XMLStreamException {
	   	if (argument == null) {
	   		return;	   	
	   	}	   	
	   	HeaderEaadDraft temp = new HeaderEaadDraft();
	   	
	   	temp.setJourneyTime(argument.getJourneyTime()); 	   	
	   	temp.setDestinationTypeCode(argument.getDestinationTypeCode());
	   	temp.setTransportArrangement(argument.getTransportArrangement());
	   	temp.setSequenceNumber(argument.getSequenceNumber());
	   	temp.setDateOfUpdateValidation(argument.getDateAndTimeOfUpdateValidation());  //EI20110927
	   
	   	writeHeaderEaad(temp);
   }
    public void writeHeaderEaad(HeaderEaadDraft argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	
    	openElement("HeaderEaad");
    	    writeElement("SequenceNumber", argument.getSequenceNumber());    	       	    
    	    //writeStringToDateTime("DateOfUpdateValidation", argument.getDateOfUpdateValidation()); //EI20110930
    	    writeFormattedDateTime("DateOfUpdateValidation", argument.getDateOfUpdateValidation(),
							EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                             //EI20111017    	   
			writeElement("DestinationTypeCode", argument.getDestinationTypeCode());
			writeElement("JourneyTime", argument.getJourneyTime());
			writeElement("TransportArrangement", argument.getTransportArrangement());
		closeElement();
    }	   
    		
	public void writeChangedDestination(ChangedDestination argument) throws XMLStreamException {
		
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	
    	openElement("ChangedDestination");
    		writeElement("DestinationTypeCode", argument.getDestinationTypeCode());      		  		
    		writeTrader("NewConsigneeTrader", argument.getNewConsignee()); 
    		writeTrader("DeliveryPlaceTrader", argument.getDeliveryPlace());     
    		writeElement("DeliveryPlaceCustomsOffice", argument.getDeliveryPlaceCustomsOffice());  
    	closeElement();
    }
	//-----------	
	public void writeExciseMovementEaadList(List<ExciseMovementEaad> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	    	
    	for (ExciseMovementEaad eaad : list) {	                        		   	    			
    		writeExciseMovementEaad(eaad);   
		}	
    }	
	public void writeExciseMovementEaad(String number, String code, String date) throws XMLStreamException {
		
    	ExciseMovementEaad temp = new ExciseMovementEaad();
	   	
	   	temp.setSequenceNumber(number); 
		temp.setAadReferenceCode(code);	   
	   	temp.setDateAndTimeOfValidationOfEaad(date);	   	
		
	   	writeExciseMovementEaad(temp);	  
	}	
	public void writeExciseMovementEaad(ExciseMovementEaad argument) throws XMLStreamException {
		
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	    	
    	openElement("ExciseMovementEaad");
    	   	writeElement("SequenceNumber", argument.getSequenceNumber());  
    	   	writeElement("AadReferenceCode", argument.getAadReferenceCode());     	       	     	
    	   	//writeStringToDateTime("DateAndTimeOfValidation", argument.getDateAndTimeOfValidationOfEaad());
    	   	writeFormattedDateTime("DateAndTimeOfValidation", argument.getDateAndTimeOfValidationOfEaad(),
    	   					EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017    	   
    	closeElement();    	
    
    }
	
	
	
	    public void  writeErrorList(List<FunctionalError> list) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}
	    	
	    	int listSize = list.size();
	    	for (int i = 0; i < listSize; i++) {		    		
	    			writeError(list.get(i));   	    		 
			}		    	
	    }
	   
	    public void  writeCAadValList(List<AadVal> list) throws XMLStreamException {
	    	if (list == null) {
	    		return;
	    	}	    	
	    	for (AadVal aad : list) {		    		
	    		writeCAadVal(aad);   
			}		    	
	    }
	    public void  writeCAadVal(AadVal argument) throws XMLStreamException {
	    	if (argument == null) {
	    		return;
	    	}
	    	if (argument.isEmpty()) {
	    		return;
	    	}
	    
	    	openElement("CAadVal");
	    		writeElement("AadReferenceCode", argument.getAadReferenceCode());
	    		writeElement("SequenceNumber", argument.getSequenceNumber());		    		
	    	closeElement(); 
	    }	    
	    	    
	    public void  writeExportCrossCheckingDiagnoses(String ref, String doc, String cancel, List<Diagnosis> list)
	    									 				throws XMLStreamException {
	       if (Utils.isStringEmpty(ref) && Utils.isStringEmpty(doc) && Utils.isStringEmpty(cancel) && (list == null)) {
	    		return;
	       }	    			    	   
	    	openElement("ExportCrossCheckingDiagnoses");
    			writeElement("LocalReferenceNumber", ref);
    			writeElement("DocumentReferenceNumber", doc);    			
    			writeElement("CancelExport", cancel);    //EI20110927     			
    			writeDiagnosisList(list);
    		closeElement(); 	    	
	    }	    
	     	   
	    public void  writeRejection(String dateTime, String code) throws XMLStreamException {
	    	if (Utils.isStringEmpty(dateTime) && Utils.isStringEmpty(code)) {
	    		return;
	    	}
	    	openElement("Rejection");	    	
	    		writeFormattedDateTime("RejectionDateAndTime", dateTime,
	    				EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	        //EI20111017
				writeElement("RejectionReasonCode", code);		    	
			closeElement(); 	    		    	 
	    }	
	    
	    //EI20140210 new for V21:
	    public void writeUpdateEaad21(MsgChangeOfDestination argument) throws XMLStreamException {
	    	
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
			temp.setComplementaryInformation(argument.getComplementaryInformation());  //new for V21
		   
		   	writeUpdateEaad(temp);
	    }      
}

