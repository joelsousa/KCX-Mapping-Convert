package com.kewill.kcx.component.mapping.countries.ch.aus20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAA;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyCHReverseDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Export 20<br>
 * Created		: 12.11.2012<br>
 * Description	: Mapping of CAA to KIDS-ReverseDeclaration (ExpCaa).
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MapCAAToReverseDeclaration extends KidsMessage {
	private MsgCAA      msgCAA;	
	private MsgExpRelCH message;
	
	public MapCAAToReverseDeclaration() {
		msgCAA = new MsgCAA();	
		message = new MsgExpRelCH();
	}

	public void setMsgCAA(MsgCAA argument) {
		
		this.msgCAA = argument;						
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
	        //header.setHeaderFields(msgCAA.getVorSubset());
	        header.setHeaderFieldsFromHead(msgCAA.getVorSubset(), msgCAA.getHeadSubset());     //EI20121005
	        header.setMessageName("ReverseDeclaration");
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyCHReverseDeclarationKids body   = new BodyCHReverseDeclarationKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
	        body.writeBody();
	        
	        //writeMessage();  //EI20090429
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapCAAToReverseDeclaration getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
			
	public void setMsgFields() {		
		if (msgCAA.getCaaSubset() != null) {
			message.setReferenceNumber(msgCAA.getCaaSubset().getBeznr());	
			message.setDeclarationKind(msgCAA.getCaaSubset().getDklart());
			message.setDeclarationNumberForwarder(msgCAA.getCaaSubset().getDksp());
			message.setDeclarationNumberCustoms(msgCAA.getCaaSubset().getDkzo());
			message.setAcceptanceTime(msgCAA.getCaaSubset().getAntdat());
			message.setRevisionCode(msgCAA.getCaaSubset().getCdrev());
			message.setCodeOfRelease(msgCAA.getCaaSubset().getCdfrei());				
		}	
    }
	
	/*
	private void writeMessage() {   //EI20090429: this mapping ist complette (use not any body)
        try {
        	openElement("soap:Body");
            openElement("ReverseDeclaration");
               openElement("GoodsDeclaration");
                  writeElement("DeclarationKind", message.getDeclarationKind());
                  writeElement("DeclarationNumberForwarder", message.getDeclarationNumberForwarder());
                  writeElement("DeclarationNumberCustoms", message.getDeclarationNumberCustoms()); 
                  if (message.getAcceptanceTime() != null) {                    
                  	if (message.getAcceptanceTime().length() <= 12) { //from FSS to KIDS
                  		writeElement("AcceptanceTime", message.getAcceptanceTime()); 
                  	} else { //from UIDS to KIDS
                  		writeDateTimeToString("AcceptanceTime", message.getAcceptanceTime());
                  	}
                  }
                  writeElement("RevisionCode", message.getRevisionCode());
                  writeElement("CodeOfRelease", message.getCodeOfRelease());                        
                  writeElement("ReferenceNumber", message.getReferenceNumber());                       
               closeElement(); // GoodsDeclaration                    
            closeElement(); // ReverseDeclaration
          closeElement(); // soap:Body
        } catch (XMLStreamException e) {
        	
        	e.printStackTrace();
        }
                    	      	
	}	
    */
}
