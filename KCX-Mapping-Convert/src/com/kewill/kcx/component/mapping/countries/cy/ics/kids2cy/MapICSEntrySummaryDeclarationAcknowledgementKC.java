package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAcknowledgementKC<br>
 * Created		: 01.06.2011<br>
 * Description	: Mapping of KIDS-Format into Cyprus-Format of 
 * 				  ICSEntrySummaryDeclarationAcknowledgement message (IE328).
 * 				
 * @author Jude Eco
 * @version 1.0.00
 */

public class MapICSEntrySummaryDeclarationAcknowledgementKC extends CyprusMessage {

	private MsgEntrySummaryDeclarationAcknowledgment msgKids;
	
	public MapICSEntrySummaryDeclarationAcknowledgementKC(XMLEventReader parser, String encoding)
		throws XMLStreamException {
			msgKids = new MsgEntrySummaryDeclarationAcknowledgment(parser);
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
            getCommonFieldsDTO().setTargetMessageType("CC328A");         
            
        	openElement("CC328A");
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
			writeHeahea328(msgKids);
			writeGooitegd328(msgKids);
			writeCusofflon328(msgKids);
			writePerlodsumdec(msgKids);
			writeCusofffent730328(msgKids);
			writeTracarent601(msgKids);					 
	}

	private void writeHeahea328(
			MsgEntrySummaryDeclarationAcknowledgment msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA328");
					writeElement("RefNumHEA4", msg.getReferenceNumber());
					writeElement("DocNumHEA5", msg.getMrn());
					writeElement("TraModAtBorHEA76", 
							msg.getMeansOfTransportBorder().getTransportMode());
					writeElement("NatHEA001", 
							msg.getMeansOfTransportBorder().getTransportationCountry());
					writeElement("IdeOfMeaOfTraCroHEA85", 
							msg.getMeansOfTransportBorder().getTransportationNumber());
					//writeElement("IdeOfMeaOfTraCroHEA85LNG", value) needs to be added, 
					//not needed for DE but for LUX
					writeElement("ComRefNumHEA", msg.getShipmentNumber());
					writeElement("ConRefNumHEA", msg.getConveyanceReference());
					writeElement("DecRegDatTimHEA115", msg.getRegistrationDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeGooitegd328(
			MsgEntrySummaryDeclarationAcknowledgment msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getGoodsItemList() != null) {
				for (GoodsItemShort goodsItemShort : msg.getGoodsItemList()) {
					openElement("GOOITEGD328");
						writeElement("IteNumGDS7", goodsItemShort.getItemNumber());
						writeElement("ComRefNumGIM1", goodsItemShort.getShipmentNumber());
						writeProdocdc2328(goodsItemShort);
						writeConnr2328(goodsItemShort);
						writeIdemeatragi970328(goodsItemShort);
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
	}
	
	private void writeProdocdc2328(
			GoodsItemShort goodsItemShort) {
		if (goodsItemShort == null) {
			return;
		}
		try {
			if (goodsItemShort.getDocumentList() != null) {
				for (IcsDocument document : goodsItemShort.getDocumentList()) {
					openElement("PRODOCDC2328");
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
	
	private void writeConnr2328(GoodsItemShort goodsItemShort) {
		if (goodsItemShort == null) {
			return;
		}
		try {
			if (goodsItemShort.getContainersList() != null) {
				for (String container : goodsItemShort.getContainersList()) {
					openElement("CONNR2328");
						writeElement("ConNumNR21", container);
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeIdemeatragi970328(GoodsItemShort goodsItemShort) {
		if (goodsItemShort == null) {
			return;
		}
		try {
			if (goodsItemShort.getMeansOfTransportBorderList() != null) {
				for (TransportMeans transportMeans : goodsItemShort.getMeansOfTransportBorderList()) {
					openElement("IDEMEATRAGI970328");
						writeElement("NatlDEMEATRAGI973", 
								transportMeans.getTransportationCountry());
						writeElement("IdeMeaTraGIMEATRA971", 
								transportMeans.getTransportationNumber());
						//writeElement("IdeMeaTraGIMEATRA972LNG", value) needs to be added,
						//not needed for DE but for LUX
					closeElement();
				}
			}
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusofflon328(MsgEntrySummaryDeclarationAcknowledgment msg) {
		
		try {
			if (msg != null) {
				openElement("CUSOFFLON328");
					writeElement("RefNumCOL1", msg.getCustomsOfficeOfLodgment());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writePerlodsumdec(MsgEntrySummaryDeclarationAcknowledgment msg) {
		if (msg == null) {
			return;
		}
		try {	
			if (msg.getPersonLodgingSuma() != null) {
				openElement("PERLODSUMDEC");
					if (msg.getPersonLodgingSuma().getAddress() != null) {
						writeElement("NamPLD1", 
								msg.getPersonLodgingSuma().getAddress().getName());
						writeElement("StrAndNumPLD1", 
								Utils.checkNull(msg.getPersonLodgingSuma().getAddress()
										.getStreet()) + " " + 
								Utils.checkNull(msg.getPersonLodgingSuma().getAddress()
										.getHouseNumber()));
						writeElement("PosCodPLD1", msg.getPersonLodgingSuma().getAddress()
								.getPostalCode());
						writeElement("CitPLD1", msg.getPersonLodgingSuma().getAddress()
								.getCity());
						writeElement("CouCodPLD1", msg.getPersonLodgingSuma().getAddress()
								.getCountry());
					}
						//writeElement("PERLODSUMDECLNG", value) needs to be added, 
						//not needed for DE but for other countries
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
	
	private void writeCusofffent730328(MsgEntrySummaryDeclarationAcknowledgment msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCustomsOfficeFirstEntry() != null) {
				openElement("CUSOFFFENT730328");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
					writeElement("ExpDatOfArrFIRENT733", msg.getDeclaredDateOfArrival());	
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTracarent601(MsgEntrySummaryDeclarationAcknowledgment msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getCarrier() != null) {
				openElement("TRACARENT601");
					if (msg.getCarrier().getAddress() != null) {
						writeElement("NamTRACARENT604", msg.getCarrier().getAddress()
								.getName());
						writeElement("StrNumTRACARENT607", 
								Utils.checkNull(msg.getCarrier().getAddress()
								.getStreet()) + " " +
								Utils.checkNull(msg.getCarrier().getAddress()
								.getHouseNumber()));
						writeElement("PstCodTRACARENT606", msg.getCarrier().getAddress()
								.getPostalCode());
						writeElement("CtyTRACARENT603", msg.getCarrier().getAddress()
								.getCity());
						writeElement("CouCodTACARENT605", msg.getCarrier().getAddress()
								.getCountry());
						//writeElement("TRACARENT601LNG", msg.getCarrier()); needs to be added, 
						//not needed for DE but for other countries
					}
					if (msg.getCarrier().getPartyTIN() != null) {
						writeElement("TINTRACARENT602", msg.getCarrier().getPartyTIN()
								.getTIN());						
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
