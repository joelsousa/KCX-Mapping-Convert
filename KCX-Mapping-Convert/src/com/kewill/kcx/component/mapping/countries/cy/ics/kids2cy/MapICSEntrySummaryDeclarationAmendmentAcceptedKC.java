package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentAcceptedKC<br>
 * Created		: 01.06.2011<br>
 * Description	: Mapping of KIDS-Format into Cyprus-Format of 
 * 				  ICSEntrySummaryDeclarationAmendmentAccepted message (IE304).
 * 				
 * @author Jude Eco
 * @version 1.0.00
 */

public class MapICSEntrySummaryDeclarationAmendmentAcceptedKC extends CyprusMessage {
	private MsgEntrySummaryDeclarationAmendmentAcceptance msgKids;
	
	public MapICSEntrySummaryDeclarationAmendmentAcceptedKC(XMLEventReader parser, String encoding)
		throws XMLStreamException {
			msgKids = new MsgEntrySummaryDeclarationAmendmentAcceptance(parser);
			this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	
            writeStartDocument(encoding, "1.0");    // MS20110629
            
            msgKids.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
            getCommonFieldsDTO().setTargetMessageType("CC304A");                    // MS20110629
            
        	openElement("CC304A");
        		setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
        		setAttribute("xmlns", "http://www.eurodyn.com");
        		setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                    		
	            writeHeaderFields();
				writeESDBody();
				
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
			writeHeahea304(msgKids);
			writeGooitegds304(msgKids);
			writeTrarep304(msgKids);
			writePerlodsumdec(msgKids);
			writeCusofffent730304(msgKids);
			writeTracarent601304(msgKids);				
	}
	
