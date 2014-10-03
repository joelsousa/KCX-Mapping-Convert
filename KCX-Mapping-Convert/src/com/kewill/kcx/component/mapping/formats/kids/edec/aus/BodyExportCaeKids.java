package com.kewill.kcx.component.mapping.formats.kids.edec.aus;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;


/*
 * Funktion    : BodyExportCaeKids.java
 * Titel       :
 * Erstellt    : 22.10.2008
 * Author      : Kewill CSF / Marcus Messer 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
public class BodyExportCaeKids extends KidsMessage {
	
    private MsgExpCae msgExpCae;
    
    public BodyExportCaeKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
            openElement("soap:Body");                
                    	openElement("CancellationResponse");
                    		openElement("GoodsDeclaration");
                    		writeElement("DeclarationKind", msgExpCae.getTypeOfDocument());
                    		writeElement("DeclarationNumberCustoms", msgExpCae.getRegistrationNumber());
                    		writeElement("Reason", msgExpCae.getReasonOfAnnulment());
                    		writeElement("CancellationType", msgExpCae.getTypeOfAnnulment());
                    		writeElement("CancellationAcceptance", msgExpCae.getDecisionFlag());
                    	closeElement(); // GoodsDeclaration
                    closeElement(); // CancellationResponse           
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	public MsgExpCae getMsgExpCae() {
		return msgExpCae;
	}

	public void setMsgExpCae(MsgExpCae msgExpCae) {
		this.msgExpCae = msgExpCae;
	}	
}

