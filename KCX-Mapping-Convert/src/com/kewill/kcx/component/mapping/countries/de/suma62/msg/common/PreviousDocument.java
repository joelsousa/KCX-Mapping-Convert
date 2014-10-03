package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the PreviousDocument Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class PreviousDocument extends KCXMessage {
	
	private String 	type;
	private String 	reference;
	private String 	marks;        //EI20130827: new in V20: in kids2fss: if reference empty, then map marks
	
	private enum EPreviousDocument {
		//KIDS				//UIDS
		Type,				//same
		Reference, 			//Marks;  //EI20130827: FORD schickt (in UIDS) auch nur Reference und Type, keine Marks
		Marks, 				//same					
	}
	
	public PreviousDocument() {
		super();  
	}

    public PreviousDocument(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EPreviousDocument) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EPreviousDocument) tag) {
  				case Type:  				
  					setType(value);
  					break;
  					
  				case Reference: 
  				//case Marks:
  					setReference(value);
  					break;
  					
  				case Marks:
  					setMarks(value);
  					break;
  					
  				default:
  					return;
  			}
  		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		 try {
				return EPreviousDocument.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = Utils.checkNull(type);
	}

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = Utils.checkNull(reference);
	}

	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = Utils.checkNull(marks);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && Utils.isStringEmpty(reference) && Utils.isStringEmpty(marks));		       
	}

}
