package com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MaltaMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module 		: 	MapICSDiversionRequestKM<br>
 * Created 		: 	15.08.2013<br>
 * Description 	: 	Mapping of KIDS-Format into Cyprus-Format of
 * 					ICSDiversionRequest message (IE323).
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MapICSDiversionRequestKM extends MaltaMessage {

	private MsgDiversionRequest 	msgKids;
	private String					nmsp = "";


	public MapICSDiversionRequestKM(XMLEventReader parser, String encoding, String nameSpace)
			throws XMLStreamException {
		msgKids = new MsgDiversionRequest(parser);
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
	        getCommonFieldsDTO().setTargetMessageType("CC323A");                    
	            
			createRootTag();
			writeHeaderFields(nmsp);
//			if (getKidsHeader() != null) {
//				writeTagsFromKidsHeader(getKidsHeader(), nmsp);
//			}
           	writeBody();				
			closeElement(); // openElement("CC323A");

			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

		return xmlOutputString.toString();
	}

	private void writeBody() {		            
		writeHeahea(msgKids);
		writeCusoffentactoff700(msgKids.getActualOfficeOfFirstEntry());
		try {
			 //no date and msgNumber required for Malta
			writeCusOffEntry(msgKids.getActualOfficeOfFirstEntry(), "", "");
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} 
		writeTrareqdic456(msgKids.getSubmitter());
		writeImpope200(msgKids.getImportOperationList(), "323");
	}

	private void writeHeahea(MsgDiversionRequest msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement(nmsp + "HEAHEA");
				if (msg.getMeansOfTransportBorder() != null) {
					writeElement(nmsp + "TraModAtBorHEA76", msg.getMeansOfTransportBorder().getTransportMode());
				}
				if (Utils.isStringEmpty(msgKids.getDeclaredOfficeOfFirstEntry())) {   //EI20110714
					writeElement(nmsp + "CouCodOffFirEntDecHEA100", msg.getDeclaredCountryOfArrival());
				}
				if (Utils.isStringEmpty(msg.getInformationType())) {
					writeElement(nmsp + "InfTypHEA122", "1");   //EI20110714 soll von CodeList 109 gefüllt sein - wo ist sie???
				} else {
					writeElement(nmsp + "InfTypHEA122", msg.getInformationType());
				}
				writeElement(nmsp + "DivRefNumHEA119", msg.getReferenceNumber());
				if (msg.getMeansOfTransportBorder() != null) {
					writeElement(nmsp + "UniIdeDivHEA132", msg.getMeansOfTransportBorder().getTransportationNumber());
				}
				writeElement(nmsp + "ExpDatArrHEA701", msg.getDeclaredDateOfArrival());
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}		

    public void writeCusoffentactoff700(String actualOfficeOfFirstEntry) {
		if (Utils.isStringEmpty(actualOfficeOfFirstEntry)) {
			return;
		}
		try {
			openElement(nmsp + "CUSOFFENTACTOFF700");
				writeElement(nmsp + "RefNumCUSOFFENTACTOFF701", actualOfficeOfFirstEntry);				
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

    public void writeCusOffEntry(String office, String date, String msgNumber)  throws XMLStreamException {
		if (Utils.isStringEmpty(office) && Utils.isStringEmpty(date)) {
			return;
		}			
		openElement(nmsp + "CUSOFFFENT730" + msgNumber);
			writeElement(nmsp + "RefNumCUSOFFFENT731", office);				
            writeElement(nmsp +"ExpDatOfArrFIRENT733", date);      
		closeElement();				
	}                                                    

    public void writeTrareqdic456(Party submitter) {		
		if (submitter == null || submitter.isEmpty()) {
			return;
		}
		try {
			if (submitter.getAddress() != null) {
				Address address = submitter.getAddress();
				openElement(nmsp + "TRAREQDIV456");
					writeElement(nmsp + "NamTRAREQDIV457", address.getName());					
					if (Utils.isStringEmpty(address.getHouseNumber())) {
						writeElement(nmsp + "StrAndNumTRAREQDIV458", address.getStreet());
					} else {
						writeElement(nmsp + "StrAndNumTRAREQDIV458", address.getStreet() + " " + address.getHouseNumber());
					}
					writeElement(nmsp + "CouTRAREQDIV459", address.getCountry());
					writeElement(nmsp + "PosCodTRAREQDIV460", address.getPostalCode());
					writeElement(nmsp + "CitTRAREQDIV461", address.getCity());
					writeElement(nmsp + "TRAREQDIV456LNG", address.getLanguage());
					if (submitter.getPartyTIN() != null) {
						writeElement(nmsp + "TINTRAREQDIV463", submitter.getPartyTIN().getTIN());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
    
    public void writeImpope200(List<ImportOperation> list, String msgNumber) {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		try {						
			for (ImportOperation importOperation : list) {
				if (importOperation != null) {
					openElement(nmsp + "IMPOPE200" + msgNumber);
						writeElement(nmsp + "DocRefNumIMPOPE201", importOperation.getMRN());						
						writeGooitegds323(importOperation.getItemNumberList(), msgNumber);						
					closeElement();
				}				
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
    
    private void writeGooitegds323(List<String> list, String msgNumber) {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		try {
			openElement(nmsp +"GOOITEIMP248" + msgNumber); 
				for (String itemNumber : list) {
					writeElement(nmsp +"IteNumGIIMP297", itemNumber);
				}
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void createRootTag() throws XMLStreamException {
		if(Utils.isStringEmpty(this.nmsp)) {
			openElement("http://ces.gov.mt/ics/schemas/CC323A", "CC323A");
		} else {
			openElement(this.nmsp + "CC323A");
		}
	}
	
}
