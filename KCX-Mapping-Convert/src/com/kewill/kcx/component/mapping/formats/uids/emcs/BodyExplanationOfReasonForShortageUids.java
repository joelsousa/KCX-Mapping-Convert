package com.kewill.kcx.component.mapping.formats.uids.emcs;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOfReasonForShortage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOfReasonForShortagePos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

 /**
 * Module		: EMCS V20<br>
 * Created		: 20.07.2011<br>
 * Description	: Body of Uids-EMCSExplanationOnReasonForShortage.
 *              : new for EMCS V20
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyExplanationOfReasonForShortageUids extends UidsMessageEMCS {
	
	private MsgExplanationOfReasonForShortage message; 
	private String version = "";         
 
    public BodyExplanationOfReasonForShortageUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExplanationOfReasonForShortage getMessage() {
		return message;
	}

	public void setMessage(MsgExplanationOfReasonForShortage message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {  
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");               
            openElement("EMCSExplanationOnReasonForShortage");      //EI20110927
            
                writeElement("LocalReferenceNumber", message.getReferenceNumber());                    
                writeExciseMovementEaad(message.getSequenceNumber(), message.getAadReferenceCode(), 
                    		message.getDateAndTimeOfValidation(), version);
                writeElement("DispatchImportOffice", message.getDispatchImportOffice());
                writeElement("SubmitterType", message.getSubmitterType());   
                //V20 - DateTime format changed:
                //writeStringToDate("DateOfAnalysis", message.getDateOfAnalysis());
                writeFormattedDateTime("DateOfAnalysis", message.getDateOfAnalysis(),
                			EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
                if (message.getGlobalExplanation() != null) {
                    writeElementWithAttribute("GlobalExplanation", message.getGlobalExplanation().getText(),
			                  "language", message.getGlobalExplanation().getLanguage());
                }
                writeTrader("ConsigneeTrader", message.getConsignee(), version);                
                writeTrader("ConsignorTrader", message.getConsignor(), version);
                     	                        	               
                if (message.getGoodsItemList() != null) {            				//EI20110926
                    for (MsgExplanationOfReasonForShortagePos item : message.getGoodsItemList()) {	                        		
                    	writeGoodsItem(item);                    		
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
	
	
    private void writeGoodsItem(MsgExplanationOfReasonForShortagePos goodsItem) throws XMLStreamException {
    	
    	openElement("BodyAnalysis");
    	
      	writeElement("BodyRecordUniqueReference", goodsItem.getItemNumber());
      	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
      	writeElement("Quantity", goodsItem.getQuantity());    	
      	if (goodsItem.getExplanation() != null) {
      		writeElementWithAttribute("Explanation", goodsItem.getExplanation().getText(),
      			                  "language", goodsItem.getExplanation().getLanguage());
      	}      
      	
      	closeElement(); 
    }	

}    	
