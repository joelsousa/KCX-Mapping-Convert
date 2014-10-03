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
 * Description : replaced MsgExpDat with msgBEExpDat
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk     
 *             : TotalNumberPositions added      
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgBEExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgBEExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyExportDeclarationKidsBE extends KidsMessage {

	private MsgBEExpDat msgBEExpDat;	
    List <MsgBEExpDatPos>goodsItemList = null;

    public BodyExportDeclarationKidsBE(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgBEExpDat getMessage() {
		return msgBEExpDat;
	}
	public void setMessage(MsgBEExpDat argument) {
		this.msgBEExpDat = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            	openElement("ExportDeclaration");
                //openElement(this.kidsMessageName);
                    openElement("GoodsDeclaration");                	                    	   
                    	//writeCodeElement("AreaCode", msgBEExpDat.getAreaCode(), "KCX0005");
                    	//12writeCodeElement("TypeOfPermit", msgBEExpDat.getTypeOfPermit(), "KCX0006");
                    	//12writeElement("UCRNumber", msgBEExpDat.getUCRNumber());                   	               	
                    	//12writeElement("DispatchCountry", msgBEExpDat.getDispatchCountry()); 
                        writeElement("DestinationCountry", msgBEExpDat.getDestinationCountry());
                        //writeElement("SituationCode", msgBEExpDat.getSituationCode());                 	          	
                        //12writeElement("PaymentType", msgBEExpDat.getPaymentType());
                        //12writeCodeElement("TransportInContainer", msgBEExpDat.getTransportInContainer(), "KCX0001");
                        writeElement("Netmass", msgBEExpDat.getGrossMass());
                        //12writeElement("GrossMass", msgBEExpDat.getGrossMass());                		         	
                        //12writeElement("UCROtherSystem", msgBEExpDat.getUCROtherSystem());
                        //12writeElement("Annotation", msgBEExpDat.getAnnotation());  
                        //12writeElement("ShipmentNumber", msgBEExpDat.getShipmentNumber());
                        //12writeElement("ReferenceNumber", msgBEExpDat.getReferenceNumber());
                        //12writeElement("OrderNumber", msgBEExpDat.getOrderNumber());
                        writeElement("TotalNumberPositions", msgBEExpDat.getTotalNumberPositions());  //EI20090610     
                        //12writeElement("AuthorizationNumber", msgBEExpDat.getAuthorizationNumber());
                        //12writeElement("PreviousProcedure", msgBEExpDat.getPreviousProcedure());
                		//12writeElement("DeclarantIsConsignor", msgBEExpDat.getDeclarantIsConsignor());
                		//EI20090608:writeElement("Clerk", msgBEExpDat.getClerk());
                  		//12writeContact("Contact", msgBEExpDat.getContact()); //EI20090608 
                		//12writeMeansOfTransport("Inland", msgBEExpDat.getTransportMeansInland());
                		//12writeMeansOfTransport("Departure", msgBEExpDat.getTransportMeansDeparture());    
                	    //12writeMeansOfTransport("Border", msgBEExpDat.getTransportMeansBorder());
                	    
                        //12writePlaceOfLoading(msgBEExpDat.getPlaceOfLoading());
                      
                    	//12writeElement("CustomsOfficeExport", msgBEExpDat.getCustomsOfficeExport());
                    	//12writeElement("CustomsOfficeForCompletion", msgBEExpDat  .getCustomsOfficeForCompletion());
                    	//12writeElement("IntendedOfficeOfExit", msgBEExpDat.getIntendedOfficeOfExit()); 
                        writeElement("PlaceOfDeclaration", msgBEExpDat.getPlaceOfDeclaration());
                        writeBusiness(msgBEExpDat.getBusiness());
                        writeTransportationRoute(msgBEExpDat.getTransportationRoute());                       
                        writeSeals(msgBEExpDat.getSeal(), "ExpDatBE");   
                        writeExportRefundHeader(msgBEExpDat.getExportRefundHeader());
                        writeLoadingTime(msgBEExpDat.getLoadingTime());
                        
                        //writeForwarder(msgBEExpDat.getShipper());                        
                        writeParty("Consignor", msgBEExpDat.getConsignor());    
                        writeParty("Declarant", msgBEExpDat.getDeclarant());                       
                        writeParty("Agent", msgBEExpDat.getAgent());                                                                                                    	
                       	writeParty("Subcontractor", msgBEExpDat.getSubcontractor());                                               
                       	writeParty("Consignee", msgBEExpDat.getConsignee());  
                       	writeParty("Beneficiary", msgBEExpDat.getBeneficiary());
  
                       	writeElement("DescriptionLanguage", msgBEExpDat.getDescriptionLanguage());
                       	writeElement("AccountNumber", msgBEExpDat.getAccountNumber());
                       	writeElement("RepertoriumNumber", msgBEExpDat.getRepertoriumNumber());
                       	writeElement("CustomsDocumentsReceiverContactPerson", msgBEExpDat.getCustomsDocumentsReceiverContactPerson());
                       	writeElement("ControlResultCode", msgBEExpDat.getControlResultCode());

                        writeIncoTerms(msgBEExpDat.getIncoTerms()); 
                        writeElement("LoadingLists", msgBEExpDat.getLoadingLists());
                        
                                                                     
                        goodsItemList =  msgBEExpDat.getGoodsItemList();                        
                        //EI20090428:writeHeadDocument(goodsItemList, "ExpDat");  //TODO-EI prüfen! max 99 - wie soll es gehen???
                        
                        if (goodsItemList != null) {
                        	int posListSize = goodsItemList.size(); //EI20090303
                        	for (int i = 0; i < posListSize; i++) {                        		                        		
                        		writeGoodsItemList(goodsItemList.get(i));                        		
                        	}   
                        }
                        
                    closeElement();	    
                    closeElement();	 // GoodsDeclaration
            
        }  //try                      
        	catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} //ende writeBody
    
    private void writeGoodsItemList(MsgBEExpDatPos msgBEExpDatPos) throws XMLStreamException {
    	if (msgBEExpDatPos == null) return;
      
    openElement("GoodsItem");
    	writeElement ("ItemNumber", msgBEExpDatPos.getItemNumber());      	
    	writeElement ("ArticleNumber", msgBEExpDatPos.getArticleNumber());                        
    	writeElement ("Description", msgBEExpDatPos.getDescription());  
    	writeElement ("UCROtherSystem", msgBEExpDatPos.getUCROtherSystem()); 
    	writeElement ("Annotation", msgBEExpDatPos.getAnnotation()); 
    	writeElement ("Annotation2", msgBEExpDatPos.getAnnotation2()); //TODO-EI was ist richtig?
      	writeElement ("ShipmentNumber", msgBEExpDatPos.getShipmentNumber()); 
    	writeCodeElement ("OriginFederalState", msgBEExpDatPos.getOriginFederalState(), "KCX0023"); //EI20090424
    	writeElement("DestinationCountry",msgBEExpDatPos.getDestinationCountry());
    	writeElement ("NetMass", msgBEExpDatPos.getNetMass());     		
    	writeElement ("GrossMass", msgBEExpDatPos.getGrossMass());     		
     	writeElement ("DangerousGoodsNumber", msgBEExpDatPos.getDangerousGoodsNumber()); 
    	writeElement ("PaymentType", msgBEExpDatPos.getPaymentType()); 
    	writeElement ("DescriptionLanguage", msgBEExpDatPos.getDescriptionLanguage());
    	
    	writeCommodityCode(msgBEExpDatPos.getCommodityCode());
    	writeExportRefundItem(msgBEExpDatPos.getExportRefundItem());
    	writeApprovedTreatment(msgBEExpDatPos.getApprovedTreatment());
    	//EI20090515 neu: ist eigentlich besser so vorzugehen:
    	//neu: writeApprovedTreatment(msgBEExpDatPos.getApprovedTreatment(), msgBEExpDatPos.getExportRefundItem());
    	writeStatistic(msgBEExpDatPos.getStatistic(), "ExpDat");
    	writeSpecialMentionList(msgBEExpDatPos.getSpecialMentionList());
    	writeParty("Consignee", msgBEExpDatPos.getConsignee());
    	writeParty("Consignor", msgBEExpDatPos.getConsignor());
    	writeParty("WarehouseKeeper", msgBEExpDatPos.getWarehouseKeeper());
    	writePackagesList(msgBEExpDatPos.getPackagesList(), ""); 
    	writeContainerNumberList(msgBEExpDatPos.getContainer()); 
    	
    	if (msgBEExpDatPos.getDocumentList() != null) {         		
    		for (int i = 0, listSize = msgBEExpDatPos.getDocumentList().size() ; i < listSize; i++) {   
     		    Document tmpDoc = msgBEExpDatPos.getDocumentList().get(i);                            	
                writeDocument(tmpDoc);        			     		
    		}    		
      	}
   	
    	if (msgBEExpDatPos.getPreviousDocumentList() != null) {
    			for (int i = 0, listSize = msgBEExpDatPos.getPreviousDocumentList().size();i < listSize; i++) {   
     		    	PreviousDocument tmpPrevDoc = msgBEExpDatPos.getPreviousDocumentList().get(i);                	            	
                	writePreviousDocument(tmpPrevDoc); 
    			}     			
    	}

    	writeCompletion("BondedWarehouseCompletion", msgBEExpDatPos.getBondedWarehouseCompletion());
    	writeCompletion("InwardProcessingCompletion", msgBEExpDatPos.getInwardProcessingCompletion());
    	writeWareHouse(msgBEExpDatPos.getWareHouse());
    	
    closeElement();
    } //item  
 
}