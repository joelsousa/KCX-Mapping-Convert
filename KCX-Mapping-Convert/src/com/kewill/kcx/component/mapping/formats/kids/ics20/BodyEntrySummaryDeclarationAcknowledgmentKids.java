package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20.<br>
 * Created	    : 24.10.2012<br>
 * Description  : Body of ICSEntrySummaryDeclarationAcknowledgment in KIDS format.
 *              : (IE328)
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class BodyEntrySummaryDeclarationAcknowledgmentKids extends KidsMessageICS20 {

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
                    writePartyTIN("Representative", message.getRepresentative().getPartyTIN());
                    writePartyAddress("Representative", message.getRepresentative());
                }
                if (message.getCarrier() != null) {
                	writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                	writePartyAddress("Carrier", message.getCarrier());
                }
                writeElement("CustomsOfficeOfLodgement", message.getCustomsOfficeOfLodgment());
                writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                //EI20101217: writeElement("ExpectedDateOfArrival", message.getExpectedDateOfArrival());
                writeFormattedDateTime("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
                		message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_DateTime);	
                writeDocument(message.getDocument());  
                    	
                if (message.getGoodsItemList() != null) {
	               for (GoodsItemShort goodsItem : message.getGoodsItemList()) {
	                   writeGoodsItem(goodsItem);
	               }
                }
                    	
            closeElement();
            closeElement();
            closeElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
    
    private void writeGoodsItem(GoodsItemShort goodsItem) throws XMLStreamException {
    	if (goodsItem == null) {
    		return;
    	}
    	
    	openElement("GoodsItem");             	                    	   
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	writeElement("ShipmentNumber", goodsItem.getShipmentNumber());

	    	if (goodsItem.getContainersList() != null) {
		    	for (String container : goodsItem.getContainersList()) {
			    	writeElement("Containers", container);
		    	}
	    	}

	    	if (goodsItem.getMeansOfTransportBorderList() != null) {
		    	for (TransportMeans tm : goodsItem.getMeansOfTransportBorderList()) {
		    		writeTransportMeansType("MeansOfTransportBorder", tm);
		    	}
	    	}
	    	
	    	if (goodsItem.getDocumentList() != null) {
		    	for (IcsDocument document : goodsItem.getDocumentList()) {
			    	openElement("Document");
			    		writeCodeElement("Type", document.getType(), "KCX0063");
			    		writeElement("Reference", document.getReference());
			    		writeElement("ReferenceLng", document.getReferenceLng());
		    	    closeElement();
		    	}
	    	}
	    closeElement();
	}
}
