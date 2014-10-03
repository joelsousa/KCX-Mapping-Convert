package com.kewill.kcx.component.mapping.countries.de.aes.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyCHReverseDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module		: MapCHReverseDeclarationKK<br>
 * Created		: 23.07.2012<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of CH Reverse Declaration message.
 * 				: replaced the MapCHReverseDeclarationKK_me, because MsgExpRelCH is defined (instead of Msg_Kids_old)
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapCHReverseDeclarationKK extends KidsMessage {
	//private BodyCHReverseDeclarationKids_me body   = null;
	private BodyCHReverseDeclarationKids body   = null;
	private MsgExpRelCH message;

	public MapCHReverseDeclarationKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgExpRelCH(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);
			body = new BodyCHReverseDeclarationKids(writer);
            kidsHeader.setWriter(writer);
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            kidsHeader.writeHeader();            
            message.parse(HeaderType.KIDS);  
            body.setMessage(message); 
            body.setKidsHeader(kidsHeader);
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
