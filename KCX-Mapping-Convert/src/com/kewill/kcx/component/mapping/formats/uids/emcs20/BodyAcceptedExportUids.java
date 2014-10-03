package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgAcceptedExport;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSNotificationOfAcceptedExport Uids message.
 *              : V20 - DateTime format was changed, Trader.Addres.StreetAndNumber now in two Tags
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyAcceptedExportUids extends UidsMessageEMCS20 {
	
    private MsgAcceptedExport  message = new MsgAcceptedExport();
   
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
        	openElement("soap:Body");
            openElement("Submit");                
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                               
            openElement("EMCS");
            openElement("EMCSNotificationOfAcceptedExport");   
                
                //writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance();
                writeFormattedDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance(),
                				EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                     
                writeTrader("ConsigneeTrader", message.getConsignee());  
                writeExciseMovementEaadList(message.getExciseMovementEaadList());                     
                writeElement("DeliveryPlaceCustomsOffice", message.getExportPlaceCustomsOffice()); //EI20110819 Tagname changed 
                writeExportAcceptance(message.getExportAcceptance());   
                     	
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	
