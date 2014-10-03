package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSEntrySummaryDeclarationRejectedKC<br>
 * Created : 23.06.2011<br>
 * Description : Mapping of KIDS-Format into Cyprus-Format of
 * ICSEntrySummaryDeclarationRejectedKC message (IE351).
 * 
 * @author LassiterCaviles.
 * @version 1.0.00
 */
public class MapICSEntrySummaryDeclarationRejectedKC extends CyprusMessage {
	
	private MsgEntrySummaryDeclarationRejected msgKids;
	
	public MapICSEntrySummaryDeclarationRejectedKC(XMLEventReader parser, String encoding)
			throws XMLStreamException {
		msgKids = new MsgEntrySummaryDeclarationRejected(parser);
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

			openElement("CC316A");
        		setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
        		setAttribute("xmlns", "http://www.eurodyn.com");
        		setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			
        		writeHeaderFields();
        		writeHeahea();
			
			if (msgKids.getFunctionalErrorList() != null) {
				for (FunctionalError error : msgKids.getFunctionalErrorList()) {
					writeFunctionalError(error);
				}
			}

			closeElement();
			
			writer.writeEndDocument();			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return xmlOutputString.toString();

	}
	
	private void writeHeahea() {
		try {
			if (msgKids != null) {
				openElement("HEAHEA316");
				writeElement("RefNumHEA4", msgKids.getReferenceNumber());
				writeElement("DecRejReaHEA252", msgKids.getDeclarationRejectionReason());
				writeElement("DecRejReaHEA252LNG", msgKids.getDeclarationRejectionReasonLNG());
				writeElement("DecRejDatTimHEA116", msgKids.getDeclarationRejectionDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeFunctionalError(FunctionalError error) {
		try {
			if (msgKids != null) {
				openElement("FUNERRER1316");
				writeElement("ErrTypER11", error.getErrorType());
				writeElement("ErrPoiER12", error.getErrorPointer());
				writeElement("ErrReaER13", error.getErrorReason());
				writeElement("OriAttValER14", error.getOriginalAttributeValue());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
