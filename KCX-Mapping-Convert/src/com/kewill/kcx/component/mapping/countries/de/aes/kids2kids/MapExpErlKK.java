/*
 * Function    : MapExpErlKK.java
 * Titel       :
 * Date        : 27.03.2009
 * Author      : Kewill CSF /meﬂer
 * Description : Mapping of KIDS-Format into KIDS-Format of Export ManualTermination
 *             :
 * Parameters  :

 * Changes
 * -------------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyManualTerminationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul        : MapExpErlKK<br>
 * Erstellt     : 27.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format of ManualTermination message.
 * 
 * @author meﬂer
 * @version 1.0.00
 */
public class MapExpErlKK extends KidsMessage {
	private BodyManualTerminationKids 	body   = null;
	private MsgExpErl msgExpErl;

	public MapExpErlKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpErl = new MsgExpErl(parser);
	}	

	public String getMessage() {
		 StringWriter xmlOutputString = new StringWriter();
	     XMLOutputFactory factory = XMLOutputFactory.newInstance();
	     try {
	    	writer = factory.createXMLStreamWriter(xmlOutputString);
	    	body = new BodyManualTerminationKids(writer);
            kidsHeader.setWriter((writer)); 
            writeStartDocument(encoding, "1.0");

            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpErl.parse(HeaderType.UIDS);
            
            body.setKidsHeader(kidsHeader);
            body.setMessage(msgExpErl);
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
	}  
}
