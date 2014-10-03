package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSDiversionRequestRejectedKC<br>
 * Created : 01.06.2011<br>
 * Description : Mapping of KIDS-Format into Cyprus-Format of
 * ICSDiversionRequestRejecte message (IE322).
 * 
 * @author Gene B.
 * @version 1.0.00
 */

public class MapICSDiversionRequestRejectedKC extends CyprusMessage {

	private MsgDiversionRequestRejected msgKids;

	public MapICSDiversionRequestRejectedKC(XMLEventReader parser,
			String encoding) throws XMLStreamException {
		msgKids = new MsgDiversionRequestRejected(parser);
		this.encoding = encoding;
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);

            writeStartDocument(encoding, "1.0");    // MS20110629
            
            msgKids.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
            getCommonFieldsDTO().setTargetMessageType("CC324A");                    // MS20110629   
            
			openElement("CC324A");
				setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
				setAttribute("xmlns", "http://www.eurodyn.com");
				setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");            
			
				writeHeaderFields();
				writeBody();
			closeElement();

			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return xmlOutputString.toString();
	}

	private void writeBody() {	
		if (msgKids == null) {
			return; 
		}
		writeHeahea();
		writeFunerrer();
	}

	private void writeHeahea() {
		if (msgKids == null) {
			return; 
		}
		try {			
			openElement("HEAHEA324");
				writeElement("DivRefNumHEA119", msgKids.getReferenceNumber());
				writeElement("RejDatTimHEA126", msgKids.getDeclarationRejectionDateAndTime());
				writeElement("RejReaHEA127", msgKids.getDeclarationRejectionReason());
				writeElement("RejReaHEA128LNG", msgKids.getDeclarationRejectionReasonLNG());
			closeElement();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeFunerrer() {
		if (msgKids == null) {
			return; 
		}
		if (msgKids.getFunctionalErrorList() == null) {
			return;
		}
		if (msgKids.getFunctionalErrorList().isEmpty()) {
			return;
		}
		try {			
			openElement("FUNERRER1324");
				for (FunctionalError functionalError : msgKids.getFunctionalErrorList()) {
					if (functionalError != null) {
						writeElement("ErrTypER11", functionalError.getErrorType());
						writeElement("ErrPoiER12", functionalError.getErrorPointer());
						writeElement("ErrReaER13", functionalError.getErrorReason());
						writeElement("OriAttValER14", functionalError.getOriginalAttributeValue());
					}
				}
			closeElement();			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
