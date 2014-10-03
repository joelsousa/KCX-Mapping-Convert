/*
 * Funktion    : BodyExportReleaseUids.java
 * Titel       :
 * Erstellt    : 10.09.2008
 * Author      : CSF GmbH / Krzoska
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 *             : relates to (reverse Declaration) kids-export-reply.xls 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      : E.Iwaniuk
 * Datum       : 10.03.2009
 * Kennzeichen : EI20090310
 * Beschreibung: Version 60
 * Anmerkungen : for the most of new KidsTags are't any UidsTags of UidsMessage 'ExportRelease'
 * 
 * Author      : krzoska
 * Date        : 
 * Label       : AK20090512
 * Description : ExternalRegistrationNumber must be mapped from  ShipmentNumber
 * 		         CommercialReferenceNumber must be mapped from UCROtherSystem
 *
 * Author      : krzoska
 * Date        : 
 * Label       : AK20090513
 * Description : ExternalRegistrationNumber must be mapped from UCROtherSystem
 * 		         CommercialReferenceNumber must be mapped from   ShipmentNumber
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
 * Author      : EI
 * Label       : EI20100607
 * Description : new member: exitTime 
 * Label       : EI20100803
 * Description : exitTime should be in DateTime-Format: writeStringToDateTime(...)    
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: BodyExportReleaseUids<br>
 * Erstellt		: 18.12.2008<br>
 * Beschreibung	: UIDS body of message "ExportRelease".
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class BodyExportReleaseUids extends UidsMessage {
    private MsgExpRel  msgExpRel = new MsgExpRel();
    private PDFInformation pdfInformation;
	private List<String> pdflist; 
 
 
    public BodyExportReleaseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpRel getMessage() {
		return msgExpRel;
	}

	public void setMessage(MsgExpRel msgExpRel) {
		this.msgExpRel = msgExpRel;
	}

    
	public void writeBody() {
//writeElement("AlternateProofIndicator", msgExpRel.getAlternativeDocument());				
        try {
        	//int listLength = 0;
        	openElement("soap:Body");
                openElement("Submit");
                // C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                // setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                    openElement("Export");
                      openElement("ExportRelease");   //AK20081120                      
                        //EI20090608: writeContact(msgExpRel.getClerk());
                        writeContact(msgExpRel.getContact()); //EI20090608
                    	writeParty("Consignee", msgExpRel.getConsignee());                     	                       
                		writeParty("Declarant", msgExpRel.getDeclarant());                		
                		         /*Agent=>DeclarantRepresentative*/
                		writeParty("DeclarantRepresentative", msgExpRel.getAgent());                		
                		         /*Consignor=>Exporter*/
                		writeParty("Exporter", msgExpRel.getConsignor());              		               
                		        /* Forwarder => Shipper */
                		//EI20081022: writeShipper(msgExpRel.getForwarder(), msgExpRel.getForwarderTIN());
                		writeParty("Subcontractor", msgExpRel.getSubcontractor());                		
                		////SubcontractorRepresentative();
                		////AirportCode
                		////AnswerIndicator
                		//EI20081022: writeElement("AuthorisationID", msgExpRel.getAuthorizationNumber());
                        writeElement("ContainerIndicator", msgExpRel.getTransportInContainer()); 	
                		writeCustomsOffices(msgExpRel.getCustomsOfficeExport(), 
                		                    msgExpRel.getCustomsOfficeForCompletion(), 
                		                    msgExpRel.getRealOfficeOfExit(), "");
                		// writeStringToDateTime("DateOfAcceptance", msgExpRel.getAcceptanceTime());  
                		writeFormattedDateTime("DateOfAcceptance", msgExpRel.getAcceptanceTime(),
                				msgExpRel.getAcceptanceTimeFormat(), EFormat.ST_DateTimeZ);	   
                        // writeStringToDateTime("DateOfAdditionalDeclaration", msgExpRel.getCompletionTime());
                		writeFormattedDateTime("DateOfAdditionalDeclaration", msgExpRel.getCompletionTime(),
                				msgExpRel.getCompletionTimeFormat(), EFormat.ST_DateTimeZ);	   
                		
                        //EI20081022: writeElement("DateOfCancelation", msgExpRel.getCancellationTime()) ;
                        // writeStringToDateTime("DateOfDeclaration", msgExpRel.getDeclarationTime());
                		writeFormattedDateTime("DateOfDeclaration", msgExpRel.getDeclarationTime(),
                				msgExpRel.getDeclarationTimeFormat(), EFormat.ST_DateTimeZ);	   

                        // writeStringToDateTime("DateOfExit", msgExpRel.getExitTime());
                		writeFormattedDateTime("DateOfExit", msgExpRel.getExitTime(),
                				msgExpRel.getExitTimeFormat(), EFormat.ST_DateTimeZ);	   
                		
                        if (msgExpRel.getLoadingTime() != null) {
                        	// writeStringToDateTime("DateOfLoadingBegin", msgExpRel.getLoadingTime().getBeginTime());
                    		writeFormattedDateTime("DateOfLoadingBegin", msgExpRel.getLoadingTime().getBeginTime(),
                    				msgExpRel.getLoadingTime().getLoadingBeginFormat(), EFormat.ST_DateTimeZ);	   

                        	// writeStringToDateTime("DateOfLoadingEnd", msgExpRel.getLoadingTime().getEndTime());
                    		writeFormattedDateTime("DateOfLoadingEnd", msgExpRel.getLoadingTime().getEndTime(),
                    				msgExpRel.getLoadingTime().getLoadingEndFormat(), EFormat.ST_DateTimeZ);	   
                        	
                        }
                        // writeStringToDateTime("DateOfReceipt", msgExpRel.getReceiveTime());
                		writeFormattedDateTime("DateOfReceipt", msgExpRel.getReceiveTime(),
                				msgExpRel.getReceiveTimeFormat(), EFormat.ST_DateTimeZ);	   
                        
                        // writeStringToDateTime("DateOfRelease", msgExpRel.getReleaseTime());
                		writeFormattedDateTime("DateOfRelease", msgExpRel.getReleaseTime(),
                				msgExpRel.getReleaseTimeFormat(), EFormat.ST_DateTimeZ);	   
                        
                        writeElement("CountryOfDispatch", msgExpRel.getDispatchCountry()); //EI20090508 wohin damit???
                        writeElement("DestinationCountry", msgExpRel.getDestinationCountry());
                        //AK200090513
                		writeElement("ExternalRegistrationNumber", msgExpRel.getUCROtherSystem());
                        writeElement("DocumentReferenceNumber", msgExpRel.getUCRNumber());
                    	////ExportRefund   
                        ////MRN                       
                        //in xsd ist nur MRN (?!): 
                        
                    	writeElement("GrossMass", msgExpRel.getGrossMass());                    	
                    	writeIncoTerms(msgExpRel.getIncoTerms());                    	
                    	writeElement("ItemsQuantity", msgExpRel.getTotalNumberPositions());
                    	writeElement("LocalReferenceNumber", msgExpRel.getOrderNumber());
                    	
                    	writeMeansOfTransportInland(msgExpRel.getTransportMeansInland());   
                    	////kommt nicht vor: writeMeansOfTransportDeparture(msgExpRel.getTransportMeansDeparture());
                    	writeMeansOfTransport(msgExpRel.getTransportMeansBorder(), "Border");
                    	
                    	writeElement("PackagesQuantity", msgExpRel.getTotalNumberPackages());                   	
                		writeElement("ParticipantsCombination", msgExpRel.getDeclarantIsConsignor()); 
                		writePdfInformation(msgExpRel.getPdfInformation());  //EI20090309 
                		
                		writePlaceofLoading(msgExpRel.getPlaceOfLoading());  //EI20090525
                		//EI20081022 writePrevious(msgExpRel.getPreviousProcedure());
                		writeElement("ReferenceNumber", msgExpRel.getReferenceNumber());
                		////RegistrationNumber
                		writeElement("Remark", msgExpRel.getAnnotation());                		
                		////RepresentationFlag
                		writeSeals(msgExpRel.getSeal());                	
                		writeElement("ShortageIndicator", msgExpRel.getShortageInQuantity());
                		writeElement("AlternateProofIndicator", msgExpRel.getAlternativeDocument()); //EI20090508 V60
                		writeShipmentPeriod(msgExpRel.getLoadingTime());                		
                		writeTransaction(msgExpRel.getBusiness());                		
                		writeTypeOfDeclaration(msgExpRel.getAreaCode(), msgExpRel.getTypeOfPermit());
                		writeElement("TypeOfRelease", msgExpRel.getKindOfAnswer());
                		writeHeaderExtensions(msgExpRel.getSeal(), "ExpRel"); 
                		
                		writeElement("SpecCircumstance", msgExpRel.getSituationCode());            //EI20090310 
                		writeElement("TransportPaymentMethod", msgExpRel.getPaymentType());        //EI20090310 
                		//AK20090513
                		writeElement("CommercialReferenceNumber", msgExpRel.getShipmentNumber());  
           
                		writeItinerary(msgExpRel.getRoute());                                      //EI20090310 
                		writeExportRestitutionHeader(msgExpRel.getExportRefundHeader());
                		
                		//EI20090310                	                		
                		 /*  EI20090310 - diese Tags wurden für UIDS/ExportRelease neu definiert:
                         ???KIDS: DocumentList(fexaed_) ==> UIDS: GoodsItem.ProducedDocument  
                                                            wie soll das gehen??? Kopf-Daten nach 1.Item???
                         */     
                		
                        if (msgExpRel.getGoodsItemList() != null) {                        	
                        	for (int i = 0; i < msgExpRel.getGoodsItemList().size(); i++) {
                        		writeGoodsItemList((MsgExpRelPos) msgExpRel.getGoodsItemList().get(i));
                        	}  
                        }
                      closeElement(); // ExportRelease
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	private void writeGoodsItemList(MsgExpRelPos argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
		openElement("GoodsItem");			
		writeElement("ItemNumber", argument.getItemNumber());		
	   	writeElement("ArticleNumber", argument.getArticleNumber());	
	   	writeContainer(argument.getContainer());	   		   	       
	   		   
	   	//writeParty("Consignee", argument.getConsignee());	   	
	   	writeCommodityCode(argument.getCommodityCode());
	   	//destinationCountry
	   	////MRN	 
	    //in xsd ist nur MRN: 
	   	writeElement("ExternalRegistrationNumber", argument.getUCROtherSystem());  
    	writeElement("GoodsDescription", argument.getDescription());
    	writeElement("GrossMass", argument.getGrossMass());  
    	writeElement("NetMass", argument.getNetMass());    	
    	////OriginCountry
    	writeElement("OriginRegion", argument.getOriginFederalState());    	
    	
    	if (argument.getPackagesList() != null) {    		
    		for (int i = 0; i < argument.getPackagesList().size(); i++) {       			         	
            	writePackaging((Packages) argument.getPackagesList().get(i), ""); 
    		}                		
    	}
    	
    	if (argument.getApprovedTreatment() != null) {    			   			     
            writeProcedure((ApprovedTreatment) argument.getApprovedTreatment());
    	}
    	
    	if (argument.getPreviousDocumentList() != null) {    		
    		for (int i = 0; i < argument.getPreviousDocumentList().size(); i++) {
            	writePreviousDocument((PreviousDocument) argument.getPreviousDocumentList().get(i)); 
    		}                		
    	}
    	
    	if (argument.getDocumentList() != null) {    		
    		for (int i = 0; i < argument.getDocumentList().size(); i++) {       			          	
            	writeProducedDocument((Document) argument.getDocumentList().get(i));   
    		}                		
    	}
    	/* für V6
    	if (argument.getSensitiveGoods() != null) {
    		openElement("SpecialGoodsInformation"); 
        		writeElement("Code", argument.getSensitiveGoods().getType());
        		writeElement("Amount", argument.getSensitiveGoods().getWeight());
    		closeElement();
    	}
    	
    	if (argument.getKindOfArticle() != null || argument.getNonCustomsLaw() != null) {
    		openElement("SpecialRemarks");
    			writeElement("AdditionalCode", argument.getKindOfArticle());
    			////Agricultural....
    			writeElement("RestrictionCode", argument.getNonCustomsLaw().getnonCustomsLawType());
    			////TaxCode
        	closeElement();
    	}
    	*/
		writeElement("Remark", argument.getAnnotation()); 
		
		if (argument.getStatistic() != null) {
			writeElement("StatisticalValue", argument.getStatistic().getStatisticalValue());
			writeElement("StatisticalQuantity", argument.getStatistic().getAdditionalUnit());			
		}
		
		writeElement("TransportPaymentMethod", argument.getPaymentType());       //EI20090310 ???
		//AK20090513
		writeElement("CommercialReferenceNumber", argument.getShipmentNumber()); 
		writeElement("UNDGCode", argument.getDangerousGoodsNumber());            //EI20090310 ???		
		writeExportRestitutionItem(argument.getExportRefundItem(), argument.getApprovedTreatment());  //EI20090310
		writeWriteOffATLAS(argument.getBondedWarehouseCompletion(), argument.getInwardProcessingCompletion());   
		
		closeElement();
		
	} //ende-Item	
	
}    	
