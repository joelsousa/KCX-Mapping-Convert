package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExportRejection;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
* Module	   : EMCS<br>
* Created      : 12.05.2010<br>
* Description  : Contains Message Structure with fields used in Uids EMCSRejection message.
*              : V20 - DateTime format was changed, Trader.Addres.StreetAndNumber now in two Tags
*                 
* @author iwaniuk
* @version 2.0.00
*/

public class BodyExportRejectionUids extends UidsMessageEMCS20 {
	
    private MsgExportRejection  message = new MsgExportRejection();
   
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
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSRejectionOfEAADForExport");                         
            	writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());  //EI20111111            	 
                //writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());
            	writeFormattedDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance(),
            			 		EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                    //EI20111017
                writeExportCrossCheckingDiagnoses(message.getReferenceNumber(), message.getDocumentReferenceNumber(),
                             	message.getCancelExport(), message.getDiagnosisList()); 
                writeRejection(message.getRejectionDateAndTime(), message.getRejectionReasonCode()); 
                writeCAadValList(message.getFunctionalErrorList());                 
                writeTrader("ConsigneeTrader", message.getConsignee());  //EI20110819 V20 new Tag             
            closeElement(); 
            closeElement(); // Export
            closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
