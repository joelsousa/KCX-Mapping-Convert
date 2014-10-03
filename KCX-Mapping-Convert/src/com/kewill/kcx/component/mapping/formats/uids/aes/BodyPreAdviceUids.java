/*
 * Function    : BodyExportPrenotificationKU.java
 * Titel       :
 * Date        : 18.03.2009
 * Author      : CSF GmbH / iwaniuk
 * Description : valid Names of KIDS-Messagenames to UIDS-Messagenames in Export
 * 			   : relates to (Export pre-notification) kiff-export.xls 
 *             : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *             : there is NO UIDS-Definition for ExportPrenotification in etn_export_V20.xsd
 *             : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*   
 * Changes
 * -----------
 * Author      : EI
 * Date        : 22.04.2009
 * Description : msgUids  replaced with MsgExpDat 
 *
 * Author      : krzoska 
 * Date        : 11.05.2009
 * Label       : AK20090511
 * Description : DateOfLoadingBegin and DateOfLoadingEnd removed 
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk  
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : BodyExportPrenotificationKU<br>
 * Erstellt     : 18.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format of Export PreNotification into UIDS-Format of ExportPreNotification. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyPreAdviceUids extends UidsMessage {
	
    private MsgExpDat  msgExpDat = new MsgExpDat();
 
    public BodyPreAdviceUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpDat getMessage() {
		return msgExpDat;
	}

	public void setMessage(MsgExpDat msgExpDat) {
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
                      openElement("ExportPreAdvice");  
                       
                    	writeParty("Consignee", msgExpDat.getConsignee(), null, null);
                		//EI20090608: writeContact(msgExpDat.getClerk());  
                    	writeContact(msgExpDat.getContact()); //EI20090608
                		writeParty("Declarant", msgExpDat.getDeclarant());                		                                       	
                		writeParty("DeclarantRepresentative", msgExpDat.getAgent());                  		                                                   	
                		writeParty("Exporter", msgExpDat.getConsignor());
                		/* EI: soll es wirklich in Item(s) ausgeben werden???      
                		if (msgExpDat.getForwarderContactPerson() != null) {           			
                				openElement("Contact");
                				writeContact(msgExpDat.getForwarderContactPerson());  
                			 	closeElement();                			
                		}
        				*/
                		writeParty("Subcontractor", msgExpDat.getSubcontractor());                 		                                           		                	
                		writeElement("AuthorisationID", msgExpDat.getAuthorizationNumber());                    	
                    	writeCustomsOffices(msgExpDat.getCustomsOfficeExport(), "", "", "");
                    	writeElement("DateOfPreAdvice", msgExpDat.getAdvanceNoticeTime());  
                    	if (msgExpDat.getExportRefundHeader() != null) {
                    		String country = msgExpDat.getExportRefundHeader().getDestinationCountry();
                    		if (!Utils.isStringEmpty(country)) {
                    			writeElement("DestinationCountry", country);   
                    		}
                    	}
                    	
                    	//AK20090511
                    	writeElement("LocalReferenceNumber", msgExpDat.getOrderNumber());            
                    	writeMeansOfTransport(msgExpDat.getTransportMeansDeparture(), "Departure");
                    	if (msgExpDat.getTransportMeansDeparture() != null) {
                    		String temp = msgExpDat.getTransportMeansDeparture().getTransportationType();
                    		if (!Utils.isStringEmpty(temp)) {
                    			openElement("MeansOfTransport");
                    			setAttribute("TransportType", "Departure");                      
                    				writeElement("Mode", temp);
                    			closeElement();
                    		}
                    	}
                    	writeElement("ParticipantsCombination", msgExpDat.getDeclarantIsConsignor());
                        writePlaceofLoading(msgExpDat.getPlaceOfLoading());                		
                		writeElement("ReferenceNumber", msgExpDat.getReferenceNumber());
                		writeElement("Remark", msgExpDat.getAnnotation());                		
                		writeShipmentPeriod(msgExpDat.getLoadingTime());                		
                		writeTypeOfDeclaration("", msgExpDat.getTypeOfPermit());
                		
                        if (msgExpDat.getGoodsItemList() != null) {
                        	for (int i = 0; i < msgExpDat.getGoodsItemList().size(); i++) {
                        		writeGoodsItemList((MsgExpDatPos) msgExpDat.getGoodsItemList().get(i));
                        	}  
                        }
                      closeElement(); // ExportPrenotification
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
	   	//writeParty("Consignee", argument.getConsignee(), null, null);
	   	writeCommodityCode(argument.getCommodityCode());    	
    	if (argument.getPackagesList() != null) {
    		for (int i = 0; i < argument.getPackagesList().size(); i++) {   
    			Packages tmpPack = new Packages();
            	tmpPack = (Packages) argument.getPackagesList().get(i);
            	writePackaging(tmpPack, ""); 
    		}                		
    	}    	         
    	if (argument.getExportRefundItem() != null) {
    		String amount = argument.getExportRefundItem().getAmount();
    		String unit = argument.getExportRefundItem().getUnitOfMeasurement();
    		if (!(Utils.isStringEmpty(amount) && Utils.isStringEmpty(unit)) ) {
    			//writeExportRefundItem(msgUidsPos.getExportRefundItem());
    			openElement("ExportRestitutionItem");    			
    				writeElement("Amount", amount);  
    				writeElement("Measurement", unit);
    			closeElement();
    		}
    	}	
	closeElement();
	}

}    	
