package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC328A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea315;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAcknowledgmentKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAcknowledgmentMK (CC328A).<br>
 * Created		: 19.08.2013<br>
 * Description	: ICSEntrySummaryDeclarationAcknowledgmentMK.
 * 
 * @author	krzoska
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAcknowledgmentMK extends KidsMessageMT {

	private BodyEntrySummaryDeclarationAcknowledgmentKids	body;
	private MsgCC328A		msgMalta = null;
	
	public MapICSEntrySummaryDeclarationAcknowledgmentMK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding) throws XMLStreamException {
			msgMalta = new MsgCC328A(parser);
			this.encoding 	= encoding;
			this.kidsHeader	= kidsHeader;				
	}
			
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body =  new BodyEntrySummaryDeclarationAcknowledgmentKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgMalta.parse(HeaderType.CYPRUS);
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationAcknowledgment kidMsg = getKidsFromMalta();
			
			if (msgMalta.getHeahea() != null) {
				getCommonFieldsDTO().setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			}
			
			body.setKidsHeader(kidsHeader);
			body.setMessage(kidMsg);
			body.writeBody();
			

            closeElement();  // soap:Envelope
            writer.writeEndDocument();
        
			writer.flush();
			writer.close();
			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }
		
	}
	
	private void mapKidsHeaderFromMessage() {
        setInReplyTo(msgMalta.getCorIdeMES25());

		setMapping();
	}
	
	private MsgEntrySummaryDeclarationAcknowledgment getKidsFromMalta() {
		MsgEntrySummaryDeclarationAcknowledgment msgKids = 
			new MsgEntrySummaryDeclarationAcknowledgment();
		
		if (msgMalta.getHeahea() != null) {
			String refnrOrg = "";
			refnrOrg = Db.getOriginalRefnr(msgMalta.getHeahea().getReferenceNumber(), "MT");
			if (!Utils.isStringEmpty(refnrOrg)) {				
				msgKids.setReferenceNumber(refnrOrg);
			} else {
				msgKids.setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			}

			msgKids.setMrn(msgMalta.getHeahea().getMrn());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgMalta.getHeahea()));
			msgKids.setShipmentNumber(msgMalta.getHeahea().getShipmentNumber());
			msgKids.setConveyanceReference(msgMalta.getHeahea().getConvyanceReference());
			msgKids.setRegistrationDateAndTime(msgMalta.getHeahea().getRegistrationDateTime());
		}
		
		if (msgMalta.getGooIteGdsList() != null) {
			List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();
			for (Gooitegds goods : msgMalta.getGooIteGdsList()) {
				if (goods != null) {
					GoodsItemShort goodsItem = new GoodsItemShort();
					
					goodsItem.setItemNumber(goods.getItemNumber());
					goodsItem.setShipmentNumber(goods.getShipmentNumber());
					
					if (goods.getProdocdc2List() != null) {
						List<IcsDocument> documentList = new ArrayList<IcsDocument>();
						for (Prodocd2 prodocd : goods.getProdocdc2List()) {
							if (prodocd != null) {
								IcsDocument document = new IcsDocument();
								
								document.setType(prodocd.getType());
								document.setReference(prodocd.getReference());
								document.setReferenceLng(prodocd.getReferenceLng());
							
								documentList.add(document);
							}
						}
						goodsItem.setDocumentList(documentList);
					}
					
					if (goods.getConnr2List() != null) {
						List <String> containersList = new ArrayList<String>();
						for (Connr2 container : goods.getConnr2List()) {
							if (container != null) {
								containersList.add(container.getContainer());
							}
						}
						goodsItem.setContainersList(containersList);
					}
					
					if (goods.getIdemeatragi970List() != null) {
						List <TransportMeans> transportMeansList = 
							new ArrayList<TransportMeans>();
						for (Idemeatragi970 idemeatragi : goods.getIdemeatragi970List()) {
							if (idemeatragi != null) {
								TransportMeans transportMeans = new TransportMeans();
								transportMeans.setTransportationCountry(idemeatragi.getTransportationCountry());
								transportMeans.setTransportationNumber(idemeatragi.getTransportationNumber());
								
								transportMeansList.add(transportMeans);
							}
						}
						goodsItem.setMeansOfTransportBorderList(transportMeansList);
					}
					
					goodsItemList.add(goodsItem);
				}
			}
			msgKids.setGoodsItemList(goodsItemList);
		}
		
		if (msgMalta.getCusofflon() != null) {
			msgKids.setCustomsOfficeOfLodgment(msgMalta.getCusofflon().getCustomsOfficeOfLodgement());
		}
		
		if (msgMalta.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgMalta.getPerlodsumdec(), "PersonLodgingSumaAddress"));
			
			if (msgKids.getPersonLodgingSuma() == null) {
				Party party = new Party("PersonLodgingSumaAddress");
				msgKids.setPersonLodgingSuma(party);
			}
		}
		
		if (msgMalta.getCusofffent730() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgMalta.getCusofffent730().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgMalta.getCusofffent730().getDeclaredDateOfArrival());
		}
		
		if (msgMalta.getTracarent601() != null) {
			msgKids.setCarrier(setParty(msgMalta.getTracarent601(), "CarrierAddress"));
			/* EI20110707
			if (msgKids.getCarrier() == null) {
				Party party = new Party("CarrierAddress");
				msgKids.setCarrier(party);
			}
			*/
		}
		return msgKids;
	}
	
	private TransportMeans setTransportMeans(Heahea315 heahea) {
		if (heahea == null) {  //EI20110707
			return null;
		}
		TransportMeans transport =  new TransportMeans();
		
		transport.setTransportMode(heahea.getTransportMode());
		transport.setTransportationCountry(heahea.getTransportationCountry());
		transport.setTransportationNumber(heahea.getTransportationNumber());
		return transport;
	}
}
