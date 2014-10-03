package com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSDeclarationAmendmentAcknowledgmentKF<br>
 * Created		: 30.12.2010<br>
 * Description	: Mapping of Fedex-Format into KIDS-format of ICSDeclarationAmendmentAcknowledgment message.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MapICSEntrySummaryDeclarationAmendmentRejectionKF extends FedexMessage {
	private MsgEntrySummaryDeclarationAmendmentRejection msgKids;
	
	public MapICSEntrySummaryDeclarationAmendmentRejectionKF(XMLEventReader parser, 
			String encoding) throws XMLStreamException {
		msgKids			= new MsgEntrySummaryDeclarationAmendmentRejection(parser);
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
							fedexHeader.mapHeaderKidsToFedex(getKidsHeader(), fedexHeader, "MessageIE305");
							fedexHeader.writeHeader();
								
								//writeHeader(getKidsHeader());
							msgKids.parse(HeaderType.KIDS);
							getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
							writeESDBody();
						closeElement();
					closeElement();
			//closeElement();
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
				openElement("CC305A");
					writeElement("MesSenMES3", getKidsHeader().getTransmitter());
					writeElement("MesRecMES6", getKidsHeader().getReceiver());
					writeElement("DatOfPreMES9", getKidsHeader().getYear() + 
									getKidsHeader().getMonth() + getKidsHeader().getDay());
					writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
					writeElement("IntConRefMES11", getKidsHeader().getMessageID());
					//writeElement("MesIdsMES19", msgKids.getShipmentNumber);
					writeElement("MesTypMES20", getKidsHeader().getMessageName());
					writeElement("ComAccRefMES21", msgKids.getReferenceNumber());
					writeHeahea(msgKids);
				closeElement();
				
				writeFunctionalError(msgKids);
				writeTraderRepresentative(msgKids);
				writePersonLodgingSuma(msgKids);
				writeCustomsOfficeFirst(msgKids);
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeHeahea(MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA");
					writeElement("DocNumHEA5", msg.getMrn());
					writeElement("DatTimAmeHEA113", msg.getAmendmentDateAndTime());
					writeElement("AmeRejDatTimHEA112", msg.getAmendmentRejectionDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeFunctionalError(MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			if (msg.getFunctionalErrorList() != null) {
				for (FunctionalError fcnErrItm : msg.getFunctionalErrorList()) {
					openElement("FUNERRER1");
						writeElement("ErrTypER11", fcnErrItm.getErrorType());
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
	
	private void writeTraderRepresentative(MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			//omitting empty tag TRAREP AK20110524
			if (msg.getRepresentative() != null && (msg.getRepresentative().getAddress() != null ||
					msg.getRepresentative().getPartyTIN() != null)) {
				openElement("TRAREP");
				if (msg.getRepresentative().getAddress() != null) {
					writeElement("NamTRE1", msg.getRepresentative().getAddress().getName());
					writeElement("StrAndNumTRE1", Utils.checkNull(msg.getRepresentative().getAddress().getStreet()) + " " + 
							Utils.checkNull(msg.getRepresentative().getAddress().getHouseNumber()));
					writeElement("PosCodTRE1", msg.getRepresentative().getAddress().getPostalCode());
					writeElement("CitTRE1", msg.getRepresentative().getAddress().getCity());
					writeElement("CouCodTRE1", msg.getRepresentative().getAddress().getCountry());
				}
				if (msg.getRepresentative().getPartyTIN() != null) {
						writeElement("TINTRE1", msg.getRepresentative().getPartyTIN().getTIN());						
				}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writePersonLodgingSuma(MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			if (msg.getPersonLodgingSuma() != null) {
				openElement("PERLODSUMDEC");
				if (msg.getPersonLodgingSuma().getAddress() != null) {
					writeElement("NamPLD1", msg.getPersonLodgingSuma().getAddress().getName());
					writeElement("StrAndNumPLD1", Utils.checkNull(msg.getPersonLodgingSuma().getAddress().getStreet()) + " " + 
							Utils.checkNull(msg.getPersonLodgingSuma().getAddress().getHouseNumber()));
					writeElement("PosCodPLD1", msg.getPersonLodgingSuma().getAddress().getPostalCode());
					writeElement("CitPLD1", msg.getPersonLodgingSuma().getAddress().getCity());
					writeElement("CouCodPLD1", msg.getPersonLodgingSuma().getAddress().getCountry());
				}
				if (msg.getPersonLodgingSuma().getPartyTIN() != null) {
					writeElement("TINPLD1", msg.getPersonLodgingSuma().getPartyTIN().getTIN());					
				}
			}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCustomsOfficeFirst(MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			if (msg.getCustomsOfficeFirstEntry() != null) {
				openElement("CUSOFFFENT730");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
