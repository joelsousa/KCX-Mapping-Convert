package com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentRejectionKC<br>
 * Created		: 01.06.2011<br>
 * Description	: Mapping of KIDS-Format into Cyprus-Format of 
 * 				  ICSEntrySummaryDeclarationAmendmentRejection message (IE305).
 * 				
 * @author Frederick T.
 * @version 1.0.00
 */

public class MapICSEntrySummaryDeclarationAmendmentRejectionKC extends CyprusMessage {

	private MsgEntrySummaryDeclarationAmendmentRejection msgKids;
	
	public MapICSEntrySummaryDeclarationAmendmentRejectionKC(XMLEventReader parser, String encoding)
		throws XMLStreamException {
			msgKids = new MsgEntrySummaryDeclarationAmendmentRejection(parser);
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
            getCommonFieldsDTO().setTargetMessageType("CC305A");    // MS20110629   
            
        	openElement("CC305A");
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
			writeHeahea(msgKids);
			writeFunerrer(msgKids);
			writeTrarep(msgKids);
			writePerlodsumdec(msgKids);
			writeCusofffent(msgKids);	   
	}

	private void writeHeahea(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement("HEAHEA305");
				writeElement("DocNumHEA5", msg.getMrn());
				writeElement("DatTimAmeHEA113", msg.getAmendmentDateAndTime());
				writeElement("AmeRejDatTimHEA112", msg.getAmendmentRejectionDateAndTime());
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeFunerrer(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getFunctionalErrorList() != null) {
				for (FunctionalError functionalError : msg.getFunctionalErrorList()) {
					openElement("FUNERRER1305");
						writeElement("ErrTypER11", functionalError.getErrorType());
						writeElement("ErrPoiER12", functionalError.getErrorPointer());
						writeElement("ErrReaER13", functionalError.getErrorReason());
						writeElement("OriAttValER14", 
									functionalError.getOriginalAttributeValue());
					closeElement();
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeTrarep(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		if (msg == null) {
			return;
		}
		if (msg.getRepresentative() == null || msg.getRepresentative().isEmpty()) {
			return;
		}
		try {
			if (msg.getRepresentative().getAddress() != null) {
				openElement("TRAREP305");
					writeElement("NamTRE1", msg.getRepresentative().getAddress().getName());
					writeElement("StrAndNumTRE1", 
							msg.getRepresentative().getAddress().getStreet() + 
							msg.getRepresentative().getAddress().getHouseNumber());
					writeElement("PosCodTRE1", 
							msg.getRepresentative().getAddress().getPostalCode());
					writeElement("CitTRE1", msg.getRepresentative().getAddress().getCity());
					writeElement("CouCodTRE1", msg.getRepresentative().getAddress().getCountry());
					if (msg.getRepresentative().getPartyTIN() != null) {
						writeElement("TINTRE1", msg.getRepresentative().getPartyTIN().getTIN());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writePerlodsumdec(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		if (msg == null) {
			return;
		}
		if (msg.getPersonLodgingSuma() == null || msg.getPersonLodgingSuma().isEmpty()) {
			return;
		}
		try {
			if (msg.getPersonLodgingSuma().getAddress() != null) {
				openElement("PERLODSUMDEC");
					writeElement("NamPLD1", msg.getPersonLodgingSuma().getAddress().getName());
					writeElement("StrAndNumPLD1", 
							msg.getPersonLodgingSuma().getAddress().getStreet() + 
							msg.getPersonLodgingSuma().getAddress().getHouseNumber());
					writeElement("PosCodPLD1", 
							msg.getPersonLodgingSuma().getAddress().getPostalCode());
					writeElement("CitPLD1", msg.getPersonLodgingSuma().getAddress().getCity());
					writeElement("CouCodPLD1", 
							msg.getPersonLodgingSuma().getAddress().getCountry());
					if (msg.getPersonLodgingSuma().getPartyTIN() != null) {
						writeElement("TINPLD1", msg.getPersonLodgingSuma().getPartyTIN().getTIN());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCusofffent(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement("CUSOFFFENT730305");
				writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	
}
