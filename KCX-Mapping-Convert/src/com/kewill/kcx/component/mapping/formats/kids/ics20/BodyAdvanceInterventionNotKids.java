package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgAdvanceInterventionNot;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.CustomsIntervention;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.25<br>
 * Description	: Body of AdvanceInterventionNot in KIDS format.
 * 				: (IE351)
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */

public class BodyAdvanceInterventionNotKids extends KidsMessageICS20 {

	private MsgAdvanceInterventionNot message;

	public BodyAdvanceInterventionNotKids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgAdvanceInterventionNot getMessage() {
		return message;
	}

	public void setMessage(MsgAdvanceInterventionNot argument) {
		this.message = argument;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("ICSAdvancedInterventionNot");
			openElement("GoodsDeclaration");
			
				writeElement("ReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getMrn());
				//writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
				writeTransportMeansBorder(message.getMeansOfTransportBorder());   //EI20121025
				writeElement("TotalNumberPositions", message.getTotalNumberPosition());
				writeElement("ShipmentNumber", message.getShipmentNumber());
				writeElement("ConveyanceReference", message.getConveyanceReference());							
				writeFormattedDateTime("NotificationDateTime", message.getNotificationDateTime(),						
						message.getNotificationDateTimeFormat(), EFormat.KIDS_DateTime);													
				writeFormattedDateTime("RegistrationDateTime", message.getRegistrationDateTime(),
						message.getRegistrationDateTimeFormat(), EFormat.KIDS_DateTime);				
				writeFormattedDateTime("AcceptedDateTime", message.getAcceptedDateTime(),
						message.getAcceptedDateTimeFormat(), EFormat.KIDS_DateTime);								
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
				writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());				
				writeFormattedDateTime("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),						
						message.getExpectedDateOfArrivalFormat(), EFormat.KIDS_DateTime);										
				if (message.getCustomsInterventionList() != null) {
					for (CustomsIntervention customsIntervention : message.getCustomsInterventionList()) {
						writeCustomsInterventions(customsIntervention);
					}
				}
				if (message.getGoodsItemList() != null) {
					for (GoodsItemShort goodsItem : message.getGoodsItemList()) {
					//writeGoodsItemShort(goodsItem);
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

	private void writeGoodsItem(GoodsItemShort argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", argument.getItemNumber());
			writeElement("ShipmentNumber", argument.getShipmentNumber());		
			if (argument.getDocumentList() != null) {
				for (IcsDocument document : argument.getDocumentList()) {
					writeDocument(document);
				}
			}		
			if (argument.getContainersList() != null) {
				for (String container : argument.getContainersList()) {
					writeElement("Containers", container);
				}
			}		
			if (argument.getMeansOfTransportBorderList() != null) {
				for (TransportMeans border : argument.getMeansOfTransportBorderList()) {
					//writeTransportMeansType("MeansOfTransportBorder", border);
					writeTransportMeansBorder(border);   //EI20121025
				}
			}
		closeElement();
	}
	
}
