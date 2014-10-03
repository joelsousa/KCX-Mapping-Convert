package com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.ncts.BodyNCTSArrivalRejectionUids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSArrivalRejectionKU<br>
 * Created		: 08.25.2010<br>
 * Description	: Mapping of KIDS-Format into UIDS-Format of NCTSArrivalRejection message. 
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 */
public class MapNCTSArrivalRejectionKU extends UidsMessage {

	private BodyNCTSArrivalRejectionUids body;
	private MsgNCTSArrivalRejection message;
	
	public MapNCTSArrivalRejectionKU(XMLEventReader parser, String encoding) throws XMLStreamException {
		message			= new MsgNCTSArrivalRejection(parser);
		this.encoding	= encoding;
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			uidsHeader	= new UidsHeader(writer);
			body		= new BodyNCTSArrivalRejectionUids(writer);
				
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			mapKidsToUidsHeader();
							
			uidsHeader.writeHeader(getCommonFieldsDTO());
							
			message.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
							
			body.setUidsHeader(uidsHeader);
			body.setMessage(message);
			body.writeBody();
			closeElement();
			writer.writeEndDocument();
				
			writer.flush();
			writer.close();
				
			Utils.log("NCTS ArrivalRejection UidsMessage = " + xmlOutputString.toString());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
}
