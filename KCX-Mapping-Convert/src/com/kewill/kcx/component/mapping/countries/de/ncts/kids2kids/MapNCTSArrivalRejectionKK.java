package com.kewill.kcx.component.mapping.countries.de.ncts.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSArrivalRejectionKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSArrivalRejectionKK<br>
 * Created		: 08.25.2010<br>
 * Description	: Mapping of KIDS-Format into KIDS-Format of NCTSArrivalRejection message. 
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 */
public class MapNCTSArrivalRejectionKK extends KidsMessage {
	
	private BodyNCTSArrivalRejectionKids body;
	private MsgNCTSArrivalRejection message;
	
	public MapNCTSArrivalRejectionKK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message	= new MsgNCTSArrivalRejection(parser);
		this.encoding	= encoding;
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			body	= new BodyNCTSArrivalRejectionKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			kidsHeader.setWriter(writer);
			kidsHeader.writeHeader();
					
			message.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
					
			body.setKidsHeader(kidsHeader);
			body.setMessage(message);
			body.writeBody();

			closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
			
			Utils.log("NCTS ArrivalRejection KidsMessage = " + xmlOutputString.toString());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
}
