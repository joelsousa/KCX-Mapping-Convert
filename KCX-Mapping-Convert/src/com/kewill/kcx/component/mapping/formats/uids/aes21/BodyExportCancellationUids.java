package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 16.07.2012<br>
 * Description	: Write Body of Export Cancellation message in UIDS format. 
 * 				: V21: new Tags
 * 
 * @author iwaniuk
 * @version 1.0.00
 */ 

public class BodyExportCancellationUids extends UidsMessageV21 {
	
    private MsgExpCan message;
    
    public BodyExportCancellationUids(XMLStreamWriter writer) {
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
                openElement("Cancelation");                    		
                	writeContact(message.getContact()); 
                    writeParty("Declarant", message.getDeclarant());                   //new for V21
                    writeParty("DeclarantRepresentative", message.getDeclarant());     //new for V21
                    writeCustomsOffices(message.getCustomsOfficeExport(), "", "", ""); //new for V21
                    writeElement("TypeOfDocument", message.getKindOfDeclaration());
                    writeStringToDateTime("DateOfAnnulment", message.getCancellationTime());
                    if (message.getCancellationInfo() != null) {
                    	writeElement("ReasonOfAnnulment", message.getCancellationInfo().getReason());
                    }
                    writeElement("ReferenceNumber", message.getReferenceNumber());
                    if (message.getCancellationInfo() != null) {
                    	writeElement("TypeOfAnnulment", message.getCancellationInfo().getKindOfCancellation());
                    }
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }

	public MsgExpCan getMessage() {
		return message;
	}

	public void setMessage(MsgExpCan message) {
		this.message = message;
	}
	
}
