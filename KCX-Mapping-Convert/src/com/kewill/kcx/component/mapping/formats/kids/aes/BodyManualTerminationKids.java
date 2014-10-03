/*
 * Function    : BodyExportErlUK.java
 * Titel       :
 * Date        : 26.09.2008
 * Author      : Kewill CSF / houdek
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff-export-reply.xls
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 27.04.2009
 * Label       :
 * Description : replaced msgUids with MsgExpErl
 * 
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyManualTerminationKids extends KidsMessage {

	private MsgExpErl msgExpErl;

    public BodyManualTerminationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpErl getMessage() {
		return msgExpErl;
	}
	
	public void setMessage(MsgExpErl argument) {
		this.msgExpErl = argument;
	}

    public void writeBody() {
        try {
            openElement("soap:Body");                                     
                openElement("ManualTermination");
                    openElement("GoodsDeclaration");
                       	writeDateTimeToString("TerminationTime", msgExpErl.getTerminationTime());
                        writeElement("Annotation", msgExpErl.getAnnotation());
                        writeElement("ReferenceNumber", msgExpErl.getReferenceNumber());                                          
                        //EI20090609: writeElement("Clerk", msgExpErl.getClerk());
                        writeContact("Contact", msgExpErl.getContact()); //EI20090609
                        if (msgExpErl.getSeal() != null) {
                        	String a1 = msgExpErl.getSeal().getUseOfTydensealStock();
                        	if (!(a1 == null || a1.equals(""))) {
                        		openElement("Seal");
                                    writeElement("UseOfTydenseals", 
                                    		msgExpErl.getSeal().getUseOfTydenseals());  
                         		closeElement();
                        	}
                        }   
                    closeElement();
                closeElement();                                   
            closeElement();	 
          
        } catch (XMLStreamException e) {
               e.printStackTrace();
        }       
    }

}
