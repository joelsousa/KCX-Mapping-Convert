/*
 * Function    : UnisysToKidsICS.java
 * Titel       :
 * Date        : 03.11.2010
 * Author      : Kewill CSF / krzoska
 * Description : transformer to convert Unisys-Format to KIDS ICS messages
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
package com.kewill.kcx.component.mapping.companies.unisys.ics;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids.MapArrivalNotificationUniK;
import com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids.MapDeclarationAmendmentUniK;
import com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids.MapDiversionRequestUniK;
import com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids.MapEntrySummaryDeclarationUniK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer called to convert Unisys-Format to KIDS ICS messages.
 * @author krzoska
 * @version 1.0.00
 */
public class UnisysToKidsICS {

	public String readUnisys(XMLEventReader parser, String encoding, UnisysHeader unisysHeader,
	                                                    CommonFieldsDTO commonFieldsDTO) throws Exception {                
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        
        KidsHeader kidsHeader     = new KidsHeader(writer);  
        
    	kidsHeader.setMethod("ESU");    
    	kidsHeader.setRelease("1.0.00");
        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }       
        kidsHeader.setReceiver(commonFieldsDTO.getKcxId()); 
        kidsHeader.setCountryCode(commonFieldsDTO.getCountryCode());
        kidsHeader.setProcedure(commonFieldsDTO.getProcedure());  
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
              
        String msg = unisysHeader.getMsgDocType();
        Utils.log("(UnisysToKidsICS readUnisys) unisysHeader " + msg);
        switch (EUnisysICSMessages.valueOf(msg)) {
        case IE315:    //Declare
        	MapEntrySummaryDeclarationUniK mapEntrySummaryDeclaration  =
        		new MapEntrySummaryDeclarationUniK(parser, kidsHeader, encoding);
        	prepareMessage(unisysHeader, kidsHeader, mapEntrySummaryDeclaration, commonFieldsDTO,
                    "ICSEntrySummaryDeclaration", direction);        	
        	mapEntrySummaryDeclaration.getMessage(writer);
        	break;
        case IE313:   //Amend
        	MapDeclarationAmendmentUniK mapDeclarationAmendment  =
        		new MapDeclarationAmendmentUniK(parser, kidsHeader, encoding);
        	prepareMessage(unisysHeader, kidsHeader, mapDeclarationAmendment, commonFieldsDTO,
                    "ICSDeclarationAmendment", direction);
        	mapDeclarationAmendment.getMessage(writer);
        	break;
        case IE347:    //Arrival
        	MapArrivalNotificationUniK mapArrivalNotification  =
        		new MapArrivalNotificationUniK(parser, kidsHeader, encoding);
        	prepareMessage(unisysHeader, kidsHeader, mapArrivalNotification, commonFieldsDTO,
                    "ICSArrivalNotification", direction);
        	kidsHeader.setMethod("ARN");  //EI20110119 
        	mapArrivalNotification.getMessage(writer);
        	break;
        case IE323:    //Diversion
        	MapDiversionRequestUniK mapDiversionRequest  =
        		new MapDiversionRequestUniK(parser, kidsHeader, encoding);
        	prepareMessage(unisysHeader, kidsHeader, mapDiversionRequest, commonFieldsDTO,
                    "ICSDiversionRequest", direction);
        	kidsHeader.setMethod("DIV");  //EI20110119 
        	mapDiversionRequest.getMessage(writer);
        	break;
        case Response:
        	Utils.log("(MSG-RESPONSE not implemented");
        	break;
        
   		default: throw new FssException("Unknown message type " + msg);
        }

        String xml = bos.toString();
        return xml;        
    }

	private void prepareMessage(UnisysHeader unisysHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
	                                                                            throws XMLStreamException {
		message.kidsMessageName = messageName;
        mapUnisysToKidsHeader(unisysHeader, kidsHeader, messageName);
        setKidsHeaderMappingFields(unisysHeader, kidsHeader, direction);         
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
	
    private void mapUnisysToKidsHeader(UnisysHeader unisysHeader, KidsHeader kidsHeader, String messageName) {
    	
    	kidsHeader.setMessageName(messageName);     	    	 
        kidsHeader.setTransmitter(unisysHeader.getMsgSender());       
    	kidsHeader.setMessageID(unisysHeader.getMsgId());
        
    	kidsHeader.setDay(unisysHeader.getDay());
    	kidsHeader.setMonth(unisysHeader.getMonth());
    	kidsHeader.setYear(unisysHeader.getYear());
    	kidsHeader.setTime(unisysHeader.getTime()); 
    }
    private void setKidsHeaderMappingFields(UnisysHeader unisysHeader, KidsHeader kidsHeader, EDirections direction) {
    	String countryTo = "";
    	String mappingCode = "";
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), 
        															        kidsHeader.getProcedure().toUpperCase());
        if (kidsHeader != null) {
        	countryTo = kidsHeader.getCountryCode();
        }
        if (customerProcedureDTO != null) {
        	mappingCode = customerProcedureDTO.getMappingCode();
        	kidsHeader.getCommonFieldsDTO().setCustomerProcedureDTO(customerProcedureDTO);
        }
        // Wenn Nachricht von Kunde Richtung Zoll,  dann ist mapFrom = mappingCode
        // und mapTo = countryCode.
        // Wenn Nachricht von Zoll  Richtung Kunde, dann ist mapFrom = countryCode
        // und mapto = mappingCode.
             
        if (mappingCode.equalsIgnoreCase("EU")) {   //EI20110126
        	kidsHeader.setMap("0");
        } else {
        	if (direction == EDirections.CustomerToCountry) {
        		kidsHeader.setMapFrom(mappingCode);           
        		kidsHeader.setMapTo(countryTo);
        	} else {
        		kidsHeader.setMapFrom(countryTo);
        		kidsHeader.setMapTo(mappingCode);
        	}
        
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
        		kidsHeader.setMap("0");
        	} else {
        		kidsHeader.setMap("1");
        	}
        }
    }
}
