/*
 * Function    : BodyDiversionRequestKids.java
 * Titel       :
 * Date        : 09.06.2010
 * Author      : Pete T
 * Description : 
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyDiversionRequestKids<br>
 * Created		: 14.07.2010<br>
 * Description	: The body of the KIDS format of EntrySummaryDeclarationAmendmentAcceptance message.
 * 
 * @author	: Pete
 * @version 1.0.00
 */
public class BodyDiversionRequestKids extends KidsMessageICS {

	private MsgDiversionRequest message;	

    public BodyDiversionRequestKids(XMLStreamWriter writer) {
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
            openElement("ICSDiversionRequest");
            openElement("GoodsDeclaration"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
                writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
                //EI20110105: it is the only one case. that DateTime-Tag only Date-Format is
	            writeFormattedDate("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
	                    		message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_Date);                      
                writeElement("DeclaredCountryOfArrival", message.getDeclaredCountryOfArrival());
                if (message.getSubmitter() != null) {
                    writePartyTIN("Submitter", message.getSubmitter().getPartyTIN());
                    writePartyAddress("Submitter", message.getSubmitter());
                    writeContactPerson("Submitter", message.getSubmitter().getContactPerson());
                }
                writeElement("DeclaredOfficeOfFirstEntry", message.getDeclaredOfficeOfFirstEntry()); 
                writeElement("ActualOfficeOfFirstEntry", message.getActualOfficeOfFirstEntry()); 
                writeElement("InformationType", message.getInformationType());
                    	
                if (message.getImportOperationList() != null) {
	                for (ImportOperation importOperation : message.getImportOperationList()) {
	                   writeImportOperation(importOperation);
	                }
                }
                    	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
}