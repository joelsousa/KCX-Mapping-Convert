package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyDeclarationAmendmentUids<br>
 * Created		: 2010.07.19<br>
 * Description	: UIDS-Body of ICSDeclarationAmendment.
 * 				: (KIDS: ICSDeclarationAmendment, IE313)
 * @author Lassiter Caviles
 * @version 1.0.00
 */

public class BodyDeclarationAmendmentUids extends UidsMessageICS {

	private MsgDeclarationAmendment message;
	private String destinationCountry = "";  //EI20110210

	public BodyDeclarationAmendmentUids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgDeclarationAmendment getMessage() {
		return message;
	}

	public void setMessage(MsgDeclarationAmendment message) {
		this.message = message;
	}

	public void writeBody() {
		destinationCountry = this.getUidsHeader().getTo().substring(0, 2);  //EI20110210
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
			openElement("ICSDeclarationAmendment");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("CommercialReferenceNumber", message.getShipmentNumber());
				writeElement("MRN", message.getMrn());
				writeElement("TotalNumberOfItems", message.getTotalNumberPositions());
				writeElement("TotalNumberOfPackages", message.getTotalNumberPackages());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
				writeElement("ConveyanceNumber", message.getConveyanceReference());
				writeElement("PlaceOfLoading", message.getLoadingPlace());
				writeElement("PlaceOfUnloading", message.getUnloadingPlace());
				writeElement("SpecificCircumstanceIndicator", message.getSituationCode());
				writeElement("TransportMethodOfPayment", message.getPaymentType());				
				writeFormattedDateTime("DeclarationDateAndTime", message.getDeclarationTime(),
						message.getDeclarationTimeFormat(), EFormat.ST_DateTimeTZ);
				
				writeElement("DeclarationPlace", message.getDeclarationPlace());   //EI20110210
				if (destinationCountry.equals("CZ")) {  //EI20110210
					String flag = getRepresentativeFlag();	
					if (flag.equals("0") || flag.equals("1")) {
						writeElement("AmendmentDeclaredByRepresentative", flag);   
					}
				}
				
				writeTrader("Consignor", message.getConsignor());
				writeTrader("Consignee", message.getConsignee());
				writeTrader("NotifyParty", message.getNotifyParty());			
				writeTrader("PersonLodgingSumDec", message.getPersonLodgingSuma());						
				writeElement("OfficeOfLodgement", message.getCustomsOfficeOfLodgment());
				writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());				
				writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(),
						message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);
				
				if (message.getCustomsOfficeOfSubsequentEntryList() != null) {
					for (String entry : message.getCustomsOfficeOfSubsequentEntryList()) {
						writeElement("OfficeOfSubsequentEntry", entry);
					}
				}
				if (message.getSealIDList() != null) {
					for (SealNumber seal : message.getSealIDList()) {
						if (seal != null) {
							writeElement("SealsIdentity", seal.getNumber());
						}
					}
				}
				if (message.getCountryOfRoutingList() != null) {
					for (String country : message.getCountryOfRoutingList()) {
						writeElement("Itinerary", country);
					}
				}
				
				if (message.getGoodsItemList() != null) {
					for (GoodsItemLong goodsItem : message.getGoodsItemList()) {
						//EI20111010: writeGoodsItem(goodsItem, "", "");  //EI20110923
						// N741/N703 should be make also for Amendment
						writeGoodsItem(goodsItem, "Amendment", message.getShipmentNumber());  //EI20111010
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
	
	private String getRepresentativeFlag() {
		boolean isRep = false;
		boolean isPers = false;
		if (message.getRepresentative() != null) {
			if ((message.getRepresentative().getPartyTIN() != null  && !message.getRepresentative().getPartyTIN().isEmpty()) || 
				(message.getRepresentative().getAddress() != null) && !message.getRepresentative().getAddress().isEmpty()) {
				isRep = true;
			}
		}
		if (message.getPersonLodgingSuma() != null) {
			if ((message.getPersonLodgingSuma().getPartyTIN() != null  && !message.getPersonLodgingSuma().getPartyTIN().isEmpty()) || 
				(message.getPersonLodgingSuma().getAddress() != null) && !message.getPersonLodgingSuma().getAddress().isEmpty()) {
				isPers = true;
			}
		}
		if (isPers)	{	
			return "0";
		} else if (isRep) {
			return "1";
		} else {
			return "";
		}
	}
   
}