	private void writeHeahea304(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA304");
					writeElement("DocNumHEA5", msg.getMrn());
					if (msg.getMeansOfTransportBorder() != null) {
						writeElement("TraModAtBorHEA76", msg.getMeansOfTransportBorder()
								.getTransportMode());
						writeElement("IdeOfMeaOfTraCroHEA85", msg.getMeansOfTransportBorder()
								.getTransportationNumber());
						//writeElement("IdeOfMeaOfTraCroHEA85LNG", value) needs to be added,
						//not needed for DE
						writeElement("NatOfMeaOfTraCroHEA87", msg.getMeansOfTransportBorder()
								.getTransportationCountry());
					}
					writeElement("ComRefNumHEA", msg.getReferenceNumber());
					writeElement("ConRefNumHEA", msg.getConveyanceReference());
					writeElement("DatTimAmeHEA113", msg.getAmendmentDateAndTime());
					//writeElement("DatTimAmeHEA113", value) <DeclarationTime> needs to be added
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeGooitegds304(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getGoodsItemList() != null) {
				for (GoodsItemShort goodsItemShort : msg.getGoodsItemList()) {
					openElement("GOOITEGDS");
						writeElement("IteNumGDS7", goodsItemShort.getItemNumber());
						writeElement("ComRefNumGIM1", goodsItemShort.getShipmentNumber());
						writeProdocdc2304(goodsItemShort);
						writeConnr2304(goodsItemShort);
						writeIdemeatragi970304(goodsItemShort);
					closeElement();		
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeProdocdc2304(GoodsItemShort goodsItemShort) {
		if (goodsItemShort == null) {
			return;
		}
		try {
			if (goodsItemShort.getDocumentList() != null) {
				for (IcsDocument document : goodsItemShort.getDocumentList()) {
					openElement("PRODOCDC2304");
						writeElement("DocTypDC21", document.getType());
						writeElement("DocRefDC23", document.getReference());
						writeElement("DocRefDCLNG", document.getReferenceLng());
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeConnr2304(GoodsItemShort goodsItemShort) {
		if (goodsItemShort == null) {
			return;
		}
		try {
			if (goodsItemShort.getContainersList() != null) {
				for (String container : goodsItemShort.getContainersList()) {
					openElement("CONNR2304");
						writeElement("ConNumNR21", container);
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeIdemeatragi970304(GoodsItemShort goodsItemShort) {
		if (goodsItemShort == null) {
			return;
		}
		try {
			if (goodsItemShort.getMeansOfTransportBorderList() != null) {
				for (TransportMeans transportMeans : goodsItemShort.getMeansOfTransportBorderList()) {
					openElement("IDEMEATRAGI970304");
						writeElement("NatIDEMEATRAGI973", transportMeans
								.getTransportationCountry());
						writeElement("IdeMeaTraGIMEATRA971", transportMeans
								.getTransportationNumber());
//						writeElement("IdeMeaTraGIMEATRA972LNG", value)//needs to be added,
//						not needed for DE but for LUX
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTrarep304(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getRepresentative() != null) {	
				openElement("TRAREP304");
					if (msg.getRepresentative().getAddress() != null) {
						writeElement("NamTRE1", msg.getRepresentative().getAddress()
								.getName());
						writeElement("StrAndNumTRE1", Utils.checkNull(msg.getRepresentative()
								.getAddress().getStreet()) + " " +
								Utils.checkNull(msg.getRepresentative().getAddress()
								.getHouseNumber()));
						writeElement("PosCodTRE1", msg.getRepresentative().getAddress()
								.getPostalCode());
						writeElement("CitTRE1", msg.getRepresentative().getAddress()
								.getCity());
						writeElement("CouCodTRE1", msg.getRepresentative().getAddress()
								.getCountry());
					}
//					writeElement("TRAREPLNG", value)  //needs to be added, 
//					not needed for DE but for other countries
					if (msg.getRepresentative().getPartyTIN() != null) {
						writeElement("TINTRE1", msg.getRepresentative().getPartyTIN().getTIN());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writePerlodsumdec(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getPersonLodgingSuma() != null) {
				openElement("PERLODSUMDEC");
					if (msg.getPersonLodgingSuma().getAddress() != null) {
						writeElement("NamPLD1", msg.getPersonLodgingSuma().getAddress()
								.getName());
						writeElement("StrAndNumPLD1", Utils.checkNull(msg.getPersonLodgingSuma()
								.getAddress().getStreet()) + " " +
								Utils.checkNull(msg.getPersonLodgingSuma()
								.getAddress().getHouseNumber()));
						writeElement("PosCodPLD1", msg.getPersonLodgingSuma().getAddress()
								.getPostalCode());
						writeElement("CitPLD1", msg.getPersonLodgingSuma().getAddress()
								.getCity());
						writeElement("CouCodPLD1", msg.getPersonLodgingSuma().getAddress()
								.getCountry());
//						writeElement("PERLODSUMDECLNG", value) //needs to be added, 
//						not needed for DE but for other countries
					}
					if (msg.getPersonLodgingSuma().getPartyTIN() != null) {
						writeElement("TINPLD1", msg.getPersonLodgingSuma().getPartyTIN()
								.getTIN());						
					}

				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusofffent730304(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCustomsOfficeFirstEntry() != null) {
				openElement("CUSOFFFENT730304");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
					writeElement("ExpDatOfArrFIRENT733", msg.getDeclaredDateOfArrival());	
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTracarent601304(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCarrier() != null) {
				openElement("TRACARENT601304");
					if (msg.getCarrier().getAddress() != null) {
						writeElement("NamTRACARENT604", msg.getCarrier().getAddress()
								.getName());
						writeElement("StrNumTRACARENT607", Utils.checkNull(msg.getCarrier()
								.getAddress().getStreet()) + " " +
								Utils.checkNull(msg.getCarrier()
								.getAddress().getHouseNumber()));
						writeElement("PstCodTRACARENT606", msg.getCarrier().getAddress()
								.getPostalCode());
						writeElement("CitTRE1", msg.getCarrier().getAddress()
								.getCity());
						writeElement("CouCodTRE1", msg.getCarrier().getAddress()
								.getCountry());
					}
//					writeElement("TRAREPLNG", msg.getCarrier()); //needs to be added, 
//					not needed for DE but for other countries
					if (msg.getCarrier().getPartyTIN() != null) {
						writeElement("TINTRE1", msg.getCarrier().getPartyTIN()
								.getTIN());						
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
