/*
 * Function    : BodyExportDeclarationKidsUK.java
 * Titel       :
 * Date        : 08.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenamesin Export
 * 			   : relates to kiff-export-reply.xls
 *             : Version 6.0
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 04.2009
 * Label       : EI200904...
 * Description : writeCodeElement(...) for OriginFederalState
 *             : checked KCX-Codes
 *             : remove writeHeadDocument(...)
 * 
 * Author      : EI
 * Date        : 25.05.2009
 * Description : replaced MsgExpDat with MsgExpDat
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk     
 *             : TotalNumberPositions added     
 *             
 * Author      : EI
 * Date        : 
 * Label       : EI20100208
 * Description : new Members for UK added
 *
 * Author      : ME
 * Date        : 22.04.2010
 * Label       : ME201004XSDVAL
 * Description : Validated against xsd KIDS scheme and modified to match the scheme
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class BodyExportDeclarationKids extends KidsMessage {

	private MsgExpDat message;	
    private List <MsgExpDatPos> goodsItemList = null;

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
                //openElement(this.kidsMessageName);
                    openElement("GoodsDeclaration");                	                    	   
                    	writeCodeElement("AreaCode", message.getAreaCode(), "KCX0005");
                    	writeCodeElement("TypeOfPermit", message.getTypeOfPermit(), "KCX0006");
                    	//ME201004XSDVAL laut xsd schema gehört das dazu
                    	writeCodeElement("KindOfDeclaration", message.getKindOfDeclaration(), "KCX0033");
                    	//ME201004XSDVAL laut xsd schema gehört das dazu
                    	writeCodeElement("TypeOfPermitCH", message.getTypeOfPermitCH(), "KCX0042");
                    	//ME201004XSDVAL laut xsd schema gehört das dazu
                    	writeCodeElement("NCTSType", message.getNCTSType(), "KCX0043");
                    	writeElement("CorrectionReason", message.getCorrectionReason());      //EI20120123: renamed
                    	writeElement("UCRNumber", message.getUCRNumber());   
                    	writeElement("StatusCode", message.getStatusCode());                  //EI20110502 xsd
                    	//ME201004XSDVAL New for NL v11
                    	writeCodeElement("FinalCode", message.getFinalCode(), "KCX0001");
                    	//ME201004XSDVAL New for NL v11
                    	writeDateTimeToString("DeclarationTime", message.getDeclarationTime());
                    	writeDateToString("DateOfDeparture", message.getDateOfDeparture());
                    	writeDateTimeToString("AcceptanceTime", message.getAcceptanceTime());
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
                        // Christine Kron 21.07.2010 added because mandatory in NL application
                        if (this.kidsHeader != null &&     //EI20120105: ist wohl nur fuer NL, andere brauchen's nicht kriegen aber "0"      
                        		this.kidsHeader.getCountryCode() != null && this.kidsHeader.getCountryCode().equals("NL")) {
                        	//writeElement("DeclarationNumber", message.getDeclarationNumber() + ""); <== for DeclarationNumber as integer
                        	writeElement("DeclarationNumber", message.getDeclarationNumber());
                        }
                        writeElement("OrderNumber", message.getOrderNumber());
                        writeElement("TotalNumberPositions", message.getTotalNumberPositions());   //EI20090610  xsd
                        writeElement("TotalNumberOfPackages", message.getTotalNumberOfPackages()); //EI20100208 UK 
                        writeElement("AuthorizationNumber", message.getAuthorizationNumber());
                        writeElement("PreviousProcedure", message.getPreviousProcedure());
                        //ME201004XSDVAL laut xsd schema gehört das dazu
                        writeElement("ReceiverCustomerNumber", message.getReceiverCustomerNumber());
                        writeElement("DeclarantIsConsignor", message.getDeclarantIsConsignor());
                		writeElement("TypeOfRepresentation", message.getTypeOfRepresentation());   //EI20100208 UK
                		//ME201004XSDVAL laut xsd schema gehört das dazu
                		writeCodeElement("CorrectionCode", message.getCorrectionCode(), "KCX0041");
                		//ME201004XSDVAL laut xsd schema gehört das dazu
                		writeElement("BunchNumber", message.getBunchNumber());
                		//ME201004XSDVAL laut xsd schema gehört das dazu
                		writeCodeElement("Language", message.getLanguage(), "KCX0037");
                		writeElement("TransferToTransitSystem",  message.getTransferToTransitSystem()); //EI100208 UK
                		//EI20090608:writeElement("Clerk", message.getClerk());
                  		writeContact("Contact", message.getContact()); 
                  		//ME201004XSDVAL New for NL v11
                  		writeElement("Validity", message.getValidity());
                  		//ME201004XSDVAL New for NL v11
                  		writeWareHouse(message.getWareHouse());
                  		//ME201004XSDVAL New for NL v11
                  		writeMeansOfTransport("MeansOfTransport", message.getMeansOfTransport());
                  		writeMeansOfTransport("Inland", message.getTransportMeansInland());
                		writeMeansOfTransport("Departure", message.getTransportMeansDeparture());    
                	    writeMeansOfTransport("Border", message.getTransportMeansBorder());
                        writePlaceOfLoading(message.getPlaceOfLoading());
                    	writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());
                    	writeElement("CustomsOfficeForCompletion", message  .getCustomsOfficeForCompletion());
                    	writeElement("IntendedOfficeOfExit", message.getIntendedOfficeOfExit());
                    	//EI20120516: writeElement("SupervisingCustomsOffice", message.getSupervisingCustomsOffice());     //EI UK 
                    	//EI20120620: writeAddress("SupervisingCustomsOffice", message.getSupervisingCustomsOffice());  
                    	writeParty("SupervisingCustomsOffice", message.getSupervisingCustomsOffice());  //EI20120620
            			writeElement("CorrectionReason", message.getCorrectionReason());                     //EI20100208 UK 
                        writeElement("PlaceOfDeclaration", message.getPlaceOfDeclaration());
                        writeBusiness(message.getBusiness());
                        writeTransportationRoute(message.getTransportationRoute());                       
                        writeSeals(message.getSeal(), "ExpDatBE");   
                        writeExportRefundHeader(message.getExportRefundHeader());
                        writeLoadingTime(message.getLoadingTime());
                        //ME201004XSDVAL laut xsd schema gehört das dazu
                        writeForwarder(message.getForwarder());
                        //ME201004XSDVAL New for NL v11
                        writeTIN("MessageReceiver", message.getMessageReceiverTIN());
                        writeParty("Consignor", message.getConsignor()); 
                        writeParty("ConsignorSecurity", message.getConsignorSecurity()); 
                        writeParty("Declarant", message.getDeclarant());    
                        writeElement("DeclarantNumber", message.getDeclarantNumber());               //EI20110502 xsd
                        writeParty("Agent", message.getAgent());                    
                       	writeParty("Subcontractor", message.getSubcontractor());     
                       	writeParty("Consignee", message.getConsignee());  
                       	writeParty("ConsigneeSecurity", message.getConsigneeSecurity());             //EI20110502 xsd
                       	writeParty("CustomsDocumentsReceiver", message.getCustomsDocumentsReceiver());
                       	//ME201004XSDVAL New for NL v11
                       	writeParty("Represented", message.getRepresented());                       
                       	writeParty("WarehouseKeeper", message.getWarehousekeeper());
                       	writeParty("Beneficiary", message.getBeneficiary());
                       	writeParty("InitialSender", message.getInitialSender());  
                       	writeElement("DescriptionLanguage", message.getDescriptionLanguage());
                       	writeElement("AccountNumber", message.getAccountNumber());
                       	writeElement("RepertoriumNumber", message.getRepertoriumNumber());                       	
                       	writeElement("ControlResultCode", message.getControlResultCode());
                        writeIncoTerms(message.getIncoTerms());
                                            
                    	if (message.getDocumentList() != null) {                                  //EI20100208 UK
                    		for (int i = 0, listSize = message.getDocumentList().size(); i < listSize; i++) {   
                     		    Document tmpDoc = message.getDocumentList().get(i);                            	
                                writeDocument(tmpDoc);        			     		
                    		}    		
                      	}
                    	//ME201004XSDVAL laut xsd schema gehört das dazu
                    	//writeContainerList(message.getContainerList());// <== EI: expected List<String>
                    	writeContainerNumberList(message.getContainer());  //EI20110628
                    	//ME201004XSDVAL laut xsd schema gehört das dazu
                    	writePreviousDocumentList(message.getPreviousDocumentList());
                    	if (message.getAddInfoStatementList() != null) {                             //EI20100208 UK  
                    		for (int i = 0, listSize = message.getAddInfoStatementList().size(); i < listSize; i++) { 
                    	        writeAddInfoStatement(message.getAddInfoStatementList().get(i));
                    		}
                    	}
                    	writeElement("LoadingLists", message.getLoadingLists()); 
                    	writeElement("SecurityIndicator", message.getSecurityIndicator());          //EI20110502 xsd   
                    	writeElement("InjunctionType", message.getInjunctionType());                //EI20110502 xsd 
                    	writeElement("CompanyNumberTaxPayer", message.getCompanyNumberTaxPayer());  //EI20110502 xsd 
                    	writeSpecialMentionList(message.getSpecialMentionList());
                    	
                        goodsItemList =  message.getGoodsItemList();                                               
                        if (goodsItemList != null) {
                        	int posListSize = goodsItemList.size(); 
                        	for (int i = 0; i < posListSize; i++) {
                        		writeGoodsItemList(goodsItemList.get(i));                        		
                        	}   
                        }
                        writeInvoice(message.getInvoice());                                          //EI20100208 UK 
                        writeCAP(message.getCap());                                                  //EI20100208 UK
                        writeElement("TemporaryReason", message.getTemporaryReason());               //EI20110502 CH
                        
                    closeElement();	    
                    closeElement();	 
                    closeElement();	
       
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
   
	private void writeGoodsItemList(MsgExpDatPos position) throws XMLStreamException {
    	if (position == null) {
    		return;
    	}
      
    openElement("GoodsItem");
    	writeElement("ItemNumber", position.getItemNumber());      	
    	writeElement("ArticleNumber", position.getArticleNumber());                        
    	writeElement("Description", position.getDescription());  
    	writeElement("UCROtherSystem", position.getUCROtherSystem()); 
    	writeElement("Annotation", position.getAnnotation()); 
    	writeElement("Annotation2", position.getAnnotation2()); 
      	writeElement("ShipmentNumber", position.getShipmentNumber()); 
      	//ME201004XSDVAL laut xsd schema gehört das dazu
      	writeElement("OriginCountry", position.getOriginCountry());
      	
      	writeCodeElement("OriginFederalState", position.getOriginFederalState(), "KCX0023"); 
    	writeElement("DestinationCountry", position.getDestinationCountry());
    	writeElement("NetMass", position.getNetMass());     	
    	writeElement("NetMassConfirmation", position.getNetMassConfirmation());     
    	writeElement("GrossMass", position.getGrossMass());  
    	writeElement("GrossMassConfirmation", position.getGrossMassConfirmation());   
     	writeElement("DangerousGoodsNumber", position.getDangerousGoodsNumber()); 
    	writeElement("PaymentType", position.getPaymentType()); 
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	writeCodeElement("KindOfArticle", position.getKindOfArticle(), "KCX0040");
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	writeCodeElement("TypeOfArticle", position.getTypeOfArticle(), "KCX0038");
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	writeCodeElement("TypeOfDeclaration", position.getTypeOfArticle(), "KCX0039");
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	writeElement("CommodityBoard", position.getCommodityBoard());
    	writeElement("ThirdQuantity", position.getThirdQuantity()); //EI20100208 for UK
    	//EI20120516: writeElement("SupervisingCustomsOffice", position.getSupervisingCustomsOffice()); //EI20100208 UK
    	writeParty("SupervisingCustomsOffice", position.getSupervisingCustomsOffice()); //EI20120620
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	writeElement("AdditionalCommodityBoardCode", position.getAdditionalCommodityBoardCode());
    	writeElement("DescriptionLanguage", position.getDescriptionLanguage());    	
    	writeCommodityCode(position.getCommodityCode());
    	writeExportRefundItem(position.getExportRefundItem());
    	writeApprovedTreatment(position.getApprovedTreatment());
    	//EI20090515 neu: ist eigentlich besser so vorzugehen:
    	//neu: writeApprovedTreatment(position.getApprovedTreatment(), MsgExpDatPos.getExportRefundItem());
    	writeStatistic(position.getStatistic(), "ExpDat");
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	//writeGoodsIdentification("GoodsIdentification", position.getGoodsIdentification());
    	writeGoodsIdentification(position.getGoodsIdentification());
    	//ME201004XSDVAL New for NL v11
    	writeSensitiveGoodsList(position.getSensitiveGoodsList());
    	writeSpecialMentionList(position.getSpecialMentionList());
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	
    	writeNonCustomsLaw("NonCustomsLaw", position.getNonCustomsLaw());
    	writeElement("PermitObligation", position.getPermitObligation());  //EI20110502 xsd
    	//ME201004XSDVAL laut xsd schema gehört das dazu
    	writePermitList(position.getPermitList());
    	writeParty("Consignee", position.getConsignee());
    	writeParty("Consignor", position.getConsignor());
    	writeParty("WarehouseKeeper", position.getWarehouseKeeper());
    	writePackagesList(position.getPackagesList(), ""); 
    	writeContainerNumberList(position.getContainer()); 
    	
    	if (position.getDocumentList() != null) {         		
    		for (int i = 0, listSize = position.getDocumentList().size(); i < listSize; i++) {   
     		    Document tmpDoc = position.getDocumentList().get(i);                            	
                writeDocument(tmpDoc);        			     		
    		}    		
      	}
   	
    	if (position.getPreviousDocumentList() != null) {
    		for (int i = 0, listSize = position.getPreviousDocumentList().size(); i < listSize; i++) {  
     		    PreviousDocument tmpPrevDoc = position.getPreviousDocumentList().get(i);       
                writePreviousDocument(tmpPrevDoc); 
    		}     			
    	}
    	if (position.getAddInfoStatementList() != null) { //EI20100208 for UK  
    		for (int i = 0, listSize = position.getAddInfoStatementList().size(); i < listSize; i++) { 
    	        writeAddInfoStatement(position.getAddInfoStatementList().get(i));
    		}
    	}
    	writeCompletion("BondedWarehouseCompletion", position.getBondedWarehouseCompletion());
    	writeCompletion("InwardProcessingCompletion", position.getInwardProcessingCompletion());
    	writeWareHouse(position.getWareHouse());
    	    	
    	writeRefinement(position.getRefinement());                         //EI20110502
    	writeDetailList(position.getDetailList());                         //EI20110502
    	writeElement("RefundType", position.getRefundType());              //EI20110502 CH
    	writeElement("RefundQuantity", position.getRefundQuantity());      //EI20110502 CH
    	writeNotificationCodeList(position.getNotificationCodeList());     //EI20110502 CH
    	
    closeElement();
    } //item  

	

	

	

	
 
}