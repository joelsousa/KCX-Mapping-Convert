package com.kewill.kcx.component.mapping.formats.kids.manifest;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	   : Manifest.<br>
 * Created	   : 12.11.2012<br>
 * Description : BodyGoodsReleasedExternalKids
 * 
 * @author Christine Kron
 * @version 1.0.00
 *
 */
public class BodyGoodsReleasedExternalKids extends KidsMessage {

	private MsgGoodsReleasedExternal message;	

    public BodyGoodsReleasedExternalKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgGoodsReleasedExternal getMessage() {
		return message;
	}
	
	public void setMessage(MsgGoodsReleasedExternal argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("GoodsReleasedExternal");
            openElement("GoodsDeclaration"); 
            	writeElement("DateOfPresentation", message.getDateOfPresentation());  
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("RegistrationNumber", message.getRegistrationNumber());                
                if (message.getCustodian() != null) {
                	writeAddress(message.getCustodian(), "Custodian");
                }
                if (message.getPlaceOfCustody() != null) {
                	writeAddress(message.getPlaceOfCustody(), "PlaceOfCustody");
                }
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

    private void writeAddress(Address address, String adrtype) throws XMLStreamException {
    	if (address == null) {
    	    return;
    	}    	
    	if (address.isEmpty()) {
    	    return; 
     	}
     	
     	openElement(adrtype);
    		   writeElement("Name", address.getName());
    		   writeElement("StreetAndNumber", address.getStreetAndNumber());
    		   writeElement("PostalCode", address.getPostalCode());
    		   writeElement("City", address.getCity());
    		   writeElement("CountryCodeISO", address.getCountryCodeISO());
        closeElement();
    }  
    
}

