package com.kewill.kcx.component.mapping.formats.uids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageManifest20;
//import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;

/**
 * Module 		: Manifest<br>
 * Date Started : 28.08.2013<br>
 * Description  : BodyGoodsReleasedExternal Messages.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyGoodsReleasedExternalUids extends UidsMessageManifest20 {	

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
	
}    	
	 	
    	
