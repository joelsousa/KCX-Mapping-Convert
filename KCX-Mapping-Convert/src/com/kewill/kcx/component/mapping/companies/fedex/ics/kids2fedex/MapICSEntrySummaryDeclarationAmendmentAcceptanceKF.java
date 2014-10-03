package com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentAcceptanceKF<br>
 * Created		: 30.12.2010<br>
 * Description	: Mapping of Fedex-Format into KIDS-Format of 
 *  			: ICSEntrySummaryDeclarationAmendmentAcceptance message (IE304).
 * 				
 * @author Frederick T.	
 * @version 1.0.00
 */

public class MapICSEntrySummaryDeclarationAmendmentAcceptanceKF extends FedexMessage {

	private MsgEntrySummaryDeclarationAmendmentAcceptance msgKids;
	
	public MapICSEntrySummaryDeclarationAmendmentAcceptanceKF(XMLEventReader parser, String encoding)
		throws XMLStreamException {
			msgKids = new MsgEntrySummaryDeclarationAmendmentAcceptance(parser);
			this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	FedexHeader fedexHeader = new FedexHeader(writer);
           
            openElement("MessageOperateur");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            openElement("Messages");
		            openElement("Message");
		                fedexHeader.mapHeaderKidsToFedex(getKidsHeader(), fedexHeader, "MessageIE304"); 
			            fedexHeader.writeHeader();
		            
			            msgKids.parse(HeaderType.KIDS);
			            getCommonFieldsDTO().setReferenceNumber(
			            		msgKids.getReferenceNumber());                   
			            writeESDBody();
		            closeElement();
	            closeElement();
            closeElement();  // MessageOperateur
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
        
	        //Utils.log("ICS FedexMessage = " + xmlOutputString.toString());
        
        } catch (XMLStreamException e) {
        e.printStackTrace();
        }
	
