package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 27.06.2013<br>
 * Description : BodyGoodsReleasedExternalKids
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 *
 */
public class BodyGoodsReleasedExternalKids extends KidsMessageManifest20 {

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
            	writeDateToString("DateOfPresentation", message.getDateOfPresentation());  
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("RegistrationNumber", message.getRegistrationNumber()); 
                writeDateToString("RegistrationDate", message.getRegistrationDate());                
                writeAddress("Custodian", message.getCustodian());               
                writeAddress("PlaceOfCustody", message.getPlaceOfCustody());                
                            
                writeLocalApplication(message.getLocalApplication());
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

   

    private void writeGoodsItem(GoodsItem item) {
    	if (item == null) {
    		return;
    	}
    	try {
        	openElement("GoodsItem");        	
        		writeElement("ItemNumber", item.getItemNumber());        		
        		writeParty("Disposal", item.getDisposal());                     
        		writeParty("Custodian", item.getCustodian());
             closeElement();
    	} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
		
	}

}

