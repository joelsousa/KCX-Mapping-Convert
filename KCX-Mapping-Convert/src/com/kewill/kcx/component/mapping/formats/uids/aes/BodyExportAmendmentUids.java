package com.kewill.kcx.component.mapping.formats.uids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmdPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;

/*
 * Funktion    : BodyExportAmendmentUids.java
 * Titel       :
 * Erstellt    : 16.03.2009
 * Author      : CSF GmbH / Alfred Krzoska
 * Description : valid Names of KIDS-Messagenames to UIDS-Messagenames in Export
 * 			   : relates to (Amendment) kids-export.xls 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 *
 */
public class BodyExportAmendmentUids extends UidsMessage {
	
    private MsgExpAmd   			msgExpAdn;
    private int 					len;
	private List 	<MsgExpAmdPos>	amdList;
	private MsgExpAmdPos 			amdPos;
	private ExportRefundItem 		refundItem;
    
    public BodyExportAmendmentUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try { 
             openElement("soap:Body");
                openElement("Submit");
                // C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                // setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                    openElement("Export");
                    	openElement("ExportAmendment");
	                        writeElement("GrossMass", msgExpAdn.getGrossMass());
	                        writeElement("ReferenceNumber", msgExpAdn.getReferenceNumber());
	                        amdList = msgExpAdn.getGoodsItemList();
	            			len = amdList.size();
	                        for(int i=0; i < len; i++) {
	                        	amdPos = (MsgExpAmdPos)amdList.get(i);
	                        	if(amdPos != null) {
	                            	openElement("GoodsItem");
	                        			writeElement("ItemNumber", amdPos.getItemNumber() );
	                        			writeElement("GrossMass", amdPos.getGrossMass());
	                        			writeElement("NetMass", amdPos.getNetMass());
	                        			refundItem = amdPos.getExportRefundItem();
	                        			if ( refundItem != null)  {
	                        			    openElement("ExportRestitutionItem");
	                        					writeElement("Amount", refundItem.getAmount());
	                        				closeElement(); //ExportRestitutionItem
	                        			}
	                        		closeElement(); // GoodsItem
	                        	}
	                        }
                        closeElement(); // ExportAmendment
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

	public MsgExpAmd getMsgExpAdn() {
		return msgExpAdn;
	}

	public void setMsgExpAdn(MsgExpAmd msgExpAdn) {
		this.msgExpAdn = msgExpAdn;
	}
}