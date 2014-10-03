package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSAdvanceInterventionNotKG<br>
 * Created : 08.06.2011<br>
 * Description : Mapping of KIDS-Format into Greece-Format of
 * ICSdvanceInterventionNotification message (IE351).
 * 
 * @author Frederick T.
 * @version 1.0.00
 */
public class MapICSAdvancedInterventionNotKG extends GreeceMessage {

	private MsgAdvanceInterventionNot msgKids;

	public MapICSAdvancedInterventionNotKG(XMLEventReader parser, String encoding)
			throws XMLStreamException {
		msgKids = new MsgAdvanceInterventionNot(parser);
		this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);

			openElement("CC351A");
			msgKids.parse(HeaderType.KIDS);

			writeESDBody();
			writeHeahea();

			if (msgKids.getGoodsItemList() != null) {
				for (GoodsItemShort goodsItem : msgKids.getGoodsItemList()) {
					writeGoodsItem(goodsItem);
				}
			}

			writeAddressRep(msgKids.getRepresentative().getAddress());
			writeAddressPer(msgKids.getPersonLodgingSuma().getAddress());

			openElement("CUSOFFFENT730");
			writeElement("RefNumCUSOFFFENT731", msgKids
					.getCustomsOfficeFirstEntry());
			writeElement("ExpDatOfArrFIRENT733", msgKids
					.getDeclaredDateOfArrival());
			closeElement();

			writeAddressCar(msgKids.getCarrier().getAddress());

			if (msgKids.getCustomsInterventionList() != null) {
				for (CustomsIntervention customs : msgKids
						.getCustomsInterventionList()) {
					writeCustomsInterventions(customs);
				}
			}

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
				writeElement("RefNumHEA4", msgKids.getMrn());
				writeElement("DocNumHEA5", msgKids.getReferenceNumber());
				writeElement("TraModAtBorHEA76", msgKids
						.getMeansOfTransportBorder().getTransportMode());
				writeElement("NatHEA001", msgKids.getMeansOfTransportBorder()
						.getTransportationCountry());
				writeElement("IdeOfMeaOfTraCroHEA85", msgKids
						.getMeansOfTransportBorder().getTransportationNumber());
				writeElement("TotNumOfIteHEA305", msgKids
						.getTotalNumberPosition());
				writeElement("ComRefNumHEA", msgKids.getShipmentNumber());
				writeElement("ConRefNumHEA", msgKids.getConveyanceReference());
				writeElement("NotDatTimHEA104", msgKids
						.getNotificationDateTime());
				writeElement("DecRegDatTimHEA115", msgKids
						.getRegistrationDateTime());
				writeElement("DecSubDatTimHEA118", msgKids
						.getAcceptedDateTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeGoodsItem(GoodsItemShort goodsItem) {
		try {
			if (goodsItem != null) {
				openElement("GOOITEGDS");
				writeElement("IteNumGDS7", goodsItem.getItemNumber());
				writeElement("ComRefNumGIM1", goodsItem.getShipmentNumber());

				if (goodsItem.getDocumentList() != null) {
					for (IcsDocument document : goodsItem.getDocumentList()) {
						writeDocument(document);
					}
				}
				if (goodsItem.getContainersList() != null) {
					for (String container : goodsItem.getContainersList()) {
						openElement("CONNR2");
						writeElement("ConNumNR21", container);
						closeElement();
					}
				}

				if (goodsItem.getMeansOfTransportBorderList() != null) {
					for (TransportMeans border : goodsItem
							.getMeansOfTransportBorderList()) {
						writeTransport("IDEMEATRAGI970", border);
					}
				}

				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeDocument(IcsDocument document) throws XMLStreamException {

		if (document == null) {
			return;
		}
		if (document.isEmpty()) {
			return;
		}
		openElement("PRODOCDC2");
		writeElement("DocTypDC21", document.getType());
		writeElement("DocRefDC23", document.getReference());
		writeElement("DocRefDCLNG", document.getReferenceLng());
		closeElement();
	}

	public void writeTransport(String tag, TransportMeans argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement(tag);
		writeElement("NatlDEMEATRAGI973", argument.getTransportationCountry());
		writeElement("IdeMeaTraGIMEATRA971", argument.getTransportationNumber());
		closeElement();
	}

	public void writeAddressRep(Address argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement("TRAREP");
		writeElement("NamTRE1", argument.getName());
		writeElement("StrAndNumTRE1", argument.getStreet()
				+ argument.getHouseNumber());
		writeElement("PosCodTRE1", argument.getPostalCode());
		writeElement("CitTRE1", argument.getCity());
		writeElement("CouCodTRE1", argument.getCountry());
		writeElement("TINTRE1", msgKids.getRepresentative().getPartyTIN()
				.getTIN());
		closeElement();
	}

	public void writeAddressPer(Address argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement("PERLODSUMDEC");
		writeElement("NamPLD1", argument.getName());
		writeElement("StrAndNumPLD1", argument.getStreet()
				+ argument.getHouseNumber());
		writeElement("PosCodPLD1", argument.getPostalCode());
		writeElement("CitPLD1", argument.getCity());
		writeElement("CouCodPLD1", argument.getCountry());
		writeElement("TINPLD1", msgKids.getPersonLodgingSuma().getPartyTIN()
				.getTIN());
		closeElement();
	}

	public void writeAddressCar(Address argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}

		openElement("TRACARENT601");
		writeElement("NamTRACARENT604", argument.getName());
		writeElement("StrNumTRACARENT607", argument.getStreet()
				+ argument.getHouseNumber());
		writeElement("PstCodTRACARENT606", argument.getPostalCode());
		writeElement("CtyTRACARENT603", argument.getCity());
		writeElement("CouCodTACARENT605", argument.getCountry());
		writeElement("TINTRACARENT602", msgKids.getCarrier().getPartyTIN()
				.getTIN());
		closeElement();
	}

	public void writeCustomsInterventions(CustomsIntervention argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("CUSINT632");
		writeElement("IteNumConCUSINT668", argument.getItemNumber());
		writeElement("CusIntCodCUSINT665", argument.getCode());
		writeElement("CusIntTexCUSINT666", argument.getText());
		writeElement("CusIntTexCUSINT667LNG", argument.getLng());
		closeElement();
	}
}
