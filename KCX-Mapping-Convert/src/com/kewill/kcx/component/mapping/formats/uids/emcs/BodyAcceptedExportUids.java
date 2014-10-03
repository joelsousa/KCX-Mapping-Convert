package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgAcceptedExport;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description : Contains Message Structure with fields used in EMCSNotificationOfAcceptedExport Uids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyAcceptedExportUids extends UidsMessageEMCS {
	
    private MsgAcceptedExport  message = new MsgAcceptedExport();
    private String version = "";         //EI20110819
 
    public BodyAcceptedExportUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgAcceptedExport getMessage() {
		return message;
	}

	public void setMessage(MsgAcceptedExport message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {   
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");                
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");               
                
                openElement("EMCS");
                openElement("EMCSNotificationOfAcceptedExport"); 
                          
                if (version.equals("10")) {                    
                	writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());  
                	writeTrader("ConsigneeTrader", message.getConsignee(), version);  
                    writeExciseMovementEaadList(message.getExciseMovementEaadList(), version);   
                    writeElement("ExportPlaceCustomsOffice", message.getExportPlaceCustomsOffice()); 
                    writeExportAcceptance(message.getExportAcceptance());   
                } else {
                	//V20 - DateTime format changed
                	//writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance();
                	writeFormattedDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance(),
                				EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                      //EI20111017 
                	writeTrader("ConsigneeTrader", message.getConsignee(), version);  
                    writeExciseMovementEaadList(message.getExciseMovementEaadList(), version); 
                    //V20 - Tagname changed
                    writeElement("DeliveryPlaceCustomsOffice", message.getExportPlaceCustomsOffice()); //EI20110819  
                    writeExportAcceptance(message.getExportAcceptance());   
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
