package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSICSDiversionRequestAcknowledgementKC<br>
 * Created : 01.06.2011<br>
 * Description : Mapping of KIDS-Format into Cyprus-Format of
 * ICSDiversionRequestAcknowledgement message (IE325).
 * 
 * @author LassiterCaviles.
 * @version 1.0.00
 */

public class MapICSDiversionRequestAcknowledgementKC  extends CyprusMessage {

	private MsgDiversionRequestAcknowledgment msgKids;
	
	public MapICSDiversionRequestAcknowledgementKC(XMLEventReader parser, String encoding)
			throws XMLStreamException {
		msgKids = new MsgDiversionRequestAcknowledgment(parser);
		this.encoding = encoding;

	}
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);

            writeStartDocument(encoding, "1.0");    // MS20110629
            
            msgKids.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());  // MS20110629 
            getCommonFieldsDTO().setTargetMessageType("CC325A");                    // MS20110629   

			openElement("CC325A");
				setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
				setAttribute("xmlns", "http://www.eurodyn.com");
				setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

				writeHeaderFields(); //EI20110708
				writeHeahea();
				writeElement("RefNumCUSOFFFENT731", msgKids.getCustomsOfficeOfFirstEntry());
				writeAddressSub(msgKids.getSubmitter());
			
			closeElement();

			writer.writeEndDocument();           
            writer.flush();
            writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return xmlOutputString.toString();

	}
	private void writeESDBody() {
		try {
			openElement("MesSenMES3");
			    // MS20110629 Begin 
//    			writeElement("MesSenMES3", getKidsHeader().getTransmitter());
//    			writeElement("MesRecMES6", getKidsHeader().getReceiver());
//    			writeElement("DatOfPreMES9", getKidsHeader().getYear()
//    					+ getKidsHeader().getMonth() + getKidsHeader().getDay());
//    			writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
//    			writeElement("MesIdeMES19", getKidsHeader().getMessageID());
//    			writeElement("MesTypMES20", getKidsHeader().getMessageName());
                writeHeaderFields();
                // MS20110629 End
			closeElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	private void writeHeahea() {
		try {
			if (msgKids != null) {
				openElement("HEAHEA351");
					writeElement("DivRefNumHEA119", msgKids.getReferenceNumber());
					writeElement("RegDatTimHEA125" , msgKids.getRegistrationDateTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	public void writeAddressSub(Party submitter) throws XMLStreamException {
		if (submitter == null) {
			return;
		}
		if (submitter.isEmpty()) {
			return;
		}
		try {	
		openElement("NamTRAREQDIV457");
			if (submitter.getAddress() != null) {
				writeElement("NamTRACARENT604", submitter.getAddress().getName());
				writeElement("StrAndNumTRAREQDIV458", submitter.getAddress().getStreet()
						+ submitter.getAddress().getHouseNumber());
				writeElement("CouTRAREQDIV459", submitter.getAddress().getCountry());
				writeElement("PosCodTRAREQDIV460", submitter.getAddress().getPostalCode());
				writeElement("CitTRE1", submitter.getAddress().getCity());
			
			if (submitter.getPartyTIN() != null) { 
				writeElement("TINTRE1", msgKids.getSubmitter().getPartyTIN()
						.getTIN());
				}
			}
		closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
