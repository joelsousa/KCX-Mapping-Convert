package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmdPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/*
 * Function    : BodyExportAmendmentKids.java
 * Date        : 16.03.2009
 * Author      : Kewill CSF Krzoska
 * Description : 
 * 
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Label       :
 * Description : KCX-Code checked (no changes)
 */
 
public class BodyExportAmendmentKids extends KidsMessage {
	
	private MsgExpAmd 	msgExpAdn				= null;	
    List <MsgExpAmdPos>	goodsItemList 		= null;
    MsgExpAmdPos 		msgExpAdnPos 		= null;
    ExportRefundItem 	refundItem 			= null; 
    
	
    public BodyExportAmendmentKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public MsgExpAmd getMessage() {
		return msgExpAdn;
	}
	public void setMessage(MsgExpAmd argument) {
		this.msgExpAdn = argument;
	}
	
    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("Amendment");
                    openElement("GoodsDeclaration");
                    	writeElement("Grossmass", msgExpAdn.getGrossMass());
                    	writeElement("ReferenceNumber", msgExpAdn.getReferenceNumber());
// ------  GoodsItem
                    	goodsItemList = msgExpAdn.getGoodsItemList();
                        if (goodsItemList != null) {
	                      	for (int i = 0; i < goodsItemList.size(); i++) {
	                      		msgExpAdnPos = (MsgExpAmdPos)goodsItemList.get(i);
	                      		
	                      		if (msgExpAdnPos != null) {
	                      			writeElement("ItemNumber", msgExpAdnPos.getItemNumber());
	                      			writeElement("NetMass", msgExpAdnPos.getNetMass());
	                      			writeElement("GrossMass", msgExpAdnPos.getGrossMass());
	                      			refundItem = msgExpAdnPos.getExportRefundItem();
	                      			if (refundItem != null)  {
	                      				openElement("ExportRefundItem");
	                      					writeElement("Amount", refundItem.getAmount());
	                      				closeElement(); // ExportRefundItem
	                      			}
	                      		}  //if (msgExpAdnPos != null) {
	                      	}   //for (int i = 0; i < goodsItemList.size(); i++) {
                        }  //if (goodsItemList != null) {
                    closeElement(); // GoodsDeclaration
                closeElement(); // Amendment
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpAmd getmsgExpAdn() {
		return msgExpAdn;
	}

	public void setMsgExpCan(MsgExpAmd msgExpAdn) {
		this.msgExpAdn = msgExpAdn;
	}
}