package com.kewill.kcx.component.mapping.countries.ie;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.ie.ie2kids.MapConfirmIK;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : IrelandToKids<br>
 * Created : 23.05.2014<br>
 * Description : Converts KIDS-Format to Ireland messages format.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class IrelandToKids {

	
	
	
	 //TODO
	
	
	
	
	public String readIreland(XMLEventReader parser, String encoding, IrelandHeader irelandHeader,
			CommonFieldsDTO commonFieldsDTO) throws Exception {
        String      countryCode = commonFieldsDTO.getCountryCode();
        String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        
        KidsHeader kidsHeader     = new KidsHeader(writer);
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        kidsHeader.setMethod("ESU");     
        kidsHeader.setProcedure("ICS");   
        
        if (direction == EDirections.CustomerToCountry) { 
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }
        
        String msg = irelandHeader.getMessageName();
        Utils.log("(IrelandToKids readIreland) irelandHeader.getMessageName " + msg);
        switch (EIrelandMessages.valueOf(msg)) {
        
        case MessageAcknowledgement:
        	MapConfirmIK mapConfirmIK = new MapConfirmIK(parser, kidsHeader, encoding);
        	prepareMessage(irelandHeader, kidsHeader, mapConfirmIK, commonFieldsDTO, "localAppResult", direction);
   			mapConfirmIK.getMessage(writer);
        	break;
        	
        case IM528:
        	
        	/*
        	MapICSEntrySummaryDeclarationAcknowledgmentCK mapICSESDAcknowledgmentCK =
        			new MapICSEntrySummaryDeclarationAcknowledgmentCK(parser, kidsHeader, encoding, irelandHeader);
        	prepareMessage(irelandHeader, kidsHeader, mapICSESDAcknowledgmentCK, commonFieldsDTO,
        			"ICSEntrySummaryDeclarationAcknowledgment", direction);
        	mapICSESDAcknowledgmentCK.getMessage(writer);
        	break;        // MS20110704
        	
        
   			*/
        	break;
        case IM529:   	//Release
        	break;
        case IM560:		//ControlNotification
        	break;
        case IM516:		//Rejection
        	break;
        case IM509:		//CancelationDecision
        break;
        
        default: 
        	throw new FssException("Unknown message type " + msg);
        }
        
        String xml = bos.toString();
        return xml;
	}
	
	
	
	private XMLStreamWriter getWriter(ByteArrayOutputStream bos, String encoding) throws Exception {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer   = factory.createXMLStreamWriter(osw);

	    return writer;
	}
	
	private void prepareMessage(IrelandHeader irelandHeader, KidsHeader kidsHeader, KidsMessage message,
            CommonFieldsDTO commonFieldsDTO, String messageType, EDirections direction) 
                                                            throws XMLStreamException {
	//todo:	mapIrelandToKidsHeader(irelandHeader, kidsHeader, messageType);
	//todo: setKidsHeaderMappingFields(irelandHeader, kidsHeader, direction);
	//todo: message.setUidsHeader(irelandHeader);
		message.setCommonFieldsDTO(commonFieldsDTO);
	}
	private KidsHeader mapIrelandToKidsHeader(CyprusHeader irelandHeader, KidsHeader kidsHeader, String messageName) {
		
		kidsHeader.setMessageName(messageName);		
		kidsHeader.setCountryCode(kidsHeader.getCommonFieldsDTO().getCountryCode());  //EI20110706       
     	kidsHeader.setReceiver(kidsHeader.getCommonFieldsDTO().getKcxId());           //EI20110706
		kidsHeader.setTransmitter(irelandHeader.getMesSenMES3());      //EI20110706
    			
		kidsHeader.setDay(irelandHeader.getDay());
    	kidsHeader.setMonth(irelandHeader.getMonth());
    	kidsHeader.setYear(irelandHeader.getYear());
    	kidsHeader.setTime(irelandHeader.getTime());
    	kidsHeader.setTimeZone(irelandHeader.getTimezone());
    	    	    	 	    
        // MS20110816 Begin
        // ist falsch und wurde sowieso schon weiter oben besetzt
        // kidsHeader.setDirection("FROM_CUSTOMER");
        // MS20110816 End
    	      
  		//if (irelandHeader.getSchemaVersion() == null) {
  		//	Utils.log("(CyprusToKidsICS mapCyprusToKidsHeader) getMessageVersion liefert null!");
  			kidsHeader.setRelease("1.0.00");
  		//} else if (irelandHeader.getSchemaVersion().equals("2.0")) {
  		//	kidsHeader.setRelease("2.0.00");  			

    	kidsHeader.setMessageID(irelandHeader.getMesIdeMES19());   //EI20110707     	
    	//kidsHeader.setInReplyTo(irelandHeader.getCorIdeMES25());   //EI20110707 wird im Body nachgefüllt

    	return kidsHeader;
	}
}
