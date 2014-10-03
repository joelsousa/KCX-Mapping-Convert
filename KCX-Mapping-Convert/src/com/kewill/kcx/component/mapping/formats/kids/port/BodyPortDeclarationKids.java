package com.kewill.kcx.component.mapping.formats.kids.port;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.ConsolidatedContainer;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessagePortGpo;

/**
 * Module	   : Port.<br>
 * Created	   : 28.10.2011<br>
 * Description : BodyPortDeclarationKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyPortDeclarationKids extends KidsMessagePortGpo {

	private MsgPortDeclaration message;	

    public BodyPortDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgPortDeclaration getMessage() {
		return message;
	}
	
	public void setMessage(MsgPortDeclaration argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("PortDeclaration");
            openElement("GoodsDeclaration"); 
                  
            	writeElement("ReferenceNumber", message.getReferenceNumber()); 
            	writeElement("PortSystem", message.getPortSystem());       
            	writeElement("PortRegistrationNumber", message.getPortRegistrationNumber());               	     
            	//EI20120417: writeElement("DeclarationState", message.getMessageFunction());  
            	writeElement("MessageFunction", message.getMessageFunction());   
            	writeElement("DeclarationType", message.getDeclarationType());  
            	writeElement("DeclarationTypeSpecification", message.getDeclarationTypeSpecification());  
            	writeElement("ActivityType", message.getActivityType());              	
            	writeElement("DeclarationMode", message.getDeclarationMode());   
            	writeElement("TestMode", message.getTestMode());               
            	writeElement("DeclarationRemark", message.getDeclarationRemark());               	                   	         
            	writeElement("ShipperReferenceNumber", message.getShipperReferenceNumber());             	
            	writeParty("Shipper", message.getShipper()); 
            	writeElement("TerminalCode", message.getTerminalCode());				
            	writeElement("OfferNumber", message.getOfferNumber());			
            	writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());
            	writeParty("Agent", message.getAgent()); 
            	//writeParty("InvoiveConsignee", message.getInvoiceConsignee()); 
            	writeParty("AlternativeConsignee", message.getInvoiceConsignee()); 
            	writeParty("FOBShipper", message.getFOBShipper()); 
            	writeParty("Tally", message.getTally());             	
            	writeContactPerson("ContactPersonForDangerousGoods", message.getContactPersonForDangerousGoods());
            	writeVoyage(message.getVoyage());	
            	writePostCarriage(message.getPostCarriage());	
            	writePreCarriage(message.getPreCarriage());	
            	writeElement("StockMarker", message.getStockMarker());				
            	writeElement("LoadMarker", message.getLoadMarker());	
            	writeAdditionInformation(message.getAdditionalData());  
            
            	writeElement("ConsolidatedContainerRemark", message.getConsolidatedContainerRemark());  
                if (message.getConsolidatedContainerList() != null) {
 	                for (ConsolidatedContainer saco : message.getConsolidatedContainerList()) {
 	                   writeConsolidatedContainer(saco);
 	                }
                }
                                                                 
                if (message.getContainerList() != null) {                	 
 	                for (Container container : message.getContainerList()) {
 	                   writeContainer(container);
 	                } 	             
                }
                 
                if (message.getGoodsItemList() != null) {
  	                for (GoodsItem goodsItem : message.getGoodsItemList()) {
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
