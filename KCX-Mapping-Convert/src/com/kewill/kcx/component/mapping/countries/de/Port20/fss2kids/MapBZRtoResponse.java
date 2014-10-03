package com.kewill.kcx.component.mapping.countries.de.Port20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationRegistration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationTerminal;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgBZR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAFE;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyErrorInformationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyPortDeclarationRegistrationKids;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyPortDeclarationTerminalKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : Port<br>
 * Created      : 26.08.2013<br>
 * Description	: Mapping of FSS BZR to KIDS MsgErrInf: is ResponseMessage of Harbour (also Errors)
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapBZRtoResponse extends KidsMessage {
	
	private MsgBZR msgBZR;	
	private MsgErrInf errorMessage;
	private MsgPortDeclarationRegistration regMessage;
	private MsgPortDeclarationTerminal terminalMessage;   //EI20130604
	private String beznr = "";
	
	public MapBZRtoResponse() {		
		errorMessage = new MsgErrInf();
		regMessage = new MsgPortDeclarationRegistration();
		terminalMessage = new MsgPortDeclarationTerminal();
		msgBZR = new MsgBZR();			
	}
	
	public void setMsgBZR(MsgBZR argument) {
		this.msgBZR = argument;						
    	//this.setMsgFields();    	
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
	        //header.setHeaderFields(msgBZR.getVorSubset());	       
	        header.setHeaderFieldsFromHead(msgBZR.getVorSubset(), msgBZR.getHeadSubset());  //EI20130826
	        header.setMessageID(getMsgID());
	        header.setMethod("JOB");              
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        getCommonFieldsDTO().setReferenceNumber(msgBZR.getAkoSubset().getBeznr());   
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        boolean isErr = true;    
	        String art = "";
	        if (msgBZR.getAkoSubset() != null && !Utils.isStringEmpty(msgBZR.getAkoSubset().getArt())) {           	
	        	art = msgBZR.getAkoSubset().getArt().trim();
	        	if (art.equalsIgnoreCase("AP") || art.equalsIgnoreCase("UV")) {
	        		isErr = false;
	        	} 
	        }
	       	      
	        if (msgBZR.getAfeSubsetList() == null && msgBZR.getAkoSubset() != null && !isErr) {  
	        	if (art.equalsIgnoreCase("AP")) {   //EI20130604	        		  
	        		header.setMessageName("PortDeclarationRegistration");	        	
	        		header.writeHeader();
	        		this.setMsgRegistrationFields(); 
	        	
	        		BodyPortDeclarationRegistrationKids body   = new BodyPortDeclarationRegistrationKids(writer);
	        		body.setMessage(regMessage);
	        		body.writeBody();
	        	} else {	        		
	        		//UV war bisher als ErrorMessage, jetzt wird eine "leere" Nachricht (ob das nicht schief geht???
		        	//es könnte als PortDeclarationStatus gehen, aber ob es beim Kunden zu Problemen führen würde???
		        	//also lasse ich sie als "PortDeclarationTerminal" an der kids2kff ausfallen	        			        		
	                header.setMessageName("localAppResult");	               	    	        
	                header.writeHeader();
	        	} 
	        	
	        } else {	        		        
	        	header.setMessageName("ErrorMessage");
	        	header.writeHeader();	        	
	        	this.setMsgErrorFields(); 
	            
	        	BodyErrorInformationKids body   = new BodyErrorInformationKids(writer);
	        	body.setMsgErrInf(errorMessage);
	        	body.writeBody();	        	
	        }
            
            
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapVFIToNCTSArrivalRejection getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
	
	public void setMsgRegistrationFields() {	//AkoSubset().getArt() = "AP"
		
		if (msgBZR.getAkoSubset() != null) {
			regMessage.setReferenceNumber(msgBZR.getAkoSubset().getBeznr());
			regMessage.setPortSystem(msgBZR.getAkoSubset().getFrom());			
			regMessage.setSendingDateTime(msgBZR.getAkoSubset().getDate()); //dateTime 						
			regMessage.setPortRegistrationNumber(msgBZR.getAkoSubset().getRegnr());
			regMessage.setPortRegistrationMode(msgBZR.getAkoSubset().getStacod());
			regMessage.setPortContactName(msgBZR.getAkoSubset().getAnspna());
			regMessage.setPortContactPhone(msgBZR.getAkoSubset().getAnspte());
			regMessage.setPortContactEmail(msgBZR.getAkoSubset().getAnspem());		
		}
	}
	public void setMsgTerminalFields() {	//AkoSubset().getArt() = "UV" 
		
		if (msgBZR.getAkoSubset() != null) {
			terminalMessage.setReferenceNumber(msgBZR.getAkoSubset().getBeznr());
			terminalMessage.setPortSystem(msgBZR.getAkoSubset().getFrom());			
			terminalMessage.setSendingDateTime(msgBZR.getAkoSubset().getDate()); //dateTime 						
			terminalMessage.setPortRegistrationNumber(msgBZR.getAkoSubset().getRegnr());
			terminalMessage.setTerminal(msgBZR.getAkoSubset().getKaicde());			
		}
	}
	public void setMsgErrorFields() {	
		
		if (msgBZR.getAkoSubset() != null) {
			//UCRNumber
			errorMessage.setDeclarationTime(msgBZR.getAkoSubset().getDate());  
			//ReceiveTime
			errorMessage.setUCROtherSystem(msgBZR.getAkoSubset().getRegnr());
			errorMessage.setReferenceNumber(msgBZR.getAkoSubset().getBeznr());
			//OrderNumber
			errorMessage.setProcedureType("PORT");
			
			Party party = new Party();
			ContactPerson contact = new ContactPerson();
			contact.setName(msgBZR.getAkoSubset().getAnspna());
			contact.setPhoneNumber(msgBZR.getAkoSubset().getAnspte());
			contact.setEmail(msgBZR.getAkoSubset().getAnspem());				
			party.setContactPerson(contact);
			errorMessage.setCustomsOffice(party);  
			
			//msgBZR.getAkoSubset().getArt()  //DAK/BHT
			//message.setCorrectionNumber(msgBZR.getAkoSubset().getLfdnr());			
		}
		String lfd = "";
		int i = 0;
		if (msgBZR.getAfeSubsetList() != null) {
			for (TsAFE afe : msgBZR.getAfeSubsetList()) {			
				if (afe != null) {
					i = i + 1;
					ErrorType tmpError = new ErrorType();								
					lfd = lfd + (i + 1);  
					tmpError.setUniqueNumber(lfd);
					tmpError.setCode(afe.getErrcode());
					tmpError.setText(afe.getErrdic());
					//tmpError.setPointer(afe.getZeiger());					
					//tmpError.setNumber(number);
					errorMessage.addErrorList(tmpError);
				}
			}
		}
	}			

}