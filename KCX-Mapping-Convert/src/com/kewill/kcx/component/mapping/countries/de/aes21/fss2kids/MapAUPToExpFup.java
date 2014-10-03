package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpFup;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgAUP;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportInvestigationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/AES21<br>
 * Created		: 30.07.2012<br>
 * Description	: Mapping of FSS-Format AUP into KIDS-Format of Investigation. 				
 * 
 * @author iwaniuk
 * @version 2.1.00
 * 
 * Changes:
 * ----------
 * EI20120730: MsgAUP is not new, only MsgExpFup has new Tags for V21
 * EI20121005: Header:MessageID and InReplyTo will be filled from TsVOR (and TsVOR from TsHEAD) 
 * EI20130808: MsgAUP will be imported from ...formats.fss.aes.messages.V70 (new Tags in TsAUP for AES22: artwvl,svldat)
 */

public class MapAUPToExpFup extends KidsMessage {
	
	private MsgAUP msgAUP;
	private MsgExpFup msgExpFup;
	
	public MapAUPToExpFup() {
		msgExpFup = new MsgExpFup();
	}

	public void setMsgAUP(MsgAUP msgAUP) {
    	this.msgAUP = msgAUP;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
    	if (msgAUP == null) {
    		return;
    	}
    	if (msgAUP.getAupSubset() == null) {
    		return;
    	}
		msgExpFup.setReferenceNumber(msgAUP.getAupSubset().getBeznr());
		msgExpFup.setDateOfLatestPossibleReply(msgAUP.getAupSubset().getSawdat());
		msgExpFup.setDateOfInquiry(msgAUP.getAupSubset().getNfgdat());
		msgExpFup.setDateOfLatestResponseOfRequest(msgAUP.getAupSubset().getSvldat());  //EI20130808
		msgExpFup.setRequestCode(msgAUP.getAupSubset().getArtwvl());          //EI20130808
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
        //header.setHeaderFields(msgAUP.getVorSubset());
        header.setHeaderFieldsFromHead(msgAUP.getVorSubset(), msgAUP.getHeadSubset());     //EI20121005
        header.setMessageName("Investigation");
       
        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(header.getReceiver());
        commonFieldsDTO.setCountryCode(header.getCountryCode());
        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
        header.setCommonFieldsDTO(commonFieldsDTO);
        
        header.writeHeader();
        
        BodyExportInvestigationKids body   = new BodyExportInvestigationKids(writer);
        body.setMessage(msgExpFup);
        body.setKidsHeader(header);

        getCommonFieldsDTO().setReferenceNumber(msgExpFup.getReferenceNumber());
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
