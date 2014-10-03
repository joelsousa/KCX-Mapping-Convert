package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpUrg;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	   : Export/aes<br>
 * Created     : 24.07.2012<br>
 * Description : Uids body of ExportReminder.
 * 			   : V21 - neu
 * 
 * @author iwaniuk
 * @version 2.1.00
 *
 */
public class BodyExportReminderUids extends UidsMessageV21 {
	
    private MsgExpUrg message;
       
    public BodyExportReminderUids(XMLStreamWriter writer) {
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
                openElement("ExportReminder");     //in xsd steht for UIDS:  "RemindCompletion"                      	
                    writeElement("DocumentReferenceNumber", message.getUCRNumber());                        	
                    writeElement("ReferenceNumber", message.getReferenceNumber());   
                    writeElement("OfficeOfAdditionalDeclarationExport", message.getCustomsOfficeForCompletion());
                    writeFormattedDateTime("DateOfReminder", message.getDateOfReminder(),   
                    			message.getDateOfReminderFormat(), EFormat.ST_Date);	
                    writeFormattedDateTime("DateOfLatestPossibleReply", message.getDateOfLatestPossibleReply(),   
                    			message.getDateOfLatestFormat(), EFormat.ST_Date);	                    	
                    writeParty("Exporter", message.getConsignor());
                    writeParty("Declarant", message.getDeclarant());
                    writeParty("DeclarantRepresentative", message.getAgent());
                    writeParty("Subcontractor", message.getSubcontractor());                    	
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
