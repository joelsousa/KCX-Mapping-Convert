package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 17.09.2012<br>
 * Description   : Uids Body of ExportConfirmation.
 * 				 : V21: only NameSpase is changed: written from CommonFieldsDTO

 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportConfirmationUids extends UidsMessage {
	
    private MsgExpCon msgExpCon;
   
    public BodyExportConfirmationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {        	
        	openElement("soap:Body");
            openElement("Submit");
                //EI20120917:
            	if (this.getCommonFieldsDTO() != null && 
                		!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {            	             
                	setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
                } else {
                	setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                }
            	
                openElement("Export");
                openElement("Confirmation");                    	
                	writeFormattedDateTime("DateOfReceipt", msgExpCon.getReceiveTime(),   
                        		msgExpCon.getReceiveTimeFormat(), EFormat.ST_DateTimeZ);	   
                    writeElement("DocumentReferenceNumber", msgExpCon.getUCRNumber());
                    writeElement("FunctionCode", msgExpCon.getKindOfAnswer());                      
                    writePDFInformationList(msgExpCon.getPdfInformationList());                             
                    writeElement("ReferenceNumber", msgExpCon.getReferenceNumber());
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
	
	public MsgExpCon getMsgExpCon() {
		return msgExpCon;
	}

	public void setMsgExpCon(MsgExpCon msgExpCon) {
		this.msgExpCon = msgExpCon;
	}
	
}
