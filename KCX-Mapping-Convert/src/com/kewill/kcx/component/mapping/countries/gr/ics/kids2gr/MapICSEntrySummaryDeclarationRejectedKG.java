package com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.gr.ics.msg.GreeceMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: ICS-GR<br>
 * Created		: 02.06.2011<br>
 * Description	: Mapping of KIDS-Format into Greece-Format of 
 * 				  ICSEntrySummaryDeclarationRejected message (IE316).
 * 				
 * @author Lassiter Caviles.
 * @version 1.0.00
 */

public class MapICSEntrySummaryDeclarationRejectedKG extends GreeceMessage {
	
	private MsgEntrySummaryDeclarationRejected msgKids;
	
	public MapICSEntrySummaryDeclarationRejectedKG(XMLEventReader parser, String encoding)
	throws XMLStreamException {
		msgKids = new MsgEntrySummaryDeclarationRejected(parser);
		this.encoding = encoding;
	}
	
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	
        	openElement("CC316A");
        		msgKids.parse(HeaderType.KIDS);
				getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
				writeESDBody();
				writeHeahea();
				if (msgKids.getFunctionalErrorList() != null) {
					for (FunctionalError error : msgKids.getFunctionalErrorList()) {
						writeFunctionalError(error);
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
			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }  
	}
	
	private void writeHeahea() {
		try {
			if (msgKids != null) {
				openElement("HEAHEA");
					writeElement("RefNumHEA4", msgKids.getReferenceNumber());
					writeElement("DecRejDatTimHEA116", msgKids.getDeclarationRejectionDateAndTime());
					writeElement("DecRejReaHEA252", msgKids.getDeclarationRejectionReason());
					writeElement("DecRejDatTimHEA116", msgKids.getDeclarationRejectionReasonLNG());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	  public void writeFunctionalError(FunctionalError error) throws XMLStreamException {
			if (error == null) {
				return;
			}
	    	openElement("FUNERRER1");             	                    	   
		    	writeElement("ErrTypER11", error.getErrorType());
		    	writeElement("ErrPoiER12", error.getErrorPointer());	 
	    		writeElement("ErrReaER13", error.getErrorReason());
		    	writeElement("OriAttValER14", error.getOriginalAttributeValue());
			closeElement();	
		}  

}
