package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgAdvanceInterventionNotPos_unused;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;


/**
 * Module		: BodyAdvanceInterventionNotUids<br>
 * Created		: 2010.07.20<br>
 * Description	: UIDS-Body of ICSDeclarationProhibited,
 * 				: (KIDS: ICSAdvanceInterventionNot, IE351)
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */

public class BodyAdvanceInterventionNotUids extends UidsMessageICS {

	private MsgAdvanceInterventionNot message;

	public BodyAdvanceInterventionNotUids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgAdvanceInterventionNot getMessage() {
		return message;
	}

	public void setMessage(MsgAdvanceInterventionNot message) {
		this.message = message;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
			openElement("ICSDeclarationProhibited");
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("CommercialReferenceNumber", message.getShipmentNumber());
				writeElement("MRN", message.getMrn());				
				writeFormattedDateTime("RegistrationDateAndTime", message.getRegistrationDateTime(), 
						message.getRegistrationDateTimeFormat(), EFormat.ST_DateTimeTZ);	
				writeFormattedDateTime("NotificationDateAndTime", message.getNotificationDateTime(),
							message.getNotificationDateTimeFormat(), EFormat.ST_DateTimeTZ);					
				writeFormattedDateTime("SubmissionDateAndTime", message.getRegistrationDateTime(),
							message.getRegistrationDateTimeFormat(), EFormat.ST_DateTimeTZ);				
				writeElement("TotalNumberOfItems", message.getTotalNumberPosition());
				writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
				writeElement("ConveyanceNumber", message.getConveyanceReference());
				writeTrader("PersonLodgingSumDec", message.getPersonLodgingSuma());				
				writeTrader("Representative", message.getRepresentative());					
				writeTrader("EntryCarrier", message.getCarrier());					
				writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());				
				writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(), 
							message.getExpectedDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);
				
				if (message.getGoodsItemList() != null) {
					for (GoodsItemShort goodsItem : message.getGoodsItemList()) {
						writeGoodsItem(goodsItem);
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

	public void writeGoodsItem(GoodsItemShort goodsItem)
			throws XMLStreamException {

		if (goodsItem == null) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", goodsItem.getItemNumber());
			writeElement("CommercialReferenceNumber", goodsItem.getShipmentNumber());
			if (goodsItem.getContainersList() != null) {
				for (String container : goodsItem.getContainersList()) {
					writeElement("ContainerNumber", container);
				}
			}		
			if (goodsItem.getMeansOfTransportBorderList() != null) {
				for (TransportMeans border : goodsItem.getMeansOfTransportBorderList()) {
					writeTransport("MeansOfTransportAtBorder", border);			
				}
			}
			if (goodsItem.getDocumentList() != null) {
				for (IcsDocument document : goodsItem.getDocumentList()) {
					writeDocument(document);
				}
			}
		closeElement();

	}
	
}
