package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TypeOfDeclaration;
import com.kewill.kcx.component.mapping.util.Utils;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
/*
 * Function    : BodyExportDeclarationUids.java
 * Title       :
 * Date        : 15.04.2009
 * Author      : CSF GmbH / iwaniuk
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Export Declaration) kiff-export.xls 
 *             : Version 6.0
 * Changes 
 * -----------
 * Author      :  krzoska 
 * Date        :  11.05.2009
 * Label       :  AK20090511
 * Description :  DateOfLoadingBegin and DateOfLoadingEnd are no tags in ExportDeclaration
 * 
 * Author      :  krzoska 
 * Date        :  11.05.2009
 * Label       :  AK20090511-2 
 * Description :  SpecCircumstance with capital S
 *
 * Author      : krzoska
 * Date        : 
 * Label       : AK20090512
 * Description : fexeda_fregnr = CommercialReferenceNumber/UCROtherSystem 
 * 		         fsssas_knrsdg = ExternalRegistrationNumber/ShipmentNumber
 *
 * Author      : krzoska
 * Date        : 
 * Label       : AK20090513
 * Description : fexeda_fregnr = CommercialReferenceNumber/ShipmentNumber 
 * 		         fsssas_knrsdg = ExternalRegistrationNumber/UCROtherSystem
 *
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk
 *             : ItemsQuantity added  
 */

public class BodyExportDeclarationUids extends UidsMessage {
	

    private MsgExpDat  msgExpDat = new MsgExpDat();
 
 
    public BodyExportDeclarationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpDat getMsgExpDat() {
		return msgExpDat;
	}

