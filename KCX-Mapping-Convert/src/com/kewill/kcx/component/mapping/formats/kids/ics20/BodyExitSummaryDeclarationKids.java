package com.kewill.kcx.component.mapping.formats.kids.ics20;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgExitSummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20.<br>
 * Created      : 24.10.2012<br>
 * Description  : Body of ICSExitSummaryDeclaration in KIDS format.
  
 * @author krzoska 
 * @version 2.0.00
 */
public class BodyExitSummaryDeclarationKids extends KidsMessageICS20 {

	private MsgExitSummaryDeclaration message;	

    public BodyExitSummaryDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExitSummaryDeclaration getMessage() {
		return message;
	}
	
	public void setMessage(MsgExitSummaryDeclaration argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSExitSummaryDeclaration");
            openElement("GoodsDeclaration"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
                writeElement("TotalNumberPositions", message.getTotalNumberPositions());
                writeElement("TotalNumberPackages", message.getTotalNumberPackages());
                writeElement("ShipmentNumber", message.getShipmentNumber());
                writeText("LocationOfGoods", message.getLocationOfGoods());
                writeElement("CustomsSubPlace", message.getCustomsSubPlace());
                writeElement("SituationCode", message.getSituationCode()); 
                writeTransportMeansType("MeansOfTransportBorder", message.getMeansOfTransportBorder());
                writeCodeElement("PaymentType", message.getPaymentType(), "KCX0068");   //C0116
	            writeFormattedDate("DeclarationDate", message.getDeclarationDate(),
                		message.getDeclarationDateFormat(), EFormat.KIDS_Date);                      

	            if (message.getConsignor() != null) {
                    writePartyTIN("Consignor", message.getConsignor().getPartyTIN());
                    writePartyAddress("Consignor", message.getConsignor());
                }                    
                if (message.getConsignee() != null) {
                    writePartyTIN("Consignee", message.getConsignee().getPartyTIN());
                    writePartyAddress("Consignee", message.getConsignee());
                }                    	                    	
                if (message.getPersonLodgingSuma() != null) {
                    writePartyTIN("PersonLodgingSuma", message.getPersonLodgingSuma().getPartyTIN());
                    writePartyAddress("PersonLodgingSuma", message.getPersonLodgingSuma());
                }
                if (message.getRepresentative() != null) {
                    writePartyTIN("Representative", message.getPersonLodgingSuma().getPartyTIN());
                    writePartyAddress("Representative", message.getPersonLodgingSuma());
                }
                writeElement("CustomsOfficeOfLodgement", message.getCustomsOfficeOfLodgment());
                writeElement("OfficeOfExit", message.getOfficeOfExit());

                if (message.getCountryOfRoutingList() != null) {
	                for (String routing : message.getCountryOfRoutingList()) {
	                    writeElement("CountryOfRouting", routing);
	                }
                 }

                 if (message.getSealIDList() != null) {
                	 openElement("SealsID");
                	 for (SealNumber seal : message.getSealIDList()) {
		                writeSealsId(seal);
		             }  
                	 closeElement();
                 }

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
