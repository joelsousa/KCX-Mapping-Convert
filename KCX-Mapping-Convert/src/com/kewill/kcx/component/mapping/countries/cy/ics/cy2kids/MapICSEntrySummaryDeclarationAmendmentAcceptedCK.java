package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.MsgCC304A;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Gooitegds;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Heahea304;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAmendmentAcceptanceKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentAcceptedCK<br>
 * Created		: 23.06.2010<br>
 * Description	: Mapping of ICSEntrySummaryDeclarationAmendmentAccepted 
 * 				 Cyprus (CC304A) to KIDS.
 * 
 * @author	Jude Eco
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAmendmentAcceptedCK extends KidsMessageCY {
	private BodyEntrySummaryDeclarationAmendmentAcceptanceKids 	body;
	private MsgCC304A									msgCyprus = null;	 
	
	public MapICSEntrySummaryDeclarationAmendmentAcceptedCK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, CyprusHeader cyprusHeader) throws XMLStreamException {
			msgCyprus = new MsgCC304A(parser);
				this.encoding = encoding;
				this.kidsHeader = kidsHeader;				
	}
	
	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		try {
			body =  new BodyEntrySummaryDeclarationAmendmentAcceptanceKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgCyprus.parse(HeaderType.CYPRUS);
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationAmendmentAcceptance kidMsg = getKidsFromCyprus();
			
			if (msgCyprus.getHeahea() != null) {
	            getCommonFieldsDTO().setReferenceNumber(
	            		msgCyprus.getHeahea().getReferenceNumber());	            	
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
		//MS20110816: this.kidsHeader.setInReplyTo(msgCyprus.getCorIdeMES25());
		//EI20140408;setInReplyTo(msgCyprus.getCorIdeMES25());
        setInReplyTo(msgCyprus.getCorIdeMES25(), "CY");     //EI20140408       
        setMapping();
	}
	
	private MsgEntrySummaryDeclarationAmendmentAcceptance getKidsFromCyprus() {
		MsgEntrySummaryDeclarationAmendmentAcceptance msgKids = 
			new MsgEntrySummaryDeclarationAmendmentAcceptance();
		
		if (msgCyprus.getHeahea() != null) {
			msgKids.setMrn(msgCyprus.getHeahea().getMrn());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgCyprus.getHeahea()));
			msgKids.setReferenceNumber(msgCyprus.getHeahea().getReferenceNumber());
			msgKids.setConveyanceReference(msgCyprus.getHeahea().getConvyanceReference());
			msgKids.setAmendmentDateAndTime(msgCyprus.getHeahea().getAmendmentDateAndTime());
		}
		
		if (msgCyprus.getGooIteGdsList() != null) {
			List<GoodsItemShort> goodsItemList = new ArrayList<GoodsItemShort>();
			for (Gooitegds goods : msgCyprus.getGooIteGdsList()) {
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
								transportMeans.setTransportationCountry(idemeatragi.
										getTransportationCountry());
								transportMeans.setTransportationNumber(idemeatragi.
										getTransportationNumber());
								
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
		
		if (msgCyprus.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgCyprus.getTrarep(), "RepresentativeAddress"));
			/* EI20110707
			if (msgKids.getRepresentative() == null) {
				Party party = new Party("RepresentativeAddress");
				msgKids.setRepresentative(party);
			}
			*/
		}
		
		if (msgCyprus.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgCyprus.getPerlodsumdec(), "PersonLodgingSumaAddress"));
			/* EI20110707
			if (msgKids.getPersonLodgingSuma() == null) {
				Party party = new Party("PersonLodgingSumaAddress");
				msgKids.setPersonLodgingSuma(party);
			}
			*/
		}
		
		if (msgCyprus.getCusofffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgCyprus.getCusofffent().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgCyprus.getCusofffent().getDeclaredDateOfArrival());
		}
		
		if (msgCyprus.getTracarent() != null) {
			msgKids.setCarrier(setParty(msgCyprus.getTracarent(), "CarrierAddress"));
			/* EI20110707
			if (msgKids.getCarrier() == null) {
				Party party = new Party("CarrierAddress");
				msgKids.setCarrier(party);
			}
			*/
		}
		return msgKids;
	}

	
	private TransportMeans setTransportMeans(Heahea304 heahea) {
		if (heahea == null) {  //EI20110707
			return null;
		}
		TransportMeans transport =  new TransportMeans();
		
		transport.setTransportMode(heahea.getTransportMode());
		transport.setTransportationNumber(heahea.getTransportationNumber());
		transport.setTransportationCountry(heahea.getTransportationCountry());
		return transport;
	}
	
}
