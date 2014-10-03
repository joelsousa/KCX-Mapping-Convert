/*
 * Function    : MapExEntKK.java
 * Titel       :
 * Date        : 26.03.2009
 * Author      : Kewill CSF /meﬂer
 * Description : Mapping of KIDS-Format into KIDS-Format of ManualTermination
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
package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportCompletionKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Modul		: MapExpEntKK<br>
 * Erstellt		: 26.03.2009<br>
 * Beschreibung	: Mapping of KIDS-Format into KIDS-Format of Completion message.
 * 
 * @author meﬂer
 * @version 1.0.00
 */
public class MapExpEntKK extends KidsMessage {
	private BodyExportCompletionKids 	body   = null;
	private MsgExpEnt 					msgExpEnt;

	public MapExpEntKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgExpEnt = new MsgExpEnt(parser);
		
        this.encoding = encoding;
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);
			body  = new BodyExportCompletionKids(writer);
            kidsHeader.setWriter(writer);
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();
            
            msgExpEnt.parse(HeaderType.KIDS);
            
            body.setKidsHeader(kidsHeader);
            body.setMsgKids(msgExpEnt); 
            body.writeBody();

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            Utils.log("(Completion getMessage) Msg = " + xmlOutputString.toString());
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }
		return xmlOutputString.toString();
	}  
}
