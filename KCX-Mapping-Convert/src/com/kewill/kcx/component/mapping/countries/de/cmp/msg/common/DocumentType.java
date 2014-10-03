package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: CMP<br>
 * Created		: 17.07.2013<br>
 * Description	: DocumentType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DocumentType extends KCXMessage {

	private String id;
    private String issueDate;
    private String typeCode;
    private String name;
       
    public DocumentType() {
	      	super();	       
    }
    
    public DocumentType(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    private enum EDocumentType {
    	ID, 
    	IssueDate,
    	TypeCode,
    	Name,
    }    
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EDocumentType) tag) {
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((EDocumentType) tag) {

    				case ID:   
    					setId(value);    					
    					break; 
    					
    				case IssueDate:
    					setIssueDate(value);    					
    					break;
    					
    				case TypeCode:
    					setTypeCode(value);    					
    					break;
    					
    				case Name:
    					setName(value);    					
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
    			return EDocumentType.valueOf(token);
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

		public String getIssueDate() {
			return issueDate;
		}
		public void setIssueDate(String date) {
			this.issueDate = date;
		}

		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public boolean isEmpty() {
			return Utils.isStringEmpty(id) && Utils.isStringEmpty(issueDate) &&			 	
			Utils.isStringEmpty(typeCode) && Utils.isStringEmpty(name); 
		}	
	
}
