package com.kewill.kcx.component.mapping.formats.kids.ics20;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntryDetailsData;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module	: ICS.<br> 
 * Created		: 08.11.2013<br>
 * Description	: Kids Body of ICSEntryDetailsData.<br>
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyEntryDetailsDataKids extends KidsMessageICS20 {

	private MsgEntryDetailsData message;	

	public BodyEntryDetailsDataKids(XMLStreamWriter writer) {
	    this.writer = writer;
	}	  
   
	public MsgEntryDetailsData getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntryDetailsData argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSEntryDetailsData");
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
                writeElement("DeclarationPlace", message.getDeclarationPlace());                    	
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
                 if (message.getRepresentative() != null) {
                    writePartyTIN("Representative", message.getRepresentative().getPartyTIN());
                    writePartyAddress("Representative", message.getRepresentative());
                    // 2011-02-20 added in IE315 
                    // writeContactPerson("Representative", message.getRepresentativeContact());
                    if (message.getRepresentative() != null) {
	                	writeContactPerson("Representative", message.getRepresentative().getContactPerson());	
	                }
                 }                    	
                 if (message.getCarrier() != null) {
                    writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                    writePartyAddress("Carrier", message.getCarrier());
                 }
                 if (message.getPersonLodgingAtlasSuma() != null) {
                     writePartyTIN("PersonLodgingAtlasSuma", message.getPersonLodgingSuma().getPartyTIN());
                     writePartyAddress("PersonLodgingAtlasSuma", message.getPersonLodgingSuma());	                		                
                     writeContactPerson("PersonLodgingAtlasSuma", message.getPersonLodgingSuma().getContactPerson());
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
                	 openElement("SealsID");
                	 for (SealNumber seal : message.getSealIDList()) {
		                writeSealsId(seal);
		             }  
                	 closeElement();
                 }
                 if (message.getCountryOfRoutingList() != null) {
	                for (String routing : message.getCountryOfRoutingList()) {
	                    writeElement("CountryOfRouting", routing);
	                }
                 }
                 writeConveyanceCall(message.getConveyanceCall());    
                 writeFormattedDateTime("ControlDate", message.getControlDate(),
                  		message.getControlDateFormat(), EFormat.KIDS_Date);	
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
    
    public void  writeSealsId(List<SealNumber> argument) throws XMLStreamException {
       	if (argument == null) {
    	    return;
    	} 
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	openElement("SealsID");
			for (SealNumber seals : argument) {
				writeElement("SealsIdentity", seals.getNumber());
				writeElement("SealsIdentityLng", seals.getLanguage());
			}
		closeElement();
    	
    }  
    
}
