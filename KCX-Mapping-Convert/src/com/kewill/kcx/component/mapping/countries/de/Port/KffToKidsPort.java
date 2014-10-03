/*
 * Function    : KffToKidsICS.java
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
package com.kewill.kcx.component.mapping.countries.de.Port;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.Port.kff2kids.MapBillOfLadingKff2K;
import com.kewill.kcx.component.mapping.countries.de.Port.kff2kids.MapPortDeclarationKff2K;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kff.msg.JobHeader;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgJOB;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgMultiJOB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Transformer called to convert Kff-JOB-Format to KIDS ICS messages.
 * @author iwaniuk
 * @version 1.0.00
 */
public class KffToKidsPort {

	public String readKff(XMLEventReader parser, String encoding, JobHeader kffHeader, CommonFieldsDTO commonFieldsDTO)
																									throws Exception {
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        
        KidsHeader kidsHeader     = new KidsHeader(writer);  
        
    	//kidsHeader.setMethod("PORT");    
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
                     
        String msg = "PORT";        
     // EI20130517MsgJOB msgKff  = new MsgJOB(parser);
     // EI20130517msgKff.parse(HeaderType.KFF);
        // EI20130517
        MsgJOB msgKff  = new MsgJOB();
        MsgMultiJOB msgMultiKff = new MsgMultiJOB(parser);
        msgMultiKff.parse(HeaderType.KFF);         
        msgKff = msgMultiKff.mapMsgJob();
       //*/
                     
        if (msgKff != null && msgKff.getJobKCX() != null) {
        	String jobTypeDak = msgKff.getJobKCX().getDakosyMsgType();
        	String jobTypeDbh = msgKff.getJobKCX().getDBHMsgType();
        	if (!Utils.isStringEmpty(jobTypeDak)) {     	
        		if (jobTypeDak.equals("0")) {
        			msg = "PORT";
        		} else if (jobTypeDak.equals("1")) {
        			msg = "BL";
        		} else {
        			//msg = "";  
        			msg = "PORT";   //EI20120808
        		}
        	} else if (!Utils.isStringEmpty(jobTypeDbh)) {
        		if (jobTypeDbh.equals("0")) {
        			msg = "PORT";
        		} else if (jobTypeDbh.equals("1")) {
        			msg = "BL";
        		} else {
        			//msg = "";  
        			msg = "PORT";   //EI20120808
        		}
        	} else {
        		//msg = "";
        		msg = "PORT";   //EI20120807
        	}
        }

        kidsHeader.setMethod(msg); 

        // AK20120730 kidsHeader.setMessageID(msgKff.getUnid()); //EI20120807: warum hat AK es auskommentiert???      
       
        Utils.log("(KffToKidsPort readKff) kffHeader " + msg);
        switch (EKffMessages.valueOf(msg)) {
        case PORT:                                      //Hafenauftrag
        	kidsHeader.setMessageID(msgKff.getUnid()); //EI20130603: warum hat AK es oben auskommentiert? wg. BL?
        	MapPortDeclarationKff2K mapPortDeclaration = new MapPortDeclarationKff2K(msgKff, kidsHeader, encoding);        	
        	prepareMessage(kffHeader, kidsHeader, mapPortDeclaration, commonFieldsDTO, "PortDeclaration", direction);
        	mapPortDeclaration.getMessage(writer);   
        	break;    
        case BL:                                         //BillOfLading  //EI20120410	
        	MapBillOfLadingKff2K mapBillOfLading = new MapBillOfLadingKff2K(msgKff, kidsHeader, encoding);        	
        	prepareMessage(kffHeader, kidsHeader, mapBillOfLading, commonFieldsDTO, "BillOfLading", direction);
        	mapBillOfLading.getMessage(writer);   
        	break;        
        
   		default: 
   			throw new FssException("Unknown message type " + msg);
        }

        String xml = bos.toString();
        return xml;        
    }
	     

	private void prepareMessage(JobHeader kffHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
	                                                                            throws XMLStreamException {
		message.kidsMessageName = messageName;
        mapPortToKidsHeader(kffHeader, kidsHeader, messageName);
        setKidsHeaderMappingFields(kffHeader, kidsHeader, direction);         
        message.setCommonFieldsDTO(commonFieldsDTO);
	}
	
    private void mapPortToKidsHeader(JobHeader kffHeader, KidsHeader kidsHeader, String messageName) {
    	
    	kidsHeader.setMessageName(messageName);     
    	
        kidsHeader.setTransmitter(kffHeader.getSenderUserId());       
    	//kidsHeader.setMessageID(kffHeader.getBatchNo());  //EI20120425: now will be filed with UNID 
    	kidsHeader.setDay(kffHeader.getDay());
    	kidsHeader.setMonth(kffHeader.getMonth());
    	kidsHeader.setYear(kffHeader.getYear());
    	kidsHeader.setTime(kffHeader.getTransmissionTime()); 
    	kidsHeader.setTimeZone(kffHeader.getTransmissionTimeZone());    	
    }
    private void setKidsHeaderMappingFields(JobHeader kffHeader, KidsHeader kidsHeader, EDirections direction) {
    	String countryTo = "";
    	String mappingCode = "";
    	CustomerProcedureDTO customerProcedureDTO = null;
        customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), 
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
        
        if (!Utils.isStringEmpty(kffHeader.getTestFlag())) {       //EI20120807
        	if (!Utils.isStringEmpty(kffHeader.getTestFlag().trim())) {   //AK20120726 
        		kidsHeader.setTestIndicator("T");        	
        	}
        }
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
   
}
