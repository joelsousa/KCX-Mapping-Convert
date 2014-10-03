/*
 * Function    : BodyExportDeclarationKidsUK.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / houdek
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenamesin Export
 * 			   : relates to kiff-export-reply.xls
 * Parameters  :

 * Changes
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 *
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgUids;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgUidsPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentU;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyExportDeclarationKids_old extends KidsMessage {

	private MsgUids msgUids;
	private MsgUidsPos position;
    List <MsgUidsPos>goodsItemList = null;  //AK20090107
	// CK081003
	//int listSize = 0;
	int listSizePos = 0; //EI20081006

    public BodyExportDeclarationKids_old(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgUids getMsgUids() {
		return msgUids;
	}
	public void setMsgUids(MsgUids argument) {
		this.msgUids = argument;
	}
	
	

    public void writeBody() {
        try {
            openElement("soap:Body");
            	openElement("ExportDeclaration");
                //openElement(this.kidsMessageName);
                    openElement("GoodsDeclaration");                	
                    	writeAreacodeTypeofpermit(msgUids.getTypeOfDeclaration());                     	
                        //EI20081022: writeElement ("KindOfAnswer", msgUids.getTypeOfRelease());                   	  	
                    	writeElement ("UCRNumber", msgUids.getDocumentReferenceNumber());                   	               	
                    	//EI20081022: writeElement ("CompletionTime", msgUids.getDateOfAdditionalDeclaration());     
                        writeDateTimeToString("CancellationTime", msgUids.getDateOfCancellation());                  	           	
                       //EI20081022: writeElement ("DeclarationTime", msgUids.getDateOfDeclaration());                  		                   	
                        writeElement ("DestinationCountry", msgUids.getDestinationCountry());                  			
//                      writeElement ("ExitTime", msgUids.getDateOfExit());                 	          	
//                      writeElement ("ReleaseTime", msgUids.getDateOfRelease());                 		                 		 
//                      writeElement ("AcceptanceTime", msgUids.getDateOfAcceptance());                 	       	
//                      writeElement ("ReceiveTime", msgUids.getDateOfReceipt());                		            	
//                      writeElement ("TimeOfRejection", msgUids.getDateOfRejection());                		   	
//                      writeElement ("BeginTimeOfProcessing", msgUids.getDateOfProcessing());                		           	
//                      writeElement ("ShortageInQuantity", msgUids.getShortageIndicator());                		              	
                        writeElement ("TransportInContainer", msgUids.getContainerIndicator());                		           	
                        writeElement ("GrossMass", msgUids.getGrossMass());                		         	
                        writeElement ("UCROtherSystem", msgUids.getCommercialReferenceNumber());      //AK20081120             		             	
                        writeElement ("Annotation", msgUids.getRemark());  
                        writeElement ("ReferenceNumber", msgUids.getReferenceNumber());                   		            	
                        writeElement ("OrderNumber", msgUids.getLocalReferenceNumber());                   		                 	
//                      writeElement ("TotalNumberPackages", msgUids.getPackagesQuantity());
//                      gibt es nur in der reversen Zollanmeldung
//                      wird nicht in derZollanmeldung gebraucht
                        writeElement ("TotalNumberPositions", msgUids.getItemsQuantity()); 
                        writeElement ("AuthorizationNumber", msgUids.getAuthorisationID());

                		if (msgUids.getApprovedTreatment() != null) {                            
                           writeElement ("PreviousProcedure", msgUids.getApprovedTreatment().getPrevious());                          
                  	    }
                   		
                		writeElement ("DeclarantIsConsignor", msgUids.getParticipantsCombination());
               		
                		if (msgUids.getContactPerson() != null) {
                			writeElement ("Clerk", msgUids.getContactPerson().getClerk());
                  		}
              		
                		writeMeansOfTransport("Inland", msgUids.getTransportMeansInland());
                		writeMeansOfTransport("Departure", msgUids.getTransportMeansDeparture());    
//                		writeMeansOfTransport("Border", msgUids.getTransportMeansDeparture());
                	    writeMeansOfTransport("Border", msgUids.getTransportMeansBorder());
                	 
                        writePlaceOfLoading(msgUids.getPlaceOfLoading());   
                        
                        //writeCustomsOffices(msgUids.getCustomsOffices(), "ExpDat");                                                 
                        if (msgUids.getCustomsOffices() != null) {
                        	writeElement("CustomsOfficeExport", msgUids.getCustomsOffices().getOfficeOfExport());
                    		writeElement("CustomsOfficeForCompletion", msgUids.getCustomsOffices().getOfficeOfAdditionalDeclarationExport());
                    		writeElement("IntendedOfficeOfExit", msgUids.getCustomsOffices().getOfficeOfExit());  
                        }
                        
                        writeBusiness(msgUids.getBusiness());
                        writeSealsU(msgUids.getSeal(), msgUids.getHeaderExtensions(), "ExpDat");                                                                       
                        writeLoadingTime(msgUids.getLoadingTime());
                        
                        writeForwarder(msgUids.getShipper());                        
                        writePartyU2K("Consignor", msgUids.getExporter());                            
                        writePartyU2K("Declarant", msgUids.getDeclarant());                       
                        writePartyU2K("Agent", msgUids.getDeclarantRepresentative());                                                                                                    	
                       	writePartyU2K("Subcontractor", msgUids.getSubcontractor());                                               
                       	writePartyU2K("Consignee", msgUids.getConsignee());                         	                       
  
                        writeIncoTerms(msgUids.getIncoTerms()); 
                       
                        
                        /*
                        if (msgUids.getHeaderExtensions()     != null) {
                        	openElement("Seal");
                       	      writeElement ("UseOfTydenseals", msgUids.getHeaderExtensions().getTydenSealsFlag());
                    	      writeElement ("UseOfTydensealStock", msgUids.getHeaderExtensions().getTydenSealsStockFlag());
                        	closeElement();
                        } 
                        */  
                        
                        // CK081003 Unterlagen im Kopf wird es in UIDS erst in der nächsten Version geben!
                        // writeHeadDocument();
                                                
 // ------  GoodsItem      
                          goodsItemList = msgUids.getGoodsItemList();
                          if (goodsItemList != null) {
                        	for (int i = 0; i < goodsItemList.size(); i++) {                        		                        		
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
    
    private void writeGoodsItemList(MsgUidsPos msgUidsPos) throws XMLStreamException {
    	if (msgUidsPos == null) return;
    	
    	int listSizeC = 0;
    	int listSizeP = 0;
    	int listSizePD = 0;
    	
    openElement("GoodsItem");
    	writeElement ("ItemNumber", msgUidsPos.getItemNumber());      	
    	writeElement ("ArticleNumber", msgUidsPos.getArticleNumber());                        
    	writeElement ("Description", msgUidsPos.getGoodsDescription());  
    	writeElement ("UCROtherSystem", msgUidsPos.getExternalRegistrationNumber()); 
    	writeElement ("Annotation", msgUidsPos.getRemark()); 
 //??  	writeElement ("ShipmentNumber", argument.getShipmentNumber()); 
    	writeElement ("OriginFederalState", msgUidsPos.getOriginRegion()); 
    	writeElement ("NetMass", msgUidsPos.getNetMass());     		
    	writeElement ("GrossMass", msgUidsPos.getGrossMass());     		
//??   	writeElement ("DangerousGoodsNumber", argument.getDangerousGoodsNumber()); 
//??	writeElement ("PaymentType", argument.getPaymentType()); 
 
    	writeCommodityCode(msgUidsPos);
    	writeApprovedTreatment(msgUidsPos.getApprovedTreatment());
    	writeStatistic(msgUidsPos.getStatisticalQuantity(), msgUidsPos.getStatisticalValue(), "", "", "ExpDat");    
    	// CK081009 get Consignee of Position! 
    	// writePartyU("Consignee", msgUids.getConsignee());       	    
    	writePartyU2K("Consignee", msgUidsPos.getConsignee());
    	writePackagesList(msgUidsPos.getPackagesList(), ""); 
    	writeContainerList(msgUidsPos.getContainerRegistrationList());
    	
    	if (msgUidsPos.getProducedDocumentUList() != null) {
    		
    		listSizeP = msgUidsPos.getProducedDocumentUList().size();
     		if (listSizeP > 0) {    			
    			for (int i = 0; i < listSizeP; i++) {   
     		    	DocumentU tmpDoc = new DocumentU();
                	tmpDoc = (DocumentU)msgUidsPos.getProducedDocumentUList().get(i);            		
                	writeProducedDocumentU(tmpDoc); 
    			}     			
    		}
      	}
   	
    	if (msgUidsPos.getPreviousDocumentUList() != null) {
     		listSizePD = msgUidsPos.getPreviousDocumentUList().size();
    		if (listSizePD > 0 ) {
    			//openElement("PreviousDocument");  //EI20081006 in die Funktion verlegt
    			for (int i = 0; i < listSizePD; i++) {   

     		    	PreviousDocument tmpPrevDoc = new PreviousDocument();
                	tmpPrevDoc = (PreviousDocument)msgUidsPos.getPreviousDocumentUList().get(i);            		
                	writePreviousDocument(tmpPrevDoc); 
    			} 
    			//closeElement();	
    		}
    	}   		 

    closeElement();
    } //item  
 
}  
 

	  
	  