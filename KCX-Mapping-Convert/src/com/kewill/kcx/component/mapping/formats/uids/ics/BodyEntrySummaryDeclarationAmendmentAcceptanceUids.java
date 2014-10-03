package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;


/**
 * Module		: BodyEntrySummaryDeclarationAcceptanceUids<br>
 * Date Finished: July 16, 2010<br>
 * Description	: UIDS-Body of ICSDeclarationAmendmentAccepted 
 * 				: (KIDS: ICSEntrySummaryDeclarationAmendmentAcceptance, IE304 ).
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 */

public class BodyEntrySummaryDeclarationAmendmentAcceptanceUids extends UidsMessageICS {
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
                //writeElement("RegistrationDateAndTime", message.getRegistrationDateAndTime()); 
                //EI20110105: writeElement("AmendmentDateAndTime", message.getAmendmentDateAndTime()); 
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
	                    writeGoodsItemShort(goodsItem);
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
