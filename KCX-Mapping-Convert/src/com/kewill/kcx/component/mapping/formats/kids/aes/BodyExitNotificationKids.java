/*
 * Function    : BodyExitNotificationUK
 * Titel       :
 * Date        : 29.09.2008
 * Author      : Kewill CSF / houdek
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff-export.xls
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Label       :
 * Description : msgUids replaced with MsgExtNot
 *             : KCX-Code checked 
 *
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNot;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExtNotPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyExitNotificationKids extends KidsMessage {

	private MsgExtNot msgExtNot;

    public BodyExitNotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExtNot getMessage() {
		return msgExtNot;
	}
	
	public void setMessage(MsgExtNot message) {
		this.msgExtNot  = message;
	}

    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("ExitNotification");            
                    openElement("GoodsDeclaration");
                  		writeElement("UCRNumber", msgExtNot.getUCRNumber());
                  		writeCodeElement("FinalCode", msgExtNot.getFinalCode(), "KCX0001"); //EI20090424
                  		writeDateTimeToString("ExitOrForwardingTime", msgExtNot.getExitOrForwardingTime());
                  		writeCodeElement("KindOfShipment", msgExtNot.getKindOfShipment(), "KCX0011");    //EI20090424
                  		writeElement("UCROtherSystem", msgExtNot.getUCROtherSystem());
                  		writeElement("ReferenceNumber", msgExtNot.getReferenceNumber());                    		
                  		writeElement("IntendedOfficeOfExit", msgExtNot.getIntendedOfficeOfExit());
                    	writeElement("RealOfficeOfExit", msgExtNot.getRealOfficeOfExit());
                    	writeForwarder(msgExtNot.getForwarder());                  		
                                                                      
                  		if (msgExtNot.getGoodsItemList() != null) {
                  			int listSize = msgExtNot.getGoodsItemList().size();
                  			for (int i = 0; i < listSize; i++) {                         		
                  				MsgExtNotPos position = new MsgExtNotPos();
                  				position = (MsgExtNotPos) msgExtNot.getGoodsItemList().get(i);
                        		writeGoodsItemList(position);     
                  			}
                  		}  
                   
                    closeElement();	 // GoodsDeclaration
                closeElement();      // MsgName
            closeElement();          // Body
            
        } catch (XMLStreamException e) {
           e.printStackTrace();
     	}       
    }
    
    private void writeGoodsItemList(MsgExtNotPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}   
    	openElement("GoodsItem");
    		writeElement("ItemNumber", argument.getItemNumber());      	         	     	    
    		writePackagesList(argument.getPackagesList(), "ExitNotification"); 
    	closeElement();
    }     
    
}  
