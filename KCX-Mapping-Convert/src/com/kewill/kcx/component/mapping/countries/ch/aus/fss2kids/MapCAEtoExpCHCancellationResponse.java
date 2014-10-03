/*
 * Function    : MapCAEtoExpCHCancellationResponse 
 * Date        : 21.11.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of FSS format to KIDS CAE

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 */

package com.kewill.kcx.component.mapping.countries.ch.aus.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyExportCaeKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCae;

import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAE;

/**
 * Modul		: MapCAEtoExpCHCancellationResponse<br>
 * Erstellt		: 21.11.2008<br>
 * Beschreibung	: Mapping of FSS format to KIDS CAE.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapCAEtoExpCHCancellationResponse extends KidsMessage {
	
	private MsgCAE msgCAE;	
	private MsgExpCae msgExpCae;	
	
	public MapCAEtoExpCHCancellationResponse() {
		msgCAE    = new MsgCAE();		
		msgExpCae = new MsgExpCae();
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
	        header.setHeaderFields(msgCAE.getVorSubset());
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
		if (msgCAE.getCaeSubset() != null) {
			msgExpCae.setTypeOfDocument(msgCAE.getCaeSubset().getDklart());
			msgExpCae.setRegistrationNumber(msgCAE.getCaeSubset().getDknrzo());
			msgExpCae.setTypeOfAnnulment(msgCAE.getCaeSubset().getCdanzo());
			msgExpCae.setDecisionFlag(msgCAE.getCaeSubset().getCdanbs());
			msgExpCae.setReasonOfAnnulment(msgCAE.getCaeSubset().getAnugrd());
		}	
    }

}
