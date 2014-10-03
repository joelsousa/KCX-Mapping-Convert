package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExitAuthorisation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module		: AES/Exit<br>
 * Created		: 10.08.2013<br>
 * Description	: BodyExitAuthorisationKids. 
 * 				: V21: new message (for LUX) 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExitAuthorisationKids extends KidsMessageV21 {
	
	private MsgExitAuthorisation message;
	
    public BodyExitAuthorisationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
         try {
            openElement("soap:Body");
            openElement("ExitAuthorization");
            openElement("GoodsDeclaration");            	
                writeElement("UCRNumber", message.getUCRNumber());   
                writeElement("TimeOfPermit", message.getTimeOfPermit());
                //EI20140131: writeElement("StatusOfControl", message.getStatusOfControl());
                writeCodeElement("StatusOfControl", message.getStatusOfControl(), "KCX0009");  //EI20140131
                writeElement("UCROtherSystem", message.getUCROtherSystem());                              
                writeElement("ReferenceNumber", message.getReferenceNumber());                                               
                writeElement("RealOfficeOfExit", message.getRealOfficeOfExit());
                writeElement("PreCustomsClearance", message.getPreCustomsClearance());      //EI20130812 
                writeTIN("Forwarder", message.getForwarderTin());     		
                writeElement("TotalNumberPackages", message.getTotalNumberPackages());
                
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExitAuthorisation getMessage() {
		return message;
	}

	public void setMessage(MsgExitAuthorisation message) {
		this.message = message;
	}
    
}
