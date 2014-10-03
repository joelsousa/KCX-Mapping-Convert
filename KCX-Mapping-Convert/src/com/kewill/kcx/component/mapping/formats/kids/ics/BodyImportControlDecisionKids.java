/*
 * Function    : BodyEntrySummaryDeclarationKids.java
 * Titel       :
 * Date        : 03.02.2011
 * Author      : iwaniuk
 * Description : 
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.kids.ics;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgImportControlDecision;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;

/**
 * Module	 BodyImportControlDecisionKids.<br> 
 * Erstellt		: 04.02.2011<br>
 * Beschreibung : 
 * 
 * @author 	iwaniuk
 * @version 1.0.00
 */
public class BodyImportControlDecisionKids extends KidsMessageICS {

	private MsgImportControlDecision message;	

    public BodyImportControlDecisionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportControlDecision getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportControlDecision argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ImportControlDecisionNotification");
            openElement("GoodsDeclaration"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber()); 
            	writeElement("MRN", message.getMrn());
            	writeElement("TotalNumberPosition", message.getTotalNumberPosition());
                writeElement("ShipmentNumber", message.getShipmentNumber());
                writeElement("ConveyanceReference", message.getConveyanceReference());
                	
                 if (message.getCarrier() != null) {
                    writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                    writePartyAddress("Carrier", message.getCarrier());
                 }
                 writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                 
                 if (message.getCustomsInterventionList() != null) {
	 		           for (CustomsIntervention item : message.getCustomsInterventionList()) {
	 		                writeCustomsInterventions(item);
	 		           }
	 	          }
                 if (message.getGoodsItemList() != null) {
	 		           for (GoodsItemShort item : message.getGoodsItemList()) {
	 		                writeGoodsItemShort(item);
	 		           }
	 	          }
	              	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    public void  writeSealsId(List<SealNumber> argument) throws XMLStreamException {
       	if (argument == null) {
    	    return;
    	} 
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	openElement("SealsID");
			for (SealNumber seals : argument) {
				writeElement("SealsIdentity", seals.getNumber());
				writeElement("SealsIdentityLng", seals.getLanguage());
			}
		closeElement();
    	
    }  
    /* moved into/called from KidsMessage
     * 
    public void writePartyTIN(String tag, TIN argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	
    	openElement(tag);             	                    	   
	    	writeElement("TIN", argument.getTIN());
	    	writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
	    	writeElement("isTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());
	    	writeCodeElement("IdentificationType", argument.getIdentificationType(), "KCX0069");   //A1340
	    closeElement();
    } 
    
    private void writeGoodsItem(MsgEntrySummaryDeclarationPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
	    openElement("GoodsItem");
	    	writeElement("ItemNumber", argument.getItemNumber());
	    	writeElement("ShipmentNumber", argument.getShipmentNumber());
	    	writeElement("Description", argument.getDescription());
	    	writeElement("DescriptionLng", argument.getDescriptionLng());
	    	writeElement("GrossMass", argument.getGrossMass());  
	    	writeCodeElement("DangerousGoodsNumber", argument.getDangerousGoodsNumber(), "KCX0064");   //C0101
	    	writeCodeElement("PaymentType", argument.getPaymentType(), "KCX0068");   //C0116
	    	writeElement("LoadingPlace", argument.getLoadingPlace());
	    	writeElement("LoadingPlaceLng", argument.getLoadingPlaceLng());
	    	writeElement("UnloadingPlace", argument.getUnloadingPlace());
	    	writeElement("UnloadingPlaceLng", argument.getUnloadingPlaceLng());
	    	if (argument.getConsignor() != null) {   
	    		writePartyTIN("ConsignorTIN", argument.getConsignor().getPartyTIN());
	    		writeParty("ConsignorAddress", argument.getConsignor());
	    	}
	    	if (argument.getConsignee() != null) {   
	    		writePartyTIN("ConsigneeTIN", argument.getConsignee().getPartyTIN());
	    		writeParty("ConsigneeAddress", argument.getConsignee());
	    	}
	    	if (argument.getNotifyParty() != null) {   
	    		writePartyTIN("NotifyPartyTIN", argument.getNotifyParty().getPartyTIN());
	    		writeParty("NotifyPartyAddress", argument.getNotifyParty());
	    	}
	    	
	    	if (argument.getContainersList() != null) {
				for (String container : argument.getContainersList()) {
					writeElement("Containers", container);   
				}
	    	}
	    	
	    	if (argument.getPackagesList() != null) {
		    	for (Packages packages : argument.getPackagesList()) {
		    		writePackage(packages);   
		    	}    	    	
	    	}
	    	
	    	if (argument.getMeansOfTransportBorderList() != null) {
		    	for (TransportMeans border : argument.getMeansOfTransportBorderList()) {
		    		writeTransportMeansType("MeansOfTransportBorder", border);   
		    	}
	    	}
	    	
	    	writeElement("CommodityCode", argument.getCommodityCode());
	    	
	    	if (argument.getDocumentList() != null) {
	    		for (IcsDocument document : argument.getDocumentList()) {  	    		
	    			writeDocument(document);  
	    		}
	      	}
	    	if (argument.getSpecialMentionList() != null) {
	    		for (SpecialMention special : argument.getSpecialMentionList()) {    
	    			writeSpecialMention(special);  			    		
	    		} 
	    	}
	    	
	    closeElement();
    }
    
    public void writeTransportMeansType(String tag, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	
    	openElement(tag);             	                    	   
	    	writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");
	    	writeElement("TransportationNumber", argument.getTransportationNumber());
	    	writeElement("TransportationCountry", argument.getTransportationCountry());
    	closeElement();    	
    }
*/
}
