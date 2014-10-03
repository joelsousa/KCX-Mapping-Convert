package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpUrg;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	   : Export/aes<br>
 * Created     : 04.10.2012<br>
 * Description : Uids body of RemindCompletion.
 * 			   : V21 - neu
 * 
 * @author iwaniuk
 * @version 2.1.00
 *
 */
public class BodyRemindCompletionUids extends UidsMessageV21 {
	
    private MsgExpUrg message;
       
    public BodyRemindCompletionUids(XMLStreamWriter writer) {
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
                openElement("RemindCompletion");                           	
                    writeElement("DocumentReferenceNumber", message.getUCRNumber());                        	
                    writeElement("ReferenceNumber", message.getReferenceNumber());  
                    if (!Utils.isStringEmpty(message.getCustomsOfficeForCompletion())) {
                    	openElement("CustomsOffices");
                    		writeElement("OfficeOfAdditionalDeclarationExport", message.getCustomsOfficeForCompletion());
                    	closeElement(); 
                    }
                    writeFormattedDateTime("DateOfReminder", message.getDateOfReminder(),   
                    			message.getDateOfReminderFormat(), EFormat.ST_Date);	
                    writeFormattedDateTime("ResponseUntil", message.getDateOfLatestPossibleReply(),   
                    			message.getDateOfLatestFormat(), EFormat.ST_Date);
                    writeParty("Declarant", message.getDeclarant());                                       
                    writeParty("DeclarantRepresentative", message.getAgent());
                    writeParty("Subcontractor", message.getSubcontractor()); 
                    writeParty("Exporter", message.getConsignor());
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

	public MsgExpUrg getMessage() {
		return message;
	}

	public void setMessage(MsgExpUrg message) {
		this.message = message;
	}
}
