package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module       : Export/aes<br>
 * Created      : 24.07.2012<br>
 * Description  : V21: KIDS-Body of BodyConfirmInvestigation.
 * 				: V21: new Tags 
 * 				: AES22- new Tag Contact
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyConfirmInvestigationKids extends KidsMessageV21 {
	
	private MsgExpExt message;
	
    public BodyConfirmInvestigationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
    	try {
            openElement("soap:Body");
                openElement("ConfirmInvestigation");
                    openElement("GoodsDeclaration");  
                    	String date;
                    	date = message.getDateOfExit();
                    	if (date != null) {
                            if (date.length() > 8) {   
                                writeDateToString("DateOfExit", message.getDateOfExit());                         
                            } else {
                                writeElement("DateOfExit", message.getDateOfExit());                          
                            }
                    	}
                       	date = message.getIntendentExitDate();
                       	if (date != null) {
                            if (date.length() > 8) {   
                                writeDateToString("IntendentDateOfExit", message.getIntendentExitDate());
                            } else {                            
                                writeElement("IntendentDateOfExit", message.getIntendentExitDate());
                            }                       
                       	}                    	                      
                        writeElement("Annotation", message.getAnnotation()); 
                        writeElement("ReferenceNumber", message.getReferenceNumber());
                        writeCodeElement("ExitType", message.getExitType(), "KCX0014");
                        writeElement("RealOfficeOfExit", message.getRealOfficeOfExit());  
                        //EI20120919: writeElement("getCustomsOfficeExport", message.getCustomsOfficeExport());
                        writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());    //new for V21
                        writeParty("Declarant", message.getDeclarant());						  //new for V21
                        writeParty("Agent", message.getAgent());						          //new for V21   
                        writeContact("Contact", message.getContact());                //EI20130808 new for AES22
                                          
                    closeElement(); // GoodsDeclaration
                closeElement(); // ConfirmInvestigation
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpExt getMessage() {
		return message;
	}

	public void setMessage(MsgExpExt msgExpSta) {
		this.message = msgExpSta;
	}
    
}
