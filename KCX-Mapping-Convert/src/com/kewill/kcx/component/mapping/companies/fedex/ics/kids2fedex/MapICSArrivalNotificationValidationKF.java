package com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.ArrivalItem;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSArrivalNotificationKF<br>
 * Created		: 28.12.2010<br>
 * Description	: Mapping of KIDS-Format into Fedex-Format of ICSArrivalNotificationValidation message (IE348).
 * 				
 * @author Frederick T.	
 * @version 1.0.00
 */
public class MapICSArrivalNotificationValidationKF extends KidsMessage {

	private MsgArrivalNotification msgKids;
	private BodyArrivalNotificationKids body = null;
	//private MsgCC347A 		msgFedex = null;
	
	
	public MapICSArrivalNotificationValidationKF(XMLEventReader parser, String encoding) 
	throws XMLStreamException {
		msgKids = new MsgArrivalNotification(parser);		
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
			                fedexHeader.mapHeaderKidsToFedex(getKidsHeader(), fedexHeader, "MessageIE348"); 
				            fedexHeader.writeHeader();
			            
				            msgKids.parse(HeaderType.KIDS);
				            getCommonFieldsDTO().setReferenceNumber(
				            		msgKids.getReferenceNumber());                   
				            writeARNVBody();
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
	private void writeARNVBody() {
		   try {
			 openElement("MessageBody");
			 	openElement("ArrivalOperation");
			 		writeElement("RefNumHEA4", msgKids.getReferenceNumber());
			 		writeElement("BureauNotification", msgKids.getCustomsOfficeFirstEntry());
			 		writeElement("CouCodOffFirEntDecHEA100", msgKids.getCountryOfficeFirstEntry());
			 	closeElement();
		     openElement("Response347FR");
		            writeElement("RefNumHEA4", msgKids.getReferenceNumber());
		            openElement("ArrivalItems");
		            	openElement("ArrivalItem");
		            		writeElement("ArrivalItemNumber", "1");
		            			openElement("CustomsDataReferences");
		            				openElement("CustomsDataReference");
		            					writeElement("DocNumHEA5", msgKids.getMrn());
		            				closeElement();
		            			closeElement();
		            	closeElement();
		            closeElement();
		            if (msgKids.getCarrier() != null && msgKids.getCarrier().getPartyTIN() != null) {
			           	openElement("TRACARENT601");
			           		writeElement("TINTRACARENT602", msgKids.getCarrier().getPartyTIN().getTIN());
			           	closeElement();
		            }
		      closeElement(); //MessageBody
		      closeElement(); //Response347FR
	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }       
		
	}

	
	public void setMapping() {
    	// C. Kron
    	// Fehlen die Angaben zum Encoding wird folgendermaßen kodiert (analog UidsToKids)
    	// Wenn Nachricht von Kunde Richtung Zoll,  dann ist mapFrom = mappingCode aus der DB
        // und mapTo = countryCode.
        // Wenn Nachricht von Zoll  Richtung Kunde, dann ist mapFrom = countryCode
        // und mapto = mappingCode aus der DB

    	if (kidsHeader.getMap() == null) {
            CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
            		                    kidsHeader.getReceiver(),
            							kidsHeader.getProcedure().toUpperCase());
            customerProcedureDTO.setMsgFormat("FEDEX");
            String mappingCode = customerProcedureDTO.getMappingCode();	            
            kidsHeader.setMapFrom(mappingCode); 	            	
            kidsHeader.setMapTo(kidsHeader.getCountryCode());
            	        
            if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
            	kidsHeader.setMap("0");
            } else {
            	kidsHeader.setMap("1");
            }
		} else {
        	// Sind in den Encoding-Angaben "mapFrom" und "mapTo" gleich
        	// d.h. das Sender- und Empfängerland identisch, 
        	// wird kein Mapping durchgeführt und die Angabe im Tag "Map" entsprechend
        	// auf "0" gesetzt!
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
        		kidsHeader.setMap("0");
        	}
		}
	}
	
	
	protected String getTime(String time) {
		String retTime = "";
		
		if (time != null) {
			if (time.length() > 3) {
				retTime = time.substring(0, 4);
			}
		}
		return retTime;
	}
}
