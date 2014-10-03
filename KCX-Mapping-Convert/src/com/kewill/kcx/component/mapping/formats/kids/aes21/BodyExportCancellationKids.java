package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module		: Export/aes<br>
 * Created		: 16.07.2012<br>
 * Description	: Write Body of Export Cancellation message in KIDS format. 
 * 				: V21: new Tags  
 * 
 * @author iwaniuk
 * @version 1.0.00
 */ 
public class BodyExportCancellationKids extends KidsMessageV21 {
	
	private MsgExpCan message;
	
    public BodyExportCancellationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Cancellation");
            openElement("GoodsDeclaration");
            	writeElement("KindOfDeclaration", message.getKindOfDeclaration());
                writeDateTimeToString("CancellationTime", message.getCancellationTime());
                if (message.getCancellationInfo() != null) {
                	openElement("CancellationInfo");                          		   //new for V21             	    
                        writeCodeElement("KindOfCancellation", message.getCancellationInfo().getKindOfCancellation(), "KCX0010");
                        writeElement("Reason", message.getCancellationInfo().getReason());
                    closeElement(); 
                }
                writeElement("DeclarationNumberCustoms", message.getDeclarationNumberCustoms());
                writeElement("ReferenceNumber", message.getReferenceNumber());                        
                writeContact("Contact", message.getContact()); 
                writeElement("CustomsOfficeExport", message.getCustomsOfficeExport()); //new for V21  
                writeParty("Declarant", message.getDeclarant());					   //new for V21 
                writeParty("Agent", message.getAgent());							   //new for V21 
                
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
