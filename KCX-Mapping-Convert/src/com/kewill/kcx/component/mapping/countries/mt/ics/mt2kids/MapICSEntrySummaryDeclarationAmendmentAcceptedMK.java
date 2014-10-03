package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

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
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAmendmentAcceptanceKids;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentAcceptedMK<br>
 * Created		: 19.08.2013<br>
 * Description	: Mapping of ICSEntrySummaryDeclarationAmendmentAccepted 
 * 				 Malta (CC304A) to KIDS.
 * 
 * @author	krzoska
 * @version	1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAmendmentAcceptedMK extends KidsMessageMT {
	private BodyEntrySummaryDeclarationAmendmentAcceptanceKids 	body;
	private MsgCC304A									msgMalta = null;	 
	
	public MapICSEntrySummaryDeclarationAmendmentAcceptedMK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding) throws XMLStreamException {
			msgMalta = new MsgCC304A(parser);
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
			
			msgMalta.parse(HeaderType.CYPRUS);  //CYPRUS and Malta are same messages
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			MsgEntrySummaryDeclarationAmendmentAcceptance kidMsg = getKidsFromMalta();
			
			if (msgMalta.getHeahea() != null) {
	            getCommonFieldsDTO().setReferenceNumber(
	            		msgMalta.getHeahea().getReferenceNumber());	            	
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
        // MS20110816 Begin
        // this.kidsHeader.setInReplyTo(msgMalta.getCorIdeMES25());
        setInReplyTo(msgMalta.getCorIdeMES25());
        // MS20110816 End

        setMapping();
	}
	
	private MsgEntrySummaryDeclarationAmendmentAcceptance getKidsFromMalta() {
		MsgEntrySummaryDeclarationAmendmentAcceptance msgKids = 
			new MsgEntrySummaryDeclarationAmendmentAcceptance();
		
		if (msgMalta.getHeahea() != null) {
			msgKids.setMrn(msgMalta.getHeahea().getMrn());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgMalta.getHeahea()));
			msgKids.setReferenceNumber(msgMalta.getHeahea().getReferenceNumber());
			msgKids.setConveyanceReference(msgMalta.getHeahea().getConvyanceReference());
			msgKids.setAmendmentDateAndTime(msgMalta.getHeahea().getAmendmentDateAndTime());
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
		
		if (msgMalta.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgMalta.getTrarep(), "RepresentativeAddress"));
		}
		
		if (msgMalta.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgMalta.getPerlodsumdec(), "PersonLodgingSumaAddress"));
		}
		
		if (msgMalta.getCusofffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgMalta.getCusofffent().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgMalta.getCusofffent().getDeclaredDateOfArrival());
		}
		
		if (msgMalta.getTracarent() != null) {
			msgKids.setCarrier(setParty(msgMalta.getTracarent(), "CarrierAddress"));
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
