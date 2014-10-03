package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : Export/aes.<br>
 * Created      : 2.07.2012<br>
 * Description	: Kids Version 2.1.00: new Tags in Head-Part and GoodsItem
 * 				: V21: new Tags  
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportDeclarationKids extends KidsMessageV21 {

	private MsgExpDat message;	
	
    public BodyExportDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpDat getMessage() {
		return message;
	}
	public void setMessage(MsgExpDat argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
                openElement("soap:Body");
                openElement("ExportDeclaration");                
                openElement("GoodsDeclaration");                	                    	   
                    writeCodeElement("AreaCode", message.getAreaCode(), "KCX0005");
                    writeCodeElement("TypeOfPermit", message.getTypeOfPermit(), "KCX0006");                    	
                    writeCodeElement("KindOfDeclaration", message.getKindOfDeclaration(), "KCX0033");                    	
                    writeCodeElement("TypeOfPermitCH", message.getTypeOfPermitCH(), "KCX0042");
                    writeElement("StatusCode", message.getStatusCode());     
                    writeElement("UCRNumber", message.getUCRNumber());  
                    writeElement("CorrectionReason", message.getCorrectionReason());    
                    writeCodeElement("NCTSType", message.getNCTSType(), "KCX0043");  //TODO-IWA fehlt in xsd                  	
                    writeCodeElement("FinalCode", message.getFinalCode(), "KCX0001");                    	
                    writeDateTimeToString("DeclarationTime", message.getDeclarationTime());
                    writeDateTimeToString("AcceptanceTime", message.getAcceptanceTime());

                    writeDateToString("DateOfDeparture", message.getDateOfDeparture());
                    
                    writeElement("DispatchCountry", message.getDispatchCountry()); 
                    writeElement("DestinationCountry", message.getDestinationCountry());
                    writeElement("SituationCode", message.getSituationCode());                 	          	
                    writeElement("PaymentType", message.getPaymentType());
                    writeCodeElement("TransportInContainer", message.getTransportInContainer(), "KCX0001");                        
                    writeElement("NetMass", message.getNetMass());
                    writeElement("GrossMass", message.getGrossMass());                		         	
                    writeElement("UCROtherSystem", message.getUCROtherSystem());
                    writeElement("Annotation", message.getAnnotation());  
                    writeElement("ShipmentNumber", message.getShipmentNumber());
                    writeElement("ReferenceNumber", message.getReferenceNumber());                        
                    if (this.kidsHeader != null &&         
                        this.kidsHeader.getCountryCode() != null && this.kidsHeader.getCountryCode().equals("NL")) {
                    	writeElement("DeclarationNumber", message.getDeclarationNumber() + "");
                    }
                    writeElement("OrderNumber", message.getOrderNumber());
                    writeElement("TotalNumberPositions", message.getTotalNumberPositions());   
                    writeElement("TotalNumberOfPackages", message.getTotalNumberOfPackages());
                    writeElement("AuthorizationNumber", message.getAuthorizationNumber());
                    writeElement("PreviousProcedure", message.getPreviousProcedure());                        
                    writeElement("ReceiverCustomerNumber", message.getReceiverCustomerNumber());
                    writeElement("DeclarantIsConsignor", message.getDeclarantIsConsignor());
                    writeElement("TypeOfRepresentation", message.getTypeOfRepresentation());                  		
                	writeCodeElement("CorrectionCode", message.getCorrectionCode(), "KCX0041");                	
                	writeElement("BunchNumber", message.getBunchNumber());                		
                	writeCodeElement("Language", message.getLanguage(), "KCX0037");
                	writeElement("TransferToTransitSystem",  message.getTransferToTransitSystem());                 	
                  	writeContact("Contact", message.getContact());                   		
                  	writeElement("Validity", message.getValidity());                  		
                  	writeWareHouse(message.getWareHouse());                  		
                  	writeMeansOfTransport("", message.getMeansOfTransport()); 
                  	writeMeansOfTransport("Inland", message.getTransportMeansInland());
                	writeMeansOfTransport("Departure", message.getTransportMeansDeparture());    
                	writeMeansOfTransport("Border", message.getTransportMeansBorder());
                    writePlaceOfLoading(message.getPlaceOfLoading());
                    writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());
                    writeElement("CustomsOfficeForCompletion", message.getCustomsOfficeForCompletion());
                    writeElement("IntendedOfficeOfExit", message.getIntendedOfficeOfExit());                    	            
                    writeParty("SupervisingCustomsOffice", message.getSupervisingCustomsOffice());             		                   
                    writeElement("PlaceOfDeclaration", message.getPlaceOfDeclaration());
                    writeBusiness(message.getBusiness());
                    writeTransportationRoute(message.getTransportationRoute());                       
                    writeSeals(message.getSeal(), "ExpDat");                       //new for V21: sealOK
                    writeExportRefundHeader(message.getExportRefundHeader());
                    writeLoadingTime(message.getLoadingTime());                                           
                    writeParty("Forwarder", message.getForwarder());  
                    writeTIN("MessageReceiver", message.getMessageReceiverTIN());
                    writeParty("Consignor", message.getConsignor()); 
                    writeParty("ConsignorSecurity", message.getConsignorSecurity());
                    if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
                      	  this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130819
                      	  if (message.getDeclarant() != null) {
                      	      if (message.getDeclarant().getPartyTIN() != null && !Utils.isStringEmpty(message.getDeclarant().getPartyTIN().getTin()) &&
                      	    	  message.getDeclarant().getAddress() != null && !message.getDeclarant().getAddress().isEmpty()) {
                      	    	  	message.setDeclarant(null); 
                      	    	  	message.setDeclarantTIN(null);   
                      	    	  	//message.setDeclarantContactPerson(null);   
                      	      }
                      	  }
                      	 if (message.getRepresented() != null) {
                      		if (message.getRepresented().getPartyTIN() != null && !Utils.isStringEmpty(message.getRepresented().getPartyTIN().getTin()) &&
                      			message.getRepresented().getAddress() != null && !message.getRepresented().getAddress().isEmpty()) {
                        	    	message.setRepresented(null);    
                        	    	message.setRepresentedTIN(null);    
                      		} 
                      	 }
                    }
                    writeParty("Declarant", message.getDeclarant());    
                    writeElement("DeclarantNumber", message.getDeclarantNumber());              
                    writeParty("Agent", message.getAgent());                    
                    writeParty("Subcontractor", message.getSubcontractor());     
                    writeParty("Consignee", message.getConsignee());  
                    writeParty("ConsigneeSecurity", message.getConsigneeSecurity());             
                    writeParty("CustomsDocumentsReceiver", message.getCustomsDocumentsReceiver());                       	
                    writeParty("Represented", message.getRepresented());                       
                    writeParty("WarehouseKeeper", message.getWarehousekeeper());
                    writeParty("Beneficiary", message.getBeneficiary());
                    writeParty("InitialSender", message.getInitialSender());  
                    writeParty("Carrier", message.getCarrier()); 
                    writeElement("DescriptionLanguage", message.getDescriptionLanguage());
                    writeElement("AccountNumber", message.getAccountNumber());
                    writeElement("RepertoriumNumber", message.getRepertoriumNumber());                       	
                    writeElement("ControlResultCode", message.getControlResultCode());
                    writeIncoTerms(message.getIncoTerms());                                           
                    if (message.getDocumentList() != null) {                                  
                    	for (DocumentV20 tmpDoc : message.getDocumentList()) {                        		                        
                            writeDocument(tmpDoc);        			     		
                    	}    		
                    }                    	
                    writeContainerNumberList(message.getContainer());     
                    
                    if (message.getPreviousDocumentList() != null) { 
                    	//for (PreviousDocumentV20 tmpPrevDoc : message.getPreviousDocumentList()) { 
                    	for (PreviousDocumentV21 tmpPrevDoc : message.getPreviousDocumentList()) { 
                     		//writePreviousDocument(tmpPrevDoc, message.getFromFormat());   		//changes for V21 
                     		writePreviousDocument21(tmpPrevDoc);
                    	}
                    }
                    if (message.getAddInfoStatementList() != null) {                            
                    	for (Text text : message.getAddInfoStatementList()) { 
                    	    writeAddInfoStatement(text);
                    	}
                    }
                    writeElement("LoadingLists", message.getLoadingLists()); 
                    writeElement("SecurityIndicator", message.getSecurityIndicator());          
                    writeElement("InjunctionType", message.getInjunctionType());                
                    writeElement("CompanyNumberTaxPayer", message.getCompanyNumberTaxPayer());  
                    writeSpecialMentionList(message.getSpecialMentionList());                   
                 //new for V21 beginn:
                    writeElement("InvoiceCurrencyType", message.getInvoiceCurrencyType()); 
                    writeElement("AuthorizationTrustedExporter", message.getAuthorizationTrustedExporter()); 
                    writeElement("Procedure", message.getProcedure()); 
                    //EI20120912: writeElement("RelevantDate", message.getRelevantDate());   
                    writeDateToString("RelevantDate", message.getRelevantDate());         //EI20120912
                    writeElement("FlagOfStatistic", message.getFlagOfStatistic()); 
                    writeElement("RealOfficeOfExit", message.getRealOfficeOfExit()); 
                    if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
                  		  this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130819
                    	message.setAddressCombination("2");
                    } 
                    writeElement("AddressCombination", message.getAddressCombination()); 
                    writeParty("FinalRecipient", message.getFinalRecipient());  
                    writeOutwardProcessing(message.getOutwardProcessing());                     
                    if (message.getReentryList() != null) {                        	
                        for (Reentry reentry : message.getReentryList()) {
                        	writeReentry(reentry);                        		
                        }   
                    }
                    if (message.getMeansOfIdentificationList() != null) {                        	
                        for (MeansOfIdentification text : message.getMeansOfIdentificationList()) {
                        	writeMeansOfIdentification(text);
                        }
                    }
                    if (message.getProductList() != null) {                        	
                        for (Product tarif : message.getProductList()) {
                        	writeProduct(tarif);
                        }
                    }
                  //EI20120912: writeElement("DateOfExit", message.getDateOfExit()); 
                    writeDateToString("DateOfExit", message.getDateOfExit()); //EI20120912
                //new for V21 end                                                           
                    if (message.getGoodsItemList() != null) {                        	
                        for (MsgExpDatPos item : message.getGoodsItemList()) {
                        	writeGoodsItemList(item);                        		
                        }   
                    }
                    writeInvoice(message.getInvoice());  //in xsd sind viele neue Tags                                       
                    writeCAP(message.getCap());                                                 
                    writeElement("TemporaryReason", message.getTemporaryReason());                                     
                closeElement();	    
                closeElement();	 
                closeElement();	       
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
   
	private void writeGoodsItemList(MsgExpDatPos item) throws XMLStreamException {
    	if (item == null) {
    		return;
    	}
      
    openElement("GoodsItem");
    	writeElement("ItemNumber", item.getItemNumber());      	
    	writeElement("ArticleNumber", item.getArticleNumber());                        
    	writeElement("Description", item.getDescription());  
    	writeElement("UCROtherSystem", item.getUCROtherSystem()); 
    	writeElement("Annotation", item.getAnnotation()); 
    	writeElement("Annotation2", item.getAnnotation2()); 
      	writeElement("ShipmentNumber", item.getShipmentNumber());       	
      	writeElement("OriginCountry", item.getOriginCountry());      	
      	writeCodeElement("OriginFederalState", item.getOriginFederalState(), "KCX0023"); 
    	writeElement("DestinationCountry", item.getDestinationCountry());
    	writeElement("NetMass", item.getNetMass());     	
    	writeElement("NetMassConfirmation", item.getNetMassConfirmation());     
    	writeElement("GrossMass", item.getGrossMass());  
    	writeElement("GrossMassConfirmation", item.getGrossMassConfirmation());   
     	writeElement("DangerousGoodsNumber", item.getDangerousGoodsNumber()); 
    	writeElement("PaymentType", item.getPaymentType());     	
    	writeCodeElement("KindOfArticle", item.getKindOfArticle(), "KCX0040");    	    	
    	writeCodeElement("TypeOfArticle", item.getTypeOfArticle(), "KCX0039");    	
    	writeElement("CommodityBoard", item.getCommodityBoard());
    	writeElement("ThirdQuantity", item.getThirdQuantity());     	
    	writeParty("SupervisingCustomsOffice", item.getSupervisingCustomsOffice());     	
    	writeElement("AdditionalCommodityBoardCode", item.getAdditionalCommodityBoardCode());
    	writeElement("DescriptionLanguage", item.getDescriptionLanguage());

    	writeCommodityCode(item.getCommodityCode());
    	
    	writeExportRefundItem(item.getExportRefundItem());
    	writeApprovedTreatment(item.getApprovedTreatment());  
    	if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
            this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130820
    		if (message.getBusiness() != null && !Utils.isStringEmpty(message.getBusiness().getInvoicePrice())) {
    			if (item.getStatistic() == null) {
    				Statistic stat = new Statistic();
    				stat.setStatisticalValue(message.getBusiness().getInvoicePrice());
    				item.setStatistic(stat);
    			} else {
    				if (Utils.isStringEmpty(item.getStatistic().getStatisticalValue())) {    			
    					item.getStatistic().setStatisticalValue(message.getBusiness().getInvoicePrice());
    				}
    			}
            }
    	}
    	writeStatistic(item.getStatistic(), "ExpDat");    	
    	writeGoodsIdentification(item.getGoodsIdentification());    	
    	writeSensitiveGoodsList(item.getSensitiveGoodsList());
    	writeSpecialMentionList(item.getSpecialMentionList());    	
    	writeNonCustomsLaw(item.getNonCustomsLaw());
    	writeElement("PermitObligation", item.getPermitObligation());  	
    	writePermitList(item.getPermitList());
    	writeParty("Consignee", item.getConsignee());
    	writeParty("Consignor", item.getConsignor());
    	writeParty("WarehouseKeeper", item.getWarehouseKeeper());    	
    	if (item.getPackagesList() != null) {         		
    		for (Packages packages : item.getPackagesList()) {        		                       
                writePackages(packages, "");        			     		
    		}    		
      	}
    	writeContainerNumberList(item.getContainer());     	
    	if (item.getDocumentList() != null) {         		
    		for (DocumentV20 document : item.getDocumentList()) {        		                             
                writeDocument(document);        			     		
    		}    		
      	}   	
    	if (item.getPreviousDocumentList() != null) {
    		//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) { 
    		for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {
                //writePreviousDocument(prev, message.getFromFormat()); 
    			writePreviousDocument21(prev); 
    		}     			
    	}
    	if (item.getAddInfoStatementList() != null) {
    		for (Text text : item.getAddInfoStatementList()) { 
    	        writeAddInfoStatement(text);
    		}
    	}
    	writeCompletion("BondedWarehouseCompletion", item.getBondedWarehouseCompletion());
    	writeCompletion("InwardProcessingCompletion", item.getInwardProcessingCompletion());
    	writeWareHouse(item.getWareHouse());    	    	
    	writeRefinement(item.getRefinement());                         
    	writeDetailList(item.getDetailList());                         
    	writeElement("RefundType", item.getRefundType());              
    	writeElement("RefundQuantity", item.getRefundQuantity());     
    	writeNotificationCodeList(item.getNotificationCodeList());  
    	writeBusiness(item.getBusiness());
    	writeIncoTerms(item.getIncoTerms());    	
    	writeElement("AddressCombination", item.getAddressCombination());
    	//EI20120912: writeParty("FinalRecipient", item.getConsignee());    
    	writeParty("FinalRecipient", item.getFinalRecipient());   //EI20120912   
    closeElement();
    }   


}
