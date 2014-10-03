/*
* Function    : TsTD.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : TechnicalData for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsTD<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : TechnicalData for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsTD extends Teilsatz {

    private String transmitter = "";       //
    private String receiver    = "";       //
    private String messageId   = "";       //

    public TsTD() {
        tsTyp = "TD";
        trenner = trennerUK;
    } 

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        transmitter      = fields[1];
        if (size < 3) { return; }
        receiver         = fields[2];
        if (size < 4) { return; }
        messageId        = fields[3];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(transmitter);
        buff.append(trenner);
        buff.append(receiver);
        buff.append(trenner);
        buff.append(messageId);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getTransmitter() {
		return transmitter;	
	}

	public void setTransmitter(String argument) {
		this.transmitter = Utils.checkNull(argument);
	}

	public String getReceiver() {
		return receiver;	
	}

	public void setReceiver(String argument) {
		this.receiver = Utils.checkNull(argument);
	}

	public String getMessageId() {
		return messageId;	
	}

	public void setMessageId(String argument) {
		this.messageId = Utils.checkNull(argument);
	}
	
	public boolean isEmpty() {			
		return (Utils.isStringEmpty(transmitter) && Utils.isStringEmpty(receiver) &&
			    Utils.isStringEmpty(messageId)); 							 
	}
}


