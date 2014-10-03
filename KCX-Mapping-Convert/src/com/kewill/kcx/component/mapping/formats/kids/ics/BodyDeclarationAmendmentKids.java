package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyDeclarationAmendmentKids<br>
 * Created		: 2010.07.19<br>
 * Description	: The body of the KIDS format of DeclarationAmendment message.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 *
 */

public class BodyDeclarationAmendmentKids extends KidsMessageICS {

	private MsgDeclarationAmendment message;	

    public BodyDeclarationAmendmentKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgDeclarationAmendment getMessage() {
		return message;
	}
	
	public void setMessage(MsgDeclarationAmendment argument) {
		this.message = argument;
	}
		
	public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSDeclarationAmendment");
            openElement("GoodsDeclaration");
            
	        	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("ShipmentNumber", message.getShipmentNumber());
	            writeElement("MRN", message.getMrn());
	            writeElement("TotalNumberPositions", message.getTotalNumberPositions()); 
	            writeElement("TotalNumberPackages", message.getTotalNumberPackages()); 
	            writeElement("TotalGrossMass", message.getTotalGrossMass());
	            writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
	            writeElement("ConveyanceReference", message.getConveyanceReference());
	          //EI20130704: zukuenftig wird NL auch ICS machen, aus DXB -> AEDXB, AMS->NLAMS
                if (this.kidsHeader != null && kidsHeader.getCountryCode().equalsIgnoreCase("NL")) {                
                	writeCodeElement("LoadingPlace", message.getLoadingPlace(), "KCX0101");
                	writeElement("LoadingPlaceLng", message.getLoadingPlaceLng());     
                	writeCodeElement("UnloadingPlace", message.getUnloadingPlace(), "KCX0100");                   	
                	writeElement("UnloadingPlaceLng", message.getUnloadingPlaceLng());                 	
                } else {               
                	writeElement("LoadingPlace", message.getLoadingPlace());
                	writeElement("LoadingPlaceLng", message.getLoadingPlaceLng());     
                	writeElement("UnloadingPlace", message.getUnloadingPlace());
                	writeElement("UnloadingPlaceLng", message.getUnloadingPlaceLng()); 
                } 
	            writeElement("SituationCode", message.getSituationCode());
	            writeCodeElement("PaymentType", message.getPaymentType(), "KCX0068");   //C0116	       
	            writeFormattedDateTime("DeclarationTime", message.getDeclarationTime(),
		                		message.getDeclarationTimeFormat(), EFormat.KIDS_DateTime);	
	            writeElement("DeclarationPlace", message.getDeclarationPlace()); //EI20110106
	            if (message.getConsignor() != null) {
	                writePartyTIN("Consignor", message.getConsignor().getPartyTIN());
	                writePartyAddress("Consignor", message.getConsignor());
	            }
	            if (message.getConsignee() != null) {
	                writePartyTIN("Consignee", message.getConsignee().getPartyTIN());
	                writePartyAddress("Consignee", message.getConsignee());
	            }
	            if (message.getNotifyParty() != null)  {
	                writePartyTIN("NotifyParty", message.getNotifyParty().getPartyTIN());
	                writePartyAddress("NotifyParty", message.getNotifyParty());
	            }
	            if (message.getPersonLodgingSuma() != null) {
	                writePartyTIN("PersonLodgingSuma", message.getPersonLodgingSuma().getPartyTIN());
	                writePartyAddress("PersonLodgingSuma", message.getPersonLodgingSuma());
	                writeContactPerson("PersonLodgingSuma", message.getPersonLodgingSuma().getContactPerson());
	            }
	            if (message.getRepresentative() != null)  {
	                writePartyTIN("Representative", message.getRepresentative().getPartyTIN());
	                writePartyAddress("Representative", message.getRepresentative()); 
	                if (message.getRepresentative() != null) {
	                	writeContactPerson("Representative", message.getRepresentative().getContactPerson());	
	                }
	            }	                	
	            if (message.getCarrier() != null) {
	                writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
	                writePartyAddress("Carrier", message.getCarrier()); 
	            }
	            writeElement("CustomsOfficeOfLodgement", message.getCustomsOfficeOfLodgment());  
	            writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
	            writeFormattedDateTime("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
	            				message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_DateTime);
	            if (message.getCustomsOfficeOfSubsequentEntryList() != null) {
		           for (String entry : message.getCustomsOfficeOfSubsequentEntryList()) {
		               writeElement("CustomsOfficeOfSubsequentEntry", entry);
		           }
	            }
	            if (message.getSealIDList() != null) {
		           for (SealNumber seal : message.getSealIDList()) {
		        	   writeSealsId(seal);
		           }
	            }
	            if (message.getCountryOfRoutingList() != null) {
		            for (String routing : message.getCountryOfRoutingList()) {
		               writeElement("CountryOfRouting", routing);
		            }
	            }
	            writeConveyanceCall(message.getConveyanceCall());    //EI20121004 JIRA: KCXSM-34
	            
	            if (message.getGoodsItemList() != null) {
		           for (GoodsItemLong goodsItem : message.getGoodsItemList()) {
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
	
}
