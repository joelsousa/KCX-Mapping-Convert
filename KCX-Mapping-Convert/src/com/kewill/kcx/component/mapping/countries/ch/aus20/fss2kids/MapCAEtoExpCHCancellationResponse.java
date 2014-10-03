package com.kewill.kcx.component.mapping.countries.ch.aus20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAE;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyExportCaeKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Export 20<br>
 * Created		: 12.11.2012<br>
 * Description	: Mapping of FSS-CAE to KIDS-CancellationResponse (MsgExpCae).
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MapCAEtoExpCHCancellationResponse extends KidsMessage {
	
	private MsgCAE    msgCAE;	
	private MsgExpCae message;	
	
	public MapCAEtoExpCHCancellationResponse() {
		msgCAE    = new MsgCAE();		
		message = new MsgExpCae();
	}

	public void setMsgCAE(MsgCAE argument) {
		
		this.msgCAE = argument;						
    	this.setMsgFields();    	
    }
	
	public String getMessage() {
	    StringWriter xmlOutputString = new StringWriter();
	    
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    try {
	        writer = factory.createXMLStreamWriter(xmlOutputString);

	        writeStartDocument(encoding, "1.0");
	        openElement("soap:Envelope");
	        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	        
	        KidsHeader  header = new KidsHeader(writer);
	        //header.setHeaderFields(msgCAE.getVorSubset());
	        header.setHeaderFieldsFromHead(msgCAE.getVorSubset(), msgCAE.getHeadSubset());     //EI20121005
	        header.setMessageName("CancelationResponse");
	        
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);	        

	        header.writeHeader();
	        
	        BodyExportCaeKids body   = new BodyExportCaeKids(writer);
	        body.setMsgExpCae(message);

            getCommonFieldsDTO().setReferenceNumber(message.getRegistrationNumber());
	        body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        Utils.log("(MapCAEtoExpCH807C getMessage) Msg = " + xmlOutputString.toString());
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
	
	
	public void setMsgFields() {		
		if (msgCAE.getCaeSubset() != null) {
			message.setTypeOfDocument(msgCAE.getCaeSubset().getDklart());
			message.setRegistrationNumber(msgCAE.getCaeSubset().getDknrzo());
			message.setTypeOfAnnulment(msgCAE.getCaeSubset().getCdanzo());
			message.setDecisionFlag(msgCAE.getCaeSubset().getCdanbs());
			message.setReasonOfAnnulment(msgCAE.getCaeSubset().getAnugrd());
		}	
    }

}
