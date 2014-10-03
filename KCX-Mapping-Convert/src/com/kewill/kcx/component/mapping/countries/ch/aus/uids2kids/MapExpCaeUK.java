/*
 * Function    : MapExpCaeUK.java
 * Titel       :
 * Date        : 22.10.2008
 * Author      : Kewill CSF / Marcus Messer
 * Description : Mapping of UIDS-Format into KIDS-Format 
 * 				 of CancelationResponse
 *  
 *  
 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.ch.aus.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyExportCaeKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapExpCaeUK<br>
 * Erstellt     : 22.10.2008<br>
 * Beschreibung : Mapping of UIDS-Format into KIDS-Format of 
 *                Export CancellationResponse message for CH.
 * 
 * @author Messer
 * @version 1.0.00
 */
public class MapExpCaeUK extends KidsMessage {
	
	private BodyExportCaeKids 	body   = null;
	private MsgExpCae 			msgExpCae;
	
	public MapExpCaeUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		msgExpCae = new MsgExpCae(parser);
        this.kidsHeader = kidsHeader;
        this.encoding = encoding;
	}
		
	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
	    
        try {
            body   = new BodyExportCaeKids(writer);
            
            kidsHeader = new KidsHeader(writer);
	            
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	           
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
            commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
            commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
            // commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
            commonFieldsDTO.setDirection(EKidsMessages.valueOf(kidsHeader.getMessageName()).getDirection());
            kidsHeader.setCommonFieldsDTO(commonFieldsDTO);		            
            
            kidsHeader.writeHeader();
	            
            msgExpCae.parse(HeaderType.UIDS);
            
            body.setMsgExpCae(msgExpCae);
            body.writeBody();
	            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
	            
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}


