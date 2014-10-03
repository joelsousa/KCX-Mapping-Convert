package com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.EUnisysParty;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.KidsMessageICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustAwb;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.GdsDetail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Segment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDeclarationAmendmentKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapCustAwbToKids<br>
 * Erstellt		: 06.12.2010<br>
 * Beschreibung	: Mapping of Unisys CUST-AWB-MSG Message to KIDS DeclarationAmendment.
 *                IE313 in ../customsXchange/Customers/Unisys/UNISYS_To_KIDS.xlsx
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapDeclarationAmendmentUniK extends KidsMessageICS {
  //kopiert von MapEntrySummaryDeclarationUniK; ==>  Unterschied: MRN vorhanden

	private MsgCustAwb msgCustAwb = null;
	private MsgDeclarationAmendment kidsMsg	= null;

	private String countryTo = "";


	public MapDeclarationAmendmentUniK(XMLEventReader parser, KidsHeader kidsHeader, String encoding)
																			throws XMLStreamException {
			msgCustAwb  = new MsgCustAwb(parser);

			this.kidsHeader	= kidsHeader;
			this.encoding = encoding;
			countryTo = kidsHeader.getCountryCode();
	}

	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	    	    BodyDeclarationAmendmentKids body = new BodyDeclarationAmendmentKids(writer);

	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");

	            msgCustAwb.parse(HeaderType.UNISYS);
	            kidsHeader.writeHeader();
	            countryTo = kidsHeader.getCountryCode();
	            MsgEntrySummaryDeclaration kidsDeclaration = new MsgEntrySummaryDeclaration();
	            getKidsFromUnisys(kidsDeclaration, msgCustAwb, countryTo);
	            getAmendmentFromDeclaration(kidsDeclaration);
	            getCommonFieldsDTO().setReferenceNumber(kidsMsg.getReferenceNumber());

	            body.setKidsHeader(kidsHeader);
	            body.setMessage(kidsMsg);
	            body.writeBody();

	            closeElement();  // soap:Envelope
	            writer.writeEndDocument();

	            writer.flush();
	            writer.close();

	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	}

	//private MsgDeclarationAmendment getKidsFromUnisys() {
	private void getKidsFromUnisys(MsgEntrySummaryDeclaration kidsDeclaration) {

		kidsMsg.setDeclarationTime(getDeclarationTimeFromHeader());

		kidsMsg.setPersonLodgingSuma(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.L, countryTo));
		kidsMsg.setRepresentative(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.R, countryTo));
		//msgKids.setCarrier(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.I)); TODO in KidsAmendment nicht definiert ?!
		kidsMsg.setConsignor(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.S, countryTo));
		kidsMsg.setConsignee(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.C, countryTo));
		kidsMsg.setNotifyParty(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.N, countryTo));

		if (msgCustAwb.getDetailList() != null  && msgCustAwb.getDetailList().size() > 0) {
			for (Detail detail : msgCustAwb.getDetailList()) {
				msgKids.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo, 1));
				getKopfTagsFromDetail(msgKids, detail);
			}
		}
		return msgKids;
	}
	// absichtlich unterschiedlich als in MapEntrySummaryDeclarationUniK, weil nicht klar is
	// in welcher Form die Daten kommen
	private void getKopfTagsFromDetail(MsgDeclarationAmendment msgKids, Detail detail) {
		if (detail == null) {
			return;
		}
		String originCountry = "";
		String ladingCountry = "";
		String unladingCountry = "";

		msgKids.setTotalNumberPositions("1");   //Max: fix = 1

		if (detail.getCustGoodsInfo() != null && detail.getCustGoodsInfo().getGdsDetailList() != null) {
			if (detail.getCustGoodsInfo().getGdsDetailList().size() > 0) {
				GdsDetail gds = detail.getCustGoodsInfo().getGdsDetailList().get(0);
				if (gds != null) {
					if (gds.getCtrySpec() != null && gds.getCtrySpec().getCustoms() != null) {
						msgKids.setReferenceNumber(gds.getCtrySpec().getCustoms().getEnsLref());
						msgKids.setMrn(gds.getCtrySpec().getCustoms().getMrn());
					}
					if (gds.getManifested() != null) {
						msgKids.setTotalNumberPackages(gds.getManifested().getPieces());
					}
					if (gds.getFlightInfo() != null) {
						msgKids.setMeansOfTransportBorder(getTransportMeansBorderFromUnisys(gds.getFlightInfo(), null));
						msgKids.setConveyanceReference(gds.getFlightInfo().getCcd() + gds.getFlightInfo().getFlight());
					}

					if (gds.getArrival() != null) {
						msgKids.setDeclarationPlace(gds.getArrival().getStation());
						msgKids.setCustomsOfficeFirstEntry(gds.getArrival().getPortCode());
						msgKids.setDeclaredDateOfArrival(getDateTimeFromUnisys(gds.getArrival().getSta()));
					}
				}
			}
		}
		if (detail.getCustAwbInfo() != null) {
			msgKids.setPaymentType(detail.getCustAwbInfo().getPayCode());
			if (detail.getCustAwbInfo().getCustAWB() != null) {
				msgKids.setShipmentNumber(detail.getCustAwbInfo().getCustAWB().getAWB());
			}
			if (detail.getCustAwbInfo().getOrigin() != null) {
				originCountry = detail.getCustAwbInfo().getOrigin().getCountry();
				if (!Utils.isStringEmpty(originCountry)) {
					msgKids.addCountryOfRoutingList(originCountry);
				}
			}
		}
		if (detail.getCustGoodsInfo() != null && detail.getCustGoodsInfo().getGdsDetailList() != null) {
			if (detail.getCustGoodsInfo().getGdsDetailList().size() > 0) {
				Segment segment = detail.getCustGoodsInfo().getGdsDetailList().get(0).getSegment();
				if (segment != null && segment.getLading() != null && segment.getLading().getCountry() != null) {
					ladingCountry = segment.getLading().getCountry();
					if (!Utils.isStringEmpty(ladingCountry) && !ladingCountry.equals(originCountry)) {
						msgKids.addCountryOfRoutingList(ladingCountry);
					}
				}
				if (segment != null && segment.getUnlading() != null && segment.getUnlading().getCountry() != null) {
					unladingCountry = segment.getUnlading().getCountry();
					if (!Utils.isStringEmpty(unladingCountry) && !unladingCountry.equals(ladingCountry)) {
						msgKids.addCountryOfRoutingList(unladingCountry);
					}
				}
			}
		}
		if (detail.getCustAwbInfo() != null) {
			if (detail.getCustAwbInfo().getDest() != null) {
				String destCountry = detail.getCustAwbInfo().getDest().getCountry();
				if (!Utils.isStringEmpty(destCountry) && !destCountry.equals(unladingCountry)) {
					msgKids.addCountryOfRoutingList(destCountry);
				}
			}
		}
	}
}
