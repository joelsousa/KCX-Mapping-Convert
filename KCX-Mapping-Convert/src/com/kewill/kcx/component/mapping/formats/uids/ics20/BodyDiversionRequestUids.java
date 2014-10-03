package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSDiversionRequest.
 * 				: (KIDS: ICSDiversionRequest, IE323)
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyDiversionRequestUids extends UidsMessageICS20 {

	private MsgDiversionRequest message;	

    public BodyDiversionRequestUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgDiversionRequest getMessage() {
		return message;
	}
	
	public void setMessage(MsgDiversionRequest argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit");
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
            openElement("ICS");
            openElement("ICSDiversionRequest");
            
	        	writeElement("LocalReferenceNumber", message.getReferenceNumber());  
	            writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());	            
		        writeFormattedDateTime("ExpectedDateOfArrival", message.getDeclaredDateOfArrival(),
		                message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);	            
		        writeTrader("Submitter", message.getSubmitter());        
	            writeElement("OriginalOfficeOfFirstEntry", message.getDeclaredOfficeOfFirstEntry());
	            writeElement("OriginalCountryOfFirstEntry", message.getDeclaredCountryOfArrival()); 
	            writeElement("NewOfficeOfFirstEntry", message.getActualOfficeOfFirstEntry());	            
	            writeFormattedDateTime("DeclarationDateAndTime", message.getDeclarationTime(),      //new V20
		                message.getDeclarationTimeFormat(), EFormat.ST_DateTimeTZ);	
	            writeElement("DeclarationPlace", message.getDeclarationPlace());	                //new V20
	            writeElement("InformationType", message.getInformationType());	                	
	            if (message.getImportOperationList() != null) {
		           for (ImportOperation importOperation : message.getImportOperationList()) {
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
