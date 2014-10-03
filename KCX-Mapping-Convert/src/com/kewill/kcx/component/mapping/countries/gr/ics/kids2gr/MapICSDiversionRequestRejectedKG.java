package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSDiversionRequestRejectedKG<br>
 * Created : 08.06.2011<br>
 * Description : Mapping of KIDS-Format into Greece-Format of
 * ICSDiversionRequestRejected message (IE324).
 * 
 * @author Frederick T.
 * @version 1.0.00
 */

public class MapICSDiversionRequestRejectedKG extends GreeceMessage {

	private MsgDiversionRequestRejected msgKids;

	public MapICSDiversionRequestRejectedKG(XMLEventReader parser,
			String encoding) throws XMLStreamException {
		msgKids = new MsgDiversionRequestRejected(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);

			openElement("CC324A");
				msgKids.parse(HeaderType.KIDS);
				getCommonFieldsDTO().setReferenceNumber(
						msgKids.getReferenceNumber());
				writeESDBody();
				writeHeahea(msgKids);
				writeFunerrer(msgKids);
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
			if (getKidsHeader() != null) {
				openElement("MESSAGE");
				writeElement("MesSenMES3", getKidsHeader().getTransmitter());
				writeElement("MesRecMES6", getKidsHeader().getReceiver());
				writeElement("DatOfPreMES9", getKidsHeader().getYear()
						+ getKidsHeader().getMonth() + getKidsHeader().getDay());
				writeElement("TimOfPreMES10",
						getTime(getKidsHeader().getTime()));
				writeElement("MesIdeMES19", getKidsHeader().getMessageID());
				writeElement("MesTypMES20", getKidsHeader().getMessageName());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
	}

	private void writeHeahea(MsgDiversionRequestRejected msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA");
					writeElement("DivRefNumHEA119", msg.getReferenceNumber());
					writeElement("RejDatTimHEA126", msg
							.getDeclarationRejectionDateAndTime());
					writeElement("RejReaHEA127", msg
							.getDeclarationRejectionReason());
					writeElement("RejReaHEA128LNG", msg
							.getDeclarationRejectionReasonLNG());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeFunerrer(MsgDiversionRequestRejected msg) {
		try {
			if (msg.getFunctionalErrorList() != null) {
				openElement("FUNERRER1");
				for (FunctionalError functionalError : msg
						.getFunctionalErrorList()) {
					writeElement("ErrTypER11", functionalError.getErrorType());
					writeElement("ErrPoiER12", functionalError
							.getErrorPointer());
					writeElement("ErrReaER13", functionalError.getErrorReason());
					writeElement("OriAttValER14", functionalError
							.getOriginalAttributeValue());
				}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
