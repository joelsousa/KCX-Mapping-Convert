package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSAdvanceInterventionNotKC<br>
 * Created : 01.06.2011<br>
 * Description : Mapping of KIDS-Format into Cyprus-Format of
 * ICSdvanceInterventionNotification message (IE351).
 * 
 * @author LassiterCaviles.
 * @version 1.0.00
 */

public class MapICSAdvancedInterventionNotKC extends CyprusMessage {

	private MsgAdvanceInterventionNot msgKids;

	public MapICSAdvancedInterventionNotKC(XMLEventReader parser, String encoding)
			throws XMLStreamException {
		msgKids = new MsgAdvanceInterventionNot(parser);
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
            getCommonFieldsDTO().setTargetMessageType("CC351A");                    // MS20110629   

			openElement("CC351A");
				setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
				setAttribute("xmlns", "http://www.eurodyn.com");
				setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            		
				//EI20110708:writeESDBody();
				writeHeaderFields();  //EI20110708
				writeHeahea();

				if (msgKids.getGoodsItemList() != null) {
				for (GoodsItemShort goodsItem : msgKids.getGoodsItemList()) {
					writeGoodsItem(goodsItem);
				}
				}

				writeAddressRep(msgKids.getRepresentative());
			
				writeAddressPer(msgKids.getPersonLodgingSuma());

				openElement("CUSOFFFENT730351");
				writeElement("RefNumCUSOFFFENT731", msgKids.getCustomsOfficeFirstEntry());
				writeElement("ExpDatOfArrFIRENT733", msgKids.getDeclaredDateOfArrival());
				closeElement();

				writeAddressCar(msgKids.getCarrier());

				if (msgKids.getCustomsInterventionList() != null) {
				for (CustomsIntervention customs : msgKids.getCustomsInterventionList()) {
					writeCustomsInterventions(customs);
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

	private void writeESDBody() {
		try {
			openElement("MESSAGE351");
                writeHeaderFields();              
			closeElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeHeahea() {
		try {
			if (msgKids != null) {
				openElement("HEAHEA351");
				writeElement("RefNumHEA4", msgKids.getMrn());
				writeElement("DocNumHEA5", msgKids.getReferenceNumber());
				writeElement("TraModAtBorHEA76", msgKids.getMeansOfTransportBorder().getTransportMode());
				writeElement("NatHEA001", msgKids.getMeansOfTransportBorder().getTransportationCountry());
				writeElement("IdeOfMeaOfTraCroHEA85", msgKids.getMeansOfTransportBorder().getTransportationNumber());
				writeElement("TotNumOfIteHEA305", msgKids.getTotalNumberPosition());
				writeElement("ComRefNumHEA", msgKids.getShipmentNumber());
				writeElement("ConRefNumHEA", msgKids.getConveyanceReference());
				writeElement("NotDatTimHEA104", msgKids.getNotificationDateTime());
				writeElement("DecRegDatTimHEA115", msgKids.getRegistrationDateTime());
				writeElement("DecSubDatTimHEA118", msgKids.getAcceptedDateTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeGoodsItem(GoodsItemShort goodsItem) {
		if (goodsItem == null) {
			return;
		}
		try {
			
			openElement("GOOITEGDS351");
				writeElement("IteNumGDS7", goodsItem.getItemNumber());
				writeElement("ComRefNumGIM1", goodsItem.getShipmentNumber());

				if (goodsItem.getDocumentList() != null) {
					for (IcsDocument document : goodsItem.getDocumentList()) {
						writeDocument(document);
					}
				}
				if (goodsItem.getContainersList() != null) {
					for (String container : goodsItem.getContainersList()) {
						openElement("CONNR2351");
							writeElement("ConNumNR21", container);
						closeElement();
					}
				}

				if (goodsItem.getMeansOfTransportBorderList() != null) {
					for (TransportMeans border : goodsItem.getMeansOfTransportBorderList()) {
						writeTransport("IDEMEATRAGI970351", border);
					}
				}
			closeElement();
			
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
		openElement("PRODOCDC2351");
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

	public void writeAddressRep(Party representative) throws XMLStreamException {
		if (representative == null) {
			return;
		}
		if (representative.isEmpty()) {
			return;
		}
		try {
			//EI20130819: openElement("TRAREP351");
			writeTraderRep(representative, "351");  //EI20110707
			/*EI20110707
			if (representative.getAddress() != null) {
				writeElement("NamTRE1", representative.getAddress().getName());
				writeElement("StrNumTRACARENT607", representative.getAddress().getStreet()
						+ representative.getAddress().getHouseNumber());
				writeElement("PosCodTRE1", representative.getAddress().getPostalCode());
				writeElement("CitTRE1", representative.getAddress().getCity());
				writeElement("CouCodTRE1", representative.getAddress().getCountry());
			}
			
			if (representative.getPartyTIN() != null) {
				writeElement("TINTRE1", representative.getPartyTIN().getTIN());
			}
			*/
			//EI20130819: closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeAddressPer(Party argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		try {
			//EI20130819: openElement("PERLODSUMDEC");
				//EI20130819: writeTraderPer(argument, "351");    //EI20110707
				writeTraderPer(argument, "PERLODSUMDEC");    //EI20130819
			
				/* EI20110707 
				if (argument.getAddress() != null) {
					writeElement("NamPLD1", argument.getAddress().getName());
					writeElement("StrAndNumPLD1", argument.getAddress().getStreet()
							+ argument.getAddress());
					writeElement("PosCodPLD1", argument.getAddress().getPostalCode());
					writeElement("CitPLD1", argument.getAddress().getCity());
					writeElement("CouCodPLD1", argument.getAddress().getCountry());
				}
				if (argument.getPartyTIN() != null) {
					writeElement("TINTRE1", argument.getPartyTIN().getTIN());
				}
				*/
			//EI20130819: closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeAddressCar(Party argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		try {
			openElement("TRACARENT601351");
				writeTraderCar(argument, "351");  //EI20110707 
				/*
				if (argument.getAddress() != null) {
					writeElement("NamTRACARENT604", argument.getAddress().getName());
					writeElement("StrNumTRACARENT607", argument.getAddress().getStreet()
							+ argument.getAddress().getHouseNumber());
					writeElement("PstCodTRACARENT606", argument.getAddress().getPostalCode());
					writeElement("CtyTRACARENT603", argument.getAddress().getCity());
					writeElement("CouCodTACARENT605", argument.getAddress().getCountry());
				}
				if (argument.getPartyTIN() != null) {
					writeElement("TINTRACARENT602", argument.getPartyTIN().getTIN());
				}
				*/
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeCustomsInterventions(CustomsIntervention argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("CUSINT632351");
			writeElement("IteNumConCUSINT668", argument.getItemNumber());
			writeElement("CusIntCodCUSINT665", argument.getCode());
			writeElement("CusIntTexCUSINT666", argument.getText());
			writeElement("CusIntTexCUSINT667LNG", argument.getLng());
		closeElement();
	}
}
