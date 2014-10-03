package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequest;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperation;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modules		: MapICSDiversionRequestKG
 * Created		: 08.06.2011
 * Description	: Mapping of KIDS Format to Greek Format of ICSDiversionRequest message (IE323A).
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 *
 */
public class MapICSDiversionRequestKG extends GreeceMessage {
	private MsgDiversionRequest	msgKids;
	
	public MapICSDiversionRequestKG(XMLEventReader parser, String encoding) throws XMLStreamException {
		msgKids			= new MsgDiversionRequest(parser);
		this.encoding	= encoding;
	}
	
	public String getMessage() {
		StringWriter xmlOutputString	= new StringWriter();
		XMLOutputFactory	factory		= XMLOutputFactory.newInstance();
		
		try {
			writer	= factory.createXMLStreamWriter(xmlOutputString);
			
            msgKids.parse(HeaderType.KIDS);         
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
            getCommonFieldsDTO().setTargetMessageType("DCL_IE_323");    // MS20110727   

			openElement("CC323A");			    
				setAttribute("xsi:schemaLocation", "http://www.eurodyn.com CC323A.xsd");
            	setAttribute("xmlns", "http://www.eurodyn.com");
            	setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                       
            	writeHeaderFields();
            	//EI20110803: writeDRBody();
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
		writeHeahea(msgKids);
		writeCusoffentactoff700(msgKids.getActualOfficeOfFirstEntry());		
		writeCusOffEntry323(msgKids.getDeclaredOfficeOfFirstEntry(), ""); 
		writeTrareqdic456(msgKids.getSubmitter());
		writeImpope200(msgKids.getImportOperationList(), "");
	}
	
	private void writeHeahea(MsgDiversionRequest msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement("HEAHEA");
				if (msg.getMeansOfTransportBorder() != null) {
					writeElement("TraModAtBorHEA76", msg.getMeansOfTransportBorder().getTransportMode());
				}
				if (Utils.isStringEmpty(msgKids.getDeclaredOfficeOfFirstEntry())) {   //EI20110714
					writeElement("CouCodOffFirEntDecHEA100", msg.getDeclaredCountryOfArrival());
				}
				if (Utils.isStringEmpty(msg.getInformationType())) {
					writeElement("InfTypHEA122", "1");   //EI20110714 soll von CodeList 109 gefüllt sein - wo ist sie???
				} else {
					writeElement("InfTypHEA122", msg.getInformationType());
				}
				writeElement("DivRefNumHEA119", msg.getReferenceNumber());
				if (msg.getMeansOfTransportBorder() != null) {
					writeElement("UniIdeDivHEA132", msg.getMeansOfTransportBorder().getTransportationNumber());
				}
				writeElement("ExpDatArrHEA701", msg.getDeclaredDateOfArrival());
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
}

