/*
 * Function    : BodyConfirmInvestigation.java
 * Titel       :
 * Date        : 22.04.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff_export.xls (BodyConfirmInvestigation)
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Label       :
 * Description : KCX-Code checked
 *
 * Author      : EI
 * Date        : 07.05.2009
 * Label       :
 * Description : wrong write-method for Annotation, different write-method for Date-Tags
 */
 
package com.kewill.kcx.component.mapping.formats.kids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Modul		: BodyConfirmInvestigationKids<br>
 * Erstellt		: 22.04.2009<br>
 * Beschreibung	: Construct Body of KIDS message BodyConfirmInvestigation.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyConfirmInvestigationKids extends KidsMessage {
	
	private MsgExpExt 	msgExpExt;
	
    public BodyConfirmInvestigationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("ConfirmInvestigation");
                    openElement("GoodsDeclaration");  
                    	String date;
                    	date = msgExpExt.getDateOfExit();
                    	if (date != null) {
                            if (date.length() > 8) {   //EI20090507
                                writeDateToString("DateOfExit", msgExpExt.getDateOfExit());                         
                            } else {
                                writeElement("DateOfExit", msgExpExt.getDateOfExit());                          
                            }
                    	}
                       	date = msgExpExt.getIntendentExitDate();
                       	if (date != null) {
                            if (date.length() > 8) {   //EI20090514
                                writeDateToString("IntendentDateOfExit", msgExpExt.getIntendentExitDate());
                            } else {                            
                                writeElement("IntendentDateOfExit", msgExpExt.getIntendentExitDate());
                            }                       
                       	}
                    	
                        //EI20090507: writeDateTimeToString("Annotation", msgExpExt.getAnnotation());     
                        writeElement("Annotation", msgExpExt.getAnnotation()); //EI20090507
                        writeElement("ReferenceNumber", msgExpExt.getReferenceNumber());
                        writeCodeElement("ExitType", msgExpExt.getExitType(), "KCX0014");
                        writeElement("RealOfficeOfExit", msgExpExt.getRealOfficeOfExit());                        
                    closeElement(); // GoodsDeclaration
                closeElement(); // ConfirmInvestigation
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpExt getMsgExpSta() {
		return msgExpExt;
	}

	public void setMsgKids(MsgExpExt msgExpExt) {
		this.msgExpExt = msgExpExt;
	}
    
}
