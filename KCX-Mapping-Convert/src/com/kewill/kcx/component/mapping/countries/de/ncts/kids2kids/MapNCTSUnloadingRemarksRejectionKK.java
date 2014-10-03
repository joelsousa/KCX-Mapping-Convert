package com.kewill.kcx.component.mapping.countries.de.ncts.kids2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingRemarksRejectionKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSUnloadingRemarksRejectionKK
 * Created		: 31.08.2010
 * Description	: mapping of KIDS format into UIDS format then to KIDS format of UnloadingRemarksRejection message
 * 				: both KIDS format should be the same.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class MapNCTSUnloadingRemarksRejectionKK extends KidsMessage {
	private BodyNCTSUnloadingRemarksRejectionKids body;
	private MsgNCTSUnloadingRemarksRejection message;
	
	/// CONSTRUCTOR(s)
		public MapNCTSUnloadingRemarksRejectionKK(XMLEventReader parser, 
				String encoding) throws XMLStreamException {
			message	= new MsgNCTSUnloadingRemarksRejection(parser);
			this.encoding	= encoding;
		}
	
	/// METHODS
		public String getMessage() {
			StringWriter xmlOutputString	= new StringWriter();
			XMLOutputFactory factory		= XMLOutputFactory.newInstance();
			
			try {
				writer	= factory.createXMLStreamWriter(xmlOutputString);
				body	= new BodyNCTSUnloadingRemarksRejectionKids(writer);
				
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
				
				Utils.log("NCTS UnloadingRemarksRejection KidsMessage = " + xmlOutputString.toString());
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			
			return xmlOutputString.toString();
		}
		
}
