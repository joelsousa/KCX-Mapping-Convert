/*
 * Function    : MapExpFupToAUP.java
 * Titel       :
 * Date        : 10.10.2008
 * Author      : Kewill CSF / houdek
 * Description : Mapping of FSS-Format AUP into KIDS-Format of Investigation
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNer;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgAUP;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportInvestigationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapAUPToExpFup<br>
 * Erstellt		: 10.10.2008<br>
 * Beschreibung	: Mapping of FSS-Format AUP into KIDS-Format of Investigation.
 * 
 * @author houdek
 * @version 5.0.00
 */
public class MapAUPToExpFup extends KidsMessage {
	
	private MsgAUP msgAUP;
	private MsgExpNer msgExpNer;
	
	public MapAUPToExpFup() {
		msgExpNer = new MsgExpNer();
	}

	public void setMsgAUP(MsgAUP msgAUP) {
    	this.msgAUP = msgAUP;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
    	
	msgExpNer.setDocumentReferenceNumber(msgAUP.getAupSubset().getBeznr());
	msgExpNer.setResponseUntil(msgAUP.getAupSubset().getSawdat());
	msgExpNer.setDateOfInvestigation(msgAUP.getAupSubset().getNfgdat());
    }
	
	public String getMessage() {
    StringWriter xmlOutputString = new StringWriter();
    
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    try {
        writer = factory.createXMLStreamWriter(xmlOutputString);

        writeStartDocument(encoding, "1.0");
        openElement("soap:Envelope");
        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
        
        KidsHeader             header = new KidsHeader(writer);
        header.setHeaderFields(msgAUP.getVorSubset());
        header.setMessageName("Investigation");
        header.setMessageID(getMsgID());
        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(header.getReceiver());
        commonFieldsDTO.setCountryCode(header.getCountryCode());
        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
        header.setCommonFieldsDTO(commonFieldsDTO);	  
        
        header.writeHeader();
        
        BodyExportInvestigationKids body   = new BodyExportInvestigationKids(writer);
        body.setMsgExpNer(msgExpNer);
        body.setKidsHeader(header);

        getCommonFieldsDTO().setReferenceNumber(msgExpNer.getDocumentReferenceNumber());
        body.writeBody();
        
        closeElement();  // soap:Envelope
        writer.writeEndDocument();
        
        writer.flush();
        writer.close();
        
        Utils.log("(MsgExportInvestigation getMessage) Msg = " + xmlOutputString.toString());
        
    } catch (XMLStreamException e) {
        
        e.printStackTrace();
    }

    
    return xmlOutputString.toString();
}

}
