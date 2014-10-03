package com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationRejectedKF<br>
 * Created		: 28.12.2010<br>
 * Description	: Mapping of Fedex-Format into KIDS-Format of ICSEntrySummaryDeclarationRejected message.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationRejectedKF extends FedexMessage {
	private MsgEntrySummaryDeclarationRejected	msgKids;
	
	public MapICSEntrySummaryDeclarationRejectedKF(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids			= new MsgEntrySummaryDeclarationRejected(parser);
		this.encoding	= encoding;
	}
	
	public String getMessage() {
		StringWriter		xmlOutputString	= new StringWriter();
		XMLOutputFactory	factory			= XMLOutputFactory.newInstance();
		
		try {
			writer					= factory.createXMLStreamWriter(xmlOutputString);
			FedexHeader	fedexHeader	= new FedexHeader(writer);
			
			openElement("MessageOperateur");
				setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            	openElement("Messages");
						openElement("Message");
							fedexHeader.mapHeaderKidsToFedex(getKidsHeader(), fedexHeader, "MessageIE316");
							fedexHeader.writeHeader();
							
							//writeHeader(getKidsHeader());
							msgKids.parse(HeaderType.KIDS);
							getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
							writeESDBody();
						closeElement();
					closeElement();
				closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
			
			//Utils.log("ICS FedexMessage = " + xmlOutputString.toString());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	private void writeESDBody() {
		try {
			openElement("MessageBody");
				openElement("CC316A");
					openElement("CCxxxA");
						writeElement("MesSenMES3", getKidsHeader().getTransmitter());
						writeElement("MesRecMES6", getKidsHeader().getReceiver());
						writeElement("DatOfPreMES9", getKidsHeader().getYear() + 
										getKidsHeader().getMonth() + getKidsHeader().getDay());
						writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
						writeElement("IntConRefMES11", getKidsHeader().getMessageID());
						//writeElement("MesIdsMES19", msgKids.getShipmentNumber);
						writeElement("MesTypMES20", getKidsHeader().getMessageName());
						writeElement("ComAccRefMES21", msgKids.getReferenceNumber());
					closeElement();
					
					writeHeahea(msgKids);
					writeFunctionalError(msgKids);
				closeElement();
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
//	private void writeHeader(KidsHeader kidsHeader) {
//		try {
//			openElement("EnveloppeMessage");
//				writeElement("SchemaID", kidsHeader.getMessageName());
//				writeElement("SchemaVersion", kidsHeader.getRelease());
//				writeElement("PartyID", kidsHeader.getTransmitter());
//				writeElement("TransactionID", kidsHeader.getMessageID());
//			closeElement();
//		} catch (XMLStreamException e) {
//			e.printStackTrace();
//		}
//	}
	
	private void writeHeahea(MsgEntrySummaryDeclarationRejected msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA");
					writeElement("RefNumHEA4", msg.getReferenceNumber());
					writeElement("DecRejReaHEA252", msg.getDeclarationRejectionReason());
					writeElement("DecRejReaHEA252LNG", msg.getDeclarationRejectionReasonLNG());
					writeElement("DecRejDatTimHEA116", msg.getDeclarationRejectionDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeFunctionalError(MsgEntrySummaryDeclarationRejected msg) {
		try {
			if (msg.getFunctionalErrorList() != null) {
				for (FunctionalError fcnErrItm : msg.getFunctionalErrorList()) {
					openElement("FUNERRER1");
						//AK20110321
						//writeElement("ErrTypER11", fcnErrItm.getErrorType());
						writeElement("ErrTypER11", "12");
						writeElement("ErrPoiER12", fcnErrItm.getErrorPointer());
						writeElement("ErrReaER13", fcnErrItm.getErrorReason());
						writeElement("OriAttValER14", fcnErrItm.getOriginalAttributeValue());
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
