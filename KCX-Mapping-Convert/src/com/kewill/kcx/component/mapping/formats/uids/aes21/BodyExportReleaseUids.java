package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: BodyExportReleaseUids<br>
 * Created		: 18.07.2012<br>
 * Description	: UIDS body of message "ExportRelease" == Kids ReverseDeclaration.
 * 				: V21 - new Tags
 *              : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 *              
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportReleaseUids extends UidsMessageV21 {
    private MsgExpRel  message = new MsgExpRel();
   
    public BodyExportReleaseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpRel getMessage() {
		return message;
	}

	public void setMessage(MsgExpRel message) {
		this.message = message;
	}
    
	public void writeBody() {
        try {        	
        	openElement("soap:Body");
            openElement("Submit");                              
                //EI20120917:setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	//EI20120917:
        		if (this.getCommonFieldsDTO() != null && 
        				!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {
        			setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
        		} else {
        			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        		}
        	
                openElement("Export");
                openElement("ExportRelease");                                            
                	writeContact(message.getContact()); 
                	writeElement("AddressCombination", message.getAddressCombination()); 
                    writeParty("Consignee", message.getConsignee());
                    writeParty("FinalUser", message.getFinalRecipient()); 
                	writeParty("Declarant", message.getDeclarant());                		                		        
                	writeParty("DeclarantRepresentative", message.getAgent());                		                		         
                	writeParty("Exporter", message.getConsignor());              		                               		      
                	//writeParty("Shipper",message.getForwarder());
                	writeParty("Subcontractor", message.getSubcontractor());                		
                	//SubcontractorRepresentative. AirportCode, AnswerIndicator, AuthorisationID               	                	
                    writeElement("ContainerIndicator", message.getTransportInContainer());                 
                    writeElement("FlagOfStatisitc", message.getFlagOfStatistic());     						  //V21: TODO-IWA ???
                    writeElement("AuthorisationReliableExporter", message.getAuthorizationTrustedExporter()); //V21: TODO-IWA ???
                	writeCustomsOffices(message.getCustomsOfficeExport(), 
                		                    message.getCustomsOfficeForCompletion(), 
                		                    message.getRealOfficeOfExit(), "");                		
                	writeFormattedDateTime("DateOfAcceptance", message.getAcceptanceTime(),
                				message.getAcceptanceTimeFormat(), EFormat.ST_DateTimeZ);	                          
                	writeFormattedDateTime("DateOfAdditionalDeclaration", message.getCompletionTime(),
                				message.getCompletionTimeFormat(), EFormat.ST_DateTimeZ);	                   		
                	writeFormattedDateTime("DateOfDeclaration", message.getDeclarationTime(),
                				message.getDeclarationTimeFormat(), EFormat.ST_DateTimeZ);	   
                	//writeElement("DateOfRelevance", message.getRelevantDate());     //V21: TODO-IWA ???                    	   
                	writeFormattedDateTime("DateOfRelevance", message.getRelevantDate(),
            				message.getRelevantDateFormat(), EFormat.ST_DateTimeZ);  //EI20131202
                	writeFormattedDateTime("DateOfExit", message.getDateOfExit(),
                				message.getDateOfExitFormat(), EFormat.ST_DateTimeZ);	                   		
                    if (message.getLoadingTime() != null) {                        
                    	writeFormattedDateTime("DateOfLoadingBegin", message.getLoadingTime().getBeginTime(),
                    				message.getLoadingTime().getLoadingBeginFormat(), EFormat.ST_DateTimeZ);	   
                    	writeFormattedDateTime("DateOfLoadingEnd", message.getLoadingTime().getEndTime(),
                    				message.getLoadingTime().getLoadingEndFormat(), EFormat.ST_DateTimeZ);	                           	
                     }                        
                	writeFormattedDateTime("DateOfReceipt", message.getReceiveTime(),
                				message.getReceiveTimeFormat(), EFormat.ST_DateTimeZ);	   
                    writeFormattedDateTime("DateOfRelease", message.getReleaseTime(),
                				message.getReleaseTimeFormat(), EFormat.ST_DateTimeZ);	                           
                    writeElement("CountryOfDispatch", message.getDispatchCountry());
                    writeElement("DestinationCountry", message.getDestinationCountry());                       
                    writeElement("ExternalRegistrationNumber", message.getUCROtherSystem());
                    writeElement("DocumentReferenceNumber", message.getUCRNumber());
                    //ExportRefund, MRN                           
                    writeElement("GrossMass", message.getGrossMass());                    	
                    writeIncoTerms(message.getIncoTerms());                    	
                    writeElement("ItemsQuantity", message.getTotalNumberPositions());
                    writeElement("LocalReferenceNumber", message.getOrderNumber());                    	
                    writeMeansOfTransportInland(message.getTransportMeansInland());                       	
                    writeMeansOfTransport(message.getTransportMeansBorder(), "Border");                    
                    writeElement("PackagesQuantity", message.getTotalNumberPackages());                   	
                	writeElement("ParticipantsCombination", message.getDeclarantIsConsignor());                 	
                	writePassiveFinishing(message.getOutwardProcessing());  				//new for V21
                	writePdfInformation(message.getPdfInformation());                  		
                	writePlaceofLoading(message.getPlaceOfLoading());                 	
                	writePrevious(message.getProcedure());									//new for V21                	
                	writeElement("ReferenceNumber", message.getReferenceNumber());
                	//RegistrationNumber
                	writeElement("Remark", message.getAnnotation());                		
                	//RepresentationFlag
                	writeSeals(message.getSeal());                	
                	writeElement("ShortageIndicator", message.getShortageInQuantity());
                	writeElement("AlternateProofIndicator", message.getAlternativeDocument());
                	writeShipmentPeriod(message.getLoadingTime());                		
                	writeTransaction(message.getBusiness());                		
                	writeTypeOfDeclaration(message.getAreaCode(), message.getTypeOfPermit());
                	writeElement("TypeOfRelease", message.getKindOfAnswer());
                	writeHeaderExtensions(message.getSeal(), "ExpRel");                 		
                	writeElement("SpecCircumstance", message.getSituationCode());             
                	writeElement("TransportPaymentMethod", message.getPaymentType());                         		
                	writeElement("CommercialReferenceNumber", message.getShipmentNumber());             
                	writeItinerary(message.getRoute());                                      
                	writeExportRestitutionHeader(message.getExportRefundHeader());
                	writeElement("Watermark", message.getWatermark());
                	
                        if (message.getGoodsItemList() != null) {                        	
                        	for (MsgExpRelPos item : message.getGoodsItemList()) {
                        		writeGoodsItemList(item);
                        	}  
                        }
                        
                 closeElement(); 
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
		writeElement("OriginalItemNumber", item.getOriginItemNumber());      //new for V21
	   	writeElement("ArticleNumber", item.getArticleNumber());	
	   	writeContainer(item.getContainer());	   		   	       
	   	writeElement("AddressCombination", item.getAddressCombination());      //new for V21	   
	   	writeParty("Consignee", item.getConsignee());	
	   	writeParty("FinalUser", item.getFinalRecipient());
	   	writeCommodityCode(item.getCommodityCode());
	   	//destinationCountry, 		   	
	   	writeElement("ExternalRegistrationNumber", item.getUCROtherSystem());  
	   	//MRN
    	writeElement("GoodsDescription", item.getDescription());
    	writeElement("GrossMass", item.getGrossMass());  
    	writeIncoTerms(item.getIncoTerms());       //TODO-IWA: so zwischen Net und Grosmass ???
    	writeElement("NetMass", item.getNetMass());    	
    	//OriginCountry
    	writeElement("OriginRegion", item.getOriginFederalState());    	    	
    	if (item.getPackagesList() != null) {    		
    		for (Packages pack : item.getPackagesList()) {       			         	
            	writePackaging(pack, ""); 
    		}                		
    	}    	    			   			    
        writeProcedure(item.getApprovedTreatment());    
        
    	if (item.getPreviousDocumentList() != null) {    		
    		//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) {  
    		for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {  	
    			//writeItemPreviousDocument(prev, message.getFromFormat());    //EI20120914
    			writeItemPreviousDocument21(prev);        					   //EI20130827
    		}                		
    	}    	
    	if (item.getDocumentList() != null) {    		
    		for (DocumentV20 doc : item.getDocumentList()) {       			          	
            	writeProducedDocument(doc);   
    		}                		
    	}    
		writeElement("Remark", item.getAnnotation()); 		
		if (item.getStatistic() != null) {
			writeElement("StatisticalValue", item.getStatistic().getStatisticalValue());
			writeElement("StatisticalValueConfirmation", item.getStatistic().getStatisticalValueConfirmation());
			writeElement("StatisticalQuantity", item.getStatistic().getAdditionalUnit());
			writeElement("StatisticalQuantityConfirmation", item.getStatistic().getAdditionalUnitConfirmation());	
		}		
		writeElement("TransportPaymentMethod", item.getPaymentType());  
		writeTransaction(item.getBusiness());								 //new for V21
		writeElement("CommercialReferenceNumber", item.getShipmentNumber()); 
		writeElement("UNDGCode", item.getDangerousGoodsNumber()); 
		writeElement("Watermark", item.getWatermark()); 					//new for V21
		writeExportRestitutionItem(item.getExportRefundItem(), item.getApprovedTreatment());  //EI20090310
		writeWriteOffATLAS(item.getBondedWarehouseCompletion(), item.getInwardProcessingCompletion());   
		
		closeElement();
		
	} //ende-Item	
	
}    	
