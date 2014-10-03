package com.kewill.kcx.component.mapping.countries.ch.aus.fss2kids;

/*
 * Function    : MapExpCanToCAA.java
 * Date        : 17.09.2008
 * Author      : Kewill CSF / EI
 * Description : Mapping of CAA to KIDS format of ExpCaa
 * ------------
 * Changes 
 * ------------
 * Author      : EI
 * Date        : 29.04.2009
 * Label       : EI20090429
 * Description : replaced MsgKids with MsgKidsCH             
 */

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelCH;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAA;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.edec.aus.BodyCHReverseDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		: MapCAAToReverseDeclaration<br>
 * Erstellt		: 17.09.2008<br>
 * Beschreibung	: Mapping of CAA to KIDS format of ExpCaa.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapCAAToReverseDeclaration extends KidsMessage {
	private MsgCAA msgCAA;	
	private MsgExpRelCH msgExpRelCH;
	
	public MapCAAToReverseDeclaration() {
		msgCAA = new MsgCAA();	
		msgExpRelCH = new MsgExpRelCH();
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
	        header.setHeaderFields(msgCAA.getVorSubset());
	        header.setMessageName("ReverseDeclaration");
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyCHReverseDeclarationKids body   = new BodyCHReverseDeclarationKids(writer);
	        body.setMessage(msgExpRelCH);

            getCommonFieldsDTO().setReferenceNumber(msgExpRelCH.getReferenceNumber());
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
			msgExpRelCH.setReferenceNumber(msgCAA.getCaaSubset().getBeznr());	
			msgExpRelCH.setDeclarationKind(msgCAA.getCaaSubset().getDklart());
			msgExpRelCH.setDeclarationNumberForwarder(msgCAA.getCaaSubset().getDksp());
			msgExpRelCH.setDeclarationNumberCustoms(msgCAA.getCaaSubset().getDkzo());
			msgExpRelCH.setAcceptanceTime(msgCAA.getCaaSubset().getAntdat());
			msgExpRelCH.setRevisionCode(msgCAA.getCaaSubset().getCdrev());
			msgExpRelCH.setCodeOfRelease(msgCAA.getCaaSubset().getCdfrei());				
		}	
    }
	
	/*
	private void writeMessage() {   //EI20090429: this mapping ist complette (use not any body)
        try {
        	openElement("soap:Body");
            openElement("ReverseDeclaration");
               openElement("GoodsDeclaration");
                  writeElement("DeclarationKind", msgExpRelCH.getDeclarationKind());
                  writeElement("DeclarationNumberForwarder", msgExpRelCH.getDeclarationNumberForwarder());
                  writeElement("DeclarationNumberCustoms", msgExpRelCH.getDeclarationNumberCustoms()); 
                  if (msgExpRelCH.getAcceptanceTime() != null) {                    
                  	if (msgExpRelCH.getAcceptanceTime().length() <= 12) { //from FSS to KIDS
                  		writeElement("AcceptanceTime", msgExpRelCH.getAcceptanceTime()); 
                  	} else { //from UIDS to KIDS
                  		writeDateTimeToString("AcceptanceTime", msgExpRelCH.getAcceptanceTime());
                  	}
                  }
                  writeElement("RevisionCode", msgExpRelCH.getRevisionCode());
                  writeElement("CodeOfRelease", msgExpRelCH.getCodeOfRelease());                        
                  writeElement("ReferenceNumber", msgExpRelCH.getReferenceNumber());                       
               closeElement(); // GoodsDeclaration                    
            closeElement(); // ReverseDeclaration
          closeElement(); // soap:Body
        } catch (XMLStreamException e) {
        	
        	e.printStackTrace();
        }
                    	      	
	}	
    */
}
