/*
 * Function    : BodyReverseDeclarationUK.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / houdek
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff-export-reply.xls
 * Parameters  :

 * Changes
 * -----------
 * Author      : E.Iwaniuk
 * Date        : 12.03.2009
 * Label       : EI20090312
 * Description : checked V60
 *
 * Author      : EI
 * Date        : 27.04.2009
 * Label       :
 * Description : KCX-Code checked 
 * 
 * Author      : EI
 * Date        : 08.05.2009
 * Label       : EI20090508
 * Description : UcrOtherSystem was filled from wrong member
 * 
 * Author      : EI
 * Date        : 25.05.2009 
 * Label       : EI20090525
 * Description : PlaceOfLoading wieder aktiviert
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk  
 *    
 * Author      : E.Iwaniuk
 * Date        : 11.08.2010
 * Label       : EI20100811
 * Description : several new member (all from xsd)    
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Modul		: BodyExportReleaseKids<br>
 * Erstellt		: 18.12.2008<br>
 * Beschreibung	: KIDS body of message "ExportRelease".
 * 
 * @author houdek
 * @version 1.0.00
 */
public class BodyReverseDeclarationKids extends KidsMessage {

	private MsgExpRel msgExpRel;
	// CK081003
	//int listSize = 0;
	
    public BodyReverseDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpRel getMsgExpRel() {
		return msgExpRel;
	}
	public void setMessage(MsgExpRel argument) {
		this.msgExpRel = argument;
	}
	
	

