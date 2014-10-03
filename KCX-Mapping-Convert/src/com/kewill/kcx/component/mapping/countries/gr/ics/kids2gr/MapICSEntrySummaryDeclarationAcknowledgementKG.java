package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAcknowledgementKG
 * Created		: 08.06.2011
 * Description	: Mapping of KIDS Format to Greek Format of ICSEntrySummaryDeclarationAcknowledgement message (IE328A).
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAcknowledgementKG extends GreeceMessage {
	private MsgEntrySummaryDeclarationAcknowledgment	msgKids;
	
	public MapICSEntrySummaryDeclarationAcknowledgementKG(XMLEventReader parser, 
			String encoding) throws XMLStreamException {
		msgKids			= new MsgEntrySummaryDeclarationAcknowledgment(parser);
		this.encoding	= encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
			openElement("CC328A");
				msgKids.parse(HeaderType.KIDS);
				getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
				writeESDABody();
			closeElement();
			
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	private void writeESDABody() {
		try {
			openElement("Message328");
				writeElement("MesSenMES3", getKidsHeader().getTransmitter());
	    		writeElement("MesRecMES6", getKidsHeader().getReceiver());
	    		writeElement("DatOfPreMES9", getKidsHeader().getYear() + 
	    						getKidsHeader().getMonth() + getKidsHeader().getDay());
	    		writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
	    		writeElement("MesIdeMES19", getKidsHeader().getMessageID());
	    		writeElement("MesTypMES20", getKidsHeader().getMessageName());
			closeElement();
			
			writeHeahea(msgKids);
			writeGooIteGds(msgKids.getGoodsItemList());
			writeCusOffLon(msgKids);
			writePerLodSumDec(msgKids.getPersonLodgingSuma());
			writeCusOfffEnt(msgKids);
			writeTraCarEnt(msgKids.getCarrier());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeHeahea(MsgEntrySummaryDeclarationAcknowledgment msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA");
					writeElement("RefNumHEA4", msg.getReferenceNumber());
					writeElement("DocNumHEA5", msg.getMrn());
					
					if (msg.getMeansOfTransportBorder() != null) {
						writeElement("TraModAtBorHEA76", msg.getMeansOfTransportBorder().getTransportMode());
						writeElement("NatHEA001", msg.getMeansOfTransportBorder().getTransportationCountry());
						writeElement("IdeOfMeaOfTraCroHEA85", 
								msg.getMeansOfTransportBorder().getTransportationNumber());
					}
					
					writeElement("ComRefNumHEA", msg.getShipmentNumber());
					writeElement("ConRefNumHEA", msg.getConveyanceReference());
					writeElement("DecRegDatTimHEA115", msg.getRegistrationDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeGooIteGds(List<GoodsItemShort> gisList) {
		if (gisList == null || gisList.isEmpty()) {
			return;
		}
		
		try {
			openElement("GOOITEGDS");
				for (GoodsItemShort gi : gisList) {
					if (gi != null) {
						writeElement("IteNumGDS7", gi.getItemNumber());
						writeElement("ComRefNumGIM1", gi.getShipmentNumber());
						
						if (gi.getDocumentList() != null) {
							openElement("PRODOCDC2");
								for (IcsDocument icsd : gi.getDocumentList()) {
									writeElement("DocTypDC21", icsd.getType());
									writeElement("DocRefDC23", icsd.getReference());
									writeElement("DocRefDCLNG", icsd.getReferenceLng());
								}
							closeElement();
						}
						
						if (gi.getContainersList() != null) {
							openElement("CONNR2");
								for (String cl : gi.getContainersList()) {
									writeElement("ConNumNR21", cl);
								}
							closeElement();
						}
						
						if (gi.getMeansOfTransportBorderList() != null) {
							openElement("IDEMEATRAGI970");
								for (TransportMeans tm : gi.getMeansOfTransportBorderList()) {
									writeElement("NatIDEMEATRAGI973", tm.getTransportationCountry());
									writeElement("IdeMeaTraGIMEATRA971", tm.getTransportationNumber());
								}
							closeElement();
						}
					}
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusOffLon(MsgEntrySummaryDeclarationAcknowledgment msg) {
		try {
			if (msg != null) {
				openElement("CUSOFFLON");
					writeElement("RefNumCOL1", msg.getCustomsOfficeOfLodgment());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writePerLodSumDec(Party perLodSumDec) {
		if (perLodSumDec == null || perLodSumDec.isEmpty()) {
			return;
		}
		
		try {
			openElement("PERLODSUMDEC");
				if (perLodSumDec.getAddress() != null) {
					writeElement("NamPLD1", perLodSumDec.getAddress().getName());
					writeElement("StrAndNumPLD1", perLodSumDec.getAddress().getHouseNumber() + 
							" " + perLodSumDec.getAddress().getStreet());
					writeElement("PosCodPLD1", perLodSumDec.getAddress().getPostalCode());
					writeElement("CitPLD1", perLodSumDec.getAddress().getCity());
					writeElement("CouCodPLD1", perLodSumDec.getAddress().getCountry());
				}
				
				if (perLodSumDec.getPartyTIN() != null) {
					writeElement("TINPLD1", perLodSumDec.getPartyTIN().getTIN());
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusOfffEnt(MsgEntrySummaryDeclarationAcknowledgment msg) {
		try {
			if (msg != null) {
				openElement("CUSOFFFENT730");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
					writeElement("ExpDatOfArrFIRENT733", msg.getDeclaredDateOfArrival());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTraCarEnt(Party carrier) {
		try {
			openElement("TRACARENT601");
				if (carrier.getAddress() != null) {
					writeElement("NamTRACARENT604", carrier.getAddress().getName());
					writeElement("StrNumTRACARENT607", carrier.getAddress().getHouseNumber() + 
							" " + carrier.getAddress().getStreet());
					writeElement("PstCodTRACARENT606", carrier.getAddress().getPostalCode());
					writeElement("CtyTRACARENT603", carrier.getAddress().getCity());
					writeElement("CouCodTRACARENT605", carrier.getAddress().getCountry());
				}
				
				if (carrier.getPartyTIN() != null) {
					writeElement("TINTRACARENT602", carrier.getPartyTIN().getTIN());
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
