package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      : Export/aes<br>
 * Created     : 18.07.2012<br>
 * Description : Mapping of KIDS-Format of Export PreNotification into UIDS-Format of ExportPreNotification.
 * 			   : V21: new Tags   
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportPrenotificationKids extends KidsMessageV21 {

	private MsgExpDat message;	    

    public BodyExportPrenotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpDat getMessage() {
		return message;
	}
	public void setMessage(MsgExpDat argument) {
		this.message = argument;
	}
	
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("PreNotification");                 
            openElement("GoodsDeclaration");                            
                writeCodeElement("TypeOfPermit", message.getTypeOfPermit(), "KCX0026");                      
                writeDateTimeToString("AdvanceNoticeTime", message.getAdvanceNoticeTime());
                writeElement("Annotation", message.getAnnotation());  
                writeElement("ReferenceNumber", message.getReferenceNumber());       
                writeElement("OrderNumber", message.getOrderNumber());           
                writeElement("AuthorizationNumber", message.getAuthorizationNumber());
                writeElement("DeclarantIsConsignor", message.getDeclarantIsConsignor());                  		
                writeContact("Contact", message.getContact());                  	
                if (message.getTransportMeansDeparture() != null) {
                	String temp = message.getTransportMeansDeparture().getTransportationType();
                	if (!Utils.isStringEmpty(temp)) {
                		openElement("MeansOfTransportDeparture");
                			writeCodeElement("TransportationType", temp, "KCX0004");
                		closeElement(); 
                	}
                }                		                	 
                writePlaceOfLoading(message.getPlaceOfLoading());                                                
                writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());                     
                if (message.getExportRefundHeader() != null) {
                    String country = message.getExportRefundHeader().getDestinationCountry();
                    if (Utils.isStringEmpty(country)) {
                        openElement("ExportRefundHeader");
                        	writeElement("DestinationCountry", country);
                        closeElement();
                    }
                }
                writeLoadingTime(message.getLoadingTime());  
                if (message.getForwarder() != null) {                        	
                   writeContact("Forwarder", message.getForwarder().getContactPerson());  
                }
                writeParty("Consignor", message.getConsignor());                            
                writeParty("Declarant", message.getDeclarant());                       
                writeParty("Agent", message.getAgent());              
                writeParty("Subcontractor", message.getSubcontractor());                                               
                writeParty("Consignee", message.getConsignee());                         	                                               
                writeElement("Procedure", message.getProcedure());           //new for V21
               
                if (message.getGoodsItemList() != null) {
                   for (MsgExpDatPos item : message.getGoodsItemList()) {                        		                        		
                        writeGoodsItemList(item);                        		
                   }   
                }
                      
           closeElement();	    
           closeElement();	 
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
    private void writeGoodsItemList(MsgExpDatPos item) throws XMLStreamException {
    	if (item == null) {
    		return;
    	}
    	
    openElement("GoodsItem");
    	writeElement("ItemNumber", item.getItemNumber());      	
    	writeElement("ArticleNumber", item.getArticleNumber());   
    	writeElement("Description", item.getDescription());      //new for V21
    	writeCommodityCode(item.getCommodityCode());    	
    	writeExportRefundItem(item.getExportRefundItem());       //new for V21: mehr Tags, in xls sind R(equired) 
    	writePackagesList(item.getPackagesList(), "");
    	//writeParty("Consignee", item.getConsignee()); 
    	
    	if (item.getDocumentList() != null) {                    //new for V21
    		for (DocumentV20 doc : item.getDocumentList()) {
    			writeDocument(doc);
    		}
    	}
    closeElement();
    } 
 
}  
