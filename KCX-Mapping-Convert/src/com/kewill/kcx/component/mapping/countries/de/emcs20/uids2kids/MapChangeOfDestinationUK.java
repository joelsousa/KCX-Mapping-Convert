/*
 * Function    : MapChangeOfDestinationUK.java
 * Titel       :
 * Date        : 04.05.2010
 * Author      : Kewill CSF /iwaniuk
 * Description : Mapping of UIDS-Format into KIDS-Format of ChangeOfDestination message.
 *             :
 * Parameters  :

 * Changes
 * -------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgChangeOfDestination;
import com.kewill.kcx.component.mapping.formats.kids.emcs20.BodyChangeOfDestinationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: EMCS<br>
 * Created		: 15.10.2012<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of ChangeOfDestination message.
 * 
 * @author  iwaniuk
 * @version 2.0.00
 */

public class MapChangeOfDestinationUK extends KidsMessage {
	
    private BodyChangeOfDestinationKids body;
    private MsgChangeOfDestination 		message;

	public MapChangeOfDestinationUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
													throws XMLStreamException {
		message = new MsgChangeOfDestination(parser);
        
		this.kidsHeader = kidsHeader;
		this.encoding = encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
	    this.writer = writer;
        try {
            body = new BodyChangeOfDestinationKids(writer);
            
            writeStartDocument(encoding, "1.0");            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            message.parse(HeaderType.UIDS);
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(message);            
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
