package com.kewill.kcx.component.mapping.formats.kids.aes21;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtPre;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtPrePos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module		: Export/aes<br>
 * Created		: 24.07.2012.<br>
 * Description  : KIDS-Body of MsgExitPresentation     
 * 				: V21: new Tags in class Party       
 * 
 * @author iwaniuk
 * @version 1.1.00
 */

public class BodyExitPresentationKids extends KidsMessageV21 {

	private MsgExtPre message;	
   
    public BodyExitPresentationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExtPre getMessage() {
		return message;
	}
	public void setMessage(MsgExtPre argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            	openElement("soap:Body");
            	openElement("ExitPresentation");                
                openElement("GoodsDeclaration");                	                    	   
                    	
                    	writeElement("UCRNumber", message.getUCRNumber());                       	
                    	writeDateTimeToString("PresentationTime", message.getPresentationTime());                    	            		         
                        writeElement("UCROtherSystem", message.getUCROtherSystem());                      
                        writeElement("ReferenceNumber", message.getReferenceNumber());                       
                  		writeContact("Contact", message.getContact()); 
                  		writeElement("KindOfInformation", message.getKindOfInformation());  
                  		writeElement("FlagInformation", message.getFlagInformation());
                  		writeMeansOfTransport("MeansOfTransport", message.getMeansOfTransport());                        
                        writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());  
                        writeElement("PreCustomsClearance", message.getPreCustomsClearance());      //EI20130812 
                        writeParty("Forwarder", message.getForwarder()); 
                                                                                        
                        if (message.getGoodsItemList() != null) {                        
                        	for (MsgExtPrePos item : message.getGoodsItemList()) {
                        		writeGoodsItemList(item);                        		
                        	}   
                        }
                       
                 closeElement();	    
                 closeElement();	 
                 closeElement();	
       
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
   
	private void writeGoodsItemList(MsgExtPrePos position) throws XMLStreamException {
    	if (position == null) {
    		return;
    	}
      
    openElement("GoodsItem");
    	writeElement("ItemNumber", position.getItemNumber());      	
    	writeMeansOfTransport("MeansOfTransport", position.getMeansOfTransport());        	
    closeElement();
    } 


 
}