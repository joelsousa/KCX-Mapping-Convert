/*
 * Function    : BodyExportCancellationKids.java
 * Date        : 16.09.2008
 * Author      : Kewill CSF Schmidt / Heise
 * Description : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009, 27.04.
 * Label       :
 * Description : checked for V60 - no changes
 *
 * Author      : EI
 * Date        : 27.04.2009
 * Label       : EI20090427
 * Description : KCX-Code checked 
 * 
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk      
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Modul		: BodyExportCancellationKids<br>
 * Erstellt		: 16.09.1008<br>
 * Beschreibung	: Write Body of Export Cancellation message in KIDS format. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */ 
public class BodyExportCancellationKids extends KidsMessage {
	
	private MsgExpCan msgExpCan;
	
    public BodyExportCancellationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("Cancellation");
                    openElement("GoodsDeclaration");
                    	writeElement("KindOfDeclaration", msgExpCan.getTypeOfDocument());
                    	writeDateTimeToString("CancellationTime", msgExpCan.getDateOfAnnulment());
                    	openElement("CancellationInfo");
                    	    //EI20090427
                        	writeCodeElement("KindOfCancellation", msgExpCan.getTypeOfAnnulment(), "KCX0010");
                        	writeElement("Reason", msgExpCan.getReasonOfAnnulment());
                        closeElement(); // CancellationInfo
                        writeElement("DeclarationNumberCustoms", msgExpCan.getDeclarationNumberCustoms());
                        writeElement("ReferenceNumber", msgExpCan.getReferenceNumber());
                        //EI20090609: writeElement("Clerk", msgExpCan.getIdentity());
                        writeContact("Contact", msgExpCan.getContact()); //EI20090608 
                    closeElement(); // GoodsDeclaration
                closeElement(); // Cancellation
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpCan getMsgExpCan() {
		return msgExpCan;
	}

	public void setMsgExpCan(MsgExpCan msgExpCan) {
		this.msgExpCan = msgExpCan;
	}
    
}