        return xmlOutputString.toString();
	}
	
	private void writeESDBody() {
		
		try {
			openElement("MessageBody");
				openElement("CC304A");
					openElement("CCxxxA");
						writeElement("MesSenMES3", getKidsHeader().getTransmitter());
						writeElement("MesRecMES6", getKidsHeader().getReceiver());
						writeElement("DatOfPreMES9", getKidsHeader().getYear() + 
        						getKidsHeader().getMonth() + getKidsHeader().getDay());
						writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
						writeElement("IntConRefMES11", getKidsHeader().getMessageID());
						writeElement("MesIdeMES19", msgKids.getShipmentNumber());
						writeElement("MesTypMES20", getKidsHeader().getMessageName());
					closeElement();
					
					writeHeahea(msgKids);
					writeGoodsItem(msgKids);
					writeRepresentative(msgKids);
					writePersonLodgingSuma(msgKids);
					writeCustomsOfficeFirstEntry(msgKids);
					writeCarrierAddress(msgKids);
					
				closeElement();
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeHeahea(
			MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
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
					writeElement("DatTimAmeHEA113", msg.getAmendmentDateAndTime());
					//AK20110105 wrong mapping description:
					//           writeElement("DatTimAmeHEA113", msg.getRegistrationDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
				e.printStackTrace();
		}
	}

	private void writeGoodsItem(
			MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg.getGoodsItemList() != null) {
				for (GoodsItemShort goodsItemICSShort : msg.getGoodsItemList()) {
					openElement("GOOITEGDS");
						writeElement("IteNumGDS7", goodsItemICSShort.getItemNumber());
						writeElement("ComRefNumGIM1", goodsItemICSShort.getShipmentNumber());
						writeDocument(goodsItemICSShort);
						writeContainer(goodsItemICSShort);
						writeMeansOfTrasport(goodsItemICSShort);
					closeElement();		
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeDocument(GoodsItemShort goodsItemICSShort) {
		try {
			if (goodsItemICSShort.getDocumentList() != null) {
				for (IcsDocument document : goodsItemICSShort.getDocumentList()) {
					openElement("PRODOCDC2");
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

	private void writeContainer(GoodsItemShort goodsItemICSShort) {
		try {
			if (goodsItemICSShort.getContainersList() != null) {
				for (String container : goodsItemICSShort.getContainersList()) {
					openElement("CONNR2");
						writeElement("ConNumNR21", container);
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeMeansOfTrasport(GoodsItemShort goodsItemICSShort) {
		try {
			if (goodsItemICSShort.getMeansOfTransportBorderList() != null) {
				for (TransportMeans transportMeans : goodsItemICSShort.getMeansOfTransportBorderList()) {
					openElement("IDEMEATRAGI970");
						writeElement("NatIDEMEATRAGI973", transportMeans.getTransportationCountry());
						writeElement("IdeMeaTraGIMEATRA971", transportMeans.getTransportationNumber());
//						writeElement("IdeMeaTraGIMEATRA972LNG", value)//needs to be added, not needed for DE but for LUX
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeRepresentative(
			MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg.getRepresentative() != null) {	
				openElement("TRAREP");
					if (msg.getRepresentative().getAddress() != null) {
						writeElement("NamTRE1", msg.getRepresentative().getAddress().getName());
						writeElement("StrAndNumTRE1", Utils.checkNull(msg.getRepresentative().getAddress().getStreet()) + " " +
								Utils.checkNull(msg.getRepresentative().getAddress().getHouseNumber()));
						writeElement("PosCodTRE1", msg.getRepresentative().getAddress().getPostalCode());
						writeElement("CitTRE1", msg.getRepresentative().getAddress().getCity());
						writeElement("CouCodTRE1", msg.getRepresentative().getAddress().getCountry());
					}
//					writeElement("TRAREPLNG", value)  //needs to be added, not needed for DE but for other countries
					if (msg.getRepresentative().getPartyTIN() != null) {
						writeElement("TINTRE1", msg.getRepresentative().getPartyTIN().getTIN());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writePersonLodgingSuma(
			MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg.getPersonLodgingSuma() != null) {
				openElement("PERLODSUMDEC");
					if (msg.getPersonLodgingSuma().getAddress() != null) {
						writeElement("NamPLD1", msg.getPersonLodgingSuma().getAddress().getName());
						writeElement("StrAndNumPLD1", Utils.checkNull(msg.getPersonLodgingSuma().getAddress().getStreet()) + " " +
								Utils.checkNull(msg.getPersonLodgingSuma().getAddress().getHouseNumber()));
						writeElement("PosCodPLD1", msg.getPersonLodgingSuma().getAddress().getPostalCode());
						writeElement("CitPLD1", msg.getPersonLodgingSuma().getAddress().getCity());
						writeElement("CouCodPLD1", msg.getPersonLodgingSuma().getAddress().getCountry());
//						writeElement("PERLODSUMDECLNG", value) //needs to be added, not needed for DE but for other countries
					}
					if (msg.getPersonLodgingSuma().getPartyTIN() != null) {
						writeElement("TINPLD1", msg.getPersonLodgingSuma().getPartyTIN().getTIN());						
					}

				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCustomsOfficeFirstEntry(
			MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg.getCustomsOfficeFirstEntry() != null) {
				openElement("CUSOFFFENT730");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
					writeElement("ExpDatOfArrFIRENT733", msg.getDeclaredDateOfArrival());	
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCarrierAddress(
			MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
		try {
			if (msg.getCarrier() != null) {
				openElement("TRACARENT601");
					if (msg.getCarrier().getAddress() != null ) {
						writeElement("NamTRACARENT604", msg.getCarrier().getAddress().getName());
						writeElement("StrNumTRACARENT607", Utils.checkNull(msg.getCarrier().getAddress().getStreet()) + " " +
								Utils.checkNull(msg.getCarrier().getAddress().getHouseNumber()));
						writeElement("PstCodTRACARENT606", msg.getCarrier().getAddress().getPostalCode());
						writeElement("CitTRE1", msg.getCarrier().getAddress().getCity());
						writeElement("CouCodTRE1", msg.getCarrier().getAddress().getCountry());
					}
//					writeElement("TRAREPLNG", msg.getCarrier()); //needs to be added, not needed for DE but for other countries
					if (msg.getCarrier().getPartyTIN() != null) {
						writeElement("TINTRE1", msg.getCarrier().getPartyTIN().getTIN());						
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	
}
