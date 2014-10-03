package com.kewill.kcx.component.mapping.formats.uids.manifest;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;

/**
 * Module 		: Manifest<br>
 * Date Started : 12.11.2012
 * Description  : BodyGoodsReleasedExternal Messages.
 * 
 * @author kron
 * @version 1.0.00
 */

public class BodyGoodsReleasedExternalUids extends UidsMessage {	

    private MsgGoodsReleasedExternal  msgGoodsReleasedExternal = new MsgGoodsReleasedExternal();
    
    public BodyGoodsReleasedExternalUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public void writeBody() {
		
        try {
        	openElement("soap:Body");
               openElement("Submit");
                   setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/manifest/body/200601");
                 openElement("Manifest");
                    openElement("GoodsReleasedExternal");                    
                		writeStringToDate("DateOfPresentation", msgGoodsReleasedExternal.getDateOfPresentation());                	
                		writeElement("ReferenceNumber", msgGoodsReleasedExternal.getReferenceNumber());                	
                		writeElement("RegistrationNumber", msgGoodsReleasedExternal.getRegistrationNumber());
                		if (msgGoodsReleasedExternal.getCustodian() != null || 
                				(msgGoodsReleasedExternal.getPlaceOfCustody() != null)) {
                			openElement("GoodsItem");
                			if (msgGoodsReleasedExternal.getCustodian() != null) {
                				writeAddress(msgGoodsReleasedExternal.getCustodian(), "Custodian");
                			}
                			if (msgGoodsReleasedExternal.getPlaceOfCustody() != null) {
                				writeAddress(msgGoodsReleasedExternal.getPlaceOfCustody(), "PlaceOfCustody");
                			}
                			closeElement(); // GoodsItem
                		}
                      closeElement(); // MsgName
                    closeElement(); // Manifest
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }
	
	 public MsgGoodsReleasedExternal getMsgGoodsReleasedExternal() {
		return msgGoodsReleasedExternal;
	}

	public void setMsgGoodsReleasedExternal(
			MsgGoodsReleasedExternal msgGoodsReleasedExternal) {
		this.msgGoodsReleasedExternal = msgGoodsReleasedExternal;
	}

	public void writeAddress(Address address, String adrtype) throws XMLStreamException {
	    	if (address == null) {
	    	    return;
	    	}    	
	    	if (address.isEmpty()){
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
    	
    	
