package com.kewill.kcx.component.mapping.db;

import java.sql.Timestamp;

/**
 * Modul		: MessageIdHistoryDTO<br>
 * Erstellt		: 06.09.2011<br>
 * Beschreibung	: Data Transfer Object for the "message_id_history" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class MessageIdHistoryDTO {
	private String    messageIdOrg    = null;
	private String    messageIdGen    = null;
	private String    messageReceiver = null;
    private String    countryCode     = null;
    private Timestamp messageIdTs     = null;
    
    
    public String getMessageIdOrg() {
        return messageIdOrg;
    }
    public void setMessageIdOrg(String messageIdOrg) {
        this.messageIdOrg = messageIdOrg;
    }
    public String getMessageIdGen() {
        return messageIdGen;
    }
    public void setMessageIdGen(String messageIdGen) {
        this.messageIdGen = messageIdGen;
    }
    public String getMessageReceiver() {
        return messageReceiver;
    }
    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public Timestamp getMessageIdTs() {
        return messageIdTs;
    }
    public void setMessageIdTs(Timestamp messageIdTs) {
        this.messageIdTs = messageIdTs;
    }
}
