package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       PORT - BL.
 * Created               :       10.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsBillOfLading extends Teilsatz {
    
    private String applicationSenderId    = "";	 // Absenderbezeichnung des Vorgangs DAKOSY(Hamburg) or dbh(Bremen/Bremerhaven) participant code
    private String applicationRecipientId = "";	 // Empfängerbezeichnung des Vorgangs Dakosy or dbh participant code
    private String referenceNumber		  = "";	 // Dokumenten-/Nachrichtennummer unique identifier e.g. DAKOSY Reference
    private String messageFunction		  = "";	 // Nachrichtenfunktion codiert ->Original-Replace-Cancellation
    private String participantCode		  = "";	 // Teilnehmercode aus Customer-> local_id_type = 'BL'->Feld LOCAL_ID split nach "-"

    public TsBillOfLading() {
	    tsTyp = "BILLOFLADING";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    applicationSenderId = fields[1];

	    if (size < 3) { return; }
	    applicationRecipientId = fields[2];

	    if (size < 4) { return; }
	    referenceNumber = fields[3];

	    if (size < 5) { return; }
	    messageFunction = fields[4];
	    
	    if (size < 6) { return; }
	    participantCode = fields[5];
	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(applicationSenderId);
    	buff.append(trenner);
    	buff.append(applicationRecipientId);
    	buff.append(trenner);
    	buff.append(referenceNumber);
    	buff.append(trenner);
    	buff.append(messageFunction);
    	buff.append(trenner);
    	buff.append(participantCode);
    	//buff.append(trenner);

    	return new String(buff);
    }
    
    public String getReferenceNumber() {
    	 return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
    	this.referenceNumber = Utils.checkNull(referenceNumber);
    }

    public String getMessageFunction() {
    	 return messageFunction;
    }
    public void setMessageFunction(String messageFunction) {
    	this.messageFunction = Utils.checkNull(messageFunction);
    }

    public String getApplicationSenderId() {
    	 return applicationSenderId;
    }
    public void setApplicationSenderId(String applicationSenderId) {
    	this.applicationSenderId = Utils.checkNull(applicationSenderId);
    }

    public String getApplicationRecipientId() {
    	 return applicationRecipientId;
    }
    public void setApplicationRecipientId(String applicationRecipientId) {
    	this.applicationRecipientId = Utils.checkNull(applicationRecipientId);
    }

    public boolean isEmpty() {
    	return   Utils.isStringEmpty(messageFunction) &&
	    	 Utils.isStringEmpty(applicationSenderId) &&
	    	 Utils.isStringEmpty(applicationRecipientId);
    }

	public String getParticipantCode() {
		return participantCode;
	}

	public void setParticipantCode(String participantCode) {
		this.participantCode = Utils.checkNull(participantCode);
	}

}
