package com.kewill.kcx.component.mapping.formats.kids.port;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgBillOfLading;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.StatusAnnotation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessagePortBL;

/**
 * Module	   : Port.<br>
 * Created	   : 10.04.2012<br>
 * Description : BodyBillOfLadingKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyBillOfLadingKids extends KidsMessagePortBL {

	private MsgBillOfLading message;	

    public BodyBillOfLadingKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgBillOfLading getMessage() {
		return message;
	}
	
	public void setMessage(MsgBillOfLading argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("BillOfLading");
            openElement("GoodsDeclaration"); 
            
                 //TODO-IWA: z.Z only mandatory fields
                writeElement("PortSystem", message.getPortSystem());    //EI20120705
            	writeElement("ApplicationSenderId", message.getApplicationSenderId()); 
            	writeElement("ApplicationRecipientId", message.getApplicationRecipientId()); 
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("MessageFunction", message.getMessageFunction());  
            	writeElement("TestFlag", message.getTestFlag());
            	writeContactBL("SenderContact", message.getSenderContact());
            	/*               
                writeCurrencyGroup(message.getCurrencyGroup());    
                writeMonetaryAmountGroup(message.getMonetaryAmountGroup());                
                */
            	writeTextOnEntireBLGroup(message.getTextOnEntireBL()); 
            	writeTotalsOnEntireBL(message.getTotals());            	
            	writeDetailsOnDocument(message.getDocumentDetails());
            	/*
            	writePlaceAndDateOf(message.getPlaceAndDateOf());
            	writeTermsOfTransportList(message.getTermsOfTransportList());
            	*/
            	writeForwardersReference(message.getForwardersReference());            	
            	writeAdditionalReferenceGroup(message.getAdditionalReferenceGroup());
            	/*
            	writePaymentInstructionsGroup(message.getPaymentInstructionsGroup());
            	writeFreightAndChargeGroup(message.getFreightAndChargeGroup());            	
            	*/
            	writeParties(message.getParties());
            	writeCarriage(message.getCarriage());
            	
            	writeGoods(message.getGoods());
            	writeEquipment(message.getEquipment());            	
            	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
       
}
