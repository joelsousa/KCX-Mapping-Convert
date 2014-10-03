package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNotPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotification;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgControlNotificationPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 27.07.2013<br>
 * Description   : Uids Body of ControlNotification.  				 
 *               : new message
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExpControlNotificationUids extends UidsMessageV21 {	

    private MsgControlNotification message = new MsgControlNotification();
    
    public BodyExpControlNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgControlNotification getMsgKids() {
		return message;
	}

	public void setMessage(MsgControlNotification argument) {
		this.message = argument;
	}

	public void writeBody() {
		
        try {
        	openElement("soap:Body");
            openElement("Submit");  
                //EI20120917:
            	if (this.getCommonFieldsDTO() != null && 
            		!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {           
            		setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
            	} else {
            		setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	}
            	
                openElement("Export");
                openElement("ExportControlNotification"); 
                
                writeContact(message.getContact()); 
                writeParty("Shipper", message.getForwarder()); 
                if (!Utils.isStringEmpty(message.getRealOfficeOfExit())) {
                	openElement("CustomsOffices");		
        			   writeElement("OfficeOfExit", message.getRealOfficeOfExit());
        			closeElement(); 
                }
                //EI20131202: writeElement("DateOfControl", message.getTimeOfInspection());
                //writeStringToDateTime("DateOfControl", message.getTimeOfInspection());
                writeFormattedDateTime("DateOfControl", message.getTimeOfInspection(),
        				message.getTimeOfInspectionFormat(), EFormat.ST_DateTimeZ);
                writeElement("DocumentReferenceNumber", message.getUCRNumber());   
                writeElement("ExternalRegistrationNumber", message.getUCROtherSystem());                  
                writeElement("ReferenceNumber", message.getReferenceNumber());                                
                writeElement("Remark", message.getAnnotation());
                writeElement("TypeOfControl", message.getKindOfInspection());
                
                if (message.getGoodsItemList() != null) {
                	for (MsgControlNotificationPos item : message.getGoodsItemList()) {
                		writeElement("ItemNumber", item.getItemNumber());
                		writeElement("ExternalRegistrationNumber", item.getUcrOtherSystem());                		
                        writeElement("Remark", item.getAnnotation());
                        writeElement("TypeOfControl", item.getKindOfInspection());                        
                	}                	               
                }
                
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }
	
}    	
    	
    	
