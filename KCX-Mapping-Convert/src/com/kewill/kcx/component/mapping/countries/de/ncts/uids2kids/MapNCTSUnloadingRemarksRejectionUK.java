package com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSUnloadingRemarksRejectionKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapNCTSUnloadingRemarksRejectionUK
 * Created		: 08.31.2010
 * Description	: mapping of UIDS format into KIDS format of NCTSUnloadingRemarksRejection message.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class MapNCTSUnloadingRemarksRejectionUK extends KidsMessage {
	private BodyNCTSUnloadingRemarksRejectionKids body;
	private MsgNCTSUnloadingRemarksRejection message;
	
	/// CONSTRUCTOR(s)
		public MapNCTSUnloadingRemarksRejectionUK(XMLEventReader parser, 
				KidsHeader kidsHeader, String encoding) throws XMLStreamException {
			message	= new MsgNCTSUnloadingRemarksRejection(parser);
			
			this.kidsHeader	= kidsHeader;
			this.encoding	= encoding;
		}
	
	/// METHODS
		public void getMessage(XMLStreamWriter writer) {
			this.writer	= writer;
			
			try {
				body	= new BodyNCTSUnloadingRemarksRejectionKids(writer);
				
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
