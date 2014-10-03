package com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Module		: MapICSDiversionRequestAcknowledgmentKF<br>
 * Created		: 26.01.2011<br>
 * Description	: Mapping of KIDS-Format into Fedex-Format of ICSDiversionRequestAckowledgment (IE325).
 * 				
 * @author krzoska
 * @version 1.0.00
 */
public class MapICSDiversionRequestAcknowledgmentKF extends FedexMessage {
	private MsgDiversionRequestAcknowledgment msgKids;
	
	public MapICSDiversionRequestAcknowledgmentKF(XMLEventReader parser, String encoding) 
	throws XMLStreamException {
		msgKids = new MsgDiversionRequestAcknowledgment(parser);		
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
			                fedexHeader.mapHeaderKidsToFedex(getKidsHeader(), fedexHeader, "MessageIE325"); 
				            fedexHeader.writeHeader();
			            
				            msgKids.parse(HeaderType.KIDS);
				            getCommonFieldsDTO().setReferenceNumber(
				            		msgKids.getReferenceNumber());                   
				            writeDiversionRequestAcknowledgmentBody();
			            closeElement();
			        closeElement();
            

            closeElement();  // 
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            //Utils.log("ICS FedexMessage = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
		
        return xmlOutputString.toString();
	}

	private void writeDiversionRequestAcknowledgmentBody() {
		try {
	    	 
            openElement("MessageBody");
            openElement("CC325A");
            	openElement("CCxxxA");
            		writeElement("MesSenMES3", getKidsHeader().getTransmitter());
            		writeElement("MesRecMES6", getKidsHeader().getReceiver());
            		writeElement("DatOfPreMES9", getKidsHeader().getYear() + 
    						getKidsHeader().getMonth() + getKidsHeader().getDay());
            		writeElement("TimOfPreMES10", getTime(getKidsHeader().getTime()));
            		//writeElement("MesIdeMES19", msg.getShimentNumber)  doesnt exist in Kids
            		writeElement("MesTypMES20", getKidsHeader().getMessageName());
            		writeElement("ComAccRefMES21", msgKids.getReferenceNumber());
            		
            	closeElement();
            	
            	writeHeahea();
            	writeCUSOFFFENT730();
            	writeTRAREQDIV456();
            	
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
	
	private void writeHeahea() {
		try {
			if (msgKids != null) {
			openElement("HEAHEA");
				writeElement("DivRefNumHEA119", msgKids.getReferenceNumber());
				writeElement("RegDatTimHEA125", msgKids.getRegistrationDateTime());
			closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	
	private void writeCUSOFFFENT730() {
		try {
			if (msgKids != null && !Utils.isStringEmpty(msgKids.getCustomsOfficeOfFirstEntry())) {
				openElement("CUSOFFFENT730");
					writeElement("RefNumCUSOFFFENT731", msgKids.getCustomsOfficeOfFirstEntry());
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeTRAREQDIV456() {
		try {
			if (msgKids != null && msgKids.getSubmitter() != null) {
				openElement("TRAREQDIV456");
					if (msgKids.getSubmitter().getAddress() != null) {
							writeElement("NamTRAREQDIV457", msgKids.getSubmitter().getAddress().getName());
							writeElement("StrAndNumTRAREQDIV458", Utils.checkNull(msgKids.getSubmitter().getAddress().getStreet()) + " " 
									+ Utils.checkNull(msgKids.getSubmitter().getAddress().getHouseNumber()));
							writeElement("CouTRAREQDIV459", msgKids.getSubmitter().getAddress().getCountry());
							writeElement("PosCodTRAREQDIV460", msgKids.getSubmitter().getAddress().getPostalCode());
							writeElement("CitTRAREQDIV46", msgKids.getSubmitter().getAddress().getCity());
						closeElement();
					}
					
					if (msgKids.getSubmitter().getPartyTIN() != null) {
						writeElement("TINTRAREQDIV463", msgKids.getSubmitter().getPartyTIN().getTin());
					}
				closeElement();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
