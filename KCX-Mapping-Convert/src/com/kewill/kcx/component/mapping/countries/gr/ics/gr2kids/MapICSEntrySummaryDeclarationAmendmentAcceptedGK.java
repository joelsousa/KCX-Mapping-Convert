package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.KidsMessageCY;
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
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationAmendmentAcceptanceKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS GREECE<br>
 * Created		: 28.06.2011<br>
 * Description	: Mapping of ICSEntrySummaryDeclarationAmendmentAccepted from Greece to KIDS.
 * 
 * @author	Michelle Bauza
 * @version	1.0.00
 *
 */

//public class MapICSEntrySummaryDeclarationAmendmentAcceptedGK extends KidsMessageGR {
public class MapICSEntrySummaryDeclarationAmendmentAcceptedGK extends KidsMessageCY {
	
	private BodyEntrySummaryDeclarationAmendmentAcceptanceKids	body		= null;
	private MsgCC304A											msgGreece	= null;	
	
	public MapICSEntrySummaryDeclarationAmendmentAcceptedGK(XMLEventReader parser, 
			KidsHeader kidsHeader, String encoding, GreeceHeader greeceHeader) throws XMLStreamException {
		
		msgGreece		= new MsgCC304A(parser);
		this.encoding	= encoding;
		this.kidsHeader	= kidsHeader;		
	}
		
	public void getMessage(XMLStreamWriter writer) {
		this.writer	= writer;
		
		try {
			body	= new BodyEntrySummaryDeclarationAmendmentAcceptanceKids(writer);
			
			writeStartDocument(encoding, "1.0");
				openElement("soap:Envelope");
					setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
					
//					msgGreece.parse(HeaderType.GREECE);
					msgGreece.parse(HeaderType.CYPRUS);   //EI20110802
					
					mapKidsHeaderFromMessage();
					kidsHeader.writeHeader();
					
					MsgEntrySummaryDeclarationAmendmentAcceptance kidsMsg = getKidsFromGreece();
					
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
	
	private MsgEntrySummaryDeclarationAmendmentAcceptance getKidsFromGreece() {
		MsgEntrySummaryDeclarationAmendmentAcceptance msgKids	= new MsgEntrySummaryDeclarationAmendmentAcceptance();
		
		if (msgGreece.getHeahea() != null) {
			msgKids.setMrn(msgGreece.getHeahea().getMrn());
			msgKids.setMeansOfTransportBorder(setTransportMeans(msgGreece.getHeahea()));
			msgKids.setReferenceNumber(msgGreece.getHeahea().getReferenceNumber());
			msgKids.setConveyanceReference(msgGreece.getHeahea().getConvyanceReference());
			msgKids.setAmendmentDateAndTime(msgGreece.getHeahea().getAmendmentDateAndTime());
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
								IcsDocument	document	= new IcsDocument();
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
							                                     
						for (Connr2 conItem : gdsItem.getConnr2List()) {
							if (conItem != null) {
								containerList.add(conItem.getContainer());
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
		
		if (msgGreece.getTrarep() != null) {
			msgKids.setRepresentative(setParty(msgGreece.getTrarep(), "RepresentativeAddress"));					
		}
		
		if (msgGreece.getPerlodsumdec() != null) {
			msgKids.setPersonLodgingSuma(setParty(msgGreece.getPerlodsumdec(), "PersonLodgingSumaAddress"));			
		}
		
		if (msgGreece.getCusofffent() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgGreece.getCusofffent().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgGreece.getCusofffent().getDeclaredDateOfArrival());
		}
		
		if (msgGreece.getTracarent() != null) {
			msgKids.setCarrier(setParty(msgGreece.getTracarent(), "CarrierAddress"));			
		}
		
		return msgKids;
	}
	
	private TransportMeans setTransportMeans(Heahea304 heahea) {
		TransportMeans	tMeans	= new TransportMeans();
		tMeans.setTransportMode(heahea.getTransportMode());
		tMeans.setTransportationNumber(heahea.getTransportationNumber());
		tMeans.setTransportationCountry(heahea.getTransportationCountry());
		
		return tMeans;
	}
}
