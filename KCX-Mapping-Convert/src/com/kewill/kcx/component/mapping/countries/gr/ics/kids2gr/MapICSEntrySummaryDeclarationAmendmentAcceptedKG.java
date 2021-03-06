package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentAcceptedKG
 * Created		: 08.06.2011
 * Description	: Mapping of KIDS Format to Greek Format of 
 * 					ICSEntrySummaryDeclarationAmendmentAccepted message (IE304A).
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 *
 */
public class MapICSEntrySummaryDeclarationAmendmentAcceptedKG extends GreeceMessage {
	private MsgEntrySummaryDeclarationAmendmentAcceptance msgKids;
	
	public MapICSEntrySummaryDeclarationAmendmentAcceptedKG(XMLEventReader parser, 
			String encoding) throws XMLStreamException {
		msgKids			= new MsgEntrySummaryDeclarationAmendmentAcceptance(parser);
		this.encoding	= encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
			openElement("CC304A");
				msgKids.parse(HeaderType.KIDS);
				getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
				writeESDAABody();
			closeElement();
			
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	private void writeESDAABody() {
		try {
			openElement("Message304");
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
			writeTraRep(msgKids.getRepresentative());
			writePerLodSumDec(msgKids.getPersonLodgingSuma());
			writeCusOfffEnt(msgKids);
			writeTraCarEnt(msgKids.getCarrier());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeHeahea(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA");
					writeElement("DocNumHEA5", msg.getMrn());
					
					if (msg.getMeansOfTransportBorder() != null) {
						writeElement("TraModAtBorHEA76", msg.getMeansOfTransportBorder().getTransportMode());
						writeElement("IdeOfMeaOfTraCroHEA85", 
								msg.getMeansOfTransportBorder().getTransportationNumber());
						writeElement("NatOfMeaOfTraCroHEA87", 
								msg.getMeansOfTransportBorder().getTransportationCountry());
					}
					
					writeElement("ComRefNumHEA", msg.getReferenceNumber());
					writeElement("ConRefNumHEA", msg.getConveyanceReference());
					writeElement("AmeAccDatTimHEA11", msg.getAmendmentDateAndTime());
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
	
	private void writeTraRep(Party representative) {
		try {
			openElement("TRAREP");
				if (representative.getAddress() != null) {
					writeElement("NamTRE1", representative.getAddress().getName());
					writeElement("StrAndNumTRE1", representative.getAddress().getHouseNumber() + 
							" " + representative.getAddress().getStreet());
					writeElement("PosCodTRE1", representative.getAddress().getPostalCode());
					writeElement("CitTRE1", representative.getAddress().getCity());
					writeElement("CouCodTRE1", representative.getAddress().getCountry());
				}
				
				if (representative.getPartyTIN() != null) {
					writeElement("TINTRE1", representative.getPartyTIN().getTIN());
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writePerLodSumDec(Party personLodSumDec) {
		try {
			openElement("PERLODSUMDEC");
				if (personLodSumDec.getAddress() != null) {
					writeElement("NamPLD1", personLodSumDec.getAddress().getName());
					writeElement("StrAndNumPLD1", personLodSumDec.getAddress().getHouseNumber() + 
							" " + personLodSumDec.getAddress().getStreet());
					writeElement("PosCodPLD1", personLodSumDec.getAddress().getPostalCode());
					writeElement("CitPLD1", personLodSumDec.getAddress().getCity());
					writeElement("CouCodPLD1", personLodSumDec.getAddress().getCountry());
				}
				
				if (personLodSumDec.getPartyTIN() != null) {
					writeElement("TINPLD1", personLodSumDec.getPartyTIN().getTIN());
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusOfffEnt(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
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
