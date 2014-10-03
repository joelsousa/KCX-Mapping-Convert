package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC325A;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestAcknowledgmentKids;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSDiversionRequestAcknowledgementMK (CC325A).<br>
 * Created		: 20.08.2013<br>
 * 
 * @author	krzoska
 * @version	1.0.00
 *
 */

public class MapICSDiversionRequestAcknowledgementMK extends KidsMessageMT {
	private BodyDiversionRequestAcknowledgmentKids	body		= null;
	private MsgCC325A msgMalta	= null;	
	
	public MapICSDiversionRequestAcknowledgementMK(XMLEventReader parser, KidsHeader kidsHeader, 
			String encoding) throws XMLStreamException {
		msgMalta		= new MsgCC325A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer	= writer;
		
		try {
			body	= new BodyDiversionRequestAcknowledgmentKids(writer);
			
			writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
					setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
					
					msgMalta.parse(HeaderType.CYPRUS);
					
					mapKidsHeaderFromMessage();
					kidsHeader.writeHeader();
					
					MsgDiversionRequestAcknowledgment kidsMsg	= getKidsFromMalta();
					
					if (msgMalta.getHeahea() != null) {
						getCommonFieldsDTO().setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
					}
					
					body.setKidsHeader(kidsHeader);
					body.setMessage(kidsMsg);
					body.writeBody();
					
				closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void mapKidsHeaderFromMessage() {
        setInReplyTo(msgMalta.getCorIdeMES25());

		setMapping();
	}
	
	private MsgDiversionRequestAcknowledgment getKidsFromMalta() {
		MsgDiversionRequestAcknowledgment	msgKids	= new MsgDiversionRequestAcknowledgment();
		
		if (msgMalta.getHeahea() != null) {
			msgKids.setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			msgKids.setRegistrationDateTime(msgMalta.getHeahea().getRegistrationDateTime());
		}
		
		if (msgMalta.getCusOfffEnt730() != null) {
			msgKids.setCustomsOfficeOfFirstEntry(msgMalta.getCusOfffEnt730().getCustomsOfficeOfFirstEntry());
		}
		
		if (msgMalta.getTraReqDiv456() != null) {
			msgKids.setSubmitter(setParty(msgMalta.getTraReqDiv456(), "SubmitterAddress"));
		}
		
		return msgKids;
	}
}
