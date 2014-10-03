
/*
 * Function    : BodyExportKids.java
 * Titel       :
 * Date        : 03.03.2009
 * Author      : Kewill CSF / iwaniuk 
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff-export-reply.xls
 *             : Version 6
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Label       :
 * Description : KCX-Code checked
 *
 *           ===> dies ist identisch mit BodyReverseDeclaration, bis auf Datumsfelder ?!
 *
 * Author      : E.Iwaniuk         
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk     
 */
 
package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyExportADPtoReverseDeclarationKids_notInUse extends KidsMessage {
	
	
	private MsgExpRel msgExpRel = new MsgExpRel();
	private PDFInformation pdfInformation;
	List<String> pdflist; 
	
	//private int listSize; 
	
    public BodyExportADPtoReverseDeclarationKids_notInUse(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpRel getMessage() {
		return msgExpRel;
	}

	public void setMessage(MsgExpRel argument) {
		this.msgExpRel = argument;
	}

    public void writeBody() {
        try {        	
            openElement("soap:Body");
                openElement("ReverseDeclaration"); //AK20081120
                    openElement("GoodsDeclaration");             	    	
                    	writeCodeElement("AreaCode", msgExpRel.getAreaCode(), "KCX0005");
                        writeCodeElement("TypeOfPermit", msgExpRel.getTypeOfPermit(), "KCX0006");                     
                        writeCodeElement("KindOfAnswer", msgExpRel.getKindOfAnswer(), "KCX0045");                        
                        writeElement("UCRNumber", msgExpRel.getUCRNumber());
                        writeElement("CompletionTime", msgExpRel.getCompletionTime());                      
                        writeElement("DeclarationTime", msgExpRel.getDeclarationTime());                       
                        writeElement("DispatchCountry", msgExpRel.getDispatchCountry());    //EI20090306
                        writeElement("DestinationCountry", msgExpRel.getDestinationCountry());                                              
                        writeElement("ReleaseTime", msgExpRel.getReleaseTime());
                        writeElement("AcceptanceTime", msgExpRel.getAcceptanceTime());
                        writeElement("ReceiveTime", msgExpRel.getReceiveTime());                    
                        writeElement("ShortageInQuantity", msgExpRel.getShortageInQuantity() );
                        writeCodeElement("AlternativeDocument", msgExpRel.getAlternativeDocument(), "KCX0015");  //EI20090306
                        writeElement("SituationCode", msgExpRel.getSituationCode());                             //EI20090306
                        writeElement("PaymentType", msgExpRel.getPaymentType());                                 //EI20090306                        
                        writeCodeElement("TransportInContainer", msgExpRel.getTransportInContainer(), "KCX0001");
                        writeElement("GrossMass", msgExpRel.getGrossMass());
                        writeElement("UCROtherSystem", msgExpRel.getUCROtherSystem());
                        writeElement("Annotation", msgExpRel.getAnnotation());
                        writeElement("ShipmentNumber", msgExpRel.getShipmentNumber());                           //EI20090306 
                        writeElement("ReferenceNumber", msgExpRel.getReferenceNumber());
                        writeElement("OrderNumber", msgExpRel.getOrderNumber());   
                        writeElement("TotalNumberPackages", msgExpRel.getTotalNumberPackages());
                        writeElement("TotalNumberPositions", msgExpRel.getTotalNumberPositions());                                              //
                        writeElement("ReceiverCustomerNumber", msgExpRel.getReceiverCustomerNumber());  //EI20090306 
                        writeElement("DeclarantIsConsignor", msgExpRel.getDeclarantIsConsignor());                        
                        //EI20090608: writeElement("Clerk", msgExpRel.getClerk());                    
                        writeContact("Contact", msgExpRel.getContact()); //EI20090608 
                        
                        // C. Kron PdfInformation mit eingebundenem PDF schreiben  
                        writePDFInformation(msgExpRel.getPdfInformation());                       
                        writeMeansOfTransport("Inland", msgExpRel.getTransportMeansInland());
                      //EI20081022: writeMeansOfTransport("Departure", msgExpRel.getTransportMeansDeparture());
                        writeMeansOfTransport("Border", msgExpRel.getTransportMeansBorder());
                      //EI20081022: writePlaceOfLoading(msgExpRel.getPlaceOfLoading());  
                        
                        writeElement("CustomsOfficeExport", msgExpRel.getCustomsOfficeExport());
                		writeElement("CustomsOfficeForCompletion", msgExpRel.getCustomsOfficeForCompletion()); 
                		writeElement("RealOfficeOfExit", msgExpRel.getRealOfficeOfExit()); 
                                                                  
                        writeBusiness(msgExpRel.getBusiness());                          	
                        writeTransportationRoute(msgExpRel.getRoute()); //EI20090306
                        writeSeals(msgExpRel.getSeal(), "ExpRel");
                        writeExportRefundHeader(msgExpRel.getExportRefundHeader()); //EI20090306
                        writeLoadingTime(msgExpRel.getLoadingTime());                           	                                                                     
                      //EI20081022: writeForwarder(msgExpRel.getForwarder().getETNAddress(), msgExpRel.getForwarderTIN().getCustomerIdentifier()); 
                        writeParty("Consignor", msgExpRel.getConsignor());
                        writeParty("Declarant", msgExpRel.getDeclarant());
                        writeParty("Agent", msgExpRel.getAgent());
                        writeParty("Subcontractor", msgExpRel.getSubcontractor()); 
                        writeParty("Consignee", msgExpRel.getConsignee());
                                             	
                        writeIncoTerms(msgExpRel.getIncoTerms());
                        writeDocumentList(msgExpRel.getDocumentList());  //EI20090306
                        
                        if (msgExpRel.getGoodsItemList() != null) {
                        	int listSize = msgExpRel.getGoodsItemList().size();
                        	for (int i = 0; i < listSize; i++) { 
                        		openElement("GoodsItem");
                        			MsgExpRelPos position = new MsgExpRelPos();
                        			position = (MsgExpRelPos) msgExpRel.getGoodsItemList().get(i);
                        			writeGoodsItemList(position);
                        		closeElement();
                        	}  
                        }
                    closeElement(); // GoodsDeclaration                    
                closeElement(); // ExportDeclaration
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
    private void writeGoodsItemList(MsgExpRelPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
    	writeElement("ItemNumber", argument.getItemNumber());      	
    	writeElement("ArticleNumber", argument.getArticleNumber());                        
    	writeElement("Description", argument.getDescription());  
    	writeElement("UCROtherSystem", argument.getUCROtherSystem()); 
    	writeElement("Annotation", argument.getAnnotation()); 
    	writeElement("ShipmentNumber", argument.getShipmentNumber()); //EI20090306    	    	
    	writeCodeElement("OriginFederalState", argument.getOriginFederalState(), "KCX0023"); //EI20090306       	
    	writeElement("NetMass", argument.getNetMass());     		
    	writeElement("GrossMass", argument.getGrossMass());     		
    	writeElement("DangerousGoodsNumber", argument.getDangerousGoodsNumber()); //EI20090306 
    	writeElement("PaymentType", argument.getPaymentType()); //EI20090306     	    	  		 
    	writeCommodityCode(argument.getCommodityCode());         	
    	writeExportRefundItem(argument.getExportRefundItem());     	//EI20090306 	    	
    	writeApprovedTreatment(argument.getApprovedTreatment());    		    	 	
    	writeStatistic(argument.getStatistic(), "ExpAdp");     	
    	writeParty("Consignee", argument.getConsignee());     		    	
    	writePackagesList(argument.getPackagesList(), "");     	
    	    	    	
    	/*
    	if (argument.getProducedDocumentList() != null) {
    		int listSize = argument.getProducedDocumentList().size();
    		for (int i = 0; i < listSize; i++) {   
    			openElement("Document"); 
            		Document tmpDoc = new Document();
            		tmpDoc = (Document)argument.getProducedDocumentList().get(i);            		
            		writeDocument(tmpDoc); 
            	closeElement();
    		}                		
    		closeElement();
    	}  
    	*/
    	//writeContainerList(argument.getContainer().getNumberList());
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
    	//EI20090306:       	
    	writeCompletion("BondedWarehouseCompletion", argument.getBondedWarehouseCompletion() );
    	writeCompletion("InwardProcessingCompletion", argument.getInwardProcessingCompletion());
    	
    }  //ende writeGoodsItemList
   
       
// ende der Klasse
}
