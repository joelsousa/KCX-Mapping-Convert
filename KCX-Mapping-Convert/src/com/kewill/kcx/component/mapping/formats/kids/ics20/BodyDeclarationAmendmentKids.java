package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.19<br>
 * Description	: Body of DeclarationAmendment in Kids format.
 * 				: (IE313)
 *              : new V20: DelFlag
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */

public class BodyDeclarationAmendmentKids extends KidsMessageICS20 {

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
	            writeElement("DelFlag", message.getDelFlag());                              //new V20
	            writeElement("TotalNumberPositions", message.getTotalNumberPositions());
	            writeElement("TotalNumberPackages", message.getTotalNumberPackages());
	            writeElement("TotalGrossMass", message.getTotalGrossMass());
	            //writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
	            writeTransportMeansBorder(message.getMeansOfTransportBorder());   //EI20121025
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
	                writeContactPerson("Representative", message.getRepresentative().getContactPerson());		                
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
	            writeConveyanceCall(message.getConveyanceCall());    
	            
	            if (message.getGoodsItemList() != null) {
		           for (GoodsItemLong goodsItem : message.getGoodsItemList()) {
		              writeGoodsItem(goodsItem);
		           }
	            }
	             
            closeElement(); 	//GoodsDeclaration
            closeElement();		//ICSDeclarationAmendment
            closeElement();		//soap:Body
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
	
}
