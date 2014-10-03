package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSDeclarationAmendmentAccepted 
 * 				: (KIDS: ICSEntrySummaryDeclarationAmendmentAcceptance, IE304 ).
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyEntrySummaryDeclarationAmendmentAcceptanceUids extends UidsMessageICS20 {
	private MsgEntrySummaryDeclarationAmendmentAcceptance message;	

    public BodyEntrySummaryDeclarationAmendmentAcceptanceUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEntrySummaryDeclarationAmendmentAcceptance getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntrySummaryDeclarationAmendmentAcceptance argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit");
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
            openElement("ICS");
            //EI20110120: openElement("ICSEntrySummaryDeclarationAcceptance");
            openElement("ICSDeclarationAmendmentAccepted");
            	
	        	writeElement("LocalReferenceNumber", message.getReferenceNumber()); 
	            writeElement("CommercialReferenceNumber", message.getShipmentNumber());
                writeElement("MRN", message.getMrn());  
	            writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
                writeElement("ConveyanceNumber", message.getConveyanceReference());               
                writeFormattedDateTime("AmendmentDateAndTime", message.getAmendmentDateAndTime(),
                		 message.getAmendmentDateAndTimeFormat(), EFormat.ST_DateTimeTZ);	
	            writeTrader("PersonLodgingSumDec", message.getPersonLodgingSuma());	            				
	            writeTrader("Representative", message.getRepresentative());	            				
	            writeTrader("EntryCarrier", message.getCarrier());	            				
                writeElement("OfficeOfLodgement", message.getCustomsOfficeOfLodgement());  
                writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());                
	            writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(),
		                message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);
                
                if (message.getGoodsItemList() != null) {
	               for (GoodsItemShort goodsItem : message.getGoodsItemList()) {
	                    writeGoodsItemShort(goodsItem, "IE304");
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
