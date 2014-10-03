package com.kewill.kcx.component.mapping.formats.kids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Account;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalInfoStatement;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalInformation;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.DV1;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Duties;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Excise;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.ImportPackage;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Manifest;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Office;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Payment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Preference;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Quota;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Salary;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.SpecialStatement;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: EMCS<br>
 * Created		: 04.05.2010<br>
 * Description	: Fields and methods to write EMCS-KidsMessages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsMessageImport  extends KidsMessage { 	
 
	public void writeImportDocumentList(List<Document> list, String msg) throws XMLStreamException {
		if (list == null) {
	    		return;
	    }
	    if (list.isEmpty()) {
	    		return;
	    }  	   
	    for (Document document : list) {	
	    	//EI20120312: openElement("Duty");
	    	openElement("Document");     //EI20120312
				writeElement("Type", document.getType());	
				writeElement("Number", document.getNumber());	
				writeElement("IssueDate", document.getIssueDate());	
				if (!msg.equals("CTXPos")) {
					writeElement("CodeExists", document.getCodeExists());
					writeElement("WriteOffAmountValue", document.getWriteOffAmountValue()); 
					writeElement("WriteOffMeasuringUnit", document.getWriteOffMeasuringUnit());
				    writeElement("WriteOffQualifierMeasuringUnit", document.getWriteOffQualifierMeasuringUnit());
				}
			closeElement(); 
		}
	}
	
	public void writeDutiesList(List<Duties> list) throws XMLStreamException {
		if (list == null) {
	    		return;
	    }
	    if (list.isEmpty()) {
	    		return;
	    }  	   
	    for (Duties duty : list) {		
	    	openElement("Duties");
				//writeCodeElement("Type", duty.getType(), "");	//TODO: hier musste in KidsHeader: map=1, mapFrom='KCX_CODE'
				writeElement("Type", duty.getType());	        //TODO und in der DB.kcx_codes eingefügt werden:  kcx_code=A0000, DE='???'
				writeElement("Amount", duty.getAmount());	
				if (duty.getRateList() != null && !duty.getRateList().isEmpty()) {
					for (String rate : duty.getRateList()) {					
						writeElement("Rate", rate);						
					}
				}				
			closeElement(); 
		}
	}
	public void writePreviousDocument(Document document, String type) throws XMLStreamException { 
		if (document == null) {
			return;
		}		
	    if (document.isEmpty()) {
	        return;
	    }
	    if (type == null) {
	    	type = "";
	    }
		     		              	
	    openElement("PreviousDocument");   		
	    	//writeCodeElement("Type", document.getType(), "KCX0052");
	        writeCodeElement("Type", document.getType(), "KCX0035");      //TODO Max-noch nicht klar       
	        writeElement("Number", document.getNumber());
	    closeElement();		     	        						
	}
	
	public void writeDocument(Document document, String type) throws XMLStreamException { 
		if (document == null) {
			return;
		}		
	    if (document.isEmpty()) {
	        return;
	    }
	    if (type == null) {
	    	type = "";
	    }
	    String date = document.getIssueDate();
	    
	    openElement("Document");   						    	    
	        writeCodeElement("Type", document.getType(), "KCX0035"); 	        
	        writeElement("Number", document.getNumber());
	        	     
	        if (!Utils.isStringEmpty(date)) {	        		
	        	date.trim();  
	        	if (date.length() == 10) {
	        		writeDateToString("IssueDate", date);        		        		
	        	} else {
	        		writeElement("IssueDate", date); 
	        	}
	        }	        
	        if (type.equals("item")) {
	        	writeElement("CodeExists", document.getCodeExists());
	        	writeElement("WriteOffAmountValue", document.getWriteOffAmountValue());
	        	writeElement("WriteOffMeasuringUnit", document.getWriteOffMeasuringUnit());
	        	writeElement("WriteOffQualifierMeasuringUnit", document.getWriteOffQualifierMeasuringUnit());
	        }	       
	    closeElement();									
	}
	
	public void writeAccountList(List<Account> list, String name) throws XMLStreamException { 
		if (list == null) { 
    	    return;
    	}
    	if (list.isEmpty()) { 
    	    return;
    	}
    	for (Account account : list) {
    		writeAccount(account, name);			
    	}
	}
	public void writeAccount(Account argument, String name) throws XMLStreamException { 
		if (argument == null) { 
    	    return;
    	}
    	if (argument.isEmpty()) { 
    	    return;
    	}
    	openElement(name);
			writeElement("TIN", argument.getTIN());	
			writeElement("Name", argument.getName());	
			writeElement("AccountNumber", argument.getAccountNumber());	
			writeElement("DueDate", argument.getDueDate());	
			writeElement("RegionalFinanceOffice", argument.getRegionalFinanceOffice());	
			writeElement("PaymentType", argument.getPaymentType());	
			writeElement("KindOfExemption", argument.getKindOfExemption());	
			writeElement("AmountOfDuty", argument.getAmountOfDuty());	
			writeElement("DefermentAccountType", argument.getDefermentAccountType());   //EI20120928
		closeElement(); 
	}
	public void writeClearanceOffice(Office argument) throws XMLStreamException {
		if (argument == null) { 
    	    return;
    	}
		if (argument.isEmpty()) { 
    	    return;
    	}		
		openElement("CustomsOfficeOfClearance");
			writeElement("Code", argument.getCode());	
			writeElement("Name", argument.getName());	
			writeElement("Officer", argument.getOfficer());	
			writeElement("Phone", argument.getPhone());					
		closeElement();
	}
	
	public void writeOffice(Office argument, String office) throws XMLStreamException {
		if (argument == null) { 
    	    return;
    	}
		if (argument.isEmpty()) { 
    	    return;
    	}
		if (Utils.isStringEmpty(office)) { 
    	    return;
    	}
		openElement(office);		
			writeElement("Name", argument.getName());	
			writeElement("AccountNumber", argument.getAccountNumber());	
			writeElement("BankID", argument.getBankID());	
			writeElement("Bank", argument.getBank());	
			writeElement("IBAN", argument.getIBAN());	
			writeElement("BIC", argument.getBIC());			
		closeElement(); 
	}
	
	public void writeTraders(Traders traders, String msg) throws XMLStreamException { 
		if (traders == null) { 
    	    return;
    	}
    	if (traders.isEmpty()) {
    		return;
    	}
    	openElement("Traders");
    		writeImportTin("DeclarantTIN", traders.getDeclarantTIN());
    		writeImportAddress("Declarant", traders.getDeclarantAddress());
    		writeImportTin("RepresentativeTIN", traders.getRepresentativeTIN());
    		writeImportAddress("Representative", traders.getRepresentativeAddress());
    		
    		if (!msg.equalsIgnoreCase("CTX")) {
    			writeImportTin("ConsignorTIN", traders.getConsignorTIN());
        		writeImportAddress("Consignor", traders.getConsignorAddress());    			
        		writeImportTin("AcquirerTIN", traders.getAcquirerTIN());
        		writeImportAddress("Acquirer", traders.getAcquirerAddress());
    		}
    		
    		writeImportTin("ConsigneeTIN", traders.getConsigneeTIN());
    		writeImportAddress("Consignee", traders.getConsigneeAddress());
    		
    		if (!msg.equalsIgnoreCase("CTX")) {   
    			//writeImportTin("DeclarantForInvoiceTIN", traders.getDeclarantForInvoiceTIN());
        		//writeImportAddress("DeclarantForInvoice", traders.getDeclarantForInvoiceAddress());    		
    			writeImportTin("BuyerTIN", traders.getBuyerTIN());
    			//EI20130521: writeImportAddress("BuyerAddress", traders.getBuyerAddress());  //EI20130319
    			writeImportAddress("Buyer", traders.getBuyerAddress());  //EI20130319
    			writeImportTin("SellerTIN", traders.getSellerTIN());
    			//EI20130521: writeImportAddress("SellerAddress", traders.getSellerAddress());     			
    			writeImportAddress("Seller", traders.getSellerAddress()); 
    			writeImportTin("CustomsValueDeclarantTIN", traders.getCustomsValueDeclarantTIN());
    			//EI20130521: writeImportAddress("CustomsValueDeclarantAddress", traders.getCustomsValueDeclarantAddress());   
    			writeImportAddress("CustomsValueDeclarant", traders.getCustomsValueDeclarantAddress());    		
    			writeImportTin("RepresentativeOfCustomsValueDeclarantTIN", traders.getRepresentativeOfCustomsValueDeclarantTIN());
    			//EI20130521: writeImportAddress("RepresentativeOfCustomsValueDeclarantAddress", traders.getRepresentativeOfCustomsValueDeclarantAddress());
    			writeImportAddress("RepresentativeOfCustomsValueDeclarant", traders.getRepresentativeOfCustomsValueDeclarantAddress());
    			writeImportTin("DeclarantForInvoiceTIN", traders.getDeclarantForInvoiceTIN());
    			//EI20130521: writeImportAddress("DeclarantForInvoiceAddress", traders.getDeclarantForInvoiceAddress());
    			writeImportAddress("DeclarantForInvoice", traders.getDeclarantForInvoiceAddress());
    		}
    		
		closeElement(); 
	}
	
	public void writeImportTin(String person, TIN argument) throws XMLStreamException {
		if (argument == null) { 
    	    return;
    	}
		if (argument.isEmpty()) { 
    	    return;
    	}
		if (Utils.isStringEmpty(person)) { 
    	    return;
    	}
		openElement(person); 
			writeElement("TIN", argument.getTIN());
			writeElement("CustomerID", argument.getCustomerId());
			writeElement("VATNumber", argument.getVATNumber());      
        closeElement(); 
	}
	public void writeImportAddress(String person, Address argument) throws XMLStreamException {
    	if (argument == null) { 
    	    return;
    	}
    	if (Utils.isStringEmpty(person)) { 
    	    return;
    	}
    	if (argument.isEmpty()) { 
    	    return;
    	}   	  
    	
    	openElement(person + "Address"); 
            writeElement("Name", argument.getName());
            writeElement("Street", argument.getStreet());
            writeElement("HouseNumber", argument.getHouseNumber());
            writeElement("City", argument.getCity());
            writeElement("PostalCode", argument.getPostalCode());                                   
            writeElement("Country", argument.getCountry());           
            writeElement("CountrySubEntity", argument.getCountrySubEntity());
        closeElement();     
    } 
	
	public void writeRepresentativeContact(ContactPerson argument) throws XMLStreamException { 
		if (argument == null) {
    	    return;
    	}       	
    	openElement("Representative");
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
	public void writeContactPerson(String person, ContactPerson argument) throws XMLStreamException { 
    	if (argument == null) {
    	    return;
    	}   
    	if (person == null) {
    	    return;
    	} 
    	openElement(person + "ContactPerson");
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
	public void writeMeansOfTransport(String tagName, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	}    
    	if (tagName == null) {
    		return;
    	}
     	openElement("MeansOfTransport" + tagName);        	    
            writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");
            writeCodeElement("TransportationType", argument.getTransportationType(), "KCX0004");
            writeElement("TransportationNumber", argument.getTransportationNumber()); 
            writeElement("TransportationCountry", argument.getTransportationCountry());          
            writeElement("Description", argument.getDescription());                              
     	closeElement();          
    }   		
	public void writeIncoTerms(IncoTerms argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	}    	                   
     	openElement("IncoTerms");        	    
            writeElement("Key", argument.getKey());            
            writeElement("Code", argument.getCode());
            writeElement("Details", argument.getDetails()); 
            writeElement("Place", argument.getPlace());       
            writeElement("LocationCode", argument.getLocationCode());     //EI20120308
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
            writeElement("Amount", argument.getAmount());
            writeElement("Currency", argument.getCurrency());
            writeElement("ExchangeRate", argument.getExchangeRate()); 
            writeElement("RelevantTime", argument.getRelevantTime());                                      
     	closeElement();          
    }
	public void writeItemInvoice(Invoice argument) throws XMLStreamException {  //EI20130610
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	}    	                   
     	openElement("Invoice");        	    
            writeElement("Amount", argument.getAmount());
            writeElement("ReductionSurcharge", argument.getReductionSurcharge());
            writeElement("Discount", argument.getDiscount());                                              
     	closeElement();          
    }
	public void writeContainer(Container argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.getNumberList() == null) {
    	    return;
    	}    	                   
     	openElement("Container");  
     	for (String nr : argument.getNumberList()) {                  
            writeElement("Number", nr);             
     	}
     	closeElement();          
    }  
	public void writeDV1(DV1 argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	}    	                   
     	openElement("DV1");        	    
            
     		if (argument.getBuyerSellerRelationship() != null) { //temporaer bis die KFF richtige KCX_Code schicken können
     			if (argument.getBuyerSellerRelationship().equals("0") || argument.getBuyerSellerRelationship().equals("1") || 
     					argument.getBuyerSellerRelationship().equals("2")) {
     				writeElement("BuyerSellerRelationship", argument.getBuyerSellerRelationship()); 
     			} else {
     				writeCodeElement("BuyerSellerRelationship", argument.getBuyerSellerRelationship(), "KCX0075"); 
     			}
     		}
            writeElement("BuyerSellerRelationshipDetails", argument.getBuyerSellerRelationshipDetails());          
            writeElement("RestrictionsCode", argument.getRestrictionsCode());
            writeElement("RestrictionsText", argument.getRestrictionsText());  
        	writeElement("LicenseFeeDueCode", argument.getLicenseFeeDueCode());   
        	writeElement("LicenseFeeText", argument.getLicenseFeeText());  
        	writeElement("ResaleSurrenderUsageCode", argument.getResaleSurrenderUsageCode());  
        	writeElement("ResaleSurrenderUsageText", argument.getResaleSurrenderUsageText());          	
        	//writeCodeElement("AgentRepresentationDV1Code", argument.getAgentRepresentationDV1Code(), "KCX0046"); 
        	if (argument.getAgentRepresentationDV1Code() != null) {  //temporaer bis die KFF richtige KCX_Code schicken können 
     			if (argument.getAgentRepresentationDV1Code().equals("0") || argument.getAgentRepresentationDV1Code().equals("1")) {
     				writeElement("AgentRepresentationDV1Code", argument.getAgentRepresentationDV1Code()); 
     			} else {
     				writeCodeElement("AgentRepresentationDV1Code", argument.getAgentRepresentationDV1Code(), "KCX0046"); 
     			}
     		}
        	writeElement("TermsServicesCode", argument.getTermsServicesCode());  
        	writeElement("TermsServicesText", argument.getTermsServicesText());  
        	writeElement("TextPreviousDecisions", argument.getTextPreviousDecisions());          	
     	closeElement();          
    }  
	
	public void writeDeferment(Deferment argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    	
    	if (argument.isEmpty()) {
    	    return;
    	}    	                   
     	openElement("Deferment");        	                
            writeElement("Type", argument.getType()); 
            writeElement("TIN", argument.getTIN());          
            writeElement("CustomerID", argument.getCustomerId()); 
            writeElement("AccountNumber", argument.getAccountNumber());  //EI20130227
     	closeElement();          
    }  	
	public void writeCommodityCodeImport(CommodityCode argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("CommodityCode");
			writeElement("TarifCode", argument.getTarifCode());			
			writeElement("TarifAddition1", argument.getTarifAddition1());
			writeElement("TarifAddition2", argument.getTarifAddition2());			
		closeElement();
	}
	public void writeQuota(Quota argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("Quota");
			writeElement("Number1", argument.getNumber1());			
			writeElement("Number2", argument.getNumber2());
			writeElement("Quantity", argument.getQuantity());		
			writeElement("MeasuringUnit", argument.getMeasuringUnit());
			writeElement("QualifierMeasuringUnit", argument.getQualifierMeasuringUnit());		
		closeElement();
	}
	public void writeImportPackage(ImportPackage argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("Package");
			writeElement("Quantity", argument.getQuantity());			
			writeCodeElement("Type", argument.getType(), "KCX0066");
			writeElement("Marks", argument.getMarks());			
		closeElement();
	}
	public void writePayment(String tagName, Payment argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	}
		if (Utils.isStringEmpty(tagName)) {
    	    return;
    	}  
		openElement(tagName);
			writeElement("Amount", argument.getAmount());			
			writeElement("Currency", argument.getCurrency());
			writeElement("Rate", argument.getRate());		
			writeElement("Code", argument.getCode());		
		closeElement();
	}
	public void writeStatisticAndOther(String tagName, Statistic argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		if (Utils.isStringEmpty(tagName)) {
    	    return;
    	} 
		openElement(tagName);
			writeElement("Quantity", argument.getQuantity());
			writeCodeElement("MeasuringUnit", argument.getMeasuringUnit(), "KCX0025");
			writeCodeElement("QualifierMeasuringUnit", argument.getQualifierMeasuringUnit(), "KCX0025");
			if (tagName.equals("Statistic")) {
				writeElement("StatisticalValue", argument.getStatisticalValue());	
			}
		closeElement();
	}
	public void writePreference(Preference argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("Preference");
			writeCodeElement("PreferenceCertificate", argument.getPreferenceCertificate(), "KCX0035");		
			writeElement("PreferenceCertificateNumber", argument.getPreferenceCertificateNumber());			
			String date = argument.getDate();
        	if (!Utils.isStringEmpty(date)) {	        		
        		date.trim();  
        		if (date.length() == 10) {
        			writeDateToString("Date", date);        		        		
        		} else {
        			writeElement("Date", date); 
        		}
        	}
			writeElement("CodeExists", argument.getCodeExists());		
		closeElement();
	}
	
	public void writeSalary(Salary argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("Salary");
			writeElement("Type", argument.getType());			
			writeElement("RateOrPercent", argument.getRateOrPercent());				
		closeElement();
	}
	public void writetExcise(Excise argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("Excise");
			writeElement("TaxCode", argument.getTaxCode());			
			writeElement("TaxQuantity", argument.getTaxQuantity());
			writeElement("MeasuringUnit", argument.getMeasuringUnit());	
			writeElement("QualifierMeasuringUnit", argument.getQualifierMeasuringUnit());	
			writeElement("RateOrPercent", argument.getRateOrPercent());	
			writeElement("TaxValue", argument.getTaxValue());	
		closeElement();
	}
	public void writeAdditionalCosts(AdditionalCosts argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		openElement("AdditionsDeductions");
			writeElement("Type", argument.getType());
			writeElement("Amount", argument.getAmount());
			writeElement("Currency", argument.getCurrency());		
			writeElement("IATARateCode", argument.getIATARateCode());			
			writeElement("RateAgreedCode", argument.getRateAgreedCode());
			String date = argument.getDate();
        	if (!Utils.isStringEmpty(date)) {	        		
        		date.trim();  
        		if (date.length() == 10) {
        			writeDateToString("Date", date);        		        		
        		} else {
        			writeElement("Date", date); 
        		}
        	}
			writeElement("PercentageFreightCost", argument.getPercentageFreightCost());			
				
		closeElement();
	}
	
	public void writeSpecialStatement(String tagName, SpecialStatement argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
		if (argument.isEmpty()) {
    	    return;
    	} 
		if (Utils.isStringEmpty(tagName)) {
    	    return;
    	}
		openElement(tagName);
		if (tagName.equals("SpecialCaseStatement")) {
			writeElement("Group", argument.getGroup());	
			writeElement("Type", argument.getType());	
			writeElement("Value", argument.getValue());
		} else {
			writeElement("Group", argument.getGroup());			
			writeElement("Value", argument.getValue());
			writeElement("Type", argument.getType());	
		}
		closeElement();
	}
	
	public void writeBusiness(Business argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (Utils.isStringEmpty(argument.getBusinessTypeCode())) {
    		return;
    	}    	
    	openElement("Business");    
    		writeCodeElement("BusinessTypeCode", argument.getBusinessTypeCode(), "KCX0022");    	
    	closeElement();
    }	
	
	public void writeManifest(Manifest argument) throws XMLStreamException {  //EI20121122
    	if (argument == null) {
    	    return;
    	}    	   
    	openElement("ManifestCompletion");    
    		writeElement("RegistrationNumber", argument.getRegistrationNumber());  
    		writeElement("ItemNumber", argument.getItemNumber()); 
    		writeElement("NumberOfPieces", argument.getNumberOfPieces()); 
    		if (argument.getCustodian() != null) {
    			openElement("CustodianTIN"); 
    				writeElement("CustomerId", argument.getCustodian().getCustomerId()); 
    				writeElement("TIN", argument.getCustodian().getTIN()); 
    				writeElement("BO", argument.getCustodian().getBO()); 
        		closeElement();
    		}
    		if (argument.getSpecificKey() != null) {
    			openElement("SpecificKey"); 
    				writeElement("Code", argument.getSpecificKey().getCode()); 
    				writeElement("Number", argument.getSpecificKey().getNumber()); 
        		closeElement();
    		}    		
    		writeElement("ATLASAlignment", argument.getATLASAlignment()); 
    	closeElement();
    }	
	
	public void writeBondedWarehouseCompletion(Completion argument) throws XMLStreamException {  //EI20121122
    	if (argument == null) {
    	    return;
    	}    	   
    	openElement("ManifestCompletion");    
    		writeElement("RegistrationNumber", argument.getRegistrationNumber());  
    		writeElement("ItemNumber", argument.getItemNumber()); 
    		writeElement("ATLASInFlow", argument.getATLASInFlow());  
    		writeElement("CommonUse", argument.getCommonUse()); 
    		writeElement("CommodityCode", argument.getCommodityCode()); 
    		if (argument.getTradedVolume() != null) {
    			writeElement("Qualifier", argument.getTradedVolume().getQualifier()); 
        		writeElement("UnitOfMeasurement", argument.getTradedVolume().getUnit()); 
        		writeElement("Quantity", argument.getTradedVolume().getQuantity()); 
    		}  
    		writeElement("AdditionalInformation", argument.getInformation()); 
    		writeElement("ATLASAlignment", argument.getATLASAlignment()); 
    	closeElement();
    }	
	
	public void writeInwardProcessingCompletion(Completion argument) throws XMLStreamException {  //EI20121122
    	if (argument == null) {
    	    return;
    	}    	   
    	openElement("ManifestCompletion");    
    		writeElement("RegistrationNumber", argument.getRegistrationNumber());  
    		writeElement("ItemNumber", argument.getItemNumber()); 
    		writeElement("ATLASInFlow", argument.getATLASInFlow());     		
    		writeElement("ComplementOfInformation", argument.getInformation()); 
    		writeElement("ATLASAlignment", argument.getATLASAlignment()); 
    	closeElement();
    }
	
	public void writeAdditionalInfoStatement(AdditionalInfoStatement argument) throws XMLStreamException {  //EI20131028
		if (argument == null) {
    	    return;
    	}    	   
    	openElement("AdditionalInfoStatement");    
    		writeElement("StatementCode", argument.getStatementCode());  
    		writeElement("StatementText", argument.getStatementText()); 
    		writeElement("StatementType", argument.getStatementType());     		    		
    	closeElement();
	}
	
	public void writeAdditionalInformation(AdditionalInformation argument) throws XMLStreamException {  //EI20131028
		if (argument == null) {
    	    return;
    	}    	   
    	openElement("AdditionalInformation");    
    		writeElement("StatementCode", argument.getStatementCode());  
    		writeElement("StatementText", argument.getStatementText()); 
    		writeElement("StatementType", argument.getStatementType());     		    		
    	closeElement();
	}
	
}

