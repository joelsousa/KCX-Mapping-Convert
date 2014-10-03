package com.kewill.kcx.component.mapping.formats.kids.edec.aus;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module		: MapCHReverseDeclarationKK<br>
 * Created		: 23.07.2012<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of CH Reverse Declaration message.
 * 				: replaced the MapCHReverseDeclarationKK_me, because MsgExpRelCH is defined (instead of Msg_Kids_old)
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyCHReverseDeclarationKids extends KidsMessage {
	
	
	private MsgExpRelCH msgExpRelCH = new MsgExpRelCH();
	
    public BodyCHReverseDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpRelCH getMsgKids() {
		return msgExpRelCH;
	}

	public void setMessage(MsgExpRelCH argument) {
		this.msgExpRelCH = argument;
	}

    public void writeBody() {
        try {
            openElement("soap:Body");
              openElement("ReverseDeclaration");
                 openElement("GoodsDeclaration");
                    writeElement("DeclarationKind", msgExpRelCH.getDeclarationKind());
                    writeElement("DeclarationNumberForwarder", msgExpRelCH.getDeclarationNumberForwarder());
                    writeElement("DeclarationNumberCustoms", msgExpRelCH.getDeclarationNumberCustoms()); 
                    if (msgExpRelCH.getAcceptanceTime() != null) {                    
                    	if (msgExpRelCH.getAcceptanceTime().length() <= 12) { //from FSS to KIDS
                    		writeElement("AcceptanceTime", msgExpRelCH.getAcceptanceTime()); 
                    	} else { //from UIDS to KIDS
                    		writeDateTimeToString("AcceptanceTime", msgExpRelCH.getAcceptanceTime());
                    	}
                    }
                    writeElement("RevisionCode", msgExpRelCH.getRevisionCode());
                    writeElement("CodeOfRelease", msgExpRelCH.getCodeOfRelease());                        
                    writeElement("ReferenceNumber", msgExpRelCH.getReferenceNumber());                       
                 closeElement(); // GoodsDeclaration                    
              closeElement(); // ReverseDeclaration
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
       
// ende der Klasse
}
