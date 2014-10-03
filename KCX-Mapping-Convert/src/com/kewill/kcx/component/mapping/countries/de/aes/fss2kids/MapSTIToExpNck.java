/*
 * Function    : MapSTIToExpNck.java
 * Title       :
 * Date        : 04.09.2008
 * Author      : Kewill CSF / SH
 * Description : Mapping of FSS-Format STI into KIDS-Format of InternalStatusInformation
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids;


import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNck;
import com.kewill.kcx.component.mapping.formats.fss.base.messages.MsgSTI;
import com.kewill.kcx.component.mapping.formats.kids.base.BodyInternalStatus;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapSTIToExpNck<br>
 * Erstellt		: 04.09.2008<br>
 * Beschreibung	: Mapping of FSS-Format STI into KIDS-Format of InternalStatusInformation.
 * 
 * @author heise
 * @version 5.0.00
 */
public class MapSTIToExpNck extends KidsMessage {
	
	private MsgSTI msgSTI;
	private MsgExpNck msgExpNck;
	
	public MapSTIToExpNck() {
		msgExpNck = new MsgExpNck();
	}

	public void setMsgSTI(MsgSTI msgSTI) {
    	this.msgSTI = msgSTI;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
    	
		msgExpNck.setCorrectionNumber(msgSTI.getStiSubset().getKorant());
		msgExpNck.setReferenceNumber(msgSTI.getStiSubset().getBeznr());
		msgExpNck.setDateNewStatus(msgSTI.getStiSubset().getDatum());
		msgExpNck.setNewStatus(msgSTI.getStiSubset().getStatus());
		if (msgSTI.getAnrSubset() != null) {
			msgExpNck.setOrderNumber(msgSTI.getAnrSubset().getAufnr());
		}
		msgExpNck.setTemporaryUCR(msgSTI.getStiSubset().getArbnr());
		msgExpNck.setTimeNewStatus(msgSTI.getStiSubset().getZeit());
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
	        header.setHeaderFields(msgSTI.getVorSubset());
	        header.setMessageName("InternalStatusInformation");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
	        header.writeHeader();
	        
	        BodyInternalStatus body   = new BodyInternalStatus(writer);
	        body.setMsgExpNck(msgExpNck);
	        body.setKidsHeader(header);

	        getCommonFieldsDTO().setReferenceNumber(msgExpNck.getReferenceNumber());
	        body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        Utils.log("(MsgInternalStatus getMessage) Msg = " + xmlOutputString.toString());
    	
	    } catch (XMLStreamException e) {
	        
	        e.printStackTrace();
	    }
	
	    
	    return xmlOutputString.toString();
	}

}
