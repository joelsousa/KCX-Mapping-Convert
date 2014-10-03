package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 29.07.2013<br>
* Description	: MessageBusinessHeaderCSN.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MessageBusinessHeaderCSN extends KCXMessage {

    private String statusCode;   
    private String actionTypeCode;
    private String actionTypeName;
    private String information;
    private String issueDateTime;   
    private ArrayList<HeaderNote>  includedHeaderNoteList;
   
    private enum EMessageBusinessHeaderCSN {
    	StatusCode,
    	ActionTypeCode, 
    	ActionTypeCodeName, 
    	Information,
    	IssueDateTime,
    	IncludedHeaderNote,
    }        

    public MessageBusinessHeaderCSN() {
	      	super();	       
    }
    
    public MessageBusinessHeaderCSN(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EMessageBusinessHeaderCSN) tag) {
    			
    			case IncludedHeaderNote:   
    				HeaderNote note = new HeaderNote(getScanner());
    				note.parse(tag.name());
					addHeaderNoteList(note);    					
					break;    				
				    			
    			default:
    					return;
    			}
    		} else {

    			switch ((EMessageBusinessHeaderCSN) tag) {
    				case StatusCode:
    					setStatusCode(value);    					
    					break;    				
    				
    				case ActionTypeCode:
    					setActionTypeCode(value);
    					break;
    					
    				case Information:
    					setInformation(value);    					
    					break;    				
    				
    				case IssueDateTime:
    					setIssueDateTime(value);
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
    			return EMessageBusinessHeaderCSN.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}
  
    	
	public String getStatusCode() {
			return statusCode;
	}
	public void setStatusCode(String value) {  
			this.statusCode = value;
	}

	public String getActionTypeCode() {
		return actionTypeCode;
	}
	public void setActionTypeCode(String value) {  
		this.actionTypeCode = value;
	}

	public String getActionTypeName() {
		return actionTypeName;
	}
	public void setActionTypeName(String value) {  
		this.actionTypeName = value;
	}	
	
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getIssueDateTime() {
		return issueDateTime;
	}

	public void setIssueDateTime(String issueDateTime) {
		this.issueDateTime = issueDateTime;
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
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(statusCode) && Utils.isStringEmpty(actionTypeCode) && 
		Utils.isStringEmpty(actionTypeName) && Utils.isStringEmpty(information) && 
		Utils.isStringEmpty(issueDateTime) && includedHeaderNoteList == null; 
	}
	
}
