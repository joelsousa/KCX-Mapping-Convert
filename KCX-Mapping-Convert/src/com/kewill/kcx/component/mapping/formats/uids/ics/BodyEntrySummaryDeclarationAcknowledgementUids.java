/*
 * Function    : BodyAcceptanceUids.java
 * Titel       :
 * Date        : 18.06.2010
 * Author      : Pete T
 * Description :
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyAcceptanceUids<br>
 * Created		: 2010.07.19<br>
 * Description	: UIDS-Body of ICSDeclarationAccepted.
 * 				: (KIDS: ICSEntrySummaryDeclarationAcknowledgment, IE328)
 * 
 * @author Pete T.
 * @version 1.0.00
 */
public class BodyEntrySummaryDeclarationAcknowledgementUids extends UidsMessageICS {

	private MsgEntrySummaryDeclarationAcknowledgment message;

    public BodyEntrySummaryDeclarationAcknowledgementUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgEntrySummaryDeclarationAcknowledgment getMessage() {
		return message;
	}

	public void setMessage(MsgEntrySummaryDeclarationAcknowledgment argument) {
		this.message = argument;
	}

    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit");
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
            openElement("ICS");
            //EI20110120: openElement("ICSAcceptance");
            openElement("ICSDeclarationAccepted");            
            
	        	writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeElement("CommercialReferenceNumber", message.getShipmentNumber());
                writeElement("MRN", message.getMrn());
	            writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
                writeElement("ConveyanceNumber", message.getConveyanceReference());
                //EI20110105: writeElement("RegistrationDateAndTime", message.getRegistrationDateAndTime());
                writeFormattedDateTime("RegistrationDateAndTime", message.getRegistrationDateAndTime(),
                		message.getRegistrationDateAndTimeFormat(), EFormat.ST_DateTimeTZ);	    
                //writeElement("AmendmentDateAndTime", message.getAmendmentDateAndTime());
	            writeTrader("PersonLodgingSumDec", message.getPersonLodgingSuma());	            			
	            writeTrader("Representative", message.getRepresentative());	            				
	            writeTrader("EntryCarrier", message.getCarrier());	            			
                writeElement("OfficeOfLodgement", message.getCustomsOfficeOfLodgment());
                writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());
                //EI20110105: writeElement("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival());
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
