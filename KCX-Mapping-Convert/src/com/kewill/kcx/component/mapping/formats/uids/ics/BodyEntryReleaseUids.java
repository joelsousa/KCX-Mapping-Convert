package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryRelease;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyEntryReleaseUids.<br>
 * Created		: 2011.02.03<br>
 * Description	: UIDS Body format of ICSEntryRelease
 * 				: (KIDS: ICSEntryRelease, IE330) 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyEntryReleaseUids extends UidsMessageICS {

	private MsgEntryRelease message;	

    public BodyEntryReleaseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEntryRelease getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntryRelease argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit"); 
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911");
        	openElement("ICS");
    		openElement("ICSEntryRelease");
    		
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());
            	writeElement("GeneratedLRN", message.getGeneratedLRN());
            	writeElement("CommercialReferenceNumber", message.getShipmentNumber());            
            	writeElement("MRN", message.getMrn());             	
            	writeTrader("EntryCarrier", message.getCarrier());            	           
            	writeElement("OfficeOfActualEntry", message.getCustomsOfficeFirstEntry());             	
            	writeFormattedDateTime("PresentationDateAndTime", message.getDateOfPresentation(),
            			message.getDateOfPresentationFormat(), EFormat.ST_DateTimeTZ);	
            	writeFormattedDateTime("ControlDate", message.getControlResultDate(),
            			message.getControlResultDateFormat(), EFormat.ST_Date);	
            	
            	if (message.getImportOperationList() != null) {
 		           for (ImportOperationRelease importOperation : message.getImportOperationList()) {
 		                writeImportOperation(importOperation);
 		           }
 	            }
                	
            closeElement();
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
  
}
