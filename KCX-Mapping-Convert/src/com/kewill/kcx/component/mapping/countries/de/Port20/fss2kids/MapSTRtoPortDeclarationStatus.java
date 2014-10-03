package com.kewill.kcx.component.mapping.countries.de.Port20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationStatus;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.StatusAnnotation;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgSTR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsSTT;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyPortDeclarationStatusKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : Port<br>
 * Created      : 19.01.2012<br>
 * Description	: Mapping of FSS STR to KIDS PortDeclarationStatus.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapSTRtoPortDeclarationStatus extends KidsMessage {
	
	private MsgSTR msgSTR;	
	private MsgPortDeclarationStatus message;
	private String beznr = "";
	
	public MapSTRtoPortDeclarationStatus() {		
		message = new MsgPortDeclarationStatus();
		msgSTR = new MsgSTR();			
	}
	
	public void setMsgSTR(MsgSTR argument) {
		this.msgSTR = argument;						
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
	        //header.setHeaderFields(msgSTR.getVorSubset());
	        header.setHeaderFieldsFromHead(msgSTR.getVorSubset(), msgSTR.getHeadSubset());  //EI20130826
	        header.setMessageName("PortDeclarationStatus");
	        header.setMessageID(getMsgID());
	        header.setMethod("JOB");             
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyPortDeclarationStatusKids body   = new BodyPortDeclarationStatusKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
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
			
	public void setMsgFields() {	
	
		if (msgSTR.getStrSubset() != null) {
			message.setReferenceNumber(msgSTR.getStrSubset().getBeznr());			
			message.setPortSystem(msgSTR.getStrSubset().getFrom());
			message.setSendingDateTime(msgSTR.getStrSubset().getDate());  
			message.setStatusSender(msgSTR.getStrSubset().getAbsend());
			message.setSequenceNumber(msgSTR.getStrSubset().getLfdnr());
			message.setMrn(msgSTR.getStrSubset().getMrn());
			message.setPortRegistrationNumber(msgSTR.getStrSubset().getRegfsy());
			message.setPortRegistrationMode(msgSTR.getStrSubset().getRegnrs());
			message.setStatusKind(msgSTR.getStrSubset().getArt());
			message.setStatusCode(msgSTR.getStrSubset().getStacod());
			message.setStatusType(msgSTR.getStrSubset().getArt1());
			message.setDateOfStatus(msgSTR.getStrSubset().getDatsta());
			message.setStatusDescription(msgSTR.getStrSubset().getStatxt());
			message.setDateOfControl(msgSTR.getStrSubset().getDatkon());
			message.setControlDescription(msgSTR.getStrSubset().getContrl());
			message.setControlAnnotation(msgSTR.getStrSubset().getContrl1());
			message.setDateOfInterdiction(msgSTR.getStrSubset().getDatunt());
			message.setDateOfFinal(msgSTR.getStrSubset().getDatend());
			message.setDateOfExitAllowance(msgSTR.getStrSubset().getDatclr());
			message.setContainerNumber(msgSTR.getStrSubset().getConnr());
			
			String oldKey = "";
			String newKey = "";        
			StatusAnnotation meldung = new StatusAnnotation();
			
			if (msgSTR.getSttSubsetList() != null) {
				for (TsSTT stt : msgSTR.getSttSubsetList()) {
					if (stt != null) {   
						newKey = stt.getTxtart();						
						if (!newKey.equalsIgnoreCase(oldKey)) {
							oldKey = newKey;
							meldung = new StatusAnnotation();								
							meldung.setAnnotationKey(stt.getTxtart());	
							meldung.addTextList(stt.getText());
							message.addStatusAnnotationList(meldung);	
						} else {
							meldung.addTextList(stt.getText());
						}				
					}
				}		
			}
		}
		
	}

}