/*
 * Function    : BodyEntrySummaryDeclarationUids.java
 * Titel       :
 * Date        : 17.06.2010
 * Author      : iwaniuk
 * Description : 
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyEntrySummaryDeclarationUids<br>
 * Created		: 2010.07.19<br>
 * Description	: UIDS Body format of ICSDeclaration 
 * 				: (KIDS: ICSEntrySummaryDeclaration, IE315) 
 * 
 * @author Pete T.
 * @version 1.0.00
 */
public class BodyEntrySummaryDeclarationUids extends UidsMessageICS {

	private MsgEntrySummaryDeclaration message;	

    public BodyEntrySummaryDeclarationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEntrySummaryDeclaration getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntrySummaryDeclaration argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit"); 
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911");
        	openElement("ICS");
    		openElement("ICSDeclaration");
    		
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());
            	writeElement("CommercialReferenceNumber", message.getShipmentNumber());
            	//MRN
            	writeElement("TotalNumberOfItems", message.getTotalNumberPositions()); 
            	writeElement("TotalNumberOfPackages", message.getTotalNumberPackages()); 
            	writeElement("TotalGrossMass", message.getTotalGrossMass());
            	writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
            	writeElement("ConveyanceNumber", message.getConveyanceReference()); 
            	writeElement("PlaceOfLoading", message.getLoadingPlace()); 
            	writeElement("PlaceOfUnloading", message.getUnloadingPlace()); 
            	writeElement("SpecificCircumstanceIndicator", message.getSituationCode()); 
            	writeElement("TransportMethodOfPayment", message.getPaymentType()); 
            	// CK 2010-12-22 write datetime fields with method writeStringToDateTime
            	// writeElement("DeclarationDateAndTime", message.getDeclarationTime());
            	//EI20110105:  writeStringToDateTime("DeclarationDateAndTime", message.getDeclarationTime());
            	writeFormattedDateTime("DeclarationDateAndTime", message.getDeclarationTime(),
            			message.getDeclarationTimeFormat(), EFormat.ST_DateTimeTZ);	
            	writeElement("DeclarationPlace", message.getDeclarationPlace());
            	writeTrader("Consignor", message.getConsignor());
            	writeTrader("Consignee", message.getConsignee());
            	writeTrader("NotifyParty", message.getNotifyParty());	                	
            	writeTrader("PersonLodgingSumDec", message.getPersonLodgingSuma());            	
            	writeElement("OfficeOfLodgement", message.getCustomsOfficeOfLodgment()); 
            	writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());
            	// CK 2010-12-22 write datetime fields with method writeStringToDateTime and Arrival with "rr"
            	// writeElement("ExpectedArivalDateAndTime", message.getDeclaredDateOfArrival());
            	//EI20110105:  writeStringToDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival());
            	writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(),
            			message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);	
            	
            	if (message.getCustomsOfficeOfSubsequentEntryList() != null) {
            		for (String entry : 
            			message.getCustomsOfficeOfSubsequentEntryList()) {
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
            			writeGoodsItem(goodsItem, "Declaration",  message.getShipmentNumber());
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
