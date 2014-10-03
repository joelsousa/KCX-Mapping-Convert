package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentAcceptance;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemShort;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: BodyEntrySummaryDeclarationAcceptanceKids<br>
 * Created		: 14.07.2010<br>
 * Description	: The body of the KIDS format of EntrySummaryDeclarationAmendmentAcceptance message.
 *              : (IE304)
 *              
 * @author	: Michelle Bauza
 * @version 1.0.00
 */
public class BodyEntrySummaryDeclarationAmendmentAcceptanceKids extends KidsMessageICS {
	private MsgEntrySummaryDeclarationAmendmentAcceptance message;
		
		public BodyEntrySummaryDeclarationAmendmentAcceptanceKids(XMLStreamWriter writer) {
			this.writer	= writer;
		}
		
		public void setMessage(MsgEntrySummaryDeclarationAmendmentAcceptance msg) {
			this.message	= msg;
		}
		
		public MsgEntrySummaryDeclarationAmendmentAcceptance getMessage() {
			return this.message;
		}
	
		public void writeBody() {
			try {
				openElement("soap:Body");
				openElement("ICSEntrySummaryDeclarationAmendmentAcceptance");
				openElement("GoodsDeclaration");
				
				    if (!Utils.isStringEmpty(message.getReferenceNumber())) {
				    	writeElement("ReferenceNumber", message.getReferenceNumber());
				    } else {
						if (!Utils.isStringEmpty(this.getKidsHeader().getInReplyTo())) {					
							writeElement("ReferenceNumber", this.getKidsHeader().getInReplyTo());
						}
					}
					writeElement("MRN", message.getMrn());														
					writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
					writeElement("ShipmentNumber", message.getShipmentNumber());
					writeElement("ConveyanceReference", message.getConveyanceReference());
					//EI29119195: writeElement("RegistrationDateAndTime", message.getRegistrationDateAndTime());
					writeFormattedDateTime("AmendmentDateAndTime", message.getAmendmentDateAndTime(),
									message.getAmendmentDateAndTimeFormat(), EFormat.KIDS_DateTime);					
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
					writeElement("CustomsOfficeOfLodgement", message.getCustomsOfficeOfLodgement());
					writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());					
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
