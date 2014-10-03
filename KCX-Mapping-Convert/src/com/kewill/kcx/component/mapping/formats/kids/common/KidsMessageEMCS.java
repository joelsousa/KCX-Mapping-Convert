package com.kewill.kcx.component.mapping.formats.kids.common;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AadVal;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ComplementConsignee;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.Diagnosis;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.DocumentCertificate;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsPackage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EvidenceOfEvent;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExportAcceptance;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.SubmittingPerson;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.TransportDetails;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.UnsatisfactoryReason;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.WineProduct;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: EMCS<br>
 * Created		: 04.05.2010<br>
 * Description	: Fields and methods to write EMCS-KidsMessages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsMessageEMCS  extends KidsMessage { 	
 
	public void writeList(String tagName, List<String> list) throws XMLStreamException {
		if (list == null) {
	    		return;
	    }
	    if (Utils.isStringEmpty(tagName)) {
	    		return;
	    }  
	    int size = list.size();
	    for (int i = 0; i < size; i++) {					
			writeElement(tagName, list.get(i));						
		}
	}
	   
	public void writeTransportDetailsList(List<TransportDetails> list) throws XMLStreamException {    	
		if (list == null) {
    		return;
		}    
		int size = list.size();
		for (int i = 0; i < size; i++) {					
			writeTransportDetails(list.get(i));						
		}
    }
	
    public void writeTransportDetails(TransportDetails argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	    	    	
    	openElement("TransportDetails");
    		//writeCodeElement("TransportUnitCode", argument.getTransportUnitCode(), "C0035");      
    	    writeElement("TransportUnitCode", argument.getTransportUnitCode());
    		writeElement("IdentityOfTransportUnits", argument.getIdentityOfTransportUnits());      	
    		writeElement("CommercialSealIdentification", argument.getCommercialSealIdentification()); 
    		if (argument.getComplementaryInformation() != null) {
    			//writeCodeElementWithAttribute("ComplementaryInformation", 
    			//			argument.getComplementaryInformation().getText(),
				//			"C0012", "language", argument.getComplementaryInformation().getLanguage());
    			writeElementWithAttribute("ComplementaryInformation",
    						argument.getComplementaryInformation().getText(),
    						"language", argument.getComplementaryInformation().getLanguage());
    		}
    		if (argument.getSealInformation() != null) {
    			//writeCodeElementWithAttribute("SealInformation", argument.getSealInformation().getText(),
				//		  	"C0012", "language", argument.getSealInformation().getLanguage());
    			writeElementWithAttribute("SealInformation", argument.getSealInformation().getText(),
					  	"language", argument.getSealInformation().getLanguage());
    		}
    	closeElement();   	
    }
    
    public void writeTrader(String person, EmcsTrader trader) throws XMLStreamException {
       	if (trader == null) {
    		return;
    	}
    	if (Utils.isStringEmpty(person)) {
    		return;
    	}
    	String country = trader.getCountryCodeISO();
    	String customer = trader.getCustomerID();
    	String tax = trader.getExciseTaxNumber();
    	String vat = trader.getVatId();
    	String name = trader.getName();
    	String street = trader.getStreet();
    	String number = trader.getStreetNumber();
    	String plz = trader.getPostalCode();
    	String city = trader.getCity();   
    	//EI20110930: String language = trader.getLanguage();
    	
    	if (Utils.isStringEmpty(country) && Utils.isStringEmpty(customer) &&
    		Utils.isStringEmpty(tax) && Utils.isStringEmpty(vat) &&
    		Utils.isStringEmpty(name) && Utils.isStringEmpty(street) &&
    		Utils.isStringEmpty(number) && Utils.isStringEmpty(plz) && Utils.isStringEmpty(city)) {
        		return;
    	}
       	openElement(person);
       		//writeCodeElement("CountryCodeISO", country, "C0012");   
       	    writeElement("CountryCodeISO", country);  
       		writeElement("CustomerID", customer);      	
       		writeElement("ExciseTaxNumber", tax); 
       		writeElement("VATID", vat);  
       		writeElement("Name", name); 
       		writeElement("Street", street); 
       		writeElement("StreetNumber", number); 
       		writeElement("PostalCode", plz); 
       		writeElement("City", city); 
		closeElement();  
    }
    
    public void writeTraderList(String tagName, List <EmcsTrader> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	
        int sizeList = list.size();
        for (int i = 0; i < sizeList; i++) {	                        		
    		writeTrader(tagName, list.get(i));
    	}              
    }
    
    public void writeDocumentCertificateList(List<DocumentCertificate> list) throws XMLStreamException {		
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
    		//writeCodeElementWithAttribute("DocumentDescription", argument.getDocumentDescription().getText(),
			//			  "C0012", "language", argument.getDocumentDescription().getLanguage());
       		writeElementWithAttribute("DocumentDescription", argument.getDocumentDescription().getText(),
					  "language", argument.getDocumentDescription().getLanguage());
       	}
       	if (argument.getDocumentReference() != null) {
       		//writeCodeElementWithAttribute("DocumentReference", argument.getDocumentReference().getText(),
			//			  "C0012", "language", argument.getDocumentReference().getLanguage());
       		writeElementWithAttribute("DocumentReference", argument.getDocumentReference().getText(),
					  "language", argument.getDocumentReference().getLanguage());
       	}
		closeElement();		
    }
   
    public void writePackageList(List<EmcsPackage> list) throws XMLStreamException {		
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
	   	    //writeCodeElement("KindOfPackages", argument.getKindOfPackages(), "C0017");  
	   	    writeElement("KindOfPackages", argument.getKindOfPackages());  
	   		writeElement("NumberOfPackages", argument.getNumberOfPackages());
	    	writeElement("CommercialSealIdentification", argument.getCommercialSealIdentification());
    		if (argument.getSealInformation() != null) {
    			//writeCodeElementWithAttribute("SealInformation", argument.getSealInformation().getText(),
				//		  					  "C0012", "language", argument.getSealInformation().getLanguage());
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
	   		//writeCodeElement("WineGrowingZoneCode", argument.getWineGrowingZoneCode(), "C0040");   
	   		writeElement("WineGrowingZoneCode", argument.getWineGrowingZoneCode());   
	   		//writeCodeElement("WineProductCategory", argument.getWineProductCategory(), "C0038");
	   		writeElement("WineProductCategory", argument.getWineProductCategory());
	    	writeElement("ThirdCountryOfOrigin", argument.getThirdCountryOfOrigin());
	    	
	    	if (argument.getOtherInformation() != null) {
	    		//writeCodeElementWithAttribute("OtherInformation", argument.getOtherInformation().getText(),
	  			//		  "C0012", "language", argument.getOtherInformation().getLanguage());
	    		writeElementWithAttribute("OtherInformation", argument.getOtherInformation().getText(),
	  					  "language", argument.getOtherInformation().getLanguage());
    		}
	    	
	    	List<String> list = argument.getWineOperationCodeList();
	    	if (list != null) {
	    		int listSize = list.size();
	    		for (int i = 0; i < listSize; i++) {	                        		   	    			
	    			//writeCodeElement("WineOperationCode", list.get(i), "C0041");   
	    			writeElement("WineOperationCode", list.get(i));  
	    		}	
	    	}
		closeElement();  		
    }
   
    public void writeComplementConsignee(ComplementConsignee argument) throws XMLStreamException {
    	
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	openElement("ComplementConsignee");
    		//writeCodeElement("MemberStateCode", argument.getMemberStateCode(), "C0011");
    		writeElement("MemberStateCode", argument.getMemberStateCode());
    		writeElement("CertificateOfExemption", argument.getCertificateOfExemption());
    	closeElement();
    }   
    
    public void writeExportAcceptance(ExportAcceptance argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	
    	openElement("ExportAcceptance");
    		writeElement("SenderCustomsOffice", argument.getSenderCustomsOffice());
    		writeDateToString("DateOfAcceptance", argument.getDateOfAcceptance());
    		writeElement("DocumentReferenceNumber", argument.getDocumentReferenceNumber());
    		writeElement("CustomsOfficerID", argument.getCustomsOfficerID());
    	closeElement();  
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
		closeElement();
    }
    
    public void writeExciseMovementEaadList(List<ExciseMovementEaad> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeExciseMovementEaad(list.get(i));   
		}	
    }
    
    public void writeUnsatisfactoryReasonList(List<UnsatisfactoryReason> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeUnsatisfactoryReason(list.get(i));   
		}	
    } 
    
    public void writeUnsatisfactoryReason(UnsatisfactoryReason argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	String version = kidsHeader.getRelease();
    	version = Utils.removeDots(version.substring(0, 3));
    	openElement("UnsatisfactoryReason");
    		writeElement("UnsatisfactoryReasonCode", argument.getUnsatisfactoryReasonCode());
    		if (argument.getComplementaryInformation() != null) {    			
    			//writeCodeElementWithAttribute("ComplementaryInformation", 
    			//		argument.getComplementaryInformation().getText(),
    			//		"C0012", "language", argument.getComplementaryInformation().getLanguage());    	
    			writeElementWithAttribute("ComplementaryInformation", 
    					argument.getComplementaryInformation().getText(),
    					"language", argument.getComplementaryInformation().getLanguage());   
    		}
		closeElement();
    }  
    
    public void  writeFunctionalErrorList(List<FunctionalError> list, String version) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeFunctionalErrorCT(list.get(i), version);   
		}		    	
    }
    
    public void  writeFunctionalErrorCT(FunctionalError argument, String version) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}    
    	//String version = uidsHeader.getMessageVersion();
    	//version = Utils.removeDots(version.substring(0, 3));
    	
    	openElement("FunctionalError");
    		//EI20110629:
    		//writeElement("ErrorType", argument.getErrorType());
    		if (version.equals("10")) { 
    			writeElement("ErrorType", argument.getErrorType());
    		} else {
    			//writeCodeElement("ErrorType", argument.getErrorType(), "A0049"); 
    			writeElement("ErrorType", argument.getErrorType()); 
    		}  //EI20110629-end
			writeElement("ErrorReason", argument.getErrorReason());
			writeElement("ErrorLocation", argument.getErrorLocation());
			writeElement("OriginalAttributeValue", argument.getOriginalAttributeValue());
		closeElement(); 	    	
    }   
    
    public void  writeFunctionalErrorAadList(List<AadVal> list, String version) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeFunctionalErrorAad(list.get(i), version);   
		}		    	
    }  
    
    public void  writeFunctionalErrorAad(AadVal argument, String version) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}    
    	openElement("FunctionalError");
    	    writeElement("AadReferenceCode", argument.getAadReferenceCode());
    	    writeElement("SequenceNumber", argument.getSequenceNumber());
    	    if (version.equalsIgnoreCase("10") && argument.getFunctionalError() != null ) {   //EI20110927
    	    	writeElement("ErrorType", argument.getFunctionalError().getErrorType());
    	    	writeElement("ErrorPointer", argument.getFunctionalError().getErrorLocation());					
    	    	writeElement("OriginalAttributeValue", argument.getFunctionalError().getOriginalAttributeValue());
    	    	writeElement("ErrorReason", argument.getFunctionalError().getErrorReason());
    	    }
		closeElement(); 	    	
    }
    
    public void  writeErrorList(List<String> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeElement("Error", list.get(i));   
		}	    	
    }
      
    public void writeDiagnosisList(List<Diagnosis> list) throws XMLStreamException {
    	if (list == null) {
    		return;
    	}
    	
    	int listSize = list.size();
    	for (int i = 0; i < listSize; i++) {	                        		   	    			
    		writeDiagnosis(list.get(i));   
		}	    	
    }
    
    public void writeDiagnosis(Diagnosis argument) throws XMLStreamException {
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}    
    	openElement("Diagnosis");
			writeElement("AadReferenceCode", argument.getAadReferenceCode());
			writeElement("ItemNumber", argument.getItemNumber());
			writeElement("DiagnosisCode", argument.getDiagnosisCode());			
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
			//writeCodeElement("SubmittingPersonCode", argument.getSubmittingPersonCode(), "C0034");
			writeElement("SubmittingPersonCode", argument.getSubmittingPersonCode());
			writeElement("SubmittingPersonName", argument.getSubmittingPersonName());
			if (argument.getSubmittingPersonComplement() != null) {			
				//writeCodeElementWithAttribute("SubmittingPersonComplement",
				//	argument.getSubmittingPersonComplement().getText(), "C0012", 
				//	"language", argument.getSubmittingPersonComplement().getLanguage());
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
			//writeCodeElement("EvidenceTypeCode", argument.getEvidenceTypeCode(), "C0001");		
			writeElement("EvidenceTypeCode", argument.getEvidenceTypeCode());		
			if (argument.getIssuingAuthority() != null) {			
				//writeCodeElementWithAttribute("IssuingAuthority",
				//	argument.getIssuingAuthority().getText(), "C0012", 
				//	"language", argument.getIssuingAuthority().getLanguage());
				writeElementWithAttribute("IssuingAuthority",
						argument.getIssuingAuthority().getText(),
						"language", argument.getIssuingAuthority().getLanguage());
			}
			if (argument.getReferenceOfEvidence() != null) {			
				//writeCodeElementWithAttribute("ReferenceOfEvidence",
				//	argument.getReferenceOfEvidence().getText(), "C0012", 
				//	"language", argument.getReferenceOfEvidence().getLanguage());
				writeElementWithAttribute("ReferenceOfEvidence",
						argument.getReferenceOfEvidence().getText(), 
						"language", argument.getReferenceOfEvidence().getLanguage());
			}
			if (argument.getEvidenceTypeComplement() != null) {			
				//writeCodeElementWithAttribute("EvidenceTypeComplement",
				//	argument.getEvidenceTypeComplement().getText(), "C0012", 
				//	"language", argument.getEvidenceTypeComplement().getLanguage());
				writeElementWithAttribute("EvidenceTypeComplement",
						argument.getEvidenceTypeComplement().getText(), 
						"language", argument.getEvidenceTypeComplement().getLanguage());
			}
		closeElement(); 	    	
    } 
    //EI20111007: in EMCS is "base64" as "base64Text" defined
    public void writePDFInformation(PDFInformation argument, String version) throws XMLStreamException {

    	if (getCommonFieldsDTO() != null) {
    		getCommonFieldsDTO().setPdfExists(false);
    	}
    	if (argument == null) {
    		return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}

    	openElement("PDFInformation");
    	writeElement("Name", argument.getName());
    	writeElement("Directory", argument.getDirectory());
    	writeElement("NewDirectory", argument.getNewDirectory());

    	List<String> pdfList = argument.getPdflist();
    	int listSize = pdfList.size();

		if (listSize > 0) {
			if (getCommonFieldsDTO() != null && getCommonFieldsDTO().isPdfTgz()) {
				writePdffile(pdfList);
				Utils.log("PDF nicht einbinden");
				getCommonFieldsDTO().setPdfExists(true);
			} else {
				for (int i = 0; i < pdfList.size(); i++) {
					if (version.equals("10")) {
						writeElement("base64", pdfList.get(i));
					} else {
					    writeElement("base64Text", pdfList.get(i));
					}
				}
			}
		} else {
			Utils.log("(KidsMessage writePDFInformation) pdfList is empty");
		}

		closeElement();
    }

}

