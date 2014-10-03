package com.kewill.kcx.component.mapping.countries.de.ics.uids2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAmendmentAcceptanceKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapEntrySummaryDeclarationAcceptanceUK<br>
 * Created		: 15.07.2010<br>
 * Description	: Mapping of UIDS format into KIDS format of EntrySummaryDeclarationAcceptanceUK message.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 */
public class MapDeclarationAmendmentAcceptedUK extends KidsMessage {
	private BodyEntrySummaryDeclarationAmendmentAcceptanceKids body;
	private MsgEntrySummaryDeclarationAmendmentAcceptance message;
	
	/// CONSTRUCTOR(s)
		public MapDeclarationAmendmentAcceptedUK(XMLEventReader parser,
				KidsHeader kidsHeader, String encoding) throws XMLStreamException {
			message	= new MsgEntrySummaryDeclarationAmendmentAcceptance(parser);
			
			this.kidsHeader	= kidsHeader;
			this.encoding	= encoding;
		}
	
	/// METHODS
		public void getMessage(XMLStreamWriter writer) {
			this.writer	= writer;
			
			try {
				body	= new BodyEntrySummaryDeclarationAmendmentAcceptanceKids(writer);
				
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
