package com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSArrivalItemRejectionKF<br>
 * Created		: 28.12.2010<br>
 * Description	: Mapping of KIDS-Format into Fedex-Format of ICSArrivalItemRejection message (IE349).
 * 				
 * @author Jude Eco	
 * @version 1.0.00
 */
public class MapICSArrivalItemRejectionKF extends FedexMessage {
	private MsgArrivalItemRejection msgKids;
	
	public MapICSArrivalItemRejectionKF(XMLEventReader parser, String encoding) 
	throws XMLStreamException {
		msgKids = new MsgArrivalItemRejection(parser);		
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
		                fedexHeader.mapHeaderKidsToFedex(getKidsHeader(), fedexHeader, "MessageIE349"); 
			            fedexHeader.writeHeader();
		            
			            msgKids.parse(HeaderType.KIDS);
			            getCommonFieldsDTO().setReferenceNumber(
			            		msgKids.getReferenceNumber());                   
			            writeArrivalItemRejectionBody();
		            closeElement();
		        closeElement();
	        closeElement();   // soap:Envelope
            
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            //Utils.log("ICS FedexMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
		
        return xmlOutputString.toString();
	}

	
	private void writeArrivalItemRejectionBody() {
		try {
            openElement("MessageBody");
            openElement("CC349A");
            	openElement("CCxxxA");
            		writeElement("MesSenMES3", getKidsHeader().getTransmitter());
            		writeElement("MesRecMES6", getKidsHeader().getReceiver());
            		writeElement("DatOfPreMES9", 
            				getKidsHeader().getYear() + getKidsHeader().getMonth() + getKidsHeader().getDay());
            		writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
            		writeElement("MesTypMES20", getKidsHeader().getMessageName());
            	closeElement();
            	
            	writeHeahea(msgKids);
            	writeFunerrer1(msgKids);
            	
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
	
	private void writeHeahea(MsgArrivalItemRejection msg) {
		try {
			if (msg != null) {
			openElement("HEAHEA");
				writeElement("ArrivalReferenceNumber", msg.getReferenceNumber());
				writeElement("RejDatTimHEA126", msg.getRejectionDateTime());
				writeElement("RejReaHEA127", msg.getRejectionReasonHeader());
				writeElement("RejReaHEA128LNG", msg.getRejectionReasonHeaderLNG());
			closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
	}
	
	private void writeFunerrer1(MsgArrivalItemRejection msg) {
		try {
			if (msg.getFunctionalErrorList() != null) {
				for (FunctionalError functionalError : msg.getFunctionalErrorList()) {
					openElement("Funerrer1");
						writeElement("ErrTypER11",
								functionalError.getErrorType());
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
}
