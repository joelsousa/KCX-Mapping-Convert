package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import java.util.ArrayList;

/**
* Module		: CMP<br>
* Created		: 07.06.2013<br>
* Description	: MessageHeaderDocument.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MessageHeaderDocument extends KCXMessage {

    private String id;
    private String name;
    private String typeCode;   
    private String issueDateTime;
    private String purposeCode;
    private String versionId;
    private String conversationId;   
    private ArrayList<PartyType> senderPartyList;
    private ArrayList<PartyType> recipientPartyList;
   
   
    private enum EMessageHeaderDocument {
    	ID,
    	Name,
    	TypeCode,
    	IssueDateTime,
    	PurposeCode,    	
    	VersionID,
    	ConversationID,    			     		
      	SenderParty,
      	RecipientParty,
    }        

    public MessageHeaderDocument() {
	      	super();	       
    }
    
    public MessageHeaderDocument(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EMessageHeaderDocument) tag) {
    			//case PartyType:
    			case RecipientParty:   
    				PartyType reci = new PartyType(getScanner());
    				reci.parse(tag.name());
					addRecipientPartyList(reci);    					
					break;    	
					
				case SenderParty:
					PartyType sendi = new PartyType(getScanner());
					sendi.parse(tag.name());
					addSenderPartyList(sendi);    					
					break;
					
    			default:
    					return;
    			}
    		} else {

    			switch ((EMessageHeaderDocument) tag) {
    				case ID:
    					setId(value);    					
    					break;
    					
    				case Name:
    					setName(value);    					
    					break; 
    					
    				case TypeCode:
    					setTypeCode(value);    					
    					break; 
    					
    				case IssueDateTime:
    					setIssueDateTime(value);    					
    					break; 
    					
    				case PurposeCode:
    					setPurposeCode(value);    					
    					break; 
    					
    				case VersionID:
    					setVersionId(value);    					
    					break;
    					
    				case ConversationID:   
    					setConversationId(value);    					
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
    			return EMessageHeaderDocument.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}
  
    	
	public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTypeCode() {
			return typeCode;
		}

		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}

		public String getIssueDateTime() {
			return issueDateTime;
		}

		public void setIssueDateTime(String issueDateTime) {
			this.issueDateTime = issueDateTime;
		}

		public String getPurposeCode() {
			return purposeCode;
		}

		public void setPurposeCode(String purposeCode) {
			this.purposeCode = purposeCode;
		}

		public String getVersionId() {
			return versionId;
		}

		public void setVersionId(String versionId) {
			this.versionId = versionId;
		}

		public String getConversationId() {
			return conversationId;
		}

		public void setConversationId(String conversationId) {
			this.conversationId = conversationId;
		}

	public ArrayList<PartyType> getRecipientPartyList() {
		return recipientPartyList;
	}
	public void setRecipientPartyList(ArrayList<PartyType> list) {
		this.recipientPartyList = list;
	}
	public void addRecipientPartyList(PartyType value) {
		if (recipientPartyList == null) {
			recipientPartyList = new ArrayList<PartyType>();
		}
		this.recipientPartyList.add(value);
	}
	
	public ArrayList<PartyType> getSenderPartyList() {
		return senderPartyList;
	}
	public void setSenderPartyList(ArrayList<PartyType> list) {
		this.senderPartyList = list;
	}
	public void addSenderPartyList(PartyType value) {
		if (senderPartyList == null) {
			senderPartyList = new ArrayList<PartyType>();
		}
		this.senderPartyList.add(value);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(id) && Utils.isStringEmpty(name) && 
				Utils.isStringEmpty(typeCode) && Utils.isStringEmpty(issueDateTime) && 
				Utils.isStringEmpty(purposeCode) && Utils.isStringEmpty(versionId) && 
				Utils.isStringEmpty(conversationId) && 
				senderPartyList == null && recipientPartyList == null; 
	}
	
}
