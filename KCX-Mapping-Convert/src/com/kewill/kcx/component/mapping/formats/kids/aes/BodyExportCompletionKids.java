/*
 * Function    : BodyExportCopletionKids.java
 * Titel       :
 * Date        : 10.10.2008
 * Author      : Kewill CSF Schmidt / iwaniuk
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff_export.xls and kiff-export-reply.xls
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Description : replaced MsgKids with MsgExpEnt
 *             : consignee from Position
 *             : writeCodeElement(...) for OriginFederalState
 * 
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */
 
package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class BodyExportCompletionKids extends KidsMessage {
	
	private MsgExpEnt msgExpEnt;	
	List<String> pdflist; 
	
    public BodyExportCompletionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public MsgExpEnt getMsgKids() {
		return msgExpEnt;
	}

	public void setMsgKids(MsgExpEnt argument) {
		this.msgExpEnt = argument;
	}    
    
    public void writeBody() {    	
        try {
            openElement("soap:Body");
                openElement("Completion");
                    openElement("GoodsDeclaration");                    
                    	writeElement("UCRNumber", msgExpEnt.getUCRNumber());
                    	writeDateTimeToString("CompletionTime", msgExpEnt.getCompletionTime());                    	
                        writeElement("ShipmentNumber", msgExpEnt.getShipmentNumber()); //EI20100714 for NL/Heinz
                        writeElement("ReferenceNumber", msgExpEnt.getReferenceNumber());
                		// Christine Kron 12.08.2010
                		// build up the "DeclarationNumber" 8 digits for application NL
                		String refnr = msgExpEnt.getReferenceNumber();
                		int declnum = Utils.getExistingDeclNum(kidsHeader.getReceiver(), refnr);
                		if (declnum != 0) { 
                			msgExpEnt.setDeclarationNumber(declnum);	
                			// Christine Kron 12.08.2010 added because mandatory in NL application
                    		writeElement("DeclarationNumber", msgExpEnt.getDeclarationNumber() + "");
                		}
                        writeElement("OrderNumber", msgExpEnt.getOrderNumber());                        
                        //EI20090609:writeElement("Clerk", msgExpEnt.getClerk());
                        writeContact("Contact", msgExpEnt.getContact());  //EI20090609
                        writeMeansOfTransport("Inland", msgExpEnt.getTransportInland());                      
                        writeMeansOfTransport("Border", msgExpEnt.getTransportBorder()); //EI20081027
                        writeElement("CustomsOfficeExport", msgExpEnt.getCustomsOfficeExport());
                        writeElement("CustomsOfficeForCompletion", msgExpEnt.getCustomsOfficeForCompletion());
                        writeBusiness(msgExpEnt.getBusiness());   
                        if (msgExpEnt.getLoadingTime() != null) {                      //EI20100714 for NL/Heinz
                        	String endTime = msgExpEnt.getLoadingTime().getEndTime();
                        	if (!Utils.isStringEmpty(endTime)) {
                        		openElement("LoadingTime");
                        		writeElement("EndTime", endTime);
                        		closeElement();
                        	}
                        }
                       	//EI20090422: writeForwarder(msgExpEnt.getShipper() );                        
                        writeParty("Declarant", msgExpEnt.getDeclarant());
                        writeParty("Consignee", msgExpEnt.getConsignee());
                        writeIncoTerms(msgExpEnt.getIncoTerms());                       
        
                        if (msgExpEnt.getGoodsItemList() != null) {                        	
                        	for (int i = 0; i < msgExpEnt.getGoodsItemList().size(); i++) {
                        		writeGoodsItemList((MsgExpEntPos) msgExpEnt.getGoodsItemList().get(i));
                        	}
                        }
                        	
                    closeElement(); // GoodsDeclaration
                closeElement(); // ExportDeclaration
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
    private void writeGoodsItemList(MsgExpEntPos argument) throws XMLStreamException {
    	if (argument == null) return;
    	
    openElement("GoodsItem");
    	writeElement ("ItemNumber", argument.getItemNumber());      	    	
    	writeCodeElement ("OriginFederalState", argument.getOriginFederalState(), "KCX0023");  //EI20090424	    	
    	writeStatistic(argument.getStatistic(), "ExpEam");       	
    	//EI20090424: writeParty("Consignee", msgExpEnt.getConsignee());    
    	writeParty("Consignee", argument.getConsignee());  //EI20090424
    	writeContainerNumberList(argument.getContainers());                //EI20100714 for NL/Heinz    	
    	if (argument.getPreviousDocumentList() != null) {     		
    		for (int i = 0; i < argument.getPreviousDocumentList().size(); i++) {        		               	
                writePreviousDocument( (PreviousDocument)argument.getPreviousDocumentList().get(i) ); 
    		} 	
    	}    		 

    closeElement();
    }         
}

