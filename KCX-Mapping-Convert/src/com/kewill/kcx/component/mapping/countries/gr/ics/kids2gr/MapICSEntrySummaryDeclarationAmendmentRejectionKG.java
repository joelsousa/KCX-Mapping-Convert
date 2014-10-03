package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module : MapICSEntrySummaryDeclarationAmendmentRejectionKG<br>
 * Created : 08.06.2011<br>
 * Description : Mapping of KIDS-Format into Greece-Format of
 * ICSEntrySummaryDeclarationAmendmentRejection message (IE305).
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class MapICSEntrySummaryDeclarationAmendmentRejectionKG extends GreeceMessage {
	private MsgEntrySummaryDeclarationAmendmentRejection msgKids;
	
	public MapICSEntrySummaryDeclarationAmendmentRejectionKG(XMLEventReader parser, String encoding)
		throws XMLStreamException {
			msgKids = new MsgEntrySummaryDeclarationAmendmentRejection(parser);
			this.encoding = encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	
        	openElement("CC305A");
        		msgKids.parse(HeaderType.KIDS);
				getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
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
			
			try {
				openElement("MESSAGE");
					writeElement("MesSenMES3", getKidsHeader().getTransmitter());
		    		writeElement("MesRecMES6", getKidsHeader().getReceiver());
		    		writeElement("DatOfPreMES9", getKidsHeader().getYear() + 
		    						getKidsHeader().getMonth() + getKidsHeader().getDay());
		    		writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
		    		writeElement("MesIdeMES19", getKidsHeader().getMessageID());
		    		writeElement("MesTypMES20", getKidsHeader().getMessageName());
				closeElement();
				
				writeHeahea(msgKids);
				writeFunerrer1(msgKids);
				writeTrarep(msgKids);
				writePerlodsumdec(msgKids);
				writeCusofffent(msgKids);
			} catch (XMLStreamException e) {
	            e.printStackTrace();
	        }    
	}

	private void writeHeahea(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			if (msg != null) {
				openElement("HEAHEA");
					writeElement("DocNumHEA5", msg.getMrn());
					writeElement("DatTimAmeHEA113", msg.getAmendmentDateAndTime());
					writeElement("AmeRejDatTimHEA112", msg.getAmendmentRejectionDateAndTime());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeFunerrer1(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		if (msg == null) {
			return;
		}
		try {
			if (msg.getFunctionalErrorList() != null) {
				for (FunctionalError functionalError : msg.getFunctionalErrorList()) {
					openElement("FUNERRER1");
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
				openElement("TRAREP");
					writeElement("NamTRE1", msg.getRepresentative().getAddress().getName());
					writeElement("StrAndNumTRE1", 
							msg.getRepresentative().getAddress().getStreet() + 
							msg.getRepresentative().getAddress().getHouseNumber());
					writeElement("PosCodTRE1", 
							msg.getRepresentative().getAddress().getPostalCode());
					writeElement("CitTRE1", msg.getRepresentative().getAddress().getCity());
					writeElement("CouCodTRE1", msg.getRepresentative().getAddress().getCountry());
					writeElement("TINTRE1", msg.getRepresentative().getPartyTIN().getTIN());
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
					writeElement("TINPLD1", msg.getPersonLodgingSuma().getPartyTIN().getTIN());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeCusofffent(
			MsgEntrySummaryDeclarationAmendmentRejection msg) {
		try {
			if (msg != null) {
				openElement("CUSOFFFENT730");
					writeElement("RefNumCUSOFFFENT731", msg.getCustomsOfficeFirstEntry());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
