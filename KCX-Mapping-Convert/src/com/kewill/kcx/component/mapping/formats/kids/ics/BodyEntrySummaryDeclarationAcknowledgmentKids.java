/*
 * Function    : BodyEntrySummaryDeclarationAcknowledgmentKids.java
 * Titel       :
 * Date        : 18.06.2010
 * Author      : Pete T
 * Description :
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyEntrySummaryDeclarationAcknowledgmentKids.<br>
 * Date Started	: 2010<br>
 * 
 * @author		: Pete T
 * @version 1.0.00
 *
 */
public class BodyEntrySummaryDeclarationAcknowledgmentKids extends KidsMessageICS {

	private MsgEntrySummaryDeclarationAcknowledgment message;

    public BodyEntrySummaryDeclarationAcknowledgmentKids(XMLStreamWriter writer) {
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
            openElement("ICSEntrySummaryDeclarationAcknowledgment");
            openElement("GoodsDeclaration");
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("MRN", message.getMrn());
                writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
                writeElement("ShipmentNumber", message.getShipmentNumber());
                writeElement("ConveyanceReference", message.getConveyanceReference());
                writeFormattedDateTime("RegistrationDateAndTime", message.getRegistrationDateAndTime(),
                		message.getRegistrationDateAndTimeFormat(), EFormat.KIDS_DateTime);		
                //writeElement("AmendmentDateAndTime", message.getAmendmentDateAndTime());
                if (message.getPersonLodgingSuma() != null) {
                    writePartyTIN("PersonLodgingSuma", message.getPersonLodgingSuma().getPartyTIN());
                    writePartyAddress("PersonLodgingSuma", message.getPersonLodgingSuma());
                }
                if (message.getRepresentative() != null) {
                    writeTIN("Representative", message.getRepresentative().getPartyTIN());
                    writeParty("Representative", message.getRepresentative());
                }
                if (message.getCarrier() != null) {
                    writeTIN("Carrier", message.getCarrier().getPartyTIN());
                    writeParty("Carrier", message.getCarrier());
                }
                writeElement("CustomsOfficeOfLodgement", message.getCustomsOfficeOfLodgment());
                writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                //EI20101217: writeElement("ExpectedDateOfArrival", message.getExpectedDateOfArrival());
                writeFormattedDateTime("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
                		message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_DateTime);	
                writeDocument(message.getDocument());  
                    	
                if (message.getGoodsItemList() != null) {
	               for (GoodsItemShort goodsItem : message.getGoodsItemList()) {
	                   writeGoodsItemShort(goodsItem);
	               }
                }
                    	
            closeElement();
            closeElement();
            closeElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
}