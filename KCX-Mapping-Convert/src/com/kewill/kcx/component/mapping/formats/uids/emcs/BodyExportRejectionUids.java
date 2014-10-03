package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExportRejection;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
* Module	  : EMCS<br>
* Created     : 12.05.2010<br>
* Description : Contains Message Structure with fields used in Uids EMCSRejection message.
*                 
* @author iwaniuk
* @version 1.0.00
*/

public class BodyExportRejectionUids extends UidsMessageEMCS {
	
    private MsgExportRejection  message = new MsgExportRejection();
    private String version = "";         //EI20110819
 
    public BodyExportRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExportRejection getMessage() {
		return message;
	}

	public void setMessage(MsgExportRejection message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSRejectionOfEAADForExport");   
            
            if (version.equals("10")) {             //EI20111017               	
                 writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());
                 writeExportCrossCheckingDiagnoses(message.getReferenceNumber(), message.getDocumentReferenceNumber(),
                        		message.getCancelExport(), message.getDiagnosisList(), version);  
                 writeRejection(message.getRejectionDateAndTime(), message.getRejectionReasonCode(), version); 
                 writeCAadValList(message.getFunctionalErrorList(), version); 
            } else { 
            	 writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());  //EI20111111
            	 //V20 - DateTime format changed
                 //writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());
            	 writeFormattedDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance(),
            			 		EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                    //EI20111017
                 writeExportCrossCheckingDiagnoses(message.getReferenceNumber(), message.getDocumentReferenceNumber(),
                             	message.getCancelExport(), message.getDiagnosisList(), version); 
                 writeRejection(message.getRejectionDateAndTime(), message.getRejectionReasonCode(), version); 
                 writeCAadValList(message.getFunctionalErrorList(), version); 
                 //V20 - new Tag ConsigneeTrader:
                 writeTrader("ConsigneeTrader", message.getConsignee(), version);  					//EI20110819 
             }  
                
            closeElement(); 
            closeElement(); // Export
            closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
