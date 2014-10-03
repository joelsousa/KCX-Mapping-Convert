/*
 * Function    : BodyDiversionRequestUids.java
 * Titel       :
 * Date        : 11.06.2010
 * Author      : Pete T
 * Description : 
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyDiversionRequestUids<br>
 * Created		: 2010.07.19<br>
 * Description	: UIDS-Body of ICSDiversionRequest.
 * 				: (KIDS: ICSDiversionRequest, IE323)
 * @author Pete T.
 * @version 1.0.00
 */
public class BodyDiversionRequestUids extends UidsMessageICS {

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
	            if (message.getSubmitter() != null) {
	            	openElement("Submitter");
	            		if (message.getSubmitter().getPartyTIN() != null) {
	            			writeElement("TIN", message.getSubmitter().getPartyTIN().getTIN());
	            		}
		                writeAddress("Address", message.getSubmitter().getAddress());
		                writeContact("Contact", message.getSubmitter().getContactPerson());
		            closeElement();
	            }
	            writeElement("OriginalOfficeOfFirstEntry", message.getDeclaredOfficeOfFirstEntry());
	            writeElement("OriginalCountryOfFirstEntry", message.getDeclaredCountryOfArrival()); 
	            writeElement("NewOfficeOfFirstEntry", message.getActualOfficeOfFirstEntry()); 
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
