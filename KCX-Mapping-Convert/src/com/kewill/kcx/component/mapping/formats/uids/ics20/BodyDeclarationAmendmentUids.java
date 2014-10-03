package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSDeclarationAmendment.
 * 				: (KIDS: ICSDeclarationAmendment, IE313)
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyDeclarationAmendmentUids extends UidsMessageICS20 {

	private MsgDeclarationAmendment message;
	private String destinationCountry = "";  

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
		destinationCountry = this.getUidsHeader().getTo().substring(0, 2);  
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
				writeElement("DeclarationPlace", message.getDeclarationPlace());   
				if (destinationCountry.equals("CZ")) {  
					String flag = this.getRepresentativeFlag();	
					if (flag.equals("0") || flag.equals("1")) {
						writeElement("AmendmentDeclaredByRepresentative", flag);   
					}
				}
				
				writeTrader("Consignor", message.getConsignor());
				writeTrader("Consignee", message.getConsignee());
				writeTrader("NotifyParty", message.getNotifyParty());			
				writeTrader("PersonLodgingSumDec", message.getPersonLodgingSuma());		
				writeTrader("Representative", message.getRepresentative());          //new V20
				writeTrader("EntryCarrier", message.getCarrier());                        //new V20
				
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
						writeGoodsItem(goodsItem, "Amendment", message.getShipmentNumber());  
						
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

