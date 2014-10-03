
/*
 * Function    : BodyExportConfirmationKids.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF Schmidt / kron
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff_export.xls and kiff-export-reply.xls
 * Parameters  : 

 * Changes 
 * -----------
 *
 * Author      : EI
 * Date        : 27.04.2009
 * Label       : EI20090427
 * Description : KCX-Code checked (no changes)
 */
 
package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyExportConfirmationKids extends KidsMessage {
	
	private MsgExpCon msgExpCon;
	private PDFInformation pdfInformation;
	List<String> pdflist; 
	
    public BodyExportConfirmationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
    	int listLength = 0;
        try {
            openElement("soap:Body");
                openElement("Confirmation");
                    openElement("GoodsDeclaration");
                        writeCodeElement("KindOfAnswer", msgExpCon.getKindOfAnswer(),"KCX0045");
                        writeElement("UCRNumber", msgExpCon.getUCRNumber());
                        writeDateTimeToString("ReceiveTime", msgExpCon.getReceiveTime());
                        writeElement("ReferenceNumber", msgExpCon.getReferenceNumber());   
                        /* EI20110811:
                        pdfInformation = msgExpCon.getPdfInformation();
                        if (pdfInformation != null) {
                            openElement("PDFInformation");
                            writeElement("Name", msgExpCon.getPdfInformation().getName());
                            writeElement("Directory", msgExpCon.getPdfInformation().getDirectory());
                            writeElement("NewDirectory", msgExpCon.getPdfInformation().getNewDirectory());
                           	pdflist = pdfInformation.getPdflist();
                           	if (pdflist != null ) listLength = pdflist.size(); 
                            for(int i=0; i < listLength; i++) {
                           			writeElement("base64", pdflist.get(i));
                            }
                        	closeElement(); // PDFInformation
                        }
                        */
                        writePDFInformationList(msgExpCon.getPdfInformationList());     //EI20110811
                        
                    closeElement(); // GoodsDeclaration
                closeElement(); // ExportDeclaration
            closeElement(); // soap:Body
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
