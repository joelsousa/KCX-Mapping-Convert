package com.kewill.kcx.component.mapping.formats.kids.common;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Import V20<br>
 * Created		: 22.11.2012<br>
 * Description	: Fields and methods to write KidsImportMessages.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class KidsMessageImport20  extends KidsMessageImport { 	
 	
	
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
        		//writeImportAddress("DeclarantForInvoiceAddress", traders.getDeclarantForInvoiceAddress());    		
    			writeImportTin("BuyerTIN", traders.getBuyerTIN());
    			//EI20130521: writeImportAddress("BuyerAddress", traders.getBuyerAddress());  //EI20130319
    			writeImportAddress("Buyer", traders.getBuyerAddress());  //EI20130521 ohne ...Address
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
			if (Utils.isStringEmpty(argument.getBO())) {  //EI20130820
				argument.setBO("0000");    
			}
			writeElement("BO", argument.getBO());                            //new for V20
			writeElement("CustomerID", argument.getCustomerId());   //Kundennummer
			writeElement("IdentificationType", argument.getIdentificationType());    //EI20130801 for BDP: 
		    //EI20130801: CustomerIdentifier wird auf bsu_verwid gemapped, 
			//EI20130801: in zb_fss_70.doc steht zu verwid: "nicht verwendet, Identifikationsart zur Verwahrer EORI"
			writeElement("VATNumber", argument.getVATNumber());      
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
            writeElement("BO", argument.getBO());   //AK20130430
            writeElement("CustomerID", argument.getCustomerId()); 
            writeElement("AccountNumber", argument.getAccountNumber());   //EI20130227
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
    		writeElement("InvoicePrice", argument.getInvoicePrice());   //EI20130619  for BDP        
            writeElement("Currency", argument.getCurrency());   //EI20130619  for BDP 
    	closeElement();
    }
	
	public void writePreviousDocument(PreviousDocument document) throws XMLStreamException { 
		if (document == null) {
			return;
		}		
	    if (document.isEmpty()) {
	        return;
	    }	       		              
	    openElement("PreviousDocument");   		
	    	//writeCodeElement("Type", document.getType(), "KCX0052");
	        writeCodeElement("Type", document.getType(), "KCX0035");      //TODO Max-noch nicht klar       
	        writeElement("Number", document.getNumber());
	    closeElement();		     	        						
	}
}

