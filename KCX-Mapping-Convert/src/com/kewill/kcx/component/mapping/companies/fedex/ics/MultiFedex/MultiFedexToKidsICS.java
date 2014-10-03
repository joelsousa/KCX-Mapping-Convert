/*
 * Function    : FedexToKidsICS.java
 * Titel       :
 * Date        : 03.11.2010
 * Author      : Kewill CSF / krzoska
 * Description : transformer to convert Fedex-Format to KIDS ICS messages
 *             :
 * Parameters  :

 * Changes
 * ------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.companies.fedex.ics.MultiFedex;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.fedex.ics.EFedexICSMessages;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Transformer called to convert Fedex-Format to KIDS ICS messages.
 * @author krzoska
 * @version 1.0.00
 */
public class MultiFedexToKidsICS {

	public String readFedex(XMLEventReader parser, String encoding, FedexHeader fedexHeader,
	                                                    CommonFieldsDTO commonFieldsDTO) throws Exception {
        String      countryCode = commonFieldsDTO.getCountryCode();
        String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        KidsHeader kidsHeader     = new KidsHeader(writer);
        //kidsHeader.setReceiver(kcxId);  // <== TODO-iwa: wird in DB gespeicher?        

        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }

        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
        String msg = "MessageIE315";
        Utils.log("(FedexToKidsICS readFedex) fedexHeader " + msg);
        switch (EFedexICSMessages.valueOf(msg)) {
        case MessageIE315:
        	MultiMapICSEntrySummaryDeclarationFK mapICSEntrySummaryDeclarationFK  =
        		new MultiMapICSEntrySummaryDeclarationFK(parser, kidsHeader, encoding);
        	prepareMessage(fedexHeader, kidsHeader, mapICSEntrySummaryDeclarationFK, commonFieldsDTO,
                    "ICSEntrySummaryDeclaration", direction);
        	mapICSEntrySummaryDeclarationFK.getMessages();
        	break;
   		default: throw new FssException("Unknown message type " + msg);
        }

        String xml = bos.toString();
        return xml;
    }

	private void prepareMessage(FedexHeader fedexHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
	                                                                            throws XMLStreamException {
        mapFedexToKidsHeader(fedexHeader, kidsHeader, messageName);
        //setKidsHeaderMappingFields(fedexHeader, kidsHeader, direction); <== verlegt in MapICS...
        //TODO-iwa: message.setUidsHeader(uidsHeader);  <== wozo soll es gut sein???
        message.setCommonFieldsDTO(commonFieldsDTO);
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
	
    private KidsHeader mapFedexToKidsHeader(FedexHeader fedexHeader, KidsHeader kidsHeader, String messageName) {
    	
    	kidsHeader.setMessageName(messageName); 
    	// Wird bereits vor dem Aufruf  mit der KewillId besetzt.
     	//kidsHeader.setReceiver(fedexHeader.getTo());    	
    	kidsHeader.setTransmitter(fedexHeader.getPartyId());
    	
    	kidsHeader.setDay(fedexHeader.getDay());
    	kidsHeader.setMonth(fedexHeader.getMonth());
    	kidsHeader.setYear(fedexHeader.getYear());
    	kidsHeader.setTime(fedexHeader.getTime());
    	kidsHeader.setTimeZone(fedexHeader.getTimezone());
 	  
    	kidsHeader.setCountryCode(fedexHeader.getCountryCode());   
    	kidsHeader.setProcedure("ICS");    	
    	kidsHeader.setMethod("Import");     
    	      
  		if (fedexHeader.getSchemaVersion() == null) {
  			Utils.log("(FedexToKidsICS mapFedexToKidsHeader) getMessageVersion liefert null!");
  			kidsHeader.setRelease("1.0.00");
  		} else if (fedexHeader.getSchemaVersion().equals("2.0")) {
  			kidsHeader.setRelease("2.0.00");  			

  		} else  { 
  			kidsHeader.setRelease("1.0.00");  		
  		}    	
    	kidsHeader.setMessageID(fedexHeader.getTransactionId());        	
    	//kidsHeader.setInReplyTo(fedexHeader.getInreplyto());

    	return kidsHeader;
    }
   /*
    private void setKidsHeaderMappingFields(FedexHeader fedexHeader, KidsHeader kidsHeader, EDirections direction) {
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), 
        		                                                           kidsHeader.getProcedure().toUpperCase());
        customerProcedureDTO.setMsgFormat("FEDEX");  
        customerProcedureDTO.setUidsVersion("");     //iwa
        String mappingCode = customerProcedureDTO.getMappingCode();
        Utils.log("(FedexToKidsICS setKidsHeaderMappingFields) mappingCode = " + mappingCode);
               
        // Wenn Nachricht von Kunde Richtung Zoll,  dann ist mapFrom = mappingCode
        //                                               und mapTo = countryCode. 
        // Wenn Nachricht von Zoll  Richtung Kunde, dann ist mapFrom = countryCode
        //                                               und mapto = mappingCode.
        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setMapFrom(mappingCode);
            kidsHeader.setMapTo(fedexHeader.getCountryCode());
        } else {
            kidsHeader.setMapFrom(fedexHeader.getCountryCode());
            kidsHeader.setMapTo(mappingCode);
        }        
        Utils.log("(FedexToKidsICS setKidsHeaderMappingFields) " +
        		                "kidsHeader.getMapFrom() = " + kidsHeader.getMapFrom());
        Utils.log("(FedexToKidsICS setKidsHeaderMappingFields) " +
        		                "kidsHeader.getMapTo()   = " + kidsHeader.getMapTo());
        if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
            kidsHeader.setMap("0");
        } else {
            kidsHeader.setMap("1");
        }        
    }
   */
}
