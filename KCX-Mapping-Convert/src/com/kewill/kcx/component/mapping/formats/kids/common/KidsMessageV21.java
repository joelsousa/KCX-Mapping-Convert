package com.kewill.kcx.component.mapping.formats.kids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.AmendmentInfo;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.OutwardProcessing;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmountV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      : Export/aes.<br>
 * Created     : 09.07.2012<br>
 * Description : KIDS V21
 * 			   : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class KidsMessageV21 extends KidsMessage {
    
    public void writeDocument(DocumentV20 argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	    
    	openElement("Document");  
    			writeElement("Qualifier", argument.getQualifier());      	
    			writeCodeElement("Type", argument.getType(), "KCX0035");                        
    			writeElement("Reference", argument.getReference()); 
    			writeElement("AdditionalInformation", argument.getAdditionalInformation());      	
    			writeElement("Detail", argument.getDetail());       			
    			if (!Utils.isStringEmpty(argument.getIssueDate())) {   
    				String date = argument.getIssueDate();
    				date.trim();    
    				if (date.length() == 10) {
            		    writeDateToString("IssueDate", date);
            		} else {
            			writeElement("IssueDate", date); 
            		}
    			}
    			if (!Utils.isStringEmpty(argument.getExpirationDate())) {
    				String date = argument.getExpirationDate();
    				date.trim();   
    				if (date.length() == 10) {
    					writeDateToString("ExpirationDate", date);
    				} else {
    					writeElement("ExpirationDate", date); 
    				}
    			}
    			writeElement("Value", argument.getValue());
    		    writeElement("Reason", argument.getReason());       			
    			writeWriteDownAmount("WritedownAmount", argument.getWriteDownAmount(), "KCX0016");     		 		
    			writeWriteDownAmount("WritedownQuantity", argument.getWriteDownQuantity(), "");
    			writeElement("Office", argument.getOffice());
    			writeElement("Present", argument.getPresent());
    			writeElement("PresentLocation", argument.getPresentLocation());    	
    	closeElement();    	
    } 
    
    public void writeWriteDownAmount(String tag, WriteDownAmountV20 argument, String kcx) throws XMLStreamException  {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}    	
		openElement(tag);	
			writeElement("Qualifier", argument.getQualifier());
			if (kcx.equals("")) {
				writeElement("UnitOfMeasurement", argument.getUnitOfMeasurement());
			} else	{				
				writeCodeElement("UnitOfMeasurement", argument.getUnitOfMeasurement(), kcx);
			}			
			writeElement("Value", argument.getValue()); 			
		closeElement();
    }
    
	public void writePreviousDocument(PreviousDocumentV20 argument, String fromFormat) throws XMLStreamException {   
    	if (argument == null) {
    		return;
    	} 
    	if (fromFormat == null) {
    		fromFormat = "";
    	}
		    	
    	String marks = argument.getMarks();
    	if (fromFormat.equalsIgnoreCase("UIDS")) {
    		marks = argument.getMarks("UIDS");
    	}
    	
    	openElement("PreviousDocument");  
    		writeElement("Type", argument.getType());    		
    		writeElement("Marks", marks);    		
    		//writeElement("Reference", dummy); 	     		
    	    //writeElement("ReferenceSpecification", dummy);    	   
    	    //writeElement("Quantity", dummy); 
    	    //writeElement("GrossMass", dummy);
    	    //writeElement("NetMass", dummy);
    	    //writeElement("UnitOfMeasurement", dummy); 
    	    //writeElement("Value", dummy);
    	    writeElement("AdditionalInformation", argument.getAdditionalInformation());
    	    writeDateToString("Date", argument.getDate());
    	    writeElement("Office", argument.getOffice());    	         	       
    	closeElement();						
    }
	public void writePreviousDocument21(PreviousDocumentV21 argument) throws XMLStreamException {   
    	if (argument == null) {
    		return;
    	} 
    	if (argument.isEmpty()) {
    		return;
    	} 	    	
    	openElement("PreviousDocument");  
    		writeElement("Type", argument.getType());
    		writeElement("DocumentType", argument.getDocumentType());  //EI20130807 for kids2kids
    		writeElement("Marks", argument.getMarks());    		
    		writeElement("Reference", argument.getReference()); 	  //EI20130807 for for kids2kids    		
    	    //writeElement("ReferenceSpecification", dummy);    	   
    	    //writeElement("Quantity", dummy); 
    	    //writeElement("GrossMass", dummy);
    	    //writeElement("NetMass", dummy);
    	    //writeElement("UnitOfMeasurement", dummy); 
    	    //writeElement("Value", dummy);
    	    writeElement("AdditionalInformation", argument.getAdditionalInformation());
    	    writeDateToString("Date", argument.getDate());
    	    writeElement("Office", argument.getOffice());    	         	       
    	closeElement();						
    }
    public void writeOutwardProcessing(OutwardProcessing argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}     	
    	openElement("OutwardProcessing");
    		writeElement("AuthorizationNumber", argument.getAuthorizationNumber());      	
    		writeElement("AuthorizationLocalClearenceProcedure", argument.getAuthorizationLocalClearenceProcedure());      	
    		// AK20120824 writeElement("DateOfReExport", argument.getDateOfReExport());
    		writeDateToString("DateOfReExport", argument.getDateOfReExport());
    		writeElement("StandardExchange", argument.getStandardExchange());
    		//TODO-IWA nach aenderung von xsd hier ev. weiter
    	closeElement();
    }
  //EI20120703:
    public void writeReentry(Reentry argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}    	
    	if (argument.isEmpty()) {
    		return;
    	}    	
    	openElement("Reentry");
    		writeElement("Country", argument.getCountry());      	    		  
    	closeElement();
    }  
    public void writeMeansOfIdentification(MeansOfIdentification argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}     	
    	openElement("MeansOfIdentification");
    		writeElement("Type", argument.getType());      	
    		writeElement("Text", argument.getText());      	    		 
    	closeElement();
    } 
    public void writeProduct(Product argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}    	
    	if (argument.isEmpty()) {
    		return;
    	}     	
    	openElement("Product");
    		writeElement("TarifCode", argument.getTarifCode());      	
    		writeElement("Description", argument.getDescription());      	    		 
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
    		writeElement("EUTarifCode", argument.getEUTarifCode());
    		writeElement("TarifAddition1", argument.getTarifAddition1());
    		writeElement("TarifAddition2", argument.getTarifAddition2());
    		writeElement("Addition", argument.getAddition());
    		writeElement("Addition2", argument.getAddition2());
    		writeElement("Addition3", argument.getAddition3());
    		writeElement("Confirmation", argument.getConfirmation());
    	closeElement();
    }
    
    public void writeExportRefundItem(ExportRefundItem argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	openElement("ExportRefundItem");
		writeElement("Addition1", argument.getAddition1());      	
		writeElement("Addition2", argument.getAddition2());                        
		writeElement("OriginCountry", argument.getOriginCountry());   
		writeElement("AmountCode", argument.getAmountCode());      	
		writeElement("Amount", argument.getAmount());  
   		writeCodeElement("TypeOfRefund", argument.getTypeOfRefund(), "KCX0018");
		writeElement("AmountCoefficient", argument.getAmountCoefficient());      	
		writeElement("PartA", argument.getPartA());  
		writeElement("PartB", argument.getPartB());   
		writeElement("PartC", argument.getPartC());   
		writeElement("PartD", argument.getPartD());   
		writeElement("UnitOfMeasurement", argument.getUnitOfMeasurement());   
		writeElement("ApplyFor" , argument.getApplyFor());
		writeElement("InAdvance", argument.getInAdvance());
		writeElement("CBT", argument.getCBT());
		writeElement("Prefinancing", argument.getPrefinancing()); 
		writeElement("ApplicationForm", argument.getApplicationForm());
		writeDateToString("ApplicationDate", argument.getApplicationDate());
		writeElement("SimplifiedDeclaration", argument.getSimplifiedDeclaration()); 
		writeDateToString("SimplifiedDeclarationDate", argument.getSimplifiedDeclarationDate());
		if (argument.getIngredientsList() != null) {    			
			for (Ingredients ing : argument.getIngredientsList()) {
        		writeIngredients(ing);
			} 
		}
	closeElement();    	
    }
    
    public void writeNonCustomsLaw(NonCustomsLaw argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}
    	openElement("NonCustomsLaw");
    		writeElement("Obligation", argument.getObligation());    		
    		if (argument.getNonCustomsLawType() != null) {			
				for (String type : argument.getNonCustomsLawType()) {
					writeElement("Type", type);
				}			
    		}
		closeElement();
	}
    
    public void writeParty(String person, Party argument) throws XMLStreamException  {    	
    	if (argument == null) {
    		return;
    	}
    	if (Utils.isStringEmpty(person)) {
    		return;
    	}
     	if (argument.getPartyTIN() != null) {
     		writeTIN(person, argument.getPartyTIN());
     	}
      	
     	if (argument.getAddress() != null) {
     		openElement(person);     
        	    writeElement("VATNumber", argument.getVATNumber());      
         	    writeElement("ETNAddress", argument.getETNAddress());        		
     			writeAddress(person, argument.getAddress());     			
     		closeElement();     		
     	}
     	     	
     	if (argument.getContactPerson() != null) {
    		writeContact(person, argument.getContactPerson());
    	}
    } 
    
    public void writeTIN(String person, TIN argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (Utils.isStringEmpty(person)) {
    	    return;  
    	}    	
    	openElement(person + "TIN");      			
			writeElement("TIN", argument.getTIN());     			
			writeCodeElement("IsTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber(), "KCX0001"); 
			writeElement("CustomerIdentifier", argument.getCustomerIdentifier()); 
			writeElement("BO", argument.getBO()); 
			writeElement("IdentificationType", argument.getIdentificationType()); 
		closeElement();     	    
    }   
    public void writeContact(String person, ContactPerson argument) throws XMLStreamException { 
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}
    	String tag = person + "ContactPerson";
    	if (person.equals("Contact")) {
    		tag = person;
    	}
    	openElement(tag); 
			writeElement("Position", argument.getPosition());
			if (tag.equals("Contact")) {
				writeElement("Identity", argument.getIdentity());
			}
			writeElement("Clerk", argument.getName());
			if (!tag.equals("Contact")) {
				writeElement("Identity", argument.getIdentity());
			}
			writeElement("Email", argument.getEmail());
			writeElement("FaxNumber", argument.getFaxNumber());
			writeElement("PhoneNumber", argument.getPhoneNumber());   
			if (tag.equals("Contact")) {
				writeElement("City", argument.getCity());       		   
				if (!Utils.isStringEmpty(argument.getDate())) {   		     		
					String date = argument.getDate().trim();  
					if (date.length() == 10) {
						writeDateToString("Date", date);
					} else {
						writeElement("Date", date);  
					}
				}
			}
			writeElement("Initials", argument.getInitials());
			writeElement("Title", argument.getTitle());
		closeElement(); 
    }
  
    public void writeMeansOfTransport(String tag, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (Utils.isStringEmpty(tag)) {
    	    return;
    	}
    	if (tag == null) {
    		return;
    	}
    	        	
        openElement("MeansOfTransport" + tag);         	    	
             writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");
             writeCodeElement("TransportationType", argument.getTransportationType(), "KCX0004");
             writeElement("TransportTime", argument.getTransportTime());
             writeElement("TransportationNumber", argument.getTransportationNumber()); 
             writeElement("TransportationCountry", argument.getTransportationCountry());         
             writeElement("PlaceOfLoading", argument.getPlaceOfLoading());                  
             writeElement("PlaceOfLoadingCode", argument.getPlaceOfLoadingCode());         
         closeElement();   
         
    }
    public void writeSeals(Seal argument, String message) throws XMLStreamException { 
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}
    	String stock = "";
    	if (!message.equals("ExpRel")) {
    	    stock = argument.getUseOfTydensealStock();
    	}
    	
    	openElement("Seal");  
        	writeCodeElement("Kind", argument.getKind(), "KCX0002");
        	writeElement("Number", argument.getNumber());                        
        	writeElement("UseOfTydenseals", argument.getUseOfTydenseals());
        	writeElement("UseOfTydensealStock", stock);
		
        	if (argument.getSealNumbersList() != null) {    //EI20120803
        		for (SealNumber snr : argument.getSealNumbersList()) {			
        			if (snr != null) {
        				openElement("SealNumbers");
        					writeElement("Number", snr.getNumber());
        					writeElement("SealingParty", snr.getSealingParty());
        				closeElement(); 
        			}
        		}
        	}
        	writeElement("SealOK", argument.getSealOK());   //new for V21
        closeElement();  
    }
    
    public void writePackages(Packages argument, String message) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}

    	String type = "";
    	String marks = "";    	 
    	if (!message.equals("ExitNotification")) {
    		type = argument.getType();  
    		marks = argument.getMarks(); 
    	}    	
    	openElement("Package");   					
    		writeElement("Quantity", argument.getQuantity());
    		writeElement("SequentialNumber", argument.getSequentialNumber());    				
    		writeCodeElement("Type", type, "KCX0066");
    		writeElement("Marks", marks);
    		writeMeansOfTransport("MeansOfTransport", argument.getTransportMeans());  //new for V21
    	closeElement();		
    		   	
    } 
    
    public void writeExportRefundHeader(ExportRefundHeader argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	       	   	
       	openElement("ExportRefundHeader");  
       		writeElement("Text", argument.getText());      	     	
	   		writeCodeElement("PaymentType", argument.getPaymentType(), "KCX0019");
       		writeElement("BankNumber", argument.getBankNumber());    
       		writeElement("Assignee", argument.getAssignee());    
       		writeElement("Guarantee", argument.getGuarantee());   
       		writeCodeElement("ReservationCode", argument.getReservationCode(), "KCX0001");
       		writeElement("DestinationCountry", argument.getDestinationCountry());    
       	closeElement();       	
    }    

    public void writeApprovedTreatment(ApprovedTreatment argument) throws XMLStreamException {   
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	
    	openElement("CustomsApprovedTreatment");
    		writeElement("Declared", argument.getDeclared());
    		writeElement("Previous", argument.getPrevious());
    		writeElement("Current", argument.getCurrent());     		//new for V21
    		writeElement("National", argument.getNational());
    		writeElement("National2", argument.getNational2());
    		writeElement("National3", argument.getNational3());
    		writeCodeElement("CodeForRefund", argument.getCodeForRefund(), "KCX0021");   
    		writeElement("Community", argument.getCommunity());
    		writeElement("Community2", argument.getCommunity2());
    		writeElement("Community3", argument.getCommunity3());
    	closeElement();
     } 
    
    public void writePermitList(List<Permit> list) throws XMLStreamException {
    	if (list == null) {
    	    return;
    	}
    	for (Permit p : list) {
    		if (p != null) {
    		openElement("Permit");
				writeElement("PermitAuthority", p.getPermitAuthority());
				writeElement("PermitNumber", p.getPermitNumber());
				writeElement("IssueDate", p.getIssueDate());
				writeElement("AdditionalInformation", p.getAdditionalInformation());
				writeElement("Type", p.getType());  					 //new for V21
			closeElement();
    		}
    	}		
	}
    
    public void writeInvoice(Invoice argument) throws XMLStreamException  {     
     	if (argument == null) {
    	    return;
    	}
     	if (argument.isEmpty()) {
    	    return;
    	}      	   
      	openElement("Invoice");
      		writeElement("InvoiceNumber", argument.getInvoiceNumber()); 
      		writeElement("InvoicePrice", argument.getInvoicePrice());
      		writeElement("Currency", argument.getCurrency());
	    closeElement();    	
    	
    }    
    
    public void writeAmendmentInfo(AmendmentInfo argument) throws XMLStreamException  {     
     	if (argument == null) {
    	    return;
    	}
     	if (argument.isEmpty()) {
    	    return;
    	}      	
      	openElement("AmendmentInfo");
      		writeElement("KindOfAmendment", argument.getKindOfAmendment()); 
      		writeElement("Reason", argument.getReason());      	
	    closeElement();    	
    	
    }    
    
    public void writeStatistic(Statistic argument, String message) throws XMLStreamException  {  //EI20130808 new Tag fpr AES22
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}
    	
    	if (Utils.isStringEmpty(argument.getAdditionalUnit()) && Utils.isStringEmpty(argument.getStatisticalValue()) &&
        	!(message.equals("ExpEam") || message.equals("ExpDat"))) {
    	    return;
    	}                
    	openElement("Statistic");
    		writeElement("AdditionalUnit", argument.getAdditionalUnit());
    		writeElement("AdditionalUnitConfirmation", argument.getAdditionalUnitConfirmation()); //EI20100208 for UK
    		writeElement("AdditionalUnitCode", argument.getAdditionalUnitCode());    		
    		writeElement("StatisticalValue", argument.getStatisticalValue());
    		writeElement("StatisticalValueConfirmation",  argument.getStatisticalValueConfirmation()); //EI20100208 for UK  
    		writeElement("StatisticalValueSendFlag", argument.getStatisticalValueSendFlag());  //EI20130808 AES22 for FSS
    		if (message.equals("ExpEam") || message.equals("ExpDat") || message.equals("ExpRel")) {
    			writeElement("Value", argument.getValue());
        		writeElement("Currency", argument.getCurrency());
    		}
	    closeElement();    	
    }
    ////
}
