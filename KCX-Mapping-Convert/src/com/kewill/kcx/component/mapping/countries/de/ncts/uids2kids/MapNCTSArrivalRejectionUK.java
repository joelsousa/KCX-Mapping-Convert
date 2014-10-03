package com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSArrivalRejectionKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSArrivalRejectionUK<br>
 * Created		: 08.26.2010<br>
 * Description	: Mapping of UIDS-Format into KIDS-Format of NCTSArrivalRejection message. 
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 */
public class MapNCTSArrivalRejectionUK extends KidsMessage {
	private BodyNCTSArrivalRejectionKids body;
	private MsgNCTSArrivalRejection message;
	
	public MapNCTSArrivalRejectionUK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
		throws XMLStreamException {
		
		message	= new MsgNCTSArrivalRejection(parser);
		
		this.kidsHeader	= kidsHeader;
		this.encoding	= encoding;
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer	= writer;
		
		try {
			body	= new BodyNCTSArrivalRejectionKids(writer);
			
			writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
					setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
					
					kidsHeader.writeHeader();
					
					message.parse(HeaderType.UIDS);
					getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
					
					body.setKidsHeader(kidsHeader);
					body.setMessage(message);
					body.writeBody();
				closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
