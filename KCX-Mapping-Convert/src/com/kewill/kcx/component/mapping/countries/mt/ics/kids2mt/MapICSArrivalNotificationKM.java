package com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDetails;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MaltaMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSArrivalNotificationKM
 * Created		: 15.08.2013
 * Description	: Mapping of KIDS Format to MALTA Format of ICSArrivalNotificationKM message (IE347A).
 * 
 * xsd smb01\home1\dat\develop\Projekte\OK\customsXchange\Countries\Malta\ICS\SD-TradersRequirementDocument-AppendixD-v1.6.9\XSD
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 *
 */
public class MapICSArrivalNotificationKM extends MaltaMessage {
	private MsgArrivalNotification 	msgKids;
	private String 					nmsp = "";  

	
	public MapICSArrivalNotificationKM(XMLEventReader parser, String encoding, String nameSpace) throws XMLStreamException {
		msgKids = new MsgArrivalNotification(parser);
		this.encoding	= encoding;
		if(!Utils.isStringEmpty(nameSpace)) {
			this.nmsp = nameSpace + ":";
		}
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
            msgKids.parse(HeaderType.KIDS);			
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
	        getCommonFieldsDTO().setTargetMessageType("CC347A");

	        createRootTag();
	        writeHeaderFields(nmsp);
//	        	setAttribute("xsi:schemaLocation", "http://ces.gov.mt/ics/schemas/CC347A CC347A.xsd");
//	        	setAttribute("xmlns", "http://ces.gov.mt/ics/schemas/CC347A");
//	        	setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
//	        	writeHeaderFields(nmsp);
//				if (getKidsHeader() != null) {
//					writeTagsFromKidsHeader(getKidsHeader(), nmsp);
//				}
				writeBody();
			closeElement();
			
			writer.writeEndDocument();			
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return xmlOutputString.toString();
	}
	
