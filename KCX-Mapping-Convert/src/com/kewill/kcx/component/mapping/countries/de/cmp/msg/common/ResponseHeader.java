package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import java.util.ArrayList;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: ResponseHeader.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ResponseHeader extends KCXMessage {

    private String type;
    private String subType;
    private String name;
    private String function;
    private String dateTime;
    private String version;
    private String referenceID;
    private String conversationID;
    private HeaderParty recipient;
    private HeaderParty sender;
   
   
    private enum EResponseHeader {
    	MessageType,
    	MessageSubType,    
    	MessageName,
    	MessageFunction,
    	MessageDateTime,
    	MessageSender,
    	MessageRecipient,
    	MessageVersion,
    	MessageReferenceID,
    	MessageConversationID;
    }        

    public ResponseHeader() {
	      	super();	       
    }
    
    public ResponseHeader(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EResponseHeader) tag) {
    			case MessageSender:
    				sender = new HeaderParty(getScanner());
    				sender.parse(tag.name());
    				break;	
    				
    			case MessageRecipient:
    				recipient = new HeaderParty(getScanner());
    				recipient.parse(tag.name());
    				break;
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EResponseHeader) tag) {

    				case MessageType:   
    					setMessageType(value);    					
    					break; 
    					
    				case MessageSubType:   
    					setMessageSubType(value);    					
    					break;
    					
    				case MessageName:
    					setMessageName(value);    					
    					break;
    					
    				case MessageFunction:   
    					setMessageFunction(value);    					
    					break; 
    					
    				case MessageDateTime:   
    					setMessageDateTime(value);   //TODO Format??? 					
    					break;  
    					
    				case MessageVersion:
    					setMessageVersion(value);    					
    					break;
    					
    				case MessageReferenceID:   
    					setMessageReferenceID(value);    					
    					break; 
    					
    				case MessageConversationID:   
    					setMessageConversationID(value);    					
    					break;    				
    				
    				default:
    					break;
    			}
    		}
    	}

    	public void stoppElement(Enum tag) {
    	}


    	public Enum translate(String token) {
    		try {
    			return EResponseHeader.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}

		public String getMessageType() {
			return type;
		}
		public void setMessageType(String type) {
			this.type = type;
		}

		public String getMessageSubType() {
			return subType;
		}
		public void setMessageSubType(String subType) {
			this.subType = subType;
		}

		public String getMessageName() {
			return name;
		}
		public void setMessageName(String name) {
			this.name = name;
		}

		public String getMessageFunction() {
			return function;
		}
		public void setMessageFunction(String function) {
			this.function = function;
		}

		public String getMessageDateTime() {
			return dateTime;
		}
		public void setMessageDateTime(String dateTime) {
			this.dateTime = dateTime;
		}

		public String getMessageVersion() {
			return version;
		}
		public void setMessageVersion(String version) {
			this.version = version;
		}

		public String getMessageReferenceID() {
			return referenceID;
		}
		public void setMessageReferenceID(String referenceID) {
			this.referenceID = referenceID;
		}

		public String getMessageConversationID() {
			return conversationID;
		}
		public void setMessageConversationID(String conversationID) {
			this.conversationID = conversationID;
		}

		public HeaderParty getMessageRecipient() {
			return recipient;
		}
		public void setMessageRecipient(HeaderParty recipient) {
			this.recipient = recipient;
		}

		public HeaderParty getMessageSender() {
			return sender;
		}
		public void setMessageSender(HeaderParty sender) {
			this.sender = sender;
		}
       
		public boolean isEmpty() {		
			return Utils.isStringEmpty(type) && Utils.isStringEmpty(subType) && 
					Utils.isStringEmpty(name) && Utils.isStringEmpty(function) && 
					Utils.isStringEmpty(dateTime) && Utils.isStringEmpty(version) && 
					Utils.isStringEmpty(referenceID) && Utils.isStringEmpty(conversationID) && 
					recipient == null && sender == null;
		}
}
