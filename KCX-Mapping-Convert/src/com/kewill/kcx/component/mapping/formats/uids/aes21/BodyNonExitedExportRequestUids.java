package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpFup;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	   : Export/aes<br>
 * Created     : 24.07.2012<br>
 * Description : Uids body of NonExitedExportRequest.
 * 			   : V21 - eigentlich keine neuen Tags fuer Uids, aber die MsgExpFup habe die MsgExpNer erstezt
 * 
 * @author iwaniuk
 * @version 2.1.00
 *
 * Changes: AES22 new Tags
 */
public class BodyNonExitedExportRequestUids extends UidsMessageV21 {
	
    private MsgExpFup message;
       
    public BodyNonExitedExportRequestUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public void writeBody() {
        try {
        	openElement("soap:Body");
            openElement("Submit");                
                //EI20120917: setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	//EI20120917:
        		if (this.getCommonFieldsDTO() != null && 
        				!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {
        			setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
        		} else {
        			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        		}
        	
                openElement("Export");
                openElement("NonExitedExportRequest");  
                
                    //V21: es war in aes ein Dreh: /Kids:ReferenceNumber war Uids:DocumentReferenceNumber
                    //writeElement("ReferenceNumber", message.getDocumentReferenceNumber());                        	
                    writeElement("ReferenceNumber", message.getReferenceNumber());                    	
                    writeFormattedDateTime("ResponseUntil", message.getDateOfLatestPossibleReply(),   
                    			message.getDateOfLatestFormat(), EFormat.ST_Date);	
                    writeFormattedDateTime("DateOfInvestigation", message.getDateOfInquiry(),   
                    			message.getDateOfInquiryFormat(), EFormat.ST_Date);	
                    writeElement("RequestCode", message.getRequestCode());     	//EI20130808 AES22
                    writeFormattedDateTime("ResponseOfRequestUntil", message.getDateOfLatestResponseOfRequest(),   
                			message.getDateOfLatestResponseOfRequestFormat(), EFormat.ST_Date);	//EI20130808 AES22
                   
                    
                closeElement();  
                closeElement(); 
            closeElement(); 
            closeElement(); 
           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

	public MsgExpFup getMessage() {
		return message;
	}

	public void setMessage(MsgExpFup message) {
		this.message = message;
	}
}
