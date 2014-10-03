package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 19.10.2012<br>
 * Description	: Body of ICSDiversionRequest KIDS format.
 *              : (IE323)
 *  
 * @author krzoska
 * @version 2.0.00
 */

public class BodyDiversionRequestKids extends KidsMessageICS20 {

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
                //writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
                writeTransportMeansBorder(message.getMeansOfTransportBorder());   //EI20121025
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
                writeFormattedDate("DeclarationTime", message.getDeclarationTime(),    //new V20
                		message.getDeclarationTimeFormat(), EFormat.KIDS_DateTime);  
                writeElement("DeclarationPlace", message.getDeclarationPlace());       //new V20
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
