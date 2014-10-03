package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotification;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotificationPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72.MsgACL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsACP;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExpControlNotificationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: AES21<br>
 * Created		: 22.07.2013<br>
 * Description	: Mapping of FSS-Format ACL into KIDS-Format of ControlNotification.
 * 				: new in EXPORT21 == aber AES22
 * 
 * @author iwaniuk
 * @version 2.1.00
 * 
 */

public class MapACLToControlNotification extends KidsMessage {
	
	private MsgACL msgACL;
	private MsgControlNotification message;
	
	public MapACLToControlNotification() {
		message = new MsgControlNotification();
	}

	public void setMsgACR(MsgACL msgACL) {
    	this.msgACL = msgACL;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
    	if (msgACL.getAckSubset() == null) {
    		return;
    	}
		message.setReferenceNumber(msgACL.getAckSubset().getBeznr());
		message.setUCRNumber(msgACL.getAckSubset().getMrn());
		message.setTimeOfInspection(msgACL.getAckSubset().getCtldat());
		message.setKindOfInspection(msgACL.getAckSubset().getCtlart());
		message.setContact(msgACL.getAckSubset().getSbname());		
		message.setAnnotation(msgACL.getAckSubset().getText());				  
		  
		if (msgACL.getPosList() != null) {					
			for (TsACP acp : msgACL.getPosList()) {	
				if (acp != null) {
					MsgControlNotificationPos item = new MsgControlNotificationPos();
					item.setItemNumber(acp.getPosnr());					
					item.setAnnotation(acp.getText());
					item.setKindOfInspection(acp.getCtlart());					
					message.addItemList(item);
				}
			}	
		}
    }
	
	public String getMessage() {
    StringWriter xmlOutputString = new StringWriter();
    
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    try {
        writer = factory.createXMLStreamWriter(xmlOutputString);

        writeStartDocument(encoding, "1.0");
        openElement("soap:Envelope");
        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
        
        KidsHeader             header = new KidsHeader(writer);
        //header.setHeaderFields(msgAUP.getVorSubset());
        header.setHeaderFieldsFromHead(msgACL.getVorSubset(), msgACL.getHeadSubset());     //EI20121005
        header.setMessageName("ExportControlNotification");
       
        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(header.getReceiver());
        commonFieldsDTO.setCountryCode(header.getCountryCode());
        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
        header.setCommonFieldsDTO(commonFieldsDTO);
        
        header.writeHeader();
        
        BodyExpControlNotificationKids body = new BodyExpControlNotificationKids(writer);
        body.setMessage(message);
        body.setKidsHeader(header);

        getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
        body.writeBody();
        
        closeElement();  // soap:Envelope
        writer.writeEndDocument();
        
        writer.flush();
        writer.close();
        
        Utils.log("(MsgExportInvestigation getMessage) Msg = " + xmlOutputString.toString());
        
    } catch (XMLStreamException e) {
        
        e.printStackTrace();
    }

    
    return xmlOutputString.toString();
}

}
