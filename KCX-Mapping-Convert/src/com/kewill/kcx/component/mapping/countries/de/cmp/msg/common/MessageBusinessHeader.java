package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 17.07.2013<br>
* Description	: BusinessHeaderDocument.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MessageBusinessHeader extends KCXMessage {

    private String id;   
    private String senderAssignedId;
    private ArrayList<HeaderNote> includedHeaderNoteList;
    private AuthentificationType signatoryConsignorAuthentication;
    private AuthentificationType signatoryCarrierAuthentication;
   
    private enum EBusinessHeader {
    	ID,
    	SenderAssignedID,
    	IncludedHeaderNote, 
    	SignatoryConsignorAuthentication,
    	SignatoryCarrierAuthentication,
    }        

    public MessageBusinessHeader() {
	      	super();	       
    }
    
    public MessageBusinessHeader(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EBusinessHeader) tag) {
    			
    			case IncludedHeaderNote:   
    				HeaderNote note = new HeaderNote(getScanner());
    				note.parse(tag.name());
					addHeaderNoteList(note);    					
					break;    				
				
    			case SignatoryConsignorAuthentication:
    				signatoryConsignorAuthentication = new AuthentificationType(getScanner());
    				signatoryConsignorAuthentication.parse(tag.name());
    				break;
    				
    			case SignatoryCarrierAuthentication:
    				signatoryCarrierAuthentication = new AuthentificationType(getScanner());
    				signatoryCarrierAuthentication.parse(tag.name());
    				break;
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EBusinessHeader) tag) {
    				case ID:
    					setId(value);    					
    					break;    				
    				
    				case SenderAssignedID:
    					setSenderAssignedId(value);
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
    			return EBusinessHeader.valueOf(token);
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

	public String getSenderAssignedId() {
		return senderAssignedId;
	}
	public void setSenderAssignedId(String id) {  
		this.senderAssignedId = id;
	}

	public ArrayList<HeaderNote> getIncludedHeaderNoteList() {
		return includedHeaderNoteList;
	}
	public void setIncludedHeaderNoteList(ArrayList<HeaderNote> list) {
		this.includedHeaderNoteList = list;
	}
	public void addHeaderNoteList(HeaderNote value) {
		if (includedHeaderNoteList == null) {
			includedHeaderNoteList = new ArrayList<HeaderNote>();
		}
		this.includedHeaderNoteList.add(value);
	}
		
	public AuthentificationType getSignatoryConsignorAuthentication() {
		return signatoryConsignorAuthentication;
	}

	public void setSignatoryConsignorAuthentication(
			AuthentificationType signatoryConsignorAuthentication) {
		this.signatoryConsignorAuthentication = signatoryConsignorAuthentication;
	}

	public AuthentificationType getSignatoryCarrierAuthentication() {
		return signatoryCarrierAuthentication;
	}

	public void setSignatoryCarrierAuthentication(
			AuthentificationType signatoryCarrierAuthentication) {
		this.signatoryCarrierAuthentication = signatoryCarrierAuthentication;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(id) && Utils.isStringEmpty(senderAssignedId) && 
		signatoryConsignorAuthentication == null && signatoryCarrierAuthentication == null &&
		includedHeaderNoteList == null; 
	}
	
}
