package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 18.07.2012<br>
 * Description   : Uids body of ExportPreAdvice.
 * 				 : V21 - new Tags
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
 
public class BodyPreAdviceUids extends UidsMessageV21 {
	
    private MsgExpDat  message = new MsgExpDat();
 
    public BodyPreAdviceUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExpDat getMessage() {
		return message;
	}

	public void setMessage(MsgExpDat message) {
		this.message = message;
	}

    
	public void writeBody() {
				
        try {
        	openElement("soap:Body");
            openElement("Submit");               
            	//EI20120917: setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	//EI20120917:
        		if (this.getCommonFieldsDTO() != null && 
        				!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {
        			setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
        		} else {
        			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        		}
        	
                openElement("Export");
                openElement("ExportPreAdvice");                         
                    writeParty("Consignee", message.getConsignee(), null, null);
                	writeContact(message.getContact());                                 
                	writeParty("Declarant", message.getDeclarant());                		                                       	
                	writeParty("DeclarantRepresentative", message.getAgent());     
                	writeParty("Exporter", message.getConsignor());  
                	writeParty("Shipper", message.getForwarder()); 
                	writeParty("Subcontractor", message.getSubcontractor());
                	writeElement("AuthorisationID", message.getAuthorizationNumber());                    	
                    writeCustomsOffices(message.getCustomsOfficeExport(), "", "", "");
                    //writeElement("DateOfPreAdvice", message.getAdvanceNoticeTime());                     
                    writeFormattedDateTime("DateOfPreAdvice", message.getAdvanceNoticeTime(),
            				message.getAdvanceNoticeTimeFormat(), EFormat.ST_DateTimeZ); //EI20131202
                    if (message.getExportRefundHeader() != null) {                    	
                    	writeElement("DestinationCountry", message.getExportRefundHeader().getDestinationCountry());                       	
                    }                    	
                    writeElement("LocalReferenceNumber", message.getOrderNumber());            
                    	writeMeansOfTransport(message.getTransportMeansDeparture(), "Departure");
                    if (message.getTransportMeansDeparture() != null) {
                    	String temp = message.getTransportMeansDeparture().getTransportationType();
                    	if (!Utils.isStringEmpty(temp)) {
                    		openElement("MeansOfTransport");
                    		setAttribute("TransportType", "Departure");                      
                    			writeElement("Mode", temp);
                    		closeElement();
                    	}
                    }
                    writeElement("ParticipantsCombination", message.getDeclarantIsConsignor());
                    writePlaceofLoading(message.getPlaceOfLoading());                		
                	writeElement("ReferenceNumber", message.getReferenceNumber());
                	writeElement("Remark", message.getAnnotation());                		
                	writeShipmentPeriod(message.getLoadingTime());                		
                	writeTypeOfDeclaration("", message.getTypeOfPermit(), message.getProcedure()); //new for V21
                		
                    if (message.getGoodsItemList() != null) {
                        for (MsgExpDatPos item : message.getGoodsItemList()) {
                        	writeGoodsItemList(item);
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
	private void writeGoodsItemList(MsgExpDatPos item) throws XMLStreamException {
		if (item == null) {
		    return;
		}
		
		openElement("GoodsItem"); 		
			writeElement("ItemNumber", item.getItemNumber());		
			writeElement("ArticleNumber", item.getArticleNumber());
			writeElement("GoodsDescription", item.getDescription());      //new for V21			
			writeCommodityCode(item.getCommodityCode());    	
			if (item.getPackagesList() != null) {
				for (Packages pack : item.getPackagesList()) {       			
					writePackaging(pack, ""); 
				}                		
			}    	             			 
			if (item.getDocumentList() != null) {                          //new for V21
	    		for (DocumentV20 doc : item.getDocumentList()) {
	    			writeProducedDocument(doc); 
	    		}
	    	}
			writeExportRestitutionItem(item.getExportRefundItem(), null);  //new for V21: mehr Tags, in xls sind R(equired) 
    	closeElement();
	}

}    	