	private void writeBody() {
		if (msgKids == null) {
			return;
		}
		try {				
			writeArrivalOperation();
			writeArrivalItems();
			writeEntryCarrierTrader(msgKids.getCarrier(), "entry-carrier-trader", nmsp);
			writeCustomsOfficeOfFirstEntry(msgKids.getCustomsOfficeFirstEntry());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	

	private void writeCustomsOfficeOfFirstEntry(String customsOfficeFirstEntry) throws XMLStreamException {
		if (!Utils.isStringEmpty(customsOfficeFirstEntry)) {
			openElement("customs-office-actual-office-of-first-entry");
				writeElement("reference-number", customsOfficeFirstEntry);
			closeElement();
		}
		
	}

	private void writeArrivalOperation() throws XMLStreamException {
		if (msgKids == null) {
			return;
		}

		openElement(nmsp + "arrival-operation");
			writeElement(nmsp + "arrival-lrn", msgKids.getReferenceNumber());
			if (msgKids.getMeansOfTransportBorder() != null) {
				writeElement(nmsp + "transport-mode-at-border", msgKids.getMeansOfTransportBorder().getTransportMode());
			}
			writeElement(nmsp + "identification-of-the-means-of-transport", msgKids.getConveyanceReference());
			writeElement(nmsp + "date-and-time-of-presentation", msgKids.getDateOfPresentation());
			writeElement(nmsp + "expected-date-and-time-of-arrival", msgKids.getDeclaredDateOfArrival());
			writeElement(nmsp + "country-code-of-office-of-first-entry-declared", msgKids.getCountryOfficeFirstEntry());
			writeElement(nmsp + "total-number-of-arrival-items", msgKids.getTotalNumberPositions());
			writeElement(nmsp + "total-number-of-arrival-packages", msgKids.getTotalNumberPackages());
			writeElement(nmsp + "total-gross-mass", msgKids.getTotalGrossMass());
		closeElement();  //arrival-operation");
	}

	private void writeArrivalItems() throws XMLStreamException {
		if (msgKids != null && msgKids.getGoodsItemList() != null && msgKids.getGoodsItemList().size() > 0) {
			for (GoodsItemArn item : msgKids.getGoodsItemList()) {
				if (item != null) {
					writeTranportDocumentData(item);
					writeCustomsDataReference(item);
				}
			}
		}
	}

	private void writeTranportDocumentData(GoodsItemArn item) throws XMLStreamException {
		if (!emptyDocument(item)) {
			openElement(nmsp + "transport-document-data");
				writeElement(nmsp + "document-type", item.getDocument().getType());
				writeElement(nmsp + "transport-document-reference", item.getDocument().getReference());
				writeElement(nmsp + "transport-document-reference-lng", item.getDocument().getReferenceLng());
			closeElement();  // nmsp + "transport-document-data"
		}
	}

	private boolean emptyDocument(GoodsItemArn item) {
		Boolean documentEmpty = true;

		if (item.getDocument() != null) {
			documentEmpty = Utils.isStringEmpty(item.getDocument().getType());
			documentEmpty = documentEmpty && Utils.isStringEmpty(item.getDocument().getReference());
			documentEmpty = documentEmpty && Utils.isStringEmpty(item.getDocument().getReferenceLng());
		}

		return documentEmpty;
	}

	private void writeCustomsDataReference(GoodsItemArn item) throws XMLStreamException  {
		if (!emptyCustomsDataReference(item)) {
			openElement(nmsp + "transport-document-data");
				writeElement(nmsp + "document-type", item.getDocument().getType());
				writeElement(nmsp + "transport-document-reference", item.getDocument().getReference());
				writeElement(nmsp + "transport-document-reference-lng", item.getDocument().getReferenceLng());
			closeElement();
		}
	}

	
	private boolean emptyCustomsDataReference(GoodsItemArn item)  throws XMLStreamException  {
		Boolean customsDataReferenceEmpty = true;

		if (item.getEsumaDataReferenceList() != null && item.getEsumaDataReferenceList().size() > 0) {
			for (EsumaDataReference sdr : item.getEsumaDataReferenceList()) {
				if (sdr != null && !Utils.isStringEmpty(sdr.getMrn()) && 
					!Utils.isStringEmpty(sdr.getCountryOfficeFirstEntry())) {
						writeElement(nmsp + "mrn", sdr.getMrn());
						writeElement(nmsp + "country-code-of-office-of-first-entry-declared", 
										sdr.getCountryOfficeFirstEntry());
						writeCustomsDataReferenceDetails(sdr);
				}
			}
		}
		return customsDataReferenceEmpty;
	}

	private void writeEntryCarrierTrader(Party party, String partyName, String nmsp) throws XMLStreamException {
    	if (party == null) {
    		return;
    	} 
    	Address address = party.getAddress();
    	
    	openElement(nmsp + partyName);    	
        if (address != null) {
            writeElement(nmsp + "name", address.getName());  
            if (Utils.isStringEmpty(address.getHouseNumber())) {
            	writeElement(nmsp + "street-and-number", address.getStreet());     
            } else {
            	writeElement(nmsp + "street-and-number", address.getStreet() + " " + address.getHouseNumber());
            }
            writeElement(nmsp + "country-code", address.getCountry());
            writeElement(nmsp + "postal-code", address.getPostalCode());
            writeElement(nmsp + "city", address.getCity());
            writeElement(nmsp + "nad-lng", address.getLanguage());
            if (party.getPartyTIN() != null) {
            	writeElement(nmsp + "tin", party.getPartyTIN().getTin());
            }
        }

		closeElement();  // openElement(nmsp + partyName);
	}


	private void writeCustomsDataReferenceDetails(EsumaDataReference sdr)   throws XMLStreamException  {
		if (sdr.getItemNumberEsumaLists() != null && sdr.getItemNumberEsumaLists().size() > 0) {
			for (EsumaDetails details : sdr.getItemNumberEsumaLists()) {
				if (details != null && !Utils.isStringEmpty(details.getItemNumberEsuma())) {
					openElement(nmsp + "customs-data-reference-details");
						writeElement(nmsp + "mrn-item-number", details.getItemNumberEsuma());
					closeElement();
				}
			}
		}
	}
	
	private void createRootTag() throws XMLStreamException {
		if(Utils.isStringEmpty(this.nmsp)) {
			openElement("http://ces.gov.mt/ics/schemas/CC347A", "CC347A");
		} else {
			openElement(this.nmsp + "CC347A");
		}
	}
}
