package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
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
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAcknowledgmentKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 28.06.2011<br>
 * Description	: Mapping of ICSEntrySummaryDeclarationAcknowledgement from Greece to KIDS.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

public class MapICSEntrySummaryDeclarationAcknowledgementGK extends KidsMessageCY {
	
	private BodyEntrySummaryDeclarationAcknowledgmentKids	body		= null;
	private MsgCC328A										msgGreece	= null;	
	
	public MapICSEntrySummaryDeclarationAcknowledgementGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
		msgGreece		= new MsgCC328A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
	
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer	= writer;
		
		try {
			body	= new BodyEntrySummaryDeclarationAcknowledgmentKids(writer);
			
			writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
					setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
					
//					msgGreece.parse(HeaderType.GREECE);
					msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
					
					mapKidsHeaderFromMessage();
					kidsHeader.writeHeader();
					
					MsgEntrySummaryDeclarationAcknowledgment kidsMsg = getKidsFromGreece();
					
					if (msgGreece.getHeahea() != null) {
						getCommonFieldsDTO().setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
					}
					
					body.setKidsHeader(kidsHeader);
					body.setMessage(kidsMsg);
					body.writeBody();
					
				closeElement();
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void mapKidsHeaderFromMessage() {       
        //MS20110816: this.kidsHeader.setInReplyTo(msgGreece.getCorIdeMES25());
		//EI20140408: setInReplyTo(msgGreece.getCorIdeMES25()); 
        setInReplyTo(msgGreece.getCorIdeMES25(), "GR");       
		setMapping();
	}
	
	private MsgEntrySummaryDeclarationAcknowledgment getKidsFromGreece() {
		MsgEntrySummaryDeclarationAcknowledgment msgKids	= new MsgEntrySummaryDeclarationAcknowledgment();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			msgKids.setMrn(msgGreece.getHeahea().getMrn());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgGreece.getHeahea()));
			msgKids.setShipmentNumber(msgGreece.getHeahea().getShipmentNumber());
			msgKids.setConveyanceReference(msgGreece.getHeahea().getConvyanceReference());
			msgKids.setRegistrationDateAndTime(msgGreece.getHeahea().getRegistrationDateTime());
		}
		
		if (msgGreece.getGooIteGdsList() != null) {
			List<GoodsItemShort> goodsItemList	= new ArrayList<GoodsItemShort>();
			
			for (Gooitegds gdsItem : msgGreece.getGooIteGdsList()) {
				if (gdsItem != null) {
					GoodsItemShort goodsItem	= new GoodsItemShort();
					goodsItem.setItemNumber(gdsItem.getItemNumber());
					goodsItem.setShipmentNumber(gdsItem.getShipmentNumber());
						
					if (gdsItem.getProdocdc2List() != null) {
						List<IcsDocument> docList	= new ArrayList<IcsDocument>();
							
						for (Prodocd2 docItem : gdsItem.getProdocdc2List()) {
							if (docItem != null) {
								IcsDocument document	= new IcsDocument();
								document.setType(docItem.getType());
								document.setReference(docItem.getReference());
								document.setReferenceLng(docItem.getReferenceLng());
										
								docList.add(document);
							}
						}
						goodsItem.setDocumentList(docList);
					}
						
					if (gdsItem.getConnr2List() != null) {
						List<String> containerList	= new ArrayList<String>();
							
						for (Connr2 containerItem : gdsItem.getConnr2List()) {
							if (containerItem != null) {
								containerList.add(containerItem.getContainer());
							}
						}
						goodsItem.setContainersList(containerList);
					}
						
					if (gdsItem.getIdemeatragi970List() != null) {
						List<TransportMeans> tMeansList	= new ArrayList<TransportMeans>();
							
						for (Idemeatragi970 tMeansItem : gdsItem.getIdemeatragi970List()) {
							if (tMeansItem != null) {
								TransportMeans tMeans	= new TransportMeans();
								tMeans.setTransportationCountry(tMeansItem.getTransportationCountry());
								tMeans.setTransportationNumber(tMeansItem.getTransportationNumber());
										
								tMeansList.add(tMeans);
							}
						}
						goodsItem.setMeansOfTransportBorderList(tMeansList);
					}
					goodsItemList.add(goodsItem);
				}
			}			
			msgKids.setGoodsItemList(goodsItemList);
		}
		
		if (msgGreece.getCusofflon() != null) {
			msgKids.setCustomsOfficeOfLodgment(msgGreece.getCusofflon().getCustomsOfficeOfLodgement());
		}
		
		if (msgGreece.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgGreece.getPerlodsumdec(), "PersonLodgingSumaAddress"));			
		}
		
		if (msgGreece.getCusofffent730() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgGreece.getCusofffent730().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgGreece.getCusofffent730().getDeclaredDateOfArrival());
		}
		
		if (msgGreece.getTracarent601() != null) {
			msgKids.setCarrier(setParty(msgGreece.getTracarent601(), "CarrierAddress"));			
		}
		
		return msgKids;
	}
	
	private TransportMeans setTransportMeans(Heahea315 heahea) {
		TransportMeans tMeans	= new TransportMeans();
			tMeans.setTransportMode(heahea.getTransportMode());
			tMeans.setTransportationCountry(heahea.getTransportationCountry());
			tMeans.setTransportationNumber(heahea.getTransportationNumber());
		
		return tMeans;
	}
}
