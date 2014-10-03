package com.kewill.kcx.component.mapping.formats.uids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.OutwardProcessing;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : Export/aes.<br>
 * Created      : 10.07.2012<br>
 * Description	: Uids Version 2.0.00
 * 			    : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class UidsMessageV21 extends UidsMessage {
	
	public void writeTypeOfDeclaration(String areaCode, String procedureCode, String procedure) throws XMLStreamException {
		if (Utils.isStringEmpty(areaCode) && Utils.isStringEmpty(procedureCode) && Utils.isStringEmpty(procedure)) {
		    return;
		}		
		openElement("TypeOfDeclaration"); 
			//writeElement("DeclarationCode", 
			writeElement("RegionCode", areaCode);
			writeElement("Procedure", procedure);
			writeElement("ProcedureCode", procedureCode);			
		closeElement(); 
	}

	public void writeExportRestitutionHeader(ExportRefundHeader argument)	throws XMLStreamException {
	   	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}    	       	   	
     
		
		String AIDAaccount = argument.getGuarantee();
		String pending = argument.getReservationCode();
		String country = argument.getDestinationCountry();
		
		openElement("ExportRestitutionHeader");
			writeElement("Remark", argument.getText()); 
			writeElement("ApplicationType", argument.getPaymentType()); 
			writeElement("PaymentType", argument.getBankNumber());
			writeElement("AIDACode", argument.getAssignee());  			  
			writeElement("AIDAAccount", argument.getGuarantee());  
			writeElement("RestitutionPending", argument.getReservationCode());  
			//writeElement("CountryOfRouting", argument.getDestinationCountry());  //gibt es nicht in UIDS-Definition
		closeElement(); 		                					
	}
	
	public void writeCustomsOffices(String cExport, String cCompletion, 
            String rExit, String actExit, Party supervisor) throws XMLStreamException {	
		if (Utils.isStringEmpty(cExport) && Utils.isStringEmpty(cCompletion) &&
				Utils.isStringEmpty(rExit) && Utils.isStringEmpty(actExit) &&
				supervisor == null) {
			return;		
		}	
		openElement("CustomsOffices");		
			writeElement("OfficeOfActualExit", actExit);
			writeElement("OfficeOfAdditionalDeclarationExport", cCompletion);
			writeElement("OfficeOfExit", rExit);     //for "ExpEAM" is rExit==null, the Tag won't be visible            
			writeElement("OfficeOfExport", cExport); 
			if (supervisor != null && supervisor.getAddress() != null) {
				writeElement("OfficeOfExport", supervisor.getAddress().getName()); 
			}
		closeElement(); 
	}
	public void writeCustomsOffice(Party supervisor) throws XMLStreamException {			
		if (supervisor == null) {
			return;		
		}	
		openElement("CustomsOffices");					
			if (supervisor != null && supervisor.getAddress() != null) {
				writeElement("SupervisingCustomsOffice", supervisor.getAddress().getName()); 
			}
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
		closeElement(); 
	}
	
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
		
		//AK20120831
        if (partyTIN != null && partyTIN.isEmpty()  &&
                adr != null && adr.isEmpty() && 
                contact != null && contact.isEmpty()) {
          return;
        }
        
       //AK20120831
        /*if (partyTIN.isEmpty() && adr.isEmpty() && contact.isEmpty()) {
			return;
		}*/
		
		openElement(person);	
			if (party.getPartyTIN() != null) {
				writeElement("CustomerID", partyTIN.getCustomerIdentifier());
				writeElement("IdentificationType", partyTIN.getIdentificationType());
				if (!person.equals("Consignee")) {
				    writeElement("CustomsID", partyTIN.getIsTINGermanApprovalNumber());
				}
				writeElement("BO", partyTIN.getBO());       //new for V20
				writeElement("TIN", partyTIN.getTIN());
			}
			
			writeAddress(adr);
			
			//if (!(person.equals("Consignee"))) { //EI20120912: in V21 auch Consignee hat ContactDaten
			writeContact(contact);   
			//}
			
		closeElement();			
	}
	
	public void writeInvoice(Invoice argument) throws XMLStreamException {
		if (argument == null) {
			return;		
		}	
		if (argument.isEmpty()) {
			return;	
		}
		openElement("Invoice");								
			writeElement("Number", argument.getInvoiceNumber()); 	
			writeElement("Date", argument.getDate()); 	
			writeElement("Amount", argument.getInvoicePrice()); 				
			if (!Utils.isStringEmpty(argument.getCurrency()) || !Utils.isStringEmpty(argument.getRate())) {
				openElement("ExchangeRate");	
					writeElement("Rate", argument.getCurrency()); 	
					writeElement("Currency", argument.getRate()); 
				closeElement(); 
			}
		closeElement(); 
	}
	public void writeSeals(Seal argument) throws XMLStreamException {		 
		if (argument == null) {
		    return;
		}
		if (argument.isEmpty()) {
		    return;
		}

		List <SealNumber>tmpSealList =  argument.getSealNumbersList();
		//List <String>tmpSealList =  argument.getSealNumberList();
				
		openElement("Seals");
			writeElement("Type", argument.getKind());
			writeElement("SealOK", argument.getSealOK());
			writeElement("Quantity", argument.getNumber());
			
			if (argument.getSealNumberList() != null) {					
				for (SealNumber snr : tmpSealList) {												
					if (snr != null) {
						openElement("SealsID");
							writeElement("Identity", snr.getNumber());
							writeElement("Language", snr.getLanguage());
							
						closeElement();
						writeElement("SealingParty", snr.getSealingParty());
					}
				}					
			}
			
		closeElement(); 
	}	

	public void writeTransaction(Business argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		if (argument.isEmpty()) {
		    return;
		}
		String code = argument.getBusinessTypeCode();
		String price = argument.getInvoicePrice();
		String curr = argument.getCurrency();

		if (Utils.isStringEmpty(code) && Utils.isStringEmpty(price)  && Utils.isStringEmpty(curr)) {
		    return;
		}
		
		openElement("Transaction");
			writeElement("Type", argument.getBusinessTypeCode());
			writeElement("AdditionalType", argument.getAdditionalType());
			writeElement("Amount", argument.getInvoicePrice());
			writeElement("Currency", argument.getCurrency());            				
		closeElement();
	}
	
	public void writePreviousDocument(PreviousDocumentV20 argument, String fromFormat) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		if (Utils.isStringEmpty(fromFormat)) {
			fromFormat = "";
		}
		String marks = argument.getMarks();
		if (fromFormat.equalsIgnoreCase("UIDS")) {
			marks = argument.getMarks("UIDS");
		}
		if (Utils.isStringEmpty(marks) && Utils.isStringEmpty(argument.getAdditionalInformation()) && 
				Utils.isStringEmpty(argument.getType()) && Utils.isStringEmpty(argument.getDate())) {
			return;
		}
		
		openElement("PreviousDocument");						
			writeElement("DateOfCreation", argument.getDate());
			writeElement("Reference", marks);
			writeElement("Remark", argument.getAdditionalInformation());						
			writeElement("Type", argument.getType()); 			
		closeElement(); 		
	}
	
	public void writeItemPreviousDocument(PreviousDocumentV20 argument, String fromFormat) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		if (Utils.isStringEmpty(fromFormat)) {
			fromFormat = "";
		}
						
		String marks = argument.getMarks();
		if (fromFormat.equalsIgnoreCase("UIDS")) {
			marks = argument.getMarks("UIDS");
		}
		if (Utils.isStringEmpty(marks) && Utils.isStringEmpty(argument.getAdditionalInformation()) && 
				Utils.isStringEmpty(argument.getType()) && Utils.isStringEmpty(argument.getDate())) {
			return;
		}
		
		openElement("PreviousDocument");			
			if (!Utils.isStringEmpty(marks) || !Utils.isStringEmpty(argument.getAdditionalInformation()) || 
					!Utils.isStringEmpty(argument.getDate())) {
				openElement("Document");
					writeElement("DateOfCreation", argument.getDate());
					writeElement("Reference", marks);
					writeElement("Remark", argument.getAdditionalInformation());
				closeElement();
			}
			//Product, Calculation				
			writeElement("Type", argument.getType());  
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
			writeElement("DateOfCreation", argument.getDate());
			writeElement("Reference", argument.getReference());
			writeElement("Remark", argument.getAdditionalInformation());						
			writeElement("Type", argument.getType()); 			
		closeElement(); 		
	}
	public void writeItemPreviousDocument21(PreviousDocumentV21 argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}			
		openElement("PreviousDocument");			
			if (argument.getReferenceList() != null || 
					!Utils.isStringEmpty(argument.getAdditionalInformation()) || 
					!Utils.isStringEmpty(argument.getDate())) {
				openElement("Document");
					writeElement("DateOfCreation", argument.getDate());
					writeElement("Reference", argument.getReference());
					writeElement("Remark", argument.getAdditionalInformation());
				closeElement();
			}
			//Product, Calculation				
			writeElement("Type", argument.getType());  
		closeElement(); 		
	}
	public void writeProducedDocument(DocumentV20 argument)throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		if (argument.isEmpty()) {
		    return;
		}		
		String present = argument.getPresent();
		String presentL = argument.getPresentLocation();		
		
		openElement("ProducedDocument");
			openElement("Document");
				////sehr viele nicht gemappte Tags
				writeElement("Category", argument.getType());				
                writeStringToDate("DateOfCreation", argument.getIssueDate());
				writeStringToDate("DateOfValidity", argument.getExpirationDate());					
				writeElement("Reference", argument.getReference());
				writeElement("Remark", argument.getAdditionalInformation());
				writeElement("Detail", argument.getDetail());
				writeElement("Type", argument.getQualifier());
				if (!Utils.isStringEmpty(argument.getValue()) || argument.getWriteDownAmount() != null) {
					openElement("Writeoff");
					    if (argument.getWriteDownAmount() != null)  { 	
					    	//writeElement("Unit", argument.getWriteDownAmount().getUnitOfMeasurement());
					    	writeElement("Measurement", argument.getWriteDownAmount().getUnitOfMeasurement()); //steht in xls
					    }
						writeElement("Value", argument.getValue());														
						if (argument.getWriteDownAmount() != null)  { 							    
							writeElement("WriteoffValue", argument.getWriteDownAmount().getValue()); 
						}
					closeElement();
				}
				writeElement("Present", present);
				writeElement("PresentLocation", presentL);
			closeElement();  //Document			
		closeElement();  //ProducedDocument
	}
	
	public void writePassiveFinishing(OutwardProcessing argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}   
    	openElement("PassiveFinishing"); 
			writeElement("AuthorisationID", argument.getAuthorizationNumber());
			writeElement("LocalClearanceProcedure", argument.getAuthorizationLocalClearenceProcedure());
			writeElement("DateOfReentry", argument.getDateOfReExport());
			writeElement("StandardReplacement", argument.getStandardExchange());
			if (argument.getReentryList() != null) {
				for (Reentry reentry : argument.getReentryList()) {
					if (reentry != null) {
						writeElement("CountryOfReentry", reentry.getCountry());
					}
				}
			}
			if (argument.getMeansOfIdentificationList() != null) {
				for (MeansOfIdentification identity : argument.getMeansOfIdentificationList()) {
					if (identity != null) {
						openElement("Identity");
							writeElement("IdentityOfGoods", identity.getType());
							writeElement("Remark", identity.getText());
						closeElement();
					}
				}
			}
			if (argument.getProductList() != null) {
				for (Product tarif : argument.getProductList()) {
					if (tarif != null) {
						openElement("Product");
							writeElement("CommodityCode", tarif.getTarifCode());
							writeElement("GoodsDescription", tarif.getDescription());
						closeElement();
					}
				}
			}		
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
    		//writeElement("LocationCode", argument.getLocationCode());
    	closeElement();
    }
			
	public void writeSpecialGoodsInformation(SensitiveGoods argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
    	    return;
    	}
		openElement("SpecialGoodsInformation");
   			writeElement("Code", argument.getType()); 
   			writeElement("Amount", argument.getWeight()); 
   		closeElement(); 
	}
	
	public void writeSpecialRemarks(NonCustomsLaw argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	}
		openElement("SpecialRemarks");
			writeElement("Obligation", argument.getObligation()); 
			if (argument.getNonCustomsLawType() != null) {
				for (String type : argument.getNonCustomsLawType()) {
					writeElement("RestrictionCode", type); 
				}
			}
			
		closeElement(); 		
	}
	 public void writeAddInfoStatement(Text argument) throws XMLStreamException {  //EI20100208 for UK   
	     	if (argument == null) {
	    	    return;
	    	}
	    	String code = argument.getCode();
	     	String text = argument.getText();     	
	     	if (Utils.isStringEmpty(code) && Utils.isStringEmpty(text))  {
	     		return;
	     	}
	     	openElement("AddInfoStatement");
	     		writeElement("Code", code); 
	     		writeElement("Text", text); 
	     	closeElement();   
	}
	 
	public void writeRefinement(Refinement argument)throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}
	    	if (argument.isEmpty()) {
	    	    return;
	    	}
	    	openElement("Refinement");
				writeElement("Direction", argument.getDirection());      	
				writeElement("RefinementType", argument.getRefinementType());      	
				writeElement("ProcessType", argument.getProcessType()); 
				writeElement("BillingType", argument.getBillingType());      	
				writeElement("TemporaryAdmission", argument.getTemporaryAdmission());      	
				writeElement("PositionType", argument.getPositionType());   
			closeElement();
	}
	public void writeDetailList(List<Text> list) throws XMLStreamException {   
	    if (list == null) {
	    	return;
	    }	   		    	
	    for (Text text : list) {
	    	if (text != null) {
	    		openElement("Detail"); 
	    			writeElement("Name", text.getCode());
	    			writeElement("Value", text.getText());
	    		closeElement();
	    	} 		
	    }   
	}
	 public void writeNotificationCodeList(List<String> list)throws XMLStreamException {    	
		if (list == null) {
	    	    return;
	    }	         	   		   
	    for (String code : list) {     		
	    	writeElement("NotificationCode", code);       	
	    }     		    	
	}
	 
	public void writeCAP(Cap argument) throws XMLStreamException { //EI20100208 for UK     
      	if (argument == null) {
    	    return;
    	}
      	String ibClaimRef = argument.getIBClaimRef();
      	String ibClaimType = argument.getIBClaimType();
      	String ibRegNo = argument.getIBRegNo();
      	String ibGan = argument.getIBGAN();
      	
       	if (Utils.isStringEmpty(ibClaimRef) && Utils.isStringEmpty(ibClaimType) &&
            Utils.isStringEmpty(ibRegNo) && Utils.isStringEmpty(ibGan)) {
        	return;
       	}
       	openElement("CAP");
       		writeElement("IBClaimRef", ibClaimRef);      	
       		writeElement("IBClaimType", ibClaimType);      	
       		writeElement("IBRegNo", ibRegNo); 
       		writeElement("IBGAN", ibGan); 
	    closeElement();    	
    }
	public void writeControlResult(String status)  throws XMLStreamException { //EI20130810
		if (Utils.isStringEmpty(status)) {
    	    return;
    	}
		openElement("ControlResultExit");
   			//writeElement("Type", type);      	
   			//writeElement("Date", ibClaimType);      	
   			//writeElement("Result", result); 
   			writeElement("Status", status); 
   			//writeInCharge(todo);
    	closeElement();  
	}
	
}

