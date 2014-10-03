package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgImportControlDecision;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;

/**
 * Module		: ICS20.<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS Body format of ICSImportControlDecision
 * 				: (KIDS: ICSImportControlDecision, IE361) 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyImportControlDecisionUids extends UidsMessageICS20 {

	private MsgImportControlDecision message;	

    public BodyImportControlDecisionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportControlDecision getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportControlDecision argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit"); 
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911");
        	openElement("ICS");
    		openElement("ICSImportControlDecision");
    		
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());            	
            	writeElement("CommercialReferenceNumber", message.getShipmentNumber());            
            	writeElement("MRN", message.getMrn()); 
            	writeElement("ConveyanceNumber", message.getConveyanceReference());
            	writeTrader("EntryCarrier", message.getCarrier());            	           
            	writeElement("OfficeOfActualEntry", message.getCustomsOfficeFirstEntry());    
            	
            	if (message.getGoodsItemList() != null) {
 		           for (GoodsItemShort item : message.getGoodsItemList()) {
 		                writeGoodsItemShort(item, "IE361");
 		           }
 	            }
            	if (message.getCustomsInterventionList() != null) {
					for (CustomsIntervention customs : message.getCustomsInterventionList()) {
						writeCustomsInterventions(customs);
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
