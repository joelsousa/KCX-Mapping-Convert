package com.kewill.kcx.component.mapping.formats.uids.edec.aus;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;


/*
 * Funktion    : BodyExportCaeUids.java
 * Titel       :
 * Erstellt    : 22.10.2008
 * Author      : Marcus Messer 
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Cancelation) kiff-export.xls 
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
public class BodyExportCaeUids extends UidsMessage {
	
    private MsgExpCae msgExpCae;
    
    public BodyExportCaeUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                    openElement("Export");
                    	openElement("Cancelation");
                    		writeElement("TypeOfDocument", msgExpCae.getTypeOfDocument());
                    		writeElement("RegistrationNumber", msgExpCae.getRegistrationNumber());
                    		writeElement("TypeOfAnnulment", msgExpCae.getTypeOfAnnulment());
                    		writeElement("ReasonOfAnnulment", msgExpCae.getReasonOfAnnulment());
                    		writeElement("DecisionFlag", msgExpCae.getDecisionFlag());
                        closeElement(); // Cancelation
                    closeElement(); // Export
                closeElement(); // Submit
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
