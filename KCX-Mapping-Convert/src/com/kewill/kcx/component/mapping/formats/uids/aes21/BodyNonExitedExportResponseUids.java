package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpExt;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Export/aes<br>
 * Created       : 18.07.2012<br>
 * Description   : Uids body of NonExitedExportResponse.
 * 				 : V21 - new Tags
 * 				 : AES22- new Tag Contact
 * 
 * @author iwaniuk
 * @version 2.1.00
 * 
 * Changes: AES22 new Tag: Contact
 */
 
public class BodyNonExitedExportResponseUids extends UidsMessageV21 {
	
    private MsgExpExt message;
   
    public BodyNonExitedExportResponseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
  
	public void writeBody() {
        try {        	
        	openElement("soap:Body");
            openElement("Submit");               
            	//EI20120917; setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	//EI20120917:
        		if (this.getCommonFieldsDTO() != null && 
        				!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {
        			setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
        		} else {
        			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
        		}
        		
                openElement("Export");
                openElement("NonExitedExportResponse");
                
                	writeElement("ReferenceNumber", message.getReferenceNumber());
                	//V21 ?? DocumentReferenceNumber == Kids.UCRNumber
                    writeElement("Remark", message.getAnnotation());
                    if (!Utils.isStringEmpty(message.getRealOfficeOfExit())) {
                    	openElement("CustomsOffices");
                    		writeElement("OfficeOfActualExit", message.getRealOfficeOfExit());
                    		writeElement("OfficeOfExport", message.getCustomsOfficeExport());  //new for V21
                    	closeElement();
                    }
                    writeStringToDate("DateOfExit", message.getDateOfExit());
                    writeStringToDate("DateOfAwaitedExit", message.getIntendentExitDate());
                    writeElement("TypeOfExit", message.getExitType());   
                    writeParty("Declarant", message.getDeclarant());               //new for V21
                    writeParty("DeclarantRepresentative", message.getAgent());     //new for V21
                    writeContact(message.getContact());                            //EI20130808 new for AES22
                    
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

	public MsgExpExt getMessage() {
		return message;
	}

	public void setMessage(MsgExpExt message) {
		this.message = message;
	}
	
}
