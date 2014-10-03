package com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MaltaMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSAdvanceInterventionNotKM<br>
 * Created : 15.08.2013<br>
 * Description : Mapping of KIDS-Format into Malta-Format of
 * ICSdvanceInterventionNotification message (IE351).
 * 
 * @author Alfred Krzoska.
 * @version 1.0.00
 */

public class MapICSAdvancedInterventionNotKM extends MaltaMessage {

	private MsgAdvanceInterventionNot 	msgKids;
	private String					 	nmsp	= "";
	
	public MapICSAdvancedInterventionNotKM(XMLEventReader parser, String encoding, String nameSpace)
			throws XMLStreamException {
		msgKids = new MsgAdvanceInterventionNot(parser);
		this.encoding = encoding;
		if(!Utils.isStringEmpty(nameSpace)) {
			this.nmsp = nameSpace + ":";
		}
	}

	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			writer = factory.createXMLStreamWriter(xmlOutputString);            
            
			msgKids.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber()); 
            getCommonFieldsDTO().setTargetMessageType("CC351A");   

			createRootTag();
			writeHeaderFields(nmsp);
//				setAttribute("xsi:schemaLocation", "http://ces.gov.mt/ics/schemas/CC351A CC351A.xsd");
//				setAttribute("xmlns", "http://ces.gov.mt/ics/schemas/CC328A");
//				setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            		
//				if (getKidsHeader() != null) {
//					writeTagsFromKidsHeader(getKidsHeader(), nmsp);
//				}

				writeHeahea();

				if (msgKids.getGoodsItemList() != null) {
					for (GoodsItemShort goodsItem : msgKids.getGoodsItemList()) {
						writeGoodsItem(goodsItem);
				}
				}
				writeAddressRep(msgKids.getRepresentative());
				writeAddressPer(msgKids.getPersonLodgingSuma());

				openElement(nmsp + "CUSOFFFENT730Type");
					writeElement(nmsp + "RefNumCUSOFFFENT731", msgKids.getCustomsOfficeFirstEntry());
					writeElement(nmsp + "ExpDatOfArrFIRENT733", msgKids.getDeclaredDateOfArrival());
				closeElement();

				writeAddressCar(msgKids.getCarrier());

				if (msgKids.getCustomsInterventionList() != null) {
					for (CustomsIntervention customs : msgKids.getCustomsInterventionList()) {
						writeCustomsInterventions(customs);
				}
				}

			closeElement();

			writer.writeEndDocument();           
            writer.flush();
            writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return xmlOutputString.toString();

	}

	private void writeHeahea() {
		try {
			if (msgKids != null) {
				openElement(nmsp + "HEAHEA");
					writeElement(nmsp + "RefNumHEA4", msgKids.getMrn());
					writeElement(nmsp + "DocNumHEA5", msgKids.getReferenceNumber());
					if (msgKids.getMeansOfTransportBorder() != null) {
						writeElement(nmsp + "TraModAtBorHEA76", msgKids.getMeansOfTransportBorder().getTransportMode());
						writeElement(nmsp + "NatHEA001", msgKids.getMeansOfTransportBorder().getTransportationCountry());
						writeElement(nmsp + "IdeOfMeaOfTraCroHEA85", msgKids.getMeansOfTransportBorder().
																	 getTransportationNumber());
					}
					writeElement(nmsp + "TotNumOfIteHEA305", msgKids.getTotalNumberPosition());
					writeElement(nmsp + "ComRefNumHEA", msgKids.getShipmentNumber());
					writeElement(nmsp + "ConRefNumHEA", msgKids.getConveyanceReference());
					writeElement(nmsp + "NotDatTimHEA104", msgKids.getNotificationDateTime());
					writeElement(nmsp + "DecRegDatTimHEA115", msgKids.getRegistrationDateTime());
					writeElement(nmsp + "DecSubDatTimHEA118", msgKids.getAcceptedDateTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeGoodsItem(GoodsItemShort goodsItem) {
		if (goodsItem == null) {
			return;
		}
		try {
			
			openElement(nmsp + "GOOITEGDS");
				writeElement(nmsp + "IteNumGDS7", goodsItem.getItemNumber());
				writeElement(nmsp + "ComRefNumGIM1", goodsItem.getShipmentNumber());

				if (goodsItem.getDocumentList() != null) {
					for (IcsDocument document : goodsItem.getDocumentList()) {
						writeDocument(document);
					}
				}
				if (goodsItem.getContainersList() != null) {
					for (String container : goodsItem.getContainersList()) {
						openElement(nmsp + "CONNR2Type");
							writeElement(nmsp + "ConNumNR21", container);
						closeElement();
					}
				}

				if (goodsItem.getMeansOfTransportBorderList() != null) {
					for (TransportMeans border : goodsItem.getMeansOfTransportBorderList()) {
						writeTransport(nmsp + "IDEMEATRAGI970Type", border);
					}
				}
			closeElement();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeDocument(IcsDocument document) throws XMLStreamException {

		if (document == null) {
			return;
		}
		if (document.isEmpty()) {
			return;
		}
		openElement(nmsp + "PRODOCDC2");
			writeElement(nmsp + "DocTypDC21", document.getType());
			writeElement(nmsp + "DocRefDC23", document.getReference());
			writeElement(nmsp + "DocRefDCLNG", document.getReferenceLng());
		closeElement();
	}

	public void writeTransport(String tag, TransportMeans argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(nmsp + tag);
			writeElement(nmsp + "NatlDEMEATRAGI973", argument.getTransportationCountry());
			writeElement(nmsp + "IdeMeaTraGIMEATRA971", argument.getTransportationNumber());
		closeElement();
	}

	public void writeAddressRep(Party representative) throws XMLStreamException {
		if (representative == null) {
			return;
		}
		if (representative.isEmpty()) {
			return;
		}
		try {
			writeTraderRep(representative, "");
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeAddressPer(Party argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		try {
			   writeTraderPer(argument, "PERLODSUMDEC");
			   closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeAddressCar(Party argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		try {
			openElement(nmsp + "TRACARENT601");
				writeTraderCar(argument, ""); 
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeTraderRep(Party party, String msgNumber) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(nmsp + "TRAREP" + msgNumber);
    	writeElement(nmsp + "NamTRE1", address.getName());
    	if (Utils.isStringEmpty(address.getHouseNumber())) {
    		writeElement(nmsp + "StrAndNumTRE1", address.getStreet());
    	} else {
    		writeElement(nmsp + "StrAndNumTRE1", address.getStreet() + " " + address.getHouseNumber());
    		//writeElement("StrNumTRACARENT607", address.getStreet()+" "+address.getHouseNumber());
    	}
		writeElement(nmsp + "PosCodTRE1", address.getPostalCode());
		writeElement(nmsp + "CitTRE1", address.getCity());
		writeElement(nmsp + "CouCodTRE1", address.getCountry());	
		writeElement(nmsp + "TRAREPLNG", address.getLanguage());
	
		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINTRE1", party.getPartyTIN().getTIN());
		} 
		closeElement();
	}

    public void writeTraderCar(Party party, String msgNumber) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}    
    	Address address = party.getAddress();
    	
    	openElement(nmsp + "TRACARENT601" + msgNumber);
    	if (address != null) {
			writeElement(nmsp + "NamTRACARENT604", address.getName());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement(nmsp + "StrNumTRACARENT607", address.getStreet());
			} else {
				writeElement(nmsp + "StrNumTRACARENT607", address.getStreet() + " " + address.getHouseNumber());
			}
			writeElement(nmsp + "PstCodTRACARENT606", address.getPostalCode());
			writeElement(nmsp + "CtyTRACARENT603", address.getCity());
			writeElement(nmsp + "CouCodTRACARENT605", address.getCountry());
			writeElement(nmsp + "TRACARENT601LNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINTRACARENT602", party.getPartyTIN().getTIN());
		}
		closeElement();
    }

    public void writeTraderPer(Party party, String partyName) throws XMLStreamException {
    	if (party == null) {
    		return;
    	}
    	Address address = party.getAddress();
    	
    	openElement(nmsp + partyName);
    	if (party.getAddress() != null) {
			writeElement(nmsp + "NamPLD1", address.getName());
			if (Utils.isStringEmpty(address.getHouseNumber())) {
				writeElement(nmsp + "StrAndNumPLD1", address.getStreet());
			} else {
				writeElement(nmsp + "StrAndNumPLD1", address.getStreet() + " " + address.getHouseNumber());
			}
			writeElement(nmsp + "PosCodPLD1", address.getPostalCode());
			writeElement(nmsp + "CitPLD1", address.getCity());
			writeElement(nmsp + "CouCodPLD1", address.getCountry());
			writeElement(nmsp + "PERLODSUMDECLNG", address.getLanguage());
		}
		if (party.getPartyTIN() != null) {
			writeElement(nmsp + "TINPLD1", party.getPartyTIN().getTIN()); 
		}
		closeElement();
    }

	public void writeCustomsInterventions(CustomsIntervention argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(nmsp + "CUSINT632Type");
			writeElement(nmsp + "IteNumConCUSINT668", argument.getItemNumber());
			writeElement(nmsp + "CusIntCodCUSINT665", argument.getCode());
			writeElement(nmsp + "CusIntTexCUSINT666", argument.getText());
			writeElement(nmsp + "CusIntTexCUSINT667LNG", argument.getLng());
		closeElement();
	}
	
	
	private void createRootTag() throws XMLStreamException {
		if(Utils.isStringEmpty(this.nmsp)) {
			openElement("http://ces.gov.mt/ics/schemas/CC351A", "CC351A");
		} else {
			openElement(this.nmsp + "CC351A");
		}
	}
}
