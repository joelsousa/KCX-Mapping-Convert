package com.kewill.kcx.component.mapping.formats.kids.base;

/*
 * Function    : BodyInternalStatus.java
 * Title       :
 * Date		   : 03.09.2008
 * Author      : CSF GmbH / Heise
 *
 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNck;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyInternalStatus extends KidsMessage {

	private MsgExpNck msgExpNck;
	private String tagName = "";
    
    public BodyInternalStatus(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
        	
//        	if (kidsHeader.getMethod().equals("EMCS")) {
            if (kidsHeader.getProcedure().equals("EMCS")) {
                tagName = "EMCS";
        	} else {
        		tagName = "GoodsDeclaration";
        	}
        	
            openElement("soap:Body");                
                if (tagName.equals("EMCS")) {
                    openElement("EMCS");
                    openElement("InternalStatusInformation");
                } else {
                	openElement("InternalStatusInformation");
                	openElement("GoodsDeclaration");
                }
                        writeElement("ReferenceNumber", msgExpNck.getReferenceNumber());
                        if (msgExpNck.getOrderNumber() != null) {
                        	writeElement("OrderNumber", msgExpNck.getOrderNumber());
                        }
                        writeElement("CorrectionNumber", msgExpNck.getCorrectionNumber());
                        writeElement("TemporaryUCR", msgExpNck.getTemporaryUCR());
                        writeElement("DateNewStatus", msgExpNck.getDateNewStatus());
                        writeElement("TimeNewStatus", msgExpNck.getTimeNewStatus());
                        writeElement("NewStatus", msgExpNck.getNewStatus());
                   
                	closeElement(); // GoodsDeclaration                   
                closeElement(); // InternalStatusInformation     //AK20081124
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /* ***************************************
     * Getters and Setters
     * ***************************************/
    
	public MsgExpNck getMsgExpNck() {
		return msgExpNck;
	}

	public void setMsgExpNck(MsgExpNck msgExpNck) {
		this.msgExpNck = msgExpNck;
	}

}
