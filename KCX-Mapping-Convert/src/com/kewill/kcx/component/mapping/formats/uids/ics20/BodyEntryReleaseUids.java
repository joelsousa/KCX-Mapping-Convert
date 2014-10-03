package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntryRelease;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20.<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS Body of ICSEntryRelease ( IE330) 
 * 				: new for V20: TransportAtBorder, PersonLodgingSumA, Representative, ExpectedArivalDateAndTime,
 *              : StatusInformation  
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyEntryReleaseUids extends UidsMessageICS20 {

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
            	writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());  //new for V20
            	writeTrader("EntryCarrier", message.getCarrier());
            	writeTrader("PersonLodgingSumA", message.getPersonLodgingSuma());          //new for V20 
            	writeTrader("Representative", message.getRepresentative());                //new for V20
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
            	writeFormattedDateTime("ExpectedArivalDateAndTime", message.getDeclaredDateOfArrival(), //new for V20 
            			message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);
            	writeStatusInformation(message.getStatusInformation());         //new for V20 
                	
            closeElement();
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
  
}
