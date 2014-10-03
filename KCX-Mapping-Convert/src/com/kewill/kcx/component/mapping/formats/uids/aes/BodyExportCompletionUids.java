/*
 * Function    : BodyExportCompletionUids.java
 * Title       :
 * Date        : 23.09.2008
 * Author      : Kewill CSF / Heise
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Completion) kiff-export.xls 
 * Changes:
 * -----------
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;

public class BodyExportCompletionUids extends UidsMessage {	

    private MsgExpEnt  msgExpEnt = new MsgExpEnt();
  
    //StringWriter xmlOutputString = new StringWriter();
   
    public BodyExportCompletionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpEnt getMsgExpEnt() {
		return msgExpEnt;
	}

	public void setMsgExpEnt(MsgExpEnt msgExpEnt) {
		this.msgExpEnt = msgExpEnt;
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
                	openElement(msgExpEnt.getMsgName());
                	    //EI20090609: writeContact(msgExpEnt.getClerk());
                	//  writeContact(msgExpEnt.getContact()); EI20090609
                		writeParty("Consignee", msgExpEnt.getConsignee());
            			writeParty("Declarant", msgExpEnt.getDeclarant());
                        writeCustomsOffices(msgExpEnt.getCustomsOfficeExport(), 
                                            msgExpEnt.getCustomsOfficeForCompletion(), "", "");
            			writeStringToDateTime("DateOfAdditionalDeclaration", msgExpEnt.getCompletionTime());
            			writeElement("DocumentReferenceNumber", msgExpEnt.getUCRNumber());
            			writeIncoTerms(msgExpEnt.getIncoTerms());
            			writeElement("LocalReferenceNumber", msgExpEnt.getOrderNumber());
                        writeMeansOfTransportInland(msgExpEnt.getTransportInland());                        
                        writeMeansOfTransport(msgExpEnt.getTransportBorder(), "Border");
                        writeElement("ReferenceNumber", msgExpEnt.getReferenceNumber());                        
                        writeTransaction(msgExpEnt.getBusiness()); 
                                                                         
                        if (msgExpEnt.getGoodsItemList() != null) {
                        	int listSize = msgExpEnt.getGoodsItemList().size();
                        	for (int i = 0; i < listSize; i++) {                         	
                        		writeGoodsItemList((MsgExpEntPos) msgExpEnt.getGoodsItemList().get(i));
                        	}  
                        }
	                	closeElement(); //msgExpEnt.msgName
	                closeElement(); // Export
                closeElement(); // Submit
        	closeElement(); // soap:Body
        } catch (XMLStreamException e) {            
            e.printStackTrace();
        } 
    }

	private void writeGoodsItemList(MsgExpEntPos argument) throws XMLStreamException {
		if (argument == null) {
		    return;
		}
		
	openElement("GoodsItem");
	    ////EI - CompletionAufbau: 
        ////ItemNumber,OriginCountry,OriginRegion,Procedure,StatisticalValue,StatisticalQuantity,PreviousDocument
	    
	 	writeElement("ItemNumber", argument.getItemNumber());
	 	writeParty("Consignee", argument.getConsignee());
	 	writeElement("OriginRegion", argument.getOriginFederalState());	            	
        
	 	if (argument.getStatistic() != null) {
			writeElement("StatisticalValue", argument.getStatistic().getStatisticalValue());
			writeElement("StatisticalQuantity", argument.getStatistic().getAdditionalUnit());
			writeElement("StatisticalBaseValue", argument.getStatistic().getValue());
			writeElement("StatisticalBaseCurrency", argument.getStatistic().getCurrency());
	   	}
	   	
	 	if (argument.getPreviousDocumentList() != null) {    	
    		for (int i = 0; i < argument.getPreviousDocumentList().size(); i++) {
            	writePreviousDocument((PreviousDocument) argument.getPreviousDocumentList().get(i));
            	//closeElement();
    		}
	 	}
	closeElement();
	}	

}    	
    	
    	
