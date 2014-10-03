package com.kewill.kcx.component.mapping.formats.kids.Import;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Excise;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Salary;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.SpecialStatement;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageImport;

/**
 * Module	   : Import.<br>
 * Created	   : 09.02.2012<br>
 * Description : BodyImportDeclarationKids (for KidsToKids)
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyImportDeclarationKids extends KidsMessageImport {

	private MsgImportDeclaration message;	

    public BodyImportDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportDeclaration getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportDeclaration argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ImportDeclaration");
            openElement("GoodsDeclaration"); 
         
            writeElement("ReferenceNumber", message.getReferenceNumber()); 
            writeCodeElement("DeclarationPriorPresentation", message.getDeclarationPriorPresentation(), "KCX0001"); 
            //writeCodeElement("DeclarationPriorPresentation", message.getDeclarationPriorPresentation(), "KCX0068"); //EI20120813
        	writeCodeElement("AgentRepresentationCode", message.getAgentRepresentationCode(), "KCX0046");   
        	//writeCodeElement("AgentRepresentationCode", message.getAgentRepresentationCode(), "KCX0069");    //EI20120813
        	writeCodeElement("PreTaxDeductionCode", message.getPreTaxDeductionCode(), "KCX0001");  
        	//writeCodeElement("PreTaxDeductionCode", message.getPreTaxDeductionCode(), "KCX0070");       //EI20120813
        	writeCodeElement("TransportInContainer", message.getTransportInContainer(), "KCX0001");
        	//writeCodeElement("TransportInContainer", message.getTransportInContainer(), "KCX0071");     //EI20120813
        	writeBusiness(message.getBusiness());   	        	
        	writeCodeElement("PaymentType", message.getPaymentType(), "KCX0019"); 
        	//writeCodeElement("PaymentType", message.getPaymentType(), "KCX0072");    //EI20120813
        	writeElement("PlaceOfDeclaration", message.getPlaceOfDeclaration());   
        	writeElement("CustomsOfficeOfDeclaration", message.getCustomsOfficeOfDeclaration());  
        	writeTraders(message.getTraders(), "EZA");   
        	writeElement("DispatchCountry", message.getDispatchCountry()); 
        	writeElement("CustomsOfficeEntry", message.getCustomsOfficeEntry());      
        	writeElement("ImporterLocation", message.getImporterLocation());   
        	writeElement("GoodsLocation", message.getGoodsLocation()); 
        	writeElement("DestinationCountry", message.getDestinationCountry());     //EI20131028
        	writeElement("DestinationFederalState", message.getDestinationFederalState());      
        	writeElement("TaxOffice", message.getTaxOffice());   
        	writeCodeElement("CustomsStatus", message.getCustomsStatus(), "KCX0005"); 
        	//writeCodeElement("CustomsStatus", message.getCustomsStatus(), "KCX0073");           //EI20120813
        	writeCodeElement("StatisticalStatus", message.getStatisticalStatus(), "KCX9974"); 
        	//EI20120813 - bei Aktivierung von der unteren Zeile muss DB.kcx_codes.kcxcode_id geändert werden von kcx9974 auf kcx0074
        	//writeCodeElement("StatisticalStatus", message.getStatisticalStatus(), "KCX0074"); //EI20120813 - bei Aktivierung ... 
        	writeElement("TotalNumberPositions", message.getTotalNumberPositions());            //EI20120308 new in xsd
        	writeElement("GrossMass", message.getGrossMass());                       				
    		writePreviousDocument(message.getPreviousDocument(), "head");    
    		writeMeansOfTransport("Border", message.getMeansOfTransportBorder());
      		writeMeansOfTransport("Inland", message.getMeansOfTransportInland());      		
    		writeMeansOfTransport("Arrival", message.getMeansOfTransportArrival());        	       			
    		writeContactPerson("",  message.getContactPerson());					
    		writeIncoTerms(message.getIncoterms());			
    		writeInvoice(message.getInvoice());	
    		writeElement("WriteOffSumAType", message.getWriteOffSumAType());          	 
        	writeElement("WriteOffBonWarAvuvAuthNum", message.getWriteOffBonWarAvuvAuthNum());  
        	writeElement("WriteOffBonWarRefNum", message.getWriteOffBonWarRefNum());  
        	writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());              				
        	if (message.getDocumentList() != null) {	
        		for (Document doc : message.getDocumentList()) {
        			writeDocument(doc, "head");
        		}
        	}
        	writeContainer(message.getContainer());	
        	
        	if (message.getDefermentList() != null) {
        		for (Deferment def : message.getDefermentList()) {
        			writeDeferment(def);
        		}
        	}
        	writeElement("AdditionalInformation", message.getAdditionalInformation());    
        	writeAdditionalInfoStatement(message.getAdditionalInfoStatement());    //EI20131028
    		writeDV1(message.getDV1());    		    
    		                                                          
            if (message.getGoodsItemList() != null) {            
            	for (GoodsItemDeclaration item : message.getGoodsItemList()) {
            		writeGoodsItem(item);                        		
            	}   
            }
           
        closeElement();	    
        closeElement();	 
        closeElement();	

            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    
    private void writeGoodsItem(GoodsItemDeclaration item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}
    	
	    openElement("GoodsItem");
	    	writeElement("ItemNumber", item.getItemNumber());	    	
	    	writeElement("Text", item.getText());  	    	
	    	writeCommodityCodeImport(item.getCommodityCode());	    	
	    	writeElement("ProcedureCode", item.getProcedureCode());
	    	writeElement("DutyControlCode", item.getDutyControlCode()); 
	    	writeElement("SupplementaryText", item.getSupplementaryText()); 
	    	writeElement("Description", item.getDescription()); 
	    	writeElement("EAN", item.getEAN()); 
	    	writeElement("Amount", item.getAmount()); 
	    	writeCodeElement("UnitOfMeasurementAmount", item.getUnitOfMeasurementAmount(), "KCX0025"); 
	    	writeCodeElement("QualifierAmount", item.getQualifierAmount(), "KCX0025");
	    	writeElement("CountryOfOrigin", item.getCountryOfOrigin()); 
	    	writeElement("EUCode", item.getEUCode());
	    	writeQuota(item.getQuota()); 
	    	writeElement("PlaceOfIntroduction", item.getPlaceOfIntroduction()); 
	    	writeElement("PlaceOfDeparture", item.getPlaceOfDeparture()); 
	    	writeElement("NetMass", item.getNetMass()); 
	    	writeElement("GrossMass", item.getGrossMass()); 
	    	writeImportPackage(item.getImportPackage()); 
	    	writeElement("RequestedPrivilege", item.getRequestedPrivilege()); 
	    	writeElement("Condition", item.getCondition()); 
	    	writeElement("CustomsValue", item.getCustomsValue());
	    	writeElement("ImportTurnoverTax", item.getImportTurnoverTax()); 
	    	//writeInvoice(item.getInvoice()); 
	    	writeItemInvoice(item.getInvoice()); 				//EI20130601
	    	writePayment("NetPrice", item.getNetPrice()); 
	    	writePayment("IndirectPayment", item.getIndirectPayment()); 
	    	
	    	writeStatisticAndOther("Statistic", item.getStatistic()); 
	    	writeStatisticAndOther("GoodsQuantityCustoms", item.getGoodsQuantityCustoms()); 
	    	writeStatisticAndOther("GoodsQuantityAgriculturalDuty", item.getGoodsQuantityAgriculturalDuty()); 
	    	
	    	writeElement("ProcessingFeeValueIncrease", item.getProcessingFeeValueIncrease()); 	    	
	    	writeElement("ExtraCostImportVAT", item.getExtraCostImportVAT()); 	    	
	    	writeElement("TobaccoDutyCodeNumber", item.getTobaccoDutyCodeNumber()); 
	    	writePreference(item.getPreference());
	    	if (item.getDocumentList() != null) {
	    		for (Document doc : item.getDocumentList()) {
	    			writeDocument(doc, "item");
	    		}
	    	}
	    	if (item.getSalaryList() != null) {
	    		for (Salary sal : item.getSalaryList()) {
	    			writeSalary(sal);
	    		}
	    	}
	    	if (item.getExciseList() != null) {
	    		for (Excise exc : item.getExciseList()) {
	    			writetExcise(exc);
	    		}
	    	}
	    	if (item.getAdditionsDeductionsList() != null) {
	    		for (AdditionalCosts add : item.getAdditionsDeductionsList()) {
	    			writeAdditionalCosts(add);
	    		}
	    	}
	    	writeElement("AdditionsDeductionsDescription", item.getAdditionsDeductionsDescription());
	    	if (item.getReductionStatementList() != null) {
	    		for (SpecialStatement red : item.getReductionStatementList()) {
	    			writeSpecialStatement("ReductionStatement", red);
	    		}
	    	}
	    	if (item.getSpecialValueStatementList() != null) {
	    		for (SpecialStatement spe : item.getSpecialValueStatementList()) {
	    			writeSpecialStatement("SpecialValueStatement", spe);
	    		}
	    	}
	    	if (item.getSpecialCaseStatementList() != null) {
	    		for (SpecialStatement cas : item.getSpecialCaseStatementList()) {
	    			writeSpecialStatement("SpecialCaseStatement", cas);
	    		}
	    	}
	    closeElement();
    }
    
}
