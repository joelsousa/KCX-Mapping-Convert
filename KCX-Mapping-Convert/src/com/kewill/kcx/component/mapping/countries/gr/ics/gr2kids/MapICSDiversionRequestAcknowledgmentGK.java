package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC325A;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDiversionRequestAcknowledgmentKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 27.06.2011<br>
 * Description	: ICSDiversionRequestAcknowledgement.
 * 
 * @author	Frederick T.
 * @version	1.0.00
 *
 */
public class MapICSDiversionRequestAcknowledgmentGK extends KidsMessageCY {
	
	private BodyDiversionRequestAcknowledgmentKids 	body		= null;
	private MsgCC325A								msgGreece	= null;
	
	public MapICSDiversionRequestAcknowledgmentGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
		msgGreece		= new MsgCC325A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		
		try {
			body	= new BodyDiversionRequestAcknowledgmentKids(writer);
			
			writeStartDocument(encoding, "1.0");			
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
//			msgGreece.parse(HeaderType.GREECE);
			msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgDiversionRequestAcknowledgment kidsMsg = getKidsFromGreece();
			
			if (msgGreece.getHeahea() != null) {
				getCommonFieldsDTO().setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
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
        //MS20110816: this.kidsHeader.setInReplyTo(msgGreece.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgGreece.getCorIdeMES25()); 
        setInReplyTo(msgGreece.getCorIdeMES25(), "GR");        
		setMapping();
	}
	
	private MsgDiversionRequestAcknowledgment getKidsFromGreece() {
		MsgDiversionRequestAcknowledgment msgKids = new MsgDiversionRequestAcknowledgment();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			msgKids.setRegistrationDateTime(msgGreece.getHeahea().getRegistrationDateTime());
		}
		
		if (msgGreece.getCusOfffEnt730() != null) {
			msgKids.setCustomsOfficeOfFirstEntry(msgGreece.
					getCusOfffEnt730().getCustomsOfficeOfFirstEntry());
		}
		
		if (msgGreece.getTraReqDiv456() != null) {
			msgKids.setSubmitter(setParty(msgGreece.getTraReqDiv456(), "SubmitterAddress"));			
		}
		
		return msgKids;
	}
}
