
/*
 * Function    : BodyExportInvestigationKids.java
 * Titel       :
 * Date        : 10.10.2008
 * Author      : Kewill CSF Houdek
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff_export.xls and kiff-export-reply.xls
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 14.05.2009
 * Label       : EI20090514
 * Description : Abfrage für beide Datums einzeln
 *
 *
 */
 
package com.kewill.kcx.component.mapping.formats.kids.aes;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpNer;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyExportInvestigationKids extends KidsMessage {
	
	private MsgExpNer msgExpNer;
	
    public BodyExportInvestigationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
         try {
            openElement("soap:Body");
                openElement("Investigation");
                    openElement("GoodsDeclaration");
                    String date;
                    date = msgExpNer.getResponseUntil();
                    if (date.length() > 8 ) {   //EI20090506
                        writeDateToString("DateOfLatestPossibleReply", msgExpNer.getResponseUntil());                                               
                    } else {
                    	writeElement("DateOfLatestPossibleReply", msgExpNer.getResponseUntil());                    	                       
                    }      
                    date = msgExpNer.getDateOfInvestigation();
                    if (date.length() > 8 ) {   //EI20090514                       
                        writeDateToString("DateOfInquiry", msgExpNer.getDateOfInvestigation());                        
                    } else {                    	
                    	writeElement("DateOfInquiry", msgExpNer.getDateOfInvestigation());                        
                    }                              
                        writeElement("ReferenceNumber", msgExpNer.getDocumentReferenceNumber());                        
                    closeElement(); // GoodsDeclaration
                closeElement(); // Investigation
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpNer getMsgExpNer() {
		return msgExpNer;
	}

	public void setMsgExpNer(MsgExpNer msgExpNer) {
		this.msgExpNer = msgExpNer;
	}
    
}
