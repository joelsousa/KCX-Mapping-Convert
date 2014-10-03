package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpUrg;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgAUG;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportReminderKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/AES21<br>
 * Created		: 30.07.2012<br>
 * Description	: Mapping of FSS-Format AUG into KIDS-Format of ExportReminder.
 * 
 * @author iwaniuk
 * @version 2.1.00
 * 
 * Changes:
 * ----------
 * EI20121005  : Header:MessageID and InReplyTo will be filled from TsVOR (and TsVOR from TsHEAD) 
 */

public class MapAUGToExpUrg extends KidsMessage {
	
	private MsgAUG msgAUG;
	private MsgExpUrg message;
	
	public MapAUGToExpUrg() {
		message = new MsgExpUrg();
	}

	public void setMsgAUG(MsgAUG msg) {
    	this.msgAUG = msg;
    	this.setMsgFields();
    }
	
	public void setMsgFields() {
    	if (msgAUG.getAugSubset() == null) {
    		return;
    	}
		message.setReferenceNumber(msgAUG.getAugSubset().getBeznr());
		message.setDateOfLatestPossibleReply(msgAUG.getAugSubset().getSawdat());
		message.setDateOfReminder(msgAUG.getAugSubset().getAnmdat());
		message.setUCRNumber(msgAUG.getAugSubset().getMrn());    					 //EI20121004
		message.setCustomsOfficeForCompletion(msgAUG.getAugSubset().getEamdst());    //EI20121004
		if (msgAUG.getAdrList() != null) {
			mapAdrToKids();
		}
    }
	
	public String getMessage() {
    StringWriter xmlOutputString = new StringWriter();
    
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    try {
        writer = factory.createXMLStreamWriter(xmlOutputString);

        writeStartDocument(encoding, "1.0");
        openElement("soap:Envelope");
        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
        
        KidsHeader header = new KidsHeader(writer);
        //header.setHeaderFields(msgAUG.getVorSubset());
        header.setHeaderFieldsFromHead(msgAUG.getVorSubset(), msgAUG.getHeadSubset());     //EI20121005
        header.setMessageName("ExportReminder");
       
        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(header.getReceiver());
        commonFieldsDTO.setCountryCode(header.getCountryCode());
        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
        header.setCommonFieldsDTO(commonFieldsDTO);
        
        header.writeHeader();
        
        BodyExportReminderKids body   = new BodyExportReminderKids(writer);
        body.setMessage(message);
        body.setKidsHeader(header);

        getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
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

	private void mapAdrToKids() { 
		if (msgAUG.getAdrList() == null) {
			return;
		}					
		for (TsADR tmpTsAdr : msgAUG.getAdrList()) {	
			if (tmpTsAdr != null) {
				String typ = "";
  		  		if (!Utils.isStringEmpty(tmpTsAdr.getTyp())) {
  		  			typ = tmpTsAdr.getTyp();
  		  		}
  		  		Address tmpAdr = new Address();
  		  		String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();      				           
  		  		tmpAdr.setName(tmpName);
  		  		tmpAdr.setStreet(tmpTsAdr.getStr());
  		  		tmpAdr.setCity(tmpTsAdr.getOrt());
  		  		tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
  		  		tmpAdr.setCountry(tmpTsAdr.getLand());	
  			
  		  		TIN tmpTIN = new TIN();      			
  		  		tmpTIN.setCustomerIdentifier(tmpTsAdr.getKdnr());
  		  		tmpTIN.setTIN(tmpTsAdr.getTin());
  		  		tmpTIN.setBO(tmpTsAdr.getNl());
  		  		tmpTIN.setIdentificationType(tmpTsAdr.getDtzo());
  			
  		  		Party tmpParty = new Party();
  		  		tmpParty.setETNAddress(tmpTsAdr.getEtn()); 
  		  		if (!tmpAdr.isEmpty()) {
  		  			tmpParty.setAddress(tmpAdr);  
  		  		}
  		  		if (!tmpTIN.isEmpty()) {
  		  			tmpParty.setPartyTIN(tmpTIN); 
  		  		}
  		  		if (tmpParty.isEmpty()) {
  		  			return;	
  		  		}
			
  		  		if (typ.equals("1")) {                     //Consignor == ausfuehrer
  		  			message.setConsignor(tmpParty);	    			
  		  		}           	
  		  		if (typ.equals("3")) {                     //Declarant == anmelder														
  		  			message.setDeclarant(tmpParty);	  			 
  		  		}		
  		  		if (typ.equals("4")) {                     //Agent	== vertreter												 
  		  			message.setAgent(tmpParty);  			   
  		  		}											
  		  		if (typ.equals("5")) {                     //Subcontractor == subunternehmer										
  		  			message.setSubcontractor(tmpParty);	  			    
  		  		}
		  }
		}
	}
}
