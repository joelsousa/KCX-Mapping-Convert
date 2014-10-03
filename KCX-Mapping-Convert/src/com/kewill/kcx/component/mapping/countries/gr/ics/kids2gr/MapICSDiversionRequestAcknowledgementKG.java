package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSDiversionRequestAcknowledgementKG<br>
 * Created : 08.06.2011<br>
 * Description : Mapping of KIDS-Format into Greece-Format of
 * ICSDiversionRequestAcknowledgement message (IE325).
 * 
 * @author Frederick T.
 * @version 1.0.00
 */

public class MapICSDiversionRequestAcknowledgementKG extends GreeceMessage {

	private MsgDiversionRequestAcknowledgment msgKids;
	
	public MapICSDiversionRequestAcknowledgementKG(XMLEventReader parser, String encoding)
			throws XMLStreamException {
		msgKids = new MsgDiversionRequestAcknowledgment(parser);
		this.encoding = encoding;

	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);

			openElement("CC325A");
			msgKids.parse(HeaderType.KIDS);

			writeESDBody();
			writeHeahea();
			
			openElement("CUSOFFFENT730");
				writeElement("RefNumCUSOFFFENT731", msgKids.getCustomsOfficeOfFirstEntry());
			closeElement();
			
			writeAddressSub(msgKids.getSubmitter().getAddress());
			
			closeElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return xmlOutputString.toString();

	}
	private void writeESDBody() {
		try {
			openElement("MESSAGE");
			writeElement("MesSenMES3", getKidsHeader().getTransmitter());
			writeElement("MesRecMES6", getKidsHeader().getReceiver());
			writeElement("DatOfPreMES9", getKidsHeader().getYear()
					+ getKidsHeader().getMonth() + getKidsHeader().getDay());
			writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
			writeElement("MesIdeMES19", getKidsHeader().getMessageID());
			writeElement("MesTypMES20", getKidsHeader().getMessageName());
			closeElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	private void writeHeahea() {
		try {
			if (msgKids != null) {
				openElement("HEAHEA");
					writeElement("DivRefNumHEA119", msgKids.getReferenceNumber());
					writeElement("RegDatTimHEA125" , msgKids.getRegistrationDateTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	public void writeAddressSub(Address argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement("TRAREQDIV456");
		writeElement("NamTRAREQDIV457", argument.getName());
		writeElement("StrAndNumTRAREQDIV458", argument.getStreet()
				+ argument.getHouseNumber());
		writeElement("CouTRAREQDIV459", argument.getCountry());
		writeElement("PosCodTRAREQDIV460", argument.getPostalCode());
		writeElement("CitTRAREQDIV46", argument.getCity());
		
		writeElement("TINTRAREQDIV463", msgKids.getSubmitter().getPartyTIN()
				.getTIN());
		closeElement();
	}
	
}
