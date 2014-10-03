/*
 * Function    : BodyExportPrenotificationKidsUK.java
 * Titel       :
 * Date        : 18.03.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to kiff-export.xls (Export pre-notification)				
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 22.04.2009
 * Description : msgUids  replaced with MsgExpDat 
 * Description : KCX-Code checked (no changes)
 * 
 * Author      : E.Iwaniuk
 * Date        : 08.06.2009
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk      
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : BodyExportPrenotificationKidsUK<br>
 * Erstellt     : 18.03.2009<br>
 * Beschreibung : Mapping of KIDS-Format of Export PreNotification into UIDS-Format of ExportPreNotification. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyExportPrenotificationKids extends KidsMessage {

	private MsgExpDat msgExpDat;	    
	int listSizePos = 0; 

    public BodyExportPrenotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpDat getMessage() {
		return msgExpDat;
	}
	public void setMessage(MsgExpDat argument) {
		this.msgExpDat = argument;
	}
	
    public void writeBody() {
        try {
            openElement("soap:Body");
            	openElement("Prenotification");  //!!! Export Prenotification !!!
                //openElement(this.kidsMessageName);
                    openElement("GoodsDeclaration");                            
                        writeCodeElement("TypeOfPermit", msgExpDat.getTypeOfPermit(), "KCX0026");                      
                        writeDateTimeToString("AdvanceNoticeTime", msgExpDat.getAdvanceNoticeTime());                  	           	                               
                        writeElement ("Annotation", msgExpDat.getAnnotation());  
                        writeElement ("ReferenceNumber", msgExpDat.getReferenceNumber());                   		            	
                        writeElement ("OrderNumber", msgExpDat.getOrderNumber());                   		                 	
                        writeElement ("AuthorizationNumber", msgExpDat.getAuthorizationNumber());
                		writeElement ("DeclarantIsConsignor", msgExpDat.getDeclarantIsConsignor());               		                		
                		//EI20090608:writeElement ("Clerk", msgExpDat.getClerk());
                		writeContact("Contact", msgExpDat.getContact()); //EI20090608 
                  		
                		if (msgExpDat.getTransportMeansDeparture() != null) {
                			String temp = msgExpDat.getTransportMeansDeparture().getTransportationType();
                			if (!Utils.isStringEmpty(temp) ) {
                				openElement("MeansOfTransportDeparture");
                				writeCodeElement("TransportationType", temp, "KCX0004");
                				closeElement(); 
                			}
                  		}                		                	 
                        writePlaceOfLoading(msgExpDat.getPlaceOfLoading());                                                
                        writeElement("CustomsOfficeExport", msgExpDat.getCustomsOfficeExport());
                        
                        if (msgExpDat.getExportRefundHeader() != null) {
                        	String country = msgExpDat.getExportRefundHeader().getDestinationCountry();
                        	if (Utils.isStringEmpty(country)) {
                        		openElement("ExportRefundHeader");
                        		writeElement("DestinationCountry", country);
                        		closeElement();
                        	}
                        }
                        writeLoadingTime(msgExpDat.getLoadingTime());  
                        if (msgExpDat.getForwarder() != null) {                        	
                        	writeContact("Forwarder", msgExpDat.getForwarder().getContactPerson());  
                        }
                        writeParty("Consignor", msgExpDat.getConsignor());                            
                        writeParty("Declarant", msgExpDat.getDeclarant());                       
                        writeParty("Agent", msgExpDat.getAgent());                                                                                                    	
                       	writeParty("Subcontractor", msgExpDat.getSubcontractor());                                               
                       	writeParty("Consignee", msgExpDat.getConsignee());                         	                                               
    
                        List <MsgExpDatPos> goodsItemList = msgExpDat.getGoodsItemList();
                        if (goodsItemList != null) {
                        	for (int i = 0; i < goodsItemList.size(); i++) {                        		                        		
                        		writeGoodsItemList(goodsItemList.get(i));                        		
                        	}   
                        }
                    closeElement();	    
                    closeElement();	 
            
        }  //try                      
        	catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
    
    private void writeGoodsItemList(MsgExpDatPos argument) throws XMLStreamException {
    	if (argument == null) return;
    	
    openElement("GoodsItem");
    	writeElement ("ItemNumber", argument.getItemNumber());      	
    	writeElement ("ArticleNumber", argument.getArticleNumber());     
    	writeCommodityCode(argument.getCommodityCode());
    	if (argument.getExportRefundItem() != null) {
    		String amount = argument.getExportRefundItem().getAmount();
    		String unit = argument.getExportRefundItem().getUnitOfMeasurement();
    		if (!(Utils.isStringEmpty(amount) && Utils.isStringEmpty(unit)) ) {
    			//writeExportRefundItem(msgExpDatPos.getExportRefundItem());
    			openElement("ExportRefundItem");    			
    				writeElement("Amount", amount);  
    				writeCodeElement("UnitOfMeasurement", unit, "KCX0017");
    			closeElement();
    		}
    	}
    	//writeParty("Consignee", argument.getConsignee()); 
    	writePackagesList(argument.getPackagesList(), "");
    closeElement();
    } 
 
}  
 

	  
	  