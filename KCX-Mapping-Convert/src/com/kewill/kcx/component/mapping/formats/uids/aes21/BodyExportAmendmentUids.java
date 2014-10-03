package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmdPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.AmendmentInfo;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 18.07.2012<br>
 * Description   : Uids Body of ExportAmendment.
 * 			     : New Tags for V21
 * 				 : TODO-IWA - es gibt kein ExportAmendment for V21 ???
 
 * @author iwaniuk
 * @version 2.1.00
 */
public class BodyExportAmendmentUids extends UidsMessageV21 {
	
    private MsgExpAmd message;
    
    public BodyExportAmendmentUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
		/* new for V21 - begin
		AmendmentInfo amendmentInfo;
		Party declarant;
		TIN declarantTIN;
		ContactPerson declarantContactPerson; 
		Party agent;
		TIN agentTIN;
		ContactPerson agentContactPerson; 
		//new for V21 - end
		*/
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
                    openElement("ExportAmendment");
	                   writeElement("GrossMass", message.getGrossMass());
	                   writeElement("ReferenceNumber", message.getReferenceNumber());
	                   writeParty("Declarant", message.getDeclarant());
	                   writeParty("Agent", message.getAgent());
	                   
	                   if (message.getGoodsItemList() != null) {
	                        for (MsgExpAmdPos item : message.getGoodsItemList()) {	                        	
	                        	if (item != null) {
	                            	openElement("GoodsItem");
	                        			writeElement("ItemNumber", item.getItemNumber());
	                        			writeElement("GrossMass", item.getGrossMass());
	                        			writeElement("NetMass", item.getNetMass());	                        			
	                        			if (item.getExportRefundItem() != null)  {
	                        			    openElement("ExportRestitutionItem");
	                        					writeElement("Amount", item.getExportRefundItem().getAmount());
	                        				closeElement();
	                        			}
	                        		closeElement(); 
	                        	}
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
	
	public MsgExpAmd getMessage() {
		return message;
	}

	public void setMessage(MsgExpAmd message) {
		this.message = message;
	}
}