	public void setMsgKids(MsgExpDat msgExpDat) {
		this.msgExpDat = msgExpDat;
	}

    
	public void writeBody() {
				
		
        try {    		
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
                      openElement("ExportDeclaration");                        
                    	writeParty("Consignee", msgExpDat.getConsignee());   
                    	//writeParty("Consignor", msgExpDat.getConsignor());                     					
                		//EI20090608:writeContact(msgExpDat.getClerk());    
                    	writeContact(msgExpDat.getContact());  //EI20090608
                		writeParty("Declarant", msgExpDat.getDeclarant());                		
                		writeParty("DeclarantRepresentative", msgExpDat.getAgent());                												                 		                	
                		writeParty("Exporter", msgExpDat.getConsignor());                   		
                		writeParty("WarehouseKeeper", msgExpDat.getWarehousekeeper());
                		writeParty("Subcontractor", msgExpDat.getSubcontractor());
                		writeParty("Beneficiary", msgExpDat.getBeneficiary());
                		writeParty("InitialSender", msgExpDat.getInitialSender());
                		writeParty("CustomsDocumentsReceiver", msgExpDat.getCustomsDocumentsReceiver());
                       
                		writeElement("AuthorisationID", msgExpDat.getAuthorizationNumber());                		
                    	writeElement("ContainerIndicator", msgExpDat.getTransportInContainer());                                    		
                    	writeCustomsOffices(msgExpDat.getCustomsOfficeExport(), msgExpDat.getCustomsOfficeForCompletion(), 
                    	                    msgExpDat.getIntendedOfficeOfExit(), "");
                		
                    	writeElement("ExternalRegistrationNumber",msgExpDat.getUCROtherSystem());                 		
                    	writeElement("DocumentReferenceNumber", msgExpDat.getUCRNumber());    //MRN                	
                        writeElement("DestinationCountry", msgExpDat.getDestinationCountry());                		
                        writeElement("NetMass", msgExpDat.getNetMass());
                        writeElement("GrossMass", msgExpDat.getGrossMass());
                    	writeIncoTerms(msgExpDat.getIncoTerms());
                    	writeElement("IssuePlace", msgExpDat.getPlaceOfDeclaration());
                    	writeElement("ItemsQuantity", msgExpDat.getTotalNumberPositions());//EI20090610  
                    	// new for Belgium
                    	writeElement("LoadingList",msgExpDat.getLoadingLists());
                    	writeElement("LocalReferenceNumber", msgExpDat.getOrderNumber());
                    	writeMeansOfTransportInland(msgExpDat.getTransportMeansInland());
                    	writeMeansOfTransport(msgExpDat.getTransportMeansDeparture(), "Departure");
                        writeMeansOfTransport(msgExpDat.getTransportMeansBorder(), "Border");
                		writeElement("CountryOfDispatch", msgExpDat.getDispatchCountry()); //AK20081120
                		writeElement("ParticipantsCombination", msgExpDat.getDeclarantIsConsignor());
                		writePlaceofLoading(msgExpDat.getPlaceOfLoading());                		
                		writePrevious(msgExpDat.getPreviousProcedure());                		
                		writeElement("ReferenceNumber", msgExpDat.getReferenceNumber());
                		writeElement("Remark", msgExpDat.getAnnotation());
                		writeSeals(msgExpDat.getSeal());
                		writeShipmentPeriod(msgExpDat.getLoadingTime());
                		writeTransaction(msgExpDat.getBusiness()); 
                		writeTypeOfDeclaration(msgExpDat.getAreaCode(), msgExpDat.getTypeOfPermit());
                		writeHeaderExtensions(msgExpDat.getSeal(), "ExpDat");
                		//AK20090511-2 
                		writeElement("SpecCircumstance", msgExpDat.getSituationCode());
                		writeElement("TransportPaymentMethod", msgExpDat.getPaymentType());
                		
                		//AK20090512
                		writeElement("CommercialReferenceNumber", msgExpDat.getShipmentNumber()); 
                 		                		             		
                		writeItinerary(msgExpDat.getTransportationRoute());
                		writeExportRestitutionHeader(msgExpDat.getExportRefundHeader());
                		writeElement("DescriptionLanguage", msgExpDat.getDescriptionLanguage());
                       	writeElement("AccountNumber", msgExpDat.getAccountNumber());
                       	writeElement("RepertoriumNumber", msgExpDat.getRepertoriumNumber());
                       	writeElement("ControlResultCode", msgExpDat.getControlResultCode());

                        if (msgExpDat.getGoodsItemList() != null) {
                        	for (int i = 0; i < msgExpDat.getGoodsItemList().size(); i++) {
                        		writeGoodsItemList((MsgExpDatPos) msgExpDat.getGoodsItemList().get(i));
                        	}  
                        }
                      closeElement(); // ExportDeclaration
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
	private void writeGoodsItemList(MsgExpDatPos argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
	openElement("GoodsItem"); 
		
		writeElement("ItemNumber", argument.getItemNumber());		
	   	writeElement("ArticleNumber", argument.getArticleNumber());	   
	   	writeContainer(argument.getContainer());	   	   	
	   	writeParty("Consignee", argument.getConsignee());
	   	writeParty("WarehouseKeeper", argument.getWarehouseKeeper());
	   	writeCommodityCode(argument.getCommodityCode()); 	   
	   	//MRN ???
	    //writeElement("ExternalRegistrationNumber", argument.getUCROtherSystem());	  
	   	writeElement("DestinationCountry",argument.getDestinationCountry());
	   	writeElement("GoodsDescription", argument.getDescription());	   		   	
    	writeElement("GrossMass", argument.getGrossMass());  
    	writeElement("NetMass", argument.getNetMass());
    	writeElement("OriginRegion", argument.getOriginFederalState());
    	
    	if (argument.getPackagesList() != null) {
    		int size = argument.getPackagesList().size();
    		for (int i = 0; i < size; i++) {   
    			Packages tmpPack = new Packages();
            	tmpPack = (Packages) argument.getPackagesList().get(i);
            	writePackaging(tmpPack, ""); 
    		}                		
    	}
    	            		
        writeProcedure(argument.getApprovedTreatment());
    	
    	if (argument.getPreviousDocumentList() != null) {
    		for (int i = 0; i < argument.getPreviousDocumentList().size(); i++) {   
    			PreviousDocument tmpPrev = new PreviousDocument();
    			tmpPrev = (PreviousDocument) argument.getPreviousDocumentList().get(i);
            	writePreviousDocument(tmpPrev); 
    		}                		
    	}

    	if (argument.getDocumentList() != null) {
    		for (int i = 0; i < argument.getDocumentList().size(); i++) {   
    			Document tmpDoc = new Document();
    			tmpDoc = (Document) argument.getDocumentList().get(i);
            	writeProducedDocument(tmpDoc); 
    		}                		
    	}       
    	
		writeElement("Remark", argument.getAnnotation());		
		if (argument.getStatistic() != null) {
			writeElement("StatisticalValue", argument.getStatistic().getStatisticalValue());
			writeElement("StatisticalQuantity", argument.getStatistic().getAdditionalUnit());
			writeElement("StatisticalBaseValue", argument.getStatistic().getValue());
			writeElement("StatisticalBaseCurrency", argument.getStatistic().getCurrency());
			writeElement("AdditionalUnitCode", argument.getStatistic().getAdditionalUnitCode());
			
		}
		writeElement("TransportPaymentMethod", argument.getPaymentType());  //EI20090415
		writeElement("TCommercialReferenceNumber", argument.getShipmentNumber()); //EI20090415
		writeElement("UNDGCode", argument.getDangerousGoodsNumber()); //EI20090415
		writeExportRestitutionItem(argument.getExportRefundItem(), argument.getApprovedTreatment());  //EI20090415
		writeWriteOffATLAS(argument.getBondedWarehouseCompletion(), argument.getInwardProcessingCompletion());   //EI20090415
		writeElement ("DescriptionLanguage", argument.getDescriptionLanguage());
		writeWareHouse(argument.getWareHouse());
		
	closeElement();
	}

}    	
