package com.kewill.kcx.component.mapping.formats.kids.ics20;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20.<br>
 * Created      : 19.10.2012<br>
 * Description  : Body of ICSEntrySummaryDeclaration in KIDS format.
 * 				: (IE315)
  
 * @author krzoska 
 * @version 2.0.00
 */
public class BodyEntrySummaryDeclarationKids extends KidsMessageICS20 {

	private MsgEntrySummaryDeclaration message;	

    public BodyEntrySummaryDeclarationKids(XMLStreamWriter writer) {
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
            openElement("ICSEntrySummaryDeclaration");
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
    /* moved into/called from KidsMessage
     * 
    public void writePartyTIN(String tag, TIN argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	
    	openElement(tag);             	                    	   
	    	writeElement("TIN", argument.getTIN());
	    	writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
	    	writeElement("isTINGermanApprovalNumber", argument.getIsTINGermanApprovalNumber());
	    	writeCodeElement("IdentificationType", argument.getIdentificationType(), "KCX0069");   //A1340
	    closeElement();
    } 
    
    private void writeGoodsItem(MsgEntrySummaryDeclarationPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	
	    openElement("GoodsItem");
	    	writeElement("ItemNumber", argument.getItemNumber());
	    	writeElement("ShipmentNumber", argument.getShipmentNumber());
	    	writeElement("Description", argument.getDescription());
	    	writeElement("DescriptionLng", argument.getDescriptionLng());
	    	writeElement("GrossMass", argument.getGrossMass());  
	    	writeCodeElement("DangerousGoodsNumber", argument.getDangerousGoodsNumber(), "KCX0064");   //C0101
	    	writeCodeElement("PaymentType", argument.getPaymentType(), "KCX0068");   //C0116
	    	writeElement("LoadingPlace", argument.getLoadingPlace());
	    	writeElement("LoadingPlaceLng", argument.getLoadingPlaceLng());
	    	writeElement("UnloadingPlace", argument.getUnloadingPlace());
	    	writeElement("UnloadingPlaceLng", argument.getUnloadingPlaceLng());
	    	if (argument.getConsignor() != null) {   
	    		writePartyTIN("ConsignorTIN", argument.getConsignor().getPartyTIN());
	    		writeParty("ConsignorAddress", argument.getConsignor());
	    	}
	    	if (argument.getConsignee() != null) {   
	    		writePartyTIN("ConsigneeTIN", argument.getConsignee().getPartyTIN());
	    		writeParty("ConsigneeAddress", argument.getConsignee());
	    	}
	    	if (argument.getNotifyParty() != null) {   
	    		writePartyTIN("NotifyPartyTIN", argument.getNotifyParty().getPartyTIN());
	    		writeParty("NotifyPartyAddress", argument.getNotifyParty());
	    	}
	    	
	    	if (argument.getContainersList() != null) {
				for (String container : argument.getContainersList()) {
					writeElement("Containers", container);   
				}
	    	}
	    	
	    	if (argument.getPackagesList() != null) {
		    	for (Packages packages : argument.getPackagesList()) {
		    		writePackage(packages);   
		    	}    	    	
	    	}
	    	
	    	if (argument.getMeansOfTransportBorderList() != null) {
		    	for (TransportMeans border : argument.getMeansOfTransportBorderList()) {
		    		writeTransportMeansType("MeansOfTransportBorder", border);   
		    	}
	    	}
	    	
	    	writeElement("CommodityCode", argument.getCommodityCode());
	    	
	    	if (argument.getDocumentList() != null) {
	    		for (IcsDocument document : argument.getDocumentList()) {  	    		
	    			writeDocument(document);  
	    		}
	      	}
	    	if (argument.getSpecialMentionList() != null) {
	    		for (SpecialMention special : argument.getSpecialMentionList()) {    
	    			writeSpecialMention(special);  			    		
	    		} 
	    	}
	    	
	    closeElement();
    }
    
    public void writeTransportMeansType(String tag, TransportMeans argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    	    return;
    	}  
    	
    	openElement(tag);             	                    	   
	    	writeCodeElement("TransportMode", argument.getTransportMode(), "KCX0027");
	    	writeElement("TransportationNumber", argument.getTransportationNumber());
	    	writeElement("TransportationCountry", argument.getTransportationCountry());
    	closeElement();    	
    }
*/
}
