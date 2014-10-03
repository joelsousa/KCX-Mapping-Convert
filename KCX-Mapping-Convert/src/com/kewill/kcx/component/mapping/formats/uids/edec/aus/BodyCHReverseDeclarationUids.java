package com.kewill.kcx.component.mapping.formats.uids.edec.aus;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
/*
 * Function    : BodyReverseDeclarationUids.java
 * Title       :
 * Date   	   : 10.09.2008
 * Author      : CSF GmbH / Krzoska
 * Description : Body output KIDS -> UIDS
 * -----------
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 29.04.2009
 * Label       :
 * Description : MsgKids replaced with MsgExpRelCH
 */
public class BodyCHReverseDeclarationUids extends UidsMessage {
	

    private MsgExpRelCH  msgExpRelCH = new MsgExpRelCH();
 
 
    public BodyCHReverseDeclarationUids(XMLStreamWriter writer) {
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
       		openElement("Submit");
       		setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
            openElement("Export");
            openElement("ReverseDeclaration");   
            	writeElement("ApplicationReference", msgExpRelCH.getDeclarationKind()); 
            	writeStringToDateTime("DateOfRelease", msgExpRelCH.getAcceptanceTime()); 
            	writeElement("LocalReferenceNumber", msgExpRelCH.getDeclarationNumberForwarder());
            	writeElement("ReferenceNumber", msgExpRelCH.getReferenceNumber());     
            	writeElement("RegistrationNumber", msgExpRelCH.getDeclarationNumberCustoms());
            	
            	String rc = msgExpRelCH.getRevisionCode();
            	String cr = msgExpRelCH.getCodeOfRelease();
            	if (!(Utils.isStringEmpty(rc) && Utils.isStringEmpty(cr))) {
            		openElement("HeaderExtensions");
            			writeElement("EDECRevisionFlag", rc);
                		writeElement("EDECReleaseFlag", cr);
            		closeElement();   
            	}
           closeElement(); // ReverseDeclaration
           closeElement(); // Export
           closeElement(); // Submit
           closeElement(); // soap:Body
		} catch (XMLStreamException e) {    
			e.printStackTrace();
		}	
	}
	
}    	
