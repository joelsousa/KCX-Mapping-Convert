package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Export/aes.<br>
 * Created      : 17.07.2012<br>
 * Description	: Uids Body of ExportDeclaration
 * 				: V21 - new Tags   
 * 				: AES22- new Tag in GoodsItem: StatisticalValueSendFlag
 *              : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 *              
 * @author iwaniuk
 * @version 2.1.00
 */
 
public class BodyExportDeclarationUids extends UidsMessageV21 {
	
    private MsgExpDat  message = new MsgExpDat();
 
    public BodyExportDeclarationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpDat getMessage() {
		return message;
	}

	public void setMessage(MsgExpDat message) {
		this.message = message;
	}
    
	public void writeBody() {					
        try {    		
        	openElement("soap:Body");
            openElement("Submit");               
            	//EI20120917: setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	//EI20120917:
        		if (this.getCommonFieldsDTO() != null && 
        				!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {
        			setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
        		} else {
        			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        		}
                openElement("Export");
                openElement("ExportDeclaration");       
                	writeElement("AddressCombination", message.getAddressCombination());        //new for V20
                	//writePartyTIN("CommunicationPartnerTIN", message.getMessageReceiverTIN());  //new for V20: unklar 
                    writeParty("Consignee", message.getConsignee());   
                    writeParty("FinalUser", message.getFinalRecipient());         	     //new for V20
                    writeParty("ConsigneeSecurity", message.getConsigneeSecurity());     //new for V20                 		
                    writeContact(message.getContact()); 
                	writeParty("Declarant", message.getDeclarant()); 
                	writeElement("DeclarantNumber", message.getDeclarantNumber());        //new for V20
                	writeParty("DeclarantRepresentative", message.getAgent());        
                	writeParty("Exporter", message.getConsignor());  
                	writeParty("ExporterSecurity", message.getConsignorSecurity());       //new for V20
                	//writeParty("Issuer", message);                                      //new for V20
                	//writeParty("Recipient", message);                                   //new for V20
                	writeParty("Represented", message.getRepresented());  			      //new for V20
                	writeParty("WarehouseKeeper", message.getWarehousekeeper());
                	writeParty("Shipper", message.getForwarder());					       //new for V20
                	writeParty("Subcontractor", message.getSubcontractor());
                	//SubcontractorRepresentative
                	writeParty("Beneficiary", message.getBeneficiary());
                	writeParty("InitialSender", message.getInitialSender());
                	writeParty("CustomsDocumentsReceiver", message.getCustomsDocumentsReceiver());
                	writeParty("Carrier", message.getCarrier());                            //new for V20
                	//AirportCode, ActualAirportCode, AnswerIndicator, 
                	writeElement("AuthorisationID", message.getAuthorizationNumber());     
                	writeElement("AuthorisationReliableExporter", message.getAuthorizationTrustedExporter());  //new for V20                	
                    writeElement("ContainerIndicator", message.getTransportInContainer());
                    //Customs_dex
                    writeElement("FlagOfStatistic", message.getFlagOfStatistic());            //new for V20
                    writeCustomsOffices(message.getCustomsOfficeExport(), message.getCustomsOfficeForCompletion(), 
                    	                    message.getIntendedOfficeOfExit(), message.getRealOfficeOfExit(),
                    	                    message.getSupervisingCustomsOffice());           //new for V20:RealOfficeOfExit
                    //Date
                    writeElement("ExternalRegistrationNumber", message.getUCROtherSystem());  
                    writeElement("DocumentReferenceNumber", message.getUCRNumber());
                    writeElement("CorrectionReason", message.getCorrectionReason());           //new for V20
                    //DateOfAdditionalDeclaration, DateOfAmendmentExit, DateOfPreAdvice, DateOfDeclaration, 
                    //EI20131202: writeElement("DateOfRelevance", message.getRelevantDate());  //new for V20                    
                    writeFormattedDateTime("DateOfRelevance", message.getRelevantDate(),
            				message.getRelevantDateFormat(), EFormat.ST_Date); //EI20131202
                    //EI20131202: writeElement("DateOfExit", message.getDateOfExit());         //new for V20                     
                    writeFormattedDateTime("DateOfExit", message.getDateOfExit(),
            				message.getDateOfExitFormat(), EFormat.ST_Date); //EI20131202
                    writeElement("DestinationCountry", message.getDestinationCountry());    
                    writeElement("CountryOfDispatch", message.getDispatchCountry());           //new for V20
                    //DocumentInformation, ExportAssuranceCertificate, ExportRefund, FunctionCode, 
                    writeElement("NetMass", message.getNetMass());
                    writeElement("GrossMass", message.getGrossMass());
                    //Guarantee, PackagesQuantity
                    writeIncoTerms(message.getIncoTerms());
                    writeInvoice(message.getInvoice());  // V20
                    writeElement("IssuePlace", message.getPlaceOfDeclaration());
                    writeElement("ItemsQuantity", message.getTotalNumberPositions());                     	
                    writeElement("LoadingList", message.getLoadingLists());
                    writeElement("LocalReferenceNumber", message.getOrderNumber());
                    writeMeansOfTransportInland(message.getTransportMeansInland());
                    writeMeansOfTransport(message.getTransportMeansDeparture(), "Departure");
                    writeMeansOfTransport(message.getTransportMeansBorder(), "Border");
                    // OriginCountry,                    
                	writeElement("ParticipantsCombination", message.getDeclarantIsConsignor());
                	writePassiveFinishing(message.getOutwardProcessing());                     //new for V20
                	//PaymentDateLimit
                	writePlaceofLoading(message.getPlaceOfLoading());                		
                	writePrevious(message.getPreviousProcedure());   
                	//ReferenceOfAVUV, ReferenceOfEMCS, ReferenceOfZL
                	writeElement("ReferenceNumber", message.getReferenceNumber());
                	//RemittanceToOrderIndicator
                	writeElement("Remark", message.getAnnotation());
                	//RepresentationFlag, Route
                	writeSeals(message.getSeal());
                	writeShipmentPeriod(message.getLoadingTime());
                	//ShipReference, SimplifiedProcedureAuthorization, SimplifiedProcedureIndicator, SpecialRequests,
                	writeTransaction(message.getBusiness()); 
                	writeTypeOfDeclaration(message.getAreaCode(), message.getTypeOfPermit(), message.getProcedure());
                	writeHeaderExtensions(message.getSeal(), "ExpDat");      
                	//EdecHeader
                	writeElement("SpecCircumstance", message.getSituationCode());
                	writeElement("TransportPaymentMethod", message.getPaymentType());                	
                	writeElement("CommercialReferenceNumber", message.getShipmentNumber());  
                	writeItinerary(message.getTransportationRoute());
                	writeExportRestitutionHeader(message.getExportRefundHeader());
                	writeElement("TotalNumberOfPackages", message.getTotalNumberOfPackages()); //new for V20
                	writeElement("Reason", message.getCorrectionReason());                     //new for V20, EI20120912 
                	if (message.getAddInfoStatementList() != null) {  
                		for (Text text : message.getAddInfoStatementList()) { 
                			writeAddInfoStatement(text);                	                	
                		}
                	}
                	writeElement("DescriptionLanguage", message.getDescriptionLanguage());
                    writeElement("AccountNumber", message.getAccountNumber());
                    writeElement("RepertoriumNumber", message.getRepertoriumNumber());
                    writeElement("ControlResultCode", message.getControlResultCode());
                    //writeElement("DeclarationTime", message.getDeclarationTime());             //new for V20                   
                    writeFormattedDateTime("DeclarationTime", message.getDeclarationTime(),
            				message.getDeclarationTimeFormat(), EFormat.ST_DateTimeZ); //EI20131202
                    writeElement("SecurityIndicator", message.getSecurityIndicator());         //new for V20  
                	writeElement("InjunctionType", message.getInjunctionType());               //new for V20
                	writeElement("CompanyNumberTaxPayer", message.getCompanyNumberTaxPayer()); //new for V20
                	
                	if (message.getPreviousDocumentList() != null) {
                		//for (PreviousDocumentV20 tmpPrev : message.getPreviousDocumentList()) {
                		for (PreviousDocumentV21 tmpPrev : message.getPreviousDocumentList()) {
                			//writePreviousDocument(tmpPrev, message.getFromFormat());
                			writePreviousDocument21(tmpPrev);   //EI20130827
                		}
                	}
                	writeContainer(message.getContainer()); 
                	//InvoiceCurrencyType

                    if (message.getGoodsItemList() != null) {
                        for (MsgExpDatPos item : message.getGoodsItemList()) {
                        	writeGoodsItemList(item);
                        }  
                     }                       
                    writeCAP(message.getCap()); 
                    
                closeElement(); 
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
	   	writeContainer(item.getContainer());	   
	   	writeElement("AddressCombination", item.getAddressCombination());	 //new for V20 
	   	writeParty("Consignor", item.getConsignor());				
	   	writeParty("Consignee", item.getConsignee());
		writeParty("FinalUser", item.getFinalRecipient());	
	   	writeParty("WarehouseKeeper", item.getWarehouseKeeper());
	   	writeCommodityCode(item.getCommodityCode()); 	   
	   	//MRN 	    
	   	writeElement("DestinationCountry", item.getDestinationCountry());
	   	writeElement("GoodsDescription", item.getDescription());	   		   	
    	writeElement("GrossMass", item.getGrossMass());  
    	writeElement("NetMass", item.getNetMass());
    	writeElement("OriginRegion", item.getOriginFederalState());    	
    	if (item.getPackagesList() != null) {    		
    		for (Packages tmpPack : item.getPackagesList()) {       			            	
            	writePackaging(tmpPack, "");            	
    		}                		
    	}    	            	
        writeProcedure(item.getApprovedTreatment());    	
    	if (item.getPreviousDocumentList() != null) {
    		//for (PreviousDocumentV20 tmpPrev : item.getPreviousDocumentList()) {   
    		for (PreviousDocumentV21 tmpPrev : item.getPreviousDocumentList()) {   
            	//writeItemPreviousDocument(tmpPrev, message.getFromFormat());  //EI20120913
            	writeItemPreviousDocument21(tmpPrev);   				        //EI20130827
    		}                		
    	}
    	if (item.getDocumentList() != null) {
    		for (DocumentV20 tmpDoc : item.getDocumentList()) {       			    			
            	writeProducedDocument(tmpDoc); 
    		}                		
    	}
    	if (item.getSensitiveGoodsList() != null)  {
    		for (SensitiveGoods sgut : item.getSensitiveGoodsList()) {
    			writeSpecialGoodsInformation(sgut);    
    		}
    	}
    	writeSpecialRemarks(item.getNonCustomsLaw()); 
    	//SpecialTaxes, SupplementaryUnit
		writeElement("Remark", item.getAnnotation());	
		//Restitutions, RestitutionIndicator
		if (item.getStatistic() != null) {
			writeElement("StatisticalValue", item.getStatistic().getStatisticalValue());
			writeElement("StatisticalValueConfirmation", item.getStatistic().getStatisticalValueConfirmation());
			writeElement("StatisticalValueSendFlag", item.getStatistic().getStatisticalValueSendFlag());  //EI20130808 AES22
			writeElement("StatisticalQuantity", item.getStatistic().getAdditionalUnit());
			writeElement("StatisticalQuantityConfirmation", item.getStatistic().getAdditionalUnitConfirmation());
			writeElement("StatisticalBaseValue", item.getStatistic().getValue());
			writeElement("StatisticalBaseCurrency", item.getStatistic().getCurrency());
			writeElement("AdditionalUnitCode", item.getStatistic().getAdditionalUnitCode());
			
		}
		//EdecItem
		writeElement("TransportPaymentMethod", item.getPaymentType()); 
		writeElement("CommercialReferenceNumber", item.getShipmentNumber()); 
		writeElement("UNDGCode", item.getDangerousGoodsNumber()); 
		writeExportRestitutionItem(item.getExportRefundItem(), item.getApprovedTreatment()); 
		writeWriteOffATLAS(item.getBondedWarehouseCompletion(), item.getInwardProcessingCompletion());
		writeElement("ThirdQuantity", item.getThirdQuantity());                                //new for V20
		writeElement("DescriptionLanguage", item.getDescriptionLanguage());
		writeCustomsOffice(item.getSupervisingCustomsOffice());		
		writeWareHouse(item.getWareHouse());
		if (item.getAddInfoStatementList() != null) {  
    		for (Text text : item.getAddInfoStatementList()) { 
    	        writeAddInfoStatement(text);
    		}
    	}
		writeIncoTerms(item.getIncoTerms());
		writeTransaction(item.getBusiness());
		writeRefinement(item.getRefinement());
		writeDetailList(item.getDetailList());
		writeNotificationCodeList(item.getNotificationCodeList());
		
	closeElement();
	}

}    	
