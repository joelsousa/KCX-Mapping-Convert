package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC325A;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestAcknowledgmentKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSDiversionRequestAcknowledgementCK (CC325A).<br>
 * Created		: 24.06.2011<br>
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MapICSDiversionRequestAcknowledgementCK extends KidsMessageCY {
	private BodyDiversionRequestAcknowledgmentKids	body		= null;
	private MsgCC325A msgCyprus	= null;	
	
	public MapICSDiversionRequestAcknowledgementCK(XMLEventReader parser, KidsHeader kidsHeader, 
			String encoding, CyprusHeader cyprusHeader) throws XMLStreamException {
		msgCyprus		= new MsgCC325A(parser);
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
					
					msgCyprus.parse(HeaderType.CYPRUS);
					
					mapKidsHeaderFromMessage();
					kidsHeader.writeHeader();
					
					MsgDiversionRequestAcknowledgment kidsMsg	= getKidsFromCyprus();
					
					if (msgCyprus.getHeahea() != null) {
						getCommonFieldsDTO().setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
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
		//MS20110816: this.kidsHeader.setInReplyTo(msgCyprus.getCorIdeMES25());
		//EI20140408;setInReplyTo(msgCyprus.getCorIdeMES25());
        setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");     //EI20140408);        
		setMapping();
	}
	
	private MsgDiversionRequestAcknowledgment getKidsFromCyprus() {
		MsgDiversionRequestAcknowledgment	msgKids	= new MsgDiversionRequestAcknowledgment();
		
		if (msgCyprus.getHeahea() != null) {
			msgKids.setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			msgKids.setRegistrationDateTime(msgCyprus.getHeahea().getRegistrationDateTime());
		}
		
		if (msgCyprus.getCusOfffEnt730() != null) {
			msgKids.setCustomsOfficeOfFirstEntry(msgCyprus.getCusOfffEnt730().getCustomsOfficeOfFirstEntry());
		}
		
		if (msgCyprus.getTraReqDiv456() != null) {
			msgKids.setSubmitter(setParty(msgCyprus.getTraReqDiv456(), "SubmitterAddress"));
			/* EI20110707
			if (msgKids.getSubmitter() != null) {
				Party party	= new Party("Submitter");
					msgKids.setSubmitter(party);
			}
			*/
		}
		
		return msgKids;
	}
}
