package com.kewill.kcx.component.mapping.countries.ch.Import20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70.MsgCCA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAE;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyExportCaeKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MapCAEtoExpCHCancellationResponse<br>
 * Created		: 06.11.2012<br>
 * Description	: Mapping of FSS format CCA to KIDS.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MapCCAtoExpCHCancellationResponse extends KidsMessage {
	
	private MsgCCA msgCCA;	
	private MsgExpCae msgExpCae;	
	
	public MapCCAtoExpCHCancellationResponse() {
		msgCCA    = new MsgCCA();		
		msgExpCae = new MsgExpCae();
	}

	public void setMsgCAE(MsgCCA argument) {
		
		this.msgCCA = argument;						
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
	        header.setHeaderFields(msgCCA.getVorSubset());
	        header.setMessageName("CancelationResponse");
	        
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);	        

	        header.writeHeader();
	        
	        BodyExportCaeKids body   = new BodyExportCaeKids(writer);
	        body.setMsgExpCae(msgExpCae);

            getCommonFieldsDTO().setReferenceNumber(msgExpCae.getRegistrationNumber());
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
		if (msgCCA.getCCASubset() != null) {
			
			 //TODO: doch kein Mapping definiert !?
			/*
			msgExpCae.setTypeOfDocument(msgCCA.getCCASubset().getDklart());
			msgExpCae.setRegistrationNumber(msgCCA.getCCASubset().getDknrzo());
			msgExpCae.setTypeOfAnnulment(msgCCA.getCCASubset().getCdanzo());
			msgExpCae.setDecisionFlag(msgCCA.getCCASubset().getCdanbs());
			msgExpCae.setReasonOfAnnulment(msgCCA.getCCASubset().getAnugrd());
			*/
		}	
    }

}
