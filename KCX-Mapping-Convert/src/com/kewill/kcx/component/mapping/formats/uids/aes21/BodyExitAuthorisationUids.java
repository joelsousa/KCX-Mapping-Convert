package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExitAuthorisation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : AESD/Exit<br>
 * Created       : 10.08.2013<br>
 * Description   : Uids Body of ExitAuthorisationUids.
 * 				 : z.Z noch von niemanden verwendet 	 				

 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExitAuthorisationUids extends UidsMessageV21 {	

    private MsgExitAuthorisation message = new MsgExitAuthorisation();
    
    public BodyExitAuthorisationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExitAuthorisation getMsgKids() {
		return message;
	}

	public void setMessage(MsgExitAuthorisation argument) {
		this.message = argument;
	}

	public void writeBody() {
		
        try {
        	openElement("soap:Body");
            openElement("Submit");  
               
            	if (this.getCommonFieldsDTO() != null && 
            		!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {           
            		setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
            	} else {
            		setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
            	}
            	
                openElement("Export");
                openElement("ExitAuthorisation"); 
             
                writeParty("Shipper", message.getForwarder()); 
                writeControlResult(message.getStatusOfControl());
                if (!Utils.isStringEmpty(message.getRealOfficeOfExit())) {
                	openElement("CustomsOffices");		
        			   writeElement("OfficeOfExit", message.getRealOfficeOfExit());
        			closeElement(); 
                }
                //EI20131202: writeElement("DateOfPermission", message.getTimeOfPermit());               
                writeFormattedDateTime("DateOfPermission", message.getTimeOfPermit(),
        				message.getTimeOfPermitFormat(), EFormat.ST_DateTimeZ); //EI20131202
                writeElement("DocumentReferenceNumber", message.getUCRNumber());   
                writeElement("DocumentReferenceNumber", message.getUCRNumber());  
                writeElement("PreCustomsClearance", message.getPreCustomsClearance());      //EI20130812         
                writeElement("ReferenceNumber", message.getReferenceNumber());                                
                //writeElement("TotalNumberPackages", message.getTotalNumberPackages());
                
                closeElement(); 
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }
	
}    	
    	
    	
