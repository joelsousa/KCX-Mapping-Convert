package com.kewill.kcx.component.mapping.formats.kids.Import;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemTaxAssessment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageImport;

/**
 * Module	   : Import.<br>
 * Created	   : 12.09.2011<br>
 * Description : BodyImportTaxAssessmentKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyImportTaxAssessmentKids extends KidsMessageImport {

	private MsgImportTaxAssessment message;	

    public BodyImportTaxAssessmentKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportTaxAssessment getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportTaxAssessment argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ImportTaxAssessment");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("UCR", message.getUCR());  
            	writeElement("RegistrationCode", message.getRegistrationCode());
            	
            	writeClearanceOffice(message.getCustomsOfficeOfClearance());           		
            	writeElement("CustomsOfficeOfRemedies", message.getCustomsOfficeOfRemedies());
            	writeOffice(message.getCustomsOfficeOfPayment(), "CustomsOfficeOfPayment");
            	
            	//writeElement("DateOfRegistration", message.getDateOfRegistration());
            	writeElement("RegistrationDate", message.getDateOfRegistration());       //EI20110927
            	writeElement("TotalAmountOfExemption", message.getTotalAmountOfExemption());
            	writeElement("TotalAmountOfSecurity", message.getTotalAmountOfSecurity());
            	writeElement("TotalAmountOfCashSecurity", message.getTotalAmountOfCashSecurity());
            	writeElement("TotalAmountOfNonCashSecurity", message.getTotalAmountOfNonCashSecurity());  
            	writeElement("TaxDeductionCode", message.getTaxDeductionCode());
            	writeElement("Findings", message.getFindings());
            	writeElement("DutyType", message.getDutyType());
            	
            	writeAccountList(message.getDefermentAccountList(), "DefermentAccount");       
            	writeTraders(message.getTraders(), "CTX");
            	
            	writeImportAddress("CustomsOfficeOfClearance", message.getCustomsOfficeOfClearanceAddress());            	
            	writeImportAddress("CustomsOfficeOfPaymentAddress", message.getCustomsOfficeOfPaymentAddress());            	
            	writeImportAddress("CustomsOfficeOfRemediesAddress", message.getCustomsOfficeOfRemediesAddress());
            	writeImportAddress("AddressForInvoice", message.getAddressForInvoice());
            
                 if (message.getGoodsItemList() != null) {
	                for (GoodsItemTaxAssessment goodsItem : message.getGoodsItemList()) {
	                   writeGoodsItem(goodsItem);
	                }
                 }
                    	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    private void writeGoodsItem(GoodsItemTaxAssessment item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}
    	
	    openElement("GoodsItem");
	    	writeElement("ItemNumber", item.getItemNumber());
	    	writeElement("ExaminationText", item.getExaminationText());  
        	writeElement("AdditionalProof", item.getAdditionalProof());
        	writeElement("TimeLimitAdditionalProof", item.getTimeLimitAdditionalProof());
        	writeElement("MessageOfDischarge", item.getMessageOfDischarge());
        	writeElement("AcceptanceDate", item.getAcceptanceDate());  
        	writeElement("ReleaseDate", item.getReleaseDate());
        	writeElement("GrantedBenefitCode", item.getGrantedBenefitCode());
        	writeElement("ExaminationCode", item.getExaminationCode());
        	writeElement("DischargeCode", item.getDischargeCode());  
        	writeElement("AcceptanceCode", item.getAcceptanceCode());
        	writeElement("DeviantAssessmentCode", item.getDeviantAssessmentCode());
        	writeElement("VATValue", item.getVATValue());
        	writeElement("AmountOfGarantee", item.getAmountOfGarantee());  
        	writeElement("ExchangeRate", item.getExchangeRate());
        	writeElement("CustomsValue", item.getCustomsValue());
        	writeElement("Findings", item.getFindings());
        	
        	writeDutiesList(item.getDutiesList());        	
        	writeImportDocumentList(item.getDocumentList(), "CTXPos");
        	
        	
	    closeElement();
    }
}