    public void writeBody() {
    	
        try {        	
            openElement("soap:Body");
            	openElement("ReverseDeclaration");
                //openElement(this.kidsMessageName);
                    openElement("GoodsDeclaration"); 
                    	writeCodeElement("AreaCode", msgExpRel.getAreaCode(), "KCX0005");
                    	writeCodeElement("TypeOfPermit", msgExpRel.getTypeOfPermit(), "KCX0006");                     
                        writeCodeElement("KindOfAnswer", msgExpRel.getKindOfAnswer(), "KCX0045");
                        writeElement("DeclarationKind", msgExpRel.getKindOfAnswer());        //EI20100811
                        writeElement("UCRNumber", msgExpRel.getUCRNumber()); 
                        writeDateTimeToString("CompletionTime", msgExpRel.getCompletionTime());
                        writeDateTimeToString("DeclarationTime", msgExpRel.getDeclarationTime());
                        writeElement("DeclarationNumberForwarder", msgExpRel.getDeclarationNumberForwarder()); //EI20100811
                        writeElement("DeclarationNumberCustoms", msgExpRel.getDeclarationNumberCustoms());  //EI20100811
                    	writeElement("DispatchCountry", msgExpRel.getDispatchCountry());    //EI20090312 V60
                        writeElement("DestinationCountry", msgExpRel.getDestinationCountry());    
                        writeDateTimeToString("ExitTime", msgExpRel.getExitTime());  		//EI20100607
                        writeDateTimeToString("ReleaseTime", msgExpRel.getReleaseTime());
                        writeDateTimeToString("AcceptanceTime", msgExpRel.getAcceptanceTime());   
                        writeDateTimeToString("ReceiveTime", msgExpRel.getReceiveTime());         
                        writeElement("RevisionCode", msgExpRel.getRevisionCode());          //EI20100811
                        writeElement("CodeOfRelease", msgExpRel.getCodeOfRelease());        //EI20100811 
                        writeElement("ShortageInQuantity", msgExpRel.getShortageInQuantity());
                        writeCodeElement("AlternativeDocument", msgExpRel.getAlternativeDocument(), "KCX0015"); 
                        writeElement("SituationCode", msgExpRel.getSituationCode());       //EI20090312 V60
                        writeElement("PaymentType", msgExpRel.getPaymentType());   //EI20090312 V60                    
                        writeCodeElement("TransportInContainer", msgExpRel.getTransportInContainer(), "KCX0001");
                        writeElement("GrossMass", msgExpRel.getGrossMass());
                        //writeElement("UCROtherSystem", msgExpRel.getUCRNumber());
                        writeElement("UCROtherSystem", msgExpRel.getUCROtherSystem()); //EI20090508
                        writeElement("Annotation", msgExpRel.getAnnotation());
                        writeElement("ShipmentNumber", msgExpRel.getShipmentNumber());  //EI20090312 V60
                        writeElement("ReferenceNumber", msgExpRel.getReferenceNumber());
                        writeElement("OrderNumber", msgExpRel.getOrderNumber());
                        writeElement("TotalNumberPackages", msgExpRel.getTotalNumberPackages());
                        writeElement("TotalNumberPositions", msgExpRel.getTotalNumberPositions()); 
                        writeElement("ReceiverCustomerNumber", msgExpRel.getReceiverCustomerNumber()); //EI2010811
                		writeElement("DeclarantIsConsignor", msgExpRel.getDeclarantIsConsignor());
                		writeElement("TypeOfRepresentation", msgExpRel.getTypeOfRepresentation());  //EI20100811
                		//EI20090608: writeElement("Clerk", msgExpRel.getClerk());
                		writeContact("Contact", msgExpRel.getContact()); //EI20090608 
                		writeElement("Validity", msgExpRel.getValidity());     //EI20100811
                		writePDFInformation(msgExpRel.getPdfInformation()); //05.12.08
                		writeWareHouse(msgExpRel.getWarehouse());              //EI20100811
                		writeMeansOfTransport("Inland", msgExpRel.getTransportMeansInland());
                		/* in dieser Nachricht nicht vorgesehen: 
                	    writeMeansOfTransport("Departure", msgExpRel.getTransportMeansDeparture());
                	    */
                	    writeMeansOfTransport("Border", msgExpRel.getTransportMeansBorder());
                	    
                	    writePlaceOfLoading(msgExpRel.getPlaceOfLoading()); //EI20090525
                	    
                	    writeElement("CustomsOfficeExport", msgExpRel.getCustomsOfficeExport());
                	    writeElement("CustomsOfficeForCompletion", msgExpRel.getCustomsOfficeForCompletion());
                	    writeElement("IntendedOfficeOfExit", msgExpRel.getIntendedOfficeOfExit());   //EI20100811
                	    writeElement("RealOfficeOfExit", msgExpRel.getRealOfficeOfExit());
                	    
                        writeBusiness(msgExpRel.getBusiness());                        
                        writeTransportationRoute(msgExpRel.getRoute());                        
                        writeSeals(msgExpRel.getSeal(), "ExpRel"); 
                        writeExportRefundHeader(msgExpRel.getExportRefundHeader());  //EI20090312 V60
                        if (msgExpRel.getLoadingTime() != null) {
                            writeLoadingTime(msgExpRel.getLoadingTime());  
                        } else {
                        	writeDateTimeToString("BeginTime", msgExpRel.getDateOfLoadingBegin());
                        	writeDateTimeToString("EndTime", msgExpRel.getDateOfLoadingEnd());
                        }
                        //EI200891022: writeForwarder(msgExpRel.getShipper() );                        
                        writeParty("Consignor", msgExpRel.getConsignor());                            
                        writeParty("Declarant", msgExpRel.getDeclarant());                       
                        writeParty("Agent", msgExpRel.getAgent());
                       	writeParty("Subcontractor", msgExpRel.getSubcontractor());
                       	writeParty("Consignee", msgExpRel.getConsignee());
                       	writeParty("DocumentsReceiver", msgExpRel.getDocumentsReceiver());   //EI20100811  
                        writeParty("Represented", msgExpRel.getRepresented());	             //EI20100811
                        writeParty("WarehouseKeeper", msgExpRel.getWarehouseKeeper());       //EI20100811
                        writeIncoTerms(msgExpRel.getIncoTerms()); 
                        
                        //EI20090312: unklar, wie es gehen soll
                        //writeHeadDocument(msgExpRel.getGoodsItemList());
                        //writeDocumentList(msgExpRel.getDocumentList());  //EI20090508
                        
                        if (msgExpRel.getGoodsItemList() != null) {                        	
                        	for (int i = 0; i < msgExpRel.getGoodsItemList().size(); i++) {
                        		writeGoodsItemList((MsgExpRelPos) msgExpRel.getGoodsItemList().get(i));
                        	}  
                        }                       
                        writeInvoice(msgExpRel.getInvoice());                //EI20100811
                        
                    closeElement();  // GoodsDeclaration
                closeElement(); // ExportRelease     
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} //ende writeBody
     
    private void writeGoodsItemList(MsgExpRelPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    openElement("GoodsItem");
    	writeElement("ItemNumber", argument.getItemNumber());
    	writeElement("ArticleNumber", argument.getArticleNumber());
    	writeElement("Description", argument.getDescription());
    	writeElement("UCROtherSystem", argument.getUCROtherSystem());
    	writeElement("Annotation", argument.getAnnotation());
    	writeElement("ShipmentNumber", argument.getShipmentNumber());     //EI20090312 V60
    	writeElement("OriginCountry", argument.getOriginCountry());             //EI20100811
    	writeCodeElement("OriginFederalState", argument.getOriginFederalState(), "KCX0023");    		
    	writeElement("NetMass", argument.getNetMass());    		
    	writeElement("GrossMass", argument.getGrossMass());    	
    	writeElement("DangerousGoodsNumber", argument.getDangerousGoodsNumber()); //EI20090312 V60    	     	
    	writeElement("PaymentType", argument.getPaymentType());            //EI20090312 V60
    	writeElement("CommodityBoard", argument.getCommodityBoard());           //EI20100811
    	writeElement("AdditionalCommodityBoardCode", argument.getAdditionalCommodityBoardCode()); //EI20100811
    	writeCommodityCode(argument.getCommodityCode());
    	writeExportRefundItem(argument.getExportRefundItem());
    	writeApprovedTreatment(argument.getApprovedTreatment());    
    	writeStatistic(argument.getStatistic(), "ExpRel");  
    	// writeElement("GoodsIdentification", argument.getGoodsIdentification());  //EI20100811
    	writeGoodsIdentification(argument.getGoodsIdentification());  //CK20100813
    	writeSpecialMentionList(argument.getSpecialMentionList());               //EI20100811
    	writePermitList(argument.getPermitList());                               //EI20100811
    	writeParty("Consignee", argument.getConsignee());       	    
    	writePackagesList(argument.getPackagesList(), "");     	
    	writeContainerNumberList(argument.getContainer());
    	
    	if (argument.getDocumentList() != null) {    
    		int listSize = argument.getDocumentList().size();
    		for (int i = 0; i < listSize; i++) {        		  
    			writeDocument((Document) argument.getDocumentList().get(i)); 
    		}     			    		
      	}
    	
    	if (argument.getPreviousDocumentList() != null) {    
    		int listSize = argument.getPreviousDocumentList().size();
    		for (int i = 0; i < listSize; i++) {        		               	
                writePreviousDocument((PreviousDocument) argument.getPreviousDocumentList().get(i)); 
    		} 	
    	}    		 
        //EI20090312:    	
    	writeCompletion("BondedWarehouseCompletion", argument.getBondedWarehouseCompletion()); 
    	writeCompletion("InwardProcessingCompletion", argument.getInwardProcessingCompletion()); 
    	      
    closeElement();
    }   
}
