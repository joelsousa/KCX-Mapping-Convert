package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module		: BodyExportReleaseKids<br>
 * Created		: 18.07.2012<br>
 * Description	: KIDS body of message "ExportRelease".
 *  			: V21: new Tags  
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class BodyReverseDeclarationKids extends KidsMessageV21 {

	private MsgExpRel message;
	
    public BodyReverseDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpRel getMessage() {
		return message;
	}
	public void setMessage(MsgExpRel argument) {
		this.message = argument;
	}
	
    public void writeBody() {	
        try {        	
            openElement("soap:Body");
            openElement("ReverseDeclaration");               
            openElement("GoodsDeclaration"); 
                 writeCodeElement("AreaCode", message.getAreaCode(), "KCX0005");
                 writeCodeElement("TypeOfPermit", message.getTypeOfPermit(), "KCX0006");                     
                 writeCodeElement("KindOfAnswer", message.getKindOfAnswer(), "KCX0045");
                 writeElement("DeclarationKind", message.getKindOfAnswer());        
                 writeElement("UCRNumber", message.getUCRNumber()); 
                 writeDateTimeToString("CompletionTime", message.getCompletionTime());
                 writeDateTimeToString("DeclarationTime", message.getDeclarationTime());
                        writeElement("DeclarationNumberForwarder", message.getDeclarationNumberForwarder()); 
                        writeElement("DeclarationNumberCustoms", message.getDeclarationNumberCustoms());  
                    	writeElement("DispatchCountry", message.getDispatchCountry());    
                        writeElement("DestinationCountry", message.getDestinationCountry());                           
                        writeDateTimeToString("ReleaseTime", message.getReleaseTime());
                        writeDateTimeToString("AcceptanceTime", message.getAcceptanceTime());   
                        writeDateTimeToString("ReceiveTime", message.getReceiveTime());         
                        writeElement("RevisionCode", message.getRevisionCode());          
                        writeElement("CodeOfRelease", message.getCodeOfRelease());        
                        writeElement("ShortageInQuantity", message.getShortageInQuantity());
                        writeCodeElement("AlternativeDocument", message.getAlternativeDocument(), "KCX0015"); 
                        writeElement("SituationCode", message.getSituationCode());       
                        writeElement("PaymentType", message.getPaymentType());                      
                        writeCodeElement("TransportInContainer", message.getTransportInContainer(), "KCX0001");
                        writeElement("GrossMass", message.getGrossMass());                        
                        writeElement("UCROtherSystem", message.getUCROtherSystem()); 
                        writeElement("Annotation", message.getAnnotation());
                        writeElement("ShipmentNumber", message.getShipmentNumber());  
                        writeElement("ReferenceNumber", message.getReferenceNumber());
                        writeElement("OrderNumber", message.getOrderNumber());
                        writeElement("TotalNumberPackages", message.getTotalNumberPackages());
                        writeElement("TotalNumberPositions", message.getTotalNumberPositions()); 
                        writeElement("ReceiverCustomerNumber", message.getReceiverCustomerNumber()); 
                		writeElement("DeclarantIsConsignor", message.getDeclarantIsConsignor());
                		writeElement("TypeOfRepresentation", message.getTypeOfRepresentation());                  		
                		writeContact("Contact", message.getContact());  
                		writeElement("Validity", message.getValidity());     
                		writePDFInformation(message.getPdfInformation()); 
                		writeWareHouse(message.getWarehouse());              
                		writeMeansOfTransport("Inland", message.getTransportMeansInland());                		                	   
                	    writeMeansOfTransport("Border", message.getTransportMeansBorder());                	    
                	    writePlaceOfLoading(message.getPlaceOfLoading());                	    
                	    writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());
                	    writeElement("CustomsOfficeForCompletion", message.getCustomsOfficeForCompletion());
                	    writeElement("IntendedOfficeOfExit", message.getIntendedOfficeOfExit());  
                	    writeElement("RealOfficeOfExit", message.getRealOfficeOfExit());                	    
                        writeBusiness(message.getBusiness());                        
                        writeTransportationRoute(message.getRoute());                        
                        writeSeals(message.getSeal(), "ExpRel"); 
                        writeExportRefundHeader(message.getExportRefundHeader());  
                        if (message.getLoadingTime() != null) {
                            writeLoadingTime(message.getLoadingTime());  
                        } else {
                        	writeDateTimeToString("BeginTime", message.getDateOfLoadingBegin());
                        	writeDateTimeToString("EndTime", message.getDateOfLoadingEnd());
                        }                                       
                        writeParty("Consignor", message.getConsignor());                            
                        writeParty("Declarant", message.getDeclarant());                       
                        writeParty("Agent", message.getAgent());
                       	writeParty("Subcontractor", message.getSubcontractor());
                       	writeParty("Consignee", message.getConsignee());
                       	writeParty("CustomsDocumentsReceiver", message.getDocumentsReceiver());    
                        writeParty("Represented", message.getRepresented());	             
                        writeParty("WarehouseKeeper", message.getWarehouseKeeper());       
                        writeIncoTerms(message.getIncoTerms()); 
                     //new for V21 begin    
                        if (message.getDocumentList() != null) {                     
                        	for (DocumentV20 doc : message.getDocumentList()) {
                        		writeDocument(doc);  
                        	}
                        }
                        writeElement("AuthorizationTrustedExporter", message.getAuthorizationTrustedExporter());  //V21 ??? ist nicht in xsd
                        writeElement("Procedure", message.getProcedure());
                        writeElement("RelevantDate", message.getRelevantDate());  //V21 ??? ist nicht in xsd
                        writeElement("FlagOfStatistic", message.getFlagOfStatistic());  //V21 ??? ist nicht in xsd
                        if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
                      		  this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130819
                        	message.setAddressCombination("0");
                        } 
                        writeElement("AddressCombination", message.getAddressCombination()); 
                        writeParty("FinalRecipient", message.getFinalRecipient());  
                        writeOutwardProcessing(message.getOutwardProcessing());        //V21 ??? ist nicht in xsd              
                        if (message.getReentryList() != null) {                       //V21 ??? ist nicht in xsd  	
                            for (Reentry reentry : message.getReentryList()) {
                            	writeReentry(reentry);                        		
                            }   
                        }
                        if (message.getMeansOfIdentificationList() != null) {     //V21 ??? ist nicht in xsd                    	
                            for (MeansOfIdentification text : message.getMeansOfIdentificationList()) {
                            	writeMeansOfIdentification(text);
                            }
                        }
                        if (message.getProductList() != null) {               //V21 ??? ist nicht in xsd          	
                            for (Product tarif : message.getProductList()) {
                            	writeProduct(tarif);
                            }
                        }
                        writeElement("DateOfExit", message.getDateOfExit()); 
                      //new for V21 end
                        if (message.getGoodsItemList() != null) {                        	
                        	for (MsgExpRelPos item : message.getGoodsItemList()) {
                        		writeGoodsItemList(item);
                        	}  
                        }                       
                        writeInvoice(message.getInvoice());                                    
                closeElement();  
                closeElement();  
                closeElement(); 
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
     
    private void writeGoodsItemList(MsgExpRelPos item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}
    	
    openElement("GoodsItem");
    	writeElement("ItemNumber", item.getItemNumber());
    	writeElement("OriginItemNumber", item.getOriginItemNumber());               //new for V21
    	writeElement("ArticleNumber", item.getArticleNumber());
    	writeElement("Description", item.getDescription());
    	writeElement("UCROtherSystem", item.getUCROtherSystem());
    	writeElement("Annotation", item.getAnnotation());
    	writeElement("ShipmentNumber", item.getShipmentNumber());    
    	writeElement("OriginCountry", item.getOriginCountry());           
    	writeCodeElement("OriginFederalState", item.getOriginFederalState(), "KCX0023");    		
    	writeElement("NetMass", item.getNetMass());    		
    	writeElement("GrossMass", item.getGrossMass());    	
    	writeElement("DangerousGoodsNumber", item.getDangerousGoodsNumber());    	     	
    	writeElement("PaymentType", item.getPaymentType());          
    	writeElement("CommodityBoard", item.getCommodityBoard());           
    	writeElement("AdditionalCommodityBoardCode", item.getAdditionalCommodityBoardCode()); 
    	writeCommodityCode(item.getCommodityCode());
    	writeExportRefundItem(item.getExportRefundItem());
    	writeApprovedTreatment(item.getApprovedTreatment());    
    	writeStatistic(item.getStatistic(), "ExpRel");      
    	writeGoodsIdentification(item.getGoodsIdentification());
    	writeSpecialMentionList(item.getSpecialMentionList());               
    	writePermitList(item.getPermitList());                              
    	writeParty("Consignee", item.getConsignee());       	    
    	writePackagesList(item.getPackagesList(), "");     	
    	writeContainerNumberList(item.getContainer());    	
    	if (item.getDocumentList() != null) {        		
    		for (DocumentV20 doc : item.getDocumentList()) {        		  
    			writeDocument(doc); 
    		}     			    		
      	}    	
    	if (item.getPreviousDocumentList() != null) {        		
    		//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) {    
    		for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {                  
    			//writePreviousDocument(prev, message.getFromFormat());   //EI20120914
    			writePreviousDocument21(prev);    //20130827
    		} 	
    	}    		          
    	writeCompletion("BondedWarehouseCompletion", item.getBondedWarehouseCompletion()); 
    	writeCompletion("InwardProcessingCompletion", item.getInwardProcessingCompletion()); 
    	//new for V21 begin
    	writeBusiness(item.getBusiness());									//new for V21		
    	writeIncoTerms(item.getIncoTerms());								//new for V21
    	if (this.kidsHeader != null && this.kidsHeader.getReceiver() != null && 
      		  this.kidsHeader.getReceiver().equalsIgnoreCase("DE.TOLL.TST")) {  //EI20130819
    		item.setAddressCombination("0");
        } 
    	writeElement("AddressCombination", item.getAddressCombination());   //new for V21
    	writeParty("FinalRecipient", item.getConsignee());    				//new for V21	
    	writeElement("Watermark", item.getWatermark());  					//new for V21
    	//new for V21 end
    closeElement();
    }   
}
